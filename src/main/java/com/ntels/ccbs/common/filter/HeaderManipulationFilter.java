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
 * 1. ClassName: HeaderManipulationFilter
 * 2. FileName : HeaderManipulationFilter.java
 * 3. Package  : com.ntels.ccbs.common.filter
 * 4. Comment  :
 * 5. 작성자   : kgw
 * 6. 작성일   : 2015. 10. 22. 오후 2:43:43
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     kgw :    2015. 10. 22.    : 신규개발
 * </PRE>
 */
public class HeaderManipulationFilter implements Filter {
	private static Logger LOG = LoggerFactory.getLogger(HeaderManipulationFilter.class);
	private FilterConfig filterConfig;

	//Linefeed 예외항목 정의
	private static final List<String> EXCEPT_LINE_FEED_URL = Arrays.asList(new String[]{
						"/MagicInfoWebAuthorClient/LFDSave",
						"/MagicInfoWebAuthorClient"});

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
    	LOG.debug("In doFilter HeaderManipulationFilter  ...............");
    	// prevent kgw


    	if (request instanceof HttpServletRequest) {
    		HttpServletRequest req = (HttpServletRequest) request;

    		if (EXCEPT_LINE_FEED_URL.contains(StringUtils.defaultString(req.getRequestURI()))) {
    			chain.doFilter(request, response);
    		} else {
    			chain.doFilter(new HeaderManipulationWrapper(req), response);
    		}
    	}
    	LOG.debug("Out doFilter HeaderManipulationFilter ...............");
    }

}