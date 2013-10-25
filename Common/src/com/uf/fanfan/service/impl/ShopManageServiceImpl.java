package com.uf.fanfan.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.dao.AgentDao;
import com.uf.fanfan.dao.CustomerOrderDao;
import com.uf.fanfan.dao.ProductDao;
import com.uf.fanfan.dao.ShopDao;
import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.service.ShopManageService;

@Service("shopManagerService")
public class ShopManageServiceImpl implements ShopManageService {
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CustomerOrderDao customerOrderDao;
	@Autowired
	private AgentDao agentDao;
	
	@Override
	public Shop getShopById(Integer id) {
		Shop shop=shopDao.loadById(Shop.class, 0);
		return shop;
	}
	@Override
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,
			int pageIndex, Shop shop) {
		PageQueryResult<Product> res=new PageQueryResult<Product>();
		if(shop!=null&&shop.getId()!=null){
			res= productDao.getPageProductsInShopByShopId(pageSize, pageIndex, shop.getId());	
		}
		return res;
	}
	@Override
	public List<CustomerOrder> getOneDayOrdersInShop(Shop shop, Date date) {
		return customerOrderDao.getOneDayOrdersInShop(shop, date);
	}
	
	@Override
	public List<CustomerOrder> getOneDayAgentOrdersInShop(Agent agent,
			Date date, Shop shop) {
		return customerOrderDao.getShopOnedayOrdersInAgent(shop, agent, date);
	}
	public ShopDao getShopDao() {
		return shopDao;
	}
	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public CustomerOrderDao getCustomerOrderDao() {
		return customerOrderDao;
	}
	public void setCustomerOrderDao(CustomerOrderDao customerOrderDao) {
		this.customerOrderDao = customerOrderDao;
	}
	public AgentDao getAgentDao() {
		return agentDao;
	}
	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}
	
}
