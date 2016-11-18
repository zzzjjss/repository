package com.uf.stock.restful.action;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.uf.stock.bean.UpDownPower;
import com.uf.stock.data.bean.AlarmStock;
import com.uf.stock.data.bean.StockInfo;
import com.uf.stock.restful.bean.GoUpStrategyResponse;
import com.uf.stock.restful.bean.GoUpStrategyResponseData;
import com.uf.stock.restful.bean.ResponseError;
import com.uf.stock.restful.bean.RestfulResponse;
import com.uf.stock.service.DataSyncService;
import com.uf.stock.util.SpringBeanFactory;
import com.uf.stock.util.StockUtil;

@Singleton
@Path("/goUpStrategy")
public class GoUpStrategyAction {
  private DataSyncService service=SpringBeanFactory.getBean(DataSyncService.class);
  
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @GET
  @Path("/getGoUpStockInfo")
  public String getGoUpStockInfo(@QueryParam("minPeRatio")float minPeRatio,@QueryParam("maxPeRatio")float maxPeRation,
      @QueryParam("maxDownPercent")float maxDownPercent){
    GoUpStrategyResponse response=new GoUpStrategyResponse();
    try{
      List<StockInfo> stocks=service.findStocksPeRatioBetween(minPeRatio,maxPeRation);
      List<UpDownPower> powers=service.calculateStocksCurrentPower(stocks);
      List<GoUpStrategyResponseData> data=new ArrayList<GoUpStrategyResponseData>();
      for(UpDownPower power:powers){
        AlarmStock alarm=service.findAlarmStockInfoByStockCode(StockUtil.parseStockSymbolToStockCode(power.getTradeInfo().getStockSymbol()));
        float  downPercent=((power.getTradeInfo().getClosePrice()-alarm.getAlarmBuyPrice())/power.getTradeInfo().getClosePrice())*100;
        if(power.getTradeInfo().getUpDownRate()<1||downPercent>maxDownPercent){
          continue;
        }
        GoUpStrategyResponseData item=new GoUpStrategyResponseData();
        item.setDownPercent(downPercent);
        item.setPeRatio(power.getStockPeRatio());
        item.setPower(power.getPowerValue());
        item.setStockName(power.getStockName());
        item.setUpDownRate(power.getTradeInfo().getUpDownRate());
        item.setStockSymbol(power.getTradeInfo().getStockSymbol());
        data.add(item);
        //System.out.println(power.toString()+":  "+downPercent+"%");
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
    Gson gson=new Gson();
    return gson.toJson(response);
  }
  
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @GET
  @Path("/syncStockTradeInfo")  
  public String syncStockTradeInfo(@QueryParam("stockSymbol")String stockSymbol){
    RestfulResponse response=new RestfulResponse();
    try{
      service.syncStockTradeInfos(stockSymbol);
      response.setSuccess(true);
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
