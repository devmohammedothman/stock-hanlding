/**
 * 
 */
package com.commercetools.stockhandling.restapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.stockhandling.dto.StockDTO;
import com.commercetools.stockhandling.dto.StockStatisticsDTO;
import com.commercetools.stockhandling.service.ManageStock;
import com.commercetools.stockhandling.utils.DateRange;
import com.commercetools.stockhandling.utils.ResponseDTO;
import com.commercetools.stockhandling.utils.RestProvider;
import com.commercetools.stockhandling.utils.StatusCode;

/**
 * @author M.Othman
 *
 */

@RestController
@RequestMapping("/api/stock")
public class StockRestController {

	@Autowired
	private RestProvider restProvider;

	@Autowired
	private ManageStock stockService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getAllStocks() {

		// Define Custom Response DTO object with custom status Message and actual data
		// get all Stock List
		ResponseDTO responseDto = null;

		List<StockDTO> stockList = null;

		// Get Result Product List
		stockList = stockService.getAllStocks();

		if (stockList != null && stockList.size() > 0)
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Retreived Successfully", stockList);
		else
			responseDto = new ResponseDTO(StatusCode.NOTFOUND, "No Data", null);

		return restProvider.addObj(responseDto);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> updateStock(@Valid @RequestBody StockDTO stocktDto) {

		// Define Custom Response DTO object with custom status Message and actual data
		// update Stock
		ResponseDTO responseDto = null;

		StockDTO savedStock = null;

		// Get Result Product Object
		savedStock = stockService.updateStock(stocktDto);

		if (savedStock != null)
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Updated Successfully", savedStock);
		else
			responseDto = new ResponseDTO(StatusCode.BADREQUEST, "This product not exist", null);

		return restProvider.addObj(responseDto);
	}

	@GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getStockByProductById(@PathVariable("productId") String productId) {

		// Define Custom Response DTO object with custom status Message and actual data
		// get product by Product ID
		ResponseDTO responseDto = null;

		StockDTO resultStockDto = null;

		// Get Result Stock Object
		resultStockDto = stockService.getStockByProductId(productId);

		if (resultStockDto != null)
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Retreived Successfully", resultStockDto);
		else
			responseDto = new ResponseDTO(StatusCode.NOTFOUND, "No Data", null);

		return restProvider.addObj(responseDto);
	}

	@DeleteMapping(path = "{stockId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> deleteStock(@PathVariable("stockId") String stockId) {

		// Define Custom Response DTO object with custom status Message and actual data
		// Delete Stock
		ResponseDTO responseDto = null;

		// Get Result Product Object
		boolean result = stockService.deleteStock(stockId);

		if (result)
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Deleted Successfully",
					"Data Deleted Successfully");
		else
			responseDto = new ResponseDTO(StatusCode.BADREQUEST, "This Stock not exist", null);

		return restProvider.addObj(responseDto);
	}

	@GetMapping(path = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getStatistics(
			@RequestParam(value = "time", defaultValue = "today") String time) {

				
		// Define Custom Response DTO object with custom status Message and actual data
		// get all topAvailableProducts List
		ResponseDTO responseDto = null;
		StockStatisticsDTO resultStatistics = new StockStatisticsDTO();

		List<StockDTO> topAvailableProducts = null;

		List<StockDTO> topSoldProducts = null;

		// Get Result topAvailableProducts List
		topAvailableProducts = stockService.findTop3StocksOrderByAvailableQuantity(DateRange.getDateForSearch(time));

		// Get Result topSoldProducts List
		topSoldProducts = stockService.findTop3StocksOrderBySoldQuantity(DateRange.getDateForSearch(time));

		if (topAvailableProducts != null && topAvailableProducts.size() > 0) {
			// set resultStatistics with found top 3 available products
			resultStatistics.setTopAvailableProducts(topAvailableProducts);

			// check if there is value in top sold products list
			if (topSoldProducts != null && topSoldProducts.size() > 0)
				resultStatistics.setTopSellingProducts(topSoldProducts);
			
			//set range in returned DTO object
			resultStatistics.setRang(time);

			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Retreived Successfully", resultStatistics);
		} else
			responseDto = new ResponseDTO(StatusCode.NOTFOUND, "No Data", null);

		return restProvider.addObj(responseDto);
	}
}
