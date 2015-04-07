package com.uf.stock.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.uf.stock.bean.Stock;
import com.uf.stock.bean.StockTradeInfo;
import com.uf.stock.dao.CompanyInfoDao;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockTradeInfoDao;
import com.uf.stock.util.HttpUnit;

public class GetStockTradeInfoTask implements Callable<Object>{
	private Stock stock;
	private int year;
	private int jidu;
	
	public GetStockTradeInfoTask(Stock stock,int year,int jidu)throws IllegalArgumentException{
		if(year<2000|| jidu>4 ||jidu<1){
			throw new IllegalArgumentException();
		}
		this.stock=stock;
		this.year=year;
		this.jidu=jidu;
	}
	
	public Object call() throws Exception {
		String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+stock.getCode()+".phtml?year="+year+"&jidu="+jidu;
		HttpGet getMethod = new HttpGet(url);
		CloseableHttpResponse responese = null;
		CloseableHttpClient client=HttpUnit.createHttpClient();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		StockTradeInfoDao dao=DaoFactory.getDao(StockTradeInfoDao.class);
		for(int i=0;i<3;i++){
			try {
				responese = client.execute(getMethod);
				HttpEntity entity = responese.getEntity();
				String response=EntityUtils.toString(entity,Charset.forName("gb2312"));
				int  begin=response.indexOf("<table id=\"FundHoldSharesTable\">");
				if(begin==-1)
					return null;
				int end=response.indexOf("</table>", begin);
				String prices=response.substring(begin, end);
				
				Document doc=Jsoup.parse(prices+"</table>");
				Iterator<Element> rows=doc.select("tr").iterator();
				rowss:while(rows.hasNext()){
					Element  row=rows.next();
					Iterator<Element> rowcontents=row.select("td div").iterator();
					String rowContent[]=new String[7];
					int col=0;
					while(rowcontents.hasNext()){
						Element priceContent=rowcontents.next();
						String info=priceContent.text();
						System.out.println(info);
						if(info.equals("ÈÕÆÚ")){
							continue rowss;
						}
						rowContent[col]=info;
						col++;
					}
					if(col>0){
						StockTradeInfo tradeInfo= new StockTradeInfo();
						tradeInfo.setTradeDate(format.parse(rowContent[0]));
						tradeInfo.setOpenPrice(Float.parseFloat(rowContent[1]));
						tradeInfo.setHighestPrice(Float.parseFloat(rowContent[2]));
						tradeInfo.setClosePrice(Float.parseFloat(rowContent[3]));
						tradeInfo.setLowestPrice(Float.parseFloat(rowContent[4]));
						tradeInfo.setTradeAmount(Long.parseLong(rowContent[5]));
						tradeInfo.setTradeMoney(Long.parseLong(rowContent[6]));
						tradeInfo.setStock(stock);
						dao.insert(tradeInfo);
						System.out.println("insert a tradeinfo"+rowContent[0]);
					}
					
				}
				break;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					responese.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
		
		
		
		
//		Document doc = Jsoup.connect(url).get();
//		Elements divs = doc.select("#FundHoldSharesTable tr td div");
//		Iterator<Element> items=divs.iterator();
//		while(items.hasNext()){
//			Element element=items.next();
//			System.out.println(element.data());
//		}
		//return true;
		
	}

}
