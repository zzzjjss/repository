package com.uf.rest.service;

import java.util.List;
import java.util.Set;

import com.uf.rest.entity.BankCard;
import com.uf.rest.entity.CustomComment;
import com.uf.rest.entity.Order;
import com.uf.rest.entity.OrderAddress;
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
	public void cancelOrder(Integer orderId);
	public void addUserAddress(OrderAddress address);
	public void updateUserAddress(OrderAddress address);
	public void deleteUserAddressById(Integer addressId);
	public List<OrderAddress> findPagedOrderAddress(Integer userId,Integer start,Integer count);
	public List<BankCard> findPagedBankCards(Integer userId,Integer start,Integer count);
	public BankCard findBankCardById(Integer bankCardId);
	public void addUserBankCard(BankCard bankCard);
	public void updateUserBankCard(BankCard bankCard);
	public void deleteUserBankCard(Integer bankCardId);
	public void addCustomComment(CustomComment comment);
	public List<CustomComment> findPagedComments(Integer start,Integer count);
}
