package com.uf.fanfan.service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;

public interface ProductManageService {
	public void  addProduct(Product product);
	public PageQueryResult<Product> getPageProductsInShopByNameQuery(int pageSize,int pageIndex, int shopid, String nameQuery);
	public void deleteProduct(Integer id);
	public Product getProduct(Integer id);
}
