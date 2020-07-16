package com.ntels.ccbs.common.tag.menu;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.MenuTagService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * Menu Tag Lib.
 *
 * @author smyun@ntels.com
 */
public class MenuTag extends TagSupport {

	/** Add generated version serial ID. */
	private static final long serialVersionUID = -4737401661375830268L;

	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 2레벨 메뉴 No */
	private String menuNo;
	/** 선택 메뉴 번호. */
	private String selectMenuNo;
	/** 선택 메뉴명  */
	private String selectMenuNm;
	/** 최상위 메뉴NO  */
	private String topMenuNo;
	/** 최상위 메뉴명  */
	private String topMenuNm;
	

	@SuppressWarnings("rawtypes")
	@Override
	public int doStartTag() throws JspException {

    	String result = "";

    	HttpSession session = pageContext.getSession();
    	
    	
    	String sessionLanguage = (String) session.getAttribute("sessionLanguage");
    	if (session.getAttribute("session_user")!=null) {
    		SessionUser sessionUser = (SessionUser)session.getAttribute("session_user");
    		String user_id = sessionUser.getUserId();
    		result=makeMenu(user_id, sessionLanguage);

    		WebApplicationContext _applicationContext = RequestContextUtils.getWebApplicationContext(
    				pageContext.getRequest(),
    				pageContext.getServletContext());
    		
    		if (!StringUtils.isEmpty(selectMenuNo)) {
    			String sessionKey = user_id + selectMenuNo + sessionLanguage;
    			Ehcache ehcache = (Ehcache)_applicationContext.getBean("ehcache");
	    		Cache cacheAuth = ehcache.getCacheManager().getCache("authMenuCache");
	    		Element menuAuthElement = cacheAuth.get(sessionKey);
	    		if(menuAuthElement != null){
	    			Map<String,Object> munuAuth = (HashMap) menuAuthElement.getObjectValue();

		    		if (munuAuth.get(selectMenuNo)!=null) {
		    			Map<String,Object> auth = (HashMap)munuAuth.get(selectMenuNo);
						session.setAttribute("menuAuthC", auth.get("AUTH_C"));
						session.setAttribute("menuAuthD", auth.get("AUTH_D"));
						session.setAttribute("menuAuthP", auth.get("AUTH_P"));
						session.setAttribute("menuAuthR", auth.get("AUTH_R"));
						session.setAttribute("menuAuthU", auth.get("AUTH_U"));
					}else{
		    			session.setAttribute("menuAuthC", "");
						session.setAttribute("menuAuthD", "");
						session.setAttribute("menuAuthP", "");
						session.setAttribute("menuAuthR", "");
						session.setAttribute("menuAuthU", "");
		    		}
	    		}else{
	    			session.setAttribute("menuAuthC", "");
					session.setAttribute("menuAuthD", "");
					session.setAttribute("menuAuthP", "");
					session.setAttribute("menuAuthR", "");
					session.setAttribute("menuAuthU", "");
	    		}
    		}
    		
    		if(logger.isDebugEnabled()){
    			/*
    			 * Session Attribute Log 출력
    			 */
    			if(session != null){
    				Enumeration attribuuteNames = session.getAttributeNames();
    				while(attribuuteNames != null && attribuuteNames.hasMoreElements()){
    					String attrinuteName = (String)attribuuteNames.nextElement();
    					StringBuilder sb = new StringBuilder();
    					sb.append("[");
    					sb.append(session.getAttribute(attrinuteName) == null ? "" : session.getAttribute(attrinuteName).toString());
    					sb.append("]");
    					logger.debug("Session Parameter\t{}\t : {} ", attrinuteName,  sb.toString());
    				}
    			}
    		}
    	}

		try {
			Enumeration<String> keyEnum = pageContext.getRequest().getParameterNames();
			while(keyEnum.hasMoreElements()){
				String key = keyEnum.nextElement();
				if("menuNo".equals(key) || "selectMenuNo".equals(key) || "selectMenuNm".equals(key) || "topMenuNo".equals(key) || "topMenuNm".equals(key)){
					continue;
				}
				
				logger.debug("Page Open Parameter : " + key + " / " + pageContext.getRequest().getParameter(key));
				pageContext.setAttribute(key, pageContext.getRequest().getParameter(key), PageContext.REQUEST_SCOPE);
			}
			pageContext.getOut().print(result);
		} catch (IOException e) {

			e.printStackTrace();
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
		
		String result = menuTagService.getLeftMenu(user_id, topMenuNo, menuNo, selectMenuNo, sessionLanguage); // db data
		return result;

	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getSelectMenuNo() {
		return selectMenuNo;
	}

	public void setSelectMenuNo(String selectMenuNo) {
		this.selectMenuNo = selectMenuNo;
	}

	public String getSelectMenuNm() {
		return selectMenuNm;
	}

	public void setSelectMenuNm(String selectMenuNm) {
		this.selectMenuNm = selectMenuNm;
	}

	public String getTopMenuNo() {
		return topMenuNo;
	}

	public void setTopMenuNo(String topMenuNo) {
		this.topMenuNo = topMenuNo;
	}

	public String getTopMenuNm() {
		return topMenuNm;
	}

	public void setTopMenuNm(String topMenuNm) {
		this.topMenuNm = topMenuNm;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public void setValue(String k, Object o) {
		// TODO Auto-generated method stub
		super.setValue(k, o);
	}




}
