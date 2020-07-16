package com.ntels.ccbs.charge.service.charge.charge.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.charge.calculationSearch.PaymentChargeCalculationVo;
import com.ntels.ccbs.charge.mapper.charge.charge.PaymentChargeCalculationMapper;
import com.ntels.ccbs.charge.service.charge.charge.PaymentChargeCalculationService;

@Service
public class PaymentChargeCalculationServiceImpl implements PaymentChargeCalculationService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private PaymentChargeCalculationMapper paymentChargeCalculationMapper; 

	@Override
	public int getChrgeInfoCount(PaymentChargeCalculationVo paymentChargeCalculationVo) {
		return paymentChargeCalculationMapper.getChrgeInfoCount(paymentChargeCalculationVo);
	}

	@Override
	public List<PaymentChargeCalculationVo> getChrgeInfoList(PaymentChargeCalculationVo paymentChargeCalculationVo,
			int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return paymentChargeCalculationMapper.getChrgeInfoList(paymentChargeCalculationVo, start, end);
	}

	@Override
	public List<PaymentChargeCalculationVo> getDtlList(PaymentChargeCalculationVo paymentChargeCalculationVo) {

		return paymentChargeCalculationMapper.getDtlList(paymentChargeCalculationVo);
	}

}
