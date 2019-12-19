/**
 * 
 */
package com.commercetools.stockhandling.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.commercetools.stockhandling.entity.Stock;

/**
 * @author M.Othman
 *
 */
@Repository
public interface StockDao extends JpaRepository<Stock, Long> {
	
	@Query(value = " SELECT s FROM Stock s WHERE s.product.productId  = :productId")
	Stock findByProductId(@Param("productId")String productId);
	
	Stock findByStockId(String stockId);
	
	
	List<Stock> findTop3ByUpdateDateAfterOrderByQuantityDesc(Date updateDate);
	
	List<Stock> findTop3ByUpdateDateAfterOrderBySoldQuantityDesc(Date updateDate);

}
