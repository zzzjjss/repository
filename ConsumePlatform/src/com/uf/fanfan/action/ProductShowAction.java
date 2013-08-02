package com.uf.fanfan.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.service.ProductManageService;

public class ProductShowAction extends BaseAction{
	ProductManageService pmService=(ProductManageService)appContext.getBean("productManageService");
	Logger log = LoggerFactory.getLogger(ProductShowAction.class);
	private int pageIndex;
	private int id;
	public String getOnePageProducts(){
		PageQueryResult<Product> products=pmService.getPageProductsInShop(6, pageIndex, 1,null	,null);
		request.setAttribute("products", products.getPageData());
		request.setAttribute("pageIndex", pageIndex);
		return "productShow";
	}
	public String getProduct(){
		Product pro=pmService.getProduct(id);
		request.setAttribute("product", pro);
		return "showProduct";
	}
}
