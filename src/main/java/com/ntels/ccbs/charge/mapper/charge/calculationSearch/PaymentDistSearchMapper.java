package com.ntels.ccbs.charge.mapper.charge.calculationSearch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface PaymentDistSearchMapper {
	
	int chrgDistListCnt(@Param("soId")String soId,
			@Param("billYymm")String billYymm,
			@Param("billCycl")String billCycl,
			@Param("pymAcntId")String pymAcntId,
			@Param("custId")String custId,
			@Param("ctrtId")String ctrtId);
	
	List<Map<String, Object>> getChargeDiscountInfoList(@Param("soId")String soId,
			@Param("billYymm")String billYymm,
			@Param("billCycl")String billCycl,
			@Param("pymAcntId")String pymAcntId,
			@Param("custId")String custId,
			@Param("ctrtId")String ctrtId,
			@Param("sidx")String sidx,
	        @Param("sord")String sord,
	        @Param("start")String start,
	        @Param("end")String end,
	        @Param("lng")String lng);
	
	int chrgDistDetListCnt(@Param("soId")String soId,
			@Param("clcwrkNo")String clcwrkNo,
			@Param("rateItmCd")String rateItmCd,
			@Param("prodCd")String prodCd,
			@Param("svcCd")String svcCd,
			@Param("billYymm")String billYymm,
			@Param("billCycl")String billCycl,
			@Param("pymAcntId")String pymAcntId,
			@Param("custId")String custId,
			@Param("ctrtId")String ctrtId);
	
	List<Map<String, Object>> getChargeDiscountInfoDetList(@Param("soId")String soId,
				@Param("clcwrkNo")String clcwrkNo,
				@Param("rateItmCd")String rateItmCd,
				@Param("prodCd")String prodCd,
				@Param("svcCd")String svcCd,
				@Param("billYymm")String billYymm,
				@Param("billCycl")String billCycl,
				@Param("pymAcntId")String pymAcntId,
				@Param("custId")String custId,
				@Param("ctrtId")String ctrtId,
				@Param("sidx")String sidx,
		        @Param("sord")String sord,
		        @Param("start")String start,
		        @Param("end")String end,
		        @Param("lng")String lng);
}
