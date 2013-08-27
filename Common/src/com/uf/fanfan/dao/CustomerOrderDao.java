package com.uf.fanfan.dao;

import java.util.Date;
import java.util.List;

import com.uf.fanfan.entity.CustomerOrder;

public interface CustomerOrderDao {
	//@Query("select t from CustomerOrder t where t.arriveTime>:begin and t.arriveTime<:end and t.customerid=:cusId")
	public List<CustomerOrder> findCustomerOrdersBetweenArriveTime( Date begin, Date end, int customerId);
}
