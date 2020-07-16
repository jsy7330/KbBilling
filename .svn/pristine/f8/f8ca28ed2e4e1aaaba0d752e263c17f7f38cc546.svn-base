package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeRule;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.RatingCodeRuleMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingCodeRuleService;
	
@Service
public class RatingCodeRuleServiceImpl implements RatingCodeRuleService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** RatingFactorUnitMapper Autowired. */
	@Autowired
	private RatingCodeRuleMapper ratingCodeRuleMapper;

	@Override
	public List<RatingCodeRule> getRatingCodeRuleList(RatingCodeRule ratingCodeRule, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return ratingCodeRuleMapper.getRatingCodeRuleList(ratingCodeRule, start,  end);
	}

	@Override
	public int getRatingCodeRuleListCount(RatingCodeRule ratingCodeRule) {
		// TODO Auto-generated method stub
		return ratingCodeRuleMapper.getRatingCodeRuleListCount(ratingCodeRule);
	}
	
	@Override
	public int addRatingCodeRule(RatingCodeRule ratingCodeRule) {
		return ratingCodeRuleMapper.addRatingCodeRule(ratingCodeRule);
	}

	@Override
	public int modRatingCodeRule(RatingCodeRule ratingCodeRule) {
		return ratingCodeRuleMapper.modRatingCodeRule(ratingCodeRule);
	}
	
	@Override
	public int delRatingCodeRule(RatingCodeRule ratingCodeRule) {
		return ratingCodeRuleMapper.delRatingCodeRule(ratingCodeRule);
	}

}
