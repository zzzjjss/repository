package com.uf.stock.dao;

import java.util.Date;

import com.uf.stock.bean.BeforeFuQuanTradeInfo;
import com.uf.stock.bean.Stock;
import com.uf.stock.bean.StockTradeInfo;


public interface BeforeFuQuanTradeInfoDao extends CommonDao<BeforeFuQuanTradeInfo>{
	public BeforeFuQuanTradeInfo findHighestClosePrice(Stock stock,int days);
	public BeforeFuQuanTradeInfo  findLatestDateStockTradeInfo(Stock stock);
	public BeforeFuQuanTradeInfo findLoweestClosePrice(Stock stock);
	public BeforeFuQuanTradeInfo findByTradeDate(Integer stockCode,Date tradeDate);
}
