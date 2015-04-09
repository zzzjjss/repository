package com.uf.stock.alarm;

import java.util.List;

import com.uf.stock.alarm.email.MailSender;
import com.uf.stock.bean.AlarmStock;
import com.uf.stock.dao.AlarmStockDao;
import com.uf.stock.dao.DaoFactory;

public class Alarmer {
	public void startEmailAlarm(){
		AlarmStockDao dao=DaoFactory.getDao(AlarmStockDao.class);
		while(true){
			List<AlarmStock> stocks=dao.findAll(AlarmStock.class);
			if(stocks!=null&&stocks.size()>0){
				for(AlarmStock stock:stocks){
					GetCurrentPriceTask getStockPrice=new GetCurrentPriceTask(stock);
					Float price=getStockPrice.call();
					if(price!=null&&price.floatValue()>=stock.getAlarmPrice()){
						MailSender sender=new MailSender();
						sender.sendTextMail("stock:"+stock.getStockCode()+"  current price:"+price.floatValue());
						dao.delete(stock);
					}
				}
			}
			try {
				Thread.sleep(30*1000);
			} catch (InterruptedException e) {
			}
		}
		
		
	}
}
