package com.ntels.ccbs.product.service.refInfo.itemTypeMgt;

import java.util.List;
import com.ntels.ccbs.product.domain.refInfo.itemTypeMgt.ChargeType;

public interface ChargeTypeService {
	public List<ChargeType> getRateTypeList (ChargeType chargeType, int page, int perPage);
	public int getRateTypeListCount (ChargeType chargeType);
	public ChargeType getRateTypeMaxPriNo(ChargeType chargeType);
	public int getRateTypeDupCnt(ChargeType chargeType);
	public int addRateType(ChargeType chargeType);
	public ChargeType getRateType(ChargeType chargeType);
	public int getRateTypeDupExpCnt(ChargeType chargeType);
	public int modRateType(ChargeType chargeType);
	public int modRateTypeActDt(ChargeType chargeType);
	public int modRateTypeActDtion(ChargeType chargeType);
}
