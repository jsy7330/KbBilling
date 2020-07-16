package com.ntels.ccbs.customer.service.customer.customer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoHistVO;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountHistVO;
import com.ntels.ccbs.customer.mapper.customer.customer.CustomerHistoryManagementMapper;
import com.ntels.ccbs.customer.service.customer.customer.CustomerHistoryManagementService;


@Service
public class CustomerHistoryManagementServiceImpl implements CustomerHistoryManagementService{
	@Autowired
	private CustomerHistoryManagementMapper  customerHistoryManagementMapper;

	@Override
	public Map<String, Object> getCustChngHistList(String soId, String custId,
	        String today, String sidx, String sord, int page, int rows,
	        String lng, CustomerInfoHistVO custInfoHist) {
		Map<String,Object> custChngHistInfo = new HashMap<String,Object>();
		Integer totalCount = customerHistoryManagementMapper.getCustChngHistListTotalCnt(soId, custId,today,custInfoHist);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			custChngHistInfo.put("custChngHistList", new ArrayList<PaymentAccountHistVO>());
			custChngHistInfo.put("totalCount", totalCount);
			custChngHistInfo.put("totalPages", new Integer(0));
			custChngHistInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			List<CustomerInfoHistVO> custChngHist = customerHistoryManagementMapper.getCustChngHistList(soId, custId, today, sidx, sord, start, end, lng,custInfoHist);
			custChngHistInfo.put("custChngHistList", custChngHist);
			custChngHistInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			custChngHistInfo.put("totalPages", totalPages);
			custChngHistInfo.put("page", new Integer(page));
		}
		
		return custChngHistInfo;
	}

}
