package com.ntels.ccbs.customer.controller.contract.receipt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.receipt.ReceiptInfoVO;
import com.ntels.ccbs.customer.service.contract.receipt.ReceiptInfoService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/customer/contract/receipt/receiptInfo")
public class ReceiptInfoController {
	
	private static String URL = "customer/contract/receipt/receiptInfo";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ReceiptInfoService receiptInfoService;
	
	@RequestMapping(value = "receiptInfo", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("orderTpCodeList", commonDataService.getCommonCodeList("CM00022", lng));  //오더유형
		model.addAttribute("orderStatusCodeList", commonDataService.getCommonCodeList("CM00030", lng));  //오더상태

		return URL + "/receiptInfo";
	}
	
	@RequestMapping(value = "getReceiptInfoListAction", method = RequestMethod.POST)
	public String getReceiptInfoListAction(Model model, HttpServletRequest request,
			String condSdate,
			String condEdate,
			String condCustId,
			String condRcptUsrNm,
			String condOrderTpSel,
			String condOrderStatusSel,
			String condCtrtId,
			ReceiptInfoVO rcptInfo) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		rcptInfo.setCondSdate(condSdate);
		rcptInfo.setCondEdate(condEdate);
		rcptInfo.setCondCustId(condCustId);
		rcptInfo.setCondRcptUsrNm(condRcptUsrNm);
		rcptInfo.setCondOrderTpSel(condOrderTpSel);
		rcptInfo.setCondOrderStatusSel(condOrderStatusSel);
		rcptInfo.setCondCtrtId(condCtrtId);
		
		List<ReceiptInfoVO> recieptInfoList = receiptInfoService.getReceiptInfoList(rcptInfo, lng,today);
		
		model.addAttribute("recieptInfoList", recieptInfoList);
		
		return URL + "/ajax/receiptInfo";
	}
	
	@RequestMapping(value = "receiptInfoInquiryPopup", method = RequestMethod.POST)
	public String receiptInfoInquiryPopup(Model model,HttpServletRequest request
			,String soId
			,String ctrtId
			,String orderStat
			) {
		model.addAttribute("soId", soId);
		model.addAttribute("ctrtId", ctrtId);
		model.addAttribute("orderStat", orderStat);
		/*model.addAttribute("INPUT_SO_ID", (StringUtils.isEmpty(inputSoId) == true ? "SEL" : inputSoId));
		model.addAttribute("INPUT_CUST_NM", (StringUtils.isEmpty(inputCustNm) == true ? "" : inputCustNm));
		model.addAttribute("INPUT_IS_UNMASK_YN", (StringUtils.isEmpty(inputIsUnmaskYn) == true ? "" : inputIsUnmaskYn));
		model.addAttribute("OUTPUT_SO_ID", (StringUtils.isEmpty(outputSoId) == true ? "" : outputSoId));
		model.addAttribute("OUTPUT_CUST_NM", (StringUtils.isEmpty(outputCustNm) == true ? "" : outputCustNm));
		model.addAttribute("OUTPUT_CUST_ID", (StringUtils.isEmpty(outputCustId) == true ? "" : outputCustId));*/
		
		return  URL+"/ajax/receiptInfoInquiryPopup";
		
	}
	
	@RequestMapping(value = "getMenuInfo", method = RequestMethod.POST)
	public String getMenuInfo(Model model, HttpServletRequest request,
			String menuNo
		   ,String condCustSoId
		   ,String condCustNm
		   ,String condCustId
		   ,String condCtrtId
		   ,String condOrderId
		   ) {	
		ReceiptInfoVO menuInfo = receiptInfoService.getMenuNo(menuNo);
		
		model.addAttribute("menuInfo", menuInfo);
		
		HttpSession session = request.getSession();
		session.setAttribute("condCustSoId", condCustSoId);
		session.setAttribute("condCustNm", condCustNm);
		session.setAttribute("condCustId", condCustId);
		session.setAttribute("condCtrtId", condCtrtId);
		session.setAttribute("condOrderId", condOrderId);
		
		return URL + "/ajax/receiptInfo";
	}
	
}