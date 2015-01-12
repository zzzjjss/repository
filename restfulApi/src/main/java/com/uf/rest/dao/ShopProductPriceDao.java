package com.uf.rest.dao;

import java.util.List;

import com.uf.rest.entity.ShopProductPrice;

public interface ShopProductPriceDao extends CommonDao<ShopProductPrice>{
		public List<ShopProductPrice> findShopProductPriceByGoodIds(List<Integer> goodIds,Integer shopId);
}
