package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeMap;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.RatingCodeMapMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingCodeMapService;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
	
@Service
public class RatingCodeMapServiceImpl implements RatingCodeMapService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** RatingFactorUnitMapper Autowired. */
	@Autowired
	private RatingCodeMapMapper ratingCodeMapMapper;

	@Override
	public List<RatingCodeMap> getRatingCodeMapList(RatingCodeMap ratingCodeMap, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return ratingCodeMapMapper.getRatingCodeMapList(ratingCodeMap, start,  end);
	}

	@Override
	public int getRatingCodeMapListCount(RatingCodeMap ratingCodeMap) {
		// TODO Auto-generated method stub
		return ratingCodeMapMapper.getRatingCodeMapListCount(ratingCodeMap);
	}
	
	@Override
	public int addRatingCodeMap(RatingCodeMap ratingCodeMap) {
		return ratingCodeMapMapper.addRatingCodeMap(ratingCodeMap);
	}
	
	@Override
	public int modRatingCodeMap(RatingCodeMap ratingCodeMap) {
		return ratingCodeMapMapper.modRatingCodeMap(ratingCodeMap);
	}

	@Override
	public int delRatingCodeMap(RatingCodeMap ratingCodeMap) {
		return ratingCodeMapMapper.delRatingCodeMap(ratingCodeMap);
	}

	@Override
	public List<CommonCodeVO> getChrgCdList() {
		return ratingCodeMapMapper.getChrgCdList();
	}
	
}
