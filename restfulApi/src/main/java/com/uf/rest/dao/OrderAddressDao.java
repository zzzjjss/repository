package com.uf.rest.dao;

import java.util.List;

import com.uf.rest.entity.OrderAddress;

public interface OrderAddressDao extends CommonDao<OrderAddress>{
	public List<OrderAddress> findPagedUserOrderAddress(Integer userId,Integer stat,Integer count);
}
