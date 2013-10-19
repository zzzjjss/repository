package com.uf.fanfan.dao;

import java.util.Date;
import java.util.List;

import com.uf.fanfan.entity.CustomerOrder;

public interface CustomerOrderDao extends CommonDao<CustomerOrder>{
	
	public List<CustomerOrder> findCustomerOrdersBetweenArriveTime( Date begin, Date end, int customerId);
	/**
	 * 查找订单，订单中包含该商品，且订单还没被商家确认。
	 * @param productid
	 * @return
	 */
	public  List<CustomerOrder>  findInProcessingOrderByProductid(Integer productid);
}
