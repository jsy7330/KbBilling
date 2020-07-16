package com.ntels.ccbs.appIf.domain.common;

import org.codehaus.jackson.annotate.JsonProperty;

public class AppResHeaderVO {

	/**
	 * 서비스코드
	 */
	@JsonProperty("SVCODE")
	private String svcode;
	
	/**
	 * 오퍼레이션코드
	 */
	@JsonProperty("OPCODE")
	private String opcode;
	
	/**
	 * 채널코드
	 */
	@JsonProperty("CHCODE")
	private String chcode;
	
	/**
	 * 응답 메세지코드
	 */
	@JsonProperty("MSGCODE")
	private String msgcode;
	
	/**
	 * 응답 메세지
	 */
	@JsonProperty("MESSAGE")
	private String message;

	public String getSvcode() {
		return svcode;
	}

	public void setSvcode(String svcode) {
		this.svcode = svcode;
	}

	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}

	public String getChcode() {
		return chcode;
	}

	public void setChcode(String chcode) {
		this.chcode = chcode;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppResHeaderVO [svcode=");
		builder.append(svcode);
		builder.append(", opcode=");
		builder.append(opcode);
		builder.append(", chcode=");
		builder.append(chcode);
		builder.append(", msgcode=");
		builder.append(msgcode);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
}
