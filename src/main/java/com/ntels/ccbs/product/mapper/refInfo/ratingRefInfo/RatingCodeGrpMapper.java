package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeGrp;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface RatingCodeGrpMapper {

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
	List<RatingCodeGrp> getRatingCodeGrpList(
			@Param(value = "ratingCodeGrp") RatingCodeGrp ratingCodeGrp
			, @Param(value="start")int start
			, @Param(value="end") int end
			);		

	List<RatingCodeGrp> getRatingDescription(
			@Param(value = "ratingCodeGrp") RatingCodeGrp ratingCodeGrp
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
	int getRatingCodeGrpListCount(
			@Param(value = "ratingCodeGrp") RatingCodeGrp ratingCodeGrp);

	
	/**
	 * Add RatingFactorUnit
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int addRatingCodeGrp(
			@Param(value ="ratingCodeGrp") RatingCodeGrp ratingCodeGrp);	
	
	int modRatingCodeGrp(
			@Param(value ="ratingCodeGrp") RatingCodeGrp ratingCodeGrp);
	
	/**
	 * Delete RatingFactorUnit
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	int delRatingCodeGrp(
			@Param(value ="ratingCodeGrp") RatingCodeGrp ratingCodeGrp);	
}