package com.uf.rest.bean.request;

import java.util.List;

public class CreateOrderRequest {
	private String token;
	private Integer p;
	private Integer shop_id;
	private Integer pick_address_id;
	private Integer deliver_address_id;
	private Integer payment;
	private List<RequestGood> good;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getP() {
		return p;
	}
	public void setP(Integer p) {
		this.p = p;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getPick_address_id() {
		return pick_address_id;
	}
	public void setPick_address_id(Integer pick_address_id) {
		this.pick_address_id = pick_address_id;
	}
	public Integer getDeliver_address_id() {
		return deliver_address_id;
	}
	public void setDeliver_address_id(Integer deliver_address_id) {
		this.deliver_address_id = deliver_address_id;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public List<RequestGood> getGood() {
		return good;
	}
	public void setGood(List<RequestGood> good) {
		this.good = good;
	}
	
}
