package com.ntels.ccbs.system.mapper.user.userMng;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;

@Component
public interface UserMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 사용자 리스트 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:06:04
	 * </PRE>
	 *   @return Integer 사용자 리스트 수
	 *   @param user 사용자VO
	 *   @param soAuthList  해당user에 해당하는 사업ID
	 *   @param today 현재날짜
	 */
	Integer count(@Param(value = "user")UserMngVO user, @Param(value = "soAuthList")List<Map<String, Object>> soAuthList, @Param(value = "today")String today);

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 사용자 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:08:43
	 * </PRE>
	 *   @return List<UserMngVO> 사용자 리스트
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param lng 언어코드
	 *   @param user 사용자관리VO
	 *   @param soAuthList 해당user에 해당하는 사업ID
	 *   @param today 현재날짜
	 */
	List<UserMngVO> list(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord, @Param(value = "start")String start,
			@Param(value = "end")String end, @Param(value = "lng")String lng, @Param(value = "user")UserMngVO user,
			@Param(value = "soAuthList")List<Map<String, Object>> soAuthList, @Param(value = "today")String today);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 사용자 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:10:23
	 * </PRE>
	 *   @return int 등록여부
	 *   @param user 사용자VO
	 */
	int insert(UserMngVO user);

	/**
	 * <PRE>
	 * 1. MethodName: update
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 사용자 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:10:49
	 * </PRE>
	 *   @return int 수정여부
	 *   @param user 사용자VO
	 */
	int update(UserMngVO user);

	/**
	 * <PRE>
	 * 1. MethodName: checkUserId
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 사용자ID 중복체크
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:11:10
	 * </PRE>
	 *   @return int 사용자ID 중복 여부
	 *   @param user 사용자VO
	 */
	int checkUserId(UserMngVO user);

	/**
	 * <PRE>
	 * 1. MethodName: insertUserAuth
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 해당 user_id에 해당하는 기본그룹ID 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:12:54
	 * </PRE>
	 *   @return void
	 *   @param user 사용자VO
	 */
	void insertUserAuth(UserMngVO user);

	/**
	 * <PRE>
	 * 1. MethodName: insertSoAuth
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 해당 user_id에 해당하는 기본SO 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:13:59
	 * </PRE>
	 *   @return void
	 *   @param user 사용자VO
	 */
	void insertSoAuth(UserMngVO user);

	/**
	 * <PRE>
	 * 1. MethodName: selectSoId
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 조직id에 해당하는 사업아이디를 조회해 so권한에 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:14:57
	 * </PRE>
	 *   @return String 조횐한 사업ID
	 *   @param user 사용자VO
	 *   @param today 현재날짜
	 *   @return
	 */
	String selectSoId(@Param(value = "user")UserMngVO user,@Param(value = "today") String today);

	/**
	 * <PRE>
	 * 1. MethodName: userAuthCount
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 해당하는 사용자그룹id와 사용자id가 없을때 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:16:06
	 * </PRE>
	 *   @return int 해당하는 사용자그룹id와 사용자id에 해당하는 기본그룹 유무 확인
	 *   @param user 사용자VO
	 */
	int userAuthCount(UserMngVO user);

	/**
	 * <PRE>
	 * 1. MethodName: getUserInfo
	 * 2. ClassName : UserMngMapper
	 * 3. Comment   : 사용자정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 7. 14. 오전 11:23:35
	 * </PRE>
	 *   @return UserMngVO 사용자VO
	 *   @param userId 사용자ID
	 *   @return
	 */
	UserMngVO getUserInfo(@Param(value="userId")String userId);

}