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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeGrp;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.RatingCodeGrpService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/ratingCodeGrp")
public class RatingCodeGrpController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RatingCodeGrpService ratingCodeGrpService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/ratingCodeGrp";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
	/**
	 * ratingCodeGrpList
	 * 설명 : 속성 목록 조회페이지 호출
	 */
	@RequestMapping(value = "ratingCodeGrpList", method = RequestMethod.POST)
	public String list(
			Model model, 
			RatingCodeGrp ratingCodeGrp, 
			HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("chrgCdGrpList", commonDataService.getCommonCodeList("PP00281", lngTyp));
		
		return thisUrl + "/ratingCodeGrpList";
	}
	
	/** 
	 * ratingCodeGrpListAction
	 * 설	명 : 속성 목록 조회 페이지 리스트
	 * @param ratingCodeGrp
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "ratingCodeGrpListAction", method = RequestMethod.POST)
	public void ratingCodeGrpListAction(
			RatingCodeGrp ratingCodeGrp
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		ratingCodeGrp.setLngTyp(lngTyp);
		ratingCodeGrp.setSidx(sidx);
		ratingCodeGrp.setSord(sord);
		
		//오늘날짜 가져오기
        String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		
        List<RatingCodeGrp> list = new ArrayList<RatingCodeGrp>();
		
		list = ratingCodeGrpService.getRatingCodeGrpList(ratingCodeGrp, page, perPage);

		model.addAttribute("chrgCdGrpList", commonDataService.getCommonCodeList("PP00281", lngTyp));
		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/** 
	 * ratingCodeGrpListAction
	 * 설	명 : 속성 목록 조회 페이지 리스트
	 * @param ratingCodeGrp
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "ratingCodeDescriptionAction", method = RequestMethod.POST)
	public void ratingCodeDescriptionAction(
			RatingCodeGrp ratingCodeGrp
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		ratingCodeGrp.setLngTyp(lngTyp);
		ratingCodeGrp.setSidx(sidx);
		ratingCodeGrp.setSord(sord);

        List<RatingCodeGrp> list = new ArrayList<RatingCodeGrp>();
		
		list = ratingCodeGrpService.getRatingDescription(ratingCodeGrp, page, perPage);

		model.addAttribute("chrgCdGrpList", commonDataService.getCommonCodeList("PP00281", lngTyp));
		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}

	@RequestMapping(value = "ratingCodeGrpInsertAction", method = RequestMethod.POST)
	public String ratingCodeGrpInsertAction(
			@RequestBody Map<String, Object> ratingCodeGrp
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
        SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		RatingCodeGrp ratingCodeGrpInfo = new RatingCodeGrp();
		
		ratingCodeGrpInfo.setUpdateSetValList((List<Map<String, Object>>)ratingCodeGrp.get("updateSetValList"));
		ratingCodeGrpInfo.setUserId(session.getUserId());
		ratingCodeGrpInfo.setSysToDate(DateUtil.sysdate());
		
		model.addAttribute("chrgCdGrpList", commonDataService.getCommonCodeList("PP00281", lngTyp));

		ratingCodeGrpService.addRatingCodeGrp(ratingCodeGrpInfo);
		
		return thisUrl + "/ajax/ratingCodeGrpInsertAction";
	}

	@RequestMapping(value = "ratingCodeGrpDeleteAction", method = RequestMethod.POST)
	public String ratingCodeGrpDeleteAction(
			RatingCodeGrp ratingCodeGrp,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		ratingCodeGrp.setUserId(session.getUserId());
		model.addAttribute("chrgCdGrpList", commonDataService.getCommonCodeList("PP00281", lngTyp));
		
		if( ratingCodeGrpService.delRatingCodeGrp(ratingCodeGrp) > 0 ) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}  
	
		return thisUrl + "/ratingCodeGrpList"; 
	}
}