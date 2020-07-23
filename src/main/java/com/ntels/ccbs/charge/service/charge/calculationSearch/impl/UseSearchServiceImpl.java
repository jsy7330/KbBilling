package com.ntels.ccbs.charge.service.charge.calculationSearch.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.mapper.charge.calculationSearch.UseSearchMapper;
import com.ntels.ccbs.charge.service.charge.calculationSearch.UseSearchService;

@Service
public class UseSearchServiceImpl implements UseSearchService{
	
	@Autowired
	private UseSearchMapper useSearchMapper;

	@Override
	public Map<String, Object> getUsgListByCtrt(String soId, String ctrtId, String useYymm, String orderTp, String useStDt,
			String useEdDt, String sidx, String sord, int page, int rows, String lng) {
		// TODO Auto-generated method stub
		
		Map<String, Object> UsgListInfo = new HashMap<String, Object>();
		Integer totalCount = useSearchMapper.totalCount(soId, ctrtId, useYymm, orderTp, useStDt, useEdDt);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		
		if(totalCount.intValue() == 0){
			UsgListInfo.put("usgListByCtrtInfo", new ArrayList<Map<String,Object>>());
			UsgListInfo.put("totalCount", totalCount);
			UsgListInfo.put("totalPages", new Integer(0));
			UsgListInfo.put("page", new Integer(1));
		}else {
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<Map<String,Object>> usgListByCtrtInfo = useSearchMapper.getUsgListByCtrt(soId, ctrtId, useYymm, orderTp, useStDt, useEdDt, sidx, sord, start, end, lng);
			
			UsgListInfo.put("usgListByCtrtInfo", usgListByCtrtInfo);
			UsgListInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			UsgListInfo.put("totalPages", totalPages);
			UsgListInfo.put("page", new Integer(page));
		}
		
		return UsgListInfo;
	}

	@Override
	public List<Map<String, Object>> listExcel(String soId, String ctrtId, String useYymm, String orderTp,
			String useStDt, String useEdDt, String lng) {
		// TODO Auto-generated method stub
		return useSearchMapper.listExcel(soId, ctrtId, useYymm, orderTp, useStDt, useEdDt);
	}
	
	
}
