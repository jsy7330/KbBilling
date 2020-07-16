package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeGrp;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.UsageTypeGrpMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.UsageTypeGrpService;
	
@Service
public class UsageTypeGrpServiceImpl implements UsageTypeGrpService {
	
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** RatingFactorUnitMapper Autowired. */
	@Autowired
	private UsageTypeGrpMapper usageTypeGrpMapper;

	@Override
	public List<UsageTypeGrp> getUsageTypeList(UsageTypeGrp usageTypeGrp,
			int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;
		
		return usageTypeGrpMapper.getUsageTypeList(usageTypeGrp, start, end);
	}

	@Override
	public List<UsageTypeGrp> getUsageTypeGrpList(UsageTypeGrp usageTypeGrp,
			int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;
		
		return usageTypeGrpMapper.getUsageTypeGrpList(usageTypeGrp, start, end);
	}

	@Override
	public int addUsageTypeGrp(UsageTypeGrp usageTypeGrp) {
		for( Map<String, Object> updateInfo: usageTypeGrp.getUpdateSetValList() ) {
			usageTypeGrp.setInsertUsgTyp((String)updateInfo.get("usgTyp"));
			usageTypeGrp.setInsertUsgTypGrpCd((String)updateInfo.get("usgTypGrpCd"));
			usageTypeGrp.setInsertEffDt((String)updateInfo.get("effDt"));
			
			System.out.println("!!!!!!!!!!!!!!!!!!" + usageTypeGrp.toString());
			
			if( usageTypeGrpMapper.getUsageTypeGrpListCount(usageTypeGrp) == 0 ) {
				usageTypeGrpMapper.addUsageTypeGrp(usageTypeGrp);	
			}
		}
		return 1;
	}

	@Override
	public int delUsageTypeGrp(UsageTypeGrp usageTypeGrp) {
		return usageTypeGrpMapper.delUsageTypeGrp(usageTypeGrp);
	}
}