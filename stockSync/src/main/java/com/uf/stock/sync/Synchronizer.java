package com.uf.stock.sync;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.uf.stock.bean.StockTradeInfo;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockDao;
import com.uf.stock.dao.StockTradeInfoDao;
import com.uf.stock.util.HttpUnit;

public class Synchronizer {
	private Logger logger = LogManager.getLogger(Synchronizer.class);

	public void syncAllStockInfo() {
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
		ExecutorService pool = Executors.newCachedThreadPool();
		try {
			StockDao dao=DaoFactory.getDao(StockDao.class);
			Stock maxStock=dao.findMaxCodeStockByType(Stock.STOCK_TYPE_SHANG_HAI);
			int begin=600000;
			if(maxStock!=null){
			  begin=maxStock.getCode();
			}
			for(int i=begin;i<605000;i++){
				List<Stock> stock=dao.findStockByCode(i);
				if(stock==null||stock.size()==0){
					pool.submit(new GetStockTask("sh"+i,Stock.STOCK_TYPE_SHANG_HAI));
				}
			}
			
			maxStock=dao.findMaxCodeStockByType(Stock.STOCK_TYPE_SHEN_ZHEN);
			if(maxStock!=null){
              begin=maxStock.getCode();
            }else{
              begin=1;
            }
			for(int i=begin;i<=4750;i++){
			    DecimalFormat  format=new DecimalFormat("000000");
				String code=format.format(i);
				List<Stock> stock=dao.findStockByCode(Integer.parseInt(code));
				if(stock==null||stock.size()==0){
					pool.submit(new GetStockTask("sz"+code,Stock.STOCK_TYPE_SHEN_ZHEN));
				}
				
			}
			
			maxStock=dao.findMaxCodeStockByType(Stock.STOCK_TYPE_CHUANG_YE);
            if(maxStock!=null){
              begin=maxStock.getCode();
            }else{
              begin=300000;
            }
			
			for(int i=begin;i<=301000;i++){
				List<Stock> stock=dao.findStockByCode(i);
				if(stock==null||stock.size()==0){
					pool.submit(new GetStockTask("sz"+i,Stock.STOCK_TYPE_CHUANG_YE));
				}
			}
			pool.shutdown();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		return true;
	}
	
	public void resyncStrockTradeInfo(Integer stockCode){
	  StockTradeInfoDao stockTradeDao = DaoFactory.getDao(StockTradeInfoDao.class);
	  StockDao dao = DaoFactory.getDao(StockDao.class);
	  stockTradeDao.deleteStockTradeInfoByCode(stockCode);
	  Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      List<Stock> stocks=dao.findStockByCode(stockCode);
      if(stocks!=null&&!stocks.isEmpty()){
        Stock stock=stocks.get(0);
        int currentYear = calendar.get(Calendar.YEAR);
        for (int year = 2014; year <= currentYear; year++) {
          for (int jidu = 1; jidu <= 4; jidu++) {
            try {
              new GetStockTradeInfoTask(stock, year, jidu).call();
            }catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      }
      
	}
	
  public void syncAllStockTradeInfo() {
    ExecutorService pool = Executors.newFixedThreadPool(30);
    StockDao dao = DaoFactory.getDao(StockDao.class);
    StockTradeInfoDao stockTradeDao = DaoFactory.getDao(StockTradeInfoDao.class);
    try {
      List<Stock> stocks = dao.findAll(Stock.class);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      int currentYear = calendar.get(Calendar.YEAR);
      for (Stock stock : stocks) {
        StockTradeInfo info = stockTradeDao.findLatestDateStockTradeInfo(stock);
        int trYear;
        if (info != null) {
          Date date = info.getTradeDate();
          calendar.setTime(date);
          trYear = calendar.get(Calendar.YEAR);
        } else {
          trYear = 2014;
        }
        for (int year = trYear; year <= currentYear; year++) {
          for (int jidu = 1; jidu <= 4; jidu++) {
            pool.submit(new GetStockTradeInfoTask(stock, year, jidu));
          }

        }

      }
      pool.shutdown();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }
	public static void main(String[] args) {
	  Synchronizer  syn=new Synchronizer();
	  syn.resyncStrockTradeInfo(520);
	  
    }
	
}
