package com.ntels.ccbs.charge.mapper.billing.billingAdjust;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustSearchVO;

@Component
public interface BillingAfterAdjustSearchMapper {
	
	int totalCount(@Param(value="billingSearch")BillingAfterAdjustSearchVO billingAfterAdjustSearchVO);
	
	List<BillingAfterAdjustSearchVO> getBillChargeAdjustReportList(@Param(value="billingSearch")BillingAfterAdjustSearchVO billingAfterAdjustSearchVO,
			@Param(value = "sidx")String sidx,
	        @Param(value = "sord")String sord,
	        @Param(value = "start")String start,
	        @Param(value = "end")String end,
	        @Param(value = "lng")String lng);

}
