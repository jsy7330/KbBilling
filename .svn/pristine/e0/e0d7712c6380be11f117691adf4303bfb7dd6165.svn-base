package com.ntels.ccbs.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <PRE>
 * 1. ClassName: LogForgingWrapper
 * 2. FileName : LogForgingWrapper.java
 * 3. Package  : com.ntels.ccbs.common.filter
 * 4. Comment  :
 * 5. 작성자   : kgw
 * 6. 작성일   : 2015. 10. 22. 오후 2:49:20
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     kgw :    2015. 10. 22.    : 신규개발
 * </PRE>
 */
public final class LogForgingWrapper extends HttpServletRequestWrapper {
	private static Logger logger = LoggerFactory.getLogger(LogForgingWrapper.class);
	/**
	 *
	 * 생성자
	 *
	 * @param		servletRequest : servletRequest
	 */
	public LogForgingWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
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
			encodedValues[i] = clean(values[i]);
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
		logger.debug("In getParameter LogForgingWrapper ........ value .......");
		return clean(value);
	}

	@Override
	public String getHeader(String name) {
		logger.debug("In getHeader .. parameter .......");
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		logger.debug("In getHeader LogForgingWrapper ........... value ....");
		return clean(value);
	}

	private String clean(String value) {
		// You'll need to remove the spaces from the html entities below
		logger.debug("In cleanXSS LogForgingWrapper ...............{}", value);

		StringBuilder ret = new StringBuilder();

		do {
			ret.setLength(0);
			ret.append(value);

			value = ret.toString().replace("\\n", "");
			value = value.replace("\\r", "");
			//value = value.replace("INFO", "");  주석처리
			//value = value.replace("DEBUG", "");  주석처리
		} while(ret.toString().length() != value.length());

		logger.debug("Out clean LogForgingWrapper ........ value ....... {}", value);
		return value;
	}
}