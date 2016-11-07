package com.uf.stock.sniffer;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.uf.stock.bean.UpDownPower;
import com.uf.stock.service.DataSyncService;
import com.uf.stock.util.SpringBeanFactory;

public class SynchronizerTest {

  @Test
  public void test() {
    DataSyncService service=SpringBeanFactory.getBean(DataSyncService.class);
    List<UpDownPower> powers=service.calculateAllStocksCurrentPower();
    Collections.sort(powers);
    System.out.println(powers);
   // service.syncAllStocksBaseInfo();
    //syn.syncStocksCurrentTradeInfo(Arrays.asList("603005"));
    //List<StockInfo> result=syn.syncAllStocksInfo();
    //System.out.println(result.size());
  }

}
