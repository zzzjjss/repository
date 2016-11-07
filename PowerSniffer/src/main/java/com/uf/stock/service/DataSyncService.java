package com.uf.stock.service;

import java.util.List;

import com.uf.stock.bean.UpDownPower;

public interface DataSyncService {
    public int syncAllStocksBaseInfo();
    public List<UpDownPower> calculateAllStocksCurrentPower();
}
