package com.soap.wallet.database.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long walletId;
	
	private Double amount;
	
	@OneToOne
    @MapsId
    private Client client;
	
	@OneToMany(mappedBy="wallet")
    private Set<PayOrders> payOrders;
	

	public Set<PayOrders> getPayOrders() {
		return payOrders;
	}

	public void setPayOrders(Set<PayOrders> payOrders) {
		this.payOrders = payOrders;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
