package com.uf.rest.bean.response;

import java.util.List;

public class QueryGoodsByClassResponseData {
	private Integer count;
	private Integer cursor_next;
	private List<ResponseQueryByClassGoods> class_goods;
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
	public List<ResponseQueryByClassGoods> getClass_goods() {
		return class_goods;
	}
	public void setClass_goods(List<ResponseQueryByClassGoods> class_goods) {
		this.class_goods = class_goods;
	}
	
}
