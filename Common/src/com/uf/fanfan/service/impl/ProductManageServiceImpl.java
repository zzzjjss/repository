package com.uf.fanfan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.common.ProductState;
import com.uf.fanfan.dao.ProductDao;
import com.uf.fanfan.dao.TradeDetailDao;
import com.uf.fanfan.entity.OrderDetail;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.service.ProductManageService;
@Service("productManageService")
public class ProductManageServiceImpl implements  ProductManageService{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private TradeDetailDao tradeDetailDao;
	
	public void  addProduct(Product product){
		productDao.save(product);
	}
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,int pageIndex,final int shopid,final String qtype,final String queryKey){
		
//		Page<Product> pages =productRepository.findAll(new Specification<Product>() {
//			
//			@Override
//			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
//					CriteriaBuilder cb) {
//				root =query.from(Product.class);
//				Path<String> p_name=root.get(qtype);
//				Path<Shop> shop=root.get("shop");
//				Path<Integer> shopId=shop.get("id");
//				if(StringUtil.isNullOrEmpty(queryKey)){
//					return cb.equal(shopId, shopid);
//				}else{
//					return cb.and(cb.equal(shopId, shopid),cb.like(p_name, "%"+queryKey+"%"));
//				}
//			}
//		}, new PageRequest(pageIndex-1, pageSize));
		PageQueryResult<Product> res=new PageQueryResult<Product>();
//		res.setPageData(pages.getContent());
//		res.setPageIndex(pageIndex);
//		res.setTotalPage(pages.getTotalPages());
//		res.setPageSize(pageSize);
//		res.setTotalRecord(pages.getTotalElements());
		return res;
	}
	
	public void deleteProduct(Integer id){
		List<OrderDetail> tradeDetail=tradeDetailDao.findByProductid(id);
		if(tradeDetail==null||tradeDetail.size()==0){
			Product pro=new Product();
			pro.setId(id);
			productDao.delete(pro);
		}else{
			Product pro=productDao.loadById(Product.class, id);
			pro.setState(ProductState.DELETE);
			productDao.save(pro);
		}
		
	}
	public Product getProduct(Integer id){
		return this.productDao.loadById(Product.class, id);
	}
	
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public TradeDetailDao getTradeDetailDao() {
		return tradeDetailDao;
	}
	public void setTradeDetailDao(TradeDetailDao tradeDetailDao) {
		this.tradeDetailDao = tradeDetailDao;
	}
	
	
}
