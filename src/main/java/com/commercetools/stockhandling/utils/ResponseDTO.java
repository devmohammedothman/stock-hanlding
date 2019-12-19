/**
 * 
 */
package com.commercetools.stockhandling.utils;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Helper Class That represents Custom Response DTO which will be used in Response for REST APIs
 * @author M.Othman
 *
 */
@ApiModel(description = "Helper Class represents Custom Response DTO which will be used in All Responses for REST APIs")
public class ResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Custom Status object for returned response from API includes time , code , message")
	private ResponseStatusDTO status;
	
	@ApiModelProperty(notes = "Actual returned Data from API exist in this property")
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
