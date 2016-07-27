package com.uf.stock.bean;

public class AlarmStock {
  private Integer id;
  private Integer stockCode;
  private String stockName;
  private Float alarmBuyPrice;
  private Float alarmSellPrice;
  private Float downPercent;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getStockCode() {
    return stockCode;
  }

  public void setStockCode(Integer stockCode) {
    this.stockCode = stockCode;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }



  public Float getAlarmBuyPrice() {
    return alarmBuyPrice;
  }

  public void setAlarmBuyPrice(Float alarmBuyPrice) {
    this.alarmBuyPrice = alarmBuyPrice;
  }

  public Float getAlarmSellPrice() {
    return alarmSellPrice;
  }

  public void setAlarmSellPrice(Float alarmSellPrice) {
    this.alarmSellPrice = alarmSellPrice;
  }

  public Float getDownPercent() {
    return downPercent;
  }

  public void setDownPercent(Float downPercent) {
    this.downPercent = downPercent;
  }

}
