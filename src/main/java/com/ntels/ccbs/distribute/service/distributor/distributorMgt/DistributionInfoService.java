package com.ntels.ccbs.distribute.service.distributor.distributorMgt;

import java.util.List;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.DistributionInfoVO;

public interface DistributionInfoService {

	public List<DistributionInfoVO> distributionInfoList(DistributionInfoVO distributionInfoVO,	int page, int perPage);	
	
	public int distributionInfoCount(DistributionInfoVO distributionInfoVO);
	
	public DistributionInfoVO selectDistributionInfo(DistributionInfoVO distributionInfoVO);
	
	public int insertDistributionInfo(DistributionInfoVO distributionInfoVO);
	
	public int updateDistributionInfo(DistributionInfoVO distributionInfoVO);
	
}
