package com.soap.wallet.database.repository;

import org.springframework.data.repository.CrudRepository;

import com.soap.wallet.database.entities.Client;

public interface ClientRepository extends CrudRepository<Client,Long>{
	public Client findByDocumentOrEmail(String document, String email);
	
	public Client findByDocumentAndCellphone(String document, String cellphone);
	
	public Client findByEmail(String email);
}
