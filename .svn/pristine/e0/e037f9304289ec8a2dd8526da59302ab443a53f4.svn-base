package com.ntels.ccbs.distribute.controller.distributor.settlementMgt;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.distributor.settlementMgt.SettlementMgtVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/distributor/distributor/settlementMgt/settlementMgt")
public class SettlementMgtController {
	
	private static String URL = "distributor/distributor/settlementMgt/settlementMgt";
	
	@Autowired
	private CommonDataService commonDataService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "mngChargeRefMng", method = RequestMethod.POST)
	public String mngChargeRefMng(Model model, SettlementMgtVO settlementMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("chargeTp", commonDataService.getCommonCodeList("DN00025", lng));
		model.addAttribute("chargeCode", commonDataService.getCommonCodeList("DN00015", lng));		
		return URL + "/mngChargeRefMng";
	}
	
	@RequestMapping(value = "attractChargeRefMng", method = RequestMethod.POST)
	public String attractChargeRefMng(Model model, SettlementMgtVO settlementMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("policyChanel", commonDataService.getCommonCodeList("DN00019", lng));
		model.addAttribute("calculationTp", commonDataService.getCommonCodeList("DN00020", lng));		
		return URL + "/attractChargeRefMng";
	}
	@RequestMapping(value = "chargeSearch", method = RequestMethod.POST)
	public String chargeSearch(Model model, SettlementMgtVO settlementMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("chargeCode", commonDataService.getCommonCodeList("DN00015", lng));			
		return URL + "/chargeSearch";
	}
	@RequestMapping(value = "chargeAdjustmentMng", method = RequestMethod.POST)
	public String chargeAdjustmentMng(Model model, SettlementMgtVO settlementMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("chargeCode", commonDataService.getCommonCodeList("DN00015", lng));			
		return URL + "/chargeAdjustmentMng";
	}
	@RequestMapping(value = "openChargeRefMng", method = RequestMethod.POST)
	public String openChargeRefMng(Model model, SettlementMgtVO settlementMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("policyChanel", commonDataService.getCommonCodeList("DN00019", lng));
		model.addAttribute("calculationTp", commonDataService.getCommonCodeList("DN00020", lng));		
		return URL + "/openChargeRefMng";
	}
	@RequestMapping(value = "settlementInfoMng", method = RequestMethod.POST)
	public String settlementInfoMng(Model model, SettlementMgtVO settlementMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("chargeCode", commonDataService.getCommonCodeList("DN00015", lng));			
		return URL + "/settlementInfoMng";
	}
}
