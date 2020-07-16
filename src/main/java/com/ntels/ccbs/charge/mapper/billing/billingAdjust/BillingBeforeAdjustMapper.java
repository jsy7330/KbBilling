package com.ntels.ccbs.charge.mapper.billing.billingAdjust;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;

public interface BillingBeforeAdjustMapper {

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
	
	List<BillingAdjustVO> getPymList(
			@Param(value ="billingBeforeAdjust") BillingAdjustVO billingAdjust
	);
	
	List<BillingAdjustVO> getPymBillList(
			@Param(value ="billingBeforeAdjust") BillingAdjustVO billingAdjust
	);
}
