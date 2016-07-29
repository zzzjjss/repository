package com.uf.stock.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.uf.stock.bean.BeforeFuQuanTradeInfo;
import com.uf.stock.bean.Stock;
import com.uf.stock.bean.StockTradeInfo;
import com.uf.stock.dao.BeforeFuQuanTradeInfoDao;
import com.uf.stock.dao.StockTradeInfoDao;

public class BeforeFuQuanTradeInfoDaoImpl  extends CommonRdbsDaoImpl<BeforeFuQuanTradeInfo> implements BeforeFuQuanTradeInfoDao{
	public BeforeFuQuanTradeInfo findHighestClosePrice(Stock stock,int days){
		HibernateTemplate temp=this.getHibernateTemplate();
		temp.setMaxResults(days);
		List<BeforeFuQuanTradeInfo> result=(List<BeforeFuQuanTradeInfo>)temp.find("from BeforeFuQuanTradeInfo s  where s.stock.code=? order by s.tradeDate desc  ", stock.getCode());
		if(result!=null&&result.size()>0){
		  BeforeFuQuanTradeInfo highest=result.get(0);
			for(BeforeFuQuanTradeInfo trade:result){
				if(trade.getClosePrice()>highest.getClosePrice()){
					highest=trade;
				}
			}
			return highest;
		}
		return null;
	}
	public BeforeFuQuanTradeInfo findLoweestClosePrice(Stock stock){
	  HibernateTemplate temp=this.getHibernateTemplate();
	  temp.setMaxResults(1);
      List<BeforeFuQuanTradeInfo> result=(List<BeforeFuQuanTradeInfo>)temp.find("from BeforeFuQuanTradeInfo s  where s.stock.code=? order by s.closePrice asc  ", stock.getCode());
      if(result!=null&&result.size()>0){
          return result.get(0);
      }
      return null;
	}
	public BeforeFuQuanTradeInfo  findLatestDateStockTradeInfo(Stock stock){
		HibernateTemplate temp=this.getHibernateTemplate();
		temp.setMaxResults(1);
		List<BeforeFuQuanTradeInfo> result=(List<BeforeFuQuanTradeInfo>)temp.find("from BeforeFuQuanTradeInfo s  where s.stock.code=? order by s.tradeDate desc ", stock.getCode());
		if(result!=null&&result.size()>0){
			return result.get(0);
		}
		return null;
	}
	public BeforeFuQuanTradeInfo findByTradeDate(Integer stockCode,Date tradeDate){
	  HibernateTemplate temp=this.getHibernateTemplate();
      temp.setMaxResults(1);
      List<BeforeFuQuanTradeInfo> result=(List<BeforeFuQuanTradeInfo>)temp.find("from BeforeFuQuanTradeInfo s  where s.stock.code=? and s.tradeDate=? ", stockCode,tradeDate);
      if(result!=null&&result.size()>0){
          return result.get(0);
      }
      return null;
	}
	public int deleteStockBeforeTradeInfoByCode(Integer stockCode){
	  String hql="delete BeforeFuQuanTradeInfo s where s.stock.code=:code ";
      Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Query q = session.createQuery(hql);
      q.setInteger("code", stockCode);
      return q.executeUpdate();
	}
}
