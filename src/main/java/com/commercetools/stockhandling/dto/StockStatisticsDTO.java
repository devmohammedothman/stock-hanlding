/**
 * 
 */
package com.commercetools.stockhandling.dto;

import java.util.List;

/**
 * @author M.Othman
 *
 */
public class StockStatisticsDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String rang;
	
	private List<StockDTO> topAvailableProducts;
	
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
