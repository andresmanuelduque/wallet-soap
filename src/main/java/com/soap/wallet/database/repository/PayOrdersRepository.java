package com.soap.wallet.database.repository;

import org.springframework.data.repository.CrudRepository;

import com.soap.wallet.database.entities.PayOrders;

public interface PayOrdersRepository extends CrudRepository<PayOrders,Long>{
	public PayOrders findBySessionIdAndToken(String sessionId,String token);
}
