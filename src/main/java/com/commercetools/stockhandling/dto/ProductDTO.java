/**
 * 
 */
package com.commercetools.stockhandling.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author M.Othman
 *
 */
public class ProductDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String productId;
	
	
	private String name;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date creationDate;

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
