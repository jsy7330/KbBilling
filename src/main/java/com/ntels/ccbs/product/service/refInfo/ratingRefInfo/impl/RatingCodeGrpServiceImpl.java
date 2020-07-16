package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeGrp;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.RatingCodeGrpMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingCodeGrpService;
	
@Service
public class RatingCodeGrpServiceImpl implements RatingCodeGrpService {
	
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** RatingFactorUnitMapper Autowired. */
	@Autowired
	private RatingCodeGrpMapper ratingCodeGrpMapper;

	@Override
	public List<RatingCodeGrp> getRatingCodeGrpList(
			RatingCodeGrp ratingCodeGrp, int page, int perPage) {
		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return ratingCodeGrpMapper.getRatingCodeGrpList(ratingCodeGrp, start, end);
	}

	@Override
	public List<RatingCodeGrp> getRatingDescription(
			RatingCodeGrp ratingCodeGrp, int page, int perPage) {

		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;
		
		return ratingCodeGrpMapper.getRatingDescription(ratingCodeGrp, start, end);
	}
	
	@Override
	public int getRatingCodeGrpListCount(RatingCodeGrp ratingCodeGrp) {
		
		return ratingCodeGrpMapper.getRatingCodeGrpListCount(ratingCodeGrp);
	}

	@Override
	public int addRatingCodeGrp(RatingCodeGrp ratingCodeGrp) {
		for( Map<String, Object> updateInfo : ratingCodeGrp.getUpdateSetValList() ) {
			
			ratingCodeGrp.setInsertUsgTyp((String)updateInfo.get("usgTyp"));
			ratingCodeGrp.setInsertChrgCd((String)updateInfo.get("chrgCd"));
			ratingCodeGrp.setInsertChrgGrpCd((String)updateInfo.get("chrgGrpCd"));
			ratingCodeGrp.setInsertEffDt((String)updateInfo.get("effDt"));
			ratingCodeGrp.setInsertExpDt((String)updateInfo.get("expDt"));
			ratingCodeGrp.setInsertLmtInclude((String)updateInfo.get("lmtInclude"));
			ratingCodeGrp.setSearchChrgCd(ratingCodeGrp.getInsertChrgCd());
			ratingCodeGrp.setSearchUsgTyp(ratingCodeGrp.getInsertUsgTyp());
			ratingCodeGrp.setSearchEffDt(ratingCodeGrp.getInsertEffDt());
			ratingCodeGrp.setSearchChrgGrpCd(ratingCodeGrp.getInsertChrgGrpCd());
			
			if( ratingCodeGrpMapper.getRatingCodeGrpListCount(ratingCodeGrp) == 0 ) {
				ratingCodeGrpMapper.addRatingCodeGrp(ratingCodeGrp);
			}
			else {
				ratingCodeGrpMapper.modRatingCodeGrp(ratingCodeGrp);
			}
		}

		return 1;
	}

	@Override
	public int delRatingCodeGrp(RatingCodeGrp ratingCodeGrp) {

		return ratingCodeGrpMapper.delRatingCodeGrp(ratingCodeGrp);
	}
}