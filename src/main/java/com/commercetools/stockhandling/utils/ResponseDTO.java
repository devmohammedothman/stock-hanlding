/**
 * 
 */
package com.commercetools.stockhandling.utils;

import java.io.Serializable;

/**
 * Helper Class That represents Custom Response DTO which will be used in Response for REST APIs
 * @author M.Othman
 *
 */
public class ResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ResponseStatusDTO status;
	private Object data;
	
	/**
	 * Constructor
	 * @param customCode Status Code that represent status of Response
	 * @param customMessage Custom Message 
	 * @param data Actual Response Data
	 */
	public ResponseDTO(StatusCode customCode,String customMessage, Object data) {

		this.status = new ResponseStatusDTO(customCode,customMessage);
		this.data = data;
	}
	
	
	public ResponseStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ResponseStatusDTO status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
