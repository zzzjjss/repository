package com.uf.stock.dao;

import java.util.List;

import com.uf.stock.bean.Stock;
import com.uf.stock.bean.StockTradeInfo;


public interface StockTradeInfoDao extends CommonDao<StockTradeInfo>{
	public StockTradeInfo findHighestClosePrice(Stock stock,int days);
	public StockTradeInfo  findLatestDateStockTradeInfo(Stock stock);
}
