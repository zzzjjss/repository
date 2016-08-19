package com.uf.stock.alarm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.quartz.JobExecutionException;

import com.uf.stock.bean.AlarmStock;
import com.uf.stock.dao.AlarmStockDao;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.util.HttpUnit;

public class GetStockCurrrentPriceJob implements  Callable<List<String>>{
	private AlarmStockDao dao=DaoFactory.getDao(AlarmStockDao.class);
	
	private List<AlarmStock> alarmsSocks=new ArrayList<AlarmStock>();
	private boolean updateStock=false;
	public GetStockCurrrentPriceJob(List<AlarmStock> alarmsSocks,boolean updateStock){
	  this.alarmsSocks=alarmsSocks;
	  this.updateStock=updateStock;
	}
	
	public List<String> call()  throws Exception{
	    DecimalFormat  codeformat=new DecimalFormat("000000");
		List<String>  alarmInfo=new ArrayList<String>();
		Map<String, Float[]>  result=getStocksCurrentInfo();
		if(alarmsSocks!=null&&alarmsSocks.size()>0){
			for(AlarmStock stock:alarmsSocks){
			    String code=codeformat.format(stock.getStockCode());
			    Float  info[]=result.get(code);
			    Float price=info[0];
			    Float percent=info[1];
			    Float downPercent=(price-stock.getAlarmBuyPrice())/price;
			    Float profit=stock.getPriceProfit()==null?0f:stock.getPriceProfit();
				if(price!=null&&price!=0.0f&&price.floatValue()<=stock.getAlarmBuyPrice()&&profit>0&&profit<=80){
				  alarmInfo.add(code+stock.getStockName()+"  current price :"+price+"  <  alarm price:"+stock.getAlarmBuyPrice());
				}
				if(price!=null&&price!=0.0f&&stock.getAlarmSellPrice()!=null&&price.floatValue()>=stock.getAlarmSellPrice()){
				  alarmInfo.add(code+stock.getStockName()+"  current price :"+price+"  > "+stock.getAlarmSellPrice()+" please sell");
				}
				if(percent!=null&&percent.floatValue()>=2&&downPercent<=0.2&&profit>0&&profit<=80){
				  alarmInfo.add(code+stock.getStockName()+" ------> "+percent+"%");
				}
				if(percent!=null&&percent.floatValue()<=-2&&downPercent<=0.3&&profit>0&&profit<=80){
                  alarmInfo.add(code+stock.getStockName()+" ------> "+percent+"%  make a T ");
                }
				if(price!=null&&price!=0.0f&&updateStock){
				  stock.setDownPercent((price-stock.getAlarmBuyPrice())/price);
				  dao.update(stock);
				  System.out.println("update "+stock.getStockCode());
				}
			}
		}
		return alarmInfo;
	}
	private Map<String, Float[]> getStocksCurrentInfo(){
	      Map<String,Float[]> result=new HashMap<String, Float[]>();
  	      List<String> urlParam=new ArrayList<String>();
  	      for(AlarmStock stock:alarmsSocks){
  	        DecimalFormat  codeformat=new DecimalFormat("000000");
  	        String code=codeformat.format(stock.getStockCode());
              String stockCode = "";
              if (code.startsWith("60")) {
                stockCode = "s_sh" + code;
              } else if (code.startsWith("00") || code.startsWith("30")) {
                stockCode = "s_sz" + code;
              }
              urlParam.add(stockCode);
              result.put(code, new Float[]{0.0f,0.0f});
  	      }
    	 
			String url="http://hq.sinajs.cn/?list="+StringUtils.join(urlParam, ",");
			HttpGet getMethod = new HttpGet(url);
			CloseableHttpResponse responese = null;
			CloseableHttpClient client=HttpUnit.createHttpClient();
			try {
				responese = client.execute(getMethod);
				int status=responese.getStatusLine().getStatusCode();
				if(status==HttpStatus.SC_OK){
				  HttpEntity entity = responese.getEntity();
	                String response=EntityUtils.toString(entity,Charset.forName("gb2312"));
	                String stocksPrice[]=StringUtils.split(response,";");
	                if(stocksPrice!=null){
	                  for(String stockInfo:stocksPrice){
  	                    if(StringUtils.isNotBlank(stockInfo)&&stockInfo.contains("=")){
  	                        String keyValue[]=stockInfo.split("=");
                            String code=keyValue[0].substring(keyValue[0].length()-6);
                            Float  price[]=new Float[]{0.0f,0.0f};
                            
                            String infos[]=keyValue[1].replace("\"", "").split(",");
                            if (infos != null && infos.length > 4) {
                              if (!infos[0].contains("ST") && infos[1] != null) {
                                price[0] = Float.parseFloat(infos[1]);
                              }
                              if (!infos[0].contains("ST") && infos[3] != null) {
                                price[1] = Float.parseFloat(infos[3]);
                              }
                            }
                            result.put(code, price);  
  	                      }
	                    }
	                }
				}else{
				  System.out.println("http response  status code is:"+status+" -->"+url);
				}
				
			} catch (Exception e) {
			    System.out.println("error parse ->"+url);
				e.printStackTrace();
			} finally {
				try {
					responese.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return result;
			
	}
}
