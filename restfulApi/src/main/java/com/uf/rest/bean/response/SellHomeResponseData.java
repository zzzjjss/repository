package com.uf.rest.bean.response;

import java.util.List;

public class SellHomeResponseData {
	private Integer count;
	private String cursor_next;
	private List<ResponseIncome> income;
	private List<ResponseOrderCount> order;
	private List<ResponseVisitCount> visit;
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
	public List<ResponseIncome> getIncome() {
		return income;
	}
	public void setIncome(List<ResponseIncome> income) {
		this.income = income;
	}
	public List<ResponseOrderCount> getOrder() {
		return order;
	}
	public void setOrder(List<ResponseOrderCount> order) {
		this.order = order;
	}
	public List<ResponseVisitCount> getVisit() {
		return visit;
	}
	public void setVisit(List<ResponseVisitCount> visit) {
		this.visit = visit;
	}
	
}
