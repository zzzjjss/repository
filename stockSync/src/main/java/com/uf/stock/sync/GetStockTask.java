package com.uf.stock.sync;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.uf.stock.bean.Stock;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockDao;
import com.uf.stock.util.HttpUnit;

public class GetStockTask implements Callable<Boolean>{
	String stockCode;
	String stockType;
	public GetStockTask(String stockCode,String stockType){
		this.stockCode=stockCode;
		this.stockType=stockType;
	}
	public Boolean call() throws Exception {
		String url="http://hq.sinajs.cn/list=s_"+stockCode;
		StockDao dao=DaoFactory.getDao(StockDao.class);
		HttpGet getMethod = new HttpGet(url);
		CloseableHttpResponse responese = null;
		CloseableHttpClient client=HttpUnit.createHttpClient();
		for(int j=0;j<3;j++){
			try {
				responese = client.execute(getMethod);
				int status=responese.getStatusLine().getStatusCode();
                if(status==HttpStatus.SC_OK){
                  HttpEntity entity = responese.getEntity();
                    String response=EntityUtils.toString(entity,Charset.forName("gb2312"));
                    System.out.println(response);
                    String infos[]=response.split("=");
                    if(infos!=null&&infos.length>1){
                      infos[1]=infos[1].replaceAll("\"", "");
                      if(infos[1]!=null&&infos[1].trim().contains(",")){
                        String name=(infos[1].split(","))[0];
                        Stock  stock=new Stock();
                        stock.setCode(Integer.parseInt(stockCode.substring(2)));
                        stock.setName(name);
                        stock.setType(stockType);
                        System.out.println("add stock"+stockCode.substring(2));
                        dao.insert(stock);
                      }
                      
                    }
                }else{
                  System.out.println("http response  status code is:"+status);
                }
				break;
			} catch (Exception e) {
			} finally {
				try {
					responese.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}
