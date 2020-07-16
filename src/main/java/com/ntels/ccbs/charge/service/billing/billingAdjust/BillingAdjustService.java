package com.ntels.ccbs.charge.service.billing.billingAdjust;

import java.util.List;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;

public interface BillingAdjustService {
	public List<BillingAdjustVO> getAdjTgtList(BillingAdjustVO billingAdjust);
	public List<BillingAdjustVO> getBillClsInfo(BillingAdjustVO billingAdjust);
}
