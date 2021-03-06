package com.ntels.ccbs.charge.mapper.billing.billingAdjust;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
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
	
	int dtTotalCount(@Param(value="billingAdVO")BillingAdjustVO billingAdVO);
	
	List<BillingAdjustVO> billingAfterSearchPopupDtlList(@Param(value="billingAdVO")BillingAdjustVO billingAdVO,
			@Param(value = "sidx")String sidx,
	        @Param(value = "sord")String sord,
	        @Param(value = "start")String start,
	        @Param(value = "end")String end,
	        @Param(value = "lng")String lng);
	
	List<Map<String, Object>> listExcel(@Param(value="billingSearch")BillingAfterAdjustSearchVO billingAfterAdjustSearchVO);
	
	List<Map<String, Object>> popUpListExcel(@Param(value="billingAdVO")BillingAdjustVO billingAdVO);
}
