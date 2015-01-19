package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class SellOrderDealReponse {
	private boolean success;
	private ResponseError error;
	private SellOrderDealReponseData data;
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
	public SellOrderDealReponseData getData() {
		return data;
	}
	public void setData(SellOrderDealReponseData data) {
		this.data = data;
	}
	
}
