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
import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.ManufacturerInfoVO;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeRule;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingCodeRuleService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/ratingCodeRule")
public class RatingCodeRuleController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RatingCodeRuleService ratingCodeRuleService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/ratingCodeRule";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
    
	/**
	 * ratingCodeRuleList
	 * 설명 : 과금코드룰 조회 페이지 리스트
	 */
	@RequestMapping(value = "ratingCodeRuleList", method = RequestMethod.POST)
	public String ratingCodeRuleList(
				Model model, 
				RatingCodeRule ratingCodeRule, 
				HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("ratingCodeRule", ratingCodeRule);
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("chrgCdseqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
	
		return thisUrl + "/ratingCodeRuleList";
	}
	
	/** 
	 * ratingCodeRuleList
	 * 설	명 : 과금코드룰 조회 페이지 리스트
	 * @param attribute
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "ratingCodeRuleListAction", method = RequestMethod.POST)
	public void ratingCodeRuleListAction(
			RatingCodeRule ratingCodeRule
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
        List<RatingCodeRule> list = new ArrayList<RatingCodeRule>();
		
		list = ratingCodeRuleService.getRatingCodeRuleList(ratingCodeRule, page, perPage);
		
		model.addAttribute("rows", list);
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("chrgCdseqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
	}

	/**
	 * attributeInsert
	 * 설명 : 속성 등록 페이지 호출
	 * @param attribute
	 * @param int page
	 * @param int perPage
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingCodeRuleInsertPopUp", method = RequestMethod.POST)
	public String ratingCodeRuleInsertPopUp(
			RatingCodeRule ratingCodeRule,
			Model model,
			HttpServletRequest request) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("lngTyp", lngTyp);
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("chrgCdseqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		
		return thisUrl + "/ajax/ratingCodeRuleInsertPopUp";
	}

	
	/**
	 * ratingCodeRuleInsertAction
	 * 설명 : 정보 등록
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingCodeRuleInsertAction", method = RequestMethod.POST)
	public String ratingCodeRuleInsertAction(
			RatingCodeRule ratingCodeRule, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {
		
		String resultUrl = thisUrl + "/ratingCodeRuleList";
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingCodeRule.setUserId(session.getUserId());
		ratingCodeRule.setSysToDate(DateUtil.sysdate());
		ratingCodeRule.setUsgTyp(ratingCodeRule.getInsertUsgTyp());
		ratingCodeRule.setChrgCdSeq(ratingCodeRule.getInsertChrgCdSeq());
		ratingCodeRule.setSeqNo(ratingCodeRule.getInsertSeqNo());
		
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("chrgCdseqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		
		if( ratingCodeRuleService.getRatingCodeRuleListCount(ratingCodeRule) == 0 ) {
			if( ratingCodeRuleService.addRatingCodeRule(ratingCodeRule) > 0 ) {		
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			} else {		
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}
			return resultUrl;
		}
			
		model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00049"));
		
		return resultUrl;
	}	
	
	@RequestMapping(value = "ratingCodeRuleUpdatePopUp", method = RequestMethod.POST)
	public String ratingCodeRuleUpdatePopUp(
			RatingCodeRule ratingCodeRule,
			Model model,
			HttpServletRequest request) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("lngTyp", lngTyp);
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("chrgCdseqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		
		return thisUrl + "/ajax/ratingCodeRuleUpdatePopUp";
	}
	
	/**
	 * ratingCodeRuleInsertAction
	 * 설명 : 정보 등록
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingCodeRuleUpdateAction", method = RequestMethod.POST)
	public String ratingCodeRuleUpdateAction(
			RatingCodeRule ratingCodeRule, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingCodeRule.setUserId(session.getUserId());
		ratingCodeRule.setSysToDate(DateUtil.sysdate());
		
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("chrgCdseqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		
		if( ratingCodeRuleService.modRatingCodeRule(ratingCodeRule) > 0 ) {		
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		} else {		
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}
			
		return thisUrl + "/ratingCodeRuleList";
	}	
	
	/**
	 * deleteAction
	 * 설명 : 삭제
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingCodeRuleDeleteAction", method = RequestMethod.POST)
	public String ratingCodeRuleDeleteAction(
			RatingCodeRule ratingCodeRule,
			Model model, 
			HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		String resultUrl = thisUrl + "/ratingCodeRuleList";
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
			
		ratingCodeRule.setUserId(session.getUserId());
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("chrgCdseqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));

		if( ratingCodeRuleService.delRatingCodeRule(ratingCodeRule) > 0 ) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}
		
		return resultUrl;
	}
}