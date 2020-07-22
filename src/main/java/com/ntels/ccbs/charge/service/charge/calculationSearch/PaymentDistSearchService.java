package com.ntels.ccbs.charge.service.charge.calculationSearch;

import java.util.Map;

public interface PaymentDistSearchService {
	
	//고객별할인내역
	Map<String, Object> getChargeDiscountInfoList(String soId,
			String billYymm,
			String pymAcntId,
			String custId,
			String sidx,
        	String sord, 
        	int page, 
        	int rows, 
        	String lng);
	
	//할인과금항목내역
	Map<String, Object> getChargeDiscountInfoDetList(String soId,
			String billYymm,
			String pymAcntId,
			String ctrtId,
			String sidx,
        	String sord, 
        	int page, 
        	int rows, 
        	String lng);
}
