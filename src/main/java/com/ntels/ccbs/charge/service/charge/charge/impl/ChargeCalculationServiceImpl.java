package com.ntels.ccbs.charge.service.charge.charge.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.charge.charge.RegularChargeJobVO;
import com.ntels.ccbs.charge.mapper.charge.charge.ChargeCalculationMapper;
import com.ntels.ccbs.charge.service.charge.charge.ChargeCalculationService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ChargeCalculationServiceImpl implements ChargeCalculationService{

	@Autowired
	private ChargeCalculationMapper chargeCalculationMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public int getRegularChargeJobCount(RegularChargeJobVO regularChargeJobVO) {
		return chargeCalculationMapper.getRegularChargeJobCount(regularChargeJobVO);
	}


	@Override
	public List<RegularChargeJobVO> getRegularChargeJobList(
			RegularChargeJobVO regularChargeJobVO) {

		List<RegularChargeJobVO> returnList = chargeCalculationMapper.getRegularChargeJobList(regularChargeJobVO);
		
		return returnList;
	}


	@Override
	public List<RegularChargeJobVO> getBatchLog(
			RegularChargeJobVO regularChargeJobVO) {
		
		List<RegularChargeJobVO> returnList = chargeCalculationMapper.getBatchLog(regularChargeJobVO);
		
		return returnList;
	}


	@Override
	public RegularChargeJobVO getClcWrkNo(RegularChargeJobVO regularChargeJobVO, String userId) {

		RegularChargeJobVO returnVo = chargeCalculationMapper.getClcWrkNo(regularChargeJobVO);
		if(returnVo == null || returnVo.getClcWrkNo().equals("") ){
			String clcWrkNo = sequenceService.createNewSequence(Consts.SEQ_CODE.CH_CLC_WRK_NO, 10);
			returnVo = new RegularChargeJobVO();
			
			regularChargeJobVO.setRegId(userId);
			regularChargeJobVO.setRegDate(DateUtil.sysdate());
			regularChargeJobVO.setClcWrkNo(clcWrkNo);
			returnVo.setClcWrkNo(clcWrkNo);
			
			chargeCalculationMapper.inserttblchClcMain(regularChargeJobVO);
		}
		
		return returnVo;
	}


	@Override
	public int updateBatPgmLog(RegularChargeJobVO regularChargeJobVO) {
		return chargeCalculationMapper.updateBatPgmLog(regularChargeJobVO);
	}
	

	@Override
	public Map<String, Object> getChargeList(String soId,List<Map<String, Object>> soAuthList, String sidx,String sord, int page, int rows, String today, String lng
			 ,String condBillYymm,String condClc,String condPymAcntId,String condCustId){
		
		Map<String,Object> chargeInfo = new HashMap<String,Object>();
		Integer totalCount = chargeCalculationMapper.getChargeListTotalCnt(soId,soAuthList,condBillYymm,condClc,condPymAcntId,condCustId);
		/*
		   	page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			chargeInfo.put("chargeList", new ArrayList<Map<String,Object>>());
			chargeInfo.put("totalCount", totalCount);
			chargeInfo.put("totalPages", new Integer(0));
			chargeInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			List<Map<String,Object>> chargeList = chargeCalculationMapper.getChargeList(soId,soAuthList,sidx,start, end,sord,today,lng,condBillYymm,condClc,condPymAcntId,condCustId);
			
			chargeInfo.put("chargeList", chargeList); 
			chargeInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			chargeInfo.put("totalPages", totalPages);
			chargeInfo.put("page", new Integer(page));
		}
		
		 
		return chargeInfo;
	}
	 
	
	
	
}
