package com.ntels.ccbs.system.controller.common.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.common.common.MainViewVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

/**
 * <PRE>
 * 1. ClassName: MainViewMngController
 * 2. FileName : MainViewMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.common.common
 * 4. Comment  : mainView 관리 팝업
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 11:09:22
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/common/common/mainViewMng")
public class MainViewMngController {
	/**
	 * mainView 메인 URL
	 */
	private static String URL = "system/common/common/mainViewMng";
	
	/**
	 * mainView 서비스
	 */
	@Autowired
	private CommonDataService commonDataService;
	
	/**
	 * <PRE>
	 * 1. MethodName: mainViewMng
	 * 2. ClassName : MainViewMngController
	 * 3. Comment   : mainView 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 11:10:15
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param mainView  메인뷰VO
	 *   @param request {@link HttpServletRequest}
	 *   @param commonCdNm 페이지명
	 *   @param refCode 페이지URL
	 */
	@RequestMapping(value = "mainViewMngPopup", method = RequestMethod.POST)
	public String mainViewMng(Model model,MainViewVO mainView,HttpServletRequest request
			,String commonCdNm,String refCode) {
		model.addAttribute("commonCdNm", commonCdNm);
		model.addAttribute("refCode",refCode);
		return  URL+"/ajax/mainViewMngPopup";
		
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: mainViewListAction
	 * 2. ClassName : MainViewMngController
	 * 3. Comment   : mainView 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 11:09:43
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param mainView  메인뷰VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "mainViewListAction", method = RequestMethod.POST)
	public String mainViewListAction(Model model,MainViewVO mainView,HttpServletRequest request
			) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("mainViewList", commonDataService.getCommonCodeList("SY00005", lng));
		
		return  URL+"/ajax/mainViewListAction";
		
	}
}