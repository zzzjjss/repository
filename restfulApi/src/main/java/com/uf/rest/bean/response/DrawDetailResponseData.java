package com.uf.rest.bean.response;

import java.util.List;

public class DrawDetailResponseData {
	private List<ResponseDrawDetail> withdraw;
	private Integer count;
	private Integer cursor_next;
	public List<ResponseDrawDetail> getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(List<ResponseDrawDetail> withdraw) {
		this.withdraw = withdraw;
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
