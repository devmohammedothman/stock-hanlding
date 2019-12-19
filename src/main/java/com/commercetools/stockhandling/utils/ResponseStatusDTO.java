/**
 * 
 */
package com.commercetools.stockhandling.utils;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Helper Class that represent Response Status
 * @author M.Othman
 *
 */
@ApiModel(description = "Helper Class represents Custom Response Status DTO which will be used in All Responses for REST APIs")
public class ResponseStatusDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Custom Status Code for returned response from API")
	private int customCode;
	
	@ApiModelProperty(notes = "TimeStamp for returned response from API")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date requestTimeStamp = new Date();
	
	@ApiModelProperty(notes = "Custom Status Message for returned response from API")
	private String customMessage;
	
	/**
	 * Constructor
	 * @param customCode code 
	 * @param customMessage message
 	 */
	public ResponseStatusDTO(StatusCode customCode,String customMessage)
	{
		
		this.customCode = customCode.getValue();
		this.customMessage = customMessage;
	}

	public int getCustomCode() {
		return customCode;
	}

	public void setCustomCode(int customCode) {
		this.customCode = customCode;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

	/**
	 * @return the requestTimeStamp
	 */
	public Date getRequestTimeStamp() {
		return requestTimeStamp;
	}

	/**
	 * @param requestTimeStamp the requestTimeStamp to set
	 */
	public void setRequestTimeStamp(Date requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}

		
	

}