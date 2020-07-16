package com.ntels.ccbs.charge.mapper.billing.billing;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.billing.billing.BillingCycleInfoVO;

@Component
public interface BillingCycleInfoMapper {

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
	List<BillingCycleInfoVO> BillingCycleInfoListAction(
		@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );

	int BillingCycleInfoListCount(@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO);
	
	int getBillSetupItemCycleCopyValidate(@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO);
	
	int deleteBillSetupItemCycle(@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO);
	
	int insertBillSetupItemCycle(@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO);
	
	int getBillSetupItemCycleCount(@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO);
	
	int registerBillSetupItemCycle(@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO);
	
	int deleteBillSetupItemCycleCopy(@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO);
	
	int insertBillSetupItemCycleCopy(@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO);
	
	int updateBillSetupItemCycle(@Param(value ="billingCycleInfoVO") BillingCycleInfoVO billingCycleInfoVO);
	
}
