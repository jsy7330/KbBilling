package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.common.PymAcntSearchVO;
import com.ntels.ccbs.system.domain.common.common.PymAcntVO;

@Component
public interface PymAcntSearchPopupMapper {
	

	int getPymAcntInfoTotalCnt(@Param("soId")String soId
			,@Param("custNm") String custNm
			,@Param("custId") String custId
			,@Param("pymAcntId")String pymAcntId
			,@Param("soAuthList") List<Map<String, Object>> soAuthList);

	int getPymAcntInfoWithCtrtTotalCnt(@Param("soId")String soId
			,@Param("custNm") String custNm
			,@Param("custId") String custId
			,@Param("pymAcntId")String pymAcntId
			,@Param("ctrtId") String ctrtId
			,@Param("svcTelNo") String svcTelNo
			,@Param("soAuthList") List<Map<String, Object>> soAuthList);



	List<PymAcntSearchVO> getPymAcntInfoList(@Param("soId")String soId
			,@Param("custNm") String custNm
			,@Param("custId") String custId
			,@Param("pymAcntId")String pymAcntId
			,@Param("soAuthList") List<Map<String, Object>> soAuthList
			,@Param("today") String today
			,@Param("lng")String lng
			,@Param("sidx")String sidx
			,@Param("sort") String sort
			,@Param("start") String start
			,@Param("end") String end);

	List<PymAcntSearchVO> getPymAcntInfoWithCtrtList(@Param("soId")String soId
			,@Param("custNm") String custNm
			,@Param("custId") String custId
			,@Param("pymAcntId")String pymAcntId
			,@Param("ctrtId") String ctrtId
			,@Param("svcTelNo") String svcTelNo
			,@Param("soAuthList") List<Map<String, Object>> soAuthList
			,@Param("today") String today
			,@Param("lng")String lng
			,@Param("sidx")String sidx
			,@Param("sort") String sort
			,@Param("start") String start
			,@Param("end") String end);

	List<PymAcntVO> getPymAcntList(PymAcntVO pymAcntVO);
	
	int getPymAcntListCount(PymAcntVO pymAcntVO);


}
