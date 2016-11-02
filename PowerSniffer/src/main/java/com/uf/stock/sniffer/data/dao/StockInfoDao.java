package com.uf.stock.sniffer.data.dao;

import com.uf.stock.sniffer.data.bean.StockInfo;


public interface StockInfoDao extends CommonDao<StockInfo>{
	public StockInfo findStockBySymbol(String stockSymbol);
	public Integer deleteAll();
}
