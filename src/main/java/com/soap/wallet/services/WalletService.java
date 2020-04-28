package com.soap.wallet.services;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soap.wallet.database.entities.Client;
import com.soap.wallet.database.entities.PayOrders;
import com.soap.wallet.database.entities.Wallet;
import com.soap.wallet.database.repository.ClientRepository;
import com.soap.wallet.database.repository.PayOrdersRepository;
import com.soap.wallet.database.repository.WalletRepository;
import com.soap.wallet.util.Email;
import com.soap.wallet.util.GeneralException;
import com.soap.wallet.xsd.models.GeneratePayOrderRequest;
import com.soap.wallet.xsd.models.RechargeWalletRequest;

@Service
public class WalletService {
	
	@Autowired
	WalletRepository walletRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	PayOrdersRepository payOrdersRepository;
	
	@Autowired
	Email email;
	
	public void saveWallet(Wallet wallet) {
		walletRepository.save(wallet);
	}
	
	public void rechargeWallet(RechargeWalletRequest rechargeWalletRequest) throws GeneralException {
		if(rechargeWalletRequest.getAmount().doubleValue() < 0.0) {
			throw new GeneralException("El monto a recargar debe ser mayor a 0",400);
		}else {
			Client client = clientRepository.findByDocumentAndCellphone(rechargeWalletRequest.getDocument(), rechargeWalletRequest.getCellphone());
			if(client == null) {
				throw new GeneralException("El documento o numero de celular es incorrecto",400);
			}else {
				Wallet wallet = walletRepository.findByClientClientId(client.getClientId());
				wallet.setAmount(wallet.getAmount() + rechargeWalletRequest.getAmount().doubleValue());
				walletRepository.save(wallet);
			}
		}
		
	}
	
	@Transactional
	public String generatePayOrder(GeneratePayOrderRequest request) throws GeneralException {
		Client client = clientRepository.findByEmail(request.getEmailFromPay());
		if(client == null) {
			throw new GeneralException("Usuario no existe",400);
		}else {
			Wallet wallet = walletRepository.findByClientClientId(client.getClientId());
			if(wallet.getAmount() > request.getAmount().doubleValue()) {
				
				int leftLimit = 97;
			    int rightLimit = 122;
			    int targetStringLength = 6;
			    Random random = new Random();
			    String token = random.ints(leftLimit, rightLimit + 1)
			      .limit(targetStringLength)
			      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			      .toString();
			    
				PayOrders payOrders = new PayOrders();
				String sessionId = String.valueOf(new Date().getTime()) + String.valueOf(new Random().nextInt());
				payOrders.setSessionId(sessionId);
				payOrders.setToken(token);
				payOrders.setWallet(wallet);
				payOrders.setEmailToPay(request.getEmailToPay());
				payOrders.setConfirm(false);
				payOrdersRepository.save(payOrders);
				String emailBody="Estimado "+ client.getFirstName() +" ha sido generada una orden de pago por "+request.getAmount().doubleValue()+""
						+  " coins que seran transferidos a "+request.getEmailToPay()+", por favor ingrese al siguiente link para confirmar el pago http://localhost:3000/wallet/confirm/"+token;
				email.sendEmail(client.getEmail(), "Confirmar Orden de Pago", emailBody);
				return sessionId;
			}else {
				throw new GeneralException("No posee saldo suficiente para generar la orden de pago",400);
			}
		}
	
	}
}
