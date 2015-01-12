package com.uf.rest.dao;

import com.uf.rest.entity.OrderDetail;

public interface OrderDetailDao extends CommonDao<OrderDetail>{
	public void deleteByOrderId(Integer orderId);
}
