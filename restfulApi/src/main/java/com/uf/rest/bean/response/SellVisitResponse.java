package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class SellVisitResponse {
	private boolean success;
	private ResponseError error;
	private SellVisitResponseData data;
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
	public SellVisitResponseData getData() {
		return data;
	}
	public void setData(SellVisitResponseData data) {
		this.data = data;
	}
	
}
