package com.ntels.ccbs.product.service.refInfo.ratingRefInfo;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeMng;


public interface UsageTypeMngService {

	public List<UsageTypeMng> getUsageTypeMngList(UsageTypeMng usageTypeMng, int page, int perPage);
	public int getUsageTypeMngRepCheckCount(UsageTypeMng usageTypeMng);
	public int getUsageTypeMngListCount(UsageTypeMng usageTypeMng);
	public int addUsageTypeMng(UsageTypeMng usageTypeMng);
	public int modUsageTypeMng(UsageTypeMng usageTypeMng);
	public int delUsageTypeMng(UsageTypeMng usageTypeMng);
	List<Map<String, Object>> usageTypeMngListExcel(
			UsageTypeMng usageTypeMng
		  , String usgTypNm
			);
}