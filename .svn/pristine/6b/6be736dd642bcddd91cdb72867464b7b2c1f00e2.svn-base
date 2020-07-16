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
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.DistributionInfoVO;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.DistributionInfoService;
import com.ntels.ccbs.system.domain.common.common.OrganizationMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.common.OrganizationMngService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/distributor/distributorMgt/distributionInfo")
public class DistributionInfoController {

	@Autowired
	private OrganizationMngService organizationMngService;
	
	@Autowired
	private DistributionInfoService distributionInfoService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String URL = "distributor/distributor/distributorMgt/distributionInfo";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: distributionInfo
	 * 2. ClassName : DistributionInfoController
	 * 3. Comment   : 유통정보 관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 20. 오후 5:23:22
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param distributionInfoVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "distributionInfo", method = RequestMethod.POST)
	public String distributionInfo(Model model, DistributionInfoVO distributionInfoVO, HttpServletRequest request) throws Exception {
		
		return URL + "/distributionInfo";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: distributionInfoListAction
	 * 2. ClassName : DistributionInfoController
	 * 3. Comment   : 유통관리 정보 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 20. 오후 5:23:43
	 * </PRE>
	 *   @return void
	 *   @param distributionInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "distributionInfoListAction", method = RequestMethod.POST)
	public void distributionInfoListAction(
			DistributionInfoVO distributionInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		distributionInfoVO.setLngTyp(lngTyp);
		//distributionInfoVO.setSidx(CommonUtil.camelToDbStyle(sidx));
		distributionInfoVO.setSidx(sidx);
		distributionInfoVO.setSord(sord);
		
		List<String> orgIdList = new ArrayList<String>();
		List<OrganizationMngVO> paramList = new ArrayList<OrganizationMngVO>();
		OrganizationMngVO param = new OrganizationMngVO();
		
		//orgId 공백처리
		if(distributionInfoVO.getOrgId() != null && !distributionInfoVO.getOrgId().trim().equals("")){
			param.setOrgId(distributionInfoVO.getOrgId());
			paramList.add(param);
			
			orgIdList = organizationMngService.recursionOrganizationList(paramList);
		}
		distributionInfoVO.setOrgIdList(orgIdList);
		
		
        List<DistributionInfoVO> list = new ArrayList<DistributionInfoVO>();
		int count = 0;
		count = distributionInfoService.distributionInfoCount(distributionInfoVO);
		if (count > 0) list = distributionInfoService.distributionInfoList(distributionInfoVO, page, perPageRow);

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
	 * 2. ClassName : DistributionInfoController
	 * 3. Comment   : 유통관리 정보 등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 20. 오후 5:23:56
	 * </PRE>
	 *   @return String
	 *   @param distributionInfoVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "distributionInfoInsertPopUp", method = RequestMethod.POST)
	public String organizationInsertPopUp(
			DistributionInfoVO distributionInfoVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("busiTpCd", commonDataService.getCommonCodeList("DN00040", lngTyp));		//사업자구분
		model.addAttribute("privTpCd", commonDataService.getCommonCodeList("DN00044", lngTyp));		//자사구분코드
		model.addAttribute("orgStatCd", commonDataService.getCommonCodeList("DN00041", lngTyp));		//조직상태
		model.addAttribute("bnkCd", commonDataService.getCommonCodeList("CM00006", lngTyp));		//조직상태
		
		model.addAttribute("distributionInfoVO", distributionInfoVO);

		return URL + "/popup/distributionInfoInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: selectDistributionInfo
	 * 2. ClassName : DistributionInfoController
	 * 3. Comment   : 유통관리 상세정보 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 21. 오후 2:16:16
	 * </PRE>
	 *   @return void
	 *   @param distributionInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "selectDistributionInfo", method = RequestMethod.POST)
	public void selectDistributionInfo(
			DistributionInfoVO distributionInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		distributionInfoVO.setLngTyp(lngTyp);
		
		DistributionInfoVO returnData = distributionInfoService.selectDistributionInfo(distributionInfoVO);
		
		model.addAttribute("distributionInfoVO", returnData);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertDistributionInfo
	 * 2. ClassName : DistributionInfoController
	 * 3. Comment   : 유통정보 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 21. 오후 2:18:00
	 * </PRE>
	 *   @return void
	 *   @param distributionInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertDistributionInfo", method = RequestMethod.POST)
	public void insertDistributionInfo(
			DistributionInfoVO distributionInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		distributionInfoVO.setRegrId(session.getUserId());
		distributionInfoVO.setChgrId(session.getUserId());
		distributionInfoVO.setRegDate(DateUtil.sysdate());
		distributionInfoVO.setChgDate(DateUtil.sysdate());
		distributionInfoVO.setLngTyp(lngTyp);
		
		int result = distributionInfoService.insertDistributionInfo(distributionInfoVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateDistributionInfo
	 * 2. ClassName : DistributionInfoController
	 * 3. Comment   : 유통정보 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 21. 오후 2:18:09
	 * </PRE>
	 *   @return void
	 *   @param distributionInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateDistributionInfo", method = RequestMethod.POST)
	public void updateDistributionInfo(
			DistributionInfoVO distributionInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		distributionInfoVO.setRegrId(session.getUserId());
		distributionInfoVO.setChgrId(session.getUserId());
		distributionInfoVO.setRegDate(DateUtil.sysdate());
		distributionInfoVO.setChgDate(DateUtil.sysdate());
		distributionInfoVO.setLngTyp(lngTyp);
		
		int result = distributionInfoService.updateDistributionInfo(distributionInfoVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: distributionInfoUpdatePopUp
	 * 2. ClassName : DistributionInfoController
	 * 3. Comment   : 유통정보 수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 23. 오후 3:31:57
	 * </PRE>
	 *   @return String
	 *   @param distributionInfoVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "distributionInfoUpdatePopUp", method = RequestMethod.POST)
	public String distributionInfoUpdatePopUp(
			DistributionInfoVO distributionInfoVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("busiTpCd", commonDataService.getCommonCodeList("DN00040", lngTyp));		//사업자구분
		model.addAttribute("privTpCd", commonDataService.getCommonCodeList("DN00044", lngTyp));		//자사구분코드
		model.addAttribute("orgStatCd", commonDataService.getCommonCodeList("DN00041", lngTyp));		//조직상태
		model.addAttribute("bnkCd", commonDataService.getCommonCodeList("CM00006", lngTyp));		//조직상태
		
		model.addAttribute("distributionInfoVO", distributionInfoVO);

		return URL + "/popup/distributionInfoUpdatePopUp";
		
	}
	
}
