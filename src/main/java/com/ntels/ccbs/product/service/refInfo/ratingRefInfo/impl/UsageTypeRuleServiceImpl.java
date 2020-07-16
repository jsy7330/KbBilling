package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeRule;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.UsageTypeRuleMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.UsageTypeRuleService;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;

@Service
public class UsageTypeRuleServiceImpl implements UsageTypeRuleService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private UsageTypeRuleMapper usageTypeRuleMapper;

	@Override
	public List<UsageTypeRule> getUsageTypeRuleList(UsageTypeRule usageTypeRule, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return usageTypeRuleMapper.getUsageTypeRuleList(usageTypeRule, start,  end);
	}

	@Override
	public int getUsageTypeRuleListCount(UsageTypeRule usageTypeRule) {
		return usageTypeRuleMapper.getUsageTypeRuleListCount(usageTypeRule);
	}
	

	@Override
	public int addUsageTypeRule(UsageTypeRule usageTypeRule){
		return usageTypeRuleMapper.addUsageTypeRule(usageTypeRule);
	}	

	@Override
	public int modUsageTypeRule(UsageTypeRule usageTypeRule){
		return usageTypeRuleMapper.modUsageTypeRule(usageTypeRule);
	}

	
	@Override
	public int delUsageTypeRule(UsageTypeRule usageTypeRule){
		return usageTypeRuleMapper.delUsageTypeRule(usageTypeRule);
	}
	
	public List<CommonCodeVO> getDataNmList() {
		return usageTypeRuleMapper.getDataNmList();
	}

	@Override
	public List<CommonCodeVO> getSeqNoList() {
		return usageTypeRuleMapper.getSeqNoList();
	}
}
