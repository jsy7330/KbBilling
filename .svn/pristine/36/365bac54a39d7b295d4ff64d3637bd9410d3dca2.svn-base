package com.ntels.ccbs.common.interceptor;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.nisf.util.StringUtil;

/**
 * 
 * @author kgw
 *
 */
public class LocaleInterceptor extends HandlerInterceptorAdapter {

	/** The LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LocaleInterceptor.class);

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private Ehcache ehcache;
	
	/**
	 *  인터페이스 URL
	 */
	private List<String>  listAppUrl;

	public List<String> getListAppUrl() {
		return listAppUrl;
	}

	public void setListAppUrl(List<String> listAppUrl) {
		this.listAppUrl = listAppUrl;
	}


	/**
	 * preHandle
	 *
	 * @param		request
	 * @param		response
	 * @param		handler
	 * @return		boolean
	 * @warning
	 * @exception	Exception
	 * @see
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {

		/**
		 * 인터페이스 URL은 제외 처리
		 */
		for(String path : listAppUrl){
			if(request.getPathInfo().startsWith(path)){
				return true;
			}
		}
		
		Locale locale = null;

		// 브라우저 Locale
		locale = new Locale(request.getLocale().getLanguage(), request.getLocale().getCountry());
		
		if (StringUtil.isEmpty((String)request.getSession().getAttribute("sessionLanguage"))) {
			request.getSession().setAttribute("sessionCountry", request.getLocale().getCountry());
			request.getSession().setAttribute("sessionLanguage", request.getLocale().getLanguage());
			localeResolver.setLocale(request, response, locale);
			Cache leftMenuCache = ehcache.getCacheManager().getCache("leftMenuCache");
			leftMenuCache.removeAll();
			Cache topMenuCache = ehcache.getCacheManager().getCache("topMenuCache");
			topMenuCache.removeAll();
			Cache authMenuCache = ehcache.getCacheManager().getCache("authMenuCache");
			authMenuCache.removeAll();
		}
		
		String useLanguage = request.getParameter("useLanguage");
		String sessionCountry = (String)request.getSession().getAttribute("sessionCountry");
		String sessionLanguage = (String)request.getSession().getAttribute("sessionLanguage");
		
		if (useLanguage != null) {
			String [] arrayStr = null;
			arrayStr = useLanguage.split("-");
		
			// useLanguage : KR,ko
			if (arrayStr.length == 2) {
				sessionCountry = arrayStr[0];
				sessionLanguage = arrayStr[1];
				request.getSession().setAttribute("sessionCountry", sessionCountry);
			}
			else if (arrayStr.length == 1) {
				sessionLanguage = arrayStr[0];
			}
		}
		
		if (!StringUtil.isEmpty(sessionLanguage)) {
			if (!Consts.LANGUAGE_CODE_MAP.containsKey(sessionLanguage.toLowerCase(locale))) {
				locale = new Locale(sessionLanguage, sessionCountry);
				localeResolver.setLocale(request, response, locale);				
			}
			else {
				if(request.getSession().getAttribute("sessionLanguage").equals(sessionLanguage)) {
					locale = new Locale(sessionLanguage, sessionCountry);
					localeResolver.setLocale(request, response, locale);
				}
				else {
					locale = new Locale(sessionLanguage, sessionCountry);
					request.getSession().setAttribute("sessionLanguage", sessionLanguage);
					localeResolver.setLocale(request, response, locale);
					Cache leftMenuCache = ehcache.getCacheManager().getCache("leftMenuCache");
					leftMenuCache.removeAll();
					Cache topMenuCache = ehcache.getCacheManager().getCache("topMenuCache");
					topMenuCache.removeAll();
					Cache authMenuCache = ehcache.getCacheManager().getCache("authMenuCache");
					authMenuCache.removeAll();
				}
			}
			
			
		}

		request.setAttribute("useLanguage", request.getSession().getAttribute("sessionCountry")+"-"+request.getSession().getAttribute("sessionLanguage"));
		return true;
	}

}
