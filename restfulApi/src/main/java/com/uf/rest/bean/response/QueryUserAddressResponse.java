package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class QueryUserAddressResponse {
	private boolean success;
	private ResponseError error;
	private QueryUserAddressResponseData data;
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
	public QueryUserAddressResponseData getData() {
		return data;
	}
	public void setData(QueryUserAddressResponseData data) {
		this.data = data;
	}
	
}
