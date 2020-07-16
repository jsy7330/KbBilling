package com.ntels.ccbs.system.controller.common.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.common.common.SoSearchVO;
import com.ntels.ccbs.system.service.common.common.SoSearchMngService;

/**
 * <PRE>
 * 1. ClassName: SoSearchMngController
 * 2. FileName : SoSearchMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.common.common
 * 4. Comment  : 사업검색팝업 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:19:13
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/common/common/soSearchMng")
public class SoSearchMngController {
	/**
	 * 사업검색 메인 URL
	 */
	private static String URL = "system/common/common/soSearchMng";
	
	/**
	 * 사업검색 서비스
	 */
	@Autowired SoSearchMngService soSearchMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: soSearchPopup
	 * 2. ClassName : SoSearchMngController
	 * 3. Comment   : 사업검색 팝업 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:19:20
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param soSearch 사업검색VO
	 *   @param request {@link HttpServletRequest}
	 *   @param baseId 해당 user에 해당하는 기본 SO_ID
	 *   @param soId 해당 user_ID에 해당하는 so권한
	 *   @param userId 사용자ID
	 */
	@RequestMapping(value = "soSearchPopup", method = RequestMethod.POST)
	public String soSearchPopup(Model model,SoSearchVO soSearch,HttpServletRequest request,String baseId,
			String soId,String userId) {
		model.addAttribute("soId", soId);
		model.addAttribute("userId", userId);
		model.addAttribute("baseId", baseId);
		return  URL+"/ajax/soSearchPopup";
		
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: soSearchListAction
	 * 2. ClassName : SoSearchMngController
	 * 3. Comment   : 사업검색 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:19:24
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param soSearch 사업검색VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "soSearchListAction", method = RequestMethod.POST)
	public String soSearchListAction(Model model,SoSearchVO soSearch,HttpServletRequest request
			) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> soSearchInfo = soSearchMngService.list(soSearch,lng);
		
		model.addAttribute("soSearchList", soSearchInfo.get("soSearchList"));
		return  URL+"/ajax/soSearchListAction";
		
	}
	
}