/**
 * 
 */
package com.commercetools.stockhandling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commercetools.stockhandling.entity.Product;

/**
 * @author M.Othman
 *
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
	
	Product findByProductId(String productId);

}
