package com.ntels.ccbs.system.service.log.logMng;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.system.domain.log.logMng.ProcessLogHistVO;

/**
 * <PRE>
 * 1. ClassName: ProcessLogHistService
 * 2. FileName : ProcessLogHistService.java
 * 3. Package  : com.ntels.ccbs.system.service.log.logMng
 * 4. Comment  : 사용이력조회 서비스
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 6:14:50
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
public interface ProcessLogHistService {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : ProcessLogHistService
	 * 3. Comment   : 사용이력조회 리스트 정보 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 6:14:52
	 * </PRE>
	 *   @return Map<String,Object> 사용이력조회 리스트
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param lng 사용언어
	 *   @param process 사용이력VO
	 */
	Map<String, Object> list(String sidx, String sord, int page, int rows, String lng, ProcessLogHistVO process);

	/**
	 * <PRE>
	 * 1. MethodName: listExcel
	 * 2. ClassName : ProcessLogHistService
	 * 3. Comment   : 사용이력조회 엑셀다운로드
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 28. 오후 2:21:29
	 * </PRE>
	 *   @return List<Map<String,Object>> 사용이력조회 엑셀다운로드
	 *   @param condUserId 사용자아이디 조건
	 *   @param sdate 시작날짜
	 *   @param edate 마지막 날짜
	 *   @param lng 언어코드
	 *   @param condSessionId 세션아이디 조건
	 *   @param condWorkType 사용유형 조건
	 */
	List<Map<String, Object>> listExcel(String condUserId, String sdate, String edate, String lng,
			String condSessionId, String condWorkType);
	
}