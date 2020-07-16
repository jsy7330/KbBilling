package com.ntels.ccbs.appIf.mapper.sh.customer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CustomerIfMapper {

	LinkedHashMap<String, Object> getCustomer(@Param(value="param")Map<String, Object> param);
	
	List<LinkedHashMap<String, Object>> getCtrtInfo(@Param(value="param")Map<String, Object> param);
	
	List<LinkedHashMap<String, Object>> getProdCmpsInfo(@Param(value="param")Map<String, Object> param);
	
	
}
