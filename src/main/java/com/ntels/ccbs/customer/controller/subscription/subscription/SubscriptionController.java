package com.ntels.ccbs.customer.controller.subscription.subscription;

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
import com.ntels.ccbs.customer.domain.statistics.orderStatistics.OrderStatisticsVO;
import com.ntels.ccbs.customer.domain.subscription.subscription.SubscriptionVO;
import com.ntels.ccbs.customer.service.subscription.subscription.SubscriptionService;

@Controller
@RequestMapping(value = "/customer/subscription/subscription")
public class SubscriptionController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private CommonDataService commonDataService;

	
	private String thisUrl = "customer/subscription/subscription";
	
	@RequestMapping(value = "subscriptionList", method = RequestMethod.POST)
	public String subscriptionList(Model model, SubscriptionVO subscriptionVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");		
		String wrkDftWrker = sessionUser.getUserId();
		
		model.addAttribute("orderStatList", commonDataService.getCommonCodeList("CM00030", lng)); //ORDER_STAT
		model.addAttribute("orderTpList", commonDataService.getCommonCodeList("CM00022", lng)); //ORDER_TP
		model.addAttribute("workStatList", commonDataService.getCommonCodeList("CMWO003", lng)); //ORDER_TP
		model.addAttribute("wrkDftWrker", wrkDftWrker); //ORDER_TP
		
		return thisUrl + "/subscriptionList";
	}
	
	@RequestMapping(value = "subscriptionListAction", method = RequestMethod.POST)
	public String subscriptionListAction(SubscriptionVO subscriptionVO, Model model, HttpServletRequest request, 
			String searchStatDt,
			String searchEndDt,
			String orderStat,
			String condCustId,
			String orderTp,
			String searchSoId,	
			String wrkDftWrker,				
			String wrkStat,					
			String sidx,
			String sord,
			int page,
			int rows) {
		
		String endDate = DateUtil.getDateStringYYYYMMDD(0);
		String startDate = aMonthAgoDate();
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String soId = sessionUser.getSoId();
		
		if(StringUtils.isEmpty(searchStatDt)){
			searchStatDt =startDate;
		}	
		if(StringUtils.isEmpty(searchEndDt)){
			searchEndDt =endDate;
		}			
		if(StringUtils.isEmpty(searchSoId)){
			searchSoId =soId;
		}	

		if(!StringUtils.isEmpty(orderStat)){
			if(orderStat.equals("SEL")){
				orderStat = "";
			}
		}
		if(!StringUtils.isEmpty(orderTp)){
			if(orderTp.equals("SEL")){
				orderTp = "";
			}
		}
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> subscriptionList = subscriptionService.subscriptionList(searchStatDt, searchEndDt, orderStat, condCustId, orderTp,searchSoId, wrkDftWrker, wrkStat, sidx, sord, page, rows, lng);

		model.addAttribute("subscriptionList", subscriptionList.get("subscriptionList"));
		model.addAttribute("totalCount", subscriptionList.get("totalCount"));
		model.addAttribute("totalPages", subscriptionList.get("totalPages"));
		model.addAttribute("page", subscriptionList.get("page"));
		
		return thisUrl + "/ajax/subscriptionList";
		
	}

	@RequestMapping(value = "subscriptionAction", method = RequestMethod.POST)
	public String subscriptionAction(SubscriptionVO subscriptionVO, Model model, HttpServletRequest request, 
			String searchStatDt,
			String searchEndDt,
			String searchSoId,	
			String wrkDftWrker) {
		
		String endDate = DateUtil.getDateStringYYYYMMDD(0);
		String startDate = aMonthAgoDate();
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String soId = sessionUser.getSoId();
		
		if(StringUtils.isEmpty(searchStatDt)){
			searchStatDt =startDate;
		}	
		if(StringUtils.isEmpty(searchEndDt)){
			searchEndDt =endDate;
		}			
		if(StringUtils.isEmpty(searchSoId)){
			searchSoId =soId;
		}	

		if(StringUtils.isEmpty(wrkDftWrker)){
			wrkDftWrker =sessionUser.getUserId();
		}		
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> subscription = subscriptionService.subscription(searchStatDt, searchEndDt, searchSoId, wrkDftWrker, lng);

		model.addAttribute("subscription", subscription.get("subscription"));

		
		return thisUrl + "/ajax/subscriptionList";
		
	}
	
	@RequestMapping(value = "subscriptionDetailPopup", method = RequestMethod.POST)
	public String subscriptionDetailPopup(Model model, SubscriptionVO subscriptionVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");		
		String wrkDftWrker = sessionUser.getUserId();
		
		return thisUrl + "/ajax/subscriptionDetailPopup";
	}
	
	public String aMonthAgoDate() {		
		 DecimalFormat df = new DecimalFormat("00");
		 Calendar currentCalendar = Calendar.getInstance();
		 currentCalendar.add(currentCalendar.DATE, -24);
		 String strYear31   = Integer.toString(currentCalendar.get(Calendar.YEAR));
		 String strMonth31  = df.format(currentCalendar.get(Calendar.MONTH) + 1);
		 String strDay31   = df.format(currentCalendar.get(Calendar.DATE));
		 String strDate31 = strYear31 + strMonth31 + strDay31;		
		 return strDate31;
	}	
}

