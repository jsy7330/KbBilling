package com.ntels.ccbs.charge.mapper.charge.calculationSearch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface PaymentDistSearchMapper {
	
	int chrgDistListCnt(@Param(value = "soId")String soId,
			@Param(value = "billYymm")String billYymm,
			@Param(value = "pymAcntId")String pymAcntId,
			@Param(value = "custId")String custId);
	
	List<Map<String, Object>> getChargeDiscountInfoList(@Param(value = "soId")String soId,
			@Param(value = "billYymm")String billYymm,
			@Param(value = "pymAcntId")String pymAcntId,
			@Param(value = "custId")String custId,
			@Param(value = "sidx")String sidx,
	        @Param(value = "sord")String sord,
	        @Param(value = "start")String start,
	        @Param(value = "end")String end,
	        @Param(value = "lng")String lng);
	
	int chrgDistDetListCnt(@Param("soId")String soId,
			@Param("billYymm")String billYymm,
			@Param("pymAcntId")String pymAcntId,
			@Param("ctrtId")String ctrtId);
	
	List<Map<String, Object>> getChargeDiscountInfoDetList(@Param("soId")String soId,
				@Param("billYymm")String billYymm,
				@Param("pymAcntId")String pymAcntId,
				@Param("ctrtId")String ctrtId,
				@Param("sidx")String sidx,
		        @Param("sord")String sord,
		        @Param("start")String start,
		        @Param("end")String end,
		        @Param("lng")String lng);
}
