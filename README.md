## Stock Handling Managment REST API 

This project aims to identify some of latest technologies and best practices applied in JAVA , SPRING BOOT.

While Business is intended to be simple as a result of focusing on used technologies but it also cover some of edge cases and race conditions that might be take place
in typical work environments.

Business briefly describes how to manage a stock of products by 
 
 ** Product API Endpoint

1. Add  Product
2. Update Existing Product
3. Get List of Products / Get Detialed Product
4. Delete Product
 
 **  Stock API Endpoint 

1. Add/Update Stock for Existing Product
2. Get List of Existing Stock 
3. Get Detialed Stock by Product ID
4. Get Statistics about Top Available and Top Sold Products
5. Delete Stock for a certain product
 
 ** Assumptions And Constraints
 
 1. Product Must be added firstly before Add/Update Stock othre wise get Error Message
 2. Mapping relation between Product and Stock is Unidirectional One to One for sake of performance. 
 3. So based on above Stock table must have only one unique product , Stock can not have multiple products.
 4. Get Stock Statistics by Date Range since Last Month till Today or just Today
 5. Stock can be updated with product quantity and sold quantity if exists
 6. concurrent Stock updating not allowed and will return error message , scenario can be simulated on Jmeter (https://jmeter.apache.org/)

---

## Project Architecture

Project is build with  robust and extensible architecture which allow for future updates , considering using Generics , Java 8 Streams
Also project is runnig by default on H2 Database and also Mysql script exists for sake of multi DB providers

1. Entity package which include DB Model Classes
2. Dao package which include JPA Data persistece repositories
3. Service which include business service application interfaces
4. Service impl which include business service application implementaton classes
5. Rest API which has all rest api controllers
6. DTO which is mapping classes for Entity/Model classes applying DTO pattern for sake of separation of concerns
7. Config which is related project configurations 
8. Utils which is util classes supporting either technology aspects or business common need utilities
9. Unit Testing , DAO repository integration Testing , MVC Controller integration Testing.
10.Logging through all of project tiers applied with Aspect Oriented Programming.
11. Generic implementatonfor Response Entity supplied with Custom (Response Status , Response Error , Exception Handler).

Not all test  scenarios covered but most crucial tests included for clarification.     

---

## How To Run

Since that this project is built with maven so you can follow below steps after firstly Clonning Project from Remote Repository into your local machine

* mvn install to install all needed dependencies for the project (stable internet connection needed)

* mvn spring-boot:run to run project and it will be start up on embeded tomcat container which is built in spring-boot.

* mvn test if you need to see result of Unit testing and integration tests

## Project Startup Useful URLS

1. Base URL  http://localhost:8181/stock-handling/ 
2. Swagger API documentation URL http://localhost:8181/stock-handling/swagger-ui.html#/
3. H2 console http://localhost:8181/stock-handling/h2-console/  
		
		JDBC URL : jdbc:h2:mem:db-stock-handling
		
		user name : root
		
		password : root 
		
4. Log File creation path 

		${user.home}/StockHandling/backend/logs/logger.log		
	
** you can check Rest Api urls from Swagger API documentation and here are just samples

* Get All Products  http://localhost:8181/stock-handling/api/product

* Get All Stocks http://localhost:8181/stock-handling/api/stock	
		

---

## Used Technologies

1. Spring Boot — 2.1.4.RELEASE
2. JDK — 1.8  Streams applied in a lot of test cases specially those for Dao integration test
3. Spring Framework 
4. Hibernate 
5. Spring Data JPA
6. Aspect Oriented Programming
7. Maven 
8. Swagger 2+
9. Java Validation API
10. Junit 5 
11. Mockito Framework
