package com.uf.stock.alarm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	  Calendar ca=Calendar.getInstance();
	  boolean firstTime=true;
	  while(true){
	    try {
	      boolean updateStock=false;
	      ca.setTime(new Date());
          int house=ca.get(Calendar.HOUR_OF_DAY);
          int minute=ca.get(Calendar.MINUTE);
          if(house>=15&&minute<=5){
            updateStock=true;
          }
          if(firstTime){
            updateStock=true;
            firstTime=false;
          }
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
	          Future<List<String>> result=pool.submit(new GetStockCurrrentPriceJob(temp,updateStock));
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
