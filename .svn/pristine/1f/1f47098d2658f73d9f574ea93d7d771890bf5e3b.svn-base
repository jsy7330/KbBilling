package com.ntels.ccbs.customer.controller.customer.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpServerErrorException;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoVO;
import com.ntels.ccbs.customer.service.customer.customer.CustomerManagementService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/customer/customer/customer/customerManagement")
public class CustomerManagementController {
	
	private static String URL = "customer/customer/customer/customerManagement";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private CustomerManagementService customerManagementService;
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * <PRE>
	 * 1. MethodName: viewInit
	 * 2. ClassName : CustomerManagementController
	 * 3. Comment   : 고객관리 화면 오픈
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 3:07:15
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "customerMng", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		
		/*
			CM00009	고객유형
			CM00010	고객신분
			CM00011	세금여부
			CM00012	고객정보변경사유
			CM00013	업태
			CM00014 업종
			CM00050 주
		 */
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("custTpCdList", commonDataService.getCommonCodeList("CM00009", lng));
		model.addAttribute("custClCdList", commonDataService.getCommonCodeList("CM00010", lng));
		model.addAttribute("taxTpCdList", commonDataService.getCommonCodeList("CM00011", lng));
		model.addAttribute("chgResnCdList", commonDataService.getCommonCodeList("CM00012", lng));
		model.addAttribute("busiCndtCdList", commonDataService.getCommonCodeList("CM00013", lng));
		//model.addAttribute("busiTpCdList", commonDataService.getCommonCodeList("CM00014", lng));
		//model.addAttribute("stateCdList", commonDataService.getCommonCodeList("CM00050", lng));
		
		return URL + "/customerMng";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getCustomerInfoAction
	 * 2. ClassName : CustomerManagementController
	 * 3. Comment   : 고객정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 3:07:53
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param custNm 고객명
	 */
	@RequestMapping(value = "getCustomerInfoAction", method = RequestMethod.POST)
	public String getCustomerInfoAction(Model model, HttpServletRequest request, String soId, String custId, String custNm) {
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<CustomerInfoVO> custList = customerManagementService.getCustInfoList(soId, custId, custNm, sessionUser.getSoAuthList(), today,lng);
		
		model.addAttribute("custList", custList);
		model.addAttribute("custListCnt", custList.size());
		
		return URL + "/ajax/customerMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getBusiCndtAction
	 * 2. ClassName : CustomerManagementController
	 * 3. Comment   : 사업유형 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 3:09:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param code 업종코드
	 */
	@RequestMapping(value = "getBusiCndtAction", method = RequestMethod.POST)
	public String getBusiCndtAction(Model model, HttpServletRequest request, String code) {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("busiTpCdList", commonDataService.getCommonCodeListByRef1("CM00014", code, lng));
		
		return URL + "/ajax/customerMng";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: insertNewCustomerInfoAction
	 * 2. ClassName : CustomerManagementController
	 * 3. Comment   : 고객정보 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 3:11:02
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param customerInfo {@link CustomerInfoVO}
	 *   @throws Exception Exception
	 */
	@RequestMapping(value = "insertNewCustomerInfoAction", method = RequestMethod.POST)
	public String insertNewCustomerInfoAction(	Model model, HttpServletRequest request, 
			CustomerInfoVO customerInfo) throws Exception{
		
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		String today = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		customerInfo.setOrgId(sessionUser.getOrgId());
		customerInfo.setUsrId(sessionUser.getUserId());
		customerInfo.setChgrId(sessionUser.getUserId());
		customerInfo.setChgDate(DateUtil.sysdate());
		customerInfo.setRegrId(sessionUser.getUserId());
		customerInfo.setRegDate(DateUtil.sysdate());
		
		CustomerInfoVO custInfo = customerManagementService.insertNewCustomerInfo(customerInfo, today, lng);
		model.addAttribute("custInfo", custInfo);
		
		return URL + "/ajax/customerMng";
	}
	
	
	@RequestMapping(value = "updateCustInfoAction", method = RequestMethod.POST)
	public String updatePymCustInfoAction(	Model model, HttpServletRequest request, 
			CustomerInfoVO customerInfo) throws ServiceException{
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		String today = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String todayYYYYMMDD = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		customerInfo.setChgrId(sessionUser.getUserId());
		customerInfo.setChgDate(DateUtil.sysdate());
		
		
		CustomerInfoVO updatedCustInfo = customerManagementService.updateCustInfo(customerInfo, today,todayYYYYMMDD, lng);
		
		model.addAttribute("custInfo", updatedCustInfo);
		return URL + "/ajax/customerMng";
	}

	@RequestMapping(value = "openCustPymListPopup", method = RequestMethod.POST)
	public String openCustPymListPopup(	Model model, HttpServletRequest request, 
			String soId, String custId) throws ServiceException{
		model.addAttribute("SO_ID", soId);
		model.addAttribute("CUST_ID", custId);
		
		/*
		 *  CM00001	납부방법
			CM00002	납부방법변경사유
			CM00003	종이청구서유형
			CM00004	이메일청구서유형
			CM00005	SMS/MMS청구서유형
			CM00006	은행
			CM00007	카드사
			CM000042 청구주기
		 */
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("pymMthdCodeList", commonDataService.getCommonCodeList("CM00001", lng));
		model.addAttribute("pymChngResnCodeList", commonDataService.getCommonCodeList("CM00002", lng));
		model.addAttribute("paperInvoiceCodeList", commonDataService.getCommonCodeList("CM00003", lng));
		model.addAttribute("emailInvoiceCodeList", commonDataService.getCommonCodeList("CM00004", lng));
		model.addAttribute("smsInvoiceCodeList", commonDataService.getCommonCodeList("CM00005", lng));
		model.addAttribute("bankCodeList", commonDataService.getCommonCodeList("CM00006", lng));
		model.addAttribute("cardCodeList", commonDataService.getCommonCodeList("CM00007", lng));
		model.addAttribute("billCyclCodeList", commonDataService.getCommonCodeList("CM00042", lng));
		
		
		return  URL + "/ajax/custPymListPopup";
	}
}
