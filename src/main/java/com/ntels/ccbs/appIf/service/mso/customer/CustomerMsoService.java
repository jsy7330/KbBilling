package com.ntels.ccbs.appIf.service.mso.customer;

import java.util.Map;

import com.ntels.ccbs.appIf.domain.common.AppRequestVO;

public interface CustomerMsoService {

	Map<String, Object> getEquipmentReception(AppRequestVO appRequestVO);
	
	Map<String, Object> getHomeService(AppRequestVO appRequestVO);
	
	
}
