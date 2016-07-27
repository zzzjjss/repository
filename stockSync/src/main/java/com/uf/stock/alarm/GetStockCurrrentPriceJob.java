package com.uf.stock.alarm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
	public GetStockCurrrentPriceJob(List<AlarmStock> alarmsSocks){
	  this.alarmsSocks=alarmsSocks;
	}
	
	public List<String> call()  throws Exception{
	    DecimalFormat  codeformat=new DecimalFormat("000000");
        
		List<String>  alarmInfo=new ArrayList<String>();
		Map<String, Float>  result=getStocksCurrentPrice();
		if(alarmsSocks!=null&&alarmsSocks.size()>0){
			for(AlarmStock stock:alarmsSocks){
			    Float  price=result.get(codeformat.format(stock.getStockCode()));
				if(price!=null&&price!=0.0f&&price.floatValue()<=stock.getAlarmBuyPrice()){
				  alarmInfo.add(stock.getStockCode()+"  current price :"+price+"  <  "+stock.getAlarmBuyPrice()+"  please buy");
				}
				if(price!=null&&price!=0.0f&&stock.getAlarmSellPrice()!=null&&price.floatValue()>=stock.getAlarmSellPrice()){
				  alarmInfo.add(stock.getStockCode()+"  current price :"+price+"  > "+stock.getAlarmSellPrice()+" please sell");
				}
				if(price!=null&&price!=0.0f){
				  stock.setDownPercent((price-stock.getAlarmBuyPrice())/price);
				  dao.update(stock);
				}
				
			}
		}
		return alarmInfo;
	}
	private Map<String, Float> getStocksCurrentPrice(){
	      Map<String,Float> result=new HashMap<String, Float>();
  	      List<String> urlParam=new ArrayList<String>();
  	      for(AlarmStock stock:alarmsSocks){
  	        DecimalFormat  codeformat=new DecimalFormat("000000");
  	        String code=codeformat.format(stock.getStockCode());
              String stockCode = "";
              if (code.startsWith("60")) {
                stockCode = "s_sh" + stock.getStockCode();
              } else if (code.startsWith("00") || code.startsWith("30")) {
                stockCode = "s_sz" + stock.getStockCode();
              }
              urlParam.add(stockCode);
              result.put(code, 0.0f);
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
                            Float  price=0.0f;
                            String infos[]=keyValue[1].replace("\"", "").split(",");
                            if (infos != null && infos.length > 4) {
                              if (!infos[0].contains("ST") && infos[1] != null) {
                                price = Float.parseFloat(infos[1]);
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
