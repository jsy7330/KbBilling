package com.ntels.ccbs.charge.service.charge.calculationSearch;

import java.util.List;
import java.util.Map;

public interface UseSearchService {

	Map<String, Object> getUsgListByCtrt(String soId,
			String ctrtId,
			String useYymm,
			String orderTp,
			String useStDt,
			String useEdDt,
			String sidx,
        	String sord, 
        	int page, 
        	int rows, 
        	String lng);
	
	List<Map<String, Object>> listExcel(String soId,
			String ctrtId,
			String useYymm,
			String orderTp,
			String useStDt,
			String useEdDt,
			String lng);
}
