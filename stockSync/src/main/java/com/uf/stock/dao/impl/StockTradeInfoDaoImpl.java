package com.uf.stock.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.uf.stock.bean.Stock;
import com.uf.stock.bean.StockTradeInfo;
import com.uf.stock.dao.StockTradeInfoDao;

public class StockTradeInfoDaoImpl  extends CommonRdbsDaoImpl<StockTradeInfo> implements StockTradeInfoDao{
	public StockTradeInfo findHighestClosePrice(Stock stock){
		HibernateTemplate temp=this.getHibernateTemplate();
		temp.setMaxResults(1);
		List<StockTradeInfo> result=(List<StockTradeInfo>)temp.find("from StockTradeInfo s  where s.stock.id=? order by s.closePrice desc ", stock.getId());
		if(result!=null&&result.size()>0){
			return result.get(0);
		}
		return null;
	}
	public StockTradeInfo  findLatestDateStockTradeInfo(Stock stock){
		HibernateTemplate temp=this.getHibernateTemplate();
		temp.setMaxResults(1);
		List<StockTradeInfo> result=(List<StockTradeInfo>)temp.find("from StockTradeInfo s  where s.stock.id=? order by s.tradeDate desc ", stock.getId());
		if(result!=null&&result.size()>0){
			return result.get(0);
		}
		return null;
	}
}
