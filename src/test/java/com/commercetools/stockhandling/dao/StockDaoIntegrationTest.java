package com.commercetools.stockhandling.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.commercetools.stockhandling.entity.Product;
import com.commercetools.stockhandling.entity.Stock;
import com.commercetools.stockhandling.utils.DateRange;

@ExtendWith(SpringExtension.class) // used to provide a bridge between Spring Boot test features and JUnit
@DataJpaTest // provides some standard setup needed for testing the persistence layer
class StockDaoIntegrationTest {

	// TestEntityManager provides methods commonly used when writing tests.
	@Autowired
	private TestEntityManager entityManager;

	// Repository under test
	@Autowired
	private StockDao stockRepository;

	private Stock stockObj;

	private List<Stock> stockList;

	@BeforeEach
	void setUp() {

		// initialize pre defined product object and stock object

		Product productObj = new Product("p1", "product 01");
		stockObj = new Stock("s1", 10, 3, new Date(), productObj);

		entityManager.persist(stockObj);
		entityManager.flush();
	}

	@Test
	void testFindByProductId() {

		// 1-given that we have already stock object exist

		// 2-when method under test executed
		Stock foundStock = stockRepository.findByProductId("p1");

		// not found stock object
		Stock notFoundStock = stockRepository.findByProductId("90909090");

		// 3-then expected value

		assertNotNull(foundStock);
		assertEquals(foundStock.getId(), stockObj.getId());

		// assert that no value retrieved if product not found
		assertNull(notFoundStock);
	}

	@Test
	void testFindByStockId() {

		// 1-given that we have already stock object exist

		Stock foundStock = stockRepository.findByStockId("s1");

		// not found stock object
		Stock notFoundStock = stockRepository.findByStockId("90909090");

		// 3-then expected value

		assertNotNull(foundStock);
		assertEquals(foundStock.getId(), stockObj.getId());

		// assert that no value retrieved if product not found
		assertNull(notFoundStock);
	}
	
	
	@Test
	void testFindTop3ByUpdateDateAfterOrderByQuantityDesc() {

		// 1-given that we have already stock object exist

		stockList = Stream
				.of(
						stockObj,
						new Stock("s2", 15, 5, new Date(), new Product("p2", "product 02")),
						new Stock("s3", 20, 6, new Date(), new Product("p3", "product 03"))
					)
				.collect(Collectors.toCollection(ArrayList::new));

		// persist list of stocks
		stockList.forEach(x -> entityManager.persist(x));

		entityManager.flush();

		// 2-when method under test executed
		List<Stock> topAvailableFoundStockList = stockRepository.findTop3ByUpdateDateAfterOrderByQuantityDesc(DateRange.getDateForSearch("today"));
		
 		// 3-then expected value
		
		// order stock list by available quantity before assertion
		stockList.sort(Comparator.comparingInt(Stock::getQuantity).reversed());
		
		assertNotNull(topAvailableFoundStockList);
		
		assertEquals(stockList, topAvailableFoundStockList);
		
	}

	@Test
	void testFindTop3ByUpdateDateAfterOrderBySoldQuantityDesc() {

		// 1-given that we have already stock object exist

				stockList = Stream
						.of(
								stockObj,
								new Stock("s2", 6, 30, new Date(), new Product("p2", "product 02")),
								new Stock("s3", 5, 40, new Date(), new Product("p3", "product 03"))
							)
						.collect(Collectors.toCollection(ArrayList::new));

				// persist list of stocks
				stockList.forEach(x -> entityManager.persist(x));

				entityManager.flush();

				// 2-when method under test executed
				List<Stock> topSoldFoundStockList = stockRepository.findTop3ByUpdateDateAfterOrderBySoldQuantityDesc(DateRange.getDateForSearch("today"));
				
		 		// 3-then expected value
				
				// order stock list by available quantity before assertion
				stockList.sort(Comparator.comparingInt(Stock::getSoldQuantity).reversed());
				
				assertNotNull(topSoldFoundStockList);
				
				assertEquals(stockList, topSoldFoundStockList);
	}

}
