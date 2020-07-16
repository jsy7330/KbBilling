package com.ntels.ccbs.customer.controller.contract.contract;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.CtrtInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.CustInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderMastInfoVO;
import com.ntels.ccbs.customer.service.contract.contract.ContractManagementService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


/**
 * <PRE>
 * 1. ClassName: ContractManagementController
 * 2. FileName : ContractManagementController.java
 * 3. Package  : com.ntels.ccbs.customer.controller.contract.contract
 * 4. Comment  : 계약 정보 관리 Controller
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 3. 오후 3:14:37
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 3.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/customer/contract/contract/contractManagement")
public class ContractManagementController {
	
	private static String URL = "customer/contract/contract/contractManagement";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ContractManagementService contractManagementService;
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: viewInit
	 * 2. ClassName : ContractManagementController
	 * 3. Comment   : 계약 상담 통합 Page Open 
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 3:14:54
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "contractManagement", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("searchTpCodeList", commonDataService.getCommonCodeList("CM00015", lng)); //조회유형
		model.addAttribute("orderTpCodeList", commonDataService.getCommonCodeList("CM00022", lng));  //오더유형
		
		/*
		 * 접수 조회 화면에서 링크되었을 때 받는 조건
		 */
		
		HttpSession session = request.getSession();
		
		if(StringUtils.isNotEmpty((String)session.getAttribute("condCustSoId"))){
			model.addAttribute("linkGbn" , "Y");
			model.addAttribute("condCustSoId", (String)session.getAttribute("condCustSoId"));
			model.addAttribute("condCustNm", (String)session.getAttribute("condCustNm"));
			model.addAttribute("condCustId", (String)session.getAttribute("condCustId"));
			model.addAttribute("condCtrtId", (String)session.getAttribute("condCtrtId"));
			model.addAttribute("condOrderId", (String)session.getAttribute("condOrderId"));
			session.removeAttribute("condCustSoId");
			session.removeAttribute("condCustNm");
			session.removeAttribute("condCustId");
			session.removeAttribute("condCtrtId");
			session.removeAttribute("condOrderId");
			
			
		}
		return URL + "/contractManagement";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getCustInfoSearchAction
	 * 2. ClassName : ContractManagementController
	 * 3. Comment   : 고객 정보 조회 사전 조회 Action
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 3:15:58
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param condCustSoId 조회 대상 사업ID
	 *   @param condCustNm 조회 대상 고객명
	 *   @param condCustId 조회 대상 고객ID
	 *   @param condSearchTp 조회 유형 코드
	 *   @param condSearchKey 조회 대상 유형 값
	 */
	@RequestMapping(value = "getCustInfoSearchAction", method = RequestMethod.POST)
	public String getCustInfoSearchAction(Model model, HttpServletRequest request,
			String condCustSoId,
			String condCustNm,
			String condCustId,
			String condSearchTp,
			String condSearchKey) {
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		List<Map<String,Object>> custList = contractManagementService.getCustInfoForSearching(sessionUser.getSoAuthList(),
				condCustSoId, 
				condCustNm,
				condCustId,
				condSearchTp,
				condSearchKey);
		
		model.addAttribute("custList", custList);
		model.addAttribute("custListCnt", custList.size());
		
		return URL + "/ajax/contractManagement";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getCustInfoAction
	 * 2. ClassName : ContractManagementController
	 * 3. Comment   : 고객 정보 조회 Action
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 3:16:55
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 조회 대상 사업ID
	 *   @param custId 조회 대상 고객ID
	 */
	@RequestMapping(value = "getCustInfoAction", method = RequestMethod.POST)
	public String getCustInfoAction(Model model, HttpServletRequest request,
			String soId,
			String custId) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		CustInfoVO custInfo = contractManagementService.getCustInfo(soId, custId, lng, today);
		
		model.addAttribute("custInfo", custInfo);
		
		List<OrderMastInfoVO> orderServiceList = null;
 		if(custInfo != null && StringUtils.isNotEmpty(custInfo.getCustId())){
 			/**
 			 * 사업자의 이용가능한 오더 유형 조회
 			 */
 			orderServiceList = contractManagementService.getOrderListBySoId(soId, custInfo.getCustTp(), lng);
			
		}
 		model.addAttribute("orderCdSel", orderServiceList);
		
		return URL + "/ajax/contractManagement";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getCtrtInfoListAction
	 * 2. ClassName : ContractManagementController
	 * 3. Comment   :계약 정보 조회 Action
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 3:18:00
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 조회 대상 사업ID
	 *   @param custId 조회 대상 고객ID
	 *   @param isIncludeTermYn 해지고객포함여부(Y/N)
	 *   @param sidx 정렬키
	 *   @param sord 정렬기준
	 */
	@RequestMapping(value = "getCtrtInfoListAction", method = RequestMethod.POST)
	public String getCtrtInfoListAction(Model model, HttpServletRequest request,
			String soId,
			String custId,
			String isIncludeTermYn,
			String sidx,
			String sord) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<CtrtInfoVO> ctrtList = contractManagementService.getCtrtInfoList(soId, custId, isIncludeTermYn, lng, today, sidx, sord);
		model.addAttribute("ctrtList", ctrtList);
		model.addAttribute("totalCount", ctrtList.size());
		
		return URL + "/ajax/contractManagement";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getCtrtChangeInfoList
	 * 2. ClassName : CustomerManagementController
 	 * 3. Comment   : 계약 변경 이력 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 2:54:26
	 * </PRE>
	 *   @return String Response URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 */
	@RequestMapping(value = "getCtrtChangeInfoList", method = RequestMethod.POST)
	public String getCtrtChangeInfoList(Model model, HttpServletRequest request
			, String soId
			, String custId
			, String ctrtId) {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<OrderInfoVO> orderList = contractManagementService.getOrderInfoList(soId, custId, ctrtId, lng, today); 
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("totalCount", orderList.size());
		
		return URL + "/ajax/contractManagement";
	}
	

	
	/**
	 * <PRE>
	 * 1. MethodName: getCustAddrInfo
	 * 2. ClassName : ContractManagementController
	 * 3. Comment   : 고객정보 주소 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 10. 오후 2:09:28
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 */
	@RequestMapping(value = "getCustAddrInfoAction", method = RequestMethod.POST)
	public String getCustAddrInfo(Model model, HttpServletRequest request,
			String soId,
			String custId) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		CustInfoVO custInfo = contractManagementService.getCustInfo(soId, custId, lng, today);
		
		model.addAttribute("custInfo", custInfo);
		return URL + "/ajax/contractManagement";
	}
}
