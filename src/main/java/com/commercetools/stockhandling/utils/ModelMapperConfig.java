/**
 * 
 */
package com.commercetools.stockhandling.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Model Mapper Config Class is a configuration class to define Bean of Type Model Mapper
 * Model Mapper used later for mapping between Entity Classes and Client response Model Classes
 * @author M.Othman
 *
 */
@Configuration
public class ModelMapperConfig {
	
	/**
	 * Initialize Bean of Model Mapper with standard matching strategy
	 * @return ModelMapper Bean Object
	 */
	@Bean
    public ModelMapper strictModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

}