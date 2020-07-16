package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.AllowanceDiscount;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface AllowanceDiscountMapper {

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
	List<AllowanceDiscount> getAllowanceDiscountList(
			@Param(value = "allowanceDiscount") AllowanceDiscount allownaceDiscount
			, @Param(value="start")int start
			, @Param(value="end") int end
			);		

	/**
	 * Add 
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	int addAllowanceDiscount(
			@Param(value ="allowanceDiscount") AllowanceDiscount allownaceDiscount);	

	/**
	 * Delete RatingFactorUnit
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	int modAllowanceDiscount(
			@Param(value ="allowanceDiscount") AllowanceDiscount allownaceDiscount);	

	int delAllowanceDiscount(
			@Param(value ="allowanceDiscount") AllowanceDiscount allownaceDiscount);	
	
	List<CommonCodeVO> getCommonCodeList();
	List<CommonCodeVO> getChrgCdList();
}