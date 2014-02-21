package com.uf.fanfan.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.common.ProductState;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.entity.TradeDetail;
import com.uf.fanfan.repository.ProductRepository;
import com.uf.fanfan.repository.TradeDetailRepository;
import com.uf.fanfan.service.ProductManageService;
import com.uf.fanfan.util.StringUtil;
@Service("productManageService")
public class ProductManageServiceImpl implements  ProductManageService{
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private TradeDetailRepository tradeDetailRes;
	
	public void  addProduct(Product product){
		productRepository.save(product);
	}
	public PageQueryResult<Product> getPageProductsInShop(int pageSize,int pageIndex,final int shopid,final String qtype,final String queryKey){
		
		Page<Product> pages =productRepository.findAll(new Specification<Product>() {
			
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root =query.from(Product.class);
				Path<String> p_name=root.get(qtype);
				Path<Shop> shop=root.get("shop");
				Path<Integer> shopId=shop.get("id");
				if(StringUtil.isNullOrEmpty(queryKey)){
					return cb.equal(shopId, shopid);
				}else{
					return cb.and(cb.equal(shopId, shopid),cb.like(p_name, "%"+queryKey+"%"));
				}
			}
		}, new PageRequest(pageIndex-1, pageSize));
		PageQueryResult<Product> res=new PageQueryResult<Product>();
		res.setPageData(pages.getContent());
		res.setPageIndex(pageIndex);
		res.setTotalPage(pages.getTotalPages());
		res.setPageSize(pageSize);
		res.setTotalRecord(pages.getTotalElements());
		return res;
	}
	
	public void deleteProduct(Integer id){
		List<TradeDetail> tradeDetail=tradeDetailRes.findByProductid(id);
		if(tradeDetail==null||tradeDetail.size()==0){
			productRepository.delete(id);
		}else{
			Product pro=productRepository.findOne(id);
			pro.setState(ProductState.DELETE);
			productRepository.saveAndFlush(pro);
		}
		
	}
	public ProductRepository getProductRep() {
		return productRepository;
	}
	public void setProductRep(ProductRepository productRep) {
		this.productRepository = productRep;
	}
	
	
}
