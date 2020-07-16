package com.ntels.ccbs.charge.controller.bond.defaultSearch;

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
import com.ntels.ccbs.charge.domain.bond.defaultSearch.DefaultSearchVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/bond/defaultSearch/defaultSearchMng")
public class DefaultSearchController {
	
	private static String URL = "charge/bond/defaultSearch/defaultSearchMng";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "defaultDepositSearch", method = RequestMethod.POST)
	public String defaultDepositSearch(Model model, DefaultSearchVO defaultSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");

		return URL + "/defaultDepositSearch";
	}

	@RequestMapping(value = "defaultSearch", method = RequestMethod.POST)
	public String defaultSearch(Model model, DefaultSearchVO defaultSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
	
		return URL + "/defaultSearch";
	}

}
