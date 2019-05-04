CREATE DATABASE `db-stock-handling` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

CREATE TABLE `product` (
  `auto_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(45) NOT NULL COMMENT 'this id column is uniquly identify product in string value',
  `name` varchar(45) DEFAULT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`auto_id`),
  UNIQUE KEY `auto_id_UNIQUE` (`auto_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `stock` (
  `auto_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` varchar(45) NOT NULL COMMENT 'this id column is uniquly identify stock in string value',
  `product_id` varchar(45) NOT NULL,
  `available_quantity` int(11) NOT NULL DEFAULT '0',
  `sold_quantity` int(11) DEFAULT '0',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`auto_id`),
  UNIQUE KEY `auto_id_UNIQUE` (`auto_id`),
  UNIQUE KEY `id_UNIQUE` (`stock_id`),
  UNIQUE KEY `product_product_id_UNIQUE` (`product_id`),
  CONSTRAINT `fk_product_stock` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

