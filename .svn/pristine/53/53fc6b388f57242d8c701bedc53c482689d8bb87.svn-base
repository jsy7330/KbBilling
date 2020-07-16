package com.ntels.ccbs.customer.controller.contract.contract;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.CtrtInfoVO;
import com.ntels.ccbs.customer.service.contract.contract.ContractManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderManagementService;


@Controller
@RequestMapping(value = "/customer/contract/contract/contractDetail")
public class ContractDetailController {
	
	private static String URL = "customer/contract/contract/contractDetail";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderManagementService orderManagementService;
	
	@Autowired
	private ContractManagementService contractManagementService;
	
	@RequestMapping(value = "openContractDetail", method = RequestMethod.POST)
	public String openContractDetail(Model model
			,HttpServletRequest request
			,String soId
			,String custId
			,String ctrtId
			) 
	{
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		CtrtInfoVO ctrtInfo = contractManagementService.getCtrtInfo(soId, custId, ctrtId, lng, today);
		
		model.addAttribute("ctrtInfo", ctrtInfo);
		
		logger.debug("SO_ID : {0}",soId );
		logger.debug("CUST_ID : {0}",custId );
		logger.debug("CTRT_ID: {0}",ctrtId );
		return URL + "/ajax/contractDetailPopup";
	}
	
	@RequestMapping(value = "getCtrtInfoAction", method = RequestMethod.POST)
	public String getCtrtInfoAction(Model model, HttpServletRequest request, String soId, String custId, String ctrtId) {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		Map<String,Object> contractInformationInUse = orderManagementService.getCtrtInfo(soId, custId, ctrtId, lng);
		model.addAttribute("deviceProdList", contractInformationInUse.get("deviceInfoList"));    // 사용중인 장비상품정보
		model.addAttribute("addProdList", contractInformationInUse.get("addInfoList"));    // 사용중인 부가서비스정보
		
		return URL + "/contractDetailPopup";
	}

}
