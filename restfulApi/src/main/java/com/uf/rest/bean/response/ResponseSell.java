package com.uf.rest.bean.response;


public class ResponseSell {
	private ResponseGood good;
	private Integer count;
	public ResponseGood getGood() {
		return good;
	}
	public void setGood(ResponseGood good) {
		this.good = good;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
