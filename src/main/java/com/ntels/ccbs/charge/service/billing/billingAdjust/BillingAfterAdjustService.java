package com.ntels.ccbs.charge.service.billing.billingAdjust;

import java.util.List;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingBasicCustInfoVO;

public interface BillingAfterAdjustService {
	public List<BillingAdjustVO> getPymList(BillingAdjustVO billingAdjust);
	public List<BillingAdjustVO> getBillList(BillingAdjustVO billingAdjust);
	public List<BillingAdjustVO> getPymRcpt(BillingAdjustVO billingAdjust);
	public int modAdjTgtList(BillingAdjustVO adjAply, List<BillingAdjustVO> adjAplyDtl, String gubun);
	public int cancelAdjList(BillingAdjustVO adjNo);
	public BillingBasicCustInfoVO getBasicCustInfo(String soId, String pymAcntId);
}