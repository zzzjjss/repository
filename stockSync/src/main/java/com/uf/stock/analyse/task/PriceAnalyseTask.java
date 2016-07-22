package com.uf.stock.analyse.task;

import java.util.concurrent.Callable;

import com.uf.stock.bean.Stock;
import com.uf.stock.bean.StockTradeInfo;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockTradeInfoDao;

public class PriceAnalyseTask implements Callable<Boolean>{
	private Stock stock;
	private float percent;
	private int days;
	public PriceAnalyseTask(Stock stock ,float percent,int days){
		this.stock=stock;
		this.percent=percent;
		this.days=days;
	}
	public Boolean call() throws Exception {
		StockTradeInfoDao dao=DaoFactory.getDao(StockTradeInfoDao.class);
		StockTradeInfo hightest=dao.findHighestClosePrice(stock,days);
		StockTradeInfo latest=dao.findLatestDateStockTradeInfo(stock);
		StockTradeInfo lowest=dao.findLoweestClosePrice(stock);
		if(lowest.getClosePrice()>=latest.getClosePrice()){
		  System.out.println(stock.getCode()+":"+stock.getName());
		}
		
//		if(hightest!=null&&latest!=null&&latest.getTradeDate().getTime()!=hightest.getTradeDate().getTime()&&(latest.getClosePrice()/hightest.getClosePrice())<=percent){
//			System.out.println(stock.getCode()+":"+stock.getName());
//		}
		return null;
	}

}
