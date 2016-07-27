package com.uf.stock.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.uf.stock.bean.AlarmStock;
import com.uf.stock.bean.StockTradeInfo;
import com.uf.stock.dao.AlarmStockDao;

public class AlarmStockDaoImpl extends CommonRdbsDaoImpl<AlarmStock> implements AlarmStockDao{
  public AlarmStock  findByStockCode(Integer stockCode){
    HibernateTemplate temp=this.getHibernateTemplate();
    temp.setMaxResults(1);
    List<AlarmStock> result=(List<AlarmStock>)temp.find("from AlarmStock s  where s.stockCode=? ", stockCode);
    if(result!=null&&result.size()>0){
        return result.get(0);
    }
    return null;
  }
}
