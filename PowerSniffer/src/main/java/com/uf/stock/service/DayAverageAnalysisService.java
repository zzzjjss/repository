package com.uf.stock.service;

import com.uf.stock.data.bean.StockInfo;

public interface DayAverageAnalysisService {
	public StockInfo calculateStockIsDayAverageGoldX(StockInfo stock,int shortTerm,int mediumTerm ,int longTerm); 
}
