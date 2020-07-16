package com.ntels.ccbs.customer.mapper.contract.receipt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.contract.receipt.ReceiptInfoVO;

@Component
public interface ReceiptInfoMapper {

	List<ReceiptInfoVO> getReceiptInfoList(@Param("rcptInfo")ReceiptInfoVO rcptInfo, 
			@Param("lng")String lng, @Param("today")String today, @Param(value = "soAuthList")List<Map<String, Object>> soAuthList);

	ReceiptInfoVO getMenuNo(String menuNo);
	
}