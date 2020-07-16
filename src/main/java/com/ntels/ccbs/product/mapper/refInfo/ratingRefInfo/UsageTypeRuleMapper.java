package com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeRule;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface UsageTypeRuleMapper {

	List<UsageTypeRule> getUsageTypeRuleList(
			@Param(value = "usageTypeRule") UsageTypeRule usageTypeRule
			, @Param(value="start")int start
			, @Param(value="end") int end
			);		


	int getUsageTypeRuleListCount(
			@Param(value = "usageTypeRule") UsageTypeRule usageTypeRule);

	
	int addUsageTypeRule(
			@Param(value ="usageTypeRule") UsageTypeRule usageTypeRule);	


	int modUsageTypeRule(
			@Param(value ="usageTypeRule") UsageTypeRule usageTypeRule);	

	
	int delUsageTypeRule(
			@Param(value ="usageTypeRule") UsageTypeRule usageTypeRule);
	
	List<CommonCodeVO> getDataNmList();
	List<CommonCodeVO> getSeqNoList();

}