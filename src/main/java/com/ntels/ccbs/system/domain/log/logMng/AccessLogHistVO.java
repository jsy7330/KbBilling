package com.ntels.ccbs.system.domain.log.logMng;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: AccessLogHistVO
 * 2. FileName : AccessLogHistVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.log.logMng
 * 4. Comment  : 접속로그조회VO
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 6:06:39
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@XStreamAlias("accessLogHist")
public class AccessLogHistVO extends PagingValue {
	/**
	 * 사용자ID
	 */
	private String userId;
	/**
	 * 사용자명
	 */
	private String userName;
	/**
	 * 로그인일자
	 */
	private String loginDate;
	/**
	 * 로그인시간
	 */
	private String loginTime;
	/**
	 * 세션ID
	 */
	private String sessionId;
	/**
	 * 접속IP
	 */
	private String remoteAddr;
	/**
	 * 로그아웃일자
	 */
	private String logoutDate;
	/**
	 * 로그아웃시간
	 */
	private String logoutTime;
	/**
	 * 로그아웃상태
	 */
	private String logoutStatus;
	/**
	 * 로그인일자+로그인시간
	 */
	private String fullLogin;
	/**
	 * 로그아웃일자+로그아웃시간
	 */
	private String fullLogout;
	/**
	 * 조직ID
	 */
	private String orgId;
	/**
	 * 조직명
	 */
	private String orgNm;
	
	/**
	 * 사용자ID 조건
	 */
	private String condUserId;
	/**
	 * 시작날짜
	 */
	private String sdate;
	/**
	 * 끝날짜
	 */
	private String edate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(String logoutDate) {
		this.logoutDate = logoutDate;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getLogoutStatus() {
		return logoutStatus;
	}
	public void setLogoutStatus(String logoutStatus) {
		this.logoutStatus = logoutStatus;
	}
	public String getFullLogin() {
		return fullLogin;
	}
	public void setFullLogin(String fullLogin) {
		this.fullLogin = fullLogin;
	}
	public String getFullLogout() {
		return fullLogout;
	}
	public void setFullLogout(String fullLogout) {
		this.fullLogout = fullLogout;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getCondUserId() {
		return condUserId;
	}
	public void setCondUserId(String condUserId) {
		this.condUserId = condUserId;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}

}