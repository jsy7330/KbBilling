package com.ntels.ccbs.distribute.controller.logistics.inventoryMoveChangeMng;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.distribute.domain.logistics.inventoryMoveChangeMng.ContactProductVO;
import com.ntels.ccbs.distribute.service.logistics.inventoryMoveChangeMng.ContactProductService;
import com.ntels.ccbs.system.domain.common.common.OrganizationMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.common.OrganizationMngService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/inventoryMoveChangeMng/contactProduct")
public class ContactProductController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private OrganizationMngService organizationMngService;
	
	@Autowired
	private ContactProductService contactProductService;
	
	private String URL = "distributor/logistics/inventoryMoveChangeMng/contactProduct";
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: contactProduct
	 * 2. ClassName : ContactProductController
	 * 3. Comment   : 접점별제품관리
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 12. 오전 10:32:04
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param logisticsCenterIssueInspVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "contactProduct", method = RequestMethod.POST)
	public String contactProduct(Model model, ContactProductVO contactProductVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		
		return URL + "/contactProduct";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: ownOrgEqtInfoListAction
	 * 2. ClassName : ContactProductController
	 * 3. Comment   : 접점별 제품 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 17. 오전 9:53:20
	 * </PRE>
	 *   @return void
	 *   @param contactProductVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "ownOrgEqtInfoListAction", method = RequestMethod.POST)
	public void ownOrgEqtInfoListAction(
			ContactProductVO contactProductVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		contactProductVO.setLngTyp(lngTyp);
		contactProductVO.setSidx(sidx);
		contactProductVO.setSord(sord);
		
		List<String> orgIdList = new ArrayList<String>();
		List<OrganizationMngVO> paramList = new ArrayList<OrganizationMngVO>();
		OrganizationMngVO param = new OrganizationMngVO();
		
		//orgId 공백처리
		if(contactProductVO.getOrgId() != null && !contactProductVO.getOrgId().trim().equals("")){
			param.setOrgId(contactProductVO.getOrgId());
			paramList.add(param);
			
			orgIdList = organizationMngService.recursionOrganizationList(paramList);
		}
		contactProductVO.setOrgIdList(orgIdList);
		
		
        List<ContactProductVO> list = new ArrayList<ContactProductVO>();
		int count = 0;
		count = contactProductService.ownOrgEqtInfoCount(contactProductVO);
		if (count > 0) list = contactProductService.ownOrgEqtInfoList(contactProductVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: contactProductInsertPopUp
	 * 2. ClassName : ContactProductController
	 * 3. Comment   : 접점별 제품 등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 17. 오전 9:56:59
	 * </PRE>
	 *   @return String
	 *   @param contactProductVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "contactProductInsertPopUp", method = RequestMethod.POST)
	public String contactProductInsertPopUp(
			ContactProductVO contactProductVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("eqtStatCd", commonDataService.getCommonCodeList("DN00066", lngTyp));	//단말기상태
		
		model.addAttribute("orgList", contactProductService.orgList(contactProductVO));		//조직리스트
		
		model.addAttribute("contactProductVO", contactProductVO);

		return URL + "/popup/contactProductInsertPopUp";
		
	}
	
	
	@RequestMapping(value = "ctOrgEqtInfoListAction", method = RequestMethod.POST)
	public void ctOrgEqtInfoListAction(
			ContactProductVO contactProductVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		contactProductVO.setLngTyp(lngTyp);
		contactProductVO.setSidx(sidx);
		contactProductVO.setSord(sord);
		
        List<ContactProductVO> list  = contactProductService.ctOrgEqtInfoList(contactProductVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)list.size() / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	@RequestMapping(value = "updateEqtCtOrg", method = RequestMethod.POST)
	public void updateEqtCtOrg(
			@RequestBody List<ContactProductVO> ContactProductVs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = contactProductService.updateEqtCtOrg(ContactProductVs, session);
		
		model.addAttribute("result", result);
		
	}
	
	
}
