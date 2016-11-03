package com.uf.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.data.dao.StockInfoDao;
import com.uf.stock.data.sync.StockDataSynchronizer;
import com.uf.stock.service.DataSyncService;
@Service("dataSyncService")
public class DataSyncServiceImpl implements DataSyncService{
  @Autowired
  private StockInfoDao stockInfoDao;
  @Autowired
  private StockDataSynchronizer dataSyncher;

  public int syncAllStocksBaseInfo() {
    List<StockInfo> stockInfo=dataSyncher.syncAllStocksInfo();
    stockInfoDao.deleteAll();
    for(StockInfo info:stockInfo){
      stockInfoDao.insert(info);
    }
    return stockInfo.size();
  }

}
