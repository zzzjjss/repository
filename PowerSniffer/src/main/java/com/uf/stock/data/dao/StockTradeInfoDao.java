package com.uf.stock.data.dao;

import java.util.Date;

import com.uf.stock.data.bean.StockTradeInfo;

public interface StockTradeInfoDao extends CommonDao<StockTradeInfo>{
  public StockTradeInfo  findLatestDateStockTradeInfo(String stockSymbol);
  public Float calculateAveragePriceBeforeDate(int limit,Date date,Integer stockCode);
}
