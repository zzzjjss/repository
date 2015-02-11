package com.uf.rest.bean.response;

import java.util.List;

public class QueryOrderResponseData {
	private List<ResponseOrder> order;
	private Integer count;
	private Integer cursor_next;
	public List<ResponseOrder> getOrder() {
		return order;
	}

	public void setOrder(List<ResponseOrder> order) {
		this.order = order;
	}

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
	
}
