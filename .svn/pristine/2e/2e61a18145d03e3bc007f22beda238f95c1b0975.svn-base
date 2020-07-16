package com.ntels.ccbs.customer.controller.resource.numberReserveMng;
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
import com.ntels.ccbs.customer.domain.resource.numberReserveMng.NumberReserveMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/customer/resource/numberReserveMng/numberReserveMng")
public class NumberReserveMngController {
	
	private static String URL = "customer/resource/numberReserveMng/numberReserveMng";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "reserveTypDesc", method = RequestMethod.POST)
	public String reserveTypDesc(Model model, NumberReserveMngVO numberReserveMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		return URL + "/reserveTypDesc";
	}
	
	@RequestMapping(value = "reserveRequest", method = RequestMethod.POST)
	public String reserveRequest(Model model, NumberReserveMngVO numberReserveMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		return URL + "/reserveRequest";
	}
}
