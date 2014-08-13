package com.uf.fanfan.service;

import java.util.Date;
import java.util.List;

import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.Shop;

public interface ShopOrderManageService {
	public List<CustomerOrder> getOneDayOrdersInShop(Shop shop,Date date);
	public List<CustomerOrder> getOneDayAgentOrdersInShop(Agent agent,Date date,Shop shop);
}
