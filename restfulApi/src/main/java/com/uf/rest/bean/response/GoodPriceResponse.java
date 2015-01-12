package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class GoodPriceResponse {
	private boolean success;
	private ResponseError error;
	private GoodPriceResponseData data;
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
	public GoodPriceResponseData getData() {
		return data;
	}
	public void setData(GoodPriceResponseData data) {
		this.data = data;
	}
	
}
