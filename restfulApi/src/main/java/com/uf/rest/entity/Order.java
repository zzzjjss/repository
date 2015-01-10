package com.uf.rest.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
	private Integer id;
	private Shop shop;
	private User user;
	private Date createTime;
	private OrderAddress pickAddress;
	private OrderAddress deliverAddress;
	private Set<OrderDetail> orderDetails=new HashSet<OrderDetail>();
	private Integer paymentType;
	private Integer orderState;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public OrderAddress getPickAddress() {
		return pickAddress;
	}
	public void setPickAddress(OrderAddress pickAddress) {
		this.pickAddress = pickAddress;
	}
	public OrderAddress getDeliverAddress() {
		return deliverAddress;
	}
	public void setDeliverAddress(OrderAddress deliverAddress) {
		this.deliverAddress = deliverAddress;
	}
	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	
	
}
