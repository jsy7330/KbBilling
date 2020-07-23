package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.common.CustSearchVO;

@Component
public interface CustomerSearchPopupMapper {
	

	int getCustInfoTotalCnt(@Param("soId")String soId
			,@Param("custNm") String custNm
			,@Param("custId") String custId
			,@Param("corpRegNo")String corpRegNo
			,@Param("bizNo")String bizNo
			,@Param("corpRegNoList") List<String> corpRegNoList
			,@Param("soAuthList") List<Map<String, Object>> soAuthList);

	int getCustInfoWithCtrtTotalCnt(@Param("soId")String soId
			,@Param("custNm") String custNm
			,@Param("custId") String custId
			,@Param("corpRegNo")String corpRegNo
			,@Param("bizNo")String bizNo
			,@Param("ctrtId") String ctrtId
			,@Param("svcTelNo") String svcTelNo
			,@Param("corpRegNoList") List<String> corpRegNoList
			,@Param("soAuthList") List<Map<String, Object>> soAuthList);

	int getCustCtrtListCnt(@Param("soId")String soId
			,@Param("custNm") String custNm);


	List<CustSearchVO> getCustInfoList(@Param("soId")String soId
			,@Param("custNm") String custNm
			,@Param("custId") String custId
			,@Param("corpRegNo")String corpRegNo
			,@Param("bizNo")String bizNo
			,@Param("corpRegNoList") List<String> corpRegNoList
			,@Param("soAuthList") List<Map<String, Object>> soAuthList
			,@Param("today") String today
			,@Param("lng")String lng
			,@Param("sidx")String sidx
			,@Param("sort") String sort
			,@Param("start") String start
			,@Param("end") String end);

	List<CustSearchVO> getCustInfoWithList(@Param("soId")String soId
			,@Param("custNm") String custNm
			,@Param("custId") String custId
			,@Param("corpRegNo")String corpRegNo
			,@Param("bizNo")String bizNo
			,@Param("ctrtId") String ctrtId
			,@Param("svcTelNo") String svcTelNo
			,@Param("corpRegNoList") List<String> corpRegNoList
			,@Param("soAuthList") List<Map<String, Object>> soAuthList
			,@Param("today") String today
			,@Param("lng")String lng
			,@Param("sidx")String sidx
			,@Param("sort") String sort
			,@Param("start") String start
			,@Param("end") String end);

	List<Map<String, Object>> getCustCtrtList(@Param("soId")String soId
			,@Param("custNm") String custNm
			,@Param("today") String today
			,@Param("lng")String lng
			,@Param("sidx")String sidx
			,@Param("sort") String sort
			,@Param("start") String start
			,@Param("end") String end);
}
