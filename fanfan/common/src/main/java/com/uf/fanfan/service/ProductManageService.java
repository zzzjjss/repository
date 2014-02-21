package com.uf.fanfan.service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.entity.ProductImage;
import com.uf.fanfan.util.OperationResult;

public interface ProductManageService {
	public void  addProduct(Product product);
	public PageQueryResult<Product> getPageProductsInShopByNameQuery(int pageSize,int pageIndex, int shopid, String nameQuery);
	public OperationResult deleteProductById(Integer id);
	public Product getProductById(Integer id);
	/**
	 * 将某商品从在线商品中下架，
	 * @param product
	 * @return
	 */
	public OperationResult offLineProduct(Product product);
	
	public OperationResult modifyProduct(Product product);
	
	public void addProductImage(ProductImage image);
	
	public void deleteProductImage(int imageId);
	
	
	
}
