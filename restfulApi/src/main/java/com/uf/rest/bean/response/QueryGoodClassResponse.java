package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class QueryGoodClassResponse {
	private boolean success;
	private ResponseError error;
	private QueryGoodClassResponseData data;
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
	public QueryGoodClassResponseData getData() {
		return data;
	}
	public void setData(QueryGoodClassResponseData data) {
		this.data = data;
	}
	
}
