package com.ntels.ccbs.system.service.login;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ntels.ccbs.system.domain.common.service.CountryLanguage;
import com.ntels.ccbs.system.domain.common.service.LoginHistory;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

/**
 * 로그인 처리 Service.
 *
 * @author smyun@ntels.com
 */
public interface LoginService {

	/**
	 * <PRE>
	 * 1. MethodName: login
	 * 2. ClassName : LoginService
	 * 3. Comment   : 로그인 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 26. 오후 3:03:02
	 * </PRE>
	 *   @return Map<String,Object> <br>
	 *   						"RESULT" 결과
	 *   						"MAIN_URL" 메인 URL
	 *   @param text_id login Id
	 *   @param text_nm password
	 *   @param request 요청 HttpServletRequest 객체
	 *   @return
	 */
	public Map<String,Object> updateLogin(String text_id, String text_nm, HttpServletRequest request);



	/**
	 * 로그인 검증 및 세션정보 생성.
	 *
	 * @param user_id 사용자ID
	 * @param password 비밀번호
	 * @param login_gateway_ip 로그인IP
	 * @return SessionUser
	 */
	public SessionUser getSessionUser(String user_id, String password, String login_gateway_ip, String sessionId);

	/**
	 * 로그아웃 처리.
	 *
	 * @param sessionUser 세션정보
	 */
	public void updateLogout(SessionUser sessionUser, String status);

	/**
	 * 로그인 이력 저장.
	 *
	 * @param sessionUser 세션정보
	 * @return LoginHistory
	 */
	public LoginHistory setLoginHistory(SessionUser sessionUser);

	/**
	 * 비밀번호 만료 여부 확인.
	 *
	 * @param user_id 사용자ID
	 * @return boolean
	 */
	public boolean isOverPasswordDueDate(String user_id);

	public boolean isOverPasswordChangePeriod(String user_id);

	/**
	 * 로그인 실패 횟수 초과 여부 확인.
	 *
	 * @param user_id 사용자ID
	 * @return boolean
	 */
	public boolean isOverLoginFailCount(String user_id);

	/**
	 * 사용자 계정 잠금 설정.
	 *
	 * @param user_id 사용자ID
	 */
	public void setAccountLock(String user_id);

	/**
	 * 사용자 잠김 여부 확인.
	 *
	 * @param user_id 사용자ID
	 * @return boolean
	 */
	public boolean isAccountLock(String user_id);

	/**
	 * IP 허용 영역 확인.
	 *
	 * @param sessionUser 세션정보
	 * @param remoteAddress 원격주소
	 * @return boolean
	 */
	public boolean isPassIP_Bandwidth(SessionUser sessionUser, String remoteAddress);
	
	public List<CountryLanguage> listCountryLanguage();
	
	/**
	 * <PRE>
	 * 1. MethodName: getLoginHistoryRegCnt
	 * 2. ClassName : LoginService
	 * 3. Comment   : 로그아웃 기록 여부 확인
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 24. 오전 8:55:01
	 * </PRE>
	 *   @return int
	 *   @param sessionUser
	 *   @return
	 */
	public int getLoginHistoryRegCnt(SessionUser sessionUser);

	public void updateUserInfoConfirm(String userId, String email);
}
