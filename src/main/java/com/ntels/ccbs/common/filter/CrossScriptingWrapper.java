package com.ntels.ccbs.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * XSS 대비를 위한 HttpServletRequestWrapper 구현체.
 *
 * @author smyun@ntels.com
 * @version 1.0.0
 */
public final class CrossScriptingWrapper extends HttpServletRequestWrapper{

	private static Logger logger = LoggerFactory.getLogger(CrossScriptingWrapper.class);
	
	public CrossScriptingWrapper(HttpServletRequest request) {
		super(request);
	}
	
	
	@Override
	public String[] getParameterValues(String parameter) {
		logger.debug("In getParameterValues .. parameter .......");
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		logger.debug("In getParameter .. parameter .......");
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		logger.debug("In getParameter CrossScriptingWrapper ........ value .......");
		return cleanXSS(value);
	}

	@Override
	public String getHeader(String name) {
		logger.debug("In getHeader .. parameter .......");
		String value = super.getHeader(name);
		if (value == null)
			return null;
		logger.debug("In getHeader CrossScriptingWrapper ........... value ....");
		return cleanXSS(value);
	}
	
	
	private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		logger.debug("In cleanXSS CrossScriptingWrapper ...............{}", value);

		StringBuilder ret = new StringBuilder();

		do {
			ret.setLength(0);
			ret.append(value);
			
			value = ret.toString()
					.replaceAll("eval\\((.*)\\)", "")
					.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"")
					.replaceAll("(?i)<script.*?>.*?<script.*?>", "")
					.replaceAll("(?i)<script.*?>.*?</script.*?>", "")
					.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "")
					.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
		}while(ret.toString().length() != value.length());
		
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");

		logger.debug("Out cleanXSS CrossScriptingWrapper ........ value ....... {}", value);

		return value;
	}
	
	


	
	

	
	
	
	
	
		
}