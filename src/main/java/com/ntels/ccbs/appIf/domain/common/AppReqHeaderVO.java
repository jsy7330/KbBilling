package com.ntels.ccbs.appIf.domain.common;

import org.codehaus.jackson.annotate.JsonProperty;

public class AppReqHeaderVO {

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppReqHeaderVO [svcode=");
		builder.append(svcode);
		builder.append(", opcode=");
		builder.append(opcode);
		builder.append(", chcode=");
		builder.append(chcode);
		builder.append("]");
		return builder.toString();
	}
}
