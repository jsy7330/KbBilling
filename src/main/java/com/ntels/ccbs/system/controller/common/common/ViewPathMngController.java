package com.ntels.ccbs.system.controller.common.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.common.common.ViewPathVO;
import com.ntels.ccbs.system.service.common.common.ViewPathMngService;

/**
 * <PRE>
 * 1. ClassName: ViewPathMngController
 * 2. FileName : ViewPathMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.common.common
 * 4. Comment  : viewPath관리 팝업컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:08:13
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/common/common/viewPathMng")
public class ViewPathMngController {
	/**
	 * viewPath관리 메인 URL
	 */
	private static String URL = "system/common/common/viewPathMng";
	/**
	 * viewPath관리 서비스
	 */
	@Autowired ViewPathMngService viewPathMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: viewPathMngPopup
	 * 2. ClassName : ViewPathMngController
	 * 3. Comment   : viewPath 팝업 메인 뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:08:47
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param view viewPath관리 VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "viewPathMngPopup", method = RequestMethod.POST)
	public String viewPathMngPopup(Model model,ViewPathVO view,HttpServletRequest request) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("commonCdList", viewPathMngService.getCommonCdList(view,lng));
		return  URL+"/ajax/viewPathMngPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: mainListAction
	 * 2. ClassName : ViewPathMngController
	 * 3. Comment   : viewPath 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:08:51
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param view viewPath관리 VO
	 *   @param request {@link HttpServletRequest}
	 *   @param condCommonCd 시스템구분
	 *   @param condCommonCdNm URL
	 */
	@RequestMapping(value = "mainListAction", method = RequestMethod.POST)
	public String mainListAction(Model model,ViewPathVO view,HttpServletRequest request
			,String condCommonCd,String condCommonCdNm) {
		view.setCondCommonCd(condCommonCd);
		view.setCondCommonCdNm(condCommonCdNm);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> viewPathInfo = viewPathMngService.list(view,lng);
		
		model.addAttribute("viewPathList", viewPathInfo.get("viewPathList"));
		return  URL+"/ajax/mainListAction";
		
	}
}