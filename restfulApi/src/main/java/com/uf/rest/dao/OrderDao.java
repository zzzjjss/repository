package com.uf.rest.dao;

import java.util.List;

import com.uf.rest.entity.Order;

public interface OrderDao extends CommonDao<Order>{
	public int findUserProcessingOrderCount(int userId);
	public List<Order>  findPagedOrdersByState(Integer userId,Integer state,Integer start,Integer count);
}
