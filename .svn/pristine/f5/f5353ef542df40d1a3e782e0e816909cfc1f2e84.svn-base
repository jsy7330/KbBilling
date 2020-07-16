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
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeMap;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingCodeMapService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/ratingCodeMap")
public class RatingCodeMapController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RatingCodeMapService ratingCodeMapService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/ratingCodeMap";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
	/**
	 * ratingCodeMapList
	 * 설명 : 속성 목록 조회페이지 호출
	 */
	@RequestMapping(value = "ratingCodeMapList", method = RequestMethod.POST)
	public String list(
			Model model, 
			RatingCodeMap ratingCodeMap, 
			HttpServletRequest request) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");

		model.addAttribute("usgTypList", commonDataService.getCommonCodeList(("PP00303"), lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		
		return thisUrl + "/ratingCodeMapList";
	}
	
	/** 
	 * ratingCodeMapListAction
	 * 설	명 : 속성 목록 조회 페이지 리스트
	 * @param ratingCodeMap
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "ratingCodeMapListAction", method = RequestMethod.POST)
	public void ratingCodeMapListAction(
			RatingCodeMap ratingCodeMap
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		ratingCodeMap.setLngTyp(lngTyp);
		ratingCodeMap.setSidx(sidx);
		ratingCodeMap.setSord(sord);

        List<RatingCodeMap> list = new ArrayList<RatingCodeMap>();
		
        list = ratingCodeMapService.getRatingCodeMapList(ratingCodeMap, page, perPage);

		model.addAttribute("usgTypList", commonDataService.getCommonCodeList(("PP00303"), lngTyp));
        model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));//데이터유형 셀렉트박스에 들어갈 내용
		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}

	/**
	 * ratingCodeMapInsert
	 * 설명 : 속성 등록 페이지 호출
	 * @param ratingCodeMap
	 * @param int page
	 * @param int perPage
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingCodeMapInsertPopUp", method = RequestMethod.POST)
	public String ratingCodeMapInsertPopUp(
			RatingCodeMap ratingCodeMap,
			Model model,
			HttpServletRequest request) throws Exception {
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("lngTyp", lngTyp);
		model.addAttribute("flagList", commonDataService.getCommonCodeList("PP00025", lngTyp));
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		model.addAttribute("chrgCdList", ratingCodeMapService.getChrgCdList());
		
		return thisUrl + "/ajax/ratingCodeMapInsertPopUp";
	}
	
	/**
	 * ratingCodeMapInsertAction
	 * 설명 : 속성 정보 등록
	 * @param ratingCodeMap
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingCodeMapInsertAction", method = RequestMethod.POST)
	public String ratingCodeMapInsertAction(
			RatingCodeMap ratingCodeMap, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {
		
		String resultUrl = thisUrl + "/ratingCodeMapList";
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingCodeMap.setUserId(session.getUserId());
		ratingCodeMap.setSysToDate(DateUtil.sysdate());
		ratingCodeMap.setInsertEffDt(ratingCodeMap.getInsertEffDt().replace("-", "") + "000000");
		ratingCodeMap.setInsertExpDt(ratingCodeMap.getInsertExpDt().replace("-", "") + "235959");
		ratingCodeMap.setSearchChrgCdSeq(ratingCodeMap.getInsertChrgCd());
		ratingCodeMap.setSearchSeqNo(ratingCodeMap.getInsertSeqNo());
		ratingCodeMap.setSearchUsgTyp(ratingCodeMap.getInsertUsgTyp());
		
		model.addAttribute("flagList", commonDataService.getCommonCodeList("PP00025", lngTyp));
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		
		
		if( ratingCodeMapService.getRatingCodeMapListCount(ratingCodeMap) == 0 ) {
			if( ratingCodeMapService.addRatingCodeMap(ratingCodeMap) > 0 ) {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			} else {		
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}		
			return resultUrl;
		}
		
		model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00049"));	
		
		return resultUrl;
	}
	
	/**
	 * ratingCodeMapInsert
	 * 설명 : 속성 등록 페이지 호출
	 * @param ratingCodeMap
	 * @param int page
	 * @param int perPage
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingCodeMapUpdatePopUp", method = RequestMethod.POST)
	public String ratingCodeMapUpdatePopUp(
			RatingCodeMap ratingCodeMap,
			Model model,
			HttpServletRequest request) throws Exception {
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("lngTyp", lngTyp);
		model.addAttribute("flagList", commonDataService.getCommonCodeList("PP00025", lngTyp));
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		model.addAttribute("chrgCdList", ratingCodeMapService.getChrgCdList());
		
		return thisUrl + "/ajax/ratingCodeMapUpdatePopUp";
	}
	
	@RequestMapping(value = "ratingCodeMapUpdateChrgCdPopUp", method = RequestMethod.POST)
	public String ratingCodeMapUpdateChrgCdPopUp(
			RatingCodeMap ratingCodeMap,
			Model model,
			HttpServletRequest request) throws Exception {
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("lngTyp", lngTyp);
		
		return thisUrl + "/ajax/ratingCodeMapUpdateChrgCdPopUp";
	}
	
	/**
	 * ratingCodeMapInsertAction
	 * 설명 : 속성 정보 등록
	 * @param ratingCodeMap
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingCodeMapUpdateAction", method = RequestMethod.POST)
	public String ratingCodeMapUpdateAction(
			RatingCodeMap ratingCodeMap, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {
		
		String resultUrl = thisUrl + "/ratingCodeMapList";
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		ratingCodeMap.setUserId(session.getUserId());
		ratingCodeMap.setSysToDate(DateUtil.sysdate());
		ratingCodeMap.setModEffDt(ratingCodeMap.getModEffDt().replace("-", "") + "000000");
		ratingCodeMap.setModExpDt(ratingCodeMap.getModExpDt().replace("-", "") + "235959");
		
		System.out.println(ratingCodeMap.toString());
		
		
		if( ratingCodeMapService.modRatingCodeMap(ratingCodeMap) > 0 ) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		} else {		
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}		
		model.addAttribute("flagList", commonDataService.getCommonCodeList("PP00025", lngTyp));
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		
		return resultUrl;
	}	
	
	/**
	 * deleteAction
	 * 설명 : 속성 종료
	 * @param ratingCodeMap
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "ratingCodeMapDeleteAction", method = RequestMethod.POST)
	public String ratingCodeMapDeleteAction(
			RatingCodeMap ratingCodeMap,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String resultUrl = thisUrl + "/ratingCodeMapList";
		
		ratingCodeMap.setUserId(session.getUserId());
		
		model.addAttribute("flagList", commonDataService.getCommonCodeList("PP00025", lngTyp));
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("seqList", commonDataService.getCommonCodeList("PP00304", lngTyp));
		
		if( ratingCodeMapService.delRatingCodeMap(ratingCodeMap) > 0 ) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}  
		return resultUrl;
	}
}