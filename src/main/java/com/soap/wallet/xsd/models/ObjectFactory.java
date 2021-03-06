//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.28 at 12:28:10 PM VET 
//


package com.soap.wallet.xsd.models;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.soap.wallet.xsd.models package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.soap.wallet.xsd.models
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateClientRequest }
     * 
     */
    public CreateClientRequest createCreateClientRequest() {
        return new CreateClientRequest();
    }

    /**
     * Create an instance of {@link RechargeWalletRequest }
     * 
     */
    public RechargeWalletRequest createRechargeWalletRequest() {
        return new RechargeWalletRequest();
    }

    /**
     * Create an instance of {@link GeneratePayOrderRequest }
     * 
     */
    public GeneratePayOrderRequest createGeneratePayOrderRequest() {
        return new GeneratePayOrderRequest();
    }

    /**
     * Create an instance of {@link ConfirmPayOrderRequest }
     * 
     */
    public ConfirmPayOrderRequest createConfirmPayOrderRequest() {
        return new ConfirmPayOrderRequest();
    }

    /**
     * Create an instance of {@link GetBalanceRequest }
     * 
     */
    public GetBalanceRequest createGetBalanceRequest() {
        return new GetBalanceRequest();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

}
