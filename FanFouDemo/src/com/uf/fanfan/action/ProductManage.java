package com.uf.fanfan.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.service.ProductManageService;
import com.uf.fanfan.util.PageQueryUtil;

public class ProductManage extends BaseAction {
	ProductManageService pmService=(ProductManageService)appContext.getBean("productManageService");
	Logger log = LoggerFactory.getLogger(ProductManage.class);
	
	private int page;
	private int rp;
	private String sortname;
	private String sortorder;
	private String query;
	private String qtype;
	
	public String addProduct() {
		try{
			log.info("add product ");
			Product pro=new  Product();
			pro.setName("kaojic");
			pro.setPrice(15.00);
			pro.setCreateTime(new Timestamp(System.currentTimeMillis()));
			Shop s=new Shop();
			s.setId(1);
			pro.setShop(s);
			pmService.addProduct(pro);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public String delProduct() {
		return "ajaxSuccess";
	}

	public String modifyProduct() {
		return SUCCESS;
	}

	public String getPageShopProducts() {
		try{
			PageQueryResult<Product> res=pmService.getPageProductsInShop(rp, page, 1);
			String jsonRes=PageQueryUtil.convertToFlexigridJson(res, new String[]{"name","price","saleSum","createTime"});
			response.setContentType("text/json");
			response.setCharacterEncoding("utf-8");
			response.getOutputStream().write(jsonRes.getBytes("utf-8"));
		}catch(Exception e){
			log.error("getPageShopProducts error", e);
		}
		return null;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	
	
}
