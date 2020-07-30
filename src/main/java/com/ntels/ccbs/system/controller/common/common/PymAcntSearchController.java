package com.ntels.ccbs.system.controller.common.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.common.PymAcntVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.common.PymAcntSearchPopupService;


@Controller
@RequestMapping(value = "/system/common/common/pymAcntSearch")
public class PymAcntSearchController {
	/**
	 * 납부계정조회 메인 URL
	 */
	private static String URL = "system/common/common/pymAcntSearch";
	
	@Autowired
	private PymAcntSearchPopupService pymAcntSearchPopupService;
	
	@RequestMapping(value = "pymAcntSearchPopup", method = RequestMethod.POST)
	public String pymAcntSearchPopup(Model model,HttpServletRequest request
			,String inputSoId
			,String inputCustNm
			,String inputPymAcntId
			,String inputIsUnmaskYn
			,String outputSoId
			,String outputCustNm
			,String outputCustId
			,String outputPymAcntId
			,String outputPymAcntNm
			){
		
		model.addAttribute("INPUT_SO_ID", (StringUtils.isEmpty(inputSoId) == true ? "SEL" : inputSoId));
		model.addAttribute("INPUT_CUST_NM", (StringUtils.isEmpty(inputCustNm) == true ? "" : inputCustNm));
		model.addAttribute("INPUT_PYM_ACNT_ID", (StringUtils.isEmpty(inputPymAcntId) == true ? "" : inputPymAcntId));
		model.addAttribute("INPUT_IS_UNMASK_YN", (StringUtils.isEmpty(inputIsUnmaskYn) == true ? "" : inputIsUnmaskYn));
		model.addAttribute("OUTPUT_SO_ID", (StringUtils.isEmpty(outputSoId) == true ? "" : outputSoId));
		model.addAttribute("OUTPUT_CUST_ID", (StringUtils.isEmpty(outputCustId) == true ? "" : outputCustId));
		model.addAttribute("OUTPUT_CUST_NM", (StringUtils.isEmpty(outputCustNm) == true ? "" : outputCustNm));
		model.addAttribute("OUTPUT_PYM_ACNT_ID", (StringUtils.isEmpty(outputPymAcntId) == true ? "" : outputPymAcntId));
		model.addAttribute("OUTPUT_PYM_ACNT_NM", (StringUtils.isEmpty(outputPymAcntNm) == true ? "" : outputPymAcntNm));
		
		return  URL+"/ajax/pymAcntSearchPopup";
	}
	
	@RequestMapping(value = "getPymAcntListAction", method = RequestMethod.POST)
	public String getPymAcntListAction(Model model,HttpServletRequest request
			,String soId
			,String custNm
			,String custId
			,String ctrtId
			,String svcTelNo
			,String pymAcntId
	        ,String sidx
	        ,String sord
	        ,int page
	        ,int rows) {
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		System.out.println(today+"/////////////////////////////////////////////////////////////////////");
		System.out.println(today+"/////////////////////////////////////////////////////////////////////");
		Map<String,Object> pymInfoList = pymAcntSearchPopupService.getPymAcntInfoList(soId, custNm, custId, ctrtId, svcTelNo, pymAcntId, sessionUser.getSoAuthList(), today, lng, sidx, sord, page, rows);
		
		model.addAttribute("pymAcntInfoList", pymInfoList.get("pymAcntInfoList"));
		model.addAttribute("totalCount", pymInfoList.get("totalCount"));
		model.addAttribute("totalPages", pymInfoList.get("totalPages"));
		model.addAttribute("page", pymInfoList.get("page"));
		
		
		return  URL+"/ajax/pymAcntSearchPopup";
		
	}

	
	@RequestMapping(value = "pymAcntPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String pymAcntPopup(Model model,PymAcntVO pymAcntVO,HttpServletRequest request)throws Exception {
		model.addAttribute("pymAcntVO", pymAcntVO);
		if("o".equals(pymAcntVO.getPopType())){
			return URL + "/opopup/pymAcntPopup";
		}else{
			return URL + "/ajax/pymAcntPopup";
		}
	}
	
	@RequestMapping(value = "pymAcntList", method = RequestMethod.POST)
	public String pymAcntList(Model model,PymAcntVO pymAcntVO,HttpServletRequest request
			)throws Exception {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		pymAcntVO.setLng(lng);
		Map<String,Object> pymAcntMap = pymAcntSearchPopupService.getPymAcntList(pymAcntVO);
		
		model.addAttribute("pymAcntVO", pymAcntVO);
		model.addAttribute("pymAcntList", pymAcntMap.get("pymAcntList"));
		model.addAttribute("totalCount", pymAcntMap.get("totalCount"));
		model.addAttribute("totalPages", pymAcntMap.get("totalPages"));
		model.addAttribute("page", pymAcntMap.get("page"));
		
		if("o".equals(pymAcntVO.getPopType())){
			return URL + "/opopup/userSearchPopup";
		}else{
			return URL + "/ajax/userSearchPopup";
		}
	}
	
}