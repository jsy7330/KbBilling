package com.ntels.ccbs.charge.controller.billing.billingAdjust;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustApplyVO;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingAfterAdjustApplyService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/billing/billingAdjust/billingAfterAdjustApply")
public class BillingAfterAdjustApplyController {
	
	private static String URL = "charge/billing/billingAdjust/billingAfterAdjustApply";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private BillingAfterAdjustApplyService billingAfterAdjustApplyService;
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value = "billingAfterAdjustApply", method = RequestMethod.POST)
	public String billingAfterAdjustApply(BillingAfterAdjustApplyVO billingAfterAdjustApplyVO, Model model, HttpServletRequest request) throws Exception {		
		return URL + "/billingAfterAdjustApply";
	}
	
	@RequestMapping(value = "afterAdjApplyTgtList", method = RequestMethod.POST)
	public String afterAdjApplyTgtList(BillingAfterAdjustApplyVO billingAfterAdjustApply, Model model, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		billingAfterAdjustApply.setLngTyp(lng);
		
		System.out.println(">> afterAdjApplyTgtList << ");
		System.out.println("## SO_ID   : " +billingAfterAdjustApply.getSoId());
		System.out.println("## PYM_ACNT_ID : " +billingAfterAdjustApply.getPymAcntId());
		System.out.println("## START_DT : " +billingAfterAdjustApply.getStartDt());
		System.out.println("## END_DT : " +billingAfterAdjustApply.getEndDt());
		System.out.println("## BILL_APLY_YN : " +billingAfterAdjustApply.getBillAplyYn());
		
		List<BillingAfterAdjustApplyVO> afterAdjTgtList = new ArrayList<BillingAfterAdjustApplyVO>();
		
		afterAdjTgtList = billingAfterAdjustApplyService.getAfterAdjTgtList(billingAfterAdjustApply);
		
		model.addAttribute("afterAdjTgtList", afterAdjTgtList);	// 목록리스트
		
		return URL + "/billingAfterAdjust";
		
	}
}
