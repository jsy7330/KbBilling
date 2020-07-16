package com.ntels.ccbs.system.mapper.login;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.service.CountryLanguage;
import com.ntels.ccbs.system.domain.common.service.LoginHistory;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;


/**
 * <PRE>
 * 1. ClassName: LoginMapper
 * 2. FileName : LoginMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.login
 * 4. Comment  : 로그인처리 Mapper
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 23. 오후 2:00:05
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 23.    : 신규개발
 * </PRE>
 */
@Component
public interface LoginMapper {

	/**
	 * 세션 유저 조회. 
	 *
	 * @param user_id 사용자ID
	 * @param password 비밀번호
	 * @return SessionUser
	 */
	SessionUser login( @Param("user_id") String user_id,  @Param("password") String password);
	
	/**
	 * 최종 로그인 시간 저장.
	 *
	 * @param sessionUser 세션 유저
	 */
	void updateLastLoginDateTime(@Param("sessionUser") SessionUser sessionUser);
	
	
	/**
	 * 로그인 실패 횟수 저장.
	 *
	 * @param user_id 사용자ID
	 * @return int
	 */
	int	updateLoginFailCount(@Param("user_id") String user_id);
	
	
	/**
	 * 로그인 일자 조회.  
	 *
	 * @param user_id 사용자ID
	 * @return Map<String,String>
	 */
	Map<String, String>	getLoginDate(@Param("user_id") String user_id);
	

	/**
	 * 패스워드 만료 여부 확인.
	 * 
	 * @param user_id 사용자ID
	 * @return int
	 */
	int isOverPasswordDueDate(@Param("user_id") String user_id);

	/**
	 * 로그인 실패 횟수 조회.
	 * 
	 * @param user_id 사용자ID
	 * @return Integer
	 */
	Integer	getLoginFailCount(@Param("user_id") String user_id);

	
	/**
	 * 사용자 계정 잠금 처리.
	 * 
	 * @param user_id 사용자ID
	 * @return int
	 */
	int		setAccountLock(@Param("user_id") String user_id);
	
	
	/**
	 * 사용자 계정 잠금 여부 확인.
	 * 
	 * @param user_id 사용자ID
	 * @return String
	 */
	String	getAccountLock(@Param("user_id") String user_id);
	
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getUserRole
	 * 2. ClassName : PaymentAccountManagementMapper
	 * 3. Comment   : 사용자 ID가 가진 권한 그룹 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 3. 7. 오후 5:48:26
	 * </PRE>
	 *   @return List<String>
	 *   @param user_id 사용자 ID
	 *   @return 권한 그룹 리스트
	 */
	List<String> getUserRole(@Param("user_id") String user_id);
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrgId
	 * 2. ClassName : PaymentAccountManagementMapper
	 * 3. Comment   : 사용자의 조직 ID 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 3. 7. 오후 5:49:22
	 * </PRE>
	 *   @return SO_ID   : 소속 SO_ID
	 *          ,DEPT_ID : 소속 조직 ID
	 *   @param user_id 사용자 ID
	 */
	Map<String,Object> getOrgId(@Param("user_id") String user_id, @Param("today") String today);
	
	/**
	 * <PRE>
	 * 1. MethodName: getSoAuthList
	 * 2. ClassName : PaymentAccountManagementMapper
	 * 3. Comment   : 사용자의 SO권한 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 3. 7. 오후 6:01:06
	 * </PRE>
	 *   @return List<String,Object>> SO_ID, SO_NM
	 *   @param user_id 사용자 ID
	 */
	List<Map<String,Object>> getSoAuthList(@Param("user_id") String user_id);
	
	public List<CountryLanguage> listCountryLanguage();

   	/**
   	 * <PRE>
   	 * 1. MethodName: insertLoginHistory
   	 * 2. ClassName : LoginMapper
   	 * 3. Comment   : 로그인이력
   	 * 4. 작성자    : JHYun
   	 * 5. 작성일    : 2016. 6. 23. 오후 1:59:30
   	 * </PRE>
   	 *   @return int 이력 추가 Cnt
   	 *   @param loginHistory 로그인이력정보
   	 */
   	int insertLoginHistory(@Param("loginHist") LoginHistory loginHistory);

	/**
	 * <PRE>
	 * 1. MethodName: updateLogoutHistory
	 * 2. ClassName : LoginMapper
	 * 3. Comment   : 로그아웃 이력 기록
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 23. 오후 3:11:29
	 * </PRE>
	 *   @return int 수정 Count
	 *   @param sessionUser 세션유저 정보
	 *   @param logoutDate 로그아웃일자
	 *   @param logoutTime 로그아웃시간
	 *   @param logoutStatus 상태(N:정상, T:Session Timeout)
	 */
	int updateLogoutHistory(@Param("sessionUser")SessionUser sessionUser, @Param("logoutDate")String logoutDate, @Param("logoutTime")String logoutTime, @Param("logoutStatus") String status);

	/**
	 * <PRE>
	 * 1. MethodName: isLoginHistoryReg
	 * 2. ClassName : LoginMapper
	 * 3. Comment   : 로긴 히스토리 기록 여부 확인
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 24. 오전 8:56:03
	 * </PRE>
	 *   @return int 기록되지 않은 Login History Count
	 *   @param sessionUser
	 */
	int getLoginHistoryRegCnt(@Param("sessionUser")SessionUser sessionUser);

	UserMngVO getUserInfoConfirm(@Param("userId")String userId, @Param("email")String email);

	void updatePassword(@Param("userId")String userId, @Param("email")String email, @Param("passWord")String passWord);
	
}
