package com.uf.stock.bean;



public class UpDownPower implements Comparable<UpDownPower> {
  private Float powerValue;
  private String symbol;

  public Float getPowerValue() {
    return powerValue;
  }

  public void setPowerValue(Float powerValue) {
    this.powerValue = powerValue;
  }


  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public int compareTo(UpDownPower o) {
    return o.getPowerValue().compareTo(powerValue);
  }

  @Override
  public String toString() {
    return symbol+ ":" + powerValue.toString();
  }
}
