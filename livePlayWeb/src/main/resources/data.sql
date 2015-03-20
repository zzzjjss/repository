INSERT INTO liveplay.user
(user_name, password, phone, role, create_time)
VALUES('xu', 'xu', '1234', 'publisher', STR_TO_DATE('2015-03-06 15:00:11','%Y-%m-%d %H:%i:%s'));




INSERT INTO liveplay.servicer
(user_name, password)
VALUES('qq0', 'qq0');


CREATE TABLE liveplay.teacher (
	id INTEGER NOT NULL AUTO_INCREMENT,
	user_name VARCHAR(100) NOT NULL,
	password VARCHAR(100),
	real_name VARCHAR(100),
	CONSTRAINT teacher_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;


CREATE TABLE liveplay.public_message (
	id INTEGER NOT NULL AUTO_INCREMENT,
	message_type VARCHAR(100) NOT NULL,
	message_content VARCHAR(1000),
	CONSTRAINT teacher_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;