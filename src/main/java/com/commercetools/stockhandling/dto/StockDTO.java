/**
 * 
 */
package com.commercetools.stockhandling.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author M.Othman
 *
 */
@ApiModel(description = "All details about the Stock.")
public class StockDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	// Using java validation api to validate Stock DTO not null attributes
	@ApiModelProperty(notes = "Stock ID String value unique for Stock object")
	@NotNull(message = "Stock Id cannot be null")
	private String stockId;

	@ApiModelProperty(notes = "Product object which is assgined with Stock")
	@NotNull(message = "Product cannot be null")
	private ProductDTO product;

// this validation will be needed  if there is at least exist one item in stock	
	@ApiModelProperty(notes = "Product Available Quantity which is assgined with Stock")
	@NotNull(message = "Quanitity value cannot be null")
	@Min(value = 1, message = "CANNOT MAKE STOCK WITH ZERO QUANTITY  FOR PRODUCT AT LEAST ONE MUST BE EXIST")
	private int quantity;

	@ApiModelProperty(notes = "Product Sold Quantity which is assgined with Stock")
	private int soldQuantity;

	@ApiModelProperty(notes = "DateTime in UTC indicate timestamp of updating Stock in format yyyy-MM-dd" , dataType = "Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date timeStamp;

	/**
	 * Default Constructor
	 */
	public StockDTO() {
	}

	/**
	 * @param stockId
	 * @param quantity
	 * @param soldQuantity
	 * @param timeStamp
	 * @param product
	 */
	public StockDTO(@NotNull(message = "Stock Id cannot be null") String stockId,
			@NotNull(message = "Quanitity value cannot be null") @Min(value = 1, message = "CANNOT MAKE STOCK WITH ZERO QUANTITY  FOR PRODUCT AT LEAST ONE MUST BE EXIST") int quantity,
			int soldQuantity, Date timeStamp, @NotNull(message = "Product cannot be null") ProductDTO product) {
		this.stockId = stockId;
		this.product = product;
		this.quantity = quantity;
		this.soldQuantity = soldQuantity;
		this.timeStamp = timeStamp;
	}

	/**
	 * @param stockId
	 * @param quantity
	 * @param soldQuantity
	 * @param timeStamp
	 */
	public StockDTO(@NotNull(message = "Stock Id cannot be null") String stockId,
			@NotNull(message = "Quanitity value cannot be null") @Min(value = 1, message = "CANNOT MAKE STOCK WITH ZERO QUANTITY  FOR PRODUCT AT LEAST ONE MUST BE EXIST") int quantity,
			int soldQuantity, Date timeStamp) {
		super();
		this.stockId = stockId;
		this.quantity = quantity;
		this.soldQuantity = soldQuantity;
		this.timeStamp = timeStamp;
	}

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

		if (this.timeStamp == null)
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
