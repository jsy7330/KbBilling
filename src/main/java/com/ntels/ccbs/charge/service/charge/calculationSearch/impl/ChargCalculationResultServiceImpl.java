package com.ntels.ccbs.charge.service.charge.calculationSearch.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.charge.calculationSearch.ChargCalculationResultVO;
import com.ntels.ccbs.charge.mapper.charge.calculationSearch.ChargCalculationResultMapper;
import com.ntels.ccbs.charge.service.charge.calculationSearch.ChargCalculationResultService;

@Service
public class ChargCalculationResultServiceImpl implements ChargCalculationResultService{

	@Autowired
	ChargCalculationResultMapper chargCalculationResultMapper;

	@Override
	public Map<String, Object> getChargePersonCountList(String soId, List<Map<String, Object>> soAuthList,
			String billYymm, String sidx, String sord, int page, int rows, String lng) {
		// TODO Auto-generated method stub
		Map<String, Object> chargPersonCntInfo = new HashMap<String, Object>();
		int totalCount = chargCalculationResultMapper.totalCount(soId, soAuthList, billYymm);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		
		if(totalCount == 0){
			chargPersonCntInfo.put("workGrpList", new ArrayList<Map<String,Object>>());
			chargPersonCntInfo.put("totalCount", totalCount);
			chargPersonCntInfo.put("totalPages", new Integer(0));
			chargPersonCntInfo.put("page", new Integer(1));
		}else {
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<Map<String,Object>> charPersonCntList = chargCalculationResultMapper.getChargePersonCountList(soId, soAuthList, billYymm, sidx, sord, start, end, lng);
			
			chargPersonCntInfo.put("charPersonCntList", charPersonCntList);
			chargPersonCntInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil((float)totalCount / (float)rows));
			chargPersonCntInfo.put("totalPages", totalPages);
			chargPersonCntInfo.put("page", new Integer(page));
		}
		
		return chargPersonCntInfo;
	}
	
	
}
