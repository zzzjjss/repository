package com.uf.rest.dao;

import java.util.List;

import com.uf.rest.entity.Shop;

public interface ShopDao extends CommonDao<Shop>{
	public List<Shop> findLimitByHql(String hql,int fetchSize,Object... values);
	public List<Shop>  findNearShops(int start, int count,Double longitude,Double latitude);
	public List<Shop>  findShops(int start, int count);
}
