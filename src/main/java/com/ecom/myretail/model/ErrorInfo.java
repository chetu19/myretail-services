package com.ecom.myretail.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ErrorInfo implements Serializable {
	
	private static final long serialVersionUID = -8346192691366940964L;
	
	private int statusCode;
	private String message;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
