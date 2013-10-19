package com.uf.fanfan.dao;

import java.util.List;

import com.uf.fanfan.entity.OrderDetail;
import com.uf.fanfan.entity.TradeDetail;

public interface TradeDetailDao extends CommonDao<TradeDetail>{
	
	public  List<OrderDetail>  findByProductid(Integer productid);
}
