package com.uf.rest.bean.response;

import java.util.List;

public class QueryBankCardResponseData {
	private Integer count;
	private Integer cursor_next;
	private List<ResponseBankCard> bankcard;
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
	public List<ResponseBankCard> getBankcard() {
		return bankcard;
	}
	public void setBankcard(List<ResponseBankCard> bankcard) {
		this.bankcard = bankcard;
	}
	
}
