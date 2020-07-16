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

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.customer.customer.CustomerDocumentVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/customer/customer/customer/customerDocument")
public class CustomerDocumentController {
	
	private static String URL = "customer/customer/customer/customerDocument";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "customerDocumentMgtList", method = RequestMethod.POST)
	public String customerDocumentMgtList(Model model, CustomerDocumentVO customerDocumentVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");

		
		return URL + "/customerDocumentMgtList";
	}
	
	@RequestMapping(value = "customerDocumentSearch", method = RequestMethod.POST)
	public String customerDocumentSearch(Model model, CustomerDocumentVO customerDocumentVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");

		
		return URL + "/customerDocumentSearch";
	}	
}
