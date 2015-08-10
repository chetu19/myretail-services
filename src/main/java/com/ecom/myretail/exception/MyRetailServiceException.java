package com.ecom.myretail.exception;

public class MyRetailServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 7890901530013339947L;
	
	public MyRetailServiceException() {
        super();
    }
 
    public MyRetailServiceException(String message) {
        super(message);
    }
 
    public MyRetailServiceException(Throwable cause) {
        super(cause);
    }
    
    public MyRetailServiceException(String message , Throwable cause) {
        super(message , cause);
    }
 
}
