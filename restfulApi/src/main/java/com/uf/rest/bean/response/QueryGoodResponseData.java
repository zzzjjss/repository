package com.uf.rest.bean.response;

import java.util.List;

public class QueryGoodResponseData {
	private Integer count;
	private Integer cursor_next;
	private List<ResponseQueryGood> goods;
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
	public List<ResponseQueryGood> getGoods() {
		return goods;
	}
	public void setGoods(List<ResponseQueryGood> goods) {
		this.goods = goods;
	}
	
}
