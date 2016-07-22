package com.uf.stock.alarm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.uf.stock.alarm.email.MailSender;
import com.uf.stock.bean.AlarmStock;
import com.uf.stock.dao.AlarmStockDao;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.util.HttpUnit;

public class GetStockCurrrentPriceJob implements  Job{
	AlarmStockDao dao=DaoFactory.getDao(AlarmStockDao.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<AlarmStock> stocks=dao.findAll(AlarmStock.class);
		if(stocks!=null&&stocks.size()>0){
			for(AlarmStock stock:stocks){
				Float price=getStockCurrentPrice(stock);
				if(price!=null&&price.floatValue()>=stock.getAlarmPrice()){
					MailSender sender=new MailSender();
					sender.sendTextMail("stock:"+stock.getStockCode()+"  current price:"+price.floatValue());
					dao.delete(stock);
				}
			}
		}
	}
	private Float getStockCurrentPrice(AlarmStock stock){
			String stockCode="";
			if(stock.getStockCode().startsWith("60")){
				stockCode="sh"+stock.getStockCode();
			}else if(stock.getStockCode().startsWith("00")){
				stockCode="sz"+stock.getStockCode();
			}else{
				return null;
			}
			String url="http://hq.sinajs.cn/?list=s_"+stockCode;
			HttpGet getMethod = new HttpGet(url);
			CloseableHttpResponse responese = null;
			CloseableHttpClient client=HttpUnit.createHttpClient();
			try {
				responese = client.execute(getMethod);
				int status=responese.getStatusLine().getStatusCode();
				if(status==HttpStatus.SC_OK){
				  HttpEntity entity = responese.getEntity();
	                String response=EntityUtils.toString(entity,Charset.forName("gb2312"));
	                String infos[]=response.split(",");
	                if(infos!=null&&infos.length>4){
	                    System.out.println(stock.getStockCode()+"--->"+infos[1]);
	                    if(infos[1]!=null){
	                        return Float.parseFloat(infos[1]);
	                    }
	                }
				}else{
				  System.out.println("http response  status code is:"+status);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					responese.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
			
	}
	
	public static void main(String[] args) {
	  GetStockCurrrentPriceJob  job=new GetStockCurrrentPriceJob();
	  try {
      job.execute(null);
    } catch (JobExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    }
}
