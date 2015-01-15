package com.uf.rest.bean.response;

import java.util.List;

public class QueryUserAddressResponseData {
	private Integer count;
	private Integer cursor_next;
	private List<ResponseAddress> address;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCursor_next() {
		return cursor_next;
	}
	public void setCursor_next(Integer cursor_next) {
		this.cursor_next = cursor_next;
	}
	public List<ResponseAddress> getAddress() {
		return address;
	}
	public void setAddress(List<ResponseAddress> address) {
		this.address = address;
	}
	
}
