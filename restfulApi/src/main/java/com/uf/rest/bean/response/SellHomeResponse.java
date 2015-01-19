package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class SellHomeResponse {
	private boolean success;
	private ResponseError error;
	private SellHomeResponseData data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ResponseError getError() {
		return error;
	}
	public void setError(ResponseError error) {
		this.error = error;
	}
	public SellHomeResponseData getData() {
		return data;
	}
	public void setData(SellHomeResponseData data) {
		this.data = data;
	}
	
}
