package com.uf.rest.bean.response;

import java.util.List;

public class SellOrderDealReponseData {
	private Integer count;
	private String cursor_next;
	private List<ResponseOrderCount> order;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCursor_next() {
		return cursor_next;
	}
	public void setCursor_next(String cursor_next) {
		this.cursor_next = cursor_next;
	}
	public List<ResponseOrderCount> getOrder() {
		return order;
	}
	public void setOrder(List<ResponseOrderCount> order) {
		this.order = order;
	}
	
}
