package com.ntels.ccbs.system.mapper.auth.authMng;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.auth.authMng.UserAuthMngVO;

/**
 * <PRE>
 * 1. ClassName: UserAuthMngMapper
 * 2. FileName : UserAuthMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.auth.authMng
 * 4. Comment  : 사용자별 그룹관리 Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 9:51:45
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface UserAuthMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : UserAuthMngMapper
	 * 3. Comment   : 사용자 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:28:01
	 * </PRE>
	 *   @return Integer 사용자 수
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param list 해당user의 세션so_id
	 *   @param today 현재날짜
	 */
	Integer count(@Param(value = "userAuth")UserAuthMngVO userAuth,@Param(value = "soAuthList") List<Map<String, Object>> soAuthList,@Param(value = "today") String today);

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : UserAuthMngMapper
	 * 3. Comment   : 사용자 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:36:02
	 * </PRE>
	 *   @return List<UserAuthMngVO> 사용자리스트
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param lng 언어코드
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param list 해당user의 세션so_id
	 *   @param today 현재날짜
	 */
	List<UserAuthMngVO> list(@Param(value = "userAuth")UserAuthMngVO userAuth, @Param(value = "lng")String lng, 
			@Param(value = "sidx")String sidx, @Param(value = "sord")String sord,@Param(value = "soAuthList") List<Map<String, Object>> soAuthList,@Param(value = "today") String today);

	/**
	 * <PRE>
	 * 1. MethodName: authCount
	 * 2. ClassName : UserAuthMngMapper
	 * 3. Comment   : 사용자별 그룹관리 리스트 수
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:42:29
	 * </PRE>
	 *   @return Integer 사용자별 그룹관리 리스트 수
	 *   @param userAuth 사용자별 그룹관리VO
	 */
	Integer authCount(@Param(value = "userAuth")UserAuthMngVO userAuth);

	/**
	 * <PRE>
	 * 1. MethodName: userAuthList
	 * 2. ClassName : UserAuthMngMapper
	 * 3. Comment   : 사용자별 그룹관리 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:47:31
	 * </PRE>
	 *   @return List<UserAuthMngVO> 사용자별 그룹관리 리스트
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param lng 언어코드
	 */
	List<UserAuthMngVO> userAuthList(@Param(value = "userAuth")UserAuthMngVO userAuth, @Param(value = "lng")String lng);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : UserAuthMngMapper
	 * 3. Comment   : 사용자별 그룹 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:48:01
	 * </PRE>
	 *   @return int 등록여부
	 *   @param userAuth 사용자별 그룹관리VO
	 */
	int insert(@Param(value = "userAuth")UserAuthMngVO userAuth);

	/**
	 * <PRE>
	 * 1. MethodName: deleteAll
	 * 2. ClassName : UserAuthMngMapper
	 * 3. Comment   : 해당 사용자의 그룹 모두 삭제(등록)
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:52:40
	 * </PRE>
	 *   @return void
	 *   @param userAuth 사용자별 그룹관리VO
	 */
	void deleteAll(@Param(value = "userAuth")UserAuthMngVO userAuth);

	/**
	 * <PRE>
	 * 1. MethodName: delete
	 * 2. ClassName : UserAuthMngMapper
	 * 3. Comment   : 사용자의 그룹 삭제(삭제)
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:53:48
	 * </PRE>
	 *   @return int 삭제유무
	 *   @param userAuth 사용자별 그룹관리VO
	 */
	int delete(@Param(value = "userAuth")UserAuthMngVO userAuth);

	/**
	 * <PRE>
	 * 1. MethodName: baseUserGroupId
	 * 2. ClassName : UserAuthMngMapper
	 * 3. Comment   : 해당 user에 해당하는 기본그룹조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:54:12
	 * </PRE>
	 *   @return String 기본그룹ID
	 *   @param userAuth 사용자별 그룹관리VO
	 */
	String baseUserGroupId(@Param(value = "userAuth")UserAuthMngVO userAuth);
	
}