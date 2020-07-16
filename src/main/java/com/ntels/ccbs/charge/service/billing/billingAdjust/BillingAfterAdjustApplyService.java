package com.ntels.ccbs.charge.service.billing.billingAdjust;

import java.util.List;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustApplyVO;

public interface BillingAfterAdjustApplyService {
	public List<BillingAfterAdjustApplyVO> getAfterAdjTgtList(BillingAfterAdjustApplyVO billingAfterAdjustApply);
}