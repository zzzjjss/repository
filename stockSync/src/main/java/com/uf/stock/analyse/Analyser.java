package com.uf.stock.analyse;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.uf.stock.analyse.task.PriceAnalyseTask;
import com.uf.stock.bean.Stock;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockDao;

public class Analyser {
	public void highestAnalyse(){
		ExecutorService pool = Executors.newFixedThreadPool(30);
		StockDao dao=DaoFactory.getDao(StockDao.class);
		try {
			List<Stock> stocks=dao.findAll(Stock.class);
			for(Stock stock:stocks){
				pool.submit(new PriceAnalyseTask(stock, 0.8f,30));
				
			}
			pool.shutdown();
			pool.awaitTermination(30, TimeUnit.DAYS);
			System.out.println("analyse over");
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
	}
	public static void main(String[] args) {
	  Analyser an=new Analyser();
	  an.highestAnalyse();
    }
}
