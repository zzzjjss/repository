package com.uf.stock.restful.bean;

import java.util.List;

public class LowPriceBuyStrategyResponse {
  private boolean success;
  private ResponseError error;
  private List<LowPriceBuyStrategyResponseData> data;
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
  public List<LowPriceBuyStrategyResponseData> getData() {
    return data;
  }
  public void setData(List<LowPriceBuyStrategyResponseData> data) {
    this.data = data;
  }
  
}
