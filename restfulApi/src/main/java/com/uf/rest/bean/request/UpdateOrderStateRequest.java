package com.uf.rest.bean.request;

public class UpdateOrderStateRequest {
	private String token;
	private Integer p;
	private Integer[] order_id;
	private Integer state;
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
	public Integer[] getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer[] order_id) {
		this.order_id = order_id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
