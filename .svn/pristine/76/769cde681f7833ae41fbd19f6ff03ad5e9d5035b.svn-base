package com.ntels.ccbs.customer.controller.resource.numberAgeingMng;
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
import com.ntels.ccbs.customer.domain.resource.numberAgeingMng.NumberAgeingMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/customer/resource/numberAgeingMng/numberAgeingMng")
public class NumberAgeingMngController {
	
	private static String URL = "customer/resource/numberAgeingMng/numberAgeingMng";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "ageingTypDef", method = RequestMethod.POST)
	public String ageingTypDef(Model model, NumberAgeingMngVO numberAgeingMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		return URL + "/ageingTypDef";
	}
	
	@RequestMapping(value = "ageingRelease", method = RequestMethod.POST)
	public String ageingRelease(Model model, NumberAgeingMngVO numberAgeingMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		return URL + "/ageingRelease";
	}
}
