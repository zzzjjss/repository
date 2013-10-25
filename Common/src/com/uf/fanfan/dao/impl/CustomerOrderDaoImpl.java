package com.uf.fanfan.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.uf.fanfan.dao.CustomerOrderDao;
import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.Shop;

public class CustomerOrderDaoImpl  extends CommonDaoImpl<CustomerOrder> implements CustomerOrderDao{

	@Override
	public List<CustomerOrder> findCustomerOrdersBetweenArriveTime(Date begin,
			Date end, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerOrder> getInProcessingOrderByProductid(
			Integer productid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerOrder> getOneDayOrdersInShop(Shop shop, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerOrder> getShopOnedayOrdersInAgent(Shop shop ,Agent agent,Date date){
		// TODO Auto-generated method stub
		return null;
	}
	
	public CustomerOrder getCustomerOrderByArriveTime(Integer customerId,Timestamp arriveTime){
		return null;
	}

}
