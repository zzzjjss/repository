package com.uf.stock.dao.impl;

import java.util.List;

import com.uf.stock.bean.Stock;
import com.uf.stock.dao.StockDao;

public class StockDaoImpl extends CommonRdbsDaoImpl<Stock> implements StockDao{
	public List<Stock> findStockByCode(String code){
		return (List<Stock>)this.getHibernateTemplate().find("from Stock s  where s.code=?", code);
	}
}
