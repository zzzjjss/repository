package com.uf.stock.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.data.bean.StockTradeInfo;
import com.uf.stock.data.dao.StockInfoDao;
import com.uf.stock.data.dao.StockTradeInfoDao;
import com.uf.stock.service.DayAverageAnalysisService;

@Service
public class DayAverageAnalysisServiceImpl implements DayAverageAnalysisService {
	@Autowired
	private StockTradeInfoDao tradeInfoDao;
	@Autowired
	private StockInfoDao stockInfoDao;
	public StockInfo calculateStockIsDayAverageGoldX(StockInfo stock,int shortTerm,int mediumTerm ,int longTerm){
		Calendar calen=Calendar.getInstance();
		StockTradeInfo latestInfo=tradeInfoDao.findLatestDateStockTradeInfo(stock.getSymbol());
		if(latestInfo!=null){
			calen.setTime(latestInfo.getTradeDate());
			calen.add(Calendar.DATE, -1);
			Date before=calen.getTime();
			Float beforeShortAvg=tradeInfoDao.calculateAveragePriceBeforeDate(shortTerm, before, stock.getCode());
			System.out.println(beforeShortAvg);
			Float shortAvg=tradeInfoDao.calculateAveragePriceBeforeDate(shortTerm,latestInfo.getTradeDate() , stock.getCode());
			Float beforeMedAvg=tradeInfoDao.calculateAveragePriceBeforeDate(mediumTerm, before, stock.getCode());
			Float medAvg=tradeInfoDao.calculateAveragePriceBeforeDate(mediumTerm,latestInfo.getTradeDate() , stock.getCode());
			Float beforeLongAvg=tradeInfoDao.calculateAveragePriceBeforeDate(longTerm, before, stock.getCode());
			Float longAvg=tradeInfoDao.calculateAveragePriceBeforeDate(longTerm,latestInfo.getTradeDate() , stock.getCode());
			if(shortAvg>medAvg&&beforeShortAvg<=beforeMedAvg){
				return stock;
			}
		}
		return null;
	}
	

}
