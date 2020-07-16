package com.ntels.ccbs.system.domain.log.logMng;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: ProcessLogHistVO
 * 2. FileName : ProcessLogHistVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.log.logMng
 * 4. Comment  :
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 6:18:40
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@XStreamAlias("processLogHist")
public class ProcessLogHistVO extends PagingValue {
	/**
	 * 일련번호
	 */
	private int seq;
	/**
	 * 작업일자
	 */
	private String workDt;
	/**
	 * 사용자아이디
	 */
	private String userId;
	/**
	 * 사용자명
	 */
	private String userName;
	/**
	 * 작업유형
	 */
	private String workType;
	/**
	 * 작업일시
	 */
	private Date workDate;
	/**
	 * 메뉴번호
	 */

	private String menuNo;
	/**
	 * 메뉴명
	 */
	private String menuName;
	/**
	 * 서버명
	 */
	private String serverName;
	/**
	 * 서버포트
	 */
	private String serverPort;
	/**
	 * HEADER AGENT
	 */
	private String accept;
	/**
	 * HEADER USER AGENT
	 */
	private String userAgent;
	/**
	 * HEADER CONTENT_TYPE
	 */
	private String contentType;
	/**
	 * REMOTE ADDR
	 */
	private String remoteAddr;
	/**
	 * SESSION ID
	 */
	private String sessionId;
	/**
	 * REQUEST_PATH
	 */
	private String requestPath;
	/**
	 * METHOD
	 */
	private String requestMethod;
	/**
	 * 내용
	 */
	private String payload;
	
	/**
	 * 사용자아이디 조건
	 */
	private String condUserId;
	/**
	 * 작업유형 조건
	 */
	private String condWorkType;
	/**
	 * 시작일자
	 */
	private String sdate;
	/**
	 * 종료일자
	 */
	private String edate;
	/**
	 * 세션아이디 조건
	 */
	private String condSessionId;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWorkDt() {
		return workDt;
	}
	public void setWorkDt(String workDt) {
		this.workDt = workDt;
	}
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
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getRequestPath() {
		return requestPath;
	}
	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getCondUserId() {
		return condUserId;
	}
	public void setCondUserId(String condUserId) {
		this.condUserId = condUserId;
	}
	public String getCondWorkType() {
		return condWorkType;
	}
	public void setCondWorkType(String condWorkType) {
		this.condWorkType = condWorkType;
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
	public String getCondSessionId() {
		return condSessionId;
	}
	public void setCondSessionId(String condSessionId) {
		this.condSessionId = condSessionId;
	}
	
}