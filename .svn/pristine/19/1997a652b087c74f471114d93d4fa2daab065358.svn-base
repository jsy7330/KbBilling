package com.ntels.ccbs.system.controller.common.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.common.CustomerSearchPopupService;


@Controller
@RequestMapping(value = "/system/common/common/customerSearch")
public class CustomerSearchController {
	/**
	 * 고객조회 메인 URL
	 */
	private static String URL = "system/common/common/customerSearch";
	
	@Autowired
	private CustomerSearchPopupService customerSearchPopupService;
	
	@RequestMapping(value = "customerSearchPopup", method = RequestMethod.POST)
	public String customerSearchPopup(Model model,HttpServletRequest request
			,String inputSoId
			,String inputCustNm
			,String inputIsUnmaskYn
			,String outputSoId
			,String outputCustNm
			,String outputCustId
			) {
		
		model.addAttribute("INPUT_SO_ID", (StringUtils.isEmpty(inputSoId) == true ? "SEL" : inputSoId));
		model.addAttribute("INPUT_CUST_NM", (StringUtils.isEmpty(inputCustNm) == true ? "" : inputCustNm));
		model.addAttribute("INPUT_IS_UNMASK_YN", (StringUtils.isEmpty(inputIsUnmaskYn) == true ? "" : inputIsUnmaskYn));
		model.addAttribute("OUTPUT_SO_ID", (StringUtils.isEmpty(outputSoId) == true ? "" : outputSoId));
		model.addAttribute("OUTPUT_CUST_NM", (StringUtils.isEmpty(outputCustNm) == true ? "" : outputCustNm));
		model.addAttribute("OUTPUT_CUST_ID", (StringUtils.isEmpty(outputCustId) == true ? "" : outputCustId));
		
		return  URL+"/ajax/customerSearchPopup";
		
		
		
	}
	
	@RequestMapping(value = "getCustomerListAction", method = RequestMethod.POST)
	public String getCustomerListAction(Model model,HttpServletRequest request
			,String soId
			,String custNm
			,String custId
			,String ctrtId
			,String svcTelNo
			,String corpRegNo
			,String bizNo
	        ,String sidx
	        ,String sord
	        ,int page
	        ,int rows) {
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> custInfoList = customerSearchPopupService.getCustInfoList(soId, custNm, custId, ctrtId, svcTelNo, corpRegNo, bizNo, sessionUser.getSoAuthList(), today, lng, sidx, sord, page, rows);
		
		model.addAttribute("custInfoList", custInfoList.get("custInfoList"));
		model.addAttribute("totalCount", custInfoList.get("totalCount"));
		model.addAttribute("totalPages", custInfoList.get("totalPages"));
		model.addAttribute("page", custInfoList.get("page"));
		
		
		return  URL+"/ajax/customerSearchPopup";
		
	}

	
}