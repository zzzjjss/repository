package com.uf.fanfan.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.WeekEnum;
import com.uf.fanfan.dao.CustomerOrderDao;
import com.uf.fanfan.dao.TradeDetailDao;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.OrderDetail;
import com.uf.fanfan.service.TradeService;
import com.uf.fanfan.util.DateUtil;

@Service("tradeService")
public class TradeServiceImpl implements TradeService{
	@Autowired
	private TradeDetailDao tradeDetailDao;
	private CustomerOrderDao orderDao;
	@Override
	public void purchaseProducts(List<CustomerOrder> orders) {
		if(orders!=null){
			for(CustomerOrder order:orders){
				orderDao.save(order);
			}
		}
	}
//
//	@Override
//	public Map<WeekEnum, List<CustomerOrder>> getCustomerThisWeekOrders(
//			int customerId) {
//		Map<WeekEnum, List<CustomerOrder>> weekDetail=new HashMap<WeekEnum, List<CustomerOrder>>();
//		weekDetail.put(WeekEnum.SUNDAY, new ArrayList<CustomerOrder>());
//		weekDetail.put(WeekEnum.MONDAY, new ArrayList<CustomerOrder>());
//		weekDetail.put(WeekEnum.TUESDAY, new ArrayList<CustomerOrder>());
//		weekDetail.put(WeekEnum.WEDNESDAY, new ArrayList<CustomerOrder>());
//		weekDetail.put(WeekEnum.THURSDAY, new ArrayList<CustomerOrder>());
//		weekDetail.put(WeekEnum.FRIDAY, new ArrayList<CustomerOrder>());
//		weekDetail.put(WeekEnum.SATURDAY, new ArrayList<CustomerOrder>());
//		List<CustomerOrder> orders=orderRes.findBetweenArriveTime(DateUtil.getThisWeekSundayDate(), DateUtil.getThisWeekSaturdayDate(),customerId);
//		if(orders!=null&&orders.size()>0){
//			for(CustomerOrder order:orders){
//				WeekEnum dayOfWeek=DateUtil.getWeekdayByDate(order.getArrivetime());
//				List<CustomerOrder> dayOrders=weekDetail.get(dayOfWeek);
//				dayOrders.add(order);
//			}
//		}
//		return weekDetail;
//	}
//
//	@Override
//	public void cancelOrder(BigInteger tradeDetailId) {
//		tradeDetailRes.delete(tradeDetailId);
//		
//	}
//
//	@Override
//	public void updateOrder(OrderDetail td) {
//		tradeDetailRes.saveAndFlush(td);
//		
//	}
//	public List<CustomerOrder> getCustomerOrdersByArriveDay(Integer customerId,Timestamp arriveTime){
//		return null;
//	}
	@Override
	public void deletePurchasedProduct(int orderDetailId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modifyPurchasedProductNum(int orderDetailId, int number) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cancelOrder(int orderId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<WeekEnum, CustomerOrder> getCustomerThisWeekOrders(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CustomerOrder getCustomerOrderByArriveDay(Integer customerId,
			Timestamp arriveTime) {
		// TODO Auto-generated method stub
		return null;
	}
}

