package com.ntels.ccbs.customer.controller.contract.contract;

import java.text.SimpleDateFormat;
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
import com.ntels.ccbs.customer.domain.contract.contract.PaymentDetailVO;
import com.ntels.ccbs.customer.service.contract.contract.ContractConfirmService;
import com.ntels.ccbs.customer.service.contract.contract.OrderManagementService;
import com.ntels.ccbs.customer.service.contract.contract.PaymentDetailService;

@Controller
@RequestMapping(value = "/customer/contract/contract/contractConfirm")
public class ContractConfirmPopupController {
private static String URL = "customer/contract/contract/contractConfirm";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 상세납부 Service
	 */
	@Autowired ContractConfirmService contractConfirmService;
	
	@Autowired PaymentDetailService paymentDetailService;
	
	@Autowired OrderManagementService orderManagementService;
	
	@RequestMapping(value = "openContractConfirm", method = {RequestMethod.POST, RequestMethod.GET})
	public String openInvoiceDetail(Model model
			,HttpServletRequest request
			,String soId
			,String custId
			,String ctrtId
			,String pymAcntId
			) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		CtrtInfoVO ctrtInfo =contractConfirmService.getCtrtInfo(soId, custId, ctrtId, lng, today);
		PaymentDetailVO pymDtlInfo = paymentDetailService.paymentDetailInfo(soId, pymAcntId, today, lng);
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String chgDate = transFormat.format(ctrtInfo.getChgDate());
		model.addAttribute("chgDate", chgDate);
		model.addAttribute("today",today);
		model.addAttribute("ctrtInfo", ctrtInfo);
		model.addAttribute("pymDtlInfo", pymDtlInfo);
		Map<String,Object> contractInformationInUse = contractConfirmService.getCtrtInfo(soId, custId, ctrtId, lng);
		model.addAttribute("basicProdInfo", contractInformationInUse.get("basicProdInfo"));    // 기본계약정보
		model.addAttribute("deviceInfoList", contractInformationInUse.get("deviceInfoList"));    // 사용중인 장비상품정보
		model.addAttribute("addInfoList", contractInformationInUse.get("addInfoList"));    // 사용중인 부가서비스정보
		model.addAttribute("chargeConfList", contractInformationInUse.get("chargeConfList"));    // 월정액 협정가 정보
		model.addAttribute("oneTimeFeeList", contractInformationInUse.get("oneTimeFeeList"));    // 일회성 협정가 정보
		return URL + "/opopup/contractConfirm";
	}

}