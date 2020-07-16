package com.ntels.ccbs.system.mapper.common.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.service.Menu;

/**
 * 
 * <PRE>
 * 1. ClassName: MenuNavigationInterceptorMapper
 * 2. FileName : MenuNavigationInterceptorMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.authorization
 * 4. Comment  : 메뉴 mapper.
 * 5. 작성자   : kgw
 * 6. 작성일   : 2015. 9. 17. 오전 11:25:07
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		kgw :	2015. 9. 17.	: 신규 개발.
 * </PRE>
 */
@Component
public interface MenuNavigationInterceptorMapper {
	Menu getMenuList(@Param("selectMenuNo") String selectMenuNo,@Param("stepNo") int stepNo, @Param("sessionCountry") String sessionCountry, @Param("sessionLanguage") String sessionLanguage);
}