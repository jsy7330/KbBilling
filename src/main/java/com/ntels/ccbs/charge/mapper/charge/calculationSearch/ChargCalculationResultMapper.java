package com.ntels.ccbs.charge.mapper.charge.calculationSearch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * <PRE>
 * 1. ClassName: ChargCalculationResultMapper
 * 2. FileName : ChargCalculationResultMapper.java
 * 3. Package  : com.ntels.ccbs.charge.mapper.charge.calculationSearch
 * 4. Comment  :
 * 5. 작성자   : chkim
 * 6. 작성일   : 2020. 7. 17. 오후 5:37:15
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     chkim :    2020. 7. 17.    : 신규개발
 * </PRE>
 */

@Component
public interface ChargCalculationResultMapper {
	
	int totalCount(@Param("soId")String soId,
			@Param("billYymm")String billYymm);
	
	List<Map<String,Object>> getChargePersonCountList(@Param("soId")String soId,
			@Param("billYymm")String billYymm,
			@Param("sidx")String sidx,
	        @Param("sord")String sord,
	        @Param("start")String start,
	        @Param("end")String end,
	        @Param("lng")String lng);
}
