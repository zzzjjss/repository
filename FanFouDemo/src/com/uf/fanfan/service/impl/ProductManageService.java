package com.uf.fanfan.service.impl;

import com.uf.fanfan.entity.Product;
import com.uf.fanfan.repository.ProductRepository;
import com.uf.fanfan.service.ProductManage;
import com.uf.fanfan.util.PageQueryResult;

public class ProductManageService implements  ProductManage{
	private ProductRepository productRep;
	
	
	public void  addProduct(Product product){
		productRep.save(product);
	}
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,int pageIndex,int shopid){
		return null;
	}
	public ProductRepository getProductRep() {
		return productRep;
	}
	public void setProductRep(ProductRepository productRep) {
		this.productRep = productRep;
	}
	
	
}
