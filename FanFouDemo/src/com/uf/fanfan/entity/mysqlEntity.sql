drop table IF EXISTS agent ;
drop table IF EXISTS product ;
drop table IF EXISTS shop;
drop table IF EXISTS customer ;
drop table IF EXISTS shopmanager;
drop table IF EXISTS platformadmin ;
drop table IF EXISTS tradedetail ;
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
  `image` blob,
  `imageFileName` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `shopid` int(11) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `product_shop_FK` FOREIGN KEY (`id`) REFERENCES `shop` (`id`) ON UPDATE CASCADE
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


CREATE TABLE `tradedetail` (
  `id` bigint(20) NOT NULL,
  `productid` int(11) NOT NULL,
  `customerid` int(11) NOT NULL,
  `tradetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tradestate` smallint(6) DEFAULT NULL,
  `tradeprice` float DEFAULT NULL,
  `evaluation` varchar(200) DEFAULT NULL,
   PRIMARY KEY (`id`)
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
insert into id_table values('tradedetail_ID',0);
