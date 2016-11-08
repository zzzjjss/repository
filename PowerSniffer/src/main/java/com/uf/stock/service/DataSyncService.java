package com.uf.stock.service;

import java.util.List;

import com.uf.stock.bean.UpDownPower;
import com.uf.stock.data.bean.AlarmStock;
import com.uf.stock.data.bean.StockInfo;

public interface DataSyncService {
    public int syncAllStocksBaseInfo();
    public List<StockInfo>  findStocksPeRatioBetween(Float min,Float max);
    public AlarmStock findAlarmStockInfoByStockCode(Integer  stockCode);
    public List<UpDownPower> calculateStocksCurrentPower(List<StockInfo> stocks);
}
