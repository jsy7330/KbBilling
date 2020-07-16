package com.ntels.ccbs.appIf.domain.common;

import java.util.Date;

public class IfLogVO {

	private String seq; /*일련번호*/
	private String remoteAddr; /*요청IP주소*/
	private String serverName; /*서버명*/
	private String serverPort; /*포트*/
	private String sessionId; /*SESSION ID*/
	private String reqPath; /*호출경로*/
	private String reqMethod; /*메서드*/
	private Date reqDate; /*요청일시*/
	private Date resDate; /*응답일시*/
	private String svCode; /*서비스코드*/
	private String opCode; /*오퍼레이션코드*/
	private String chCode; /*채널*/
	private String userId; /*사용자ID*/
	private String msgcode; /*결과코드*/
	private String message; /*결과메세지*/
	private String reqMsg; /*요청메세지*/
	private String resMsg; /*응답메세지*/
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
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
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getReqPath() {
		return reqPath;
	}
	public void setReqPath(String reqPath) {
		this.reqPath = reqPath;
	}
	public String getReqMethod() {
		return reqMethod;
	}
	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public String getSvCode() {
		return svCode;
	}
	public void setSvCode(String svCode) {
		this.svCode = svCode;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	public String getChCode() {
		return chCode;
	}
	public void setChCode(String chCode) {
		this.chCode = chCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMsgcode() {
		return msgcode;
	}
	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReqMsg() {
		return reqMsg;
	}
	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	
}
