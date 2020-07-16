package com.ntels.ccbs.system.controller.auth.authMng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.system.domain.auth.authMng.AuthMngVO;
import com.ntels.ccbs.system.service.auth.authMng.AuthMngService;

/**
 * <PRE>
 * 1. ClassName: AuthMngController
 * 2. FileName : AuthMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.auth.authMng
 * 4. Comment  : 그룹별권한관리 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 27. 오전 9:08:24
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 27.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/auth/authMng/authMng")
public class AuthMngController {
	/**
	 * 그룹별 권한관리 메인 URL
	 */
	private static String URL = "system/auth/authMng/authMng";
	
	/**
	 * 그룹별 권한관리서비스
	 */
	@Autowired AuthMngService authMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: authMng
	 * 2. ClassName : AuthMngController
	 * 3. Comment   : 그룹별권한관리 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:08:46
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param auth 그룹별권한관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "authMng", method = RequestMethod.POST)
	public String authMng(	Model model,AuthMngVO auth ,HttpServletRequest request) {
		return URL + "/authMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getUserGroupListAction
	 * 2. ClassName : AuthMngController
	 * 3. Comment   : 사용자그룹 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:11:32
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param auth 그룹별권한관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @param isSearchYn 검색버튼 클릭 여부
	 *   @param condGroupId 그룹아이디 조건
	 *   @param condGroupNm 그룸명 조건
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 */
	@RequestMapping(value = "getUserGroupListAction", method = RequestMethod.POST)
	public String getUserGroupListAction(Model model,AuthMngVO auth,HttpServletRequest request
			,String isSearchYn
			,String condGroupId
			,String condGroupNm
			,String sidx
			,String sord
			){
		if(StringUtils.isEmpty(isSearchYn)){
			return URL + "/ajax/authMng";
		}
		auth.setCondGroupId(condGroupId);
		auth.setCondGroupNm(condGroupNm);
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> groupInfo = authMngService.getUserGroupList(sidx,sord, lng,auth);
		
		model.addAttribute("groupList", groupInfo.get("groupList"));
		model.addAttribute("totalCount", groupInfo.get("totalCount"));
		model.addAttribute("totalPages", groupInfo.get("totalPages"));
		model.addAttribute("page", groupInfo.get("page"));
		
		return URL + "/ajax/authMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getAuthListAction
	 * 2. ClassName : AuthMngController
	 * 3. Comment   : 권한정보 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:08:49
	 * </PRE>
	 *   @return Object 권한정보 TREE뷰 작성을 위한 OBJECT
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param userGroupId 사용자그룹ID
	 *   @param onlyAssignMenuYn 할당메뉴만 조회
	 */
	@RequestMapping(value = "getAuthListAction", method = RequestMethod.POST)
	public @ResponseBody Object getAuthListAction(Model model, HttpServletRequest request
			, String userGroupId
			, String onlyAssignMenuYn) {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		ArrayList<Object> root = new ArrayList<Object>(); // json data
		
		Map<String, Object> rootNode = new HashMap<String, Object>();
		rootNode.put("title", "/");
		rootNode.put("isFolder",true);
		rootNode.put("key", "ROOT"); 
		rootNode.put("expand", true);
		rootNode.put("order", 0);
		rootNode.put("userGroupId", userGroupId);
		rootNode.put("searchKey", "");
		if(!StringUtils.isEmpty(userGroupId)){
			rootNode.put("children", authMngService.getAuthList(userGroupId, onlyAssignMenuYn, lng));
		}
		
		root.add(rootNode);
		
		return root;
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: updateAuthAction
	 * 2. ClassName : AuthMngController
	 * 3. Comment   : 권한정보 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 27. 오전 9:08:53
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param userGroupId 사용자그룹ID
	 *   @param menuNo 메뉴번호
	 *   @param level 메뉴레벨
	 *   @param authRYn 읽기권한
	 *   @param authCYn 생성권한
	 *   @param authUYn 수정권한
	 *   @param authDYn 삭제권한
	 *   @param authPYn 출력권한
	 */
	@RequestMapping(value = "updateAuthAction", method = RequestMethod.POST)
	public String updateAuthAction(	Model model,HttpServletRequest request, 
			String userGroupId,
			String menuNo,
			String level,
			String authRYn,
			String authCYn,
			String authUYn,
			String authDYn,
			String authPYn
			) {
		authMngService.updateAuth(userGroupId, menuNo, level, authRYn, authCYn, authUYn, authDYn, authPYn);
		return URL + "/ajax/authMng";
	}
}
	