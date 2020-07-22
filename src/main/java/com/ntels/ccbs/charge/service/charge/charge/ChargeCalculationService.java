package com.ntels.ccbs.charge.service.charge.charge;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.charge.domain.charge.charge.ChargeCalculationVO;
import com.ntels.ccbs.charge.domain.charge.charge.RegularChargeJobVO;

public interface ChargeCalculationService {

	int getRegularChargeJobCount(RegularChargeJobVO regularChargeJobVO);
	
	List<RegularChargeJobVO> getRegularChargeJobList(RegularChargeJobVO regularChargeJobVO);
	
	List<RegularChargeJobVO> getBatchLog(RegularChargeJobVO regularChargeJobVO);
	
	RegularChargeJobVO getClcWrkNo(RegularChargeJobVO regularChargeJobVO, String userId);
	
	int updateBatPgmLog(RegularChargeJobVO regularChargeJobVO);

	Map<String, Object> getChargeList(List<Map<String, Object>> soAuthList, ChargeCalculationVO charVO);

	Map<String, Object> getChargeDetailList(List<Map<String, Object>> soAuthList, ChargeCalculationVO charVO);

	
	
}
