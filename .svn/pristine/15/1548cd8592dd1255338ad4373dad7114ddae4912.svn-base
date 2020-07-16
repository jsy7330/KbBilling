package com.ntels.ccbs.customer.mapper.contract.contract;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.contract.contract.PaymentDetailVO;

@Component
public interface PaymentDetailMapper {


	PaymentDetailVO paymentDetailInfo(@Param("soId")String soId, @Param("pymAcntId")String pymAcntId, 
			@Param("today")String today, @Param("lng")String lng);

	
}