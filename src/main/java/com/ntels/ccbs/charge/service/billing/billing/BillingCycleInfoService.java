package com.ntels.ccbs.charge.service.billing.billing;

import java.util.List;

import com.ntels.ccbs.charge.domain.billing.billing.BillingCycleInfoVO;

public interface BillingCycleInfoService {

	public List<BillingCycleInfoVO> BillingCycleInfoListAction(BillingCycleInfoVO billingCycleInfoVO, int page, int perPage);
	
	public int BillingCycleInfoListCount(BillingCycleInfoVO billingCycleInfoVO);
	
	public int insertBillingCycleInfo(BillingCycleInfoVO billingCycleInfoVO);
	
	public void updateBillingCycleInfo(BillingCycleInfoVO billingCycleInfoVO);
	
	public int copyBillingCycleInfo(BillingCycleInfoVO billingCycleInfoVO);
	
	public int updateBillSetupItemCycle(List<BillingCycleInfoVO> billingCycleInfoVOList, String userId);
	
	
}
