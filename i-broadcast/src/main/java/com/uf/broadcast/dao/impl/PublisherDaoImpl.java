package com.uf.broadcast.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.broadcast.dao.PublisherDao;
import com.uf.broadcast.entity.Publisher;
@Component("publisherDao")
public class PublisherDaoImpl  extends CommonDaoImpl<Publisher> implements PublisherDao{
	
}
