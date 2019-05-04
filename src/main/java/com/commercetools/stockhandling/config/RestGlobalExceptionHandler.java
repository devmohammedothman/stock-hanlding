/**
 * 
 */
package com.commercetools.stockhandling.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.commercetools.stockhandling.utils.ResponseError;
import com.commercetools.stockhandling.utils.SnippetUtils;
import com.commercetools.stockhandling.utils.StatusCode;
import com.commercetools.stockhandling.utils.StockHandlingException;

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
		if (ex instanceof ObjectOptimisticLockingFailureException) {
			
			 resError = new ResponseError(StatusCode.OUTDATEDSTOCK, "Outdated stock, because a newer stock was processed first");
		}
		else if (ex instanceof MethodArgumentNotValidException)
		{
			MethodArgumentNotValidException validationEx = (MethodArgumentNotValidException) ex;
			
			//Get all errors default messages
			 List<String> errorList = validationEx.getBindingResult()
		                .getFieldErrors()
		                .stream()
		                .map(x -> x.getDefaultMessage())
		                .collect(Collectors.toList());
			
			//Call static method to concatenate error string values
	        String errors = SnippetUtils.join(errorList, " , ");
	        
	        resError = new ResponseError(StatusCode.BADREQUEST, errors);
		}
		else
			 resError = new ResponseError(StatusCode.BADREQUEST, "Error While Handling Request");
		
		return new ResponseEntity<>(resError, HttpStatus.BAD_REQUEST);
	}
}
