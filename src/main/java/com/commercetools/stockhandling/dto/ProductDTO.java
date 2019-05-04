/**
 * 
 */
package com.commercetools.stockhandling.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author M.Othman
 *
 */
@ApiModel(description = "All details about the Product.")
public class ProductDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	//Using  java validation  api to validate Product DTO not null attributes
	@ApiModelProperty(notes = "Product ID String value unique for Product object" , required = true)
	@NotNull (message = "Product Id cannot be null")
	private String productId;
	
	@ApiModelProperty(notes = "name of Product" , required = false)
	private String name;
	
	@ApiModelProperty(notes = "Creation Date Time of Product in format yyyy-MM-dd" , readOnly = true , dataType= "Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date creationDate;

	
	/**
	 * Default constructor
	 */
	public ProductDTO() {
	}
	
	

	/**
	 * @param productId
	 * @param name
	 */
	public ProductDTO(@NotNull(message = "Product Id cannot be null") String productId, String name) {
	
		this.productId = productId;
		this.name = name;
	}

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
