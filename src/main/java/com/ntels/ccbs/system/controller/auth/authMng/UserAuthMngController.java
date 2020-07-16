package com.ntels.ccbs.system.controller.auth.authMng;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.auth.authMng.UserAuthMngVO;
import com.ntels.ccbs.system.service.auth.authMng.UserAuthMngService;

/**
 * <PRE>
 * 1. ClassName: UserAuthMngController
 * 2. FileName : UserAuthMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.auth.authMng
 * 4. Comment  : 사용자별 그룹관리 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 22. 오후 4:51:27
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 22.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/auth/authMng/userAuthMng")
public class UserAuthMngController {
	/**
	 * 사용자별 그룹관리 메인 URL
	 */
	private static String URL = "system/auth/authMng/userAuthMng";
	
	/**
	 * 사용자별 그룹관리 서비스
	 */
	@Autowired UserAuthMngService userAuthMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: userAuthMng
	 * 2. ClassName : UserAuthMngController
	 * 3. Comment   : 사용자별 그룹관리 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:53:11
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "userAuthMng", method = RequestMethod.POST)
	public String userAuthMng(	Model model,UserAuthMngVO userAuth ,HttpServletRequest request) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("userAuth", userAuth);
		return URL + "/userAuthMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: mainListAction
	 * 2. ClassName : UserAuthMngController
	 * 3. Comment   : 사용자 리스트
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:53:22
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @param srchYn 검색버튼 클릭 여부
	 *   @param condUserId 사용자ID 조건
	 *   @param condUserNm 사용자명 조건
	 *   @param condOrgId 조직ID 조건
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 */
	@RequestMapping(value = "mainListAction", method = RequestMethod.POST)
	public String mainListAction(	Model model,UserAuthMngVO userAuth ,HttpServletRequest request
			,String srchYn
			,String condUserId 
			,String condUserNm
			,String condOrgId
			,String sidx
			,String sord) {
		if(StringUtils.isEmpty(srchYn)){
			return URL + "/ajax/userAuthMng";
		}
		userAuth.setCondUserId(condUserId);
		userAuth.setCondUserNm(condUserNm);
		userAuth.setCondOrgId(condOrgId);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> userAuthInfo = userAuthMngService.list(userAuth,lng,sidx,sord);
		
		model.addAttribute("userAuthList", userAuthInfo.get("userAuthList"));
		model.addAttribute("totalCount", userAuthInfo.get("totalCount"));
		model.addAttribute("totalPages", userAuthInfo.get("totalPages"));
		model.addAttribute("page", userAuthInfo.get("page"));
		return URL + "/userAuthMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: userAuthListAction
	 * 2. ClassName : UserAuthMngController
	 * 3. Comment   : 사용자별 그룹관리 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:56:26
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @param clickYn 사용자리스트 선택여부
	 *   @param userId 사용자ID
	 *   @param condUserId  사용자ID 조건
	 *   @param condUserNm 사용자명 조건
	 *   @param condOrgId 조직ID 조건
	 */
	@RequestMapping(value = "userAuthListAction", method = RequestMethod.POST)
	public String userAuthListAction(	Model model,UserAuthMngVO userAuth ,HttpServletRequest request
			,String clickYn
			,String userId
			,String condUserId
			,String condUserNm
			,String condOrgId) {
		if(StringUtils.isEmpty(clickYn)){
			return URL + "/ajax/userAuthMng";
		}
		userAuth.setCondUserId(condUserId);
		userAuth.setCondUserNm(condUserNm);
		userAuth.setCondOrgId(condOrgId);
		userAuth.setUserId(userId);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> userGroupInfo = userAuthMngService.authList(userAuth,lng);
		
		model.addAttribute("userId", userAuth.getUserId());
		model.addAttribute("userAuthList", userGroupInfo.get("userAuthList"));
		model.addAttribute("totalCount2", userGroupInfo.get("totalCount2"));
		model.addAttribute("totalPages2", userGroupInfo.get("totalPages2"));
		model.addAttribute("page2", userGroupInfo.get("page2"));
		return URL + "/userAuthMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: insertAction
	 * 2. ClassName : UserAuthMngController
	 * 3. Comment   : 사용자별 그룹 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:59:44
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @param userId 사용자ID
	 *   @param userGroupId 그룹ID
	 */
	@RequestMapping(value = "insertAction", method = RequestMethod.POST)
	public String insertAction(	Model model,UserAuthMngVO userAuth ,HttpServletRequest request,String userId,String userGroupId) {
		
		userAuth.setUserId(userId);
		userAuth.setUserGroupId(userGroupId);
		int result=userAuthMngService.insert(userAuth);
		return URL + "/userAuthMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: deleteAction
	 * 2. ClassName : UserAuthMngController
	 * 3. Comment   : 사용자별 그룹 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:00:44
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "deleteAction", method = RequestMethod.POST)
	public String deleteAction(	Model model,UserAuthMngVO userAuth ,HttpServletRequest request) {
		int result=userAuthMngService.delete(userAuth);
		return URL + "/userAuthMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getBaseUserGroupIdAction
	 * 2. ClassName : UserAuthMngController
	 * 3. Comment   : 해당 user에 해당하는 기본그룹조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 5:17:44
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userAuth 사용자별 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "getBaseUserGroupIdAction", method = RequestMethod.POST)
	public String getBaseUserGroupIdAction(	Model model,UserAuthMngVO userAuth ,HttpServletRequest request) {
		userAuth.setUserId(userAuth.getUserId());
		String baseId=userAuthMngService.baseUserGroupId(userAuth);
		model.addAttribute("baseId", baseId);
		return URL + "/userAuthMng";
	}
	
}