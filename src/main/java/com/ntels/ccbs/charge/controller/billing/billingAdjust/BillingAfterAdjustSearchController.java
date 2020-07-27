package com.ntels.ccbs.charge.controller.billing.billingAdjust;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustSearchVO;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingAfterAdjustSearchService;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/billing/billingAdjust/billingAfterAdjustSearch")
public class BillingAfterAdjustSearchController {
	
	private static String URL = "charge/billing/billingAdjust/billingAfterAdjustSearch";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private BillingAfterAdjustSearchService billingAfterAdjustSearchService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "billingAfterAdjustSearch", method = RequestMethod.POST)
	public String billingAfterAdjustSearch(Model model, BillingAfterAdjustSearchVO billingAfterAdjustSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("dcsnProcStatList", commonDataService.getCommonCodeList("BL00055", lng));
		
		return URL + "/billingAfterAdjustSearch";
	}
	
	@RequestMapping(value = "getBillChargeAdjustReportList", method = RequestMethod.POST)
	public String getBillChargeAdjustReportList(Model model, BillingAfterAdjustSearchVO billingAfterAdjustSearchVO, HttpServletRequest request,
			String sidx, 
			String sord, 
			int page, 
			int rows) {
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		Map<String, Object> getChargeAdjustReportInfo = billingAfterAdjustSearchService.getBillChargeAdjustReportList(billingAfterAdjustSearchVO, sidx, sord, page, rows, lng);
		
		model.addAttribute("billChargeAdjustReportList", getChargeAdjustReportInfo.get("billChargeAdjustReportList"));
		model.addAttribute("totalCount", getChargeAdjustReportInfo.get("totalCount"));
		model.addAttribute("totalPages", getChargeAdjustReportInfo.get("totalPages"));
		model.addAttribute("page", getChargeAdjustReportInfo.get("page"));
		
		return URL + "/billingAfterAdjustSearch";
	}



}
