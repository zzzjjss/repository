package com.uf.stock.bean;

public class AlarmStock {
	private Integer id;
	private String stockCode;
	private Float alarmPrice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public Float getAlarmPrice() {
		return alarmPrice;
	}
	public void setAlarmPrice(Float alarmPrice) {
		this.alarmPrice = alarmPrice;
	}
	
}
