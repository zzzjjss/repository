package com.uf.stock.sniffer;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.service.DataSyncService;
import com.uf.stock.service.StockAnalysisService;
import com.uf.stock.service.bean.StableStage;
import com.uf.stock.service.bean.StockStage;
import com.uf.stock.util.SpringBeanFactory;

public class AnalysisServiceTest {

  @Test
  public void test() {
    DataSyncService dataService=SpringBeanFactory.getBean(DataSyncService.class);
    StockAnalysisService analyseService=SpringBeanFactory.getBean(StockAnalysisService.class);
    List<StockInfo> stocks=dataService.findStocksPeRatioBetween(-1f, Float.MAX_VALUE);
    for(StockInfo  stock:stocks){
      StockStage stage=analyseService.analyseCurrentStockStage(stock, 30);
      if(stage instanceof StableStage){
        System.out.println(stock.getSymbol());
      }
    }
//    StockInfo stock=new StockInfo();
//    stock.setCode(55);
//    stock.setSymbol("sz000055");
//    analyseService.analyseCurrentStockStage(stock, 30);
  }

}
