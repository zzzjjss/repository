package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class SellOrderResponse {
	private boolean success;
	private ResponseError error;
	private SellOrderResponseData data;
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
	public SellOrderResponseData getData() {
		return data;
	}
	public void setData(SellOrderResponseData data) {
		this.data = data;
	}
	
}
