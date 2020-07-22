package com.ntels.ccbs.charge.service.charge.calculationSearch;

import java.util.Map;

public interface ChargCalculationResultService {
	
	Map<String, Object> getChargePersonCountList(String soId,
	        	String billYymm, 
	        	String sidx,
	        	String sord, 
	        	int page, 
	        	int rows, 
	        	String lng);
}
