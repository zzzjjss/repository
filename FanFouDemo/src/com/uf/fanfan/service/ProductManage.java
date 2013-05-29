package com.uf.fanfan.service;

import com.uf.fanfan.entity.Product;
import com.uf.fanfan.util.PageQueryResult;

public interface ProductManage {
	public void  addProduct(Product product);
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,int pageIndex,int shopid);
}
