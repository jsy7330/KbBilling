package com.ntels.ccbs.common.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <PRE>
 * 1. ClassName: LogForgingFilter
 * 2. FileName : LogForgingFilter.java
 * 3. Package  : com.ntels.ccbs.common.filter
 * 4. Comment  :
 * 5. 작성자   : kgw
 * 6. 작성일   : 2015. 10. 22. 오후 2:49:06
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     kgw :    2015. 10. 22.    : 신규개발
 * </PRE>
 */
public class LogForgingFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(LogForgingFilter.class);
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
	public void destroy() {
        this.filterConfig = null;
    }

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    	logger.debug("In doFilter LogForgingFilter  ...............");
    	if (request instanceof HttpServletRequest) {
    		chain.doFilter(new LogForgingWrapper((HttpServletRequest) request), response);
    	}
        logger.debug("Out doFilter LogForgingFilter ...............");
    }

}