package com.ntels.ccbs.charge.service.nonpayment.nonpayment.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.nonpayment.nonpayment.DunningInfoItemVO;
import com.ntels.ccbs.charge.mapper.nonpayment.nonpayment.DunningInfoItemMapper;
import com.ntels.ccbs.charge.service.nonpayment.nonpayment.DunningCriteriaService;

@Service
public class DunningCriteriaServiceImpl implements DunningCriteriaService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private DunningInfoItemMapper dunningInfoItemMapper;

	@Override
	public List<DunningInfoItemVO> dunningCriteriaList(DunningInfoItemVO dunningInfo, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return dunningInfoItemMapper.dunningCriteriaList(dunningInfo, start, end);
	}
	
	@Override
	public int dunningCriteriaCount(DunningInfoItemVO dunningInfo) {
		return dunningInfoItemMapper.dunningCriteriaCount(dunningInfo);
	}
	
	@Override
	public DunningInfoItemVO getDunningCriteria(DunningInfoItemVO dunningInfo) {
		return dunningInfoItemMapper.getDunningCriteria(dunningInfo);
	}
	
	@Override
	public int insertDunningInfo(DunningInfoItemVO dunningInfo) {

		int count = dunningInfoItemMapper.insertDunningInfo(dunningInfo);
		
		return count;
	}

	@Override
	public int updateDunningInfo(DunningInfoItemVO dunningInfo) {
		
		int count = dunningInfoItemMapper.updateDunningInfo(dunningInfo);
		
		return count;
	}
}
