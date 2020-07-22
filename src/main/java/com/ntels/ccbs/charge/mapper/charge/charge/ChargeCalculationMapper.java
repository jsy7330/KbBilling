package com.ntels.ccbs.charge.mapper.charge.charge;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.charge.charge.ChargeCalculationVO;
import com.ntels.ccbs.charge.domain.charge.charge.RegularChargeJobVO;

@Component
public interface ChargeCalculationMapper {

	int getRegularChargeJobCount(RegularChargeJobVO regularChargeJobVO);
	
	List<RegularChargeJobVO> getRegularChargeJobList(RegularChargeJobVO regularChargeJobVO);
	
	List<RegularChargeJobVO> getBatchLog(RegularChargeJobVO regularChargeJobVO);
	
	RegularChargeJobVO getClcWrkNo(RegularChargeJobVO regularChargeJobVO);
	
	int inserttblchClcMain(RegularChargeJobVO regularChargeJobVO);
	
	int updateBatPgmLog(RegularChargeJobVO regularChargeJobVO);
	
	Integer getChargeListTotalCnt(@Param("soAuthList")List<Map<String, Object>> soAuthList,@Param("charVO")ChargeCalculationVO charVO);

	List<Map<String, Object>> getChargeList(@Param("soAuthList")List<Map<String, Object>> soAuthList,@Param("charVO")ChargeCalculationVO charVO,@Param("end")String end,@Param("start")String start);

	List<Map<String, Object>> getChargeDetailList(@Param("soAuthList")List<Map<String, Object>> soAuthList,@Param("charVO")ChargeCalculationVO charVO,@Param("end")String end,@Param("start")String start);


}
