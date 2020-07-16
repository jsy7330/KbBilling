package com.ntels.ccbs.system.domain.common.service;


/**
 * <PRE>
 * 1. ClassName: LoginHistory
 * 2. FileName : LoginHistory.java
 * 3. Package  : com.ntels.ccbs.system.domain.common.service
 * 4. Comment  : 로그인 이력 VO
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 23. 오후 1:56:08
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 23.    : 신규개발
 * </PRE>
 */
public class LoginHistory {
	
	/**
	 * 로그인ID
	 */
	private String userId;
	
	/**
	 * 로그인일자(YYYYMMDD) 
	 */
	private String loginDate;
	
	/**
	 * 로그인시간(HH24MISS) 
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
	 * 로그아웃일자(YYYYMMDD) 
	 */
	private String logoutDate;
	/**
	 * 로그아웃시간(HH24MISS)
	 */
	private String logoutTime;
	
	/**
	 * 로그아웃상태(N:정상로그아웃, T:Session Timeout)
	 */
	private String logoutStatus;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the loginDate
	 */
	public String getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate the loginDate to set
	 */
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * @return the loginTime
	 */
	public String getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the remoteAddr
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}

	/**
	 * @param remoteAddr the remoteAddr to set
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	/**
	 * @return the logoutDate
	 */
	public String getLogoutDate() {
		return logoutDate;
	}

	/**
	 * @param logoutDate the logoutDate to set
	 */
	public void setLogoutDate(String logoutDate) {
		this.logoutDate = logoutDate;
	}

	/**
	 * @return the logoutTime
	 */
	public String getLogoutTime() {
		return logoutTime;
	}

	/**
	 * @param logoutTime the logoutTime to set
	 */
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	/**
	 * @return the logoutStatus
	 */
	public String getLogoutStatus() {
		return logoutStatus;
	}

	/**
	 * @param logoutStatus the logoutStatus to set
	 */
	public void setLogoutStatus(String logoutStatus) {
		this.logoutStatus = logoutStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginHistory [userId=");
		builder.append(userId);
		builder.append(", loginDate=");
		builder.append(loginDate);
		builder.append(", loginTime=");
		builder.append(loginTime);
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append(", remoteAddr=");
		builder.append(remoteAddr);
		builder.append(", logoutDate=");
		builder.append(logoutDate);
		builder.append(", logoutTime=");
		builder.append(logoutTime);
		builder.append(", logoutStatus=");
		builder.append(logoutStatus);
		builder.append("]");
		return builder.toString();
	}
	
	

}
