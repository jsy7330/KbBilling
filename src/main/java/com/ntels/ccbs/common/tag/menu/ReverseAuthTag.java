package com.ntels.ccbs.common.tag.menu;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 권한에 따른 처리 Tag Lib.
 * 
 * @author smyun@ntels.com
 */
public class ReverseAuthTag extends BodyTagSupport {

	/** Add generated version serial ID. */
	private static final long serialVersionUID = 3080610532331848935L;

	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 권한. */
	private String auth;

	@Override
	public int doEndTag() throws JspException {
 	
    	final BodyContent bodyContent = getBodyContent();
    	
		try {
			if ("R".equals(auth)) {
				logger.debug("bodyContent.getString()=>{}", bodyContent.getString());
				
				pageContext.getOut().print(bodyContent.getString());
			} else {
				pageContext.getOut().print("");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();			
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
