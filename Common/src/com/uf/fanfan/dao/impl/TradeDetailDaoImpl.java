package com.uf.fanfan.dao.impl;

import java.util.List;

import com.uf.fanfan.dao.TradeDetailDao;
import com.uf.fanfan.entity.OrderDetail;
import com.uf.fanfan.entity.TradeDetail;

public class TradeDetailDaoImpl  extends CommonDaoImpl<TradeDetail> implements TradeDetailDao{

	@Override
	public List<OrderDetail> findByProductid(Integer productid) {
		
		return null;
	}

}
