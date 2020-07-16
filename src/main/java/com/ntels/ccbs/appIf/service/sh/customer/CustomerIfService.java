package com.ntels.ccbs.appIf.service.sh.customer;

import java.util.Map;

import com.ntels.ccbs.appIf.domain.common.AppRequestVO;

public interface CustomerIfService {

	Map<String, Object> getCustomer(AppRequestVO appRequestVO);
	
}
