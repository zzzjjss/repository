package com.uf.fanfan.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.WeekEnum;
import com.uf.fanfan.entity.TradeDetail;
import com.uf.fanfan.repository.TradeDetailRepository;
import com.uf.fanfan.service.TradeDetailManagerService;
import com.uf.fanfan.util.DateUtil;

@Service("tradeDetailManageService")
public class TradeDetailManagerServiceImpl implements TradeDetailManagerService{
	@Autowired
	private TradeDetailRepository tradeDetailRes;
	@Override
	public void purchaseProducts(List<TradeDetail> tradeDetail) {
		for(TradeDetail t:tradeDetail){
			tradeDetailRes.saveAndFlush(t);
		}
		
		
	}

	@Override
	public Map<WeekEnum, List<TradeDetail>> getCustomerThisWeekTradedetail(
			int customerId) {
		Map<WeekEnum, List<TradeDetail>> weekDetail=new HashMap<WeekEnum, List<TradeDetail>>();
		weekDetail.put(WeekEnum.SUNDAY, new ArrayList<TradeDetail>());
		weekDetail.put(WeekEnum.MONDAY, new ArrayList<TradeDetail>());
		weekDetail.put(WeekEnum.TUESDAY, new ArrayList<TradeDetail>());
		weekDetail.put(WeekEnum.WEDNESDAY, new ArrayList<TradeDetail>());
		weekDetail.put(WeekEnum.THURSDAY, new ArrayList<TradeDetail>());
		weekDetail.put(WeekEnum.FRIDAY, new ArrayList<TradeDetail>());
		weekDetail.put(WeekEnum.SATURDAY, new ArrayList<TradeDetail>());
		List<TradeDetail> details=tradeDetailRes.findBetweenArriveTime(DateUtil.getThisWeekSundayDate(), DateUtil.getThisWeekSaturdayDate(),customerId);
		if(details!=null&&details.size()>0){
			for(TradeDetail td:details){
				WeekEnum dayOfWeek=DateUtil.getWeekdayByDate(td.getArriveTime());
				List<TradeDetail> dayDetails=weekDetail.get(dayOfWeek);
				dayDetails.add(td);
			}
		}
		return weekDetail;
	}

	@Override
	public void cancelOrder(BigInteger tradeDetailId) {
		tradeDetailRes.delete(tradeDetailId);
		
	}

	@Override
	public void updateOrder(TradeDetail td) {
		tradeDetailRes.saveAndFlush(td);
		
	}

}
