package com.ntels.ccbs.charge.mapper.nonpayment.nonpayment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.nonpayment.nonpayment.DunningExemptionVO;

public interface DunningExemptionMapper {

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
	List<DunningExemptionVO> dunningExemptionList(
		@Param(value ="dunningInfo") DunningExemptionVO dunningInfo
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );

	int dunningExemptionCount(@Param(value ="dunningInfo") DunningExemptionVO dunningInfo);
	
	DunningExemptionVO getDunningExemption(@Param(value = "dunningInfo") DunningExemptionVO dunningInfo);
	
	int insertDunningExemption(@Param(value = "dunningInfo") DunningExemptionVO dunningInfo);
	
	int updateDunningExemption(@Param(value = "dunningInfo") DunningExemptionVO dunningInfo);
	
	int updateDunningExemptionList(@Param(value = "dunningInfo") DunningExemptionVO dunningInfo);
	
	int deleteDunningExemptionList(@Param(value = "dunningInfo") DunningExemptionVO dunningInfo);
}
