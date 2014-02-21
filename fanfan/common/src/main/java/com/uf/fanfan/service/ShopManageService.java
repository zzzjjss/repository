package com.uf.fanfan.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.entity.Shop;

public interface ShopManageService {
	public Shop getShopById(Integer id);
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,int pageIndex,final Shop shop);
	/**
	 * 获取商店一天所有的客户订单
	 * @param shop
	 * @param date
	 * @return
	 */
	public List<CustomerOrder> getOneDayOrdersInShop(Shop shop,Date date);
	
	/**
	 * 获取某天某个代理点在该店的订单
	 * @param agent
	 * @param date
	 * @param shop
	 * @return
	 */
	public List<CustomerOrder> getOneDayAgentOrdersInShop(Agent agent,Date date,Shop shop);
}
