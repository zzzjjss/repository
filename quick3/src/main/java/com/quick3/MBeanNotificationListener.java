package com.quick3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationListener;

import com.quick3.bean.StatisticResult;
import com.quick3.mail.MailSender;
import com.quick3.statistic.RateStatistic;

public class MBeanNotificationListener implements NotificationListener {

	@Override
	public void handleNotification(Notification notification, Object handback) {
		StringBuilder openResult = new StringBuilder();
		if (notification instanceof AttributeChangeNotification) {
			AttributeChangeNotification noti = (AttributeChangeNotification) notification;
			List<OpenResult> newOpens = (List<OpenResult>) noti.getNewValue();
			for (OpenResult open : newOpens) {
				openResult.append(open.getDateIndex() + ":" + open.getResult() + "\n");
				System.out.println(open.getDateIndex() + ":" + open.getResult());
			}
			RateStatistic stat = new RateStatistic();
			String result = stat.parseNewOpenResults(newOpens);
			if (!result.toString().trim().equals("")) {
				MailSender sender = new MailSender();
				sender.sendTextMail(openResult.toString() + result);
			}
		}

	}

}
