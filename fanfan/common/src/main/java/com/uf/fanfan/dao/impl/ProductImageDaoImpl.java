package com.uf.fanfan.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.fanfan.dao.ProductImageDao;
import com.uf.fanfan.entity.ProductImage;
@Component("productImageDao")
public class ProductImageDaoImpl extends CommonDaoImpl<ProductImage> implements ProductImageDao{

}
