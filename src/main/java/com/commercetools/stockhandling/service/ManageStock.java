/**
 * 
 */
package com.commercetools.stockhandling.service;

import java.util.Date;
import java.util.List;

import com.commercetools.stockhandling.dto.ProductDTO;
import com.commercetools.stockhandling.dto.StockDTO;

/**
 * @author M.Othman
 *
 */
public interface ManageStock {
	
	ProductDTO getProductById(String productId);
	
	List<ProductDTO> getAllProducts();
	
	ProductDTO saveProduct(ProductDTO productDto);
	
	ProductDTO updateProduct(ProductDTO productDto);
	
	boolean deleteProduct(String productId);
	
	StockDTO addStock(StockDTO stockDTO);
	
	StockDTO updateStock(StockDTO stockDTO);
	
	StockDTO getStockByProductId(String productId);
	
	List<StockDTO> getAllStocks();
	
	boolean deleteStock(String stockId);
	
	List<StockDTO> findTop3StocksOrderByAvailableQuantity(Date timeSearchValue);
	
	List<StockDTO> findTop3StocksOrderBySoldQuantity(Date timeSearchValue);

}
