package com.ntels.ccbs.customer.mapper.contract.contract;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ContractConfirmMapper {

	Map<String, Object> getCustInfo(
			@Param("soId")String soId,
			@Param("custId")String custId,
			@Param("ctrtId")String ctrtId,
			@Param("lng")String lng,
			@Param("today")String today);
	
}