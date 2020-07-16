package com.ntels.ccbs.distribute.mapper.distributor.distributorMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.CollateralVO;

@Component
public interface CollateralMapper {

	List<CollateralVO> collateralList(
			@Param(value = "collateralVO") CollateralVO collateralVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int collateralCount(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int insertCollInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	CollateralVO selectOrgInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	CollateralVO selectCollInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int loanInfoCount(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int insertLoanInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int updateLoanInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int insertLoanDetailInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int updateLoanInfo2(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int updateCollInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int updateLoanDetailInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int deleteCollInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	int deleteLoanDetailInfo(@Param(value = "collateralVO") CollateralVO collateralVO);
	
	
	
	
	
	
}
