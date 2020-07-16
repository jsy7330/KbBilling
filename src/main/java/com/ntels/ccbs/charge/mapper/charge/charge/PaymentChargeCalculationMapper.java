package com.ntels.ccbs.charge.mapper.charge.charge;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.charge.calculationSearch.PaymentChargeCalculationVo;

@Component
public interface PaymentChargeCalculationMapper {

	int getChrgeInfoCount(
			@Param(value = "paymentChargeCalculationVo") PaymentChargeCalculationVo paymentChargeCalculationVo);

	List<PaymentChargeCalculationVo> getChrgeInfoList(
			@Param(value = "paymentChargeCalculationVo") PaymentChargeCalculationVo paymentChargeCalculationVo,
			@Param(value = "start") int start, @Param(value = "end") int end);

	List<PaymentChargeCalculationVo> getDtlList(
			@Param(value = "paymentChargeCalculationVo") PaymentChargeCalculationVo paymentChargeCalculationVo);

}
