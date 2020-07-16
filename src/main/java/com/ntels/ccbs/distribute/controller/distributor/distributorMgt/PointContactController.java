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
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.PointContactVO;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.PointContactService;
import com.ntels.ccbs.system.domain.common.common.OrganizationMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.common.OrganizationMngService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/distributor/distributorMgt/pointContact")
public class PointContactController {

	@Autowired
	private OrganizationMngService organizationMngService;
	
	@Autowired
	private PointContactService pointContactService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String URL = "distributor/distributor/distributorMgt/pointContact";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: distributionInfo
	 * 2. ClassName : PointContactController
	 * 3. Comment   : 접점관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 24. 오전 10:35:42
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param pointContactVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "pointContact", method = RequestMethod.POST)
	public String distributionInfo(Model model, PointContactVO pointContactVO, HttpServletRequest request) throws Exception {
		
		return URL + "/pointContact";
	}
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: pointContactListAction
	 * 2. ClassName : PointContactController
	 * 3. Comment   : 접점 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 24. 오전 10:34:59
	 * </PRE>
	 *   @return void
	 *   @param pointContactVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "pointContactListAction", method = RequestMethod.POST)
	public void pointContactListAction(
			PointContactVO pointContactVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		pointContactVO.setLngTyp(lngTyp);
		pointContactVO.setSidx(sidx);
		pointContactVO.setSord(sord);
		
		List<String> orgIdList = new ArrayList<String>();
		List<OrganizationMngVO> paramList = new ArrayList<OrganizationMngVO>();
		OrganizationMngVO param = new OrganizationMngVO();
		
		//orgId 공백처리
		if(pointContactVO.getOrgId() != null && !pointContactVO.getOrgId().trim().equals("")){
			param.setOrgId(pointContactVO.getOrgId());
			paramList.add(param);
			
			orgIdList = organizationMngService.recursionOrganizationList(paramList);
		}
		pointContactVO.setOrgIdList(orgIdList);
		
		
        List<PointContactVO> list = new ArrayList<PointContactVO>();
		int count = 0;
		count = pointContactService.pointContactCount(pointContactVO);
		if (count > 0) list = pointContactService.pointContactList(pointContactVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: pointContactUpdatePopUp
	 * 2. ClassName : PointContactController
	 * 3. Comment   : 접점 수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 24. 오전 10:35:26
	 * </PRE>
	 *   @return String
	 *   @param pointContactVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "pointContactUpdatePopUp", method = RequestMethod.POST)
	public String pointContactUpdatePopUp(
			PointContactVO pointContactVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("busiTpCd", commonDataService.getCommonCodeList("DN00040", lngTyp));		//사업자구분
		model.addAttribute("onlnTpCd", commonDataService.getCommonCodeList("DN00043", lngTyp));		//온라인구분
		model.addAttribute("orgStatCd", commonDataService.getCommonCodeList("DN00041", lngTyp));		//조직상태
		
		model.addAttribute("pointContactVO", pointContactVO);

		return URL + "/popup/pointContactUpdatePopUp";
		
	}
	
	
	@RequestMapping(value = "updatePointContact", method = RequestMethod.POST)
	public void updatePointContact(
			PointContactVO pointContactVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		pointContactVO.setRegrId(session.getUserId());
		pointContactVO.setChgrId(session.getUserId());
		pointContactVO.setRegDate(DateUtil.sysdate());
		pointContactVO.setChgDate(DateUtil.sysdate());
		pointContactVO.setLngTyp(lngTyp);
		
		int result = pointContactService.updatePointContact(pointContactVO);
		
		model.addAttribute("result", result);
	
	}
}
