package com.ntels.ccbs.appIf.domain.common;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName(value = "REQUEST")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("REQUEST")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
public class AppRequestVO {

	/**
	 * 요청 Header VO
	 */
	@JsonProperty("HEADER")
	private AppReqHeaderVO header;
	
	/**
	 * 요청 Body VO
	 */
	@JsonProperty("BODY")
	private Map<String,Object> body;

	public AppReqHeaderVO getHeader() {
		return header;
	}

	public void setHeader(AppReqHeaderVO header) {
		this.header = header;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppRequestVO [header=");
		builder.append(header);
		builder.append(", body=");
		builder.append(body);
		builder.append("]");
		return builder.toString();
	}
}
