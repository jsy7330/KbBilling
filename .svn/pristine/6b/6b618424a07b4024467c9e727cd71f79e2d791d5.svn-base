package com.ntels.ccbs.system.service.auth.authMng;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.system.domain.auth.authMng.AuthMngVO;

/**
 * <PRE>
 * 1. ClassName: AuthMngService
 * 2. FileName : AuthMngService.java
 * 3. Package  : com.ntels.ccbs.system.service.auth.authMng
 * 4. Comment  : 그룹별권한관리 서비스
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 27. 오전 9:17:08
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 27.    : 신규개발
 * </PRE>
 */
public interface AuthMngService {

	/**
	 * <PRE>
	 * 1. MethodName: getUserGroupList
	 * 2. ClassName : AuthMngService
	 * 3. Comment   : 사용자그룹 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:17:11
	 * </PRE>
	 *   @return Map<String,Object> 사용자그룹 리스트 정보
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param lng 언어코드
	 *   @param auth 그룹별권한관리VO
	 */
	Map<String, Object> getUserGroupList(String sidx, String sord, String lng, AuthMngVO auth);

	/**
	 * <PRE>
	 * 1. MethodName: getAuthList
	 * 2. ClassName : AuthMngService
	 * 3. Comment   : 권한정보 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:17:15
	 * </PRE>
	 *   @return List<Map<String,Object>> 사용자그룹아이디에 해당하는 권한정보 
	 *   @param userGroupId 사용자그룹ID
	 *   @param onlyAssignMenuYn 할당메뉴만 조회
	 *   @param lng 언어코드
	 */
	List<Map<String,Object>> getAuthList(String userGroupId,String onlyAssignMenuYn, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: updateAuth
	 * 2. ClassName : AuthMngService
	 * 3. Comment   : 권한정보 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:17:19
	 * </PRE>
	 *   @return void
	 *   @param userGroupId 사용자그룹ID
	 *   @param menuNo 메뉴번호
	 *   @param level 메뉴레벨
	 *   @param authRYn 읽기권한
	 *   @param authCYn 생성권한
	 *   @param authUYn 수정권한
	 *   @param authDYn 삭제권한
	 *   @param authPYn 출력권한
	 */
	void updateAuth(String userGroupId, String menuNo, String level, String authRYn,
	        String authCYn, String authUYn, String authDYn, String authPYn);
	
}