package com.uf.fanfan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.common.ProductState;
import com.uf.fanfan.dao.ProductDao;
import com.uf.fanfan.dao.TradeDetailDao;
import com.uf.fanfan.entity.OrderDetail;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.service.ProductManageService;

public class ProductManageServiceImpl implements  ProductManageService{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private TradeDetailDao tradeDetailDao;
	
	public void  addProduct(Product product){
		productDao.save(product);
	}
	public PageQueryResult<Product> getPageProductsInShopByNameQuery(int pageSize,int pageIndex, int shopid, String nameQuery){
		return productDao.getPageProductsInShopByNameQuery(pageSize, pageIndex, shopid, nameQuery);
	}
	
	public void deleteProduct(Integer id){
		List<OrderDetail> tradeDetail=tradeDetailDao.findByProductid(id);
		if(tradeDetail==null||tradeDetail.size()==0){
			Product pro=new Product();
			pro.setId(id);
			productDao.delete(pro);
		}else{
			Product pro=productDao.loadById(Product.class, id);
			pro.setState(ProductState.DELETE);
			productDao.save(pro);
		}
		
	}
	public Product getProduct(Integer id){
		return this.productDao.loadById(Product.class, id);
	}
	
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public TradeDetailDao getTradeDetailDao() {
		return tradeDetailDao;
	}
	public void setTradeDetailDao(TradeDetailDao tradeDetailDao) {
		this.tradeDetailDao = tradeDetailDao;
	}
	
	
}
