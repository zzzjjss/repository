package com.uf.fanfan.dao;

import java.util.List;

import com.uf.fanfan.entity.OrderDetail;

public interface TradeDetailDao {
	//@Query( " select t from OrderDetail t where t.productid = :id " )
	public  List<OrderDetail>  findByProductid(Integer productid);
}
