package com.ecom.myretail.exception;

public class MyRetailServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 7890901530013339947L;
	
	private int errorCode ;
	
	public MyRetailServiceException() {
        super();
    }
 
    public MyRetailServiceException(String message) {
        super(message);
    }
 
    public MyRetailServiceException(Throwable cause) {
        super(cause);
    }
    
    public MyRetailServiceException( int errorCode , String message) {
        super(message);
    	this.errorCode = errorCode;
    }
    public MyRetailServiceException(String message , Throwable cause) {
        super(message , cause);
    }
    
    public MyRetailServiceException(int errorCode , String message , Throwable cause) {
        super(message , cause);
        this.errorCode = errorCode;
    }

	/**
	 * @returns the errorCode for this exception if present
	 */
	public int getErrorCode() {
		return errorCode;
	}

    
    
 
}
