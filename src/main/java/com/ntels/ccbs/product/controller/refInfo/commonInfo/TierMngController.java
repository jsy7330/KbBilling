package com.ntels.ccbs.product.controller.refInfo.commonInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.TierMng;
import com.ntels.ccbs.product.service.refInfo.commonInfo.TierMngService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Controller
@RequestMapping(value = "/product/refInfo/commonInfo/tierMng")
public class TierMngController {

	private static String URL = "product/refInfo/commonInfo/tierMng";
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private SequenceService sequenceSevice;	
	
	@Autowired
	private TierMngService tierMngService;
	
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");	
	
	
	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String main(	Model model,
			HttpServletRequest request) {		
		
		return URL + "/main";
	}	
	
	@RequestMapping(value = "tierMngListAction", method = RequestMethod.POST)	
	public void tierMngListAction(
			TierMng tierMng
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));	
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();        
        
		List<TierMng> tierMngList = null;
		
		tierMng.setLngTyp(lngTyp);
		System.out.println("getSearchMon==========>"+tierMng.getSearchMon());
		if(tierMng.getSearchMon() == null){
			
		}else if(tierMng.getSearchMon() != null){
			tierMng.setSearchMon(tierMng.getSearchMon());
		}
		
		tierMng.setCurrentDay(currentDay);
		
		int count = 0;
		
		count = tierMngService.getTierMngListCount(tierMng);		
		
		if (count > 0) {
			tierMngList = tierMngService.getTierMngList(tierMng, page, perPageRow);
		
			model.addAttribute("records", tierMngList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", tierMngList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);			
		
	}
		
	@RequestMapping(value = "tierMngDtlListAction", method = RequestMethod.POST)	
	public void tierMngDtlListAction(
			TierMng tierMng
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));	
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();        
        
		List<TierMng> tierMngDtlList = null;
		
		tierMng.setLngTyp(lngTyp);
		tierMng.setCurrentDay(currentDay);

		int count = 0;
		
		count = tierMngService.getTierMngDtlListCount(tierMng);		
		
		if (count > 0) {
			tierMngDtlList = tierMngService.getTierMngDtlList(tierMng, page, perPageRow);
		
			model.addAttribute("records", tierMngDtlList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", tierMngDtlList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	}
	
	@RequestMapping(value = "searchPopup", method = RequestMethod.POST)
	public String searchPopup(	Model model,
			HttpServletRequest request) {		
		
		return URL + "/ajax/searchPopup";
	}		
	@RequestMapping(value = "searchProdAction", method = RequestMethod.POST)	
	public void searchProdAction(
			TierMng tierMng
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));	
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();        
        		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
	
		if(tierMng.getSearchSoIdPop() == null){
			tierMng.setSearchSoIdPop(sessionUser.getSoId());
		}else if(tierMng.getSearchSoIdPop() != null){
			tierMng.setSearchSoIdPop(tierMng.getSoId());
		}

		List<TierMng> searchProdList = null;
		
		tierMng.setLngTyp(lngTyp);
		tierMng.setCurrentDay(currentDay);

		int count = 0;
		
		count = tierMngService.getSearchProdListCount(tierMng);		
		
		if (count > 0) {
			searchProdList = tierMngService.getSearchProdList(tierMng, page, perPageRow);
		
			model.addAttribute("records", searchProdList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", searchProdList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	}	
}
