package com.ntels.ccbs.product.mapper.service.serviceMgt;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WorkGrpMngMapper {

	Integer getWorkGrpListTotalCnt(@Param("soId")String soId,
	        @Param("soAuthList")List<Map<String, Object>> soAuthList,
	        @Param("workGrpNm")String workGrpNm);

	List<Map<String,Object>> getWorkGrpList(@Param("soId")String soId,
	        @Param("soAuthList")List<Map<String, Object>> soAuthList,
	        @Param("workGrpNm")String workGrpNm,
	        @Param("sidx")String sidx,
	        @Param("sord")String sord,
	        @Param("start")String start,
	        @Param("end")String end,
	        @Param("today")String today,
	        @Param("lng")String lng);

	List<Map<String, Object>> getUserList(@Param("svcWrkGrpId")String svcWrkGrpId, 
			@Param("today")String today,
	        @Param("lng")String lng);

	List<Map<String, Object>> getWorkGrpUserList(@Param("svcWrkGrpId")String svcWrkGrpId, 
			@Param("today")String today,
	        @Param("lng")String lng);

	int insertWorkGrp(@Param("seq")String seq,
			@Param("workGrpNm")String workGrpNm,
			@Param("soId")String soId,
			@Param("useYn")String useYn,
	        @Param("userId")String userId,
	        @Param("today")Date today);

	int updateWorkGrp(@Param("workGrpId")String workGrpId,
			@Param("workGrpNm")String workGrpNm,
			@Param("useYn")String useYn,
	        @Param("userId")String userId,
	        @Param("today")Date today);

	int insertWorkUser(@Param("workGrpId")String workGrpId,
			@Param("addUserId")String addUserId,
			@Param("userId")String userId,
			@Param("today")Date today);

	int updateWorkUser(@Param("workGrpId")String workGrpId,
			@Param("updateUserId")String updateUserId,
			@Param("useYn")String useYn,
			@Param("smsYn")String smsYn,
			@Param("userId")String userId,
			@Param("today")Date today);

	int deleteWorkUser(@Param("workGrpId")String workGrpId,
			@Param("deleteUserId")String deleteUserId);

	int deleteWorkGrp(@Param("workGrpId")String workGrpId);

	int deleteWorkGrpDtl(@Param("workGrpId")String workGrpId);
	
	
}



