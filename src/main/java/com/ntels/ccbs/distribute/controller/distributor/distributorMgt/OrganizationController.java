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
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.OrganizationService;
import com.ntels.ccbs.system.domain.common.common.OrganizationMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.common.OrganizationMngService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/distributor/distributorMgt/organization")
public class OrganizationController {
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private OrganizationMngService organizationMngService;
	
	private String URL = "distributor/distributor/distributorMgt/organization";

	/** per page. */
    //private @Value("${paging.per_page}") Integer perPage;
    
    /**
     * 
     * <PRE>
     * 1. MethodName: list
     * 2. ClassName : OrganizationAction
     * 3. Comment   : 조직관리 조회페이지 호출
     * 4. 작성자    : jhkim
     * 5. 작성일    : 2016. 5. 11. 오후 2:19:37
     * </PRE>
     *   @return String
     *   @param model
     *   @param organization
     *   @param request
     *   @return
     *   @throws Exception
     */
	@RequestMapping(value = "organizationList", method = RequestMethod.POST)
	public String list(Model model, OrganizationVO organization, HttpServletRequest request) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "DN00038";
		String tpDtlCd = "DN00039";
		String arClCd = "DN00100";		//지역분류
		String arTpCd = "DN00101";		//지역구분
		
		model.addAttribute("tpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("tpDtlCd", commonDataService.getCommonCodeList(tpDtlCd, lngTyp));
		model.addAttribute("arClCd", commonDataService.getCommonCodeList(arClCd, lngTyp));
		model.addAttribute("arTpCd", commonDataService.getCommonCodeList(arTpCd, lngTyp));
		
		return URL + "/organizationList";
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationListAction
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직 목록 조회 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 11. 오후 2:20:09
	 * </PRE>
	 *   @return void
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationListAction", method = RequestMethod.POST)
	public void organizationListAction(
			OrganizationVO organization
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		organization.setLngTyp(lngTyp);
		organization.setSidx(sidx);
		organization.setSord(sord);
		
		List<String> orgIdList = new ArrayList<String>();
		List<OrganizationMngVO> paramList = new ArrayList<OrganizationMngVO>();
		OrganizationMngVO param = new OrganizationMngVO();
		
		//orgId 공백처리
		if(organization.getOrgId() != null && !organization.getOrgId().trim().equals("")){
			param.setOrgId(organization.getOrgId());
			paramList.add(param);
			
			orgIdList = organizationMngService.recursionOrganizationList(paramList);
		}
		organization.setOrgIdList(orgIdList);
		
		
        List<OrganizationVO> list = new ArrayList<OrganizationVO>();
		int count = 0;
		count = organizationService.organizationCount(organization);		
		if (count > 0) list = organizationService.organizationList(organization, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getOrganizationListAction
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직 상세 정보 호출
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 11. 오후 2:20:39
	 * </PRE>
	 *   @return void
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "getOrganizationListAction", method = RequestMethod.POST)
	public void getOrganizationListAction(
			OrganizationVO organization
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		organization.setLngTyp(lngTyp);

		OrganizationVO data = organizationService.getOrganization(organization);
		
		model.addAttribute("organizationData", data);
	
	}
    
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationRelListAction
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직관계정보이력 호출
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 11. 오후 2:21:00
	 * </PRE>
	 *   @return void
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationRelListAction", method = RequestMethod.POST)
	public void organizationRelListAction(
			OrganizationVO organization
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		organization.setLngTyp(lngTyp);
		organization.setSidx(sidx);
		organization.setSord(sord);
		
		
        List<OrganizationVO> list = new ArrayList<OrganizationVO>();
		list = organizationService.organizationRelList(organization);
		
		int count = list.size();

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getCommonCodeListByRef1ListAction
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 공통코드리스트 조회(참조1)
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 11. 오후 2:21:14
	 * </PRE>
	 *   @return void
	 *   @param grpCd
	 *   @param ref1
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "getCommonCodeListByRef1ListAction", method = RequestMethod.POST)
	public void getCommonCodeListByRef1ListAction(String grpCd, String ref1, Model model,
			HttpServletRequest request) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage")
				.toString();
		
		List<CommonCodeVO> codeList = commonDataService.getCommonCodeListByRef1(grpCd, ref1, lngTyp);

		model.addAttribute("codeList", codeList);

	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationRelListPopUp
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직이력 조회 팝업 
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 11. 오후 2:21:42
	 * </PRE>
	 *   @return String
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationRelListPopUp", method = RequestMethod.POST)
	public String organizationRelListPopUp(
			OrganizationVO organization
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		model.addAttribute("organization", organization);

		return URL + "/popup/organizationRelPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationHisListAction
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직이력 조회 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 11. 오후 2:22:03
	 * </PRE>
	 *   @return void
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationHisListAction", method = RequestMethod.POST)
	public void organizationHisListAction(
			OrganizationVO organization
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		organization.setLngTyp(lngTyp);
		organization.setSidx(sidx);
		organization.setSord(sord);
		
        List<OrganizationVO> list = new ArrayList<OrganizationVO>();
		int count = 0;
		count = organizationService.organizationHisCount(organization);		
		if (count > 0) list = organizationService.organizationHisList(organization, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationInsertPopUp
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직 등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 11. 오후 2:22:24
	 * </PRE>
	 *   @return String
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationInsertPopUp", method = RequestMethod.POST)
	public String organizationInsertPopUp(
			OrganizationVO organization
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "DN00038";		//유형
		String arClCd = "DN00100";		//지역분류
		String arTpCd = "DN00101";		//지역구분
		String inqPermCd = "DN00051";	//조회권한
		String orgStatCd = "DN00041";	//조직상태
		String orgLvCd = "DN00052";		//조직레벨
		String privTpCd = "DN00044";	//자사구분
		
		model.addAttribute("tpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("arClCd", commonDataService.getCommonCodeList(arClCd, lngTyp));
		model.addAttribute("arTpCd", commonDataService.getCommonCodeList(arTpCd, lngTyp));
		model.addAttribute("inqPermCd", commonDataService.getCommonCodeList(inqPermCd, lngTyp));
		model.addAttribute("orgStatCd", commonDataService.getCommonCodeList(orgStatCd, lngTyp));
		model.addAttribute("orgLvCd", commonDataService.getCommonCodeList(orgLvCd, lngTyp));
		model.addAttribute("privTpCd", commonDataService.getCommonCodeList(privTpCd, lngTyp));
		
		model.addAttribute("organization", organization);

		return URL + "/popup/organizationInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getExistOrganizationInfo
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직 아이디 중복 체크
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 11. 오후 2:22:35
	 * </PRE>
	 *   @return void
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "getExistOrganizationInfo", method = RequestMethod.POST)
	public void getExistOrganizationInfo(
			OrganizationVO organization
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		organization.setLngTyp(lngTyp);

		int orgIdCount = organizationService.getExistOrganizationInfo(organization);
		
		model.addAttribute("orgIdCount", orgIdCount);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertOrganization
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 12. 오후 1:18:15
	 * </PRE>
	 *   @return void
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertOrganization", method = RequestMethod.POST)
	public void insertOrganization(
			OrganizationVO organization
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		organization.setRegrId(session.getUserId());
		organization.setChgrId(session.getUserId());
		organization.setRegDate(DateUtil.sysdate());
		organization.setChgDate(DateUtil.sysdate());
		organization.setLngTyp(lngTyp);
		
		String result = organizationService.insertOrganization(organization);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationUpdatePopUp
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직 수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 12. 오후 1:19:05
	 * </PRE>
	 *   @return String
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationUpdatePopUp", method = RequestMethod.POST)
	public String organizationUpdatePopUp(
			OrganizationVO organization
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		organization.setLngTyp(lngTyp);
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "DN00038";		//유형
		String arClCd = "DN00100";		//지역분류
		String arTpCd = "DN00101";		//지역구분
		String inqPermCd = "DN00051";	//조회권한
		String orgStatCd = "DN00041";	//조직상태
		String orgLvCd = "DN00052";		//조직레벨
		String privTpCd = "DN00044";	//자사구분
		
		model.addAttribute("tpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("arClCd", commonDataService.getCommonCodeList(arClCd, lngTyp));
		model.addAttribute("arTpCd", commonDataService.getCommonCodeList(arTpCd, lngTyp));
		model.addAttribute("inqPermCd", commonDataService.getCommonCodeList(inqPermCd, lngTyp));
		model.addAttribute("orgStatCd", commonDataService.getCommonCodeList(orgStatCd, lngTyp));
		model.addAttribute("orgLvCd", commonDataService.getCommonCodeList(orgLvCd, lngTyp));
		model.addAttribute("privTpCd", commonDataService.getCommonCodeList(privTpCd, lngTyp));
		
		OrganizationVO data = organizationService.getOrganization(organization);
		model.addAttribute("organization", data);

		return URL + "/popup/organizationUpdatePopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateOrganization
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 13. 오전 9:37:24
	 * </PRE>
	 *   @return void
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateOrganization", method = RequestMethod.POST)
	public void updateOrganization(
			OrganizationVO organization
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		organization.setRegrId(session.getUserId());
		organization.setChgrId(session.getUserId());
		organization.setRegDate(DateUtil.sysdate());
		organization.setChgDate(DateUtil.sysdate());
		organization.setLngTyp(lngTyp);
		
		String result = organizationService.updateOrganization(organization);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationSearchPopup
	 * 2. ClassName : OrganizationAction
	 * 3. Comment   : 조직 리스트 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 13. 오후 2:47:38
	 * </PRE>
	 *   @return String
	 *   @param organization
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	/*
	@RequestMapping(value = "organizationSearchPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String organizationSearchPopup(
			OrganizationVO organization
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		organization.setLngTyp(lngTyp);
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "DN00038";		//유형
		String arClCd = "DN00100";		//지역분류
		String arTpCd = "DN00101";		//지역구분
		
		model.addAttribute("tpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("arClCd", commonDataService.getCommonCodeList(arClCd, lngTyp));
		model.addAttribute("arTpCd", commonDataService.getCommonCodeList(arTpCd, lngTyp));
		model.addAttribute("organization", organization);
		
		if(organization.getPopType().equals("m")){
			return thisUrl + "/popup/organizationSearchPopup";
		}else{
			return thisUrl + "/opopup/organizationSearchPopup";
		}
			
	}
	*/
	
	
}
