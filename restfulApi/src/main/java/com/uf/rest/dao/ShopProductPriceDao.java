package com.uf.rest.dao;

import java.util.List;

import com.uf.rest.entity.ShopProductPrice;

public interface ShopProductPriceDao extends CommonDao<ShopProductPrice>{
		public List<ShopProductPrice> findShopProductPriceByGoodIds(List<Integer> goodIds,Integer shopId);
		public List<ShopProductPrice> findPagedShopGoodPriceInfo(Integer shopId,Integer start, Integer count);
		public ShopProductPrice findByShopIdAndProductId(Integer shopId,Integer productId);
		public  void deleteByShopIdAndProductId(Integer shopId,Integer productId);
}
