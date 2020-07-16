package com.ntels.ccbs.distribute.mapper.logistics.referenceInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.DistributionUnitPriceVO;

@Component
public interface DistributionUnitPriceMapper {

	List<DistributionUnitPriceVO> distributionUnitPriceList(
			@Param(value = "distributionUnitPriceVO") DistributionUnitPriceVO distributionUnitPriceVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int distributionUnitPriceCount(@Param(value = "distributionUnitPriceVO") DistributionUnitPriceVO distributionUnitPriceVO);
	
	List<DistributionUnitPriceVO> dstrbUtPrcDtlList(
			@Param(value = "distributionUnitPriceVO") DistributionUnitPriceVO distributionUnitPriceVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int dstrbUtPrcDtlCount(@Param(value = "distributionUnitPriceVO") DistributionUnitPriceVO distributionUnitPriceVO);
	
	int insertDstrbUtPrcDtl(@Param(value = "distributionUnitPriceVO") DistributionUnitPriceVO distributionUnitPriceVO);
	
	int updateDstrbUtPrcDtlEndDt(@Param(value = "distributionUnitPriceVO") DistributionUnitPriceVO distributionUnitPriceVO);
	
	int updateDstrbUtPrcDtl(@Param(value = "distributionUnitPriceVO") DistributionUnitPriceVO distributionUnitPriceVO);
	
	int deleteDstrbUtPrcDtl(@Param(value = "distributionUnitPriceVO") DistributionUnitPriceVO distributionUnitPriceVO);
	
}
