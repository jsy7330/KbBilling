package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeMap;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.UsageTypeMapMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.UsageTypeMapService;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;

@Service
public class UsageTypeMapServiceImpl implements UsageTypeMapService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private UsageTypeMapMapper usageTypeMapMapper;

	@Override
	public List<UsageTypeMap> getUsageTypeMapList(UsageTypeMap usageTypeMap, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return usageTypeMapMapper.getUsageTypeMapList(usageTypeMap, start,  end);
	}

	@Override
	public int getUsageTypeMapListCount(UsageTypeMap usageTypeMap) {
		return usageTypeMapMapper.getUsageTypeMapListCount(usageTypeMap);
	}
	

	@Override
	public int addUsageTypeMap(UsageTypeMap usageTypeMap){
		return usageTypeMapMapper.addUsageTypeMap(usageTypeMap);
	}	

	@Override
	public int modUsageTypeMap(UsageTypeMap usageTypeMap){
		return usageTypeMapMapper.modUsageTypeMap(usageTypeMap);
	}
	
	@Override
	public int delUsageTypeMap(UsageTypeMap usageTypeMap){
		return usageTypeMapMapper.delUsageTypeMap(usageTypeMap);
	}
	
	public List<CommonCodeVO> getDataNmList() {
		return usageTypeMapMapper.getDataNmList();
	}

	@Override
	public List<CommonCodeVO> getSeqNoList() {
		return usageTypeMapMapper.getSeqNoList();
	}
}
