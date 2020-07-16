package com.ntels.ccbs.charge.service.charge.charge;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ntels.ccbs.charge.domain.charge.calculationSearch.PaymentChargeCalculationVo;

public interface PaymentChargeCalculationService {

	public int getChrgeInfoCount(PaymentChargeCalculationVo paymentChargeCalculationVo);
	
	public List<PaymentChargeCalculationVo> getChrgeInfoList(PaymentChargeCalculationVo paymentChargeCalculationVo, int page, int perPage);
	
	public List<PaymentChargeCalculationVo> getDtlList(PaymentChargeCalculationVo paymentChargeCalculationVo);
}
