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

	List<BillingAdjustVO> getPymRcpt(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);

	Object getApplCount(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);

	int insertReqAppl(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);

	int updateReqAppl(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);
	
	int insertReqDtlAppl(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);

	int updateReqDtlAppl(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);

	int deleteReqAppl(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);
	
	int deleteReqDtlAppl(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);

	int getApplYymmCount(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);
	
	int getApplHopeYymm(@Param(value="billingAdjust")BillingAdjustVO billingAdjust);
}
