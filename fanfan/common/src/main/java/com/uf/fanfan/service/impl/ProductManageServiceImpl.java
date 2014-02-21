package com.uf.fanfan.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.common.ProductState;
import com.uf.fanfan.dao.CustomerOrderDao;
import com.uf.fanfan.dao.ProductDao;
import com.uf.fanfan.dao.ProductImageDao;
import com.uf.fanfan.dao.OrderDetailDao;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.OrderDetail;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.entity.ProductImage;
import com.uf.fanfan.service.ProductManageService;
import com.uf.fanfan.util.OperationResult;
@Service("productManageService")
public class ProductManageServiceImpl implements  ProductManageService{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrderDetailDao tradeDetailDao;
	@Autowired
	private CustomerOrderDao customerOrderDao;
	@Autowired
	private ProductImageDao productImageDao;
	
	public void  addProduct(Product product){
		productDao.save(product);
	}
	public OperationResult offLineProduct(Product product){
		OperationResult result=new OperationResult();
		product=productDao.loadById(Product.class, product.getId());
		if(product==null){
			result.setResult(OperationResult.Result.FAIL);
			result.setMessage("产品不存在，无法下线");
		}
		List<CustomerOrder> orders=customerOrderDao.getInProcessingOrderByProductid(product.getId());
		if(orders!=null&&orders.size()>0){
			result.setResult(OperationResult.Result.FAIL);
			String orderLists=Arrays.toString(orders.toArray());
			result.setMessage("存在订单-->>"+orderLists+" <<-- 产品不能下线。");
		}else{
			product.setState(ProductState.OFF_LINE);
			result.setResult(OperationResult.Result.SUCCESS);
			productDao.update(product);
			result.setMessage("产品下线成功");
		}
		return result;
		
	}
	public PageQueryResult<Product> getPageProductsInShopByNameQuery(int pageSize,int pageIndex, int shopid, String nameQuery){
		return productDao.getPageProductsInShopByNameQuery(pageSize, pageIndex, shopid, nameQuery);
	}
	public OperationResult modifyProduct(Product product){
		OperationResult result=new OperationResult(OperationResult.Result.SUCCESS,"修改成功");
		
		if(product==null||product.getId()==null){
			result.setResult(OperationResult.Result.FAIL);
			result.setMessage("产品为空，或者ID不存在，无法修改");
			return result;
		}
		productDao.update(product);
		return result;
	}
	
	public OperationResult deleteProductById(Integer id){
		List<OrderDetail> tradeDetail=tradeDetailDao.findByProductid(id);
		if(tradeDetail==null||tradeDetail.size()==0){
			Product pro=new Product();
			pro.setId(id);
			productDao.delete(pro);
			OperationResult res=new OperationResult(OperationResult.Result.SUCCESS,"删除产品成功");
			return res;
		}else{
			OperationResult res=new OperationResult(OperationResult.Result.FAIL,"存在产品交易明细，无法删除产品");
			return res;
		}
		
	}
	
	public void addProductImage(ProductImage image) {
		productImageDao.save(image);
	}
	
	public void deleteProductImage(int imageId){
		ProductImage image=new ProductImage();
		image.setId(imageId);
		productImageDao.delete(image);
	}
	public Product getProductById(Integer id){
		return this.productDao.loadById(Product.class, id);
	}
	
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public OrderDetailDao getTradeDetailDao() {
		return tradeDetailDao;
	}
	public void setTradeDetailDao(OrderDetailDao tradeDetailDao) {
		this.tradeDetailDao = tradeDetailDao;
	}
	public CustomerOrderDao getCustomerOrderDao() {
		return customerOrderDao;
	}
	public void setCustomerOrderDao(CustomerOrderDao customerOrderDao) {
		this.customerOrderDao = customerOrderDao;
	}
	
	
	
}
