package com.ntels.ccbs.system.mapper.auth.authMng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.auth.authMng.AuthMngVO;

/**
 * <PRE>
 * 1. ClassName: AuthMngMapper
 * 2. FileName : AuthMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.auth.authMng
 * 4. Comment  : 그룹별권한관리 MAPPER
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 27. 오전 9:20:27
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 27.    : 신규개발
 * </PRE>
 */
@Component
public interface AuthMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: getUserGroupList
	 * 2. ClassName : AuthMngMapper
	 * 3. Comment   : 사용자그룹 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:20:30
	 * </PRE>
	 *   @return Map<String,Object> 사용자그룹 리스트 정보
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param lng 언어코드
	 *   @param auth 그룹별권한관리VO
	 */
	List<AuthMngVO> getUserGroupList(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord, @Param(value = "lng")String lng, @Param(value = "auth")AuthMngVO auth);
	
	/**
	 * <PRE>
	 * 1. MethodName: getAuthList
	 * 2. ClassName : AuthMngMapper
	 * 3. Comment   : 권한정보 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:20:33
	 * </PRE>
	 *   @return List<Map<String,Object>> 사용자그룹아이디에 해당하는 권한정보 
	 *   @param userGroupId 사용자그룹ID
	 *   @param onlyAssignMenuYn 할당메뉴만 조회
	 *   @param stepNo 순서번호
	 *   @param upMenuNo 상위메뉴번호
	 *   @param lng 언어코드
	 */
	List<AuthMngVO> getAuthList(@Param(value = "userGroupId") String userGroupId, 
			@Param(value = "onlyAssignMenuYn") String onlyAssignMenuYn, 
			@Param(value = "stepNo") int stepNo, 
			@Param(value = "upMenuNo") String upMenuNo,
			@Param(value = "lng") String lng);

	/**
	 * <PRE>
	 * 1. MethodName: updateAuth
	 * 2. ClassName : AuthMngMapper
	 * 3. Comment   :
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:20:35
	 * </PRE>
	 *   @return int 수정여부확인
	 *   @param userGroupId 사용자그룹ID
	 *   @param menuNo 메뉴번호
	 *   @param level 메뉴레벨
	 *   @param authRYn 읽기권한
	 *   @param authCYn 생성권한
	 *   @param authUYn 수정권한
	 *   @param authDYn 삭제권한
	 *   @param authPYn 출력권한
	 */
	int updateAuth(@Param(value = "userGroupId")String userGroupId, 
			@Param(value = "menuNo")String menuNo, 
			@Param(value = "authRYn")String authRYn,
			@Param(value = "authCYn")String authCYn, 
			@Param(value = "authUYn")String authUYn, 
			@Param(value = "authDYn")String authDYn, 
			@Param(value = "authPYn")String authPYn);

	/**
	 * <PRE>
	 * 1. MethodName: deleteAuth
	 * 2. ClassName : AuthMngMapper
	 * 3. Comment   : 해당메뉴와 사용자그룹아이디에 해당하는 사용자그룹권한 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:20:39
	 * </PRE>
	 *   @return int 삭제여부
	 *   @param userGroupId 사용자그룹ID
	 *   @param menuNo 메뉴번호
	 */
	int deleteAuth(@Param(value = "userGroupId")String userGroupId, @Param(value = "menuNo")String menuNo);

	/**
	 * <PRE>
	 * 1. MethodName: getMenuList
	 * 2. ClassName : AuthMngMapper
	 * 3. Comment   : 해당메뉴번호가 상위번호이면서 순서가 같은 MENU_ID 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:20:42
	 * </PRE>
	 *   @return List<String> 해당메뉴번호가 상위번호이면서 순서가 같은 MENU_ID 조회
	 *   @param menuNo 메뉴번호
	 *   @param stepNo 순서번호
	 */
	List<String> getMenuList(@Param(value = "menuNo")String menuNo, @Param(value = "stepNo")int stepNo);
	
	/**
	 * <PRE>
	 * 1. MethodName: getUpMenuNo
	 * 2. ClassName : AuthMngMapper
	 * 3. Comment   : 해당메뉴의 상위메뉴번호 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:20:45
	 * </PRE>
	 *   @return String  상위메뉴번호 
	 *   @param menuNo 메뉴번호
	 */
	String getUpMenuNo(@Param(value = "menuNo")String menuNo);

	/**
	 * <PRE>
	 * 1. MethodName: insertAuth
	 * 2. ClassName : AuthMngMapper
	 * 3. Comment   : 권한정보 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:20:48
	 * </PRE>
	 *   @return int 등록여부
	 *   @param userGroupId 사용자그룹ID
	 *   @param menuNo 메뉴번호
	 *   @param level 메뉴레벨
	 *   @param authRYn 읽기권한
	 *   @param authCYn 생성권한
	 *   @param authUYn 수정권한
	 *   @param authDYn 삭제권한
	 *   @param authPYn 출력권한
	 */
	int insertAuth(@Param(value = "userGroupId")String userGroupId, 
			@Param(value = "menuNo")String menuNo, 
			@Param(value = "authRYn")String authRYn,
			@Param(value = "authCYn")String authCYn, 
			@Param(value = "authUYn")String authUYn, 
			@Param(value = "authDYn")String authDYn, 
			@Param(value = "authPYn")String authPYn);

	/**
	 * <PRE>
	 * 1. MethodName: getMenuAuthCnt
	 * 2. ClassName : AuthMngMapper
	 * 3. Comment   : 메뉴갯수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:20:50
	 * </PRE>
	 *   @return int 메뉴개수
	 *   @param userGroupId 사용자그룹ID
	 *   @param menu3Level 상위메뉴번호
	 */
	int getMenuAuthCnt(@Param(value = "userGroupId")String userGroupId,@Param(value = "menuNo")String menu3Level);


	
}