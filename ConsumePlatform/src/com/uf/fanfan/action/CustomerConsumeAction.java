package com.uf.fanfan.action;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uf.fanfan.common.Constant;
import com.uf.fanfan.common.TradeState;
import com.uf.fanfan.entity.Customer;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.OrderDetail;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.service.ProductManageService;
import com.uf.fanfan.service.TradeService;

public class CustomerConsumeAction extends BaseAction{
	ProductManageService pmService=(ProductManageService)appContext.getBean("productManageService");
	TradeService tdService=(TradeService)appContext.getBean("tradeService");
	Logger log = LoggerFactory.getLogger(CustomerConsumeAction.class);
	private String arriveTime;
	private int productId;
	private int tradeAmount;
	private String tradeDetailId;
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
			CustomerOrder order=null;
			List<CustomerOrder> orders=tdService.getCustomerOrdersByArriveDay(cus.getId(),arrive);
			if(orders==null||orders.size()==0)
				order=new CustomerOrder();
			else 
				order=orders.get(0);
			order.setCustomerid(cus.getId());
			order.setArrivetime(arrive);
			order.setTradestate(TradeState.PROCESSING);
			OrderDetail td=new OrderDetail();
			td.setProductid(productId);
			td.setTradeAmount(tradeAmount);
			td.setTradeprice((float)product.getPrice());
			td.setTradetime(new Timestamp(System.currentTimeMillis()));
			List<OrderDetail> tds=order.getOrderDetails();
			if(tds==null)
				tds=new ArrayList<OrderDetail>();
			tds.add(td);
			tdService.purchaseProducts(order);
			this.writeResultToClient("text/plain", "success");
		}
		return null;
	}
	
	public String delTrade(){
		tdService.cancelOrder(new BigInteger(tradeDetailId));
		writeResultToClient("text/plain", "success");
		return null;
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
	
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(int tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getTradeDetailId() {
		return tradeDetailId;
	}

	public void setTradeDetailId(String tradeDetailId) {
		this.tradeDetailId = tradeDetailId;
	}
	
	
	
}
