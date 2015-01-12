package com.uf.rest.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.rest.dao.ProductDao;
import com.uf.rest.entity.Product;
@Component("productDao")
public class ProductDaoImpl extends CommonDaoImpl<Product> implements ProductDao{

}
