package com.uf.stock.dao;

import java.util.List;

import com.uf.stock.bean.Stock;

public interface StockDao extends CommonDao<Stock>{
	public List<Stock> findStockByCode(Integer code);
	public Stock findMaxCodeStockByType(String stockType);
}
