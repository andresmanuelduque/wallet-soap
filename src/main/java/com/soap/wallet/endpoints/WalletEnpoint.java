package com.soap.wallet.endpoints;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soap.wallet.services.WalletService;
import com.soap.wallet.util.GeneralException;
import com.soap.wallet.xsd.models.CreateClientRequest;
import com.soap.wallet.xsd.models.RechargeWalletRequest;
import com.soap.wallet.xsd.models.Response;

@Endpoint
public class WalletEnpoint {
	private static final String NAMESPACE_URI = "http://localhost:8080/wallet-soap";
	
	@Autowired
	WalletService walletService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "rechargeWalletRequest")
	@ResponsePayload
	public Response rechargeWalletRequest(@RequestPayload RechargeWalletRequest request) {
		Response response = new Response();
		
		try {
			walletService.rechargeWallet(request);
			response.setErrorMessage("Cartera recargada con éxito");
			response.setSuccess(true);
			response.setErrorCode(BigInteger.valueOf(0));
		} catch (GeneralException e) {
			response.setSuccess(false);
			response.setErrorCode(BigInteger.valueOf(e.getCode()));
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

}
