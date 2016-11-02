package com.uf.stock.sniffer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.stock.sniffer.data.bean.StockInfo;
import com.uf.stock.sniffer.data.dao.StockInfoDao;
import com.uf.stock.sniffer.data.sync.StockDataSynchronizer;
import com.uf.stock.sniffer.service.DataSyncService;
@Service("dataSyncService")
public class DataSyncServiceImpl implements DataSyncService{
  @Autowired
  private StockInfoDao stockInfoDao;
  @Autowired
  private StockDataSynchronizer dataSyncher;

  public void syncAllStocksBaseInfo() {
    List<StockInfo> stockInfo=dataSyncher.syncAllStocksInfo();
    stockInfoDao.deleteAll();
    for(StockInfo info:stockInfo){
      stockInfoDao.insert(info);
    }
  }

}
