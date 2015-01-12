package com.uf.rest.bean.response;

import java.util.List;

public class ResponseOrder {
	private Integer id;
	private String time;
	private String state;
	private String payment;
	private Integer pick_address_id;
	private Integer deliver_address_id;
	private ResponseShop shop;
	private List<ResponseGood> good;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
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
	public ResponseShop getShop() {
		return shop;
	}
	public void setShop(ResponseShop shop) {
		this.shop = shop;
	}
	public List<ResponseGood> getGood() {
		return good;
	}
	public void setGood(List<ResponseGood> good) {
		this.good = good;
	}
	
}
