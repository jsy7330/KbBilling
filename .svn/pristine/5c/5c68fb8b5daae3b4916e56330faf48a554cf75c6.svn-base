package com.ntels.ccbs.product.mapper.refInfo.commonInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.commonInfo.Factor;

@Component
public interface FactorMapper {
	int totalFactorListCount(@Param(value = "factor") Factor factor);
	List<Factor> totalFactorList(@Param(value = "factor") Factor factor
			, @Param(value="start")int start, @Param(value="end") int end);
	int fctrNameCheck(@Param(value = "factor") Factor factor);
	List<Factor> getRefColmnIdComboList(@Param(value = "factor") Factor factor); 
	int getTotalFactorDupCnt(@Param(value = "factor") Factor factor);
	int addTotalFactor(@Param(value = "factor") Factor factor);
	List<Factor> getCommonGrpPopupList(@Param(value = "factor") Factor factor);
	Factor getTotalFactor(@Param(value = "factor") Factor factor);
	int getTotalFactorDupExpCnt(@Param(value = "factor") Factor factor);
	int allmodTotalFactor(@Param(value = "factor") Factor factor);
	int allmodTotalFactor1(@Param(value = "factor") Factor factor);
	int allmodTotalFactor2(@Param(value = "factor") Factor factor);
	int allmodTotalFactor3(@Param(value = "factor") Factor factor);
	int modTotalFactor(@Param(value = "factor") Factor factor);
	int modTotalFactorInactDt(@Param(value = "factor") Factor factor);
	int modTotalFactorInactDtation(@Param(value = "factor") Factor factor);
}

