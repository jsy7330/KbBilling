package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeMng;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.UsageTypeMngMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.UsageTypeMngService;

@Service
public class UsageTypeMngServiceImpl implements UsageTypeMngService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private UsageTypeMngMapper usageTypeMngMapper;

	@Override
	public List<UsageTypeMng> getUsageTypeMngList(UsageTypeMng usageTypeMng, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return usageTypeMngMapper.getUsageTypeMngList(usageTypeMng, start,  end);
	}

	@Override
	public int getUsageTypeMngRepCheckCount(UsageTypeMng usageTypeMng) {
		return usageTypeMngMapper.getUsageTypeMngRepCheckCount(usageTypeMng);
	}
	
	@Override
	public int getUsageTypeMngListCount(UsageTypeMng usageTypeMng) {
		return usageTypeMngMapper.getUsageTypeMngListCount(usageTypeMng);
	}

	@Override
	public int addUsageTypeMng(UsageTypeMng usageTypeMng){
//		usageTypeMngMapper.addCodeDetail(usageTypeMng);
//		usageTypeMngMapper.addCodeDetailName(usageTypeMng);
		
		return usageTypeMngMapper.addUsageTypeMng(usageTypeMng);
	}	

	@Override
	public int modUsageTypeMng(UsageTypeMng usageTypeMng){
		return usageTypeMngMapper.modUsageTypeMng(usageTypeMng);
	}

	
	@Override
	public int delUsageTypeMng(UsageTypeMng usageTypeMng){
		return usageTypeMngMapper.delUsageTypeMng(usageTypeMng);
	}

	@Override
	public List<Map<String, Object>> usageTypeMngListExcel(
			UsageTypeMng usageTypeMng, String usgTypNm) {
		return usageTypeMngMapper.usageTypeMngListExcel(usageTypeMng, usgTypNm);
	}
}
