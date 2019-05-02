/**
 * 
 */
package com.commercetools.stockhandling.utils;

import org.springframework.dao.DataIntegrityViolationException;
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

		ResponseError resError ; 
		
		//check if exception is instance of DataIntegrityViolationException to wrap different custom message
		if (ex instanceof DataIntegrityViolationException) {
			
			 resError = new ResponseError(StatusCode.BADREQUEST, "Supplied paramters not suffecient or not matching with DB constraints");
		}
		else
			 resError = new ResponseError(StatusCode.BADREQUEST, "Error While Handling Request");
		
		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}
}
