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
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeMap;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.UsageTypeMapService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/usageTypeMap")
public class UsageTypeMapController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UsageTypeMapService usageTypeMapService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/usageTypeMap";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
    @RequestMapping(value = "usageTypeMapList", method = RequestMethod.POST)
	public String list(
			Model model
		  , UsageTypeMap usageTypeMap
		  , HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");

		model.addAttribute("lngTyp", lngTyp);
		model.addAttribute("dataNmList", usageTypeMapService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeMapService.getSeqNoList());

        return thisUrl + "/usageTypeMapList";
	}
	

	@RequestMapping(value = "usageTypeMapListAction", method = RequestMethod.POST)
	public void attributeListAction(
			UsageTypeMap usageTypeMap
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
        List<UsageTypeMap> list = new ArrayList<UsageTypeMap>();
        
        list = usageTypeMapService.getUsageTypeMapList(usageTypeMap, page, perPage);
		
        model.addAttribute("lngTyp", lngTyp);
        model.addAttribute("dataNmList", usageTypeMapService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeMapService.getSeqNoList());
        model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}

	@RequestMapping(value = "usageTypeMapInsertPopUp", method = RequestMethod.POST)
	public String usageTypeMapInsertPopUp(
			UsageTypeMap usageTypeMap,
			Model model,
			HttpServletRequest request) throws Exception {
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("dataNmList", usageTypeMapService.getDataNmList());
		model.addAttribute("lngTyp", lngTyp);
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		
		return thisUrl + "/ajax/usageTypeMapInsertPopUp";
	}

	@RequestMapping(value = "usageTypeMapInsertAction", method = RequestMethod.POST)
	public String usageTypeMapInsertAction(
			UsageTypeMap usageTypeMap, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		usageTypeMap.setInsertEffDt(usageTypeMap.getInsertEffDt().replace("-", "") + "000000");
		usageTypeMap.setInsertExpDt(usageTypeMap.getInsertExpDt().replace("-", "") + "235959");
		usageTypeMap.setUserId(session.getUserId());
		usageTypeMap.setSysToDate(DateUtil.sysdate());
		model.addAttribute("dataNmList", usageTypeMapService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeMapService.getSeqNoList());
        model.addAttribute("lngTyp", lngTyp);
        
        if ( usageTypeMapService.getUsageTypeMapListCount(usageTypeMap) == 0 ) {
			if( usageTypeMapService.addUsageTypeMap(usageTypeMap) > 0 ) {		
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			} else {		
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}	
			return thisUrl + "/usageTypeMapList";
        }
        
    	model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00049"));
	
		return thisUrl + "/usageTypeMapList";
	}	
	
	@RequestMapping(value = "usageTypeMapUpdatePopUp", method = RequestMethod.POST)
	public String usageTypeUpdatePopUp(
			UsageTypeMap usageTypeMap,
			Model model,
			HttpServletRequest request) throws Exception {
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("dataNmList", usageTypeMapService.getDataNmList());
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("lngTyp", lngTyp);
		
		return thisUrl + "/ajax/usageTypeMapUpdatePopUp";
	}

	@RequestMapping(value = "usageTypeMapUpdateAction", method = RequestMethod.POST)
	public String usageTypeMapUpdateAction(
			UsageTypeMap usageTypeMap, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		usageTypeMap.setUserId(session.getUserId());
		usageTypeMap.setSysToDate(DateUtil.sysdate());
		usageTypeMap.setModEffDt(usageTypeMap.getModEffDt().replace("-", "") + "000000");
		usageTypeMap.setModExpDt(usageTypeMap.getModExpDt().replace("-", "") + "235959");
		
		model.addAttribute("dataNmList", usageTypeMapService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeMapService.getSeqNoList());
        model.addAttribute("lngTyp", lngTyp);
        
        System.out.println("@@@@@@@@@@@" + usageTypeMap.toString());
        
		if( usageTypeMapService.modUsageTypeMap(usageTypeMap) > 0 ) {		
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		} else {		
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}	
	
		return thisUrl + "/usageTypeMapList";
	}	

	@RequestMapping(value = "usageTypeMapDeleteAction", method = RequestMethod.POST)
	public String deleteAction(
			UsageTypeMap usageTypeMap,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		usageTypeMap.setUserId(session.getUserId());
		
		model.addAttribute("dataNmList", usageTypeMapService.getDataNmList());
        model.addAttribute("seqNoList", usageTypeMapService.getSeqNoList());
				
		if(usageTypeMapService.delUsageTypeMap(usageTypeMap) > 0 ) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}  
		return thisUrl + "/usageTypeMapList";
	}
}