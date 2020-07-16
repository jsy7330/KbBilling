package com.ntels.ccbs.system.mapper.menu.menuMng;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.menu.menuMng.MenuMngVO;

/**
 * <PRE>
 * 1. ClassName: MenuMngMapper
 * 2. FileName : MenuMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.menu.menuMng
 * 4. Comment  : 메뉴관리 Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 20. 오후 5:56:14
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 20.    : 신규개발
 * </PRE>
 */
@Component
public interface MenuMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: getAuthList
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   :메뉴 Tree뷰 작성을 위한 Object
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:56:26
	 * </PRE>
	 *   @return List<MenuMngVO> tree뷰에서 표시될 메뉴 리스트
	 *   @param stepNo 메뉴 레벨
	 *   @param upMenuNo 상위메뉴번호
	 *   @param lng 언어
	 */
	List<MenuMngVO> getAuthList(@Param(value = "stepNo") String stepNo, @Param(value = "upMenuNo") String upMenuNo,@Param(value = "lng") String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getDownMenuList
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 하위메뉴리스트
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:01:38
	 * </PRE>
	 *   @return List<MenuMngVO> 트리에서 선택한 메뉴번호 하위에 있는 하위메뉴리스트
	 *   @param lng 언어
	 *   @param condUpMenuNo tree에서 선택한 menu_no
	 *   @param topMenu 최상위(ROOT)확인 여부
	 *   @param menu 메뉴관리VO
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 */
	List<MenuMngVO> getDownMenuList(@Param(value = "lng")String lng, @Param(value = "condUpMenuNo")String condUpMenuNo,
			@Param(value = "topMenu") String topMenu,@Param(value = "menu") MenuMngVO menu, @Param(value = "sidx")String sidx, @Param(value = "sord")String sord);

	/**
	 * <PRE>
	 * 1. MethodName: getMenuLngList
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 메뉴 상세 언어 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:03:27
	 * </PRE>
	 *   @return List<Map<String,Object>> 언어리스트
	 *   @param menuNo 메뉴관리VO
	 *   @param lng 언어
	 */
	List<Map<String, Object>> getMenuLngList(@Param(value = "menuNo") String menuNo,@Param(value = "lng") String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getLngListAction
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 빈 언어 코드리스트
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:04:20
	 * </PRE>
	 *   @return List<Map<String,Object>> 빈 언어리스트
	 */
	List<Map<String, Object>> getLngListAction();

	/**
	 * <PRE>
	 * 1. MethodName: insertMenuAction
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 메뉴등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:06:03
	 * </PRE>
	 *   @return void
	 *   @param menu 메뉴관리VO
	 */
	void insertMenuAction(@Param(value = "menu")MenuMngVO menu);

	/**
	 * <PRE>
	 * 1. MethodName: insertLngMenu
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 메뉴에 해당하는 다국어 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:07:06
	 * </PRE>
	 *   @return void
	 *   @param menu 메뉴관리VO
	 */
	void insertLngMenu(@Param(value = "menu")MenuMngVO menu);

	/**
	 * <PRE>
	 * 1. MethodName: updateMenuAction
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 메뉴수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:07:40
	 * </PRE>
	 *   @return void
	 *   @param menu 메뉴관리VO
	 */
	void updateMenuAction(@Param(value = "menu")MenuMngVO menu);

	/**
	 * <PRE>
	 * 1. MethodName: deleteMenuAction
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 해당메뉴의 다국어 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:08:12
	 * </PRE>
	 *   @return void
	 *   @param menuNo 메뉴관리VO
	 */
	void deleteMenuAction(@Param(value = "menuNo")String menuNo);

	/**
	 * <PRE>
	 * 1. MethodName: getMenuNoList
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 해당 menu_no가 up_menu_no인 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:08:37
	 * </PRE>
	 *   @return List<MenuMngVO> 해당 menu_no가 up_menu_no인 리스트 조회
	 *   @param menuNo 메뉴관리VO
	 */
	List<MenuMngVO> getMenuNoList(@Param(value = "menuNo")String menuNo);

	/**
	 * <PRE>
	 * 1. MethodName: deleteAction
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : menu_no에 해당하는 메뉴 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:10:30
	 * </PRE>
	 *   @return void
	 *   @param menuNo 메뉴관리VO
	 */
	void deleteAction(@Param(value = "menuNo")String menuNo);

	/**
	 * <PRE>
	 * 1. MethodName: deleteUpAction
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 해당 menu_no가 up_menu_no일 때 메뉴 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:11:11
	 * </PRE>
	 *   @return void
	 *   @param menuNo 메뉴관리VO
	 */
	void deleteUpAction(@Param(value = "menuNo")String menuNo);

	/**
	 * <PRE>
	 * 1. MethodName: deleteMenuAuth
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 해당 menu_no가 up_menu_no일 때 메뉴권한 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:12:16
	 * </PRE>
	 *   @return void
	 *   @param menuNo 메뉴관리VO
	 */
	void deleteMenuAuth(@Param(value = "menuNo")String menuNo);

	/**
	 * <PRE>
	 * 1. MethodName: deleteLastMenuAuth
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : menu_no에 해당하는 메뉴권한 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:13:03
	 * </PRE>
	 *   @return void
	 *   @param menuNo 메뉴관리VO
	 */
	void deleteLastMenuAuth(@Param(value = "menuNo")String menuNo);

	/**
	 * <PRE>
	 * 1. MethodName: deleteLastLng
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : 해당 menu_no가 up_menu_no일 때 메뉴다국어 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:14:51
	 * </PRE>
	 *   @return void
	 *   @param menuNo 메뉴관리VO
	 */
	void deleteLastLng(String menuNo);

	/**
	 * <PRE>
	 * 1. MethodName: checkMenu
	 * 2. ClassName : MenuMngMapper
	 * 3. Comment   : menu_no 중복체크
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 6:15:59
	 * </PRE>
	 *   @return int 중복여부체크
	 *   @param menu 메뉴관리VO
	 *   @return
	 */
	int checkMenu(@Param(value = "menu")MenuMngVO menu);
	
}