package com.uf.stock.sniffer.data.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Component;

import com.uf.stock.sniffer.data.bean.StockInfo;
import com.uf.stock.sniffer.data.dao.StockInfoDao;


@Component("stockInfoDao")
public class StockInfoDaoImpl extends CommonRdbsDaoImpl<StockInfo> implements StockInfoDao {
  

  public StockInfo findStockBySymbol(String stockSymbol) {
    List<StockInfo> result=(List<StockInfo>) this.getHibernateTemplate().find("from StockInfo s  where s.symbol=?", stockSymbol);
    if(result!=null&&result.size()>0){
      return result.get(0);
    }
    return null;
  }

  public Integer deleteAll() {
    return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

      public Integer doInHibernate(Session session) throws HibernateException {
        Query query=session.createQuery("delete from StockInfo");
        return query.executeUpdate();
      }
    });
  }


}
