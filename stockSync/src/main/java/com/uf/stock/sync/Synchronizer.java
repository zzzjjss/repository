package com.uf.stock.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uf.stock.bean.Stock;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockDao;
import com.uf.stock.util.HttpUnit;

public class Synchronizer {
	private Logger logger = LogManager.getLogger(Synchronizer.class);

	public void syncStockInfo() {
		CloseableHttpClient client = HttpUnit.createHttpClient();
		ExecutorService pool = Executors.newFixedThreadPool(50);
		Future<List<String>> result=pool.submit(new GetStockCodesTask(client));
		List<String> stockCodes=null;
		try {
			stockCodes = result.get();
			if(stockCodes!=null){
				for(String code:stockCodes){
					pool.submit(new GetStockInfoTask(code,client));
				}
			}
			pool.shutdown();
			boolean completed = false;
			while (!completed) {
				try {
					completed = pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.HOURS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
	}
	
	public boolean synStock(){
		ExecutorService pool = Executors.newFixedThreadPool(1);
		try {
			Future<Boolean> future=pool.submit(new GetStockTask());
			return future.get();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		return false;
	}
	
	public void syncStockTradeInfo(){
		ExecutorService pool = Executors.newFixedThreadPool(20);
		StockDao dao=DaoFactory.getDao(StockDao.class);
		try {
			List<Stock> stocks=dao.findAll(Stock.class);
			for(Stock stock:stocks){
				for(int year=2014;year<=2015;year++){
					for(int jidu=1;jidu<=4;jidu++){
						pool.submit(new GetStockTradeInfoTask(stock, year, jidu));
					}
					
				}
				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
	}

}
