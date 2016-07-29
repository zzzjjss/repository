package com.uf.stock.alarm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.uf.stock.bean.AlarmStock;
import com.uf.stock.bean.BeforeFuQuanTradeInfo;
import com.uf.stock.bean.Stock;
import com.uf.stock.dao.AlarmStockDao;
import com.uf.stock.dao.BeforeFuQuanTradeInfoDao;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockDao;

public class MySchedule {
  private AlarmStockDao dao=DaoFactory.getDao(AlarmStockDao.class);
  private Siren alarm=DaoFactory.getBean(Siren.class);
	public void startSchedule(){
	  ExecutorService pool = Executors.newFixedThreadPool(4);
	  while(true){
	    try {
	      long begin=System.currentTimeMillis();
	      List<AlarmStock> allStocks= dao.findAll(AlarmStock.class);
	      List<AlarmStock> temp=null;
	      List<Future<List<String>>> futures=new ArrayList<Future<List<String>>>();
	      for(int i=0;i<allStocks.size();i++){
	        int subSize=10;
	        if(i%subSize==0){
	          temp=new ArrayList<AlarmStock>(); 
	        }
	        temp.add(allStocks.get(i));
	        if((i%subSize==(subSize-1))||i==(allStocks.size()-1)){
	          Future<List<String>> result=pool.submit(new GetStockCurrrentPriceJob(temp));
	          futures.add(result);
	        }
	      }
	      List<String>  alarms=new ArrayList<String>();
	      for(Future<List<String>>  f:futures){
	        List<String>  result=f.get();
	        if(result!=null&&result.size()>0){
	          alarms.addAll(result);
	        }
	      }
	      if(!alarms.isEmpty()){
	        alarm.alarm(alarms);
	      }
	      System.out.println((System.currentTimeMillis()-begin)/1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }
	  }
		
	}
	public  void countAlarmPrice(){
	  AlarmStockDao dao=DaoFactory.getDao(AlarmStockDao.class);
      StockDao stockDao=DaoFactory.getDao(StockDao.class);
      BeforeFuQuanTradeInfoDao  stockTradeDao=DaoFactory.getDao(BeforeFuQuanTradeInfoDao.class);
      List<Stock> stocks=stockDao.findAll(Stock.class);
      for(Stock s:stocks){
        try{
          BeforeFuQuanTradeInfo trade=stockTradeDao.findLoweestClosePrice(s);
          BeforeFuQuanTradeInfo latestTrade= stockTradeDao.findLatestDateStockTradeInfo(s);
            AlarmStock  alarm=dao.findByStockCode(s.getCode());
            if(alarm==null){
              alarm=new AlarmStock();
            }
            alarm.setStockCode(s.getCode());
            alarm.setStockName(s.getName());
            alarm.setAlarmBuyPrice(trade.getClosePrice());
            alarm.setDownPercent((latestTrade.getClosePrice()-trade.getClosePrice())/latestTrade.getClosePrice());
            dao.saveOrUpdate(alarm);
        }catch(Exception e){
          System.out.println(s.getCode()+" exception");
          e.printStackTrace();
        }
      }
	}
	
	public static void main(String[] args) {
	  MySchedule sche=new MySchedule();
	  sche.startSchedule();
    }
}
