package com.ntels.ccbs.common.tag.menu;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.sf.ehcache.Ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 권한 처리 Tag Lib.
 * 
 * @author smyun@ntels.com
 */
public class AuthTag extends BodyTagSupport {

	/** BodyTagSupport serialVersionUID. */
	private static final long serialVersionUID = 4866359152234315166L;

	/** 로그 출력. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** 권한 코드. */
	private String auth;
	
	/**
	 * Tag Lib 출력.
	 * 
	 * @return int
	 * @throws JspException javax.servlet.jsp.JspException
	 */
	public int doEndTag() throws JspException {
 	
    	final BodyContent bodyContent = getBodyContent();
    	
		try {
			if ("Y".equals(auth)) {
				pageContext.getOut().print(bodyContent.getString());
			} else {
				pageContext.getOut().print("");
			}
		} catch (IOException ioe) {
			logger.error("error", ioe);
		}
    	
		return SKIP_BODY;
	}

	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}

}
