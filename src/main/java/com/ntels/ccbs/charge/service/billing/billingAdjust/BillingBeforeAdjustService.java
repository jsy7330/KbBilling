package com.ntels.ccbs.charge.service.billing.billingAdjust;

import java.util.List;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;

public interface BillingBeforeAdjustService {
	public List<BillingAdjustVO> getPymList(BillingAdjustVO billingAdjust);
	public List<BillingAdjustVO> getPymBillList(BillingAdjustVO billingAdjust);
	
	public Object getPymRcpt(BillingAdjustVO billingAdjust);
}
