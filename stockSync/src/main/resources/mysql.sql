CREATE SCHEMA `stock` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `stock`.`company_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `stock_code` VARCHAR(45) NOT NULL,
  `company_name` VARCHAR(100) NOT NULL,
  `web_address` VARCHAR(45) NULL,
  `business_content` LONGTEXT NULL,
  PRIMARY KEY (`id`));

