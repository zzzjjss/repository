package com.uf.stock.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.uf.stock.bean.BeforeFuQuanTradeInfo;
import com.uf.stock.bean.Stock;
import com.uf.stock.bean.StockTradeInfo;
import com.uf.stock.dao.BeforeFuQuanTradeInfoDao;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockDao;
import com.uf.stock.dao.StockTradeInfoDao;

public class CountBeforeFuQuanTask  implements Callable<String>{
  private Stock stock;
  public CountBeforeFuQuanTask(Stock stock){
    this.stock=stock;
  }
  
  public String call() throws Exception {
    StockTradeInfoDao dao=DaoFactory.getDao(StockTradeInfoDao.class);
    BeforeFuQuanTradeInfoDao fuquanDao=DaoFactory.getDao(BeforeFuQuanTradeInfoDao.class);
    List<StockTradeInfo> tradeInfos=dao.findOrderedByStock(stock);
    List<BeforeFuQuanTradeInfo> beforeTradeInfoe=new ArrayList<BeforeFuQuanTradeInfo>();
    if(tradeInfos!=null){
      float before=0.0f;
      for(StockTradeInfo info:tradeInfos){
          if(before==0.0){
            before=info.getClosePrice();
          }
          float percent=(info.getOpenPrice()-before)/before;
          if(percent>0.12||percent<-0.12){
            recountPrice(beforeTradeInfoe,percent);
          }
          BeforeFuQuanTradeInfo temp=new BeforeFuQuanTradeInfo();
          temp.setClosePrice(info.getClosePrice());
          temp.setHighestPrice(info.getHighestPrice());
          temp.setLowestPrice(info.getLowestPrice());
          temp.setOpenPrice(info.getOpenPrice());
          temp.setStock(info.getStock());
          temp.setTradeAmount(info.getTradeAmount());
          temp.setTradeDate(info.getTradeDate());
          temp.setTradeMoney(info.getTradeMoney());
          beforeTradeInfoe.add(temp);
          before=info.getClosePrice();
      }
      for(BeforeFuQuanTradeInfo beforInfo:beforeTradeInfoe ){
        fuquanDao.saveOrUpdate(beforInfo);
      }
    }
    
    return null;
  }
  private void recountPrice(List<BeforeFuQuanTradeInfo> beforeTradeInfo ,float percent){
     for(BeforeFuQuanTradeInfo trade: beforeTradeInfo){
       trade.setOpenPrice(trade.getOpenPrice()+(percent*trade.getOpenPrice()));
       trade.setClosePrice(trade.getClosePrice()+(percent*trade.getClosePrice()));
       trade.setHighestPrice(trade.getHighestPrice()+(percent*trade.getHighestPrice()));
       trade.setLowestPrice(trade.getLowestPrice()+(percent*trade.getLowestPrice()));
     } 
  }
  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(50);
    StockDao dao=DaoFactory.getDao(StockDao.class);
    
    List<Stock> stocks=dao.findAll(Stock.class);
    for(Stock s:stocks){
      CountBeforeFuQuanTask  task=new CountBeforeFuQuanTask(s);
      pool.submit(task);
    }
    pool.shutdown();
    
  }
}
