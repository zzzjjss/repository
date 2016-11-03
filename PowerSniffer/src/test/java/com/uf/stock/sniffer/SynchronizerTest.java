package com.uf.stock.sniffer;

import java.util.Arrays;

import org.junit.Test;

import com.uf.stock.service.DataSyncService;
import com.uf.stock.sniffer.BS_PowerSniffer;
import com.uf.stock.util.SpringBeanFactory;

public class SynchronizerTest {

  @Test
  public void test() {
    //DataSyncService service=SpringBeanFactory.getBean(DataSyncService.class);
    
   // service.syncAllStocksBaseInfo();
    //syn.syncStocksCurrentTradeInfo(Arrays.asList("603005"));
    //List<StockInfo> result=syn.syncAllStocksInfo();
    //System.out.println(result.size());
    BS_PowerSniffer sniffer=SpringBeanFactory.getBean(BS_PowerSniffer.class);
    sniffer.startSniff();
  }

}
