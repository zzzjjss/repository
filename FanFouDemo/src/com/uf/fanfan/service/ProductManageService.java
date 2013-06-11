package com.uf.fanfan.service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;

public interface ProductManageService {
	public void  addProduct(Product product);
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,int pageIndex,int shopid);
	public void deleteProduct(Integer id);
}
