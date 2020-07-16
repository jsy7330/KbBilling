package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingFactor;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface RatingFactorMapper {

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
	List<RatingFactor> getRatingFactorList(
			@Param(value = "ratingFactor") RatingFactor ratingFactor
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
	int getRatingFactorListCount(
			@Param(value = "ratingFactor") RatingFactor ratingFactor);

	/**
	 * Gets the attribute.
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int addRatingFactor(
			@Param(value ="ratingFactor") RatingFactor ratingFactor);	
	
	/**
	 * Gets the attribute.
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int modRatingFactor(
			@Param(value ="ratingFactor") RatingFactor ratingFactor);

	/**
	 * insert.
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	int delRatingFactor(
			@Param(value ="ratingFactor") RatingFactor ratingFactor);	
}