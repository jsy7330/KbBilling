package com.ntels.ccbs.charge.mapper.billing.billingAdjust;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingBeforeAdjustSearchVO;


@Component
public interface BillingBeforeAdjustSearchMapper {

	int totalCount(@Param(value="billingSearch")BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO);
	
	List<BillingBeforeAdjustSearchVO> getBillChargeAdjustReportList(@Param(value="billingSearch")BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO,
			@Param(value = "sidx")String sidx,
	        @Param(value = "sord")String sord,
	        @Param(value = "start")String start,
	        @Param(value = "end")String end,
	        @Param(value = "lng")String lng);
	
	int dtTotalCount(@Param(value="billingAdVO")BillingAdjustVO billingAdVO);
	
	List<BillingAdjustVO> billingBeforeSearchPopupDtlList(@Param(value="billingAdVO")BillingAdjustVO billingAdVO,
			@Param(value = "sidx")String sidx,
	        @Param(value = "sord")String sord,
	        @Param(value = "start")String start,
	        @Param(value = "end")String end,
	        @Param(value = "lng")String lng);
	
	List<Map<String, Object>> listExcel(@Param(value="billingSearch")BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO);
	
	List<Map<String, Object>> popUpListExcel(@Param(value="billingAdVO")BillingAdjustVO billingAdVO);
}
