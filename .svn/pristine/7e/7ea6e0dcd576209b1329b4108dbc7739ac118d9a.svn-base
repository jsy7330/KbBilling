package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingFactorUnit;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.RatingFactorUnitMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingFactorUnitService;
	
@Service
public class RatingFactorUnitServiceImpl implements RatingFactorUnitService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** RatingFactorUnitMapper Autowired. */
	@Autowired
	private RatingFactorUnitMapper ratingFactorUnitMapper;

	@Override
	public List<RatingFactorUnit> getRatingFactorUnitList(RatingFactorUnit ratingFactorUnit, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return ratingFactorUnitMapper.getRatingFactorUnitList(ratingFactorUnit, start,  end);
	}

	@Override
	public int getRatingFactorUnitListCount(RatingFactorUnit ratingFactorUnit) {
		// TODO Auto-generated method stub
		return ratingFactorUnitMapper.getRatingFactorUnitListCount(ratingFactorUnit);
	}
	
	@Override
	public int addRatingFactorUnit(RatingFactorUnit ratingFactorUnit) {
		return ratingFactorUnitMapper.addRatingFactorUnit(ratingFactorUnit);
	}
	
	@Override
	public int modRatingFactorUnit(RatingFactorUnit ratingFactorUnit) {
		return ratingFactorUnitMapper.modRatingFactorUnit(ratingFactorUnit);
	}

	@Override
	public int delRatingFactorUnit(RatingFactorUnit ratingFactorUnit) {
		return ratingFactorUnitMapper.delRatingFactorUnit(ratingFactorUnit);
	}
}
