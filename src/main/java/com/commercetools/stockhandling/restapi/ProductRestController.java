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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.stockhandling.dto.ProductDTO;
import com.commercetools.stockhandling.service.ManageStock;
import com.commercetools.stockhandling.utils.ResponseDTO;
import com.commercetools.stockhandling.utils.RestProvider;
import com.commercetools.stockhandling.utils.StatusCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author M.Othman
 *
 */

@RestController
@RequestMapping("/api/product")
@Api(value = "Stock Handling REST API")
public class ProductRestController {

	@Autowired
	private RestProvider restProvider;

	@Autowired
	private ManageStock stockService;

	@ApiOperation(value = "View a list of available Products")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Data Retreived Successfully"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getAllProducts() {
		
		// Define Custom Response DTO object with custom status Message and actual data
		// get all products List
		ResponseDTO responseDto = null;
		
		List<ProductDTO> productList = null;
		
		// Get Result Product List
		productList = stockService.getAllProducts();
		
		if (productList != null && productList.size() > 0)
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Retreived Successfully", productList);
		else
			responseDto = new ResponseDTO(StatusCode.NOTFOUND, "No Data", null);

		return restProvider.addObj(responseDto);
	}
	
	@ApiOperation(value = "Get Product By Product ID")
	@GetMapping(path="/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getProductById(@PathVariable("productId") String productId) {
		
		// Define Custom Response DTO object with custom status Message and actual data
		// get product by Product ID
		ResponseDTO responseDto = null;
		
		ProductDTO resultProductDto = null;
		
		// Get Result Product Object
		resultProductDto = stockService.getProductById(productId);
		
		if (resultProductDto != null )
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Retreived Successfully", resultProductDto);
		else
			responseDto = new ResponseDTO(StatusCode.NOTFOUND, "No Data", null);

		return restProvider.addObj(responseDto);
	}
	
	@ApiOperation(value = "Add New Product")
	@ApiParam(value="Product object with mandatory product ID value")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<ResponseDTO> saveProduct(@Valid @RequestBody ProductDTO productDto) {
		
		// Define Custom Response DTO object with custom status Message and actual data
		// save New Product
		ResponseDTO responseDto = null;
		
		ProductDTO savedProduct = null;
		
		// Get Result Product Object
		savedProduct = stockService.saveProduct(productDto);
		
		if (savedProduct != null)
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Added Successfully", savedProduct);
		else
			responseDto = new ResponseDTO(StatusCode.BADREQUEST, "Not Saved", null);

		return restProvider.addObj(responseDto);
	}
	
	@ApiOperation(value = "Update Existing Product")
	@ApiParam(value="Product object with mandatory product ID value")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody ProductDTO productDto) {
		
		// Define Custom Response DTO object with custom status Message and actual data
		// update Product
		ResponseDTO responseDto = null;
		
		ProductDTO savedProduct = null;
		
		// Get Result Product Object
		savedProduct = stockService.updateProduct(productDto);
		
		if (savedProduct != null)
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Updated Successfully", savedProduct);
		else
			responseDto = new ResponseDTO(StatusCode.BADREQUEST, "This product not exist", null);

		return restProvider.addObj(responseDto);
	}
	
	@ApiOperation(value = "Delete Existing Product with Product ID")
	@DeleteMapping(path="{productId}",produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable("productId") String productId) {
		
		// Define Custom Response DTO object with custom status Message and actual data
		// Delete Product
		ResponseDTO responseDto = null;
		
		
		// Get Result Product Object
		boolean result = stockService.deleteProduct(productId);
		
		if (result)
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Deleted Successfully",  "Data Deleted Successfully");
		else
			responseDto = new ResponseDTO(StatusCode.BADREQUEST, "This product not exist", null);

		return restProvider.addObj(responseDto);
	}
}
