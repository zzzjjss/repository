package com.uf.rest.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.rest.dao.ProductClassDao;
import com.uf.rest.entity.ProductClass;
@Component("productClassDao")
public class ProductClassDaoImpl extends CommonDaoImpl<ProductClass> implements ProductClassDao{

}
