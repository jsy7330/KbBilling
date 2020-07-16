package com.ntels.ccbs.product.service.refInfo.ratingRefInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.ManageRatingDef;


public interface ManageRatingDefService {

	public List<ManageRatingDef> getChargeList(ManageRatingDef manageRatingDef, int page, int perPage);
	public int getChargeListCount(ManageRatingDef manageRatingDef);
	public int addCharge(ManageRatingDef manageRatingDef);
	public int modCharge(ManageRatingDef manageRatingDef);
	public int delCharge(ManageRatingDef manageRatingDef);
	List<Map<String, Object>> manageRatingDefListExcel(
			ManageRatingDef manageRatingDef,
			String lngTyp,
			String chrgCd,
			String description,
			String usgTyp);
}