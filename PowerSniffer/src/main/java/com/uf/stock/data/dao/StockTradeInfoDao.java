package com.uf.stock.data.dao;

import com.uf.stock.data.bean.StockTradeInfo;

public interface StockTradeInfoDao extends CommonDao<StockTradeInfo>{
  public StockTradeInfo  findLatestDateStockTradeInfo(String stockSymbol);
}
