package com.ntels.ccbs.system.mapper.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.service.LeftSubMenu;
import com.ntels.ccbs.system.domain.common.service.LeftTopMenu;
import com.ntels.ccbs.system.domain.common.service.TopMenu;
import com.ntels.ccbs.system.domain.common.service.TopSubMenu;
import com.ntels.ccbs.system.domain.common.service.UserGroupAuth;

/**
 * 
 * <PRE>
 * 1. ClassName: MenuTagMapper
 * 2. FileName : MenuTagMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.authorization
 * 4. Comment  : 사용자 그룹 권한 mapper.
 * 5. 작성자   : kgw
 * 6. 작성일   : 2015. 9. 17. 오전 11:26:01
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		kgw :	2015. 9. 17.	: 신규 개발.
 * </PRE>
 */
@Component
public interface MenuTagMapper {

	/**
	 * 최상단 메뉴.
	 * 
	 * @param user_id 사용자ID
	 * @return List<TopMenu>
	 */
	List<TopMenu> getTopMenu(@Param(value = "user_id") String user_group_id, @Param(value = "sessionLanguage") String sessionLanguage);

	/**
	 * 서브 상단 메뉴 메뉴.
	 * 
	 * @param user_id 사용자ID
	 * @return List<TopMenu>
	 */
	List<TopSubMenu> getTopSubMenu(@Param(value = "user_id") String user_group_id, @Param(value = "sessionLanguage") String sessionLanguage, @Param(value = "top_menu") String topMenu);
	
	/**
	 * Left 상위 메뉴.
	 * 
	 * @param user_ud 사용자ID
	 * @param up_menu_no 상위메뉴
	 * @param step_no depth
	 * @return List<UserGroupAuth>
	 */
	List<LeftTopMenu> getLeftTopMenu(
			@Param(value = "user_id") String user_id,
			@Param(value = "up_menu_no") String up_menu_no,
			@Param(value = "sessionLanguage") String sessionLanguage);

	/**
	 * Left 하위 메뉴.
	 * 
	 * @param user_ud 사용자ID
	 * @param up_menu_no 상위메뉴
	 * @param step_no depth
	 * @return List<UserGroupAuth>
	 */
	List<LeftSubMenu> getLeftSubMenu(
			@Param(value = "user_id") String user_id,
			@Param(value = "left_top_menu_no") String left_top_menu_no,
			@Param(value = "sessionLanguage") String sessionLanguage);
}
