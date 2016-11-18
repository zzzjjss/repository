package com.uf.stock.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.uf.stock.data.bean.AlarmStock;
import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.data.bean.StockTradeInfo;
import com.uf.stock.data.dao.AlarmStockDao;
import com.uf.stock.data.dao.StockInfoDao;
import com.uf.stock.data.dao.StockTradeInfoDao;
import com.uf.stock.data.exception.DataSyncException;
import com.uf.stock.data.sync.StockDataSynchronizer;
import com.uf.stock.service.DataSyncService;

@Service("dataSyncService")
public class DataSyncServiceImpl implements DataSyncService {
  @Autowired
  private StockInfoDao stockInfoDao;
  @Autowired
  private StockDataSynchronizer dataSyncher;
  @Autowired
  private AlarmStockDao alarmStockDao;
  @Autowired
  private StockTradeInfoDao tradeInfoDao;
  private Map<Integer, AlarmStock> alarmStockCache = new HashMap<Integer, AlarmStock>();

  public int syncAllStocksBaseInfo() {
    List<StockInfo> stockInfo = dataSyncher.syncAllStocksInfo();
    stockInfoDao.deleteAll();
    for (StockInfo info : stockInfo) {
      stockInfoDao.insert(info);
    }
    return stockInfo.size();
  }

  public List<UpDownPower> calculateStocksCurrentPower(List<StockInfo> stocks) {
    List<UpDownPower> result = new LinkedList<UpDownPower>();
    if (stocks == null || stocks.size() == 0)
      return result;
    Map<String, StockInfo> cache = new HashMap<String, StockInfo>();
    for (StockInfo stock : stocks) {
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
      float power = 0f;
      if (tradeInfo.getTurnoverRate() != 0) {
        power = tradeInfo.getUpDownRate() / tradeInfo.getTurnoverRate();
      }
      UpDownPower powerObj = new UpDownPower();
      powerObj.setPowerValue(power);
      powerObj.setStockPeRatio(stock.getPeRatio());
      powerObj.setStockName(stock.getName());
      powerObj.setTradeInfo(tradeInfo);
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

  @Override
  public List<StockInfo> findStocksPeRatioBetween(Float min, Float max) {
    return (List<StockInfo>) stockInfoDao.findByHql("from StockInfo  s where  s.peRatio<? and s.peRatio>?", max, min);
  }

  @Override
  public AlarmStock findAlarmStockInfoByStockCode(Integer stockCode) {
    AlarmStock alarm = alarmStockCache.get(stockCode);
    if (alarm == null) {
      alarm = alarmStockDao.findByStockCode(stockCode);
      if (alarm != null) {
        alarmStockCache.put(stockCode, alarm);
      }
    }
    if (alarm == null) {
      StockInfo stock = stockInfoDao.findStockByStockCode(stockCode);
      if (stock != null) {
        alarm = new AlarmStock();
        alarm.setStockCode(stockCode);
        alarmStockDao.insert(alarm);
      }
    }
    return alarm;
  }

  @Override
  public List<AlarmStock> findAllAlarmStocks() {
    return alarmStockDao.findAll(AlarmStock.class);
  }

  @Override
  public Map<String, StockTradeInfo> getCurrentStocksTradeInfo(List<String> stockSymbols) {
    Map<String, StockTradeInfo> allStockTradeInfos = new HashMap<String, StockTradeInfo>();
    if (stockSymbols == null || stockSymbols.size() == 0)
      return allStockTradeInfos;

    List<Future<Map<String, StockTradeInfo>>> futures = new ArrayList<Future<Map<String, StockTradeInfo>>>();
    ExecutorService pool = Executors.newCachedThreadPool();
    int begin = 0, syncStockNum = 20;
    while (begin <= stockSymbols.size()) {
      int end = begin + syncStockNum;
      if (end > stockSymbols.size()) {
        end = stockSymbols.size();
      }
      Future<Map<String, StockTradeInfo>> syncResult = pool.submit(new SyncStockTradeInfoTask(stockSymbols.subList(begin, end)));
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
    return allStockTradeInfos;
  }

  @Override
  public int syncStockTradeInfos(String stockSymbol) {
    int totalRecord = 0;
    try {
      StockTradeInfo latestTi = tradeInfoDao.findLatestDateStockTradeInfo(stockSymbol);
      DateFormat format = new SimpleDateFormat("yyyyMMdd");
      Date startDate = null;
      Date today = new Date();
      if (latestTi == null) {
        try {
          startDate = format.parse("20130101");
        } catch (ParseException e) {
          e.printStackTrace();
        }
      } else {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(latestTi.getTradeDate());
        calendar.add(Calendar.DATE, 1);
        startDate = calendar.getTime();
      }
      Calendar calendar = Calendar.getInstance();
      while (true) {
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 1);
        Date temp = calendar.getTime();
        if (temp.getTime() >= today.getTime()) {
          temp = today;
        }
        List<StockTradeInfo> tradeInfos;
        tradeInfos = dataSyncher.syncStockDateTradeInfos(stockSymbol, startDate, temp);

        if (tradeInfos != null && tradeInfos.size() > 0) {
          for (StockTradeInfo info : tradeInfos) {
            tradeInfoDao.insert(info);
            totalRecord++;
          }
        }
        if (temp.getTime() == today.getTime()) {
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
    return totalRecord;
  }
}
