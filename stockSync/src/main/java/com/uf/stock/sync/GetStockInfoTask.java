package com.uf.stock.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uf.stock.dao.CompanyInfoDao;
import com.uf.stock.util.HttpUnit;

public class GetStockInfoTask implements Callable<Object> {
	private Logger logger = LogManager.getLogger(GetStockInfoTask.class);
	private CloseableHttpClient client;
	private String stockCode;

	public GetStockInfoTask(String stockCode, CloseableHttpClient client) {
		this.stockCode = stockCode;
		this.client = client;
	}

	public Object call() {
		String url = "http://cj.gw.com.cn/news/stock/" + stockCode + "/gsjs.shtml";
		HttpGet getMethod = new HttpGet(url);
		CloseableHttpResponse responese = null;
		try {
			responese = client.execute(getMethod);
			HttpEntity entity = responese.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line = null;
			while ((line = br.readLine()) != null) {
				Pattern pattern = Pattern.compile(".*<td class=[\"]graybg[\"]>主营范围:</td>.*");
				Matcher match = pattern.matcher(line);
				if (match.find()) {
					StringBuilder content = new StringBuilder();
					while ((line = br.readLine()) != null) {
						Pattern pattern2 = Pattern.compile(".*</td>.*");
						Matcher match2 = pattern2.matcher(line);
						if (!match2.find()) {
							content.append(line);
						} else {
							content.append(line);
							break;
						}
					}
					String contentString=content.toString();
					CompanyInfoDao  dao=new CompanyInfoDao();
					dao.addCompanyBusinessContet(stockCode,contentString.substring(contentString.indexOf(">")+1,contentString.lastIndexOf("<")));
					break;
				}
			}
		} catch (Exception e) {
			logger.error("GetStockInfo from webservice error:[" + stockCode + "] ", e);
		} finally {
			try {
				responese.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static void main(String[] args) {
		GetStockInfoTask task = new GetStockInfoTask("sz300347", HttpUnit.createHttpClient());
		task.call();
	}
}
