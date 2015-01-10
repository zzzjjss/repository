package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class GetShopUserInfoResponse {
	private boolean success;
	private ResponseError error;
	private GetShopUserInfoResponseData data;
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
	public GetShopUserInfoResponseData getData() {
		return data;
	}
	public void setData(GetShopUserInfoResponseData data) {
		this.data = data;
	}
	
}
