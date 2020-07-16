package com.ntels.ccbs.product.service.service.serviceMgt;

import java.util.List;
import java.util.Map;

public interface WorkGrpMngService {

	Map<String, Object> getWorkGrpList(String soId,
	        List<Map<String, Object>> soAuthList, String workGrpNm, String sidx,
	        String sord, int page, int rows, String today, String lng);

	List<Map<String, Object>> getUserList(String svcWrkGrpId, String today,
	        String lng);

	List<Map<String, Object>> getWorkGrpUserList(String svcWrkGrpId,
	        String today, String lng);

	int insertWrokGrp(String workGrpNm, String soId,
	        String useYn, String userId);

	int updateWorkGrp(String workGrpId, String workGrpNm, String useYn,
	        String userId);

	int insertWorkUser(List<Map<String,Object>> addUserList, String userId);

	int updateWorkUser(List<Map<String, Object>> updateDataList,
	        String userId);

	int deleteWorkUser(List<Map<String, Object>> updateDataList,
	        String userId);

	int deleteWorkGrp(String workGrpId);
	
	
}
