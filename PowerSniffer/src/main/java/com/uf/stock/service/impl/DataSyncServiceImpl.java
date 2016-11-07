package com.uf.stock.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.uf.stock.bean.UpDownPower;
import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.data.bean.StockTradeInfo;
import com.uf.stock.data.dao.StockInfoDao;
import com.uf.stock.data.sync.StockDataSynchronizer;
import com.uf.stock.service.DataSyncService;

@Service("dataSyncService")
public class DataSyncServiceImpl implements DataSyncService {
  @Autowired
  private StockInfoDao stockInfoDao;
  @Autowired
  private StockDataSynchronizer dataSyncher;

  public int syncAllStocksBaseInfo() {
    List<StockInfo> stockInfo = dataSyncher.syncAllStocksInfo();
    stockInfoDao.deleteAll();
    for (StockInfo info : stockInfo) {
      stockInfoDao.insert(info);
    }
    return stockInfo.size();
  }

  public List<UpDownPower> calculateAllStocksCurrentPower() {
    List<UpDownPower> result = new LinkedList<UpDownPower>();
    List<StockInfo> allStocks = stockInfoDao.findAll(StockInfo.class);
    Map<String, StockInfo> cache = new HashMap<String, StockInfo>();
    for (StockInfo stock : allStocks) {
      cache.put(stock.getSymbol(), stock);
    }

    Map<String, StockTradeInfo> allStockTradeInfos = new HashMap<String, StockTradeInfo>();
    List<Future<Map<String, StockTradeInfo>>> futures = new ArrayList<Future<Map<String, StockTradeInfo>>>();
    ExecutorService pool = Executors.newCachedThreadPool();
    List<String> symbols = Lists.newArrayList(cache.keySet());
    int begin = 0, syncStockNum = 20;
    while (begin <= symbols.size()) {
      int end = begin + syncStockNum;
      if (end > symbols.size()) {
        end = symbols.size();
      }
      Future<Map<String, StockTradeInfo>> syncResult = pool.submit(new SyncStockTradeInfoTask(symbols.subList(begin, end)));
      futures.add(syncResult);

      begin = begin + syncStockNum;
    }
    pool.shutdown();
    for (Future<Map<String, StockTradeInfo>> future : futures) {
      Map<String, StockTradeInfo> tradeInfos;
      try {
        tradeInfos = future.get();
        allStockTradeInfos.putAll(tradeInfos);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    List<UpDownPower> upPowerList = new ArrayList<UpDownPower>();
    for (String key : allStockTradeInfos.keySet()) {
      StockTradeInfo tradeInfo = allStockTradeInfos.get(key);
      StockInfo stock = cache.get(key);
      if (tradeInfo.getTradeAmount() == null) {
        continue;
      }
      tradeInfo.setTurnoverRate(((float) (tradeInfo.getTradeAmount() / stock.getTotalAAmount())) * 100);
      float power =0f;
      if(tradeInfo.getTurnoverRate()!=0){
        power=tradeInfo.getUpDownRate() / tradeInfo.getTurnoverRate();
      }
      UpDownPower powerObj = new UpDownPower();
      powerObj.setPowerValue(power);
      powerObj.setSymbol(stock.getName());
      upPowerList.add(powerObj);
      result.add(powerObj);
    }
    return result;
  }

  class SyncStockTradeInfoTask implements Callable<Map<String, StockTradeInfo>> {
    private List<String> stockSymbol;

    public SyncStockTradeInfoTask(List<String> stockSymbol) {
      this.stockSymbol = stockSymbol;
    }

    @Override
    public Map<String, StockTradeInfo> call() throws Exception {
      return dataSyncher.syncStocksCurrentTradeInfo(stockSymbol);
    }
  }
}
