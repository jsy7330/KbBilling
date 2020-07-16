package com.ntels.ccbs.charge.controller.charge.billing;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.charge.charge.RegularChargeJobVO;

@Controller
@RequestMapping(value = "/charge/billing/billing/billingWork")
public class BillingWorkController {

	private static String URL = "charge/billing/billing/billingWork";
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "fixedTimeBillingWork", method = RequestMethod.POST)
	public String fixedTimeBillingWork(Model model, RegularChargeJobVO regularChargeJobVO, HttpServletRequest request) throws Exception {

		return URL + "/fixedTimeBillingWork";
	}
	
}
