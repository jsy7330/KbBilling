package com.ntels.ccbs.charge.service.billing.billingAdjust;

import java.util.List;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;

public interface BillingBeforeAdjustService {
	public List<BillingAdjustVO> getPymList(BillingAdjustVO billingAdjust);
	public List<BillingAdjustVO> getPymBillList(BillingAdjustVO billingAdjust);
	
	public Object getPymRcpt(BillingAdjustVO billingAdjust);

	public int getApplBeforeCount(BillingAdjustVO billingAdjust);
	
	public int getApplYymmCount(BillingAdjustVO billingAdjust);
	
	public int getApplHopeYymm(BillingAdjustVO billingAdjust);

	public int modAdjTgtList(BillingAdjustVO adjAply, List<BillingAdjustVO> adjAplyDtl, String gubun);
	
	public int deleteReqAppl(BillingAdjustVO billingAdjust);
	
	public int deleteReqDtlAppl(BillingAdjustVO billingAdjust);
	
}
