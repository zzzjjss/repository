package com.uf.rest.dao;

import java.util.List;

import com.uf.rest.entity.ShopWithDrawRecord;

public interface ShopWithDrawRecordDao extends CommonDao<ShopWithDrawRecord> {
	public List<ShopWithDrawRecord> findPagedWithdraw(Integer shopId,Integer start,Integer count);
}
