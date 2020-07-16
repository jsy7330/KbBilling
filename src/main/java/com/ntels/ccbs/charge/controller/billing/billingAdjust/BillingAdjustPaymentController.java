package com.ntels.ccbs.charge.controller.billing.billingAdjust;

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
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustPaymentVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/billing/billingAdjust/billingAdjustPayment")
public class BillingAdjustPaymentController {
	
	private static String URL = "charge/billing/billingAdjust/billingAdjustPayment";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "billingAdjustPayment", method = RequestMethod.POST)
	public String billingAdjustPayment(Model model, BillingAdjustPaymentVO billingAdjustPaymentVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		return URL + "/billingAdjustPayment";
	}



}
