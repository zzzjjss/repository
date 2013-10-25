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
import com.uf.fanfan.dao.OrderDetailDao;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.OrderDetail;
import com.uf.fanfan.service.TradeService;
import com.uf.fanfan.util.DateUtil;

@Service("tradeService")
public class TradeServiceImpl implements TradeService{
	@Autowired
	private OrderDetailDao orderDetailDao;
	@Autowired
	private CustomerOrderDao customerOrderDao;
	@Override
	public void purchaseProducts(List<CustomerOrder> orders) {
		if(orders!=null){
			for(CustomerOrder order:orders){
				customerOrderDao.save(order);
			}
		}
	}


	@Override
	public void deletePurchasedProduct(int orderDetailId) {
		OrderDetail detail=new OrderDetail();
		detail.setId(orderDetailId);
		orderDetailDao.delete(detail);
	}
	@Override
	public void modifyPurchasedProductNum(int orderDetailId, int number) {
		OrderDetail detail=orderDetailDao.loadById(OrderDetail.class, orderDetailId);
		detail.setTradeAmount(number);
		orderDetailDao.update(detail);
	}
	@Override
	public void cancelOrder(int orderId) {
		CustomerOrder order=new CustomerOrder();
		order.setId(orderId);
		customerOrderDao.delete(order);
	}
	@Override
	public Map<WeekEnum, CustomerOrder> getCustomerThisWeekOrders(int customerId) {
		Map<WeekEnum, CustomerOrder> weekDetail=new HashMap<WeekEnum,CustomerOrder>();
		List<CustomerOrder> orders=customerOrderDao.findCustomerOrdersBetweenArriveTime(DateUtil.getThisWeekSundayDate(), DateUtil.getThisWeekSaturdayDate(),customerId);
		if(orders!=null&&orders.size()>0){
			for(CustomerOrder order:orders){
				WeekEnum dayOfWeek=DateUtil.getWeekdayByDate(order.getArrivetime());
				weekDetail.put(dayOfWeek, order);
			}
		}
		return weekDetail;
	}
	@Override
	public CustomerOrder getCustomerOrderByArriveTime(Integer customerId,
			Timestamp arriveTime) {
		return customerOrderDao.getCustomerOrderByArriveTime(customerId, arriveTime);
	}
}

