package com.ntels.ccbs.distribute.controller.distributor.distributorMgt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationRelHistVO;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.OrganizationRelHistService;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.OrganizationService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/distributor/distributorMgt/organizationRelHist")
public class OrganizationRelHistController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private OrganizationRelHistService organizationRelHistService;
	
	public static String URL = "distributor/distributor/distributorMgt/organizationRelHist";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : OrganizationRelHistAction
	 * 3. Comment   : 조직관계이력관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 16. 오후 4:11:21
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param organization
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationRelHist", method = RequestMethod.POST)
	public String list(Model model, OrganizationVO organization, HttpServletRequest request) throws Exception {
		
		return URL + "/organizationRelHist";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getOrganizationDetailAction
	 * 2. ClassName : OrganizationRelHistAction
	 * 3. Comment   : 조직상세정보 셀렉트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 17. 오전 10:12:46
	 * </PRE>
	 *   @return void
	 *   @param organizationRelHistVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "getOrganizationDetailAction", method = RequestMethod.POST)
	public void getOrganizationDetailAction(
			OrganizationRelHistVO organizationRelHistVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		organizationRelHistVO.setLngTyp(lngTyp);

		OrganizationRelHistVO data = organizationRelHistService.getOrganizationDetail(organizationRelHistVO);
		
		model.addAttribute("organizationRelHistVO", data);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationRelHistInsertPopUp
	 * 2. ClassName : OrganizationRelHistAction
	 * 3. Comment   : 조직관계등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 17. 오후 1:01:42
	 * </PRE>
	 *   @return String
	 *   @param organizationVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationRelHistInsertPopUp", method = RequestMethod.POST)
	public String organizationRelHistInsertPopUp(
			OrganizationVO organizationVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "DN00050";		//관계유형
		
		model.addAttribute("relTpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("organization", organizationVO);

		return URL + "/popup/organizationRelHistInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertOrganizationRelHist
	 * 2. ClassName : OrganizationRelHistAction
	 * 3. Comment   : 조직관계등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 17. 오후 2:22:25
	 * </PRE>
	 *   @return void
	 *   @param organizationVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertOrganizationRelHist", method = RequestMethod.POST)
	public void insertOrganizationRelHist(
			OrganizationVO organizationVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		organizationVO.setRegrId(session.getUserId());
		organizationVO.setChgrId(session.getUserId());
		organizationVO.setRegDate(DateUtil.sysdate());
		organizationVO.setChgDate(DateUtil.sysdate());
		organizationVO.setLngTyp(lngTyp);
		
		String result = organizationRelHistService.insertOrganizationRelHist(organizationVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationRelHistUpdatePopUp
	 * 2. ClassName : OrganizationRelHistController
	 * 3. Comment   : 조직관계수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 18. 오후 3:20:55
	 * </PRE>
	 *   @return String
	 *   @param organizationVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationRelHistUpdatePopUp", method = RequestMethod.POST)
	public String organizationRelHistUpdatePopUp(
			OrganizationVO organizationVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		organizationVO.setLngTyp(lngTyp);
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "DN00050";		//관계유형
		
		OrganizationVO uppData = organizationRelHistService.getOrganizationDetail2(organizationVO);
		
		model.addAttribute("relTpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("organization", organizationVO);
		model.addAttribute("uppData", uppData);

		return URL + "/popup/organizationRelHistUpdatePopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateOrganizationRelHist
	 * 2. ClassName : OrganizationRelHistController
	 * 3. Comment   : 조직관계 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 18. 오후 3:21:11
	 * </PRE>
	 *   @return void
	 *   @param organizationVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateOrganizationRelHist", method = RequestMethod.POST)
	public void updateOrganizationRelHist(
			OrganizationVO organizationVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		organizationVO.setRegrId(session.getUserId());
		organizationVO.setChgrId(session.getUserId());
		organizationVO.setRegDate(DateUtil.sysdate());
		organizationVO.setChgDate(DateUtil.sysdate());
		organizationVO.setLngTyp(lngTyp);
		
		String result = organizationRelHistService.updateOrganizationRelHist(organizationVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteOrganizationRelHist
	 * 2. ClassName : OrganizationRelHistController
	 * 3. Comment   : 조직관계 삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 18. 오후 3:22:02
	 * </PRE>
	 *   @return void
	 *   @param organizationVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteOrganizationRelHist", method = RequestMethod.POST)
	public void deleteOrganizationRelHist(
			OrganizationVO organizationVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		organizationVO.setRegrId(session.getUserId());
		organizationVO.setChgrId(session.getUserId());
		organizationVO.setRegDate(DateUtil.sysdate());
		organizationVO.setChgDate(DateUtil.sysdate());
		organizationVO.setLngTyp(lngTyp);
		
		//조직관계이력 테이블의 적용종료일만 하루 전날 짜로 수정.
		String result = organizationRelHistService.deleteOrganizationRelHist(organizationVO);
		
		model.addAttribute("result", result);
	
	}
}
