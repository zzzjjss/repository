package com.uf.fanfan.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.uf.fanfan.common.WeekEnum;
import com.uf.fanfan.entity.CustomerOrder;

public interface TradeService {
	/**预定购买一天或多天的快餐。
	 * 同一天定的多份快餐合并到一个订单中，
	 * 几天一起提交购买时，放到一个事务中处理。
	 * @param orders
	 */
	public void purchaseProducts(CustomerOrder orders);
	/**
	 * 删除某天订单中预定的某个菜
	 * @param orderDetailId
	 */
	public void deletePurchasedProduct(int orderDetailId);
	/**
	 *  修改预定快餐的份数
	 * @param orderDetailId
	 * @param number   (must greater 0)
	 */
	public void modifyPurchasedProductNum(int orderDetailId,int number);
	/**
	 * 取消某天的订单
	 * @param tradeDetailId
	 */
	public void cancelOrder(int orderId);
	
	/**
	 * 根据客户ID获取客户本星期的订餐记录
	 * @param customerId
	 * @return
	 */
	public Map<WeekEnum,CustomerOrder> getCustomerThisWeekOrders(int customerId);
	
	/**
	 * 获取客户某天的订单
	 * @param customerId
	 * @param arriveTime
	 * @return
	 */
	public List<CustomerOrder> getCustomerOnedayOrders(Integer customerId,Timestamp arriveTime);
}
