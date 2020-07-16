package com.ntels.ccbs.customer.service.contract.contract;

import com.ntels.ccbs.customer.domain.contract.contract.PaymentDetailVO;

public interface PaymentDetailService {

	PaymentDetailVO paymentDetailInfo(String soId, String pymAcntId, String today, String lng);
	
}