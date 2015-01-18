package com.uf.rest.dao;

import java.util.List;

import com.uf.rest.entity.ProductClass;

public interface ProductClassDao extends CommonDao<ProductClass>{
	public List<ProductClass> findPagedProductClass(Integer start, Integer count);
}
