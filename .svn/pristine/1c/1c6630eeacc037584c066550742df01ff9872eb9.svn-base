/*****************************************************************************************
 * Copyright (c) 2011 nTels, All rights reserved.
 *
 * com.ntels.ccbs.system.service.authorization.usergroup.UserGroupService.java
 * 사용자 그룹 처리를 위한 Sevice 클래스
 *
 * 사용 방법:
 * <pre>
 *    Service Class
 *    사용자 그룹 처리
 * </pre>
 *
 * @저자  : yongseok
 * @버전  :
 * @작성일 : 2011. 10. 19
 *
 * @작업 완료
 *    2011-10-19 : All
 * @작업중
 *    일자 : 내역을 적으세요
 ******************************************************************************************/
package com.ntels.ccbs.system.service.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.common.service.LeftSubMenu;
import com.ntels.ccbs.system.domain.common.service.LeftTopMenu;
import com.ntels.ccbs.system.domain.common.service.TopMenu;
import com.ntels.ccbs.system.domain.common.service.TopSubMenu;
import com.ntels.ccbs.system.mapper.common.service.MenuTagMapper;
import com.ntels.ccbs.system.service.common.service.MenuTagService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * 사용자 그룹 Service.
 *
 * @author smyun@ntels.com
 */
@Service
public class MenuTagServiceImpl implements MenuTagService {
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** MenuTagMapper Autowired. */
	@Autowired
	private MenuTagMapper menuTagMapper;

	/** Ehcache Autowired. */
	@Autowired
	private Ehcache ehcache;


	/**
	 * 최상단 메뉴 추출.
	 *
	 * @param user_id 사용자ID
	 * @param selectedMenuClass 선택메뉴
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String getTopMenu(String user_id, String sessionLanguage) {

		List<TopMenu> listMenu = null;

		String cacheKey = user_id + sessionLanguage;
		/* cache에서 로드 */
		Cache cache = ehcache.getCacheManager().getCache("topMenuCache");
		Element menuHtmlElement = cache.get(cacheKey);

		if (menuHtmlElement!=null && menuHtmlElement.getObjectValue()!=null) {
			listMenu = (List<TopMenu>)menuHtmlElement.getObjectValue();
			logger.debug("get cache topMenuHtml");
		} else {
			listMenu = menuTagMapper.getTopMenu(user_id, sessionLanguage);
			
			for(TopMenu tm : listMenu){
				List<TopSubMenu> subTopMenu = menuTagMapper.getTopSubMenu(user_id, sessionLanguage, tm.getMenuNo());
				
				for(TopSubMenu tsm : subTopMenu){
					List<LeftTopMenu> ltmList = menuTagMapper.getLeftTopMenu(user_id, tsm.getMenuNo(), sessionLanguage);
					if(ltmList.size() > 0){
						List<LeftSubMenu> lsmList = menuTagMapper.getLeftSubMenu(user_id, ltmList.get(0).getMenuNo(), sessionLanguage);
						if(lsmList.size() > 0){
							tsm.setSelectMenuNo(lsmList.get(0).getMenuNo());
							tsm.setSelectMenuNm(lsmList.get(0).getMenuNm());
							tsm.setViewPath(lsmList.get(0).getViewPath());
						}
					}
				}
				tm.setSubMenu(subTopMenu);
			}
			/* cache에 저장 */
			menuHtmlElement = new Element(cacheKey,listMenu);
			cache.put(menuHtmlElement);
			logger.debug("put cache topMenuHtml");
		}

		StringBuilder result=new StringBuilder();
    	for(TopMenu tm : listMenu){
			result.append("<li>");
			result.append("<a href=\"#\">");
			result.append(tm.getMenuNm());
			result.append("</a>");
			if(tm.getSubMenu().size() > 0){
				result.append("<ul>");
				for(TopSubMenu topSubMenu : tm.getSubMenu()){
					result.append("<li>");
					result.append("<a href='javascript:goTopMenu(\"");
					result.append(topSubMenu.getMenuNo());
					result.append("\",\"");
					result.append(topSubMenu.getViewPath());
					result.append("\",\"");
					result.append(topSubMenu.getSelectMenuNo());
					result.append("\",\"");
					result.append(topSubMenu.getSelectMenuNm());
					result.append("\",\"");
					result.append(topSubMenu.getTopMenuNo());
					result.append("\",\"");
					result.append(topSubMenu.getTopMenuNm());
					result.append("\");'>");
					result.append(topSubMenu.getMenuNm());
					result.append("</a>");
					result.append("</li>");
				}
				result.append("</ul>");
			}
			result.append("</li>");
    	}
		return result.toString();
	}

	/**
	 * Left Menu Generate.
	 *
	 * @param user_id 사용자ID
	 * @param top_menu_no top메뉴
	 * @param select_menu_no 선택메뉴
	 * @param menuClass 메뉴클래스
	 * @return String
	 */
	public String getLeftMenu(String user_id, String topMenuNo, String menuNo, String selectMenuNo, String sessionLanguage) {
		
		String result = "";
		/* cache에서 로드 */
		String sessionKey = user_id + selectMenuNo + sessionLanguage;
		Cache cache = ehcache.getCacheManager().getCache("leftMenuCache");
		Element leftMenuHtmlElement = cache.get(sessionKey);

		if (leftMenuHtmlElement!=null && leftMenuHtmlElement.getObjectValue()!=null) {
			result = (String) leftMenuHtmlElement.getObjectValue();
			logger.debug("get cache leftMenuHtml");
		} else {
			
			List<LeftTopMenu> listLeftMenu= menuTagMapper.getLeftTopMenu(user_id,menuNo,sessionLanguage);
			StringBuilder leftMenuStr = new StringBuilder();
			Map<String, Object> menuAuth = new HashMap<String, Object>();
			leftMenuStr.append("<h4 class=\"lnb_title\">");
			if(listLeftMenu.size() > 0){
				LeftTopMenu topName = listLeftMenu.get(0);
				leftMenuStr.append(topName.getTopMenuNm());
			}
			leftMenuStr.append("</h4>");
			leftMenuStr.append("<div id=\"lnb\">");
			leftMenuStr.append("<ul>");
			for(LeftTopMenu ltm : listLeftMenu){
				List<LeftSubMenu> lsm = menuTagMapper.getLeftSubMenu(user_id, ltm.getMenuNo(), sessionLanguage);
				ltm.setLeftSubMenu(lsm);
				
				if(lsm.size() == 0){ /*Left의 하위 메뉴가 존재 하지 않을 경우*/
					leftMenuStr.append("<li class=\"sub_none\">");
					leftMenuStr.append("<a href=\"#\">");
					leftMenuStr.append(ltm.getMenuNm());
					leftMenuStr.append("</a>");
					leftMenuStr.append("</li>");
				}else{
					//선택 메뉴 찾기
					boolean isSelected = false;
					for(LeftSubMenu subMenu : ltm.getLeftSubMenu()){
						if(!StringUtils.isEmpty(selectMenuNo) && selectMenuNo.equals(subMenu.getMenuNo())){
							isSelected = true;
							break;
						}
					}
					
					if(isSelected){ //선택된 하위 메뉴가 존재 할 경우
						leftMenuStr.append("<li id=\"selectedLeft\" class=\"on\">");
					}else{ //선택된 하위 메뉴가 존재 하지 않을 경우
						leftMenuStr.append("<li>");
					}
					leftMenuStr.append("<a href=\"#\">");
					leftMenuStr.append(ltm.getMenuNm());
					leftMenuStr.append("</a>");
					if(isSelected){
						leftMenuStr.append("<ul style=\"height:100%\">");
					}else{
						leftMenuStr.append("<ul>");
					}
					for(LeftSubMenu subMenu : ltm.getLeftSubMenu()){
						if(!StringUtils.isEmpty(selectMenuNo) && selectMenuNo.equals(subMenu.getMenuNo())){ //선택된 메뉴의 경우
							leftMenuStr.append("<li class=\"on\">");
						}else{ //선택 되지 않은 메뉴의 경우
							leftMenuStr.append("<li>");
						}
						leftMenuStr.append("<a href=\"javascript:goLeftMenuPage('");
						leftMenuStr.append(menuNo);
						leftMenuStr.append("','");
						leftMenuStr.append(subMenu.getViewPath());
						leftMenuStr.append("','");
						leftMenuStr.append(subMenu.getMenuNo());
						leftMenuStr.append("','");
						leftMenuStr.append(subMenu.getMenuNm());
						leftMenuStr.append("','");
						leftMenuStr.append(topMenuNo);
						leftMenuStr.append("','");
						leftMenuStr.append(ltm.getTopMenuNm());
						leftMenuStr.append("');\">");
						leftMenuStr.append(subMenu.getMenuNm());
						leftMenuStr.append("</a>");
						leftMenuStr.append("</li>");
						
						
			    		Map<String,Object> auth = new HashMap<String,Object>();
			    		auth.put("AUTH_C", subMenu.getAuthCYn());
			    		auth.put("AUTH_D", subMenu.getAuthDYn());
			    		auth.put("AUTH_P", subMenu.getAuthPYn());
			    		auth.put("AUTH_R", subMenu.getAuthRYn());
			    		auth.put("AUTH_U", subMenu.getAuthUYn());
			    		menuAuth.put(subMenu.getMenuNo(), (HashMap<String, Object>) auth);
					}
					leftMenuStr.append("</ul>");
					leftMenuStr.append("</li>");
				}
			}
			leftMenuStr.append("</ul>");
			leftMenuStr.append("</div>");
			result = leftMenuStr.toString();
			
			/* cache에 저장 */
			leftMenuHtmlElement = new Element(sessionKey,leftMenuStr.toString());
			cache.put(leftMenuHtmlElement);
			logger.debug("put cache leftMenuHtml");
			
			Cache authCache = ehcache.getCacheManager().getCache("authMenuCache");
			Element authMenuElement = new Element(sessionKey,menuAuth);
			authCache.put(authMenuElement);
		}
		return result;
	}

}