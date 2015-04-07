package com.uf.stock.analyse.task;

import java.util.concurrent.Callable;

import com.uf.stock.bean.Stock;
import com.uf.stock.bean.StockTradeInfo;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockTradeInfoDao;

public class PriceAnalyseTask implements Callable<Boolean>{
	private Stock stock;
	private float percent;
	public PriceAnalyseTask(Stock stock ,float percent){
		this.stock=stock;
		this.percent=percent;
	}
	public Boolean call() throws Exception {
		StockTradeInfoDao dao=DaoFactory.getDao(StockTradeInfoDao.class);
		StockTradeInfo hightest=dao.findHighestClosePrice(stock);
		StockTradeInfo latest=dao.findLatestDateStockTradeInfo(stock);
		if(hightest!=null&&latest!=null&&(latest.getClosePrice()/hightest.getClosePrice())<=percent){
			System.out.println(stock.getCode()+":"+stock.getName());
		}
		return null;
	}

}
