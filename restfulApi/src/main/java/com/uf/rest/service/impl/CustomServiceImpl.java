package com.uf.rest.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.rest.bean.Constant;
import com.uf.rest.dao.BankCardDao;
import com.uf.rest.dao.ClientVersionDao;
import com.uf.rest.dao.CustomCommentDao;
import com.uf.rest.dao.OrderAddressDao;
import com.uf.rest.dao.OrderDao;
import com.uf.rest.dao.OrderDetailDao;
import com.uf.rest.dao.ProductClassDao;
import com.uf.rest.dao.ProductDao;
import com.uf.rest.dao.ShopDao;
import com.uf.rest.dao.ShopProductPriceDao;
import com.uf.rest.dao.ShopVisitRecordDao;
import com.uf.rest.entity.BankCard;
import com.uf.rest.entity.ClientVersion;
import com.uf.rest.entity.CustomComment;
import com.uf.rest.entity.Order;
import com.uf.rest.entity.OrderAddress;
import com.uf.rest.entity.OrderDetail;
import com.uf.rest.entity.Product;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopProductPrice;
import com.uf.rest.entity.ShopVisitRecord;
import com.uf.rest.service.CustomService;
import com.uf.rest.util.DateUtil;

@Service("customService")
public class CustomServiceImpl implements CustomService{
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private OrderAddressDao orderAddressDao;
	
	@Autowired
	private ProductClassDao productClssDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ShopProductPriceDao shopProductPriceDao;
	@Autowired
	private BankCardDao bankCardDao;
	@Autowired
	private CustomCommentDao commentDao;
	@Autowired
	private ClientVersionDao clientVersionDao;
	@Autowired
	private ShopVisitRecordDao shopVisitDao;
	
	public BankCardDao getBankCardDao() {
		return bankCardDao;
	}

	public void setBankCardDao(BankCardDao bankCardDao) {
		this.bankCardDao = bankCardDao;
	}

	public CustomCommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CustomCommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public ClientVersionDao getClientVersionDao() {
		return clientVersionDao;
	}

	public void setClientVersionDao(ClientVersionDao clientVersionDao) {
		this.clientVersionDao = clientVersionDao;
	}

	

	public ShopVisitRecordDao getShopVisitDao() {
		return shopVisitDao;
	}

	public void setShopVisitDao(ShopVisitRecordDao shopVisitDao) {
		this.shopVisitDao = shopVisitDao;
	}

	public ShopProductPriceDao getShopProductPriceDao() {
		return shopProductPriceDao;
	}

	public void setShopProductPriceDao(ShopProductPriceDao shopProductPriceDao) {
		this.shopProductPriceDao = shopProductPriceDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public OrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public OrderAddressDao getOrderAddressDao() {
		return orderAddressDao;
	}

	public void setOrderAddressDao(OrderAddressDao orderAddressDao) {
		this.orderAddressDao = orderAddressDao;
	}

	public ProductClassDao getProductClssDao() {
		return productClssDao;
	}

	public void setProductClssDao(ProductClassDao productClssDao) {
		this.productClssDao = productClssDao;
	}

	public ShopDao getShopDao() {
		return shopDao;
	}

	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}

	@Override
	public List<Shop> findNearShops(int start, int count,Double longitude,Double latitude) {
		return shopDao.findNearShops(start, count, longitude, latitude);
	}
	
	public int findUserProcessingOrderCount(int userId){
		return orderDao.findUserProcessingOrderCount(userId);
	}
	public List<Shop> findShops(int start,int count){
		return shopDao.findShops(start, count);
	}
	public void saveOrder(Order order,Set<OrderDetail> details){
		orderDao.insert(order);
		for(OrderDetail detail:details){
			detail.setOrder(order);
			orderDetailDao.insert(detail);
		}
		
	}
	public void updateOrder(Order order,Set<OrderDetail> details){
		orderDetailDao.deleteByOrderId(order.getId());
		orderDao.update(order);
		for(OrderDetail detail:details){
			detail.setOrder(order);
			orderDetailDao.insert(detail);
		}
	}
	public void deleteOrderById(int orderId){
		orderDetailDao.deleteByOrderId(orderId);
		Order order=new Order();
		order.setId(orderId);
		orderDao.delete(order);
	}
	
	public List<Product> findAllProducts(){
		return productDao.findByHql("select p from Product p");
	}
	public List<ShopProductPrice> findShopProductPricesByProductIdsAndShopId(List<Integer> productIds,Integer shopId){
		return shopProductPriceDao.findShopProductPriceByGoodIds(productIds, shopId);
	}
	public Order findOrderById(Integer orderId){
		return orderDao.findById(Order.class, orderId);
	}
	public List<Order>  findPagedOrdersByState(Integer userId,Integer state,Integer start,Integer count){
		return orderDao.findPagedOrdersByState(userId, state, start, count);
	}
	public void cancelOrder(Integer orderId){
		Order order=orderDao.findById(Order.class, orderId);
		order.setOrderState(Constant.ORDER_STATE_CANCELED);
		orderDao.update(order);
	}
	public void addUserAddress(OrderAddress address){
		orderAddressDao.insert(address);
	}
	public void updateUserAddress(OrderAddress address){
		orderAddressDao.update(address);
	}
	public void deleteUserAddressById(Integer addressId){
		OrderAddress add=new OrderAddress();
		add.setId(addressId);
		orderAddressDao.delete(add);
	}
	public List<OrderAddress> findPagedOrderAddress(Integer userId,Integer start,Integer count){
		return orderAddressDao.findPagedUserOrderAddress(userId, start, count);
	}
	public List<BankCard> findPagedBankCards(Integer userId,Integer start,Integer count){
		return bankCardDao.findPagedUserBankCards(userId, start, count);
	}
	public void addUserBankCard(BankCard bankCard){
		bankCardDao.insert(bankCard);
	}
	public void updateUserBankCard(BankCard bankCard){
		bankCardDao.update(bankCard);
	}
	public void deleteUserBankCard(Integer bankCardId){
		BankCard card=new BankCard();
		card.setId(bankCardId);
		bankCardDao.delete(card);
	}
	public BankCard findBankCardById(Integer bankCardId){
		return bankCardDao.findById(BankCard.class, bankCardId);
	}
	public void addCustomComment(CustomComment comment){
		commentDao.insert(comment);
	}
	public List<CustomComment> findPagedComments(Integer start,Integer count){
		return commentDao.findPagedComments(start, count);
	}
	public ShopVisitRecord findOneDayVisitRecord(Integer shopId,Date date){
		List<ShopVisitRecord> records=shopVisitDao.findByHql("select v from ShopVisitRecord v where v.shop.id=? and v.date>=? and v.date<=? ", shopId,DateUtil.getDateBegin(date),DateUtil.getDateEnd(date));
		if(records!=null){
			return records.get(0);
		}
		return null;
	}
	public void saveVisitRecord(ShopVisitRecord record){
		shopVisitDao.saveOrUpdate(record);
	}
	public ClientVersion findLastClientVersion(){
		List<ClientVersion> versions=clientVersionDao.findByHql("select c from ClientVersion c where c.clientName=?","custom");
		if(versions!=null&&versions.size()>0){
			return versions.get(0);
		}
		return null;
	}
}
