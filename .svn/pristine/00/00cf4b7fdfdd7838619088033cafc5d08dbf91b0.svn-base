package com.ntels.ccbs.charge.mapper.billing.billingAdjust;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;

public interface BillingAfterAdjustMapper {

	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attribute
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	
	List<BillingAdjustVO> getPymList (@Param(value ="billingAdjust") BillingAdjustVO billingAdjust);
	
	List<BillingAdjustVO> getBillList(@Param(value ="billingAdjust") BillingAdjustVO billingAdjust);
	
	List<BillingAdjustVO> getPymRcpt (@Param(value ="billingAdjust") BillingAdjustVO billingAdjust);
	
	int insertAdjAply   (@Param(value = "adjAply")    BillingAdjustVO adjAply);
	
	int insertAdjAplyDtl(@Param(value = "adjAplyDtl") BillingAdjustVO adjAplyDtl);
	
	int updateAdjAply   (@Param(value = "adjAply")    BillingAdjustVO adjAply);
	
	int deleteAdjAply   (@Param(value = "adjAply")    BillingAdjustVO adjAply);
	
	int deleteAdjAplyDtl(@Param(value = "adjAply")    BillingAdjustVO adjAply);
}
