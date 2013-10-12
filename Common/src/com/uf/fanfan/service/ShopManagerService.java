package com.uf.fanfan.service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.entity.Shop;

public interface ShopManagerService {
	public Shop findShopById(Integer id);
	public PageQueryResult<Product> getPageProductsInShopByShopId(int pageSize,int pageIndex,final int shopid);
}
