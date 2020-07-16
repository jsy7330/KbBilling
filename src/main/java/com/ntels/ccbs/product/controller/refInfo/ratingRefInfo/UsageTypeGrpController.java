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

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeGrp;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeGrp;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.UsageTypeGrpService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/usageTypeGrp")
public class UsageTypeGrpController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UsageTypeGrpService usageTypeGrpService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/usageTypeGrp";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
	/**
	 * attributeList
	 * 설명 : 속성 목록 조회페이지 호출
	 */
	@RequestMapping(value = "usageTypeGrpList", method = RequestMethod.POST)
	public String list(Model model, Attribute attribute, HttpServletRequest request) throws Exception {
	
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("usgTypGrpList", commonDataService.getCommonCodeList("PP00214", lngTyp));

		return thisUrl + "/usageTypeGrpList";
	}
	
	@RequestMapping(value = "usageTypeListAction", method = RequestMethod.POST)
	public void usageTypeListAction(
			UsageTypeGrp usageTypeGrp
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		usageTypeGrp.setLngTyp(lngTyp);
		usageTypeGrp.setSidx(sidx);
		usageTypeGrp.setSord(sord);
		
		//오늘날짜 가져오기
        String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		
        List<UsageTypeGrp> list = new ArrayList<UsageTypeGrp>();
		
		list = usageTypeGrpService.getUsageTypeList(usageTypeGrp, page, perPage); 

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		model.addAttribute("usgTypGrpList", commonDataService.getCommonCodeList("PP00214", lngTyp));
	}
	
	@RequestMapping(value = "usageTypeGrpListAction", method = RequestMethod.POST)
	public void usageTypeGrpListAction(
			UsageTypeGrp usageTypeGrp
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		usageTypeGrp.setLngTyp(lngTyp);
		usageTypeGrp.setSidx(sidx);
		usageTypeGrp.setSord(sord);
		
        List<UsageTypeGrp> list = new ArrayList<UsageTypeGrp>();
		
		list = usageTypeGrpService.getUsageTypeGrpList(usageTypeGrp, page, perPage); 

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		model.addAttribute("usgTypGrpList", commonDataService.getCommonCodeList("PP00214", lngTyp));
	}
	
	@RequestMapping(value = "usageTypeGrpInsertAction", method = RequestMethod.POST)
	public String usageTypeGrpInsertAction(
			@RequestBody Map<String, Object> usageTypeGrp
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
        SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		UsageTypeGrp usageTypeGrpInfo = new UsageTypeGrp();
		
		usageTypeGrpInfo.setUpdateSetValList((List<Map<String, Object>>)usageTypeGrp.get("updateSetValList"));
		usageTypeGrpInfo.setInsertChrgCd("0000000000");
		usageTypeGrpInfo.setInsertExpDt("99991231235959");
		usageTypeGrpInfo.setInsertLmtInclude("Y");
		usageTypeGrpInfo.setUserId(session.getUserId());
		usageTypeGrpInfo.setSysToDate(DateUtil.sysdate());
		
		model.addAttribute("usgTypGrpList", commonDataService.getCommonCodeList("PP00214", lngTyp));

		usageTypeGrpService.addUsageTypeGrp(usageTypeGrpInfo);
		
		return thisUrl + "/ajax/usageTypeGrpInsertAction";
	}	
	
	@RequestMapping(value = "usageTypeGrpDeleteAction", method = RequestMethod.POST)
	public String usageTypeGrpDeleteAction(
			UsageTypeGrp usageTypeGrp
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
        SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분

		
		model.addAttribute("usgTypGrpList", commonDataService.getCommonCodeList("PP00214", lngTyp));
				
		usageTypeGrpService.delUsageTypeGrp(usageTypeGrp);
		
		return thisUrl + "/usageTypeGrpList";
	}	
}