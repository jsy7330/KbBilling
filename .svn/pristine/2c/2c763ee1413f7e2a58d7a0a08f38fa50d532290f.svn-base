package com.ntels.ccbs.customer.controller.contract.receipt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.domain.contract.receipt.ReceiptCounselVO;
import com.ntels.ccbs.customer.service.contract.receipt.ReceiptCounselService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;



@Controller
@RequestMapping(value = "/customer/contract/receipt/receiptCounsel")
public class ReceiptCounselController {
	
	private static String URL = "customer/contract/receipt/receiptCounsel";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ReceiptCounselService receiptCounselService;
	
	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
	@RequestMapping(value = "receiptCounselList", method = RequestMethod.POST)
	public String receiptCounselList(Model model, ReceiptCounselVO receiptCounselVO, HttpServletRequest request) throws Exception {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String refCode = receiptCounselVO.getRefCode();
		
		model.addAttribute("rcptLvl1CodeList", getRcptLvlCodeList("CM00016", refCode, lng));
		model.addAttribute("cnslStatList", commonDataService.getCommonCodeList("CM00020", lng)); //상담상태

		return URL + "/receiptCounsel";
	}

	@RequestMapping(value = "getRcptTabListAction", method = RequestMethod.POST)
	public String getRcptTabListAction(	Model model, HttpServletRequest request, 
			String searchStatDt,
			String searchEndDt,
			String cnslStat,
			String condCustId,
			String selRcptLvl1,
			String selRcptLvl2,
			String selRcptLvl3,
			String rcptYn,
			String elapse,
			String orgId,
			String sidx,
			String sord,
			int page,
			int rows) {
	
		//12시간 전 날짜 구하기
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -12);
		Date oneHourBack = cal.getTime();
		SimpleDateFormat Date = new SimpleDateFormat("yyyyMMddHHmmss");     
        String hourBack =  Date.format(oneHourBack);

		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String elapseDate="";
		
		if(elapse == "TL" || elapse.equals("TL")){
			elapseDate = "";
		}else if(elapse=="TW" || elapse.equals("TW")){
			elapseDate = hourBack;
		}else if(elapse=="TF" || elapse.equals("TF")){
			elapseDate = DateUtil.getDateStringYYYYMMDDHH24MISS(-1);
		}else if(elapse=="SD" || elapse.equals("SD")){
			elapseDate = DateUtil.getDateStringYYYYMMDDHH24MISS(-7);
		}
		
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> rcptTabInfo = receiptCounselService.getRcptTabList(searchStatDt, searchEndDt, cnslStat, condCustId, selRcptLvl1, selRcptLvl2, selRcptLvl3, rcptYn, elapse, elapseDate, orgId, sidx, sord, page, rows, lng, today);
		model.addAttribute("rcptList", rcptTabInfo.get("rcptList"));
		model.addAttribute("totalCount", rcptTabInfo.get("totalCount"));
		model.addAttribute("totalPages", rcptTabInfo.get("totalPages"));
		model.addAttribute("page", rcptTabInfo.get("page"));
		
		return URL + "/ajax/receiptCounsel";
	}

	public List<ReceiptCounselVO> getRcptLvlCodeList(String commonGrp,String refCode, String lng) throws Exception {
		List<ReceiptCounselVO> list = receiptCounselService.getRcptLvlCodeList("CM00016", refCode, lng);	
		return list;		
	}
	
	@RequestMapping(value = "getRcptInfoAction", method = RequestMethod.POST)
	public String getRcptInfoAction(	Model model, HttpServletRequest request, 
			String rcptId) {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		ReceiptCounselVO receiptCounselVO = receiptCounselService.getRcptInfo(rcptId, lng, today);
		
		model.addAttribute("resRcptInfo", receiptCounselVO);
		model.addAttribute("rcptLvl1CodeList", commonDataService.getCommonCodeListByRef1("CM00016", receiptCounselVO.getSoId(), lng));
		model.addAttribute("rcptLvl2CodeList", commonDataService.getCommonCodeListByRef1("CM00017", receiptCounselVO.getCnslMstCl(), lng));
		model.addAttribute("rcptLvl3CodeList", commonDataService.getCommonCodeListByRef1("CM00018", receiptCounselVO.getCnslMidCl(), lng));
		
		return URL + "/ajax/contractManagement";
	}
}
