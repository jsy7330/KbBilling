package com.ntels.ccbs.customer.controller.customer.payment;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountInfoVO;
import com.ntels.ccbs.customer.service.customer.payment.PaymentAccountManagementService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/customer/customer/payment/paymentManagement")
public class PaymentManagementController {
	
	private static String URL = "customer/customer/payment/paymentManagement";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private PaymentAccountManagementService paymentAccountManagementService;
	
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@RequestMapping(value = "paymentMng", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
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
		model.addAttribute("stateCdList", commonDataService.getCommonCodeList("CM00050", lng));
		return URL + "/paymentMng";
	}
	
	@RequestMapping(value = "getPymCustInfoListAction", method = RequestMethod.POST)
	public String getPymCustInfListAction(	Model model, HttpServletRequest request, String soId, String custId, String custNm) {
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		List<Map<String,Object>> custList = paymentAccountManagementService.getCustInfoList(soId, custNm, custId, sessionUser.getSoAuthList());
		
		model.addAttribute("custList", custList);
		model.addAttribute("custListCnt", custList.size());
		
		return URL + "/ajax/paymentMng";
	}
	
	@RequestMapping(value = "getPymAcntInfoListAction", method = RequestMethod.POST)
	public String getPymAcntInfoListAction(	Model model, HttpServletRequest request, 
			String soId, 
			String custId,
			String sidx,
			String sord,
			int page,
			int rows) {
		if(StringUtils.isEmpty(custId)){
			return URL + "/ajax/paymentMng";
		}
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> pymAcntInfo = paymentAccountManagementService.getPymAcntInfoList(soId, custId, today, sidx,sord, page, rows, lng);
		
		model.addAttribute("pymAcntInfoList", pymAcntInfo.get("pymAcntList"));
		model.addAttribute("totalCount", pymAcntInfo.get("totalCount"));
		model.addAttribute("totalPages", pymAcntInfo.get("totalPages"));
		model.addAttribute("page", pymAcntInfo.get("page"));
		
		return URL + "/ajax/paymentMng";
	}
	
	
	@RequestMapping(value = "insertPymCustInfoAction", method = RequestMethod.POST)
	public String insertPymCustInfoAction(	Model model, HttpServletRequest request, 
			PaymentAccountInfoVO paymentAccountInfo) throws Exception{
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		String today = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		paymentAccountInfo.setOrgId(sessionUser.getOrgId());
		paymentAccountInfo.setUsrId(sessionUser.getUserId());
		paymentAccountInfo.setChgrId(sessionUser.getUserId());
		paymentAccountInfo.setChgDate(DateUtil.sysdate());
		paymentAccountInfo.setRegrId(sessionUser.getUserId());
		paymentAccountInfo.setRegDate(DateUtil.sysdate());
		
		paymentAccountManagementService.insertPymAcntInfo(paymentAccountInfo, today, lng);
		
		return URL + "/ajax/paymentMng";
	}
	
	
	@RequestMapping(value = "updatePymCustInfoAction", method = RequestMethod.POST)
	public String updatePymCustInfoAction(	Model model, HttpServletRequest request, 
			PaymentAccountInfoVO paymentAccountInfo) throws ServiceException{
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		String today = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String todayYYYYMMDD = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		paymentAccountInfo.setOrgId(sessionUser.getOrgId());
		paymentAccountInfo.setUsrId(sessionUser.getUserId());
		paymentAccountInfo.setChgDate(DateUtil.sysdate());
		
		
		paymentAccountManagementService.updatePymCustInfo(paymentAccountInfo, today,todayYYYYMMDD, lng);
		
		return URL + "/ajax/paymentMng";
	}
	
	@RequestMapping(value = "openPymChngHistPopup", method = RequestMethod.POST)
	public String openPymChngHistPopup(	Model model, HttpServletRequest request, 
			String soId, String pymAcntId) throws ServiceException{
		model.addAttribute("SO_ID", soId);
		model.addAttribute("PYM_ACNT_ID", pymAcntId);
		
		return  URL + "/ajax/paymentChngHistPopup";
	}
	
	@RequestMapping(value = "openVirInfoPopup", method = RequestMethod.POST)
	public String openVirInfoPopup(	Model model, HttpServletRequest request, 
			String soId, String pymAcntId) throws ServiceException{
		model.addAttribute("SO_ID", soId);
		model.addAttribute("PYM_ACNT_ID", pymAcntId);
		
		return  URL + "/ajax/virAcntChngPopup";
	}
	
	@RequestMapping(value = "getPymAcntChngHistListAction", method = RequestMethod.POST)
	public String getPymAcntChngHistListAction(	Model model, HttpServletRequest request, 
			String soId, 
			String pymAcntId,
			String sidx,
			String sord,
			int page,
			int rows) {
		if(StringUtils.isEmpty(pymAcntId)){
			return URL + "/ajax/paymentChngHistPopup";
		}
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> pymAcntInfo = paymentAccountManagementService.getPymAcntChngHistList(soId, pymAcntId, today, sidx, sord, page, rows, lng);
		model.addAttribute("pymAcntChngHistList", pymAcntInfo.get("pymAcntChngHistList"));
		model.addAttribute("totalCount", pymAcntInfo.get("totalCount"));
		model.addAttribute("totalPages", pymAcntInfo.get("totalPages"));
		model.addAttribute("page", pymAcntInfo.get("page"));
		
		return URL + "/ajax/paymentChngHistPopup";
	}
	
	
	@RequestMapping(value = "getVirAcntListAction", method = RequestMethod.POST)
	public String getVirAcntListAction(	Model model, HttpServletRequest request, 
			String soId, 
			String pymAcntId,
			String sidx,
			String sord,
			int page,
			int rows) {
		if(StringUtils.isEmpty(pymAcntId)){
			return URL + "/ajax/virAcntChngPopup";
		}
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> virAcntInfo = paymentAccountManagementService.getVirAcntList(soId, pymAcntId, today, sidx, sord, page, rows, lng);
		model.addAttribute("virAcntList", virAcntInfo.get("virAcntList"));
		model.addAttribute("totalCount", virAcntInfo.get("totalCount"));
		model.addAttribute("totalPages", virAcntInfo.get("totalPages"));
		model.addAttribute("page", virAcntInfo.get("page"));
		
		return URL + "/ajax/virAcntChngPopup";
	}
	
	@RequestMapping(value = "updateVirAcntAction", method = RequestMethod.POST)
	public String updateVirAcntAction(	Model model, HttpServletRequest request, 
			String soId, 
			String pymAcntId,
			String vrBnkCd,
			String vrAcntNo) throws ServiceException{
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		String today = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		paymentAccountManagementService.processChngVirAcnt(soId, pymAcntId, vrBnkCd, vrAcntNo, today, lng, sessionUser.getUserId());
		
		return URL + "/ajax/virAcntChngPopup";
	}
	
}
