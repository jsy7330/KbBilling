package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeMap;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface RatingCodeMapMapper {

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
	List<RatingCodeMap> getRatingCodeMapList(
			@Param(value = "ratingCodeMap") RatingCodeMap ratingCodeMap
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
	int getRatingCodeMapListCount(
			@Param(value = "ratingCodeMap") RatingCodeMap ratingCodeMap);

	/**
	 * Add RatingFactorUnit
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int addRatingCodeMap(
			@Param(value ="ratingCodeMap") RatingCodeMap ratingCodeMap);	
	
	/**
	 * Mod RatingFactorUnit
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int modRatingCodeMap(
			@Param(value ="ratingCodeMap") RatingCodeMap ratingCodeMap);	


	/**
	 * Delete RatingFactorUnit
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	int delRatingCodeMap(
			@Param(value ="ratingCodeMap") RatingCodeMap ratingCodeMap);	
	
	List<CommonCodeVO> getChrgCdList();

}