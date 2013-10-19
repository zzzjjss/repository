package com.uf.fanfan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.PageQueryResult;
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
	
	public PageQueryResult<Product> getPageProductsInShopByShopId(int pageSize,int pageIndex,final int shopid){
		return productDao.getPageProductsInShopByShopId(pageSize, pageIndex, shopid);
	}
	@Override
	public Shop getShopById(Integer id) {
		Shop shop=shopDao.loadById(Shop.class, 0);
		return shop;
	}
	@Override
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,
			int pageIndex, Shop shop) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CustomerOrder> getOneDayOrdersInShop(Shop shop, Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<Agent, List<CustomerOrder>> getOneDayAllAgentOrderInShop(
			Shop shop, Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CustomerOrder> getOneDayAgentOrdersInShop(Agent agent,
			Date date, Shop shop) {
		// TODO Auto-generated method stub
		return null;
	}

}
