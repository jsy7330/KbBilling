package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.ManageRatingDef;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.ManageRatingDefMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.ManageRatingDefService;

@Service
public class ManageRatingDefServiceImpl implements ManageRatingDefService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private ManageRatingDefMapper manageRatingDefMapper;

	@Override
	public List<ManageRatingDef> getChargeList(ManageRatingDef manageRatingDef, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return manageRatingDefMapper.getChargeList(manageRatingDef, start,  end);
	}

	@Override
	public int getChargeListCount(ManageRatingDef manageRatingDef) {
		return manageRatingDefMapper.getChargeListCount(manageRatingDef);
	}

	@Override
	public int addCharge(ManageRatingDef manageRatingDef){
		return manageRatingDefMapper.addCharge(manageRatingDef);
	}	

	@Override
	public int modCharge(ManageRatingDef manageRatingDef){
		return manageRatingDefMapper.modCharge(manageRatingDef);
	}

	@Override
	public int delCharge(ManageRatingDef manageRatingDef){
		return manageRatingDefMapper.delCharge(manageRatingDef);
	}
//	public List<Map<String,Object>> manageRatingDefListExcel(ManageRatingDef manageRatingDef, String lngTyp) {
//		return manageRatingDefMapper.manageRatingDefListExcel(manageRatingDef, lngTyp);
//	}

	@Override
	public List<Map<String, Object>> manageRatingDefListExcel(
			ManageRatingDef manageRatingDef, String lngTyp, String chrgCd,
			String description, String usgTyp) {
		return manageRatingDefMapper.manageRatingDefListExcel(manageRatingDef, lngTyp, chrgCd, description, usgTyp);
	}
}
