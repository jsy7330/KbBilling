package com.ntels.ccbs.system.controller.common.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.common.common.OrganizationMngVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.common.OrganizationMngService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/system/common/common/organizationMng")
public class OrganizationMngController {

	private static String URL = "system/common/common/organizationMng";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private OrganizationMngService organizationMngService;
	
	@RequestMapping(value = "organizationSearchPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String organizationSearchPopup(
			OrganizationMngVO organizationMngVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		organizationMngVO.setLngTyp(lngTyp);
		
		model.addAttribute("organization", organizationMngVO);
		
		if(organizationMngVO.getPopType().equals("m")){
			return URL + "/mpopup/organizationSearchPopup";
		}else{
			return URL + "/opopup/organizationSearchPopup";
		}
			
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationListAction
	 * 2. ClassName : OrganizationMngController
	 * 3. Comment   : 조직검색
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 18. 오전 9:50:22
	 * </PRE>
	 *   @return void
	 *   @param organizationMngVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "organizationListAction", method = RequestMethod.POST)
	public void organizationListAction(
			OrganizationMngVO organizationMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		organizationMngVO.setLngTyp(lngTyp);
		organizationMngVO.setSidx(sidx);
		organizationMngVO.setSord(sord);
		
		List<String> orgIdList = new ArrayList<String>();
		List<OrganizationMngVO> paramList = new ArrayList<OrganizationMngVO>();
		OrganizationMngVO param = new OrganizationMngVO();
		
		//orgId 공백처리
		if(organizationMngVO.getOrgId() != null && !organizationMngVO.getOrgId().trim().equals("")){
			param.setOrgId(organizationMngVO.getOrgId());
			paramList.add(param);
			
			orgIdList = organizationMngService.recursionOrganizationList(paramList);
		}
		organizationMngVO.setOrgIdList(orgIdList);
		
        List<OrganizationMngVO> list = new ArrayList<OrganizationMngVO>();
		int count = 0;
		count = organizationMngService.organizationCount(organizationMngVO);		
		if (count > 0) list = organizationMngService.organizationList(organizationMngVO, page, perPageRow);

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
	
	
	@RequestMapping(value = "organizationSearchPopup2", method = {RequestMethod.POST, RequestMethod.GET})
	public String organizationSearchPopup2(
			OrganizationMngVO organizationMngVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		organizationMngVO.setLngTyp(lngTyp);
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "DN00038";		//유형
		String arClCd = "DN00100";		//지역분류
		String arTpCd = "DN00101";		//지역구분
		
		model.addAttribute("tpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("arClCd", commonDataService.getCommonCodeList(arClCd, lngTyp));
		model.addAttribute("arTpCd", commonDataService.getCommonCodeList(arTpCd, lngTyp));
		model.addAttribute("organization", organizationMngVO);
		
		if(organizationMngVO.getPopType().equals("m")){
			return URL + "/mpopup/organizationSearchPopup2";
		}else{
			return URL + "/opopup/organizationSearchPopup2";
		}
			
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: organizationListAction
	 * 2. ClassName : OrganizationMngController
	 * 3. Comment   : 조직검색
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 18. 오전 9:50:22
	 * </PRE>
	 *   @return void
	 *   @param organizationMngVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "organization2ListAction", method = RequestMethod.POST)
	public void organization2ListAction(
			OrganizationMngVO organizationMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		organizationMngVO.setLngTyp(lngTyp);
		organizationMngVO.setSidx(sidx);
		organizationMngVO.setSord(sord);
		
		List<String> orgIdList = new ArrayList<String>();
		List<OrganizationMngVO> paramList = new ArrayList<OrganizationMngVO>();
		OrganizationMngVO param = new OrganizationMngVO();
		
		//orgId 공백처리
		if(organizationMngVO.getOrgId() != null && !organizationMngVO.getOrgId().trim().equals("")){
			param.setOrgId(organizationMngVO.getOrgId());
			paramList.add(param);
			
			orgIdList = organizationMngService.recursionOrganizationList(paramList);
		}
		organizationMngVO.setOrgIdList(orgIdList);
		
        List<OrganizationMngVO> list = new ArrayList<OrganizationMngVO>();
		int count = 0;
		count = organizationMngService.organizationCount2(organizationMngVO);		
		if (count > 0) list = organizationMngService.organizationList2(organizationMngVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
}
