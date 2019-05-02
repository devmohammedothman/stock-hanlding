/**
 * 
 */
package com.commercetools.stockhandling.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Helper Class that Handle all controller exceptions
 * 
 * @author M.Othman
 *
 */
@ControllerAdvice
public class RestGlobalExceptionHandler {
	
	
	

	/**
	 * Handle Custom Application Exception
	 * 
	 * @param ex custom application Exception
	 * @return Custom Response Error
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(StockHandlingException ex) {

		ResponseError resError = new ResponseError(ex.getCode(), ex.getMessage());
		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle General Exceptions for Controllers
	 * 
	 * @param ex custom application Exception
	 * @return Custom Response Error
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseError> handleException(Exception ex) {

		ex.printStackTrace();
		
		ResponseError resError = new ResponseError(StatusCode.BADREQUEST, ex.getMessage());
		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}
}
