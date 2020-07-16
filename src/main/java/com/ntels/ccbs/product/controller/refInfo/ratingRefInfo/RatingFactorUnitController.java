package com.ntels.ccbs.product.controller.refInfo.ratingRefInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingFactorUnit;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingFactorService;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingFactorUnitService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/ratingFactorUnit")
public class RatingFactorUnitController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RatingFactorUnitService ratingFactorUnitService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/ratingFactorUnit";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
	/**
	 * ratingFactorList
	 * 설명 : 속성 목록 조회페이지 호출
	 */
	@RequestMapping(value = "ratingFactorUnitList", method = RequestMethod.POST)
	public String getRatingFactorUnitList(
				Model model, 
				RatingFactorUnit ratingFactorUnit,
				HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingFactorUnit.setRateFacUnitNm(ratingFactorUnit.getRateFacUnitNm());
	
		return thisUrl + "/ratingFactorUnitList";
	}
	
	/** 
	 * ratingFactorListAction
	 * 설	명 : 과금요소 조회 페이지 리스트
	 * @param attribute
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "ratingFactorUnitListAction", method = RequestMethod.POST)
	public void getRatingFactorUnitListAction(
			RatingFactorUnit ratingFactorUnit
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	//오늘날짜 가져오기
		
        List<RatingFactorUnit> list = new ArrayList<RatingFactorUnit>();
        
        list = ratingFactorUnitService.getRatingFactorUnitList(ratingFactorUnit, page, perPage);
 
		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * ratingFactorInsertPopUp
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorUnitInsertPopUp", method = RequestMethod.POST)
	public String ratingFactorUnitInsertPopUp(
			RatingFactorUnit ratingFactorUnit
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		 
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		ratingFactorUnit.setCurrentDay(currentDay);
		
		//사용유형 셀렉트 박스에 들어갈 속성 유형 
		model.addAttribute("ratingFacList", commonDataService.getCommonCodeList("PP00212", lngTyp));     //필드성격
		model.addAttribute("lngTyp", lngTyp);
		
		return  thisUrl + "/ajax/ratingFactorUnitInsertPopUp";
	}
	
	/**
	 * addRatingFactor
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorUnitInsertAction", method = RequestMethod.POST)
	public String addRatingFactorUnit(
			RatingFactorUnit ratingFactorUnit,
			Model model, 
			HttpServletRequest request) {
		
		String resultUrl = thisUrl + "/ratingFactorUnitList";
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingFactorUnit.setUserId(session.getUserId());
		ratingFactorUnit.setSysToDate(DateUtil.sysdate());
		ratingFactorUnit.setDefaultUnitFlag("0");
		ratingFactorUnit.setInsertEffDt(ratingFactorUnit.getInsertEffDt().replace("-", "") + "000000");
		ratingFactorUnit.setInsertExpDt("99991231235959");
		ratingFactorUnit.setSearchRateFacUnit(ratingFactorUnit.getInsertRateFacUnit());

		model.addAttribute("listIfSys", commonDataService.getCommonCodeList("PP00111", lngTyp));
		
		if( ratingFactorUnitService.getRatingFactorUnitListCount(ratingFactorUnit) == 0 ) {
			if( ratingFactorUnitService.addRatingFactorUnit(ratingFactorUnit) > 0) {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			} else {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}
			model.addAttribute("listIfSys", commonDataService.getCommonCodeList("PP00111", lngTyp));
			return resultUrl;
		}
		model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00049"));
		
		return resultUrl;
	}
	
	/**
	 * ratingFactorUpdatePopUp
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorUnitUpdatePopUp", method = RequestMethod.POST)
	public String ratingFactorUnitUpdatePopUp(
			RatingFactorUnit ratingFactorUnit
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		 
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		ratingFactorUnit.setCurrentDay(currentDay);
		
		//사용유형 셀렉트 박스에 들어갈 속성 유형 
		model.addAttribute("ratingFacList", commonDataService.getCommonCodeList("PP00212", lngTyp));     //필드성격
		model.addAttribute("lngTyp", lngTyp);
		
		return  thisUrl + "/ajax/ratingFactorUnitUpdatePopUp";
	}
	
	/**
	 * modRatingFactor
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorUnitUpdateAction", method = RequestMethod.POST)
	public String ratingFactorUnitUpdateAction(
			RatingFactorUnit ratingFactorUnit,
			Model model, 
			HttpServletRequest request) {
		
		String resultUrl = thisUrl + "/ratingFactorUnitList";
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingFactorUnit.setUserId(session.getUserId());
		ratingFactorUnit.setSysToDate(DateUtil.sysdate());
		ratingFactorUnit.setDefaultUnitFlag("0");

		System.out.println("%%%%%%%" + ratingFactorUnit.toString());
		
		model.addAttribute("listIfSys", commonDataService.getCommonCodeList("PP00111", lngTyp));

		if( ratingFactorUnitService.modRatingFactorUnit(ratingFactorUnit) > 0) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}

		return resultUrl;
	}
	
	/**
	 * delRatingFactor
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorUnitDeleteAction", method = RequestMethod.POST)
	public String delRatingFactorUnit(
			RatingFactorUnit ratingFactorUnit,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String resultUrl = thisUrl + "/ratingFactorUnitList";
			
		int result = ratingFactorUnitService.delRatingFactorUnit(ratingFactorUnit);

		model.addAttribute("result", result);
		model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
	
		return resultUrl;
	}
	

}