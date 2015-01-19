package com.uf.rest.bean.response;

import java.util.List;

public class ResponseOrderToShopUser {
	private Integer id;
	private String time;
	private List<ResponseOrderState> state;
	private String payment;
	private Integer pick_address_id;
	private Integer deliver_address_id;
	private Boolean paid;
	private ResponseUser user;
	private List<OrderResponseGood> good;
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
	public List<ResponseOrderState> getState() {
		return state;
	}
	public void setState(List<ResponseOrderState> state) {
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
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	public ResponseUser getUser() {
		return user;
	}
	public void setUser(ResponseUser user) {
		this.user = user;
	}
	public List<OrderResponseGood> getGood() {
		return good;
	}
	public void setGood(List<OrderResponseGood> good) {
		this.good = good;
	}
	
}
