/**
 * 
 */
package com.commercetools.stockhandling.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * This class provide basic common implementation for all service classes it is
 * responsible for providing Mapping between Entity , DTO objects
 * 
 * @author M.Othman
 *
 */
@Service
public class BasicServiceImpl<D, E> {

	// adding logging support
	protected static final Logger logger = LogManager.getLogger(BasicServiceImpl.class);
	
	@Autowired
	protected ModelMapper modelMapper;
	
	/**
	 * Convert from Entity to Client Api Model Object
	 * 
	 * @param entity to be converted
	 * @param DTO object
	 * @return DTO object
	 */
	@SuppressWarnings("unchecked")
	public D convertToDTO(E entity, D source) {
		D convertedDtoObj = null;
		try {

			// Perfom Mapping between Entity and DTO
			convertedDtoObj = (D) modelMapper.map(entity, source.getClass());
			
		} catch (Exception ex) {
			logger.error("Error when converting to DTO ", ex);

		}
		return convertedDtoObj;
	}

	/**
	 * Convert from Client Api Model Object to Entity
	 * 
	 * @param Entity 
	 * @param DTO to be converted
	 * @return Entity Object
	 */
	@SuppressWarnings("unchecked")
	public E convertToEntity(E entity, D source) {
		E convertedEntity = null;
		try {

			// Perfom Mapping between Entity and DTO
			convertedEntity = (E) modelMapper.map(source, entity.getClass());

		} catch (Exception ex) {
			//logger.error("Error when converting to Entity ", ex);

		}
		return convertedEntity;
	}

	/**
	 * Used if there is special configuration needed from service classes to Mapping
	 * its Entity objects with Client API Objects
	 */
	protected void configureMapperLocally() {

	}

	protected boolean stringIsBlank(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Used to order Returned data with Id Asc
	 * */
	protected Sort orderByIdAsc() {
	    return new Sort(Sort.Direction.ASC,"id");
	}
	
	/**
	 * Used to order Returned data with Id desc
	 * */
	protected Sort orderByIdDesc() {
	    return new Sort(Sort.Direction.DESC,"id");
	}
}

