package com.ntels.ccbs.charge.service.nonpayment.nonpayment.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.nonpayment.nonpayment.DunningExemptionVO;
import com.ntels.ccbs.charge.mapper.nonpayment.nonpayment.DunningExemptionMapper;
import com.ntels.ccbs.charge.service.nonpayment.nonpayment.DunningExemptionService;

@Service
public class DunningExemptionServiceImpl implements DunningExemptionService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private DunningExemptionMapper dunningExemptionMapper;

	@Override
	public List<DunningExemptionVO> dunningExemptionList(DunningExemptionVO dunningInfo, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return dunningExemptionMapper.dunningExemptionList(dunningInfo, start, end);
	}
	
	@Override
	public int dunningExemptionCount(DunningExemptionVO dunningInfo) {
		return dunningExemptionMapper.dunningExemptionCount(dunningInfo);
	}
	
	@Override
	public DunningExemptionVO getDunningExemption(DunningExemptionVO dunningInfo) {
		return dunningExemptionMapper.getDunningExemption(dunningInfo);
	}
	
	@Override
	public int insertDunningExemption(DunningExemptionVO dunningInfo) {

		int count = dunningExemptionMapper.insertDunningExemption(dunningInfo);
		
		return count;
	}

	@Override
	public int updateDunningExemption(DunningExemptionVO dunningInfo) {
		
		int count = dunningExemptionMapper.updateDunningExemption(dunningInfo);
		
		return count;
	}
	
	@Override
	public int updateDunningExemptionList(DunningExemptionVO dunningInfo) {
		
		int count = dunningExemptionMapper.updateDunningExemptionList(dunningInfo);
		
		return count;
	}
	
	@Override
	public int deleteDunningExemptionList(DunningExemptionVO dunningInfo) {
		
		int count = dunningExemptionMapper.deleteDunningExemptionList(dunningInfo);
		
		return count;
	}
}
