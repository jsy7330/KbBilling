package com.ntels.ccbs.system.controller.auth.authMng;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.auth.authMng.SoAuthMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.auth.authMng.SoAuthMngService;

/**
 * <PRE>
 * 1. ClassName: SoAuthMngController
 * 2. FileName : SoAuthMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.auth.authMng
 * 4. Comment  : 사용자별 사업권한관리 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 9:37:10
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/auth/authMng/soAuthMng")
public class SoAuthMngController {
	/**
	 * 사용자별 사업권한관리 URL
	 */
	private static String URL = "system/auth/authMng/soAuthMng";
	
	
	/**
	 * 사용자별 사업권한관리 서비스
	 */
	@Autowired SoAuthMngService soAuthMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: soAuthMng
	 * 2. ClassName : SoAuthMngController
	 * 3. Comment   : 사용자별 사업권한관리 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:38:09
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param soAuth 사용자별 사업권한관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "soAuthMng", method = RequestMethod.POST)
	public String soAuthMng(	Model model,SoAuthMngVO soAuth ,HttpServletRequest request) {
		model.addAttribute("soAuth", soAuth);
		return URL + "/soAuthMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: soAuthListAction
	 * 2. ClassName : SoAuthMngController
	 * 3. Comment   : 사용자별 사업권한관리 리스트
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:39:12
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param soAuth 사용자별 사업권한관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @param clickYn 그리드 선택 여부
	 *   @param userId 사용자아이디
	 *   @param condUserId 사용자ID 조건
	 *   @param condUserNm 사용자명 조건
	 *   @param condOrgId 조직ID 조건
	 */
	@RequestMapping(value = "soAuthListAction", method = RequestMethod.POST)
	public String soAuthListAction(Model model,SoAuthMngVO soAuth,HttpServletRequest request
			,String clickYn
			,String userId
			,String condUserId
			,String condUserNm
			,String condOrgId) {
		if(StringUtils.isEmpty(clickYn)){
			return URL + "/ajax/soAuthMng";
		}
		soAuth.setCondUserId(condUserId);
		soAuth.setCondUserNm(condUserNm);
		soAuth.setCondOrgId(condOrgId);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> soAuthInfo = soAuthMngService.userAuthList(soAuth,lng);
		
		model.addAttribute("userId", soAuth.getUserId());
		model.addAttribute("soAuthList", soAuthInfo.get("soAuthList"));
		model.addAttribute("totalCount2", soAuthInfo.get("totalCount2"));
		model.addAttribute("totalPages2", soAuthInfo.get("totalPages2"));
		model.addAttribute("page2", soAuthInfo.get("page2"));
		
		return URL + "/ajax/soAuthMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: insertAction
	 * 2. ClassName : SoAuthMngController
	 * 3. Comment   :사용자별 사업권한관리 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:40:23
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param soAuth 사용자별 사업권한관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "insertAction", method = RequestMethod.POST)
	public String insertAction(Model model,SoAuthMngVO soAuth,HttpServletRequest request) {
		SessionUser session_user = (SessionUser) request.getSession().getAttribute("session_user");
		
		soAuth.setUserId(soAuth.getUserId());
		soAuth.setSoId(soAuth.getSoId());
		soAuth.setRegrId(session_user.getUserId());
		soAuth.setSysToDate(DateUtil.sysdate());
		
		int result=soAuthMngService.insert(soAuth);
		return URL + "/ajax/soAuthMng";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: deleteAction
	 * 2. ClassName : SoAuthMngController
	 * 3. Comment   : 사용자별 사업권한관리 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:40:40
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param soAuth 사용자별 사업권한관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "deleteAction", method = RequestMethod.POST)
	public String deleteAction(Model model,SoAuthMngVO soAuth,HttpServletRequest request) {
		
		soAuth.setUserId(soAuth.getUserId());
		soAuth.setSoId(soAuth.getSoId());
		
		int result=soAuthMngService.delete(soAuth);
		return URL + "/ajax/soAuthMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getBaseSoIdAction
	 * 2. ClassName : SoAuthMngController
	 * 3. Comment   : 해당사용자의 기본사업아이디 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:41:37
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param soAuth 사용자별 사업권한관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "getBaseSoIdAction", method = RequestMethod.POST)
	public String getBaseSoIdAction(Model model,SoAuthMngVO soAuth,HttpServletRequest request) {
		
		soAuth.setUserId(soAuth.getUserId());
		
		String baseId=soAuthMngService.baseSoId(soAuth);
		model.addAttribute("baseId", baseId);

		return URL + "/ajax/soAuthMng";
	}
}