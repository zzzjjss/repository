package com.uf.fanfan.dao.impl;

import java.util.Date;
import java.util.List;

import com.uf.fanfan.dao.CustomerOrderDao;
import com.uf.fanfan.entity.CustomerOrder;

public class CustomerOrderDaoImpl  extends CommonDaoImpl<CustomerOrder> implements CustomerOrderDao{

	@Override
	public List<CustomerOrder> findCustomerOrdersBetweenArriveTime(Date begin,
			Date end, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerOrder> findInProcessingOrderByProductid(
			Integer productid) {
		// TODO Auto-generated method stub
		return null;
	}

}
