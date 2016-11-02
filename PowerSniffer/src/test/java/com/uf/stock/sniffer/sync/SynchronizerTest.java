package com.uf.stock.sniffer.sync;

import java.util.Arrays;

import org.junit.Test;

import com.uf.stock.sniffer.service.DataSyncService;
import com.uf.stock.sniffer.util.SpringBeanFactory;

public class SynchronizerTest {

  @Test
  public void test() {
    DataSyncService service=SpringBeanFactory.getBean(DataSyncService.class);
    service.syncAllStocksBaseInfo();
    //syn.syncStocksCurrentTradeInfo(Arrays.asList("603005"));
    //List<StockInfo> result=syn.syncAllStocksInfo();
    //System.out.println(result.size());
  }

}
