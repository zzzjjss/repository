package com.uf.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.rest.dao.ProductClassDao;
import com.uf.rest.dao.ProductDao;
import com.uf.rest.dao.ShopDao;
import com.uf.rest.dao.ShopProductPriceDao;
import com.uf.rest.dao.ShopUserDao;
import com.uf.rest.entity.Product;
import com.uf.rest.entity.ProductClass;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopProductPrice;
import com.uf.rest.entity.ShopUser;
import com.uf.rest.exception.UserExistException;
import com.uf.rest.service.ShopService;

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

}
