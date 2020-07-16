package com.ntels.ccbs.charge.controller.bond.bondAction;

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
import com.ntels.ccbs.charge.domain.bond.bondAction.BondActionVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/bond/bondAction/bondActionMng")
public class BondActionController {
	
	private static String URL = "charge/bond/bondAction/bondActionMng";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "bondSearch", method = RequestMethod.POST)
	public String bondSearch(Model model, BondActionVO bondActionVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("searchTp", commonDataService.getCommonCodeList("BL00108", lng));
		return URL + "/bondSearch";
	}

	@RequestMapping(value = "bondMng", method = RequestMethod.POST)
	public String bondMng(Model model, BondActionVO bondActionVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("searchTp", commonDataService.getCommonCodeList("BL00108", lng));
		model.addAttribute("ctrtStat", commonDataService.getCommonCodeList("CM00023", lng));		
		return URL + "/bondMng";
	}

}
