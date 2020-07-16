package com.ntels.ccbs.customer.controller.statistics.orderStatistics;

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

import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.statistics.orderStatistics.OrderStatisticsVO;
import com.ntels.ccbs.customer.service.statistics.orderStatistics.OrderStatisticsService;

@Controller
@RequestMapping(value = "/customer/statistics/orderStatistics")
public class OrderStatisticsController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderStatisticsService orderStatisticsService;
	
	@Autowired
	private CommonDataService commonDataService;

	
	private String thisUrl = "customer/statistics/orderStatistics";
	
	@RequestMapping(value = "orderStatisticsList", method = RequestMethod.POST)
	public String orderStatisticsList(Model model, OrderStatisticsVO orderStatisticsVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");

		
		model.addAttribute("orderStatList", commonDataService.getCommonCodeList("CM00030", lng)); //ORDER_STAT
		model.addAttribute("orderTpList", commonDataService.getCommonCodeList("CM00022", lng)); //ORDER_TP
		
		return thisUrl + "/orderStatisticsList";
	}
	
	@RequestMapping(value = "orderStatisticsListAction", method = RequestMethod.POST)
	public String orderStatisticsListAction(OrderStatisticsVO orderStatisticsVO, Model model, HttpServletRequest request, 
			String searchStatDt,
			String searchEndDt,
			String orderStat,
			String condCustId,
			String orderTp,
			String searchSoId,			
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
		Map<String,Object> orderStatisticsList = orderStatisticsService.orderStatisticsList(searchStatDt, searchEndDt, orderStat, condCustId, orderTp,searchSoId, sidx, sord, page, rows, lng);
	
		model.addAttribute("orderStatisticsList", orderStatisticsList.get("orderStatisticsList"));
		model.addAttribute("totalCount", orderStatisticsList.get("totalCount"));
		model.addAttribute("totalPages", orderStatisticsList.get("totalPages"));
		model.addAttribute("page", orderStatisticsList.get("page"));
		
		return thisUrl + "/ajax/orderStatisticsList";
		
	}
	@RequestMapping(value = "orderStatisticsChart", method = RequestMethod.POST)
	public String orderStatisticsChart(HttpServletRequest request, OrderStatisticsVO orderStatisticsVO, Model model,
			String searchStatDt,
			String searchEndDt,
			String searchSoId) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");	

		String endDate = DateUtil.getDateStringYYYYMMDD(0);
		String startDate = DateUtil.getDateStringYYYYMMDD(-30);
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String soId = sessionUser.getSoId();
				
		if(StringUtils.isEmpty(searchStatDt)){
			orderStatisticsVO.setSearchStatDt(aMonthAgoDate());
		}else{
			orderStatisticsVO.setSearchStatDt(searchStatDt);
		}
		
		if(StringUtils.isEmpty(searchEndDt)){
			orderStatisticsVO.setSearchEndDt(endDate);
		}else{
			orderStatisticsVO.setSearchEndDt(searchEndDt);
		}
		
		if(StringUtils.isEmpty(searchSoId)){
			orderStatisticsVO.setSearchSoId(soId);
		}else{
			orderStatisticsVO.setSearchSoId(searchSoId);
		}	
		
	    Map<String,Object> orderStatisticsChart =orderStatisticsService.orderStatisticsChart(orderStatisticsVO,lng);
	    
	    model.addAttribute("orderStatisticsChart", orderStatisticsChart.get("orderStatisticsChart"));
	    return thisUrl + "/ajax/orderStatisticsList";
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

