package com.soap.wallet.endpoints;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;

import org.dom4j.QName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soap.wallet.services.ClientService;
import com.soap.wallet.util.GeneralException;
import com.soap.wallet.xsd.models.CreateClientRequest;
import com.soap.wallet.xsd.models.Response;



@Endpoint
public class ClientEndpoint {
	private static final String NAMESPACE_URI = "http://localhost:8080/wallet-soap";
	
	@Autowired
	ClientService clientService;
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createClientRequest")
	@ResponsePayload
	public Response  createClientRequest(@RequestPayload CreateClientRequest request) {
		Response response = new Response();
		try {
			clientService.saveClient(request);
			response.setErrorMessage("Cliente creado con exito");
			response.setSuccess(true);
			response.setErrorCode(BigInteger.valueOf(0));
			response.setData("");
		} catch (GeneralException e) {
			response.setSuccess(false);
			response.setErrorCode(BigInteger.valueOf(e.getCode()));
			response.setErrorMessage(e.getMessage());
			response.setData("");
		}catch(Exception e) {
			response.setSuccess(false);
			response.setErrorCode(BigInteger.valueOf(500));
			response.setErrorMessage(e.getMessage());
			response.setData("");
		}
		return response;
	}

}
