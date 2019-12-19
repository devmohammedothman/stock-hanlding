/**
 * 
 */
package com.commercetools.stockhandling.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author M.Othman
 *
 */
@ApiModel(description = "All details about the Stock Statistics")
public class StockStatisticsDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "date range that can be used for search" )
	private String rang;
	
	@ApiModelProperty(notes = "Top Available Products in since supplied Date Time Range")
	private List<StockDTO> topAvailableProducts;
	
	@ApiModelProperty(notes = "Top Sold Products in since supplied Date Time Range")
	private List<StockDTO> topSellingProducts;

	/**
	 * @return the rang
	 */
	public String getRang() {
		return rang;
	}

	/**
	 * @param rang the rang to set
	 */
	public void setRang(String rang) {
		this.rang = rang;
	}

	/**
	 * @return the topAvailableProducts
	 */
	public List<StockDTO> getTopAvailableProducts() {
		return topAvailableProducts;
	}

	/**
	 * @param topAvailableProducts the topAvailableProducts to set
	 */
	public void setTopAvailableProducts(List<StockDTO> topAvailableProducts) {
		this.topAvailableProducts = topAvailableProducts;
	}

	/**
	 * @return the topSellingProducts
	 */
	public List<StockDTO> getTopSellingProducts() {
		return topSellingProducts;
	}

	/**
	 * @param topSellingProducts the topSellingProducts to set
	 */
	public void setTopSellingProducts(List<StockDTO> topSellingProducts) {
		this.topSellingProducts = topSellingProducts;
	}
	
	

}
