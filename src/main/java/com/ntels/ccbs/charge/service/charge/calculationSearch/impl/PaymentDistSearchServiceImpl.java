package com.ntels.ccbs.charge.service.charge.calculationSearch.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.mapper.charge.calculationSearch.PaymentDistSearchMapper;
import com.ntels.ccbs.charge.service.charge.calculationSearch.PaymentDistSearchService;

@Service
public class PaymentDistSearchServiceImpl implements PaymentDistSearchService {

	@Autowired
	private PaymentDistSearchMapper paymentDistSerachMapper;

	@Override
	public Map<String, Object> getChargeDiscountInfoList(String soId, String billYymm, 
			String pymAcntId, String custId, String sidx, String sord, int page, int rows, String lng) {
		// TODO Auto-generated method stub
		
		Map<String, Object> chargDistInfo = new HashMap<String, Object>();
		Integer totalCount = paymentDistSerachMapper.chrgDistListCnt(soId, billYymm, pymAcntId, custId);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		
		if(totalCount.intValue() == 0){
			chargDistInfo.put("chargDistInfoList", new ArrayList<Map<String,Object>>());
			chargDistInfo.put("totalCount", totalCount);
			chargDistInfo.put("totalPages", new Integer(0));
			chargDistInfo.put("page", new Integer(1));
		}else {
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<Map<String,Object>> chargDistInfoList = paymentDistSerachMapper.getChargeDiscountInfoList(soId, billYymm, pymAcntId, custId, sidx, sord, start, end, lng);
			
			chargDistInfo.put("chargDistInfoList", chargDistInfoList);
			chargDistInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			chargDistInfo.put("totalPages", totalPages);
			chargDistInfo.put("page", new Integer(page));
		}
		
		return chargDistInfo;
	}

	@Override
	public Map<String, Object> getChargeDiscountInfoDetList(String soId, String billYymm, String pymAcntId,
			String ctrtId, String sidx, String sord, int page, int rows, String lng) {
		// TODO Auto-generated method stub
		Map<String, Object> chargDistDetInfo = new HashMap<String, Object>();
		Integer totalCount = paymentDistSerachMapper.chrgDistDetListCnt(soId, billYymm, pymAcntId, ctrtId);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		
		if(totalCount.intValue() == 0){
			chargDistDetInfo.put("chargDistDetInfoList", new ArrayList<Map<String,Object>>());
			chargDistDetInfo.put("totalCount", totalCount);
			chargDistDetInfo.put("totalPages", new Integer(0));
			chargDistDetInfo.put("page", new Integer(1));
		}else {
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<Map<String,Object>> chargDistDetInfoList = paymentDistSerachMapper.getChargeDiscountInfoDetList(soId, billYymm, pymAcntId, ctrtId, sidx, sord, start, end, lng);
			
			chargDistDetInfo.put("chargDistDetInfoList", chargDistDetInfoList);
			chargDistDetInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			chargDistDetInfo.put("totalPages", totalPages);
			chargDistDetInfo.put("page", new Integer(page));
		}
		
		return chargDistDetInfo;
		
	}

}
