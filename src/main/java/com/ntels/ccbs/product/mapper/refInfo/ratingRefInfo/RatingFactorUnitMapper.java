package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingFactorUnit;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface RatingFactorUnitMapper {

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
	List<RatingFactorUnit> getRatingFactorUnitList(
			@Param(value = "ratingFactorUnit") RatingFactorUnit ratingFactorUnit
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
	int getRatingFactorUnitListCount(
			@Param(value = "ratingFactorUnit") RatingFactorUnit ratingFactorUnit);

	/**
	 * Add RatingFactorUnit
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int addRatingFactorUnit(
			@Param(value ="ratingFactorUnit") RatingFactorUnit ratingFactorUnit);
	
	/**
	 * Add RatingFactorUnit
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int modRatingFactorUnit(
			@Param(value ="ratingFactorUnit") RatingFactorUnit ratingFactorUnit);	


	/**
	 * Delete RatingFactorUnit
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	int delRatingFactorUnit(
			@Param(value ="ratingFactorUnit") RatingFactorUnit ratingFactorUnit);	
}