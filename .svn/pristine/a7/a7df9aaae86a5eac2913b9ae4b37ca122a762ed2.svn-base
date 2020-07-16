package com.ntels.ccbs.customer.controller.customer.payment;

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
import com.ntels.ccbs.customer.domain.customer.payment.PaymentMethodVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/customer/customer/payment/paymentMethod")
public class PaymentMethodController {
	
	private static String URL = "customer/customer/payment/paymentMethod";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "paymentMethod", method = RequestMethod.POST)
	public String paymentMethod(Model model, PaymentMethodVO paymentMethodVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("pymMthdCodeList", commonDataService.getCommonCodeList("CM00001", lng));
		return URL + "/paymentMethod";
	}

}
