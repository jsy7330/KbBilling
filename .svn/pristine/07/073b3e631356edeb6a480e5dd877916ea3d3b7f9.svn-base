package com.ntels.ccbs.appIf.domain.common;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonRootName(value = "RESPONSE")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonTypeName(value = "RESPONSE")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
public class AppResponseVO {

	@JsonProperty("HEADER")
	private AppResHeaderVO header;
	
	@JsonProperty("BODY")
	private Map<String,Object> body;

	public AppResHeaderVO getHeader() {
		return header;
	}

	public void setHeader(AppResHeaderVO header) {
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
		builder.append("AppResponseVO [header=");
		builder.append(header);
		builder.append(", body=");
		builder.append(body);
		builder.append("]");
		return builder.toString();
	}
	
}
