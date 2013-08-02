package com.uf.fanfan.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.common.TradeState;
import com.uf.fanfan.entity.Customer;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.entity.TradeDetail;
import com.uf.fanfan.service.ProductManageService;
import com.uf.fanfan.service.TradeDetailManagerService;

public class ProductShowAction extends BaseAction{
	ProductManageService pmService=(ProductManageService)appContext.getBean("productManageService");
	TradeDetailManagerService tdService=(TradeDetailManagerService)appContext.getBean("tradeDetailManageService");
	Logger log = LoggerFactory.getLogger(ProductShowAction.class);
	private int pageIndex;
	private int id;
	private String arriveTime;
	private int productId;
	private int tradeAmount;
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
	public String buyProduct(){
		Customer cus=(Customer)session.getAttribute("user");
		if(cus==null){
			return "loginPage";
		}else{
			Product product=pmService.getProduct(productId);
			TradeDetail td=new TradeDetail();
			td.setCustomerid(cus.getId());
			td.setArriveTime(Timestamp.valueOf(arriveTime));
			td.setProductid(productId);
			td.setTradeAmount(tradeAmount);
			td.setTradeprice(product.getPrice());
			td.setTradestate(TradeState.PROCESSING);
			td.setTradetime(new Timestamp(System.currentTimeMillis()));
			List<TradeDetail> tds=new ArrayList<TradeDetail>();
			tds.add(td);
			tdService.purchaseProducts(tds);
		}
		return null;
	}
}
