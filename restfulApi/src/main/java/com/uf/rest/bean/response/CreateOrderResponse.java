package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class CreateOrderResponse {
	private boolean success;
	private ResponseError error;
	private CreateOrderResponseData data;
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
	public CreateOrderResponseData getData() {
		return data;
	}
	public void setData(CreateOrderResponseData data) {
		this.data = data;
	}
	
}
