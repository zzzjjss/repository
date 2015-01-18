package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class QueryGoodResponse {
	private boolean success;
	private ResponseError error;
	private QueryGoodResponseData data;
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
	public QueryGoodResponseData getData() {
		return data;
	}
	public void setData(QueryGoodResponseData data) {
		this.data = data;
	}
	
}
