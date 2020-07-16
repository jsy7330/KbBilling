package com.ntels.ccbs.system.controller.common.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.common.common.UserSearchVO;
import com.ntels.ccbs.system.service.common.common.UserSearchMngService;


/**
 * <PRE>
 * 1. ClassName: UserSearchMngController
 * 2. FileName : UserSearchMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.common.common
 * 4. Comment  : 사용자검색 팝업컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:39:42
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/common/common/userSearchMng")
public class UserSearchMngController {
	/**
	 * 사용자검색 메인 URL
	 */
	private static String URL = "system/common/common/userSearchMng";
	
	/**
	 * 사용자검색 서비스
	 */
	@Autowired UserSearchMngService userSearchMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: userSearchPopup
	 * 2. ClassName : UserSearchMngController
	 * 3. Comment   : 사용자검색 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:39:47
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userSearch 사용자검색VO
	 *   @param request {@link HttpServletRequest}
	 *   @throws Exception
	 */
	@RequestMapping(value = "userSearchPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String userSearchPopup(Model model,UserSearchVO userSearch,HttpServletRequest request)throws Exception {
		model.addAttribute("userSearch", userSearch);
		if("o".equals(userSearch.getPopType())){
			return URL + "/opopup/userSearchPopup";
		}else{
			return URL + "/ajax/userSearchPopup";
		}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: userSearchListAction
	 * 2. ClassName : UserSearchMngController
	 * 3. Comment   : 사용자검색 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:39:51
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userSearch 사용자검색VO
	 *   @param request {@link HttpServletRequest}
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param condSoId 사업아이디 조건
	 *   @param condUserNm 사용자명 조건
	 *   @throws Exception
	 */
	@RequestMapping(value = "userSearchListAction", method = RequestMethod.POST)
	public String userSearchListAction(Model model,UserSearchVO userSearch,HttpServletRequest request
			,String sidx
			,String sord
			,int page
			,int rows
			,String condSoId
			,String condUserNm)throws Exception {
		
		userSearch.setCondSoId(condSoId);
		userSearch.setCondUserNm(condUserNm);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> userSearchInfo = userSearchMngService.list(userSearch,lng,sidx,sord, page, rows);
		
		model.addAttribute("userSearch", userSearch);
		model.addAttribute("userSearchList", userSearchInfo.get("userSearchList"));
		model.addAttribute("totalCount", userSearchInfo.get("totalCount"));
		model.addAttribute("totalPages", userSearchInfo.get("totalPages"));
		model.addAttribute("page", userSearchInfo.get("page"));
		if("o".equals(userSearch.getPopType())){
			return URL + "/opopup/userSearchPopup";
		}else{
			return URL + "/ajax/userSearchPopup";
		}
	}
}