package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class SellIncomeResponse {
	private boolean success;
	private ResponseError error;
	private SellIncomeResponseData data;
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
	public SellIncomeResponseData getData() {
		return data;
	}
	public void setData(SellIncomeResponseData data) {
		this.data = data;
	}
	
}
