CREATE DATABASE `rest` ;

CREATE TABLE rest.user (
	id INTEGER NOT NULL AUTO_INCREMENT,
	user_name VARCHAR(100) NOT NULL,
	password VARCHAR(100),
	regist_type integer,
	platform VARCHAR(100),
	CONSTRAINT user_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8;

