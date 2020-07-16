package com.ntels.ccbs.system.service.auth.authMng;

import java.util.Map;

import com.ntels.ccbs.system.domain.auth.authMng.UserAuthMngVO;

/**
 * <PRE>
 * 1. ClassName: UserAuthMngService
 * 2. FileName : UserAuthMngService.java
 * 3. Package  : com.ntels.ccbs.system.service.auth.authMng
 * 4. Comment  : 사용자별 그룹관리 서비스
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 22. 오후 5:19:34
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 22.    : 신규개발
 * </PRE>
 */
public interface UserAuthMngService {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : UserAuthMngService
	 * 3. Comment   : 사용자 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:20:04
	 * </PRE>
	 *   @return Map<String,Object> 사용자리스트 정보
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param lng 언어코드
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 */
	Map<String, Object> list(UserAuthMngVO userAuth, String lng, String sidx, String sord);

	/**
	 * <PRE>
	 * 1. MethodName: authList
	 * 2. ClassName : UserAuthMngService
	 * 3. Comment   : 사용자별 그룹관리 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:22:05
	 * </PRE>
	 *   @return Map<String,Object> 사용자별 그룹관리 리스트 정보
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param lng 언어코드
	 */
	Map<String, Object> authList(UserAuthMngVO userAuth, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : UserAuthMngService
	 * 3. Comment   : 사용자별 그룹 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:22:51
	 * </PRE>
	 *   @return int 등록여부
	 *   @param userAuth 사용자별 그룹관리VO
	 */
	int insert(UserAuthMngVO userAuth);

	/**
	 * <PRE>
	 * 1. MethodName: delete
	 * 2. ClassName : UserAuthMngService
	 * 3. Comment   : 사용자별 그룹 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:23:19
	 * </PRE>
	 *   @return int 삭제여부
	 *   @param userAuth 사용자별 그룹관리VO
	 */
	int delete(UserAuthMngVO userAuth);

	/**
	 * <PRE>
	 * 1. MethodName: baseUserGroupId
	 * 2. ClassName : UserAuthMngService
	 * 3. Comment   : 해당 user에 해당하는 기본그룹조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:23:42
	 * </PRE>
	 *   @return String 기본그룹ID
	 *   @param userAuth 사용자별 그룹관리VO
	 */
	String baseUserGroupId(UserAuthMngVO userAuth);

}