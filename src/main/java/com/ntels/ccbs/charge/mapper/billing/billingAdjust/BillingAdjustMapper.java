package com.ntels.ccbs.charge.mapper.billing.billingAdjust;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;

public interface BillingAdjustMapper {

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
	
	List<BillingAdjustVO> getBeforeAdjTgtList(
			@Param(value ="billingAdjust") BillingAdjustVO billingAdjust
	);
	
	List<BillingAdjustVO> getAfterAdjTgtList(
			@Param(value ="billingAdjust") BillingAdjustVO billingAdjust
	);
	
	List<BillingAdjustVO> getBillClsInfo(
			@Param(value ="billingAdjust") BillingAdjustVO billingAdjust
	);
}