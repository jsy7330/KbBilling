package com.ntels.ccbs.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <PRE>
 * 1. ClassName: SQLInjectionWrapper
 * 2. FileName : SQLInjectionWrapper.java
 * 3. Package  : com.ntels.ccbs.common.filter
 * 4. Comment  :
 * 5. 작성자   : kgw
 * 6. 작성일   : 2015. 10. 22. 오후 2:42:53
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     kgw :    2015. 10. 22.    : 신규개발
 * </PRE>
 */
public final class SQLInjectionWrapper extends HttpServletRequestWrapper {
	private static Logger logger = LoggerFactory.getLogger(SQLInjectionWrapper.class);

	/**
	 *
	 * 생성자
	 *
	 * @param	servletRequest : servletRequest
	 */
	public SQLInjectionWrapper(HttpServletRequest servletRequest) {
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
		logger.debug("In getParameter SQLInjectionWrapper ........ value .......");
		return clean(value);
	}

	@Override
	public String getHeader(String name) {
		logger.debug("In getHeader .. parameter .......");
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		logger.debug("In getHeader SQLInjectionWrapper ........... value ....");
		return clean(value);
	}

	private String clean(String value) {
		// You'll need to remove the spaces from the html entities below
		logger.debug("In clean SQLInjectionWrapper ...............{}", value);

		StringBuilder ret = new StringBuilder();

		do {
			ret.setLength(0);
			ret.append(value);

			value = ret.toString().replaceAll(" (?i)or ", "")
					.replaceAll(" (?i)and ", "")
					.replaceAll(" (?i)select ", "")
					.replaceAll(" (?i)delete ", "")
					.replaceAll(" (?i)insert ", "")
					.replaceAll(" (?i)drop ", "")
					.replaceAll("--", "__");
		} while(ret.toString().length() != value.length());

		logger.debug("Out clean SQLInjectionWrapper ........ value ....... {}", value);
		return value;
	}
}