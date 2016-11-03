package com.uf.stock.data.sync;

import java.util.List;
import java.util.Map;

import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.data.bean.StockTradeInfo;

public interface StockDataSynchronizer {
  public List<StockInfo> syncAllStocksInfo();
  public StockInfo syncStockInfo(String stockCode);  
  public Map<String,StockInfo> syncStocksInfo(List<String> stockCodes);
  public StockTradeInfo syncStockCurrentTradeInfo(String stockSymbol);
  public Map<String,StockTradeInfo> syncStocksCurrentTradeInfo(List<String> stockSymbol);
}
