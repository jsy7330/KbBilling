package com.ntels.ccbs.product.service.refInfo.ratingRefInfo;

import java.util.List;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingFactorUnit;


public interface RatingFactorUnitService {

	public List<RatingFactorUnit> getRatingFactorUnitList(RatingFactorUnit ratingFactorUnit, int page, int perPage);
	public int getRatingFactorUnitListCount(RatingFactorUnit ratingFactorUnit);
	public int addRatingFactorUnit(RatingFactorUnit ratingFactorUnit);
	public int modRatingFactorUnit(RatingFactorUnit ratingFactorUnit);
	public int delRatingFactorUnit(RatingFactorUnit ratingFactorUnit);
}