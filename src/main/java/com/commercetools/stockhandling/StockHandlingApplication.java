package com.commercetools.stockhandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.commercetools.stockhandling")
public class StockHandlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockHandlingApplication.class, args);
	}

}
