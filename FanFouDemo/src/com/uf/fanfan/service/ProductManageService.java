package com.uf.fanfan.service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;

public interface ProductManageService {
	public void  addProduct(Product product);
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,int pageIndex,final int shopid,final String qtype,final String queryKey);
	public void deleteProduct(Integer id);
}
