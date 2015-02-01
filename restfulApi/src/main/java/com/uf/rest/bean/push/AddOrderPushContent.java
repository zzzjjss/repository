package com.uf.rest.bean.push;

import java.util.List;

import com.uf.rest.bean.response.OrderResponseGood;
import com.uf.rest.bean.response.ResponseUser;

public class AddOrderPushContent {
	private Integer id;
	private String time;
	private Integer payment;
	private ResponseUser user;
	private String state;
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
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public ResponseUser getUser() {
		return user;
	}
	public void setUser(ResponseUser user) {
		this.user = user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<OrderResponseGood> getGood() {
		return good;
	}
	public void setGood(List<OrderResponseGood> good) {
		this.good = good;
	}
	
}
