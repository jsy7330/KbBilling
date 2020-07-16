package com.ntels.ccbs.customer.service.subscription.subscription.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.customer.service.subscription.subscription.SubscriptionService;
import com.ntels.ccbs.customer.domain.subscription.subscription.SubscriptionVO;
import com.ntels.ccbs.customer.mapper.subscription.subscription.SubscriptionMapper;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	@Autowired
	private SubscriptionMapper subscriptionMapper;
	
	@Override
	public Map<String, Object> subscriptionList(String searchStatDt, String searchEndDt, String orderStat, String condCustId, String orderTp, String searchSoId,String wrkDftWrker,String wrkStat, String sidx, String sord, int page, int rows, String lng) {
		
		Map<String,Object> response = new HashMap<String,Object>();
		
		Integer totalCount = subscriptionMapper.subscriptionListCnt(searchStatDt, searchEndDt, orderStat, condCustId, orderTp, searchSoId,wrkDftWrker,wrkStat);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			response.put("orderStatisticsList", new ArrayList<SubscriptionVO>());
			response.put("totalCount", totalCount);
			response.put("totalPages", new Integer(0));
			response.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			 
			
			List<SubscriptionVO> subscriptionList = subscriptionMapper.subscriptionList(searchStatDt, searchEndDt, orderStat, condCustId, orderTp, searchSoId,wrkDftWrker,wrkStat, lng,  sidx, sord, start, end);

			
			response.put("subscriptionList", subscriptionList);
			response.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			response.put("totalPages", totalPages);
			response.put("page", new Integer(page));
		}		
		return response;	
	}		
	@Override
	public Map<String, Object> subscription(String searchStatDt, String searchEndDt, String searchSoId,String wrkDftWrker, String lng) {
		Map<String,Object> response = new HashMap<String,Object>();		 
		List<SubscriptionVO> subscription = subscriptionMapper.subscription(searchStatDt, searchEndDt,searchSoId,wrkDftWrker, lng);
		response.put("subscription", subscription);
		return response;	
	}			
}