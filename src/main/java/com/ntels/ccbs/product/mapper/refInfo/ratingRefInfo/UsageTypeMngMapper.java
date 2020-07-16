package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeMng;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface UsageTypeMngMapper {


	List<UsageTypeMng> getUsageTypeMngList(
			@Param(value = "usageTypeMng") UsageTypeMng usageTypeMng
			, @Param(value="start")int start
			, @Param(value="end") int end
			);		

	int getUsageTypeMngRepCheckCount(
			@Param(value = "usageTypeMng") UsageTypeMng usageTypeMng);
	
	int getUsageTypeMngListCount(
			@Param(value = "usageTypeMng") UsageTypeMng usageTypeMng);

	int addUsageTypeMng(
			@Param(value ="usageTypeMng") UsageTypeMng usageTypeMng);	

	int modUsageTypeMng(
			@Param(value ="usageTypeMng") UsageTypeMng usageTypeMng);	
	
	int delUsageTypeMng(
			@Param(value ="usageTypeMng") UsageTypeMng usageTypeMng);	

	List<Map<String,Object>> usageTypeMngListExcel(
			@Param(value = "usageTypeMng") UsageTypeMng usageTypeMng,
			@Param(value = "usgTypNm") String usgTypNm
	);
	
	/*
	int addCodeDetail(
			@Param(value ="usageTypeMng") UsageTypeMng usageTypeMng);	
	
	int addCodeDetailName(
			@Param(value ="usageTypeMng") UsageTypeMng usageTypeMng);
	*/
}