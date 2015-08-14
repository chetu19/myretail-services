package com.ecom.myretail.model;

import java.io.Serializable;


public class ErrorInfo implements Serializable {
	
	private static final long serialVersionUID = -8346192691366940964L;
	
	private int errorCode;
	private String errorMessage;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int statusCode) {
		this.errorCode = statusCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}
	
	
	
	

}
