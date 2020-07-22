package com.ntels.ccbs.charge.mapper.charge.charge;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.charge.charge.RegularChargeJobVO;

@Component
public interface ChargeCalculationMapper {

	int getRegularChargeJobCount(RegularChargeJobVO regularChargeJobVO);
	
	List<RegularChargeJobVO> getRegularChargeJobList(RegularChargeJobVO regularChargeJobVO);
	
	List<RegularChargeJobVO> getBatchLog(RegularChargeJobVO regularChargeJobVO);
	
	RegularChargeJobVO getClcWrkNo(RegularChargeJobVO regularChargeJobVO);
	
	int inserttblchClcMain(RegularChargeJobVO regularChargeJobVO);
	
	int updateBatPgmLog(RegularChargeJobVO regularChargeJobVO);
	
	Integer getChargeListTotalCnt(@Param("soId")String soId,@Param("soAuthList")List<Map<String, Object>> soAuthList
			,@Param("condBillYymm")String condBillYymm
			,@Param("condClc")String condClc
			,@Param("condPymAcntId")String condPymAcntId
			,@Param("condCustId")String condCustId
			);

	List<Map<String, Object>> getChargeList( @Param("soId")String soId,@Param("soAuthList")List<Map<String, Object>> soAuthList,@Param("sidx")String sidx, @Param("sord")String sord, @Param("today")String today 
			,@Param("start")String start,@Param("end")String end,@Param("lng")String lng
			,@Param("condBillYymm")String condBillYymm
			,@Param("condClc")String condClc
			,@Param("condPymAcntId")String condPymAcntId
			,@Param("condCustId")String condCustId
			);

	List<Map<String, Object>> getChargeDetailList( @Param("soId")String soId,@Param("soAuthList")List<Map<String, Object>> soAuthList,@Param("sidx")String sidx, @Param("sord")String sord, @Param("today")String today 
			,@Param("start")String start,@Param("end")String end,@Param("lng")String lng
			,@Param("condBillYymm")String condBillYymm
			,@Param("condClc")String condClc
			,@Param("condPymAcntId")String condPymAcntId
			,@Param("condCustId")String condCustId
			);


}
