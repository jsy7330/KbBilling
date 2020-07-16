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
 * 
 * <PRE>
 * 1. ClassName: PathManipulationFilter
 * 2. FileName : PathManipulationFilter.java
 * 3. Package  : com.ntels.ccbs.common.filter
 * 4. Comment  :
 * 5. 작성자   : kgw
 * 6. 작성일   : 2015. 10. 22. 오후 2:49:40
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     kgw :    2015. 10. 22.    : 신규개발
 * </PRE>
 */
public class PathManipulationFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(PathManipulationFilter.class);
	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    	logger.debug("In doFilter PathManipulationFilter  ...............");
    	if (request instanceof HttpServletRequest) {
    		chain.doFilter(new PathManipulationWrapper((HttpServletRequest) request), response);
    	}
        logger.debug("Out doFilter PathManipulationFilter ...............");
    }

}