package com.ntels.ccbs.system.service.so.soMng;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ntels.ccbs.system.domain.so.soMng.SoMngVO;

public interface SoMngService {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : SoMngService
	 * 3. Comment   :
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:11:51
	 * </PRE>
	 *   @return Map<String,Object> 사업관리 리스트 정보
	 *   @param Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param lng 언어
	 *   @param so 사업관리VO
	 */
	Map<String, Object> list(String sidx, String sord,int page, int rows, String lng, SoMngVO so);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : SoMngService
	 * 3. Comment   :
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:11:59
	 * </PRE>
	 *   @return int 
	 *   @param so 사업관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	int insert(SoMngVO so, HttpServletRequest request);

	/**
	 * <PRE>
	 * 1. MethodName: update
	 * 2. ClassName : SoMngService
	 * 3. Comment   :
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:12:03
	 * </PRE>
	 *   @return int
	 *   @param so 사업관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	int update(SoMngVO so, HttpServletRequest request);

	/**
	 * <PRE>
	 * 1. MethodName: soAuthList
	 * 2. ClassName : SoMngService
	 * 3. Comment   :
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:12:08
	 * </PRE>
	 *   @return List<SoMngVO> 모든 사업ID, 사업명 조회 리스트
	 *   @param so 사업관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	List<SoMngVO> soAuthList(SoMngVO so, HttpServletRequest request);
	
}