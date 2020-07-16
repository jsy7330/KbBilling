package com.ntels.ccbs.appIf.service.mso.customer.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.appIf.domain.common.AppRequestVO;
import com.ntels.ccbs.appIf.mapper.mso.customer.CustomerMsoMapper;
import com.ntels.ccbs.appIf.service.mso.customer.CustomerMsoService;
import com.ntels.ccbs.ifg.service.InterfaceService;

@Service
public class CustomerMsoServiceImpl implements CustomerMsoService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerMsoMapper customerMsoMapper;
	
	@Autowired
	private InterfaceService interfaceService;

	/**
	 * 실장비정보조회
	 */
	@Override
	public Map<String, Object> getEquipmentReception(AppRequestVO appRequestVO) {
		
		Map<String, Object> param = appRequestVO.getBody();
		
		//리턴 맵
		Map<String, Object> result = appRequestVO.getBody();
		
		//실장비리스트 셀렉트
		List<Map<String, Object>> reception = customerMsoMapper.getEquipmentReception(param);
		
		result.remove("lng");
		result.put("EQT_INFO", reception);
		
		return result;
	}

	/**
	 * 홈서비스 ID조회
	 */
	@Override
	public Map<String, Object> getHomeService(AppRequestVO appRequestVO) {

		Map<String, Object> param = appRequestVO.getBody();
		
		//리턴 맵
		Map<String, Object> result = appRequestVO.getBody();
				
		//인터페이스 호출
		List<Map<String, Object>> homeService = interfaceService.getHomeServiceList((String)param.get("SO_ID"), (String)param.get("CUST_ID"));
				
		result.put("MEM_INFO", homeService);
		
		return result;
	}
	
	
	
}
