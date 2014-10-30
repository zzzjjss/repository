package com.uf.stock.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uf.stock.util.HttpUnit;

public class Synchronizer {
	private Logger logger = LogManager.getLogger(Synchronizer.class);

	public void syncTestRunResult() {
		CloseableHttpClient client = HttpUnit.createHttpClient();
		ExecutorService pool = Executors.newFixedThreadPool(500);
		List<String> stockCodes=new ArrayList<String>();
		for(String code:stockCodes){
			pool.submit(new GetStockInfoTask(code,client));
		}
		pool.shutdown();
		boolean completed = false;
		while (!completed) {
			try {
				completed = pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.HOURS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
