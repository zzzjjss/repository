package com.uf.rest.bean.request;


public class GoodPriceRequest {
	private String token;
	private Integer p;
	private Integer[] good;
	private Integer count;
	private Integer start;
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
	public Integer[] getGood() {
		return good;
	}
	public void setGood(Integer[] good) {
		this.good = good;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	
}
