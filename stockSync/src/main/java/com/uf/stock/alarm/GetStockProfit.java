package com.uf.stock.alarm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.uf.stock.bean.AlarmStock;
import com.uf.stock.dao.AlarmStockDao;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.util.HttpUnit;

public class GetStockProfit implements  Callable<Boolean>{

  public Boolean call() throws Exception {
    CloseableHttpClient client=HttpUnit.createHttpClient();
    AlarmStockDao dao=DaoFactory.getDao(AlarmStockDao.class);
    while(true){
      int page=1,totalPage=Integer.MAX_VALUE;
      while(page<=totalPage){
        String url="http://screener.finance.sina.com.cn/znxg/data/json.php/SSCore.doView";
        HttpPost  post=new HttpPost(url);
        List<NameValuePair>  params=new ArrayList<NameValuePair>();
        NameValuePair  pair1=new BasicNameValuePair("page", String.valueOf(page));
        NameValuePair  pair2=new BasicNameValuePair("num", "60");
        NameValuePair  pair3=new BasicNameValuePair("asc", "1");
        NameValuePair  pair4=new BasicNameValuePair("field0", "stocktype");
        NameValuePair  pair5=new BasicNameValuePair("field1", "sinahy");
        NameValuePair  pair6=new BasicNameValuePair("field2", "diyu");
        NameValuePair  pair7=new BasicNameValuePair("value0", "*");
        NameValuePair  pair8=new BasicNameValuePair("value1", "*");
        NameValuePair  pair9=new BasicNameValuePair("value2", "*");
        NameValuePair  pair10=new BasicNameValuePair("field3", "dtsyl");
        NameValuePair  pair11=new BasicNameValuePair("max3", "87307.8");
        NameValuePair  pair12=new BasicNameValuePair("min3", "1");
        params.add(pair1);
        params.add(pair2);
        params.add(pair3);
        params.add(pair4);
        params.add(pair5);
        params.add(pair6);
        params.add(pair7);
        params.add(pair8);
        params.add(pair9);
        params.add(pair10);
        params.add(pair11);
        params.add(pair12);
        UrlEncodedFormEntity  entiry=new  UrlEncodedFormEntity(params); 
        post.setEntity(entiry);
       
        CloseableHttpResponse  response=client.execute(post);
        int statusCode=response.getStatusLine().getStatusCode();
        if(statusCode != org.apache.http.HttpStatus.SC_OK){
          System.out.println("response code is:"+statusCode+" from "+url);
        }else{
          String json=EntityUtils.toString(response.getEntity());
          json=json.substring(1, json.length()-1);
          JsonParser parser=new JsonParser();
          JsonObject obj=(JsonObject)parser.parse(json);
          JsonArray stockInfos=obj.getAsJsonArray("items");
          Iterator<JsonElement> its= stockInfos.iterator();
          while(its.hasNext()){
            JsonElement  stockInfo=its.next();
            JsonObject stockInfoObj=stockInfo.getAsJsonObject();
            String symbol=stockInfoObj.get("symbol").getAsString();
            symbol=symbol.substring(2, symbol.length());
            Float dtsyl=stockInfoObj.get("dtsyl").getAsFloat();
            AlarmStock alarm=dao.findByStockCode(Integer.parseInt(symbol));
            if(alarm!=null){
              alarm.setPriceProfit(dtsyl);
              dao.update(alarm);
            }
            System.out.println(symbol+"-->"+dtsyl);
          }
          
          JsonElement totalPageEl=obj.get("page_total");
          totalPage=totalPageEl.getAsInt();
          page++;
        }
      }
      return true;
    }
   
  }
public static void main(String[] args) {
  GetStockProfit  profit=new GetStockProfit();
  try {
    profit.call();
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
}
}
