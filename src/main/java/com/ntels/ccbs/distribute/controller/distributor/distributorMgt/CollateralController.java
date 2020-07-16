package com.ntels.ccbs.distribute.controller.distributor.distributorMgt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.CollateralVO;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.CollateralService;
import com.ntels.ccbs.system.domain.common.common.OrganizationMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.common.OrganizationMngService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/distributor/distributorMgt/collateral")
public class CollateralController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private OrganizationMngService organizationMngService;
	
	@Autowired
	private CollateralService collateralService;
	
	private String URL = "distributor/distributor/distributorMgt/collateral";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: employee
	 * 2. ClassName : CollateralController
	 * 3. Comment   : 담보관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 8. 오후 2:36:06
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param collateralVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "collateral", method = RequestMethod.POST)
	public String employee(Model model, CollateralVO collateralVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("collKndCd", commonDataService.getCommonCodeList("DN00045", lngTyp));
		
		return URL + "/collateral";
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: collateralListAction
	 * 2. ClassName : CollateralController
	 * 3. Comment   : 담보관리 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 8. 오후 2:36:21
	 * </PRE>
	 *   @return void
	 *   @param collateralVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "collateralListAction", method = RequestMethod.POST)
	public void collateralListAction(
			CollateralVO collateralVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		collateralVO.setLngTyp(lngTyp);
		collateralVO.setSidx(sidx);
		collateralVO.setSord(sord);
		
		List<String> orgIdList = new ArrayList<String>();
		List<OrganizationMngVO> paramList = new ArrayList<OrganizationMngVO>();
		OrganizationMngVO param = new OrganizationMngVO();
		
		//orgId 공백처리
		if(collateralVO.getOrgId() != null && !collateralVO.getOrgId().trim().equals("")){
			param.setOrgId(collateralVO.getOrgId());
			paramList.add(param);
			
			orgIdList = organizationMngService.recursionOrganizationList(paramList);
		}
		collateralVO.setOrgIdList(orgIdList);
		
		
        List<CollateralVO> list = new ArrayList<CollateralVO>();
		int count = 0;
		count = collateralService.collateralCount(collateralVO);		
		if (count > 0) list = collateralService.collateralList(collateralVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: collateralInsertPopUp
	 * 2. ClassName : CollateralController
	 * 3. Comment   : 보증보험등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 8. 오후 2:36:35
	 * </PRE>
	 *   @return String
	 *   @param collateralVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "collateralInsertPopUp", method = RequestMethod.POST)
	public String collateralInsertPopUp(
			CollateralVO collateralVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("assrKndCd", commonDataService.getCommonCodeList("DN00053", lngTyp));
		model.addAttribute("assrCorpCd", commonDataService.getCommonCodeList("DN00055", lngTyp));
		

		return URL + "/popup/collateralInsertPopUp";
		
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertCollInfo
	 * 2. ClassName : CollateralController
	 * 3. Comment   : 보증보험등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 8. 오후 2:36:47
	 * </PRE>
	 *   @return void
	 *   @param collateralVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertCollInfo", method = RequestMethod.POST)
	public void insertCollInfo(
			CollateralVO collateralVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		collateralVO.setRegrId(session.getUserId());
		collateralVO.setChgrId(session.getUserId());
		collateralVO.setRegDate(DateUtil.sysdate());
		collateralVO.setChgDate(DateUtil.sysdate());
		collateralVO.setLngTyp(lngTyp);
		
		int result = collateralService.insertCollInfo(collateralVO, session);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: selectOrgInfo
	 * 2. ClassName : CollateralController
	 * 3. Comment   : 여신부여여부 체크
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 8. 오후 4:39:45
	 * </PRE>
	 *   @return void
	 *   @param collateralVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "selectOrgInfo", method = RequestMethod.POST)
	public void selectOrgInfo(
			CollateralVO collateralVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		
		CollateralVO result = collateralService.selectOrgInfo(collateralVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: selectCollInfo
	 * 2. ClassName : CollateralController
	 * 3. Comment   : 상세조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 9. 오후 3:02:02
	 * </PRE>
	 *   @return void
	 *   @param collateralVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "selectCollInfo", method = RequestMethod.POST)
	public void selectCollInfo(
			CollateralVO collateralVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		//SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		collateralVO.setLngTyp(lngTyp);
		
		CollateralVO result = collateralService.selectCollInfo(collateralVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: collateralInsertPopUp
	 * 2. ClassName : CollateralController
	 * 3. Comment   : 보증보험 수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 9. 오후 3:04:07
	 * </PRE>
	 *   @return String
	 *   @param collateralVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "collateralUpdatePopUp", method = RequestMethod.POST)
	public String collateralUpdatePopUp(
			CollateralVO collateralVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("assrKndCd", commonDataService.getCommonCodeList("DN00053", lngTyp));
		model.addAttribute("assrCorpCd", commonDataService.getCommonCodeList("DN00055", lngTyp));
		

		return URL + "/popup/collateralUpdatePopUp";
		
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateCollInfo
	 * 2. ClassName : CollateralController
	 * 3. Comment   : 보증보험 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 9. 오후 3:03:43
	 * </PRE>
	 *   @return void
	 *   @param collateralVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateCollInfo", method = RequestMethod.POST)
	public void updateCollInfo(
			CollateralVO collateralVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		collateralVO.setRegrId(session.getUserId());
		collateralVO.setChgrId(session.getUserId());
		collateralVO.setRegDate(DateUtil.sysdate());
		collateralVO.setChgDate(DateUtil.sysdate());
		collateralVO.setLngTyp(lngTyp);
		
		int result = collateralService.updateCollInfo(collateralVO, session);
		
		model.addAttribute("result", result);
	
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteCollInfo
	 * 2. ClassName : CollateralController
	 * 3. Comment   : 보증보험 삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 9. 오후 5:00:49
	 * </PRE>
	 *   @return void
	 *   @param collateralVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteCollInfo", method = RequestMethod.POST)
	public void deleteCollInfo(
			CollateralVO collateralVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		collateralVO.setRegrId(session.getUserId());
		collateralVO.setChgrId(session.getUserId());
		collateralVO.setRegDate(DateUtil.sysdate());
		collateralVO.setChgDate(DateUtil.sysdate());
		collateralVO.setLngTyp(lngTyp);
		
		int result = collateralService.deleteCollInfo(collateralVO, session);
		
		model.addAttribute("result", result);
	
	}
	
}
