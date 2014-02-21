package com.uf.fanfan.dao;

import java.util.List;

import com.uf.fanfan.entity.OrderDetail;

public interface OrderDetailDao extends CommonDao<OrderDetail>{
	
	public  List<OrderDetail>  findByProductid(Integer productid);
}
