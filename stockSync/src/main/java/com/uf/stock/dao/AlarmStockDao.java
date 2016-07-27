package com.uf.stock.dao;

import com.uf.stock.bean.AlarmStock;

public interface AlarmStockDao  extends CommonDao<AlarmStock>{
  public AlarmStock  findByStockCode(Integer stockCode);
}
