package com.soap.wallet.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PayOrders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long payOrderId;
	private String sessionId;
	private String token;
	private	String emailToPay;
	private boolean confirm;
	private Double amount;
	
	
	@ManyToOne
    @JoinColumn(name="wallet_id", nullable=false)
    private Wallet wallet;

	

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public String getEmailToPay() {
		return emailToPay;
	}

	public void setEmailToPay(String emailToPay) {
		this.emailToPay = emailToPay;
	}

	public Long getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Long payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
}
