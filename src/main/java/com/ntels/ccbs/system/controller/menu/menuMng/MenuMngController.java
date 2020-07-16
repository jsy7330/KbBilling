package com.ntels.ccbs.system.controller.menu.menuMng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.controller.login.LoginController;
import com.ntels.ccbs.system.domain.menu.menuMng.MenuMngVO;
import com.ntels.ccbs.system.service.menu.menuMng.MenuMngService;

/**
 * <PRE>
 * 1. ClassName: MenuMngController
 * 2. FileName : MenuMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.menu.menuMng
 * 4. Comment  : 메뉴관리 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 20. 오후 5:40:05
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 20.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/menu/menuMng/menuMng")
public class MenuMngController {
	/**
	 * 메뉴관리 메인 URL 
	 */
	private static String URL = "system/menu/menuMng/menuMng";
	
	/**
	  메뉴관리 서비스
	 */
	@Autowired MenuMngService menuMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: menuMng
	 * 2. ClassName : MenuMngController
	 * 3. Comment   : 메뉴관리 메인 뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:23:37
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param menu 메뉴관리 VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "menuMng", method = RequestMethod.POST)
	public String menuMng(	Model model,MenuMngVO menu ,HttpServletRequest request) {
		return URL + "/menuMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getMenuListAction
	 * 2. ClassName : MenuMngController
	 * 3. Comment   : Object 메뉴 Tree뷰 작성을 위한 Object
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:21:57
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param menu 메뉴관리 VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "getMenuListAction", method = RequestMethod.POST)
	public @ResponseBody Object getMenuListAction(	Model model,MenuMngVO menu ,HttpServletRequest request) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		ArrayList<Object> root = new ArrayList<Object>(); // json data
		
		Map<String, Object> rootNode = new HashMap<String, Object>();
		rootNode.put("title", "/");
		rootNode.put("isFolder",true);
		rootNode.put("key", "ROOT"); 
		rootNode.put("expand", true);
		rootNode.put("order", 0);
		rootNode.put("upMenuNo", 0);
		rootNode.put("stepNo", 0);
		rootNode.put("upMenuName", "/");
		List<Map<String,Object>> getMenuList = menuMngService.getMenuList(lng);
		
		rootNode.put("children", getMenuList);
		
		root.add(rootNode);
		
		return root;
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getDownMenuListAction
	 * 2. ClassName : MenuMngController
	 * 3. Comment   : 하위메뉴리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:25:21
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param menu 메뉴관리 VO
	 *   @param request {@link HttpServletRequest}
	 *   @param condUpMenuNo tree에서 선택한 menu_no
	 *   @param topMenu 최상위(ROOT)확인 여부
	 *   @param stepNo 하위메뉴의 순서번호
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 */
	@RequestMapping(value = "getDownMenuListAction", method = RequestMethod.POST)
	public String getDownMenuListAction(	Model model,MenuMngVO menu ,HttpServletRequest request,String condUpMenuNo,String topMenu
			,String stepNo
			,String sidx,
			String sord) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		if(topMenu==null || topMenu==""){
			return URL + "/ajax/menuMng";
		}
		menu.setTopMenu(topMenu);
		
		int stepNo1=Integer.parseInt(menu.getStepNo())+1;
		
		if(stepNo1>4){
			stepNo1=4;
		}
		menu.setStepNo(Integer.toString(stepNo1));

		List<MenuMngVO> downMenuList=menuMngService.getDownMenuList(lng,condUpMenuNo,topMenu,menu,sidx,sord);
		model.addAttribute("downMenuList", downMenuList);
		return URL + "/ajax/menuMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getLngListAction
	 * 2. ClassName : MenuMngController
	 * 3. Comment   : 언어 코드 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:33:01
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param menu 메뉴관리 VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "getLngListAction", method = RequestMethod.POST)
	public String getLngListAction(Model model,MenuMngVO menu ,HttpServletRequest request) {
		model.addAttribute("lngList", menuMngService.getLngListAction());
		return URL + "/ajax/menuMng";
	}
	/**
	 * <PRE>
	 * 1. MethodName: insertMenuAction
	 * 2. ClassName : MenuMngController
	 * 3. Comment   : 메뉴등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:34:10
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param menu 메뉴관리 VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "insertMenuAction", method = RequestMethod.POST)
	public String insertMenuAction(Model model, MenuMngVO menu ,HttpServletRequest request) {
		if(menu.getUpMenuNo().equals("ROOT")){
			menu.setUpMenuNo("0");
		}
		int stepNo=Integer.parseInt(menu.getStepNo())+1;
		
		if(stepNo>4){
			stepNo=4;
		}
		menu.setStepNo(Integer.toString(stepNo));
		
		try{
			menuMngService.insertMenuAction(menu);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		model.addAttribute("upMenuNo", menu.getUpMenuNo());
		model.addAttribute("menuName", menu.getMenuName());
		return URL + "/ajax/menuMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: updateMenuAction
	 * 2. ClassName : MenuMngController
	 * 3. Comment   : 메뉴수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:34:45
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param menu 메뉴관리 VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "updateMenuAction", method = RequestMethod.POST)
	public String updateMenuAction(Model model, MenuMngVO menu ,HttpServletRequest request) {
		menuMngService.updateMenuAction(menu);
		model.addAttribute("upMenuNo", menu.getUpMenuNo());
		return URL + "/ajax/menuMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: deleteAction
	 * 2. ClassName : MenuMngController
	 * 3. Comment   : 메뉴삭제(메뉴, 언어,권한)
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 20. 오후 5:38:52
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param menu 메뉴관리 VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "deleteAction", method = RequestMethod.POST)
	public String deleteAction(Model model, MenuMngVO menu ,HttpServletRequest request) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		int stepNo=Integer.parseInt(menu.getStepNo())+1;
		
		if(stepNo>4){
			stepNo=4;
		}
		
		String step=Integer.toString(stepNo);
		menuMngService.deleteAction(menu,lng,step);
		model.addAttribute("upMenuNo", menu.getUpMenuNo());
		model.addAttribute("menuName", menu.getUpMenuName());
		return URL + "/ajax/menuMng";
	}
}