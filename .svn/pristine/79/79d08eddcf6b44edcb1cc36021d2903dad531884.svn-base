package com.ntels.ccbs.common.interceptor;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.domain.common.service.Menu;
import com.ntels.ccbs.system.domain.common.service.SelectedMenu;
import com.ntels.ccbs.system.service.common.service.MenuNavigationInterceptorService;


public class MenuNavigationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private MenuNavigationInterceptorService menuNavigationInterceptorService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		/**
		 * 인터페이스 URL은 제외 처리
		 */
		for(String path : listAppUrl){
			if(request.getPathInfo().startsWith(path)){
				return true;
			}
		}
		
		String menuNo = StringUtils.defaultString(request.getParameter("menuNo"),"-1");
		String selectMenuNo = StringUtils.defaultString(request.getParameter("selectMenuNo"),"-1");
		String selectMenuNm = StringUtils.defaultString(request.getParameter("selectMenuNm"),"");
		String topMenuNo = StringUtils.defaultString(request.getParameter("topMenuNo"),"-1");
		String topMenuNm = StringUtils.defaultString(request.getParameter("topMenuNm"),"");
		if (!"-1".equals(menuNo) && !"-1".equals(selectMenuNo)) {
			
			SelectedMenu selectedMenuVO = CommonUtil.getSessionSelectMenuInfo();
			if (selectedMenuVO == null) {
				selectedMenuVO = new SelectedMenu();
				CommonUtil.setSessionSelectMenuInfo(selectedMenuVO);
			}
			selectedMenuVO.setMenuNo(menuNo);
			selectedMenuVO.setSelectMenuNo(selectMenuNo);
			selectedMenuVO.setSelectMenuNm(selectMenuNm);
			selectedMenuVO.setTopMenuNo(topMenuNo);
			selectedMenuVO.setTopMenuNm(topMenuNm);

			CommonUtil.setSessionSelectMenuInfo(selectedMenuVO);
		}
		
		/* 요청 정보 로그 출력 */
		if (logger.isDebugEnabled()) {
			if(request.getRequestURI() != null ) logger.debug("REQUEST URI \t: {}",request.getRequestURI());
			if(request.getPathInfo() != null ) logger.debug("REQUEST PATH \t: {}",request.getPathInfo());
			if(request.getQueryString() != null) logger.debug("REQUEST QueryString \t: {}",request.getQueryString());
			if(request.getMethod() != null ) logger.debug("REQUEST Method \t: {}",request.getMethod());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		
		/**
		 * 인터페이스 URL은 제외 처리
		 */
		for(String path : listAppUrl){
			if(request.getPathInfo().startsWith(path)){
				return;
			}
		}
		
		String isChangeMenu = StringUtils.defaultString(request.getParameter("isChangeMenu"),"");
		
		if("Y".equals(isChangeMenu)){
			SelectedMenu selectedMenuVO = CommonUtil.getSessionSelectMenuInfo();
			if (selectedMenuVO == null) {
				selectedMenuVO = new SelectedMenu();
				CommonUtil.setSessionSelectMenuInfo(selectedMenuVO);
			}
			
			if(!"".equals(selectedMenuVO.getSelectMenuNo()) && !"-1".equals(selectedMenuVO.getSelectMenuNo()) ){
				injectMenuPaths(request, response, modelAndView, selectedMenuVO.getSelectMenuNo());
			}
			
		}else{
			String selectMenuNo = StringUtils.defaultString(request.getParameter("selectMenuNo"),"-1");
			if (!"-1".equals(selectMenuNo)) {
				injectMenuPaths(request, response, modelAndView, selectMenuNo);
			}
		}


		
		if(logger.isDebugEnabled()){
			if(modelAndView != null && modelAndView.getViewName() != null ) logger.debug("RESPONSE VIEW \t : {}",modelAndView.getViewName());
		}
	}

	/**
	 * 메뉴 경로를 삽입한다.
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 */
	private void injectMenuPaths(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, String selectMenuNo) {

		if (isNeedMenuPaths(request, response)) {
			if (modelAndView != null) {
				HttpSession session = request.getSession();
				String sessionCountry = (String) session.getAttribute("sessionCountry");
				String sessionLanguage = (String) session.getAttribute("sessionLanguage");
				
				Map<String,Object> info = getMenuPaths(selectMenuNo,sessionCountry, sessionLanguage );
				
				session.setAttribute("naviMenuList", info.get("NAVI_LIST"));
				session.setAttribute("menuName", info.get("MENU_NAME"));
				session.setAttribute("lastPagePath", info.get("VIEW_PATH"));
			}
		}
	}

	/**
	 * 메뉴 경로가 필요한 화면인지 판단한다.
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean isNeedMenuPaths(HttpServletRequest request, HttpServletResponse response) {
		return !request.getPathInfo().toLowerCase().endsWith(".json");
	}

	/**
	 * 요청 pathInfo의 전체 메뉴 경로 찾기.
	 * @param selectMenuNo
	 * @return
	 */
	public Map<String,Object> getMenuPaths(String selectMenuNo, String sessionCountry, String sessionLanguage) {
		
		Map<String,Object> result = new HashMap<String,Object>();

		List<Menu> menuList;

		try {
			menuList = menuNavigationInterceptorService.getMenuList(selectMenuNo, sessionCountry, sessionLanguage );
			
			
			if(menuList.size() == 4 && !StringUtils.isEmpty(menuList.get(3).getMenuName())){
				result.put("MENU_NAME", menuList.get(3).getMenuName());
				result.put("VIEW_PATH", menuList.get(3).getViewPath());
			}else{
				result.put("MENU_NAME", "");
				result.put("VIEW_PATH", "");
			}
			result.put("NAVI_LIST", menuList);
		} catch (Exception e) {
			menuList = Collections.emptyList();
			result.put("NAVI_LIST", menuList);
			result.put("MENU_NAME", "");
			result.put("VIEW_PATH", "");
		}

		return result;
	}
	
}
