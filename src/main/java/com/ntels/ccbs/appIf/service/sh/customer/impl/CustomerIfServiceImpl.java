package com.ntels.ccbs.appIf.service.sh.customer.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.appIf.domain.common.AppRequestVO;
import com.ntels.ccbs.appIf.mapper.sh.customer.CustomerIfMapper;
import com.ntels.ccbs.appIf.service.sh.customer.CustomerIfService;

@Service
public class CustomerIfServiceImpl implements CustomerIfService {
	
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerIfMapper customerIfMapper;

	@Override
	public Map<String, Object> getCustomer(AppRequestVO appRequestVO) {
		
		Map<String, Object> param = appRequestVO.getBody();
		
		//고객정보 셀렉트
		Map<String, Object> result = customerIfMapper.getCustomer(param);

		//고객의 계약 정보 셀렉트
		List<LinkedHashMap<String, Object>> ctrtInfo = customerIfMapper.getCtrtInfo(param);
		
		//계약 정보가 있을 경우
		if(ctrtInfo != null && ctrtInfo.size()>0){
			
			for(LinkedHashMap<String, Object> data : ctrtInfo){
				
				data.put("SO_ID", param.get("SO_ID"));
				//상품정보 셀렉트
				List<LinkedHashMap<String, Object>> prodInfo = customerIfMapper.getProdCmpsInfo(data);
				
				if(prodInfo != null && prodInfo.size() > 0){
					data.put("PROD_INFO", prodInfo);
				}
				
			}
			
			result.put("CTRT_INFO", ctrtInfo);
			
		} //계약 정보가 있을 경우
		
		
		return result;
	}

	
}
