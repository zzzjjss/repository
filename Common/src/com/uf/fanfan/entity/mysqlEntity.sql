drop table IF EXISTS agent ;
drop table IF EXISTS product ;
drop table IF EXISTS shop;
drop table IF EXISTS customer ;
drop table IF EXISTS shopmanager;
drop table IF EXISTS platformadmin ;
drop table IF EXISTS deliveryman ;
drop table IF EXISTS order_detail ;
drop table IF EXISTS customer_order;
drop table IF EXISTS id_table;

CREATE TABLE `agent` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phoneNum` varchar(100) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `backMoney` float DEFAULT NULL,
  `realName` varchar(100) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `shop` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phoneNum` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `product` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `productImage` (
	`id` int(11) NOT NULL,
	`productid` int(11) NOT NULL,
	`bit_image` mediumblob ,
	`imageFileExtName` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `totalConsume` float DEFAULT NULL,
  `agentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `shopmanager` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `platformadmin` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `deliveryman` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer_order` (
  `id` bigint(20) NOT NULL,
  `customerid` int(11) NOT NULL,
  `arrivetime` timestamp NOT NULL,
  `tradestate` smallint(6) DEFAULT NULL,
  `sumMoney` float DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL,
  `orderid` bigint(20) NOT NULL,
  `productid` int(11) NOT NULL,
  `tradeprice` float DEFAULT NULL,
  `tradeAmount` int(11) NOT NULL,
  `tradetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `evaluation` varchar(200) DEFAULT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT `detail_order_FK` FOREIGN KEY (`orderid`) REFERENCES `customer_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `id_table` (
  `id_key` varchar(100) NOT NULL,
  `id_value` bigint(20) NOT NULL,
  PRIMARY KEY (`id_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into id_table values('agent_ID',0);
insert into id_table values('product_ID',0);
insert into id_table values('shop_ID',0);
insert into id_table values('customer_ID',0);
insert into id_table values('shopmanager_ID',0);
insert into id_table values('platformadmin_ID',0);
insert into id_table values('deliveryman_ID',0);
insert into id_table values('order_detail_ID',0);
insert into id_table values('customer_order_ID',0);

INSERT INTO fanfan.shop
(id, name, address, phoneNum, description)
VALUES(0, 'fanfan', 'kingdee', '15818590405', 'test shop');
