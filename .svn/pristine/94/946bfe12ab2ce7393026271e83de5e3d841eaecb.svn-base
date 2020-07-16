package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeGrp;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface UsageTypeGrpMapper {

	List<UsageTypeGrp> getUsageTypeList(
			  @Param(value = "usageTypeGrp") UsageTypeGrp usageTypeGrp
			, @Param(value = "start") int start 
			, @Param(value = "end") int end
			);
	
	List<UsageTypeGrp> getUsageTypeGrpList(
			  @Param(value = "usageTypeGrp") UsageTypeGrp usageTypeGrp
			, @Param(value = "start") int start 
			, @Param(value = "end") int end
			);
	
	int getUsageTypeGrpListCount(
			@Param(value = "usageTypeGrp") UsageTypeGrp usageTypeGrp);
	
	int addUsageTypeGrp(
			@Param(value = "usageTypeGrp") UsageTypeGrp usageTypeGrp);
	
	int delUsageTypeGrp(
			@Param(value = "usageTypeGrp") UsageTypeGrp usageTypeGrp);
	
}