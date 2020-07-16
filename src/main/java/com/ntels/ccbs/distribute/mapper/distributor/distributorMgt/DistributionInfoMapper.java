package com.ntels.ccbs.distribute.mapper.distributor.distributorMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.DistributionInfoVO;

@Component
public interface DistributionInfoMapper {

	List<DistributionInfoVO> distributionInfoList(
			@Param(value = "distributionInfoVO") DistributionInfoVO distributionInfoVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int distributionInfoCount(@Param(value = "distributionInfoVO") DistributionInfoVO distributionInfoVO);
	
	DistributionInfoVO selectDistributionInfo(@Param(value = "distributionInfoVO") DistributionInfoVO distributionInfoVO);
	
	int insertDistInfo(@Param(value = "distributionInfoVO") DistributionInfoVO distributionInfoVO);
	
	int updateOrgInfo(@Param(value = "distributionInfoVO") DistributionInfoVO distributionInfoVO);
	
	int updateOrgRelHist(@Param(value = "distributionInfoVO") DistributionInfoVO distributionInfoVO);
	
	int updateDistInfo(@Param(value = "distributionInfoVO") DistributionInfoVO distributionInfoVO);
	
	
	
	
}
