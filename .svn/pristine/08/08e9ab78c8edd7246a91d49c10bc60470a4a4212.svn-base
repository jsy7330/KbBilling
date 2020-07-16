package com.ntels.ccbs.charge.controller.payment.refund;

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
import com.ntels.ccbs.charge.domain.payment.refund.RefundMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/payment/refund/refundMng")
public class RefundMngController {
	
	private static String URL = "charge/payment/refund/refundMng";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "refundRequest", method = RequestMethod.POST)
	public String refundRequest(Model model, RefundMngVO refundMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		return URL + "/refundRequest";
	}

	@RequestMapping(value = "refundRequestSearch", method = RequestMethod.POST)
	public String refundRequestSearch(Model model, RefundMngVO refundMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		return URL + "/refundRequestSearch";
	}
	
	@RequestMapping(value = "refundApprovalCancel", method = RequestMethod.POST)
	public String refundApprovalCancel(Model model, RefundMngVO refundMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");

		return URL + "/refundApprovalCancel";
	}

}
