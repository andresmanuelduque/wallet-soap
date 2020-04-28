package com.soap.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soap.wallet.database.entities.Client;
import com.soap.wallet.database.entities.Wallet;
import com.soap.wallet.database.repository.ClientRepository;
import com.soap.wallet.util.GeneralException;
import com.soap.wallet.xsd.models.CreateClientRequest;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	WalletService walletService;
	
	public void saveClient(CreateClientRequest clientRequest) throws GeneralException {
		System.out.println("Se esta haciendo una peticion");
		Client client = new Client();
		Client clientExist = clientRepository.findByDocumentOrEmail(clientRequest.getDocument(), clientRequest.getEmail());
		if(clientExist == null) {
			client.setDocument(clientRequest.getDocument());
			client.setCellphone(clientRequest.getCellphone());
			client.setFirstName(clientRequest.getFirstName());
			client.setLastName(clientRequest.getLastName());
			client.setEmail(clientRequest.getEmail());	
			Wallet wallet = new Wallet();
			wallet.setAmount(0.0);
			wallet.setClient(client);
			client.setWallet(wallet);
		}else {
			throw new GeneralException("El cliente ya se encuentra registrado",400);
		}
		try {
			clientRepository.save(client);
		}catch(javax.validation.ConstraintViolationException e) {
			throw new GeneralException(e.getConstraintViolations().iterator().next().getMessageTemplate(),400);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
