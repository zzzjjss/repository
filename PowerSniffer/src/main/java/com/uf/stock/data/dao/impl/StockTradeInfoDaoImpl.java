package com.uf.stock.data.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.uf.stock.data.bean.StockTradeInfo;
import com.uf.stock.data.dao.StockTradeInfoDao;

@Component("stockTradeInfoDao")
public class StockTradeInfoDaoImpl extends CommonRdbsDaoImpl<StockTradeInfo> implements StockTradeInfoDao {

  @Override
  public StockTradeInfo findLatestDateStockTradeInfo(String stockSymbol) {
      HibernateTemplate temp=this.getHibernateTemplate();
      temp.setMaxResults(1);
      List<StockTradeInfo> result=(List<StockTradeInfo>)temp.find("from StockTradeInfo s  where s.stockSymbol=? order by s.tradeDate desc ", stockSymbol);
      if(result!=null&&result.size()>0){
          return result.get(0);
      }
      return null;
  }

}
