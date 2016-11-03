package com.uf.stock.sniffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.data.bean.StockTradeInfo;
import com.uf.stock.data.dao.StockInfoDao;
import com.uf.stock.data.sync.StockDataSynchronizer;

public class BS_PowerSniffer {
  @Autowired
  private StockInfoDao stockInfoDao;
  @Autowired
  private StockDataSynchronizer  synchronizer;
  
  public void startSniff(){
    List<StockInfo> allStocks=stockInfoDao.findAll(StockInfo.class);
    Map<String,StockInfo> cache=new HashMap<String, StockInfo>();
    for(StockInfo stock:allStocks){
      cache.put(stock.getSymbol(), stock);
    }
   
    while(true){
      Map<String,StockTradeInfo> allStockTradeInfos=new HashMap<String, StockTradeInfo>();
      
      ExecutorService pool = Executors.newFixedThreadPool(4);
      List<String> symbols=Lists.newArrayList(cache.keySet());
      int begin=0,syncStockNum=20;
      while(begin<=symbols.size()){
        int end=begin+syncStockNum;
        if(end>symbols.size()){
          end=symbols.size();
        }
        Map<String,StockTradeInfo> tradeInfos=synchronizer.syncStocksCurrentTradeInfo(symbols.subList(begin,end));
        allStockTradeInfos.putAll(tradeInfos);
        begin=begin+syncStockNum;
      }
      List<Power> upPowerList=new ArrayList<Power>();
      for(String key:allStockTradeInfos.keySet()){
        StockTradeInfo tradeInfo=allStockTradeInfos.get(key);
        StockInfo stock=cache.get(key);
        if(tradeInfo.getTradeAmount()==null){
          continue;
        }
        tradeInfo.setTurnoverRate(((float)(tradeInfo.getTradeAmount()/stock.getTotalAAmount()))*100);
        float power=tradeInfo.getUpDownRate()/tradeInfo.getTurnoverRate();
        if(power>0&&stock.getPeRatio()<40&&stock.getPeRatio()>0&&tradeInfo.getUpDownRate()>1){
          Power powerObj=new Power();
          powerObj.setPowerValue(power);
          powerObj.setSymbol(stock.getName());
          upPowerList.add(powerObj);
        }
      }
      Collections.sort(upPowerList);
      System.out.println(upPowerList);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
  }
  class Power implements Comparable<Power>{
      private Float powerValue;
      private String symbol;
      
    public Float getPowerValue() {
        return powerValue;
      }

      public void setPowerValue(Float powerValue) {
        this.powerValue = powerValue;
      }

      public String getSymbol() {
        return symbol;
      }

      public void setSymbol(String symbol) {
        this.symbol = symbol;
      }

    @Override
    public int compareTo(Power o) {
      return o.getPowerValue().compareTo(powerValue);
    }
    @Override
    public String toString(){
      return  symbol+":"+powerValue.toString();
    }
  }
}
