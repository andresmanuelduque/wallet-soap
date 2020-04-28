package com.soap.wallet.database.repository;

import org.springframework.data.repository.CrudRepository;

import com.soap.wallet.database.entities.Wallet;

public interface WalletRepository extends CrudRepository<Wallet,Long>{
	public Wallet findByClientClientId(Long clientClientId);
	
}
