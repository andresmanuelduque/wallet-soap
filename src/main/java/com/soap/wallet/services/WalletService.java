package com.soap.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soap.wallet.database.entities.Client;
import com.soap.wallet.database.entities.Wallet;
import com.soap.wallet.database.repository.ClientRepository;
import com.soap.wallet.database.repository.WalletRepository;
import com.soap.wallet.util.GeneralException;
import com.soap.wallet.xsd.models.RechargeWalletRequest;

@Service
public class WalletService {
	
	@Autowired
	WalletRepository walletRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
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
}
