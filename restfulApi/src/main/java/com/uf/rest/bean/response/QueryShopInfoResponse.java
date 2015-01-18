package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class QueryShopInfoResponse {
	private boolean success;
	private ResponseError error;
	private QueryShopInfoResponseData data;
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
	public QueryShopInfoResponseData getData() {
		return data;
	}
	public void setData(QueryShopInfoResponseData data) {
		this.data = data;
	}
	
	
}
