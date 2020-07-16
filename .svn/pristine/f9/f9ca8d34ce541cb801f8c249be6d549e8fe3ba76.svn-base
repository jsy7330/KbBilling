package com.ntels.ccbs.customer.service.contract.contract.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.customer.domain.contract.contract.PaymentDetailVO;
import com.ntels.ccbs.customer.mapper.contract.contract.PaymentDetailMapper;
import com.ntels.ccbs.customer.service.contract.contract.PaymentDetailService;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService{

	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	
	@Override
	public PaymentDetailVO paymentDetailInfo(String soId,String pymAcntId, String today, String lng) {
		
		PaymentDetailVO pymDtlInfo = paymentDetailMapper.paymentDetailInfo(soId, pymAcntId, today, lng);
		
		return pymDtlInfo;
	}
	
}