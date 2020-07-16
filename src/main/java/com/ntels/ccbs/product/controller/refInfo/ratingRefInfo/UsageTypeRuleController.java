package com.ntels.ccbs.product.controller.refInfo.ratingRefInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeRule;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.UsageTypeRuleService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/usageTypeRule")
public class UsageTypeRuleController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UsageTypeRuleService usageTypeRuleService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/usageTypeRule";

    private @Value("${paging.per_page}") Integer perPage;
    

	@RequestMapping(value = "usageTypeRuleList", method = RequestMethod.POST)
	public String list(
			Model model
		  , UsageTypeRule usageTypeRule
		  , HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");

		model.addAttribute("lngTyp", lngTyp);
		model.addAttribute("dataNmList", usageTypeRuleService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeRuleService.getSeqNoList());
		model.addAttribute("dataNmList", usageTypeRuleService.getDataNmList());
		return thisUrl + "/usageTypeRuleList";
	}
	

	@RequestMapping(value = "usageTypeRuleListAction", method = RequestMethod.POST)
	public void attributeListAction(
			UsageTypeRule usageTypeRule
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
        List<UsageTypeRule> list = new ArrayList<UsageTypeRule>();
        
        list = usageTypeRuleService.getUsageTypeRuleList(usageTypeRule, page, perPage);
		
        model.addAttribute("lngTyp", lngTyp);
        model.addAttribute("dataNmList", usageTypeRuleService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeRuleService.getSeqNoList());
        model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}

	@RequestMapping(value = "usageTypeRuleInsertPopUp", method = RequestMethod.POST)
	public String usageTypeRuleInsertPopUp(
			UsageTypeRule usageTypeRule,
			Model model,
			HttpServletRequest request) throws Exception {
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("dataNmList", usageTypeRuleService.getDataNmList());
		model.addAttribute("lngTyp", lngTyp);
		
		return thisUrl + "/ajax/usageTypeRuleInsertPopUp";
	}

	@RequestMapping(value = "usageTypeRuleInsertAction", method = RequestMethod.POST)
	public String usageTypeRuleInsertAction(
			UsageTypeRule usageTypeRule, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		usageTypeRule.setInsertEffDt(usageTypeRule.getInsertEffDt().replace("-", "") + "000000");
		usageTypeRule.setInsertExpDt("99991231235959");
		
		usageTypeRule.setUserId(session.getUserId());
		usageTypeRule.setSysToDate(DateUtil.sysdate());
		model.addAttribute("dataNmList", usageTypeRuleService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeRuleService.getSeqNoList());
        model.addAttribute("lngTyp", lngTyp);
        
        if ( usageTypeRuleService.getUsageTypeRuleListCount(usageTypeRule) == 0 ) {
			if( usageTypeRuleService.addUsageTypeRule(usageTypeRule) > 0 ) {		
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			} else {		
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}	
			return thisUrl + "/usageTypeRuleList";
        }
        
    	model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00049"));
	
		return thisUrl + "/usageTypeRuleList";
	}	
	
	@RequestMapping(value = "usageTypeRuleUpdatePopUp", method = RequestMethod.POST)
	public String usageTypeUpdatePopUp(
			UsageTypeRule usageTypeRule,
			Model model,
			HttpServletRequest request) throws Exception {
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("dataNmList", usageTypeRuleService.getDataNmList());
		model.addAttribute("lngTyp", lngTyp);
		
		return thisUrl + "/ajax/usageTypeRuleUpdatePopUp";
	}

	@RequestMapping(value = "usageTypeRuleUpdateAction", method = RequestMethod.POST)
	public String usageTypeRuleUpdateAction(
			UsageTypeRule usageTypeRule, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		usageTypeRule.setUserId(session.getUserId());
		usageTypeRule.setSysToDate(DateUtil.sysdate());
		model.addAttribute("dataNmList", usageTypeRuleService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeRuleService.getSeqNoList());
        model.addAttribute("lngTyp", lngTyp);
        
        System.out.println("@@@@@@@@@@@" + usageTypeRule.toString());
        
		if( usageTypeRuleService.modUsageTypeRule(usageTypeRule) > 0 ) {		
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		} else {		
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}	
	
		return thisUrl + "/usageTypeRuleList";
	}	

	@RequestMapping(value = "usageTypeRuleDeleteAction", method = RequestMethod.POST)
	public String deleteAction(
			UsageTypeRule usageTypeRule,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		usageTypeRule.setUserId(session.getUserId());
		
        model.addAttribute("dataNmList", usageTypeRuleService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeRuleService.getSeqNoList());
				
		if(usageTypeRuleService.delUsageTypeRule(usageTypeRule) > 0 ) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}  
		return thisUrl + "/usageTypeRuleList";
	}

}