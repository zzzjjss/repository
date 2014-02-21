package com.uf.fanfan.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.Shop;

public interface CustomerOrderDao extends CommonDao<CustomerOrder>{
	
	public List<CustomerOrder> findCustomerOrdersBetweenArriveTime( Date begin, Date end, int customerId);
	/**
	 * 查找订单，订单中包含该商品，且订单还没被商家确认。
	 * @param productid
	 * @return
	 */
	public  List<CustomerOrder>  getInProcessingOrderByProductid(Integer productid);
	
	public List<CustomerOrder> getOneDayOrdersInShop(Shop shop, Date date) ;
	/**
	 * 获取商店某代理点某天的所以的订单
	 * @param agent
	 * @param date
	 * @return
	 */
	public List<CustomerOrder> getShopOnedayOrdersInAgent(Shop shop ,Agent agent,Date date);
	public List<CustomerOrder> getCustomerOnedayOrders(Integer customerId,Date date);
}
