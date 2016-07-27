package com.uf.stock.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.uf.stock.bean.Stock;
import com.uf.stock.dao.StockDao;

public class StockDaoImpl extends CommonRdbsDaoImpl<Stock> implements StockDao {
  public List<Stock> findStockByCode(Integer code) {
    return (List<Stock>) this.getHibernateTemplate().find("from Stock s  where s.code=?", code);
  }

  public Stock findMaxCodeStockByType(String stockType) {
    String hql = "from Stock s where s.type=:type order by s.code desc";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setString("type", stockType);
    query.setFirstResult(0);
    query.setMaxResults(1);
    List result = query.list();
    if (result == null || result.isEmpty()) {
      return null;
    }
    return (Stock) result.get(0);

  }
}
