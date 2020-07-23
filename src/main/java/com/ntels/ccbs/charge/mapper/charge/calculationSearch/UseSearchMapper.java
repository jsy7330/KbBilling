package com.ntels.ccbs.charge.mapper.charge.calculationSearch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UseSearchMapper {

	int totalCount(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("useYymm")String useYymm,
			@Param("orderTp")String orderTp,
			@Param("useStDt")String useStDt,
			@Param("useEdDt")String useEdDt);
	
	List<Map<String, Object>> getUsgListByCtrt(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("useYymm")String useYymm,
			@Param("orderTp")String orderTp,
			@Param("useStDt")String useStDt,
			@Param("useEdDt")String useEdDt,
			@Param(value = "sidx")String sidx,
	        @Param(value = "sord")String sord,
	        @Param(value = "start")String start,
	        @Param(value = "end")String end,
	        @Param(value = "lng")String lng);
	
	List<Map<String, Object>> listExcel(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("useYymm")String useYymm,
			@Param("orderTp")String orderTp,
			@Param("useStDt")String useStDt,
			@Param("useEdDt")String useEdDt);
}
