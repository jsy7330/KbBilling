package com.ntels.ccbs.charge.service.charge.charge.impl;

import java.util.List;

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

	
	
	
}
