package com.uf.rest.bean.push;

import java.util.List;

import com.uf.rest.bean.response.OrderResponseGood;
import com.uf.rest.bean.response.ResponseOrderState;
import com.uf.rest.bean.response.ResponseShop;

public class ChangeOrderStatePushContent {
	private Integer id;
	private String time;
	private List<ResponseOrderState> state;
	private Integer payment;
	private Integer pick_address_id;
	private Integer deliver_address_id;
	private ResponseShop shop;
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
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
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
	public List<OrderResponseGood> getGood() {
		return good;
	}
	public void setGood(List<OrderResponseGood> good) {
		this.good = good;
	}
	
	
}
