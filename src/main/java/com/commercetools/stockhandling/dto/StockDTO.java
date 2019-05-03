/**
 * 
 */
package com.commercetools.stockhandling.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author M.Othman
 *
 */
public class StockDTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;
	
	//Using  java validation  api to validate Stock DTO not null attributes
	@NotNull (message = "Stock Id cannot be null")
	private String stockId;
	
	@NotNull (message = "Product cannot be null")
	private ProductDTO product;

// this validation will be needed  if there is at least exist one item in stock	
	@NotNull (message = "Quanitity value cannot be null")
	@Min(value = 1, message = "CANNOT MAKE STOCK WITH ZERO QUANTITY  FOR PRODUCT AT LEAST ONE MUST BE EXIST")
	private int quantity;
	
	private int soldQuantity;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING	)
	private Date timeStamp;

	/**
	 * @return the stockId
	 */
	public String getStockId() {
		return stockId;
	}

	/**
	 * @param stockId the stockId to set
	 */
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	/**
	 * @return the product
	 */
	public ProductDTO getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the soldQuantity
	 */
	public int getSoldQuantity() {
		return soldQuantity;
	}

	/**
	 * @param soldQuantity the soldQuantity to set
	 */
	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}
	
	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		
		if(this.timeStamp == null)
			this.timeStamp = new Date();
				
		return this.timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
