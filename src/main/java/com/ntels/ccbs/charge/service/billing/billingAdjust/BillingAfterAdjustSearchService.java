package com.ntels.ccbs.charge.service.billing.billingAdjust;

import java.util.Map;


import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustSearchVO;

public interface BillingAfterAdjustSearchService {

	Map<String, Object> getBillChargeAdjustReportList(BillingAfterAdjustSearchVO billingAfterAdjustSearchVO,
			String sidx,
	        String sord,
	        int page, 
        	int rows, 
        	String lng);
}
