package com.uf.fanfan.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uf.fanfan.common.Constant;
import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.common.WeekEnum;
import com.uf.fanfan.entity.Customer;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.OrderDetail;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.service.ProductManageService;
import com.uf.fanfan.service.TradeService;

public class ProductShowAction extends BaseAction{
	ProductManageService pmService=(ProductManageService)appContext.getBean("productManageService");
	TradeService tdService=(TradeService)appContext.getBean("tradeService");
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
	
	
	public String getUserWeekTradeDetails(){
		Customer cus=(Customer)session.getAttribute("user");
		if(cus==null){
			return "loginPage";
		}else{
			Map<WeekEnum,List<CustomerOrder>> tds=tdService.getCustomerThisWeekOrders(cus.getId());
			Map<WeekEnum,List<OrderDetail>>  details=new HashMap<WeekEnum, List<OrderDetail>>();
			for(WeekEnum weekDay:tds.keySet()){
				List<OrderDetail> orderDetail=new ArrayList<OrderDetail>();
				List<CustomerOrder> weekdayOrders=tds.get(weekDay);
				for(CustomerOrder weekdayOrder:weekdayOrders){
					orderDetail.addAll(weekdayOrder.getOrderDetails());
				}
				details.put(weekDay, orderDetail);
			}
			request.setAttribute("MONDAY", details.get(WeekEnum.MONDAY));
			request.setAttribute("TUESDAY", details.get(WeekEnum.TUESDAY));
			request.setAttribute("WEDNESDAY", details.get(WeekEnum.WEDNESDAY));
			request.setAttribute("THURSDAY", details.get(WeekEnum.THURSDAY));
			request.setAttribute("FRIDAY", details.get(WeekEnum.FRIDAY));
			request.setAttribute("SATURDAY", details.get(WeekEnum.SATURDAY));
			request.setAttribute("SUNDAY", details.get(WeekEnum.SUNDAY));
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
	
	
}
