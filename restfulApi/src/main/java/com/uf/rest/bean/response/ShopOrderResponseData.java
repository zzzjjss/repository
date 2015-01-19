package com.uf.rest.bean.response;

import java.util.List;

public class ShopOrderResponseData {
	private Integer count;
	private Integer cursor_next;
	private List<ResponseOrderToShopUser> order;
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
	public List<ResponseOrderToShopUser> getOrder() {
		return order;
	}
	public void setOrder(List<ResponseOrderToShopUser> order) {
		this.order = order;
	}
	
}
