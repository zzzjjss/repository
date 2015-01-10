package com.uf.rest.bean.response;

import java.util.List;

public class CustomHomeResponseData {
	private List<ResponseShop> shop;
	private Integer count;
	private Integer next_cursor;
	
	public List<ResponseShop> getShop() {
		return shop;
	}
	public void setShop(List<ResponseShop> shop) {
		this.shop = shop;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getNext_cursor() {
		return next_cursor;
	}
	public void setNext_cursor(Integer next_cursor) {
		this.next_cursor = next_cursor;
	}
	
	
	
}
