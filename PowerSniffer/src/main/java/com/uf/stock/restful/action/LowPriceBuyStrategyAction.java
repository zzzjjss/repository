package com.uf.stock.restful.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uf.stock.data.bean.AlarmStock;
import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.data.bean.StockTradeInfo;
import com.uf.stock.data.sync.StockDataSynchronizer;
import com.uf.stock.restful.bean.LowPriceBuyStrategyResponse;
import com.uf.stock.restful.bean.LowPriceBuyStrategyResponseData;
import com.uf.stock.restful.bean.ResponseError;
import com.uf.stock.restful.bean.RestfulResponse;
import com.uf.stock.service.DataSyncService;
import com.uf.stock.util.SpringBeanFactory;

@Singleton
@Path("/lowPriceBuyStrategy")
public class LowPriceBuyStrategyAction {
private DataSyncService service=SpringBeanFactory.getBean(DataSyncService.class);
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @GET
  @Path("/getStocksDownPercent")
  public String getGoUpStockInfo(){
    LowPriceBuyStrategyResponse response=new LowPriceBuyStrategyResponse(); 
    try{
      List<LowPriceBuyStrategyResponseData> data=new LinkedList<LowPriceBuyStrategyResponseData>();
      List<StockInfo> stocks=service.findStocksPeRatioBetween(-1f, Float.MAX_VALUE);
      List<String> stockSymbols=new ArrayList<String>();
      for(StockInfo stock:stocks){
        stockSymbols.add(stock.getSymbol());
      }
      Map<String,StockTradeInfo> tradeInfo=service.getCurrentStocksTradeInfo(stockSymbols);
      for(String symbol:tradeInfo.keySet()){
        StockTradeInfo info=tradeInfo.get(symbol);
        LowPriceBuyStrategyResponseData rsData=new LowPriceBuyStrategyResponseData();
        AlarmStock alarm=service.findAlarmStockInfoByStockCode(Integer.parseInt(symbol.substring(2)));
        float sellPrice=alarm.getAlarmSellPrice()==null?-1f:alarm.getAlarmSellPrice();
        float buyPrice=alarm.getAlarmBuyPrice()==null?-1f:alarm.getAlarmBuyPrice();
        rsData.setAlarmBuyPrice(buyPrice);
        rsData.setAlarmSellPrice(sellPrice);
        float  downPercent=0f;
        if(info.getClosePrice()!=0){
          downPercent=((info.getClosePrice()-buyPrice)/info.getClosePrice())*100;
          BigDecimal bd = new BigDecimal(downPercent);
          bd = bd.setScale(2, RoundingMode.HALF_UP);
          rsData.setDownPercent(bd.floatValue());
          rsData.setStockName(alarm.getStockName());
          data.add(rsData);
        }
      }
      response.setSuccess(true);
      response.setData(data);
    }catch(Exception e){
      e.printStackTrace();
      ResponseError error=new ResponseError();
      error.setCode("1");
      error.setMsg(e.getMessage());
      response.setError(error);
      response.setSuccess(false);
    }
    GsonBuilder gb=new GsonBuilder();
    gb.serializeSpecialFloatingPointValues();
    Gson gson=gb.create();
    return gson.toJson(response);
  }
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @GET
  @Path("/getAlarmStocks")
  public String getAlarmStocks(){
    StringBuilder alarmInfo=new StringBuilder();
    try{
      List<StockInfo> stocks=service.findStocksPeRatioBetween(-1f, Float.MAX_VALUE);
      List<String> stockSymbols=new ArrayList<String>();
      for(StockInfo stock:stocks){
        stockSymbols.add(stock.getSymbol());
      }
      Map<String,StockTradeInfo> tradeInfo=service.getCurrentStocksTradeInfo(stockSymbols);
      
      for(String symbol:tradeInfo.keySet()){
        StockTradeInfo info=tradeInfo.get(symbol);
        AlarmStock alarm=service.findAlarmStockInfoByStockCode(Integer.parseInt(symbol.substring(2)));
        if(info.getClosePrice()!=0&&info.getClosePrice()<=alarm.getAlarmBuyPrice()){
          alarmInfo.append(symbol+":"+info.getClosePrice()+"\n");
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return alarmInfo.toString();
  }
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @GET
  @Path("/syncAllStock")
  public String syncAllStock(){
	  RestfulResponse response=new RestfulResponse();
	    try{
	      int count=service.syncAllStocksBaseInfo();
	      response.setSuccess(true);
	      response.setMsg("total sync "+count+" stocks");
	    }catch(Exception e){
	      e.printStackTrace();
	      ResponseError error=new ResponseError();
	      error.setCode("1");
	      error.setMsg(e.getMessage());
	      response.setError(error);
	      response.setSuccess(false);
	    }
	    Gson gson=new Gson();
	    return gson.toJson(response);
  }
  
}
