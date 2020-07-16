package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeMap;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface UsageTypeMapMapper {

	List<UsageTypeMap> getUsageTypeMapList(
			@Param(value = "usageTypeMap") UsageTypeMap usageTypeMap
			, @Param(value="start")int start
			, @Param(value="end") int end
			);		


	int getUsageTypeMapListCount(
			@Param(value = "usageTypeMap") UsageTypeMap usageTypeMap);
	
	int addUsageTypeMap(
			@Param(value ="usageTypeMap") UsageTypeMap usageTypeMap);	

	int modUsageTypeMap(
			@Param(value ="usageTypeMap") UsageTypeMap usageTypeMap);	
	
	int delUsageTypeMap(
			@Param(value ="usageTypeMap") UsageTypeMap usageTypeMap);
	
	List<CommonCodeVO> getDataNmList();
	List<CommonCodeVO> getSeqNoList();

}