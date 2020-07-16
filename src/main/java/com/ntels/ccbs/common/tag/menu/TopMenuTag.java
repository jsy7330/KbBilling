package com.ntels.ccbs.common.tag.menu;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.MenuTagService;

/**
 * 최상단 메뉴 출력 Tag Lib.
 *
 * @author smyun@ntels.com
 */
public class TopMenuTag extends TagSupport {

	/** Add generated version serial ID. */
	private static final long serialVersionUID = -3180920422727061465L;

	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public int doStartTag() throws JspException {

    	String result = "";

    	HttpSession session = pageContext.getSession();
    	String sessionLanguage = (String) session.getAttribute("sessionLanguage");
    	if (session.getAttribute("session_user")!=null) {
    		SessionUser sessionUser = (SessionUser)session.getAttribute("session_user");
    		String user_id = sessionUser.getUserId();
    		result = makeMenu(user_id, sessionLanguage);
    	}

		try {
			pageContext.getOut().print(result);
		} catch (IOException e) {
			logger.error("error", e);
		}
		return SKIP_BODY;
	}

	/**
	 * 메뉴 생성.
	 *
	 * @param user_id 사용자ID
	 * @return String
	 */
	private String makeMenu(String user_id, String sessionLanguage) {
		
		WebApplicationContext applicationContext = RequestContextUtils.getWebApplicationContext(
				pageContext.getRequest(),
				pageContext.getServletContext());
		
		MenuTagService menuTagService = (MenuTagService)applicationContext.getBean("menuTagServiceImpl");
		
		
		String result = menuTagService.getTopMenu(user_id, sessionLanguage); // db data
		return result;

	}

}
