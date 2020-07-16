/*****************************************************************************************
 * Copyright (c) 2011 nTels, All rights reserved.
 *
 * com.ntels.ccbs.common.interceptor.LoginInterceptor.java
 * 세션을 가지고 있는지 체크하고 없으면 로그인 페이지로 이동시키기 위한 클래스
 *
 * 사용 방법:
 * <pre>
 *    스프링 인터셉터에 등록합니다.
 * </pre>
 *
 * @저자  : 오준호
 * @버전  : 1.0.0
 * @작성일 : 2011. 10. 4
 *
 * @작업 완료
 *    2011. 10. 4 :
 * @작업중
 *
 ******************************************************************************************/
package com.ntels.ccbs.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ntels.ccbs.system.domain.common.service.SessionUser;



/**
 *
 * <PRE>
 * 1. ClassName: LoginInterceptor
 * 2. FileName : LoginInterceptor.java
 * 3. Package  : com.ntels.ccbs.common.interceptor
 * 4. Comment  : Login 여부를 판별하기 위한 Interceptor
 * 5. 작성자   : smyun@ntels.com
 * 6. 작성일   : 2014. 4. 10. 오후 1:23:17
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		smyun@ntels.com :	2014. 4. 10.	: 신규 개발.
 * </PRE>
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** The redirect page. */
	private String redirectPage;

	/** The list no session. */
	private List<String>  listNoSession;
	
	/**
	 *  인터페이스 URL
	 */
	private List<String>  listAppUrl;
	

	/**
	 * Gets the redirect page.
	 *
	 * @return the redirect page
	 */
	public String getRedirectPage() {
		return redirectPage;
	}

	/**
	 * Sets the redirect page.
	 *
	 * @param redirectPage the new redirect page
	 */
	public void setRedirectPage(String redirectPage) {
		this.redirectPage = redirectPage;
	}

	/**
	 * Gets the list no session.
	 *
	 * @return the list no session
	 */
	public List<String> getListNoSession() {
		return listNoSession;
	}

	/**
	 * Sets the list no session.
	 *
	 * @param listNoSession the new list no session
	 */
	public void setListNoSession(List<String> listNoSession) {
		this.listNoSession = listNoSession;
	}
	
	public List<String> getListAppUrl() {
		return listAppUrl;
	}

	public void setListAppUrl(List<String> listAppUrl) {
		this.listAppUrl = listAppUrl;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//listNoSession를 호출하면 무조건 통과
		for(int i=0;i<listNoSession.size();i++) {
			if (listNoSession.get(i).trim().endsWith("/*")) {
				String temp = listNoSession.get(i).trim();
				temp=temp.substring(0,temp.length()-1);
				if (request.getPathInfo().startsWith(temp)) {
					return true;
				}
			}else if (listNoSession.get(i).trim().equals(request.getPathInfo())) {
				return true;
			}
		}

		/**
		 * 인터페이스 URL은 제외 처리
		 */
		for(String path : listAppUrl){
			if(request.getPathInfo().startsWith(path)){
				return true;
			}
		}
		
		try {
			Object obj = request.getSession().getAttribute("session_user");

			//세션 검사 후 없다면 redirectPage로 이동
			if (obj != null) {
				String userId = ((SessionUser) obj).getUserId();
				if (userId==null || userId.isEmpty()) {
					response.sendRedirect(redirectPage);
					return false;
				}
			} else {
				response.sendRedirect(redirectPage);
				return false;
				
			}
		} catch (Exception e) {
			response.sendRedirect(redirectPage);
			return false;
		}

		//redirectPage로 들어오면 무조건 통과
		if (redirectPage.equals(request.getPathInfo())) {
			return true;
		}
		return true;
	}
}
