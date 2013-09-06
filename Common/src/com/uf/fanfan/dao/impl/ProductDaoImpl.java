package com.uf.fanfan.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.dao.ProductDao;
import com.uf.fanfan.entity.Product;
@Component("productDao")
public class ProductDaoImpl extends CommonDaoImpl<Product> implements ProductDao{
	
	public PageQueryResult<Product> getPageProductsInShopByNameQuery(int pageSize,int pageIndex,final int shopid,final String nameQuery){
		StringBuilder hql=new StringBuilder("select p from Product p where p.Shop.id=:shopid and p.name like:name ");
		Map<String, Object> paramValue=new HashMap<String, Object>();
		paramValue.put("shopid", new Integer(shopid));
		paramValue.put("name", nameQuery);
		PageQueryResult<Product> result=queryPageEntity(pageSize, pageIndex, hql.toString(), paramValue);
		return result;
		}
		 
	
}
