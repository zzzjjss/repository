package com.uf.rest.service;

import java.util.List;
import java.util.Set;

import com.uf.rest.entity.Order;
import com.uf.rest.entity.OrderDetail;
import com.uf.rest.entity.Product;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopProductPrice;

public interface CustomService {
	public List<Shop> findNearShops(int start, int count,Double longitude,Double latitude);
	public List<Shop> findShops(int start,int count);
	public int findUserProcessingOrderCount(int userId);
	public void saveOrder(Order order,Set<OrderDetail> details);
	public void updateOrder(Order order,Set<OrderDetail> details);
	public void deleteOrderById(int orderId);
	public List<Product> findAllProducts();
	public List<ShopProductPrice> findShopProductPricesByProductIdsAndShopId(List<Integer> productIds,Integer shopId);
	public Order findOrderById(Integer orderId);
	public List<Order>  findPagedOrdersByState(Integer userId,Integer state,Integer start,Integer count);
}
