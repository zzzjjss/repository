package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class QueryGoodsSellResponse {
	private boolean success;
	private ResponseError error;
	private QueryGoodsSellResponseData data;
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
	public QueryGoodsSellResponseData getData() {
		return data;
	}
	public void setData(QueryGoodsSellResponseData data) {
		this.data = data;
	}
	
}
