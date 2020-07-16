package com.ntels.ccbs.system.service.menu.menuMng;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.system.domain.menu.menuMng.MenuMngVO;

/**
 * <PRE>
 * 1. ClassName: MenuMngService
 * 2. FileName : MenuMngService.java
 * 3. Package  : com.ntels.ccbs.system.service.menu.menuMng
 * 4. Comment  : 메뉴관리 서비스
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 20. 오후 5:49:23
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 20.    : 신규개발
 * </PRE>
 */
public interface MenuMngService {

	/**
	 * <PRE>
	 * 1. MethodName: getMenuList
	 * 2. ClassName : MenuMngService
	 * 3. Comment   : 메뉴 Tree뷰 작성을 위한 Object
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:41:48
	 * </PRE>
	 *   @return List<Map<String,Object>>
	 *   @param lng 언어
	 */
	List<Map<String, Object>> getMenuList(String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getDownMenuList
	 * 2. ClassName : MenuMngService
	 * 3. Comment   : 하위메뉴리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:43:01
	 * </PRE>
	 *   @return List<MenuMngVO>
	 *   @param lng 언어
	 *   @param condUpMenuNo tree에서 선택한 menu_no
	 *   @param topMenu 최상위(ROOT)확인 여부
	 *   @param menu 메뉴관리 VO
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 */
	List<MenuMngVO> getDownMenuList(String lng, String condUpMenuNo, String topMenu, MenuMngVO menu, String sidx, String sord);

	/**
	 * <PRE>
	 * 1. MethodName: getLngListAction
	 * 2. ClassName : MenuMngService
	 * 3. Comment   : 빈 언어 코드 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:44:21
	 * </PRE>
	 *   @return Object 코드 언어 리스트
	 */
	Object getLngListAction();

	/**
	 * <PRE>
	 * 1. MethodName: insertMenuAction
	 * 2. ClassName : MenuMngService
	 * 3. Comment   : 메뉴등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:46:29
	 * </PRE>
	 *   @return void
	 *   @param menu 메뉴관리 VO
	 */
	void insertMenuAction(MenuMngVO menu);

	/**
	 * <PRE>
	 * 1. MethodName: updateMenuAction
	 * 2. ClassName : MenuMngService
	 * 3. Comment   : 메뉴수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:47:01
	 * </PRE>
	 *   @return void
	 *   @param menu 메뉴관리 VO
	 */
	void updateMenuAction(MenuMngVO menu);

	/**
	 * <PRE>
	 * 1. MethodName: deleteAction
	 * 2. ClassName : MenuMngService
	 * 3. Comment   : 메뉴삭제(메뉴, 언어,권한) 해당하는 레벨의 하위레벨까지 모두다 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:47:16
	 * </PRE>
	 *   @return void
	 *   @param menu 메뉴관리 VO
	 *   @param lng 언어
	 *   @param step 삭제하려는 메뉴의 레벨
	 */
	void deleteAction(MenuMngVO menu, String lng, String step);
	
}