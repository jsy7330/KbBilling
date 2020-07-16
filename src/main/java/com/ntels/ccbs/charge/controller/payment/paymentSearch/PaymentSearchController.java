package com.ntels.ccbs.charge.controller.payment.paymentSearch;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ntels.ccbs.charge.domain.payment.paymentSearch.PaymentSearchVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/payment/paymentSearch/paymentSearchMng")
public class PaymentSearchController {
	
	private static String URL = "charge/payment/paymentSearch/paymentSearchMng";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "depositSearch", method = RequestMethod.POST)
	public String depositSearch(Model model, PaymentSearchVO paymentSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("depositTp", commonDataService.getCommonCodeList("BL00069", lng));
		
		return URL + "/depositSearch";
	}

	@RequestMapping(value = "advanceReceivedSearch", method = RequestMethod.POST)
	public String advanceReceivedSearch(Model model, PaymentSearchVO paymentSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("advanceReceivedTp", commonDataService.getCommonCodeList("BL00073", lng));
		
		return URL + "/advanceReceivedSearch";
	}
	
	@RequestMapping(value = "refundSearch", method = RequestMethod.POST)
	public String refundSearch(Model model, PaymentSearchVO paymentSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		return URL + "/refundSearch";
	}
}
