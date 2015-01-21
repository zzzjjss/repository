package com.uf.rest.service;

import java.util.Date;
import java.util.List;

import com.uf.rest.entity.ClientVersion;
import com.uf.rest.entity.Order;
import com.uf.rest.entity.Product;
import com.uf.rest.entity.ProductClass;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopBankCard;
import com.uf.rest.entity.ShopProductPrice;
import com.uf.rest.entity.ShopUser;
import com.uf.rest.entity.ShopVisitRecord;
import com.uf.rest.entity.ShopWithDrawRecord;
import com.uf.rest.exception.UserExistException;

public interface ShopService {
	public void registShopUser(ShopUser user)throws UserExistException;
	public boolean isShopUserExist(String userName)throws Exception;
	public ShopUser findShopUserByName(String userName)throws Exception;
	public void updateShopUserInfo(ShopUser user)throws Exception;
	public void updateShop(Shop shop)throws Exception;
	public void addShop(Shop shop);
	public Shop findShopByShopUserId(Integer userId);
	public List<ShopProductPrice> findShopGoodPriceInfoByShopId(Integer shopId);
	public List<ShopProductPrice> findPagedShopGoodPriceInfo(Integer shopId,Integer start,Integer count);
	public void addProductClass(ProductClass proClass);
	public void updateProductClass(ProductClass proClass);
	public void deleteProductClassById(Integer id);
	public ProductClass findProductClassById(Integer id);
	public List<ProductClass> findPagedProductClass(Integer start,Integer count);
	public void addPublicProduct(Product pro);
	public void updatePublicProduct(Product pro);
	public void priceShopProduct(Integer shopId,Integer productId,Float shopProPrice);
	public void deletePublicProductById(Integer productId);
	public void deleteShopProductPrice(Integer shopId,Integer productId);
	public Product findProducById(Integer productId);
	public ShopProductPrice findShopProductPrice(Integer shopId,Integer productId);
	public List<Product> findProductsByClassId(Integer productClassId);
	public List<Order> findShopOrderByOrderState(Integer shopId,Integer orderState);
	public List<Order> findOneDayOrdersByOrderState(Integer shopId,Date date ,Integer orderState);
	public List<ShopWithDrawRecord> findInProcessWithdraw(Integer shopId);
	public ShopWithDrawRecord findLastInprocessWithdraw(Integer shopId);
	public ShopBankCard findShopBankCard(Integer shopId);
	public List<ShopWithDrawRecord> findPagedWithdraw(Integer shopId,Integer start,Integer count);
	public List<Order> findPagedShopOrderByOrderState(Integer shopId,Integer orderState,Integer satart,Integer count);
	public List<Order> findSuccessShopOrder(Integer shopId,Date start,Date end);
	public void updateOrderState(Integer orderId,Integer newState);
	public ClientVersion findLastClientVersion();
	public Shop findShopById(Integer id);
	public List<ShopVisitRecord> findShopVisitRecord(Integer shopId,Date begin,Date end);
}
