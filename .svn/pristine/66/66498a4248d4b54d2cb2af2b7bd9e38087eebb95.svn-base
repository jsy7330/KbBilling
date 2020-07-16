package com.ntels.ccbs.system.service.auth.authMng;

import java.util.Map;

import com.ntels.ccbs.system.domain.auth.authMng.SoAuthMngVO;

/**
 * <PRE>
 * 1. ClassName: SoAuthMngService
 * 2. FileName : SoAuthMngService.java
 * 3. Package  : com.ntels.ccbs.system.service.auth.authMng
 * 4. Comment  : 사용자별 사업권한관리 서비스
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 9:42:24
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
public interface SoAuthMngService {

	/**
	 * <PRE>
	 * 1. MethodName: userAuthList
	 * 2. ClassName : SoAuthMngService
	 * 3. Comment   : 사용자별 사업권한관리 리스트 정보조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:43:11
	 * </PRE>
	 *   @return Map<String,Object> 사용자별 사업권한관리 정보
	 *   @param soAuth 사용자별 사업권한관리VO
	 *   @param lng 언어코드
	 */
	Map<String, Object> userAuthList(SoAuthMngVO soAuth, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : SoAuthMngService
	 * 3. Comment   : 사용자별 사업권한관리 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:43:29
	 * </PRE>
	 *   @return int 등록여부
	 *   @param soAuth 사용자별 사업권한관리VO
	 */
	int insert(SoAuthMngVO soAuth);

	/**
	 * <PRE>
	 * 1. MethodName: delete
	 * 2. ClassName : SoAuthMngService
	 * 3. Comment   : 사용자별 사업권한관리 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:43:33
	 * </PRE>
	 *   @return int 삭제여부
	 *   @param soAuth  사용자별 사업권한관리VO
	 */
	int delete(SoAuthMngVO soAuth);

	/**
	 * <PRE>
	 * 1. MethodName: baseSoId
	 * 2. ClassName : SoAuthMngService
	 * 3. Comment   : 해당사용자의 기본사업아이디 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:43:36
	 * </PRE>
	 *   @return String 기본SO_ID
	 *   @param soAuth 사용자별 사업권한관리VO
	 */
	String baseSoId(SoAuthMngVO soAuth);
	
}