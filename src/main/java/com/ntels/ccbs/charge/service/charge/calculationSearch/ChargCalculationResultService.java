package com.ntels.ccbs.charge.service.charge.calculationSearch;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.charge.domain.charge.calculationSearch.ChargCalculationResultVO;

public interface ChargCalculationResultService {
	
	Map<String, Object> getChargePersonCountList(String soId,
	        List<Map<String, Object>> soAuthList, String billYymm, String sidx,
	        String sord, int page, int rows, String lng);
}
