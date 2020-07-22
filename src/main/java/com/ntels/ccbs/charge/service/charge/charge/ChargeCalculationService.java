package com.ntels.ccbs.charge.service.charge.charge;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.charge.domain.charge.charge.RegularChargeJobVO;

public interface ChargeCalculationService {

	int getRegularChargeJobCount(RegularChargeJobVO regularChargeJobVO);
	
	List<RegularChargeJobVO> getRegularChargeJobList(RegularChargeJobVO regularChargeJobVO);
	
	List<RegularChargeJobVO> getBatchLog(RegularChargeJobVO regularChargeJobVO);
	
	RegularChargeJobVO getClcWrkNo(RegularChargeJobVO regularChargeJobVO, String userId);
	
	int updateBatPgmLog(RegularChargeJobVO regularChargeJobVO);

	Map<String, Object> getChargeList(String soId,List<Map<String, Object>> soAuthList,String sidx,String sord, int page, int rows, String today, String lng
			 ,String condBillYymm
			 ,String condClc
			 ,String condPymAcntId
			 ,String condCustId);

	Map<String, Object> getChargeDetailList(String soId, List<Map<String, Object>> soAuthList, String sidx, String sord,int page, int rows, String today, String lng
			, String condBillYymm
			, String condClc
			, String condPymAcntId
			, String condCustId);
	
}
