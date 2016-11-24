package com.uf.stock.service;

import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.service.bean.StockStage;

public interface StockAnalysisService {
	public StockInfo calculateStockIsDayAverageGoldX(StockInfo stock,int shortTerm,int mediumTerm ,int longTerm); 
	public StockStage analyseCurrentStockStage(StockInfo stock,int days);
}
