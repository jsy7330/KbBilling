package com.ntels.ccbs.product.service.refInfo.ratingRefInfo;

import java.util.List;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeGrp;


public interface RatingCodeGrpService {

	public List<RatingCodeGrp> getRatingCodeGrpList(RatingCodeGrp ratingCodeGrp, int page, int perPage);
	public List<RatingCodeGrp> getRatingDescription(RatingCodeGrp ratingCodeGrp, int page, int perPage);
	public int getRatingCodeGrpListCount(RatingCodeGrp ratingCodeGrp);
	public int addRatingCodeGrp(RatingCodeGrp ratingCodeGrp);
	public int delRatingCodeGrp(RatingCodeGrp ratingCodeGrp);
}