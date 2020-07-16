package com.ntels.ccbs.charge.mapper.nonpayment.nonpayment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.nonpayment.nonpayment.DunningInfoItemVO;

public interface DunningInfoItemMapper {

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
	List<DunningInfoItemVO> dunningCriteriaList(
		@Param(value ="dunningInfo") DunningInfoItemVO dunningInfo
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );

	int dunningCriteriaCount(@Param(value ="dunningInfo") DunningInfoItemVO dunningInfo);
	
	DunningInfoItemVO getDunningCriteria(@Param(value = "dunningInfo") DunningInfoItemVO dunningInfo);
	
	int insertDunningInfo(@Param(value = "dunningInfo") DunningInfoItemVO dunningInfo);
	
	int updateDunningInfo(@Param(value = "dunningInfo") DunningInfoItemVO dunningInfo);
}
