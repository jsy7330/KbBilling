package com.ntels.ccbs.ifg.service;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.common.exception.ServiceException;

public interface InterfaceService{
	
	/**
	 * <PRE>
	 * 1. MethodName: getHomeServiceList
	 * 2. ClassName : InterfaceService
	 * 3. Comment   : 홈서비스ID 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 2:50:36
	 * </PRE>
	 *   @return List<Map<String,Object>> 홈서비스ID List
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @throws ServiceException
	 */
	List<Map<String, Object>> getHomeServiceList(String soId, String custId);
	
	/**
	 * <PRE>
	 * 1. MethodName: sendCtrtChange
	 * 2. ClassName : InterfaceService
	 * 3. Comment   : 서비스허브IF(C-S)
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 4:44:45
	 * </PRE>
	 *   @return boolean 성공여부
	 *   @param requestInfo 요청내용
	 */
	boolean sendCtrtChange(Map<String,Object> requestInfo);
}

