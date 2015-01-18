package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class QueryGoodsByClassResponse {
	private boolean success;
	private ResponseError error;
	private QueryGoodsByClassResponseData data;
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
	public QueryGoodsByClassResponseData getData() {
		return data;
	}
	public void setData(QueryGoodsByClassResponseData data) {
		this.data = data;
	}
	
}
