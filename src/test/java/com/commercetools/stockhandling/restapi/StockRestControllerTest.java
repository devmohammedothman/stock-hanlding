/**
 * 
 */
package com.commercetools.stockhandling.restapi;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.commercetools.stockhandling.dto.ProductDTO;
import com.commercetools.stockhandling.dto.StockDTO;
import com.commercetools.stockhandling.service.ManageStock;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Linux Plus
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
class StockRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	ManageStock stockService;

	private List<StockDTO> stockList;

	private String CONTEXT_PATH = "/api/stock";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.restapi.StockRestController#getAllStocks()}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetAllStocks() throws Exception {

		// 1-given that we have already stock List object exist

		stockList = Stream
				.of(new StockDTO("s1", 10, 3, new Date(), new ProductDTO("p1", "product 01")),
						new StockDTO("s2", 15, 5, new Date(), new ProductDTO("p2", "product 02")),
						new StockDTO("s3", 20, 6, new Date(), new ProductDTO("p3", "product 03")))
				.collect(Collectors.toCollection(ArrayList::new));

		// 2-when method under test executed
		given(stockService.getAllStocks()).willReturn(stockList);

		// 3-then expected value
		mvc.perform(get(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("status.customMessage", is("Data Retreived Successfully")))
				.andExpect(jsonPath("data", hasSize(3)));

		verify(stockService, VerificationModeFactory.times(1)).getAllStocks();

	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.restapi.StockRestController#updateStock(com.commercetools.stockhandling.dto.StockDTO)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testUpdateStock() throws Exception {

		// 1-given that we have already stock List object exist

		StockDTO newStockObj = new StockDTO("s1", 10, 3, new Date(), new ProductDTO("p1", "product 01"));

		ObjectMapper objectMapper = new ObjectMapper();
		String newStockObjson = objectMapper.writeValueAsString(newStockObj);

		// 2-when method under test executed

		given(stockService.updateStock(Mockito.any())).willReturn(newStockObj);

		// 3-then expected value
		mvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON).content(newStockObjson))
				.andExpect(status().isOk()).andExpect(jsonPath("status.customMessage", is("Data Updated Successfully")))
				.andExpect(jsonPath("data.quantity", is(10)));

		verify(stockService, VerificationModeFactory.times(1)).updateStock(Mockito.any());

	}

	@Test
	void testUpdateStock_WithNotFoundProduct_NotExistProduct() throws Exception {

		// 1-given that we have already stock object exist

		StockDTO newStockObj = new StockDTO("s1", 10, 3, new Date(), new ProductDTO("p1", "product 01"));

		ObjectMapper objectMapper = new ObjectMapper();
		String newStockObjson = objectMapper.writeValueAsString(newStockObj);

		// 2-when method under test executed

		// 3-then expected value
		mvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON).content(newStockObjson))
				.andExpect(status().isOk()).andExpect(jsonPath("status.customMessage", is("This product not exist")));

		verify(stockService, VerificationModeFactory.times(1)).updateStock(Mockito.any());

	}

	@Test
	void testUpdateStock_WithNotsuppliedProduct_NotNullProduct() throws Exception {

		// 1-given that we have already stock object exist

		StockDTO newStockObj = new StockDTO("s1", 10, 3, new Date());

		ObjectMapper objectMapper = new ObjectMapper();
		String newStockObjson = objectMapper.writeValueAsString(newStockObj);

		// 2-when method under test executed

		// 3-then expected value
		mvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON).content(newStockObjson))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("customMessage", is("Product cannot be null")));

		verify(stockService, VerificationModeFactory.times(0)).updateStock(Mockito.any());

	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.restapi.StockRestController#getStockByProductById(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetStockByProductById() throws Exception {

		// 1-given that we have already stock object exist

		StockDTO newStockObj = new StockDTO("s1", 10, 3, new Date(), new ProductDTO("p1", "product 01"));

		// 2-when method under test executed

		given(stockService.getStockByProductId(Mockito.any())).willReturn(newStockObj);

		// 3-then expected value
		mvc.perform(get(CONTEXT_PATH + "/p1")).andExpect(status().isOk())
				.andExpect(jsonPath("status.customMessage", is("Data Retreived Successfully")))
				.andExpect(jsonPath("data.quantity", is(10)));

		verify(stockService, VerificationModeFactory.times(1)).getStockByProductId(Mockito.any());

	}

	@Test
	void testGetStockByNotFoundProductById_NoData() throws Exception {

		// 1-given that we have already stock  object exist

		// 2-when method under test executed

		// given(stockService.getStockByProductId(Mockito.any())).willReturn(newStockObj);

		// 3-then expected value

		mvc.perform(get(CONTEXT_PATH + "/p133333")).andExpect(status().isOk())
				.andExpect(jsonPath("status.customMessage", is("No Data")));

		verify(stockService, VerificationModeFactory.times(1)).getStockByProductId(Mockito.any());

	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.restapi.StockRestController#deleteProduct(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testDeleteStock() throws Exception {

		// 1-given that we have already stock object exist

		// 2-when method under test executed

		given(stockService.deleteStock(Mockito.any())).willReturn(true);

		// 3-then expected value
		mvc.perform(delete(CONTEXT_PATH + "/s1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("status.customMessage", is("Data Deleted Successfully")));

		verify(stockService, VerificationModeFactory.times(1)).deleteStock(Mockito.any());
	}
	
	@Test
	void testDeleteStock_notExistStock() throws Exception {

		// 1-given that we have already stock List object exist

		// 2-when method under test executed

		// 3-then expected value
		mvc.perform(delete(CONTEXT_PATH + "/s13333").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("status.customMessage", is("This Stock not exist")));

		verify(stockService, VerificationModeFactory.times(1)).deleteStock(Mockito.any());
	}

	/**
	 * Test method for
	 * {@link com.commercetools.stockhandling.restapi.StockRestController#getStatistics(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetStatistics() throws Exception {

		// 1-given that we have already stock List object exist

		List<StockDTO> top3AvailablestockList = Stream
				.of(new StockDTO("s1", 10, 3, new Date(), new ProductDTO("p1", "product 01")),
						new StockDTO("s2", 15, 5, new Date(), new ProductDTO("p2", "product 02")),
						new StockDTO("s3", 20, 6, new Date(), new ProductDTO("p3", "product 03")))
				.collect(Collectors.toCollection(ArrayList::new));

		List<StockDTO> top3SoldstockList = Stream
				.of(new StockDTO("s1", 7, 20, new Date(), new ProductDTO("p4", "product 01")),
						new StockDTO("s2", 6, 15, new Date(), new ProductDTO("p5", "product 02")),
						new StockDTO("s3", 5, 10, new Date(), new ProductDTO("p6", "product 03")))
				.collect(Collectors.toCollection(ArrayList::new));

		// 2-when method under test executed
		given(stockService.findTop3StocksOrderByAvailableQuantity(Mockito.any())).willReturn(top3AvailablestockList);
		given(stockService.findTop3StocksOrderBySoldQuantity(Mockito.any())).willReturn(top3SoldstockList);

		// 3-then expected value
		mvc.perform(get(CONTEXT_PATH + "/statistics").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("status.customMessage", is("Data Retreived Successfully")))
				.andExpect(jsonPath("data.topAvailableProducts", hasSize(3)))
				.andExpect(jsonPath("data.topSellingProducts", hasSize(3)));

		verify(stockService, VerificationModeFactory.times(1)).findTop3StocksOrderByAvailableQuantity(Mockito.any());
		verify(stockService, VerificationModeFactory.times(1)).findTop3StocksOrderBySoldQuantity(Mockito.any());
	}

}
