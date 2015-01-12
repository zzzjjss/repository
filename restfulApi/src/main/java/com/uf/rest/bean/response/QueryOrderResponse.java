package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class QueryOrderResponse {
	private boolean success;
	private ResponseError error;
	private QueryOrderResponseData data;
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
	public QueryOrderResponseData getData() {
		return data;
	}
	public void setData(QueryOrderResponseData data) {
		this.data = data;
	}
	
}
