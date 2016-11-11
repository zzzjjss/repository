package com.uf.stock.sniffer;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.uf.stock.bean.UpDownPower;
import com.uf.stock.data.bean.AlarmStock;
import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.service.DataSyncService;
import com.uf.stock.util.SpringBeanFactory;
import com.uf.stock.util.StockUtil;

public class SynchronizerTest {

  @Test
  public void test() {
     float minPeRatio=0f,maxPeRation=100;
     float maxDownPercent=30;
    
    DataSyncService service=SpringBeanFactory.getBean(DataSyncService.class);
    List<StockInfo> stocks=service.findStocksPeRatioBetween(minPeRatio,maxPeRation);
    stocks=service.findStocksPeRatioBetween(minPeRatio,maxPeRation);
    
    List<UpDownPower> powers=service.calculateStocksCurrentPower(stocks);
    Collections.sort(powers);
    for(UpDownPower power:powers){
      
      AlarmStock alarm=service.findAlarmStockInfoByStockCode(StockUtil.parseStockSymbolToStockCode(power.getTradeInfo().getStockSymbol()));
      float  downPercent=((power.getTradeInfo().getClosePrice()-alarm.getAlarmBuyPrice())/power.getTradeInfo().getClosePrice())*100;
      if(power.getTradeInfo().getUpDownRate()<1||downPercent>maxDownPercent){
        continue;
      }
        
      System.out.println(power.toString()+":  "+downPercent+"%");
    }
   // service.syncAllStocksBaseInfo();
    //syn.syncStocksCurrentTradeInfo(Arrays.asList("603005"));
    //List<StockInfo> result=syn.syncAllStocksInfo();
    //System.out.println(result.size());
  }

}
