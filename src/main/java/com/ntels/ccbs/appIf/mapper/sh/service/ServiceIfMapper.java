package com.ntels.ccbs.appIf.mapper.sh.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ServiceIfMapper {

	LinkedHashMap<String, Object> getStockStat(@Param(value="param")Map<String, Object> param);
	
	LinkedHashMap<String, Object> getCustomer(@Param(value="param")Map<String, Object> param);
	
	List<LinkedHashMap<String, Object>> getCtrtInfo(@Param(value="param")Map<String, Object> param);
	
	List<LinkedHashMap<String, Object>> getProdCmpsInfo(@Param(value="param")Map<String, Object> param);
	
	List<LinkedHashMap<String, Object>> getTcmctDeviceHistKeyCheck(@Param(value="param")Map<String, Object> param);
	
	int updateTcmctDeviceHistKey(@Param(value="param")Map<String, Object> param);
	
	int setTcmctDeviceHist(@Param(value = "param") Map<String, Object> param);
	
	List<LinkedHashMap<String, Object>> getTdndtEqtSeq(@Param(value="param")Map<String, Object> param);
	
	int updateTdndtEqt(@Param(value="param")Map<String, Object> param);
	
	int setTdndtUpdProcHist(@Param(value="param")Map<String, Object> param);
	
	int deleteDeviceHist(@Param(value="param")Map<String, Object> param);
	
	
	
	
	
	
}
