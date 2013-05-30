package com.uf.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uf.fanfan.entity.Product;
import com.uf.fanfan.repository.ProductRepository;
import com.uf.fanfan.service.ProductManageService;
import com.uf.fanfan.util.PageQueryResult;
@Service("productManageService")
public class ProductManageServiceImpl implements  ProductManageService{
	@Autowired
	private ProductRepository productRepository;
	
	
	public void  addProduct(Product product){
		productRepository.save(product);
	}
	
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,int pageIndex,int shopid){
		Page<Product> pages= productRepository.findAll(new PageRequest(pageIndex, pageSize));
		PageQueryResult<Product> res=new PageQueryResult<Product>();
		res.setPageData(pages.getContent());
		res.setPageIndex(pageIndex);
		res.setTotalPage(pages.getTotalPages());
		res.setPageSize(pageSize);
		return res;
	}
	
	
	public ProductRepository getProductRep() {
		return productRepository;
	}
	public void setProductRep(ProductRepository productRep) {
		this.productRepository = productRep;
	}
	
	
}
