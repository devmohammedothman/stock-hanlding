/**
 * 
 */
package com.commercetools.stockhandling.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import com.commercetools.stockhandling.dao.ProductDao;
import com.commercetools.stockhandling.dao.StockDao;
import com.commercetools.stockhandling.dto.ProductDTO;
import com.commercetools.stockhandling.dto.StockDTO;
import com.commercetools.stockhandling.entity.Product;
import com.commercetools.stockhandling.entity.Stock;
import com.commercetools.stockhandling.utils.StockHandlingException;

/**
 * @author Linux Plus
 *
 */
class ManageStockImplTest {

	@Mock
	ProductDao productDao;

	@Mock
	StockDao stockDao;

	@Mock
	ModelMapper modelMapper;

	@InjectMocks
	ManageStockImpl stockService;

	Product entityProductObj;

	ProductDTO dtoProductObj;

	Stock entityStockObj;

	StockDTO dtoStockObj;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#getProductById(java.lang.String)}.
	 */
	@Test
	void testGetProductById() {
		// 1-given objects
		// initialize need objects before running unit tests

		entityProductObj = new Product("p1", "product 01");

		dtoProductObj = new ProductDTO("p1", "product 01");

		// 2-when method under test executed
		when(productDao.findByProductId("p1")).thenReturn(entityProductObj);

		when(modelMapper.map(any(Product.class), any())).thenReturn(dtoProductObj);

		ProductDTO resultProductObj_found = stockService.getProductById("p1");

		// 3- then expected value
		assertNotNull(resultProductObj_found);
		assertEquals("p1", resultProductObj_found.getProductId());

	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#getAllProducts()}.
	 */
	@Test
	void testGetAllProducts() {

		// 1-given objects
		// initialize need objects before running unit tests

		List<Product> entityProductList = Stream
				.of(new Product("p1", "product 01"), new Product("p2", "product 02"), new Product("p3", "product 03"))
				.collect(Collectors.toCollection(ArrayList::new));

		// 2-when method under test executed
		when(productDao.findAll(any(Sort.class))).thenReturn(entityProductList);

		when(modelMapper.map(any(Product.class), any())).thenReturn(new ProductDTO());

		List<ProductDTO> resultProductList_found = stockService.getAllProducts();

		// 3- then expected value
		assertNotNull(resultProductList_found);
		assertEquals(3, resultProductList_found.size());

	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#saveProduct(com.commercetools.stockhandling.dto.ProductDTO)}.
	 */
	@Test
	void testSaveProduct() {

		// 1-given objects
		// initialize need objects before running unit tests

		entityProductObj = new Product("p1", "product 01");

		dtoProductObj = new ProductDTO("p1", "product 01");

		// 2-when method under test executed
		when(productDao.save(entityProductObj)).thenReturn(entityProductObj);

		when(modelMapper.map(any(ProductDTO.class), any())).thenReturn(entityProductObj);

		when(modelMapper.map(any(Product.class), any())).thenReturn(dtoProductObj);

		ProductDTO resultProductObj_found = stockService.saveProduct(dtoProductObj);

		// 3- then expected value
		assertNotNull(resultProductObj_found);
		assertEquals("p1", resultProductObj_found.getProductId());
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#updateProduct(com.commercetools.stockhandling.dto.ProductDTO)}.
	 */
	@Test
	void testUpdateProduct() {

		// 1-given objects
		// initialize need objects before running unit tests
		Date currDate = new Date();

		entityProductObj = new Product("p1", "product 01");

		entityProductObj.setCreationDate(currDate);

		dtoProductObj = new ProductDTO("p1", "product 01");
		dtoProductObj.setCreationDate(currDate);

		// 2-when method under test executed
		when(productDao.findByProductId("p1")).thenReturn(entityProductObj);

		when(productDao.save(entityProductObj)).thenReturn(entityProductObj);

		when(modelMapper.map(any(ProductDTO.class), any())).thenReturn(entityProductObj);

		when(modelMapper.map(any(Product.class), any())).thenReturn(dtoProductObj);

		ProductDTO resultProductObj_found = stockService.updateProduct(dtoProductObj);

		// 3- then expected value
		assertNotNull(resultProductObj_found);
		assertEquals("p1", resultProductObj_found.getProductId());
		assertEquals(currDate, resultProductObj_found.getCreationDate());
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#deleteProduct(java.lang.String)}.
	 */
	@Test
	void testDeleteProduct() {

		// 1-given objects
		// initialize need objects before running unit tests

		entityProductObj = new Product("p1", "product 01");

		dtoProductObj = new ProductDTO("p1", "product 01");

		// 2-when method under test executed
		when(productDao.findByProductId("p1")).thenReturn(entityProductObj);

		boolean resultProductObj_deleted = stockService.deleteProduct("p1");

		// 3- then expected value
		assertTrue(resultProductObj_deleted);
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#addStock(com.commercetools.stockhandling.dto.StockDTO)}.
	 */
	@Test
	void testAddStock() {

		// 1-given objects
		// initialize need objects before running unit tests
		Date currDate = new Date();

		entityProductObj = new Product("p1", "product 01");

		dtoProductObj = new ProductDTO("p1", "product 01");

		entityStockObj = new Stock("s1", 10, 3, currDate, entityProductObj);

		dtoStockObj = new StockDTO("s1", 10, 3, currDate, dtoProductObj);

		// 2-when method under test executed
		when(productDao.findByProductId("p1")).thenReturn(entityProductObj);

		when(modelMapper.map(any(StockDTO.class), any())).thenReturn(entityStockObj);

		when(stockDao.save(entityStockObj)).thenReturn(entityStockObj);

		when(modelMapper.map(any(Stock.class), any())).thenReturn(dtoStockObj);

		StockDTO resultStockObj_found = stockService.updateStock(dtoStockObj);

		// 3- then expected value
		assertNotNull(resultStockObj_found);
		assertNotNull(resultStockObj_found.getProduct());
		assertEquals("p1", resultStockObj_found.getProduct().getProductId());
		assertEquals("s1", resultStockObj_found.getStockId());
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#updateStock(com.commercetools.stockhandling.dto.StockDTO)}.
	 */
	@Test
	void testUpdateStock() {

		// 1-given objects
		// initialize need objects before running unit tests
		Date currDate = new Date();

		entityProductObj = new Product("p1", "product 01");

		dtoProductObj = new ProductDTO("p1", "product 01");

		entityStockObj = new Stock("s1", 10, 3, currDate, entityProductObj);

		dtoStockObj = new StockDTO("s1", 10, 3, currDate, dtoProductObj);

		StockDTO dtoStockObj_without_Product = new StockDTO("s1", 10, 3, currDate);
		
		StockDTO dtoStockObj_notMatch_Product = new StockDTO("s98887", 10, 3, currDate,dtoProductObj);

		// 2-when method under test executed
		when(stockDao.findByProductId("p1")).thenReturn(entityStockObj);

		when(modelMapper.map(any(StockDTO.class), any())).thenReturn(entityStockObj);

		when(stockDao.save(entityStockObj)).thenReturn(entityStockObj);

		when(modelMapper.map(any(Stock.class), any())).thenReturn(dtoStockObj);

		StockDTO resultStockObj_found = stockService.updateStock(dtoStockObj);

		// 3- then expected value
		assertNotNull(resultStockObj_found);
		assertNotNull(resultStockObj_found.getProduct());
		assertEquals("p1", resultStockObj_found.getProduct().getProductId());
		assertEquals("s1", resultStockObj_found.getStockId());
		assertEquals(10, resultStockObj_found.getQuantity());

		assertThrows(StockHandlingException.class, () -> stockService.updateStock(dtoStockObj_without_Product));
		
		assertThrows(StockHandlingException.class, () -> stockService.updateStock(dtoStockObj_notMatch_Product));
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#getStockByProductId(java.lang.String)}.
	 */
	@Test
	void testGetStockByProductId() {

		// 1-given objects
		// initialize need objects before running unit tests

		Date currDate = new Date();

		entityProductObj = new Product("p1", "product 01");

		dtoProductObj = new ProductDTO("p1", "product 01");

		entityStockObj = new Stock("s1", 10, 3, currDate, entityProductObj);

		dtoStockObj = new StockDTO("s1", 10, 3, currDate, dtoProductObj);

		// 2-when method under test executed
		when(stockDao.findByProductId("p1")).thenReturn(entityStockObj);

		when(modelMapper.map(any(Stock.class), any())).thenReturn(dtoStockObj);

		StockDTO resultStockObj_found  = stockService.getStockByProductId("p1");

		// 3- then expected value
		assertNotNull(resultStockObj_found);
		assertEquals("p1", resultStockObj_found.getProduct().getProductId());
		assertEquals("s1", resultStockObj_found.getStockId());
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#getAllStocks()}.
	 */
	@Test
	void testGetAllStocks() {

		// 1-given objects
		// initialize need objects before running unit tests

		Date currDate = new Date();
		
		List<Stock> entityStockList = Stream
				.of(
						new Stock("s1", 10, 3, currDate, new Product("p1", "product 01")),
						new Stock("s2", 15, 6, currDate, new Product("p2", "product 02")),
						new Stock("s3", 20, 9, currDate, new Product("p3", "product 03")))
				.collect(Collectors.toCollection(ArrayList::new));

		// 2-when method under test executed
		when(stockDao.findAll(any(Sort.class))).thenReturn(entityStockList);

		when(modelMapper.map(any(Product.class), any())).thenReturn(new ProductDTO());

		List<StockDTO> resultStockList_found = stockService.getAllStocks();

		// 3- then expected value
		assertNotNull(resultStockList_found);
		assertEquals(3, resultStockList_found.size());
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#deleteStock(java.lang.String)}.
	 */
	@Test
	void testDeleteStock() {

		// 1-given objects
		// initialize need objects before running unit tests

		Date currDate = new Date();

		entityProductObj = new Product("p1", "product 01");

		entityStockObj = new Stock("s1", 10, 3, currDate, entityProductObj);

		// 2-when method under test executed
		when(stockDao.findByStockId("s1")).thenReturn(entityStockObj);

		boolean resultStockObj_deleted = stockService.deleteStock("s1");

		// 3- then expected value
		assertTrue(resultStockObj_deleted);
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#findTop3StocksOrderByAvailableQuantity(java.util.Date)}.
	 */
	@Test
	void testFindTop3StocksOrderByAvailableQuantity() {

		// 1-given objects
		// initialize need objects before running unit tests

		Date currDate = new Date();
		
		List<Stock> top3AvailablestockList = Stream
				.of(new Stock("s1", 10, 3, new Date(), new Product("p1", "product 01")),
						new Stock("s2", 15, 5, new Date(), new Product("p2", "product 02")),
						new Stock("s3", 20, 6, new Date(), new Product("p3", "product 03")))
				.collect(Collectors.toCollection(ArrayList::new));

		// 2-when method under test executed
		when(stockDao.findTop3ByUpdateDateAfterOrderByQuantityDesc(currDate)).thenReturn(top3AvailablestockList);

		when(modelMapper.map(any(Stock.class), any())).thenReturn(new StockDTO());

		List<StockDTO> resultStockList_found = stockService.findTop3StocksOrderByAvailableQuantity(currDate);

		// 3- then expected value
		assertNotNull(resultStockList_found);
		assertEquals(3, resultStockList_found.size());
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.service.impl.ManageStockImpl#findTop3StocksOrderBySoldQuantity(java.util.Date)}.
	 */
	@Test
	void testFindTop3StocksOrderBySoldQuantity() {

		// 1-given objects
		// initialize need objects before running unit tests

		Date currDate = new Date();
		
		List<Stock> top3SoldStockList = Stream
				.of(
						new Stock("s1", 3, 10, new Date(), new Product("p1", "product 01")),
						new Stock("s2", 6, 15, new Date(), new Product("p2", "product 02")),
						new Stock("s3", 9, 20, new Date(), new Product("p3", "product 03")))
				.collect(Collectors.toCollection(ArrayList::new));

		// 2-when method under test executed
		when(stockDao.findTop3ByUpdateDateAfterOrderBySoldQuantityDesc(currDate)).thenReturn(top3SoldStockList);

		when(modelMapper.map(any(Stock.class), any())).thenReturn(new StockDTO());

		List<StockDTO> resultStockList_found = stockService.findTop3StocksOrderBySoldQuantity(currDate);

		// 3- then expected value
		assertNotNull(resultStockList_found);
		assertEquals(3, resultStockList_found.size());
	}

}
