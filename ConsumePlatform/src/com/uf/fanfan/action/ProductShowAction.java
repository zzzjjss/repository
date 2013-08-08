package com.uf.fanfan.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uf.fanfan.common.Constant;
import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.common.TradeState;
import com.uf.fanfan.common.WeekEnum;
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
			Timestamp arrive=Timestamp.valueOf(arriveTime);
			if(arrive.before(getLatestArriveTime())){
				writeResultToClient("text/plain", "outOfDate");
				return null;
			}
			Product product=pmService.getProduct(productId);
			TradeDetail td=new TradeDetail();
			td.setCustomerid(cus.getId());
			td.setArriveTime(arrive);
			td.setProductid(productId);
			td.setTradeAmount(tradeAmount);
			td.setTradeprice(product.getPrice());
			td.setTradestate(TradeState.PROCESSING);
			td.setTradetime(new Timestamp(System.currentTimeMillis()));
			List<TradeDetail> tds=new ArrayList<TradeDetail>();
			tds.add(td);
			tdService.purchaseProducts(tds);
			this.writeResultToClient("text/plain", "success");
		}
		return null;
	}
	
	public String getUserWeekTradeDetails(){
		Customer cus=(Customer)session.getAttribute("user");
		if(cus==null){
			return "loginPage";
		}else{
			Map<WeekEnum,List<TradeDetail>> tds=tdService.getCustomerThisWeekTradedetail(cus.getId());
			request.setAttribute("MONDAY", tds.get(WeekEnum.MONDAY));
			request.setAttribute("TUESDAY", tds.get(WeekEnum.TUESDAY));
			request.setAttribute("WEDNESDAY", tds.get(WeekEnum.WEDNESDAY));
			request.setAttribute("THURSDAY", tds.get(WeekEnum.THURSDAY));
			request.setAttribute("FRIDAY", tds.get(WeekEnum.FRIDAY));
			request.setAttribute("SATURDAY", tds.get(WeekEnum.SATURDAY));
			request.setAttribute("SUNDAY", tds.get(WeekEnum.SUNDAY));
			GregorianCalendar calen=new GregorianCalendar(Locale.CHINA);
			calen.setTime(new Date(System.currentTimeMillis()));
			int dayOfWeek=calen.get(Calendar.DAY_OF_WEEK);
			Calendar cal = Calendar.getInstance();
			int house=cal.get(Calendar.HOUR_OF_DAY);
			//当前时间超过了当天订餐时间，则 可订餐时间推后一天。
			if(house>=Constant.STOP_CONSUME_HOUSE){
				dayOfWeek++;
			}
			request.setAttribute("today", dayOfWeek);
			return "weekOrder";
		}
	}
	
	private Date getLatestArriveTime(){
		Calendar cal = Calendar.getInstance();
		int house = cal.get(Calendar.HOUR_OF_DAY);
		if (house >= Constant.STOP_CONSUME_HOUSE) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		}
		return cal.getTime();
	}
}
