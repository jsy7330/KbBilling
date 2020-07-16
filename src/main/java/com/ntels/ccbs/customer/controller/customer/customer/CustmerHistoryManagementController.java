package com.ntels.ccbs.customer.controller.customer.customer;

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
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoHistVO;
import com.ntels.ccbs.customer.service.customer.customer.CustomerHistoryManagementService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/customer/customer/customer/customerHistoryManagement")
public class CustmerHistoryManagementController {
	
	private static String URL = "customer/customer/customer/customerHistoryManagement";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private CustomerHistoryManagementService customerHistoryManagementService;
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "customerChngHist", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		
		/*
			CM00009	고객유형
			CM00010	고객신분
			CM00011	세금여부
			CM00012	고객정보변경사유
			CM00013	업태
			CM00050 주
		 */
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("custTpCdList", commonDataService.getCommonCodeList("CM00009", lng));
		model.addAttribute("custClCdList", commonDataService.getCommonCodeList("CM00010", lng));
		model.addAttribute("taxTpCdList", commonDataService.getCommonCodeList("CM00011", lng));
		model.addAttribute("chgResnCdList", commonDataService.getCommonCodeList("CM00012", lng));
		model.addAttribute("busiCndtCdList", commonDataService.getCommonCodeList("CM00013", lng));
		model.addAttribute("stateCdList", commonDataService.getCommonCodeList("CM00050", lng));
		
		return URL + "/customerChngHist";
	}
	
	
	@RequestMapping(value = "openCustChngHistPopup", method = RequestMethod.POST)
	public String openCustChngHistPopup(	Model model, HttpServletRequest request, 
			String soId, String custId) throws ServiceException{
		String sdate = DateUtil.getDateStringYYYYMMDD(-7);
		//String edate = DateUtil.getDateStringYYYYMMDD(0);
		
		model.addAttribute("SO_ID", soId);
		model.addAttribute("CUST_ID", custId);
		//model.addAttribute("sdate", sdate);
		//model.addAttribute("edate", edate);
		return  URL + "/ajax/custChngHistPopup";
	}
	
	@RequestMapping(value = "getCustChngHistAction", method = RequestMethod.POST)
	public String getPymAcntChngHistListAction(	Model model, HttpServletRequest request, 
			String soId, 
			String custId,
			String sidx,
			String sord,
			String sdate,
			String edate,
			int page,
			int rows) {
		if(StringUtils.isEmpty(custId)){
			return URL + "/ajax/getCustChngHistAction";
		}
		CustomerInfoHistVO custInfoHist = new CustomerInfoHistVO();
		custInfoHist.setSdate(sdate);
		custInfoHist.setEdate(edate);
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> custChngHistInfo = customerHistoryManagementService.getCustChngHistList(soId, custId, today, sidx, sord, page, rows, lng, custInfoHist);
		model.addAttribute("custChngHistList", custChngHistInfo.get("custChngHistList"));
		model.addAttribute("totalCount", custChngHistInfo.get("totalCount"));
		model.addAttribute("totalPages", custChngHistInfo.get("totalPages"));
		model.addAttribute("page", custChngHistInfo.get("page"));
		
		return URL + "/ajax/getCustChngHistAction";
	}
	
}
