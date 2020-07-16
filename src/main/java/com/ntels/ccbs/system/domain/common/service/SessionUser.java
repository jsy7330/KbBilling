package com.ntels.ccbs.system.domain.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 로그인한 유저의 세션 정보 저장.
 *
 * @author smyun@ntels.com
 */
@XStreamAlias("sessionUser")
public class SessionUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3232265411246637758L;

	/** 사용자 ID. */
	private String userId;
	
	/** 사용자 이름. */
	private String userName;
	
	/** 기본 사용자 그룹 ID. */
	private String userGroupId;
	
	/** 기본 사용자 그룹명. */
	private String userGroupName;
	
	/** 사용자 그룹 래벨. */
	private String userGroupLevel;
	
	/** 메인뷰 **/
	private String mainView;
	
	/** IP대역. */
	private String ipBandwidth;
	
	/** Login Gateway IP. */
	private String loginGatewayIp;

	/** 최종 로그인 일자. */
	private String lastLoginDate;
	
	/** 최종 로그인 시간. */
	private String lastLoginTime;
	
	/** 로그인 실패 횟수. */
	private Integer loginFailCount;
	
	/**
	 * 세션ID 
	 */
	private String sessionId;
	
	
	/**
	 * 사용자 권한
	 */
	private List<String> userRoleList;
	
	/**
	 * 소속 SO_ID
	 */
	private String soId;
	
	
	/**
	 * 소속 SO명
	 */
	private String soNm;
	
	
	/**
	 * 소속 조직 ID
	 */
	private String orgId;
	
	/**
	 * 소속 조직명
	 */
	private String orgNm;
	
	/**
	 * 권한을 가진 SO 리스트
	 */
	private List<Map<String,Object>> soAuthList;

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userGroupId
	 */
	public String getUserGroupId() {
		return userGroupId;
	}

	/**
	 * @param userGroupId the userGroupId to set
	 */
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	/**
	 * @return the userGroupName
	 */
	public String getUserGroupName() {
		return userGroupName;
	}

	/**
	 * @param userGroupName the userGroupName to set
	 */
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	/**
	 * @return the userGroupLevel
	 */
	public String getUserGroupLevel() {
		return userGroupLevel;
	}

	/**
	 * @param userGroupLevel the userGroupLevel to set
	 */
	public void setUserGroupLevel(String userGroupLevel) {
		this.userGroupLevel = userGroupLevel;
	}

	/**
	 * @return the mainView
	 */
	public String getMainView() {
		return mainView;
	}

	/**
	 * @param mainView the mainView to set
	 */
	public void setMainView(String mainView) {
		this.mainView = mainView;
	}

	/**
	 * @return the ipBandwidth
	 */
	public String getIpBandwidth() {
		return ipBandwidth;
	}

	/**
	 * @param ipBandwidth the ipBandwidth to set
	 */
	public void setIpBandwidth(String ipBandwidth) {
		this.ipBandwidth = ipBandwidth;
	}

	/**
	 * @return the loginGatewayIp
	 */
	public String getLoginGatewayIp() {
		return loginGatewayIp;
	}

	/**
	 * @param loginGatewayIp the loginGatewayIp to set
	 */
	public void setLoginGatewayIp(String loginGatewayIp) {
		this.loginGatewayIp = loginGatewayIp;
	}

	/**
	 * @return the lastLoginDate
	 */
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * @param lastLoginDate the lastLoginDate to set
	 */
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * @return the lastLoginTime
	 */
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the loginFailCount
	 */
	public Integer getLoginFailCount() {
		return loginFailCount;
	}

	/**
	 * @param loginFailCount the loginFailCount to set
	 */
	public void setLoginFailCount(Integer loginFailCount) {
		this.loginFailCount = loginFailCount;
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
	 * @return the userRoleList
	 */
	public List<String> getUserRoleList() {
		return userRoleList;
	}

	/**
	 * @param userRoleList the userRoleList to set
	 */
	public void setUserRoleList(List<String> userRoleList) {
		this.userRoleList = userRoleList;
	}

	/**
	 * @return the soId
	 */
	public String getSoId() {
		return soId;
	}

	/**
	 * @param soId the soId to set
	 */
	public void setSoId(String soId) {
		this.soId = soId;
	}

	/**
	 * @return the soNm
	 */
	public String getSoNm() {
		return soNm;
	}

	/**
	 * @param soNm the soNm to set
	 */
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the orgNm
	 */
	public String getOrgNm() {
		return orgNm;
	}

	/**
	 * @param orgNm the orgNm to set
	 */
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	/**
	 * @return the soAuthList
	 */
	public List<Map<String, Object>> getSoAuthList() {
		return soAuthList;
	}

	/**
	 * @param soAuthList the soAuthList to set
	 */
	public void setSoAuthList(List<Map<String, Object>> soAuthList) {
		this.soAuthList = soAuthList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SessionUser [userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userGroupId=");
		builder.append(userGroupId);
		builder.append(", userGroupName=");
		builder.append(userGroupName);
		builder.append(", userGroupLevel=");
		builder.append(userGroupLevel);
		builder.append(", mainView=");
		builder.append(mainView);
		builder.append(", ipBandwidth=");
		builder.append(ipBandwidth);
		builder.append(", loginGatewayIp=");
		builder.append(loginGatewayIp);
		builder.append(", lastLoginDate=");
		builder.append(lastLoginDate);
		builder.append(", lastLoginTime=");
		builder.append(lastLoginTime);
		builder.append(", loginFailCount=");
		builder.append(loginFailCount);
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append(", userRoleList=");
		builder.append(userRoleList);
		builder.append(", soId=");
		builder.append(soId);
		builder.append(", soNm=");
		builder.append(soNm);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", orgNm=");
		builder.append(orgNm);
		builder.append(", soAuthList=");
		builder.append(soAuthList);
		builder.append("]");
		return builder.toString();
	}


	
}
