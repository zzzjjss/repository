package com.uf.rest.dao;

import java.util.Date;
import java.util.List;

import com.uf.rest.entity.ShopVisitRecord;

public interface ShopVisitRecordDao extends CommonDao<ShopVisitRecord>{
	public Date findShopFirstVisitDate(Integer shopId);
	public long countShopVisitAfterDate(Integer shopId,Date date);
}
