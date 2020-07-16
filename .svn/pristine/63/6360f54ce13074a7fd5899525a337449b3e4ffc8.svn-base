package com.ntels.ccbs.customer.controller.service.serviceMgt;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.service.serviceMgt.ServiceMgtVO;

@Controller
@RequestMapping(value = "/customer/service/serviceMgt")
public class ServiceMgtController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private CommonDataService commonDataService;

	
	private String thisUrl = "customer/service/serviceMgt";
	
	@RequestMapping(value = "serviceMgtList", method = RequestMethod.POST)
	public String serviceMgtList(Model model, ServiceMgtVO ServiceMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");

		
		model.addAttribute("orderStatList", commonDataService.getCommonCodeList("CM00030", lng)); //ORDER_STAT
		model.addAttribute("orderTpList", commonDataService.getCommonCodeList("CM00022", lng)); //ORDER_TP
		
		return thisUrl + "/serviceMgtList";
	}

}

