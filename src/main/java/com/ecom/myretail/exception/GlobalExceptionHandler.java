package com.ecom.myretail.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.ecom.myretail.model.ErrorInfo;

/**
 * REST exception handlers defined at a global level for the application. 
 * Extends ResponseEntityExceptionHandler to handle Response Entity Exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
	/**
	 * Global Exception handler for all  MyRetailServiceException exceptions.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MyRetailServiceException.class)
	protected ResponseEntity<ErrorInfo> handleError(MyRetailServiceException ex, WebRequest request) {
		log.error("Error Processing this request", ex.getMessage());
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setMessage(ex.getMessage());
		errorInfo.setStatusCode(HttpStatus.NOT_FOUND.value());
		return response(errorInfo , HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Global Exception handler for other any other exceptions.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorInfo> handleAnyException(Exception e ) {
    	ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setMessage("Internal Server Error Occured");
		errorInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return response(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Send a 409 Conflict in case of concurrent modification 
     */
    @ExceptionHandler({  DataIntegrityViolationException.class })
    public ResponseEntity <ErrorInfo> handleConflict(Exception ex) {
    	ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setMessage(ex.getMessage());
		errorInfo.setStatusCode(HttpStatus.CONFLICT.value());
        return response(errorInfo, HttpStatus.CONFLICT);
    }
	
	
	protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
        log.debug("Responding with a status of {}", status);
        return new ResponseEntity<T>(body, new HttpHeaders(), status);
    }

}