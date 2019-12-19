/**
 * 
 */
package com.commercetools.stockhandling.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.stockhandling.dao.ProductDao;
import com.commercetools.stockhandling.dao.StockDao;
import com.commercetools.stockhandling.dto.BaseDTO;
import com.commercetools.stockhandling.dto.ProductDTO;
import com.commercetools.stockhandling.dto.StockDTO;
import com.commercetools.stockhandling.entity.BaseEntity;
import com.commercetools.stockhandling.entity.Product;
import com.commercetools.stockhandling.entity.Stock;
import com.commercetools.stockhandling.service.ManageStock;
import com.commercetools.stockhandling.utils.StatusCode;
import com.commercetools.stockhandling.utils.StockHandlingException;

/**
 * @author M.Othman
 *
 */
@Service
@Transactional
public class ManageStockImpl extends BasicServiceImpl<BaseDTO, BaseEntity> implements ManageStock {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private StockDao stockDao;

	/**
	 * find specific product by string value product id
	 */
	@Override
	public ProductDTO getProductById(String productId) {

		// define returned object of product DTO
		ProductDTO productDto = null;

		// define product entity object which save value from DB if exist
		Product productEntity = new Product();

		// check product id value
		if (productId != null && !productId.isEmpty()) {
			productEntity = productDao.findByProductId(productId);

			if (productEntity != null)
				productDto = (ProductDTO) convertToDTO(productEntity, new ProductDTO());
		}

		return productDto;
	}

	/**
	 * Get All Current Products sorted by Auto id Asc
	 */
	@Override
	public List<ProductDTO> getAllProducts() {

		// define returned list of product DTO
		List<ProductDTO> productList = null;

		// define list of products exist on DB
		List<Product> sourceProductList = productDao.findAll(orderByIdAsc());

		if (sourceProductList != null && sourceProductList.size() > 0) {
			productList = sourceProductList.stream().map(p -> (ProductDTO) convertToDTO(p, new ProductDTO()))
					.collect(Collectors.toList());
		}

		return productList;
	}

	/**
	 * Save New Product
	 */
	@Override
	public ProductDTO saveProduct(ProductDTO productDto) {

		// define save product DTO which will be returned after saving into DB
		ProductDTO savedProduct = null;

		Product savedEntity = new Product();

		// converting product dto into entity
		savedEntity = (Product) convertToEntity(savedEntity, productDto);

		savedEntity = productDao.save(savedEntity);

		// convert entity back to DTO
		savedProduct = (ProductDTO) convertToDTO(savedEntity, productDto);

		return savedProduct;

	}

	/**
	 * Update Existing Product
	 */
	@Override
	public ProductDTO updateProduct(ProductDTO productDto) {
		// define save product DTO which will be returned after saving into DB
		ProductDTO savedProduct = null;

		Product savedEntity = productDao.findByProductId(productDto.getProductId());

		// check if product entity found to be updated
		if (savedEntity != null) {
			Long id = savedEntity.getId();
			Long version = savedEntity.getVersion();
			
			Date creationDate = savedEntity.getCreationDate();

			// converting product dto into entity
			savedEntity = (Product) convertToEntity(savedEntity, productDto);

			// set pre defined properties before saving
			savedEntity.setId(id);
			savedEntity.setVersion(version);
			savedEntity.setCreationDate(creationDate);

			savedEntity = productDao.save(savedEntity);

			// convert entity back to DTO
			savedProduct = (ProductDTO) convertToDTO(savedEntity, productDto);
		}

		return savedProduct;
	}

	/**
	 * Delete Existing Product
	 **/
	@Override
	public boolean deleteProduct(String productId) {
		Product foundObj = productDao.findByProductId(productId);

		if (foundObj != null) {

			productDao.deleteById(foundObj.getId());

			return true;
		} else
			return false;

	}

	/**
	 * Add Stock for Existing Product
	 */
	@Override
	public StockDTO addStock(StockDTO stockDTO) {
		// Define stock DTO result object
		StockDTO addedStock = null;

		// Define added stock entity
		Stock addedStockEntity = new Stock();

		// check if product has Stock already
		Product foundProduct = productDao.findByProductId(stockDTO.getProduct().getProductId());

		if (foundProduct != null) {

			// convert stock to entity
			addedStockEntity = (Stock) convertToEntity(addedStockEntity, stockDTO);

			// set existing product in new added stock
			addedStockEntity.setProduct(foundProduct);

			// save stock object
			addedStockEntity = stockDao.save(addedStockEntity);

			// convert added stock to dto result object
			addedStock = (StockDTO) convertToDTO(addedStockEntity, stockDTO);
		}
		return addedStock;
	}

	/**
	 * Update Stock for Existing Product
	 */
	@Override
	public StockDTO updateStock(StockDTO stockDTO) {
		
		// Define stock DTO result object
		StockDTO resultStock = null;

		// Define added stock entity
		Stock addedStockEntity = new Stock();

		// check found stock have product object
		if (stockDTO.getProduct() == null || stockDTO.getProduct().getProductId() == null
				|| stockDTO.getProduct().getProductId().isEmpty())
			throw new StockHandlingException(StatusCode.BADREQUEST, "There is no supplied product");

		// check if product has Stock already
		Stock foundStock = stockDao.findByProductId(stockDTO.getProduct().getProductId());

		//check if stock is already exist for the same product before update its value
		//because it  may send existing product but for another stock
		if(foundStock != null && !foundStock.getStockId().equals(stockDTO.getStockId()))
			throw  new StockHandlingException(StatusCode.BADREQUEST , "Supplied Stock ID not matching with it's existing  product ID");
		
		// Means that this product does not have stock and we will add it
		else if(foundStock == null)
		{
			resultStock = addStock(stockDTO);
			return resultStock;
		}
		
		//update existing stock
		if(foundStock != null) {
			Long id = foundStock.getId();
			Long version = foundStock.getVersion();

			// define temp product object to not update existing product
			Product tempProduct = foundStock.getProduct();
			// convert stock to entity
			addedStockEntity = (Stock) convertToEntity(foundStock, stockDTO);

			addedStockEntity.setId(id);
			addedStockEntity.setVersion(version);
			addedStockEntity.setProduct(tempProduct);

			// save stock object
			addedStockEntity = stockDao.save(addedStockEntity);

			// convert added stock to dto result object
			resultStock = (StockDTO) convertToDTO(addedStockEntity, stockDTO);
		}
		
		return resultStock;
	}

	/**
	 * find specific stock by string value product id
	 */
	@Override
	public StockDTO getStockByProductId(String productId) {

		// define returned object of Stock DTO
		StockDTO stockDto = null;

		// define product entity object which save value from DB if exist
		Stock stockEntity = new Stock();

		// check product id value
		if (productId != null && !productId.isEmpty()) {
			stockEntity = stockDao.findByProductId(productId);

			if (stockEntity != null)
				stockDto = (StockDTO) convertToDTO(stockEntity, new StockDTO());
		}

		return stockDto;
	}

	/**
	 * Get All available Stocks
	 */
	@Override
	public List<StockDTO> getAllStocks() {

		// define returned list of product DTO
		List<StockDTO> stockList = null;

		// define list of products exist on DB
		List<Stock> sourceStockList = stockDao.findAll(orderByIdAsc());

		if (sourceStockList != null && sourceStockList.size() > 0) {
			stockList = sourceStockList.stream().map(p -> (StockDTO) convertToDTO(p, new StockDTO()))
					.collect(Collectors.toList());
		}

		return stockList;
	}

	/**
	 * Delete Stock by Stock Id
	 */
	@Override
	public boolean deleteStock(String stockId) {

		// find stock object by stock id
		Stock foundObj = stockDao.findByStockId(stockId);

		if (foundObj != null) {

			stockDao.deleteById(foundObj.getId());

			return true;
		} else
			return false;
	}

	/**
	 * Find top 3 available products
	 */
	@Override
	public List<StockDTO> findTop3StocksOrderByAvailableQuantity(Date timeSearchValue) {

		// define list of Stock DTO result
		List<StockDTO> stockDtoResultList = null;

		// call stock repository to get top 3 available products
		List<Stock> stockEntityList;
		stockEntityList = stockDao.findTop3ByUpdateDateAfterOrderByQuantityDesc(timeSearchValue);

		// check result list before mapping into dto list
		if (stockEntityList != null && stockEntityList.size() > 0) {
			stockDtoResultList = stockEntityList.stream().map(p -> (StockDTO) convertToDTO(p, new StockDTO()))
					.collect(Collectors.toList());
		}
		return stockDtoResultList;

	}

	/**
	 * find top 3 sold products
	 */
	@Override
	public List<StockDTO> findTop3StocksOrderBySoldQuantity(Date timeSearchValue) {
		// define list of Stock DTO result
		List<StockDTO> stockDtoResultList = null;

		// call stock repository to get top 3 sold products
		List<Stock> stockEntityList;
		stockEntityList = stockDao.findTop3ByUpdateDateAfterOrderBySoldQuantityDesc(timeSearchValue);

		// check result list before mapping into dto list
		if (stockEntityList != null && stockEntityList.size() > 0) {
			stockDtoResultList = stockEntityList.stream().map(p -> (StockDTO) convertToDTO(p, new StockDTO()))
					.collect(Collectors.toList());
		}

		return stockDtoResultList;
	}

	@PostConstruct
	public void init() {
		// configure mapper with local settings
		configureMapperLocally();
	}

	@Override
	protected void configureMapperLocally() {

		if (modelMapper != null) {
			// skip some of entity fields from being mapped
			PropertyMap<ProductDTO, Product> productSkipModifiedFieldsMap = new PropertyMap<ProductDTO, Product>() {
				protected void configure() {
					skip().setId(null);
				}
			};

			PropertyMap<StockDTO, Stock> stockSkipModifiedFieldsMap = new PropertyMap<StockDTO, Stock>() {
				protected void configure() {
					skip().setId(null);
					map().setUpdateDate(source.getTimeStamp());
				}
			};

			modelMapper.addMappings(productSkipModifiedFieldsMap);
			modelMapper.addMappings(stockSkipModifiedFieldsMap);
		}
	}
}
