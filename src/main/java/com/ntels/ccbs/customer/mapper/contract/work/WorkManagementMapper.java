package com.ntels.ccbs.customer.mapper.contract.work;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface WorkManagementMapper {

	Map<String, Object> getWorkStatistics(@Param("soId")String soId, @Param("soAuthList")List<Map<String, Object>> soAuthList);

	Integer getWorkListTotalCnt(@Param("soId")String soId,
	        @Param("soAuthList")List<Map<String, Object>> soAuthList,
	        @Param("workStartDate")String workStartDate,
	        @Param("workEndDate")String workEndDate,
	        @Param("orderTp")String orderTp,
	        @Param("workStat")String workStat,
	        @Param("custId")String custID,
	        @Param("workUserId")String workUserId);

	List<Map<String, Object>> getWorkList(@Param("soId")String soId,
	        @Param("soAuthList")List<Map<String, Object>> soAuthList,
	        @Param("workStartDate")String workStartDate,
	        @Param("workEndDate")String workEndDate,
	        @Param("orderTp")String orderTp,
	        @Param("workStat")String workStat,
	        @Param("custId")String custId,
	        @Param("workUserId")String workUserId,
	        @Param("sidx")String sidx,
	        @Param("sord")String sord,
	        @Param("start")String start,
	        @Param("end")String end,
	        @Param("today")String today,
	        @Param("lng")String lng);

	Map<String, Object> getWorkInfo(@Param("wrkId")String wrkId,
			@Param("orderId")String orderId,
			@Param("lng")String lng,
	        @Param("today")String today);

	List<Map<String, Object>> getWorkHistList(@Param("orderId")String orderId, 
			@Param("today")String today,
			@Param("lng")String lng);

	int checkWorkAuthR(@Param("orderId")String orderId, @Param("userId")String userId);

	int checkWorkDftUser(@Param("wrkId")String wrkId, @Param("orderId")String orderId, @Param("userId")String userId);

	List<Map<String, Object>> getWorkResultList(@Param("wrkId")String wrkId, @Param("orderId")String orderId);

	int checkHasMoreWork(@Param("wrkId")String wrkId, @Param("orderId")String orderId);

	int checkHasPreviousWork(@Param("wrkId")String wrkId, @Param("orderId")String orderId);

	int checkWorkCmpl(@Param("wrkId")String wrkId, @Param("orderId")String orderId);

	void updateWorkInfo(@Param("wrkId")String wrkId,
			@Param("orderId")String orderId,
			@Param("wrkStat")String wrkStat,
	        @Param("usrId")String usrId,
	        @Param("today")String today,
	        @Param("todayDateType")Date todayDateType);

	void insertWrkHist(@Param("wrkHistSeq")String wrkHistSeq,
			@Param("orderId")String orderId,
			@Param("wrkId")String wrkId,
	        @Param("comment")String comment,
	        @Param("stat")String string,
	        @Param("usrId")String usrId,
	        @Param("today")Date todayDateType);
	
	List<Map<String,Object>> getRemainWorkList(@Param("wrkId")String wrkId, @Param("orderId")String orderId);
	
	void updateWorkInfoReqUsr(@Param("wrkId")String wrkId,
			@Param("orderId")String orderId,
			@Param("usrId")String usrId,
	        @Param("wrkReqDt")String wrkReqDt,
	        @Param("todayDateType")Date todayDateType);

	List<Map<String, Object>> getPreviousWorkList(@Param("wrkId")String wrkId, @Param("orderId")String orderId);
	
}

