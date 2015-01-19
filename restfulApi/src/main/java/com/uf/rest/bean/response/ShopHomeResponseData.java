package com.uf.rest.bean.response;

public class ShopHomeResponseData {
	private Integer shop_id;
	private Float income;
	private Integer good_count;
	private Integer order_count;
	private Float business;
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Float getIncome() {
		return income;
	}
	public void setIncome(Float income) {
		this.income = income;
	}
	public Integer getGood_count() {
		return good_count;
	}
	public void setGood_count(Integer good_count) {
		this.good_count = good_count;
	}
	public Integer getOrder_count() {
		return order_count;
	}
	public void setOrder_count(Integer order_count) {
		this.order_count = order_count;
	}
	public Float getBusiness() {
		return business;
	}
	public void setBusiness(Float business) {
		this.business = business;
	}
	
}
