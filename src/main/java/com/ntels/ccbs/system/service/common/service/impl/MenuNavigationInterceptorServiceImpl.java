package com.ntels.ccbs.system.service.common.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.common.service.Menu;
import com.ntels.ccbs.system.mapper.common.service.MenuNavigationInterceptorMapper;
import com.ntels.ccbs.system.service.common.service.MenuNavigationInterceptorService;

/**
 * 메뉴 Service.
 *
 * @author smyun@ntels.com
 */
@Service
public class MenuNavigationInterceptorServiceImpl implements MenuNavigationInterceptorService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/** the mapper. */
	@Autowired
	private MenuNavigationInterceptorMapper menuNavigationInterceptorMapper;

	@Override
	public List<Menu> getMenuList(String selectMenuNo, String sessionCountry, String sessionLanguage) {
		List<Menu> menuNaviList = new ArrayList<Menu>();
		
		String menuNo = selectMenuNo;
		
		for(int i = 4; i > 0; i--){
			Menu menu = menuNavigationInterceptorMapper.getMenuList(menuNo,i, sessionCountry, sessionLanguage);
			menuNaviList.add(menu);
			
			menuNo = menu.getUpMenuNo();
		}
		
		Collections.reverse(menuNaviList);
		return menuNaviList;
	}
}
