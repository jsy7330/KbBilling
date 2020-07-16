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
import com.ntels.ccbs.system.domain.common.common.PymAcntSearchVO;
import com.ntels.ccbs.system.domain.common.common.PymAcntVO;
import com.ntels.ccbs.system.mapper.common.common.PymAcntSearchPopupMapper;
import com.ntels.ccbs.system.service.common.common.PymAcntSearchPopupService;
import com.ntels.nisf.util.StringUtil;

@Service
public class PymAcntSearchPopupServiceImpl implements PymAcntSearchPopupService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PymAcntSearchPopupMapper pymAcntSearchPopupMapper;

	@Override
	public Map<String, Object> getPymAcntInfoList(String soId, String custNm,
	        String custId, String ctrtId, String svcTelNo, String pymAcntId,
	        List<Map<String, Object>> soAuthList, String today, String lng,
	        String sidx, String sort, int page, int rows) {
		
		Map<String,Object> custInfo = new HashMap<String,Object>();
		
		boolean hasCtrtKey = false;
		
		if(!StringUtil.isEmpty(ctrtId) ||
				!StringUtil.isEmpty(svcTelNo)){
			hasCtrtKey = true;
		}
		
		Integer totalCount = new Integer(0);
		
		if(!hasCtrtKey){
			totalCount = pymAcntSearchPopupMapper.getPymAcntInfoTotalCnt(soId, custNm, custId, pymAcntId, soAuthList);
		}else{
			totalCount = pymAcntSearchPopupMapper.getPymAcntInfoWithCtrtTotalCnt(soId, custNm, custId, pymAcntId, ctrtId, svcTelNo, soAuthList);
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
			
			List<PymAcntSearchVO> pymAcntInfoList;
			if(!hasCtrtKey){
				pymAcntInfoList = pymAcntSearchPopupMapper.getPymAcntInfoList(soId, custNm, custId, pymAcntId, soAuthList,  today, lng, sidx, sort, start, end);
			}else{
				pymAcntInfoList = pymAcntSearchPopupMapper.getPymAcntInfoWithCtrtList(soId, custNm, custId, pymAcntId, ctrtId, svcTelNo, soAuthList, today, lng, sidx, sort, start, end);
			}
			custInfo.put("pymAcntInfoList", pymAcntInfoList);
			custInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			custInfo.put("totalPages", totalPages);
			custInfo.put("page", new Integer(page));
		}
		
		return custInfo;
	}

	@Override
	public Map<String, Object> getPymAcntList(PymAcntVO pymAcntVO) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();

		int totalCount = pymAcntSearchPopupMapper.getPymAcntListCount(pymAcntVO);
			
		if(totalCount < 1){
			returnMap.put("totalCount", totalCount);
			returnMap.put("totalPages", new Integer(0));
			returnMap.put("page", new Integer(1));
		}else{
			int endIndex = pymAcntVO.getRows();
			int startIndex = (pymAcntVO.getPage() -1) * endIndex;
			
			pymAcntVO.setEnd(String.valueOf(endIndex));
			pymAcntVO.setStart(String.valueOf(startIndex));
			
			List<PymAcntVO> pymAcntList = pymAcntSearchPopupMapper.getPymAcntList(pymAcntVO);
			
			returnMap.put("pymAcntList", pymAcntList);
			returnMap.put("totalCount", totalCount);

			double totalPages = Math.ceil((double)totalCount / (double)endIndex) ;
			
			returnMap.put("totalPages", totalPages);
			returnMap.put("page", pymAcntVO.getPage());
			
		}
		
		return returnMap;
	}


	


	
	
}
