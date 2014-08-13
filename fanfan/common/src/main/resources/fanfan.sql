CREATE DATABASE `fanfan` ;

delimiter $$

CREATE TABLE fanfan.`agent` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phoneNum` varchar(100) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `backMoney` float DEFAULT NULL,
  `realName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE fanfan.`customer` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `totalConsume` float DEFAULT NULL,
  `agentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE fanfan.`customer_order` (
  `id` bigint(20) NOT NULL,
  `customerid` int(11) NOT NULL,
  `arrivetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tradestate` varchar(20) DEFAULT NULL,
  `sumMoney` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE fanfan.`deliveryman` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE fanfan.`id_table` (
  `id_key` varchar(100) NOT NULL,
  `id_value` bigint(20) NOT NULL,
  PRIMARY KEY (`id_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE fanfan.`order_detail` (
  `id` bigint(20) NOT NULL,
  `orderid` bigint(20) NOT NULL,
  `productid` int(11) NOT NULL,
  `tradeprice` float DEFAULT NULL,
  `tradeAmount` int(11) NOT NULL,
  `tradetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `evaluation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `detail_order_FK` (`orderid`),
  CONSTRAINT `detail_order_FK` FOREIGN KEY (`orderid`) REFERENCES `customer_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE fanfan.`platformadmin` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$



delimiter $$

CREATE TABLE fanfan.`shop` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phoneNum` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE fanfan.`product` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `shopid` int(11) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `saleSum` int(11) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_shop_FK` (`shopid`),
  CONSTRAINT `product_shop_FK` FOREIGN KEY (`shopid`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE fanfan.`productimage` (
  `id` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `bit_image` mediumblob,
  `imageFileExtName` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$





delimiter $$

CREATE TABLE fanfan.`shopmanager` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE fanfan.`tradedetail` (
  `id` bigint(20) NOT NULL,
  `productid` int(11) NOT NULL,
  `customerid` int(11) NOT NULL,
  `tradetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tradestate` smallint(6) DEFAULT NULL,
  `tradeprice` float DEFAULT NULL,
  `evaluation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

ALTER TABLE `fanfan`.`shop` 
ADD COLUMN `status` VARCHAR(45) NULL AFTER `description`;
