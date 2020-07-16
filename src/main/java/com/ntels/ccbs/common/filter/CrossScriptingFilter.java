package com.ntels.ccbs.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CrossScriptingFilter 필터 정의.
 * 
 * @author smyun@ntels.com
 * @version 1.0.0
 */
public class CrossScriptingFilter implements Filter {
	
	/**
	 * 로그 출력.
	 */
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 필터정의.
	 */
    @SuppressWarnings("unused")
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
    	logger.debug("In doFilter CrossScriptingFilter  ...............");
        chain.doFilter(new CrossScriptingWrapper((HttpServletRequest) request), response);
        logger.debug("Out doFilter CrossScriptingFilter ...............");
    }

}