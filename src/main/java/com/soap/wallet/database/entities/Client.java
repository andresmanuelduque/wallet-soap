package com.soap.wallet.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="clients")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;
	
	@NotEmpty(message = "Por favor indique un documento")
	@NotBlank(message = "Por favor indique un documento")
	private String document;
	@NotEmpty(message = "Por favor indique el primer nombre")
	@NotBlank(message = "Por favor indique el primer nombre")
	private String firstName;
	@NotEmpty(message = "Por favor indique el segundo nombre")
	@NotBlank(message = "Por favor indique el segundo nombre")
	private String lastName;
	@Email(message = "El correo electronico no es valido")
	private String email;
	@NotEmpty(message = "Por favor indique un numero de celular")
	@NotBlank(message = "Por favor indique un numero de celular")
	private String cellphone;
	
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
}
