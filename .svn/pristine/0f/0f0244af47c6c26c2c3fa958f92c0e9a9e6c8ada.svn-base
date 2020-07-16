package com.ntels.ccbs.product.mapper.refInfo.itemTypeMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.product.domain.refInfo.itemTypeMgt.ChargeType;

public interface ChargeTypeMapper {
	List<ChargeType> getRateTypeList(@Param(value = "chargeType") ChargeType chargeType,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	int getRateTypeListCount (@Param(value = "chargeType") ChargeType chargeType);
	ChargeType getRateTypeMaxPriNo (@Param(value = "chargeType") ChargeType chargeType);
	int getRateTypeDupCnt(@Param(value = "chargeType") ChargeType chargeType);
	int addRateType(@Param(value = "chargeType") ChargeType chargeType);
	ChargeType getRateType (@Param(value = "chargeType") ChargeType chargeType);
	int getRateTypeDupExpCnt (@Param(value = "chargeType") ChargeType chargeType);
	int modRateType (@Param(value = "chargeType") ChargeType chargeType);
	int modRateTypeActDt(@Param(value = "chargeType") ChargeType chargeType);
	int modRateTypeActDtion(@Param(value = "chargeType") ChargeType chargeType);
}

