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
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingFactor;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingFactorService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/ratingFactor")
public class RatingFactorController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RatingFactorService ratingFactorService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/ratingFactor";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
	/**
	 * ratingFactorList
	 * 설명 : 속성 목록 조회페이지 호출
	 */
	@RequestMapping(value = "ratingFactorList", method = RequestMethod.POST)
	public String getRatingFactorList(
				Model model, 
				RatingFactor ratingFactor,
				HttpServletRequest request) throws Exception {

		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String usgTyp = ratingFactor.getUsgTyp();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		ratingFactor.setUsgTyp(usgTyp);
		ratingFactor.setLngTyp(lngTyp);
		ratingFactor.setSidx(sidx);
		ratingFactor.setSord(sord);
		
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("lngTyp", lngTyp);
	
		return thisUrl + "/ratingFactorList";
	}
	
	/** 
	 * ratingFactorListAction
	 * 설	명 : 속성 목록 조회 페이지 리스트
	 * @param attribute
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "ratingFactorListAction", method = RequestMethod.POST)
	public void getRatingFactorListAction(
			RatingFactor ratingFactor
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();

		String usgTyp = ratingFactor.getUsgTyp();
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	//오늘날짜 가져오기
		
		ratingFactor.setLngTyp(lngTyp);
        ratingFactor.setCurrentDay(currentDay);
        
        List<RatingFactor> list = new ArrayList<RatingFactor>();
        
        list = ratingFactorService.getRatingFactorList(ratingFactor, page, perPageRow);
 
		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));//데이터유형 셀렉트박스에 들어갈 내용
	}
	
	/**
	 * ratingFactorInsertPopUp
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorInsertPopUp", method = RequestMethod.POST)
	public String ratingFactorInsertPopUp(
			RatingFactor ratingFactor
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		ratingFactor.setCurrentDay(currentDay);
		System.out.println("currentDay==>" + currentDay);
		
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));//데이터유형 셀렉트박스에 들어갈 내용
		model.addAttribute("ratingFacList", commonDataService.getCommonCodeList("PP00212", lngTyp));
		model.addAttribute("lngTyp", lngTyp);
		
		return  thisUrl + "/ajax/ratingFactorInsertPopUp";
	}
	
	/**
	 * addRatingFactor
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorInsertAction", method = RequestMethod.POST)
	public String addRatingFactor(
			RatingFactor ratingFactor,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingFactor.setInsertEffDt(ratingFactor.getInsertEffDt().replaceAll("-", "") + "000000");
		ratingFactor.setInsertExpDt("99991231235959");
		ratingFactor.setSearchUsgTyp(ratingFactor.getInsertUsgTyp());
		ratingFactor.setSearchRatingFac(ratingFactor.getInsertRatingFac());
		ratingFactor.setUserId(session.getUserId());
		ratingFactor.setSysToDate(DateUtil.sysdate());
		
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		
		if( ratingFactorService.getRatingFactorListCount(ratingFactor) == 0 ) {		
			if( ratingFactorService.addRatingFactor(ratingFactor) > 0) {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			} else {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}
			model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
			return thisUrl + "/ratingFactorList";
		}
		model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00049"));
		
		return thisUrl + "/ratingFactorList";
	}
	
	/**
	 * ratingFactorUpdatePopUp
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorUpdatePopUp", method = RequestMethod.POST)
	public String ratingFactorUpdatePopUp(
			RatingFactor ratingFactor
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		ratingFactor.setCurrentDay(currentDay);
		System.out.println("currentDay==>" + currentDay);
		
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));//데이터유형 셀렉트박스에 들어갈 내용
		model.addAttribute("ratingFacList", commonDataService.getCommonCodeList("PP00212", lngTyp));
		model.addAttribute("lngTyp", lngTyp);
		
		return  thisUrl + "/ajax/ratingFactorUpdatePopUp";
	}
	
	/**
	 * modRatingFactor
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorUpdateAction", method = RequestMethod.POST)
	public String ratingFactorUpdateAction(
			RatingFactor ratingFactor,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingFactor.setUserId(session.getUserId());
		ratingFactor.setSysToDate(DateUtil.sysdate());
		ratingFactor.setSearchUsgTyp(ratingFactor.getModUsgTyp());
		ratingFactor.setSearchRatingFac(ratingFactor.getModRatingFac());
		
		System.out.println(ratingFactor.toString());
		
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
	
		if( ratingFactorService.getRatingFactorListCount(ratingFactor) == 0 ) {		
			if( ratingFactorService.modRatingFactor(ratingFactor) > 0) {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			} else {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}
			model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
			return thisUrl + "/ratingFactorList";
		}
		model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00049"));
		
		return thisUrl + "/ratingFactorList";
	}
	
	/**
	 * delRatingFactor
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingFactorDeleteAction", method = RequestMethod.POST)
	public String delRatingFactor(
			RatingFactor ratingFactor,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String resultUrl = thisUrl + "/ratingFactorList";
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingFactor.setUserId(session.getUserId());
		ratingFactor.setRatingFac(ratingFactor.getRatingFac());
		ratingFactor.setUsgTyp(ratingFactor.getUsgTyp());
		
		if( ratingFactorService.delRatingFactor(ratingFactor) > 0) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		
		return resultUrl;
	}
}