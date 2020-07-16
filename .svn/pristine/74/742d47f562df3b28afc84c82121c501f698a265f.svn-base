package com.ntels.ccbs.customer.service.contract.work;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WorkManagementService {
	Map<String,Object> getWorkStatistics(String soId, List<Map<String, Object>> list);

	Map<String, Object> getWorkList(String soId,
	        List<Map<String, Object>> soAuthList, String workStartDate,
	        String workEndDate, String orderTp, String workStat, String custId,
	        String workUserId, String sidx, String sord, int page, int rows, String today, String lng);

	Map<String, Object> getWorkInfo(String wrkId, String orderId, String lng,
	        String today);

	List<Map<String, Object>> getWorkHistList(String orderId, String today,
	        String lng);

	String getWorkAuthR(String orderId, String userId);

	String getWorkAuthU(String wrkId, String orderId, String userId);

	String getWorkAuthC(String wrkId, String orderId, String userId);

	void updateWorkReceipt(String wrkId, String orderId, String comment, String usrId,
	        String lng, String today, Date todayDateType);

	void updateWorkProcess(String wrkId, String orderId, String comment,
	        String usrId, String lng, String today, Date todayDateType);

	void updateWorkCancel(String wrkId, String orderId, String rcptId,  String comment,
	        String usrId, String orgId, String lng, String today, Date todayDateType);

	void updateWorkCmpl(String wrkId, String orderId, String rcptId,
	        String comment, String userId, String orgId, String lng,
	        String today, Date todayDateType, String wrkReqDt);
}
