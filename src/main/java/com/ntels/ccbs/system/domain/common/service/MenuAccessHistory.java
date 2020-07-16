package com.ntels.ccbs.system.domain.common.service;

import java.util.Date;

/**
 * <PRE>
 * 1. ClassName: MenuAccessHistory
 * 2. FileName : MenuAccessHistory.java
 * 3. Package  : com.ntels.ccbs.system.domain.common.service
 * 4. Comment  : 작업이력 VO
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 22. 오후 3:58:27
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 22.    : 신규개발
 * </PRE>
 */
public class MenuAccessHistory {

	/*
	 * 작업일자
	 */
	private String workDt;
	
	/*
	 * 일자별 Seq
	 */
	private Integer seq;
	
	/*
	 * 사용자 ID
	 */
	private String userId;
	
	/*
	 * 작업유형
	 */
	private String workType;
	
	/*
	 * 작업일시
	 */
	private Date workDate;
	
	/*
	 * 메뉴번호
	 */
	private String menuNo;
	
	/*
	 * 서버명
	 */
	private String serverName;
	
	/*
	 * 포트
	 */
	private Integer serverPort;
	
	/*
	 * Header Accept정보
	 */
	private String accept;
	
	/*
	 * Http Header User Agent정보
	 */
	private String userAgent;
	
	/*
	 * Http Header Content Type
	 */
	private String contentType;
	

	/*
	 * Remote IP
	 */
	private String remoteAddr;
	
	/*
	 * Session ID
	 */
	private String sessionId;
	
	/*
	 * 요청 Path
	 */
	private String requestPath;
	
	/*
	 * 요청 Method
	 */
	private String requestMethod;
	
	/*
	 * 요청 내용
	 */
	private String payload;
	/**
	 * @return the workDt
	 */
	public String getWorkDt() {
		return workDt;
	}
	/**
	 * @param workDt the workDt to set
	 */
	public void setWorkDt(String workDt) {
		this.workDt = workDt;
	}
	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
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
	 * @return the workType
	 */
	public String getWorkType() {
		return workType;
	}
	/**
	 * @param workType the workType to set
	 */
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	/**
	 * @return the workDate
	 */
	public Date getWorkDate() {
		return workDate;
	}
	/**
	 * @param workDate the workDate to set
	 */
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	/**
	 * @return the menuNo
	 */
	public String getMenuNo() {
		return menuNo;
	}
	/**
	 * @param menuNo the menuNo to set
	 */
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}
	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	/**
	 * @return the serverPort
	 */
	public Integer getServerPort() {
		return serverPort;
	}
	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	/**
	 * @return the accept
	 */
	public String getAccept() {
		return accept;
	}
	/**
	 * @param accept the accept to set
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}
	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}
	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
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
	 * @return the requestPath
	 */
	public String getRequestPath() {
		return requestPath;
	}
	/**
	 * @param requestPath the requestPath to set
	 */
	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}
	/**
	 * @return the requestMethod
	 */
	public String getRequestMethod() {
		return requestMethod;
	}
	/**
	 * @param requestMethod the requestMethod to set
	 */
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	/**
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}
	/**
	 * @param payload the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuAccessHistory [workDt=");
		builder.append(workDt);
		builder.append(", seq=");
		builder.append(seq);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", workType=");
		builder.append(workType);
		builder.append(", workDate=");
		builder.append(workDate);
		builder.append(", menuNo=");
		builder.append(menuNo);
		builder.append(", serverName=");
		builder.append(serverName);
		builder.append(", serverPort=");
		builder.append(serverPort);
		builder.append(", accept=");
		builder.append(accept);
		builder.append(", userAgent=");
		builder.append(userAgent);
		builder.append(", contentType=");
		builder.append(contentType);
		builder.append(", remoteAddr=");
		builder.append(remoteAddr);
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append(", requestPath=");
		builder.append(requestPath);
		builder.append(", requestMethod=");
		builder.append(requestMethod);
		builder.append(", payload=");
		builder.append(payload);
		builder.append("]");
		return builder.toString();
	}
	
	
	


	
	
	
}


