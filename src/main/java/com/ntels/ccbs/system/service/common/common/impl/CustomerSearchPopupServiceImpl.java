package com.ntels.ccbs.system.service.common.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.common.common.CustSearchVO;
import com.ntels.ccbs.system.mapper.common.common.CustomerSearchPopupMapper;
import com.ntels.ccbs.system.service.common.common.CustomerSearchPopupService;
import com.ntels.nisf.util.StringUtil;

@Service
public class CustomerSearchPopupServiceImpl implements CustomerSearchPopupService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerSearchPopupMapper customerSearchPopupMapper;

	@Override
	public Map<String, Object> getCustInfoList(String soId, String custNm,
	        String custId, String ctrtId, String svcTelNo, String corpRegNo, String bizNo,
	        List<Map<String, Object>> soAuthList, String today, String lng,
	        String sidx, String sort, int page, int rows) {
		
		Map<String,Object> custInfo = new HashMap<String,Object>();
		
		boolean hasCtrtKey = false;
		
		if(!StringUtil.isEmpty(ctrtId) ||
				!StringUtil.isEmpty(svcTelNo)){
			hasCtrtKey = true;
		}
		
		List<String> corpRegNoList = new ArrayList<String>();
		if(!StringUtil.isEmpty(corpRegNo)){
			for(int i=0; i < 10;i++){
				StringBuffer sb = new StringBuffer(corpRegNo);
				sb.append(i);
				sb.append("000000");
				corpRegNoList.add(sb.toString());
			}	
		}
		Integer totalCount = new Integer(0);
		
		if(!hasCtrtKey){
			totalCount = customerSearchPopupMapper.getCustInfoTotalCnt(soId, custNm, custId, corpRegNo, bizNo, corpRegNoList, soAuthList);
		}else{
			totalCount = customerSearchPopupMapper.getCustInfoWithCtrtTotalCnt(soId, custNm, custId, corpRegNo, bizNo, ctrtId, svcTelNo, corpRegNoList, soAuthList);
		}
				
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		

		if(totalCount.intValue() == 0){
			custInfo.put("custInfoList", new ArrayList<CustSearchVO>());
			custInfo.put("totalCount", totalCount);
			custInfo.put("totalPages", new Integer(0));
			custInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			List<CustSearchVO> custInfoList;
			if(!hasCtrtKey){
				custInfoList = customerSearchPopupMapper.getCustInfoList(soId, custNm, custId, corpRegNo, bizNo,  corpRegNoList, soAuthList,  today, lng, sidx, sort, start, end);
			}else{
				custInfoList = customerSearchPopupMapper.getCustInfoWithList(soId, custNm, custId, corpRegNo, bizNo, ctrtId, svcTelNo, corpRegNoList, soAuthList, today, lng, sidx, sort, start, end);
			}
			custInfo.put("custInfoList", custInfoList);
			custInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			custInfo.put("totalPages", totalPages);
			custInfo.put("page", new Integer(page));
		}
		
		return custInfo;
	}

	@Override
	public Map<String, Object> getCustCtrtList(String soId, String custNm, String today, String lng, String sidx,
			String sort, int page, int rows) {
		// TODO Auto-generated method stub
		
		Map<String,Object> custInfo = new HashMap<String,Object>();
		
		Integer totalCount = customerSearchPopupMapper.getCustCtrtListCnt(soId, custNm);
				
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		

		if(totalCount.intValue() == 0){
			custInfo.put("custInfoList", new ArrayList<Map<String, Object>>());
			custInfo.put("totalCount", totalCount);
			custInfo.put("totalPages", new Integer(0));
			custInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			List<Map<String,Object>> custInfoList = customerSearchPopupMapper.getCustCtrtList(soId, custNm, today, lng, sidx, sort, start, end);
		
			custInfo.put("custInfoList", custInfoList);
			custInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			custInfo.put("totalPages", totalPages);
			custInfo.put("page", new Integer(page));
		}
		
		return custInfo;
		
	}




	
	
}
