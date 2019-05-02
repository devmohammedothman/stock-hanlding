/**
 * 
 */
package com.commercetools.stockhandling.utils;

/**
 * @author M.Othman
 *
 */
public class StockHandlingException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private StatusCode code;

	/**
	 * Default Constructor
	 */
	public StockHandlingException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message that will be displayed in Exception and passed to super
	 *                Exception class
	 */
	public StockHandlingException(StatusCode code, String message) {
		super(message);

		this.setCode(code);

	}

	/**
	 * @return the code
	 */
	public StatusCode getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(StatusCode code) {
		this.code = code;
	}

}
