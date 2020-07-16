package com.ntels.ccbs.system.service.log.logMng;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.system.domain.log.logMng.AccessLogHistVO;

/**
 * <PRE>
 * 1. ClassName: AccessLogHistService
 * 2. FileName : AccessLogHistService.java
 * 3. Package  : com.ntels.ccbs.system.service.log.logMng
 * 4. Comment  : 접속로그조회 서비스
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 6:02:44
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
public interface AccessLogHistService {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : AccessLogHistService
	 * 3. Comment   :
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 6:02:42
	 * </PRE>
	 *   @return Map<String,Object>
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param lng 언어코드
	 *   @param access 접속로그조회VO
	 */
	Map<String, Object> list(String sidx, String sord, int page, int rows, String lng, AccessLogHistVO access);
	
	List<Map<String, Object>> listExcel(String condUserId,String sdate,String edate, String lng);
	
}