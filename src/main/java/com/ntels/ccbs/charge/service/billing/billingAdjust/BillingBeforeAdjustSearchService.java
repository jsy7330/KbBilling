package com.ntels.ccbs.charge.service.billing.billingAdjust;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingBeforeAdjustSearchVO;

public interface BillingBeforeAdjustSearchService {
	
	Map<String, Object> getBillChargeAdjustReportList(BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO,
			String sidx,
	        String sord,
	        int page, 
        	int rows, 
        	String lng);
	
	Map<String, Object> billingBeforeSearchPopupDtlList(BillingAdjustVO billingAdVO,
			String sidx,
	        String sord,
	        int page, 
        	int rows, 
        	String lng);
	
	List<Map<String, Object>> listExcel(BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO);
	List<Map<String, Object>> popUpListExcel(BillingAdjustVO billingAdVO);
}
