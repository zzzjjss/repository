package com.uf.rest.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.rest.bean.Constant;
import com.uf.rest.dao.ClientVersionDao;
import com.uf.rest.dao.OrderDao;
import com.uf.rest.dao.OrderStateHistoryDao;
import com.uf.rest.dao.ProductClassDao;
import com.uf.rest.dao.ProductDao;
import com.uf.rest.dao.ShopBankCardDao;
import com.uf.rest.dao.ShopDao;
import com.uf.rest.dao.ShopProductPriceDao;
import com.uf.rest.dao.ShopUserDao;
import com.uf.rest.dao.ShopVisitRecordDao;
import com.uf.rest.dao.ShopWithDrawRecordDao;
import com.uf.rest.entity.ClientVersion;
import com.uf.rest.entity.Order;
import com.uf.rest.entity.OrderStateHistory;
import com.uf.rest.entity.Product;
import com.uf.rest.entity.ProductClass;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopBankCard;
import com.uf.rest.entity.ShopProductPrice;
import com.uf.rest.entity.ShopUser;
import com.uf.rest.entity.ShopVisitRecord;
import com.uf.rest.entity.ShopWithDrawRecord;
import com.uf.rest.exception.UserExistException;
import com.uf.rest.service.ShopService;
import com.uf.rest.util.DateUtil;

@Service("shopService")
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopUserDao userDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ShopProductPriceDao shopProductPriceDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ShopWithDrawRecordDao withdrawDao;
	@Autowired
	private ShopBankCardDao shopBankCardDao;
	@Autowired
	private OrderStateHistoryDao orderStateHisDao;
	@Autowired
	private ClientVersionDao clientVersionDao;
	@Autowired
	private ShopVisitRecordDao shopVisitDao;
	
	public ShopVisitRecordDao getShopVisitDao() {
		return shopVisitDao;
	}

	public void setShopVisitDao(ShopVisitRecordDao shopVisitDao) {
		this.shopVisitDao = shopVisitDao;
	}

	public ClientVersionDao getClientVersionDao() {
		return clientVersionDao;
	}

	public void setClientVersionDao(ClientVersionDao clientVersionDao) {
		this.clientVersionDao = clientVersionDao;
	}

	public ShopWithDrawRecordDao getWithdrawDao() {
		return withdrawDao;
	}

	public void setWithdrawDao(ShopWithDrawRecordDao withdrawDao) {
		this.withdrawDao = withdrawDao;
	}

	public ShopBankCardDao getShopBankCardDao() {
		return shopBankCardDao;
	}

	public void setShopBankCardDao(ShopBankCardDao shopBankCardDao) {
		this.shopBankCardDao = shopBankCardDao;
	}

	public OrderStateHistoryDao getOrderStateHisDao() {
		return orderStateHisDao;
	}

	public void setOrderStateHisDao(OrderStateHistoryDao orderStateHisDao) {
		this.orderStateHisDao = orderStateHisDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public ShopDao getShopDao() {
		return shopDao;
	}

	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ShopProductPriceDao getShopProductPriceDao() {
		return shopProductPriceDao;
	}

	public void setShopProductPriceDao(ShopProductPriceDao shopProductPriceDao) {
		this.shopProductPriceDao = shopProductPriceDao;
	}

	public ProductClassDao getProductClassDao() {
		return productClassDao;
	}

	public void setProductClassDao(ProductClassDao productClassDao) {
		this.productClassDao = productClassDao;
	}

	public ShopUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(ShopUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void registShopUser(ShopUser user) throws UserExistException {
		if(userDao.findByName(user.getUserName())!=null){
			throw new UserExistException("shopUser has  existed!");
		}
		userDao.insert(user);
	}

	@Override
	public boolean isShopUserExist(String userName) throws Exception {
		ShopUser user=userDao.findByName(userName);
		return user!=null;
	}

	@Override
	public ShopUser findShopUserByName(String userName) throws Exception {
		return userDao.findByName(userName);
	}

	@Override
	public void updateShopUserInfo(ShopUser user) throws Exception {
		userDao.update(user);
		
	}
	public void updateShop(Shop shop)throws Exception{
		shopDao.update(shop);
	}

	@Override
	public void addShop(Shop shop) {
		shopDao.insert(shop);
		
	}

	@Override
	public Shop findShopByShopUserId(Integer userId) {
		List<Shop> shop=shopDao.findByHql("select s from Shop s where s.shopUser.id=?", userId);
		if(shop!=null&&shop.size()>0){
			return shop.get(0);
		}
		return null;
	}

	@Override
	public List<ShopProductPrice> findShopGoodPriceInfoByShopId(Integer shopId) {
		return shopProductPriceDao.findByHql("select s from ShopProductPrice s where s.shop.id=?", shopId);
		
	}

	@Override
	public List<ShopProductPrice> findPagedShopGoodPriceInfo(Integer shopId,
			Integer start, Integer count) {
		return shopProductPriceDao.findPagedShopGoodPriceInfo(shopId, start, count);
	}

	@Override
	public void addProductClass(ProductClass proClass) {
		productClassDao.insert(proClass);
		
	}

	@Override
	public void updateProductClass(ProductClass proClass) {
		productClassDao.update(proClass);
		
	}

	@Override
	public void deleteProductClassById(Integer id) {
		ProductClass proClass=new ProductClass();
		proClass.setId(id);
		productClassDao.delete(proClass);
		
	}

	@Override
	public ProductClass findProductClassById(Integer id) {
		return productClassDao.findById(ProductClass.class, id);
	}

	@Override
	public List<ProductClass> findPagedProductClass(Integer start, Integer count) {
		return productClassDao.findPagedProductClass(start, count);
	}

	@Override
	public void addPublicProduct(Product pro) {
		productDao.insert(pro);
		
	}

	@Override
	public void updatePublicProduct(Product pro) {
		productDao.update(pro);
		
	}

	@Override
	public void priceShopProduct(Integer shopId, Integer productId,
			Float shopProPrice) {
		ShopProductPrice spPrice=shopProductPriceDao.findByShopIdAndProductId(shopId, productId);
		if(spPrice!=null){
			spPrice.setPrice(shopProPrice);
			shopProductPriceDao.update(spPrice);
		}else{
			spPrice=new ShopProductPrice();
			Product pro=new Product();
			pro.setId(productId);
			Shop shop=new Shop();
			shop.setId(shopId);
			spPrice.setProduct(pro);
			spPrice.setShop(shop);
			spPrice.setPrice(shopProPrice);
			shopProductPriceDao.insert(spPrice);
		}
		
	}

	@Override
	public void deletePublicProductById(Integer productId) {
		Product pro=new Product();
		pro.setId(productId);
		productDao.delete(pro);
		
	}

	@Override
	public void deleteShopProductPrice(Integer shopId, Integer productId) {
		shopProductPriceDao.deleteByShopIdAndProductId(shopId, productId);
		
	}

	@Override
	public Product findProducById(Integer productId) {
		return productDao.findById(Product.class, productId);
	}

	@Override
	public ShopProductPrice findShopProductPrice(Integer shopId,
			Integer productId) {
		return shopProductPriceDao.findByShopIdAndProductId(shopId, productId);
	}

	@Override
	public List<Product> findProductsByClassId(Integer productClassId) {
		return productDao.findByHql("select p from Product p where p.productClass.id=?",productClassId );
		
	}
	public long countShopSucessOrderAfterDate(Integer shopId,Date date){
		return orderDao.countShopSucessOrderAfterDate(shopId, date);
	}
	public long countShopVisitAfterDate(Integer shopId,Date date){
		return shopVisitDao.countShopVisitAfterDate(shopId,date);
	}
	
	public List<Order> findShopOrderByOrderState(Integer shopId,Integer orderState){
		return orderDao.findShopOrderByOrderState(shopId, orderState);
	}
	public List<Order> findAllSucessShopOrder(Integer shopId){
		return orderDao.findAllSucessShopOrder(shopId);
	}
	public List<Order> findOneDayOrdersByOrderState(Integer shopId,Date date ,Integer orderState){
		return orderDao.findOneDayOrdersByOrderState(shopId, date, orderState);
	}
	public List<Order> findOneDaySucessOrders(Integer shopId,Date date ){
		return orderDao.findOneDaySucessOrders(shopId, date);
	}
	public List<Order> findPagedShopOrderByOrderState(Integer shopId,Integer orderState,Integer start,Integer count){
		return orderDao.findPagedShopOrderByOrderState(shopId,orderState,start,count);
	}
	public List<Order> findSuccessShopOrder(Integer shopId,Date start,Date end){
		return orderDao.findSuccessShopOrder(shopId,start,end);
	}
	public Date findShopFirstSuccessOrderDate(Integer shopId){
		return orderDao.findShopFirstSuccessOrderDate(shopId);
	}
	public Date findShopFirstVisitDate(Integer shopId){
		return shopVisitDao.findShopFirstVisitDate(shopId);
	}
	public List<ShopVisitRecord> findShopVisitRecord(Integer shopId,Date begin,Date end){
		return shopVisitDao.findByHql("select v from ShopVisitRecord v where v.shop.id=? and v.date>=? and v.date<=? ", shopId,DateUtil.getDateBegin(begin),DateUtil.getDateEnd(end));
	}
	public List<ShopVisitRecord> findAllShopVisitRecord(Integer shopId){
		return shopVisitDao.findByHql("select v from ShopVisitRecord v where v.shop.id=?", shopId);
	}
	public void updateOrderState(Integer orderId,Integer newState){
		Order order=orderDao.findById(Order.class, orderId);
		order.setOrderState(newState);
		orderDao.update(order);
		OrderStateHistory his=new OrderStateHistory();
		his.setOrder(order);
		his.setState(newState);
		his.setTime(new Date());
		orderStateHisDao.insert(his);
	}
	public List<ShopWithDrawRecord> findInProcessWithdraw(Integer shopId){
		return withdrawDao.findByHql("select w from ShopWithDrawRecord w where w.shop.id=? and w.state=?", shopId,Constant.WITHDRAW_STATE_PROCESSING);
	}
	public ShopWithDrawRecord findLastInprocessWithdraw(Integer shopId){
		List<ShopWithDrawRecord> records=withdrawDao.findByHql("select w from ShopWithDrawRecord w where w.shop.id=? and w.state=? order by w.withdrawTime desc ", shopId,Constant.WITHDRAW_STATE_PROCESSING);
		if(records!=null&&records.size()>0){
			return records.get(0);
		}
		return null;
	}
	public List<ShopWithDrawRecord> findPagedWithdraw(Integer shopId,Integer start,Integer count){
		return withdrawDao.findPagedWithdraw(shopId,start,count);
	}
	public ShopBankCard findShopBankCard(Integer shopId){
		List<ShopBankCard> bankCards=shopBankCardDao.findByHql("select b from ShopBankCard b where b.shopUser.id=?", shopId);
		if(bankCards!=null&&bankCards.size()>0){
			return bankCards.get(0);
		}
		return null;
	}
	public ClientVersion findLastClientVersion(){
		List<ClientVersion> versions=clientVersionDao.findByHql("select c from ClientVersion c where c.clientName=?","shop");
		if(versions!=null&&versions.size()>0){
			return versions.get(0);
		}
		return null;
	}
	public Shop findShopById(Integer id){
		return shopDao.findById(Shop.class, id);
	}
}
