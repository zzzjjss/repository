package com.uf.fanfan.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uf.fanfan.dao.OrderDetailDao;
import com.uf.fanfan.entity.OrderDetail;
@Component("orderDetailDao")
public class OrderDetailDaoImpl  extends CommonDaoImpl<OrderDetail> implements OrderDetailDao{

	@Override
	public List<OrderDetail> findByProductid(Integer productid) {
		
		return null;
	}

}
