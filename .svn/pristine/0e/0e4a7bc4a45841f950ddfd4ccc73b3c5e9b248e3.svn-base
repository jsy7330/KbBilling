package com.ntels.ccbs.common.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.login.LoginService;

@Component
public class SessionListener implements HttpSessionListener {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		logger.info("Session Created : {}", session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		HttpSession session = se.getSession();
		SessionUser sessionUser = (SessionUser)session.getAttribute("session_user");
		if (sessionUser!=null) {
			 ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
			
			LoginService loginService = (LoginService) ctx.getBean("loginServiceImpl");
			if(loginService.getLoginHistoryRegCnt(sessionUser) == 0){
				loginService.updateLogout(sessionUser, "T");
			}
		}
		logger.info("Session Destroyed : {}", session.getId());
	}
}
