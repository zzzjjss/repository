package com.uf.fanfan.dao;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;


public interface ProductDao extends CommonDao<Product>{
	public PageQueryResult<Product> getPageProductsInShopByNameQuery(int pageSize,int pageIndex,final int shopid,final String nameQuery);
	public PageQueryResult<Product> getPageProductsInShopByShopId(int pageSize,int pageIndex,final int shopid);
}
