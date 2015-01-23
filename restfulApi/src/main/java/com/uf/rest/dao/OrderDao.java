package com.uf.rest.dao;

import java.util.Date;
import java.util.List;

import com.uf.rest.entity.Order;

public interface OrderDao extends CommonDao<Order>{
	public int findUserProcessingOrderCount(int userId);
	public List<Order>  findPagedOrdersByState(Integer userId,Integer state,Integer start,Integer count);
	public List<Order> findOneDayOrdersByOrderState(Integer shopId,Date date ,Integer orderState);
	public List<Order> findShopOrderByOrderState(Integer shopId,Integer orderState);
	public List<Order> findAllSucessShopOrder(Integer shopId);
	public List<Order> findPagedShopOrderByOrderState(Integer shopId,Integer orderState,Integer start,Integer count);
	public List<Order> findSuccessShopOrder(Integer shopId,Date start,Date end);
	public List<Order> findOneDaySucessOrders(Integer shopId,Date date );
	public Date findShopFirstSuccessOrderDate(Integer shopId);
	public long countShopSucessOrderAfterDate(Integer shopId,Date date);
}
