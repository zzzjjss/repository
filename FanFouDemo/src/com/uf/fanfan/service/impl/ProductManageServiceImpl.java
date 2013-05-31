package com.uf.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.repository.ProductRepository;
import com.uf.fanfan.service.ProductManageService;
@Service("productManageService")
public class ProductManageServiceImpl implements  ProductManageService{
	@Autowired
	private ProductRepository productRepository;
	
	
	public void  addProduct(Product product){
		productRepository.save(product);
	}
	
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,int pageIndex,int shopid){
		Page<Product> pages= productRepository.findAll(new PageRequest(pageIndex-1, pageSize));
		PageQueryResult<Product> res=new PageQueryResult<Product>();
		res.setPageData(pages.getContent());
		res.setPageIndex(pageIndex);
		res.setTotalPage(pages.getTotalPages());
		res.setPageSize(pageSize);
		res.setTotalRecord(pages.getTotalElements());
		return res;
	}
	
	
	public ProductRepository getProductRep() {
		return productRepository;
	}
	public void setProductRep(ProductRepository productRep) {
		this.productRepository = productRep;
	}
	
	
}
