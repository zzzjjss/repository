package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class GetGoodsResponse {
	private boolean success;
	private ResponseError error;
	private GetGoodsResponseData data;
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
	public GetGoodsResponseData getData() {
		return data;
	}
	public void setData(GetGoodsResponseData data) {
		this.data = data;
	}
	
	
}
