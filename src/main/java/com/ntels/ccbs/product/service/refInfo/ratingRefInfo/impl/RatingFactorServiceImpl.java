package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingFactor;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.RatingFactorMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingFactorService;

@Service
public class RatingFactorServiceImpl implements RatingFactorService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private RatingFactorMapper ratingFactorMapper;

	@Override
	public List<RatingFactor> getRatingFactorList(RatingFactor ratingFactor, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return ratingFactorMapper.getRatingFactorList(ratingFactor, start,  end);
	}

	@Override
	public int getRatingFactorListCount(RatingFactor ratingFactor) {
		// TODO Auto-generated method stub
		return ratingFactorMapper.getRatingFactorListCount(ratingFactor);
	}
	
	@Override
	public int addRatingFactor(RatingFactor ratingFactor) {
		return ratingFactorMapper.addRatingFactor(ratingFactor);
	}
	
	public int modRatingFactor(RatingFactor ratingFactor) {
		return ratingFactorMapper.modRatingFactor(ratingFactor);
	}

	@Override
	public int delRatingFactor(RatingFactor ratingFactor) {
		return ratingFactorMapper.delRatingFactor(ratingFactor);
	}
}
