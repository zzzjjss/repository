package com.uf.stock.restful.bean;

import java.util.List;

public class GoUpStrategyResponse {
  private boolean success;
  private ResponseError error;
  private List<GoUpStrategyResponseData> data;
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
  public List<GoUpStrategyResponseData> getData() {
    return data;
  }
  public void setData(List<GoUpStrategyResponseData> data) {
    this.data = data;
  }
  
  
}
