package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.ManageRatingDef;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface ManageRatingDefMapper {

	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attribute
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	List<ManageRatingDef> getChargeList(
			@Param(value = "manageRatingDef") ManageRatingDef manageRatingDef
			, @Param(value="start")int start
			, @Param(value="end") int end
			);		

	/**
	 * Count.
	 *
	 * @param attribute
	 * @return the int
	 * 
	 * 설명 : 속성 목록수
	 */
	int getChargeListCount(
			@Param(value = "manageRatingDef") ManageRatingDef manageRatingDef);

	/**
	 * Gets the attribute.
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int addCharge(
			@Param(value ="manageRatingDef") ManageRatingDef manageRatingDef);	

	/**
	 * commList.
	 *
	 * @param  commonGrpNm
	 * @return the list
	 * 
	 * 설명 : 공통코드 조회
	 */
	int modCharge(
			@Param(value ="manageRatingDef") ManageRatingDef manageRatingDef);	

	/**
	 * insert.
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	int delCharge(
			@Param(value ="manageRatingDef") ManageRatingDef manageRatingDef);	

	/**
	 * insert.
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	
//	List<Map<String,Object>> manageRatingDefListExcel(
//			@Param(value ="manageRatingDef") ManageRatingDef manageRatingDef,
//			@Param(value="lngTyp") String lngTyp
//	);

	List<Map<String,Object>> manageRatingDefListExcel(
			@Param(value ="manageRatingDef") ManageRatingDef manageRatingDef,
			@Param(value="lngTyp") String lngTyp,
			@Param(value="chrgCd") String chrgCd,
			@Param(value="description") String description,
			@Param(value="usgTyp") String usgTyp
	);
}