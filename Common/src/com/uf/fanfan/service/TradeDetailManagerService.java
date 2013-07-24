package com.uf.fanfan.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.uf.fanfan.common.WeekEnum;
import com.uf.fanfan.entity.TradeDetail;

public interface TradeDetailManagerService {
	public void purchaseProducts(List<TradeDetail> tradeDetail);
	public Map<WeekEnum,List<TradeDetail>> getCustomerThisWeekTradedetail(int customerId);
	public void cancelOrder(BigInteger tradeDetailId);
	public void updateOrder(TradeDetail td);
}
