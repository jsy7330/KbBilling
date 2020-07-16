package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeRule;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface RatingCodeRuleMapper {

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
	List<RatingCodeRule> getRatingCodeRuleList(
			@Param(value = "ratingCodeRule") RatingCodeRule ratingCodeRule
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
	int getRatingCodeRuleListCount(
			@Param(value = "ratingCodeRule") RatingCodeRule ratingCodeRule);

	/**
	 * Add RatingFactorUnit
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int addRatingCodeRule(
			@Param(value ="ratingCodeRule") RatingCodeRule ratingCodeRule);	

	/**
	 * Mod RatingFactorUnit
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int modRatingCodeRule(
			@Param(value ="ratingCodeRule") RatingCodeRule ratingCodeRule);	
	
	/**
	 * Delete RatingFactorUnit
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	int delRatingCodeRule(
			@Param(value ="ratingCodeRule") RatingCodeRule ratingCodeRule);	
}