package com.ntels.ccbs.customer.controller.resource.resourceRefInfo;
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
import com.ntels.ccbs.customer.domain.resource.resourceRefInfo.GradeMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/customer/resource/resourceRefInfo/resourceRefInfoMng")
public class GradeMngController {
	
	private static String URL = "customer/resource/resourceRefInfo/resourceRefInfoMng";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "gradeMng", method = RequestMethod.POST)
	public String gradeMng(Model model, GradeMngVO gradeMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		return URL + "/gradeMng";
	}
	
	@RequestMapping(value = "gradeMasterMng", method = RequestMethod.POST)
	public String gradeMasterMng(Model model, GradeMngVO gradeMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		return URL + "/gradeMasterMng";
	}
}
