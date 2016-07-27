package com.uf.stock.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.uf.stock.bean.Stock;
import com.uf.stock.bean.StockTradeInfo;
import com.uf.stock.dao.StockTradeInfoDao;

public class StockTradeInfoDaoImpl  extends CommonRdbsDaoImpl<StockTradeInfo> implements StockTradeInfoDao{
	public StockTradeInfo findHighestClosePrice(Stock stock,int days){
		HibernateTemplate temp=this.getHibernateTemplate();
		temp.setMaxResults(days);
		List<StockTradeInfo> result=(List<StockTradeInfo>)temp.find("from StockTradeInfo s  where s.stock.code=? order by s.tradeDate desc  ", stock.getCode());
		if(result!=null&&result.size()>0){
			StockTradeInfo highest=result.get(0);
			for(StockTradeInfo trade:result){
				if(trade.getClosePrice()>highest.getClosePrice()){
					highest=trade;
				}
			}
			return highest;
		}
		return null;
	}
	public StockTradeInfo findLoweestClosePrice(Stock stock){
	  HibernateTemplate temp=this.getHibernateTemplate();
	  temp.setMaxResults(1);
      List<StockTradeInfo> result=(List<StockTradeInfo>)temp.find("from StockTradeInfo s  where s.stock.code=? order by s.closePrice asc  ", stock.getCode());
      if(result!=null&&result.size()>0){
          return result.get(0);
      }
      return null;
	}
	public StockTradeInfo  findLatestDateStockTradeInfo(Stock stock){
		HibernateTemplate temp=this.getHibernateTemplate();
		temp.setMaxResults(1);
		List<StockTradeInfo> result=(List<StockTradeInfo>)temp.find("from StockTradeInfo s  where s.stock.code=? order by s.tradeDate desc ", stock.getCode());
		if(result!=null&&result.size()>0){
			return result.get(0);
		}
		return null;
	}
	public StockTradeInfo findByTradeDate(Integer stockCode,Date tradeDate){
	  HibernateTemplate temp=this.getHibernateTemplate();
      temp.setMaxResults(1);
      List<StockTradeInfo> result=(List<StockTradeInfo>)temp.find("from StockTradeInfo s  where s.stock.code=? and s.tradeDate=? ", stockCode,tradeDate);
      if(result!=null&&result.size()>0){
          return result.get(0);
      }
      return null;
	}
	public List<StockTradeInfo> findOrderedByStock(Stock stock){
	  HibernateTemplate temp=this.getHibernateTemplate();
      return (List<StockTradeInfo>)temp.find("from StockTradeInfo s  where s.stock.code=? order by s.tradeDate asc", stock.getCode());
	}
}
