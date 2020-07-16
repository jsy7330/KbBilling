package com.ntels.ccbs.product.controller.refInfo.itemTypeMgt;

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
import com.ntels.ccbs.product.domain.refInfo.itemTypeMgt.BillingItem;
import com.ntels.ccbs.product.service.refInfo.itemTypeMgt.BillingItemService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Controller
@RequestMapping(value = "/product/refInfo/itemTypeMgt/billingItem")
public class BillingItemController {

	private static String URL = "product/refInfo/itemTypeMgt/billingItem";
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private SequenceService sequenceSevice;	
	
	@Autowired
	private BillingItemService billingItemService;
	
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");	
	
    
    /** 
     * 하루 전 날을 구한다. 
     */  
    public static Date getPreviousDate(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
          
        cal.add(Calendar.DATE, -1);  
          
        return cal.getTime();  
    }   	
    /** 
     * 다음 날을 구한다. 
     */  
    public static Date getNextDate(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
          
        cal.add(Calendar.DATE, +1);  
          
        return cal.getTime();  
    }   	
	
	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {		
		
		return URL + "/main";
	}	
	
	@RequestMapping(value = "productBillingItemListAction", method = RequestMethod.POST)	
	public void productBillingItemListAction(
			BillingItem billingItem
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();        
        
		List<BillingItem> productBillingItemList = null;
		
		billingItem.setLngTyp(lngTyp);
		billingItem.setCurrentDay(currentDay);
		
		int count = 0;
		
		count = billingItemService.getInvoiceItemListCount(billingItem);		
		
		if (count > 0) {
			productBillingItemList = billingItemService.getInvoiceItemList(billingItem, page, perPageRow);
		
			model.addAttribute("records", productBillingItemList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", productBillingItemList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);			
		
	}	
	
	@RequestMapping(value = "productBillingItemListInsertPopUp", method = RequestMethod.POST)
	public String productDevMgtProductListInsertPopUp(Model model
										, HttpServletRequest request
										, BillingItem billingItem 
										) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String invItmGrp1 = "PP09911";		//인쇄항목그룹1 공통코드 없음
		String invItmGrp2 = "PP09912";		//인쇄항목그룹2 공통코드 없음
		String invPrtGrpStd = "PP00186";	//인쇄출력그룹기준
		
		model.addAttribute("invItmGrp1", commonDataService.getCommonCodeList(invItmGrp1, lngTyp));		
		model.addAttribute("invItmGrp2", commonDataService.getCommonCodeList(invItmGrp2, lngTyp));	
		model.addAttribute("invPrtGrpStd", commonDataService.getCommonCodeList(invPrtGrpStd, lngTyp));
		model.addAttribute("printPriNo", billingItemService.getInvoiceItmPriNo());			
		
		return  URL + "/ajax/productBillingItemListInsertPopUp";		
	}		
	
	@RequestMapping(value = "addInvoiceItem", method = RequestMethod.POST)
	public void addInvoiceItem (
				BillingItem billingItem 
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		int result = -1;
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();	
		
		billingItem.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		billingItem.setCurrentDay(currentDay);
		billingItem.setSysDate(DateUtil.sysdate());
		billingItem.setRegrId(usrId);
		billingItem.setChgrId(usrId);		
		
		billingItem.setCurrentDay(currentDay);
		
		int count = billingItemService.getInvoiceItemDupCnt(billingItem);
		
		if (count == 0) {
			String invItmCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMBI_RATE_ITM_TYP, "IP", 5);
			billingItem.setInvItmCd(invItmCd);
			
			result = billingItemService.addInvoiceItem(billingItem);
			model.addAttribute("result", String.valueOf(result));
		} else {
			model.addAttribute("result", "-1");
		}
		
	}
	
	@RequestMapping(value = "productBillingItemListUpdatePopUp", method = RequestMethod.POST)
	public String productBillingItemListUpdatePopUp(Model model
										, HttpServletRequest request
										, BillingItem billingItem 
										) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String invItmGrp1 = "PP09911";		//인쇄항목그룹1 공통코드 없음
		String invItmGrp2 = "PP09912";		//인쇄항목그룹2 공통코드 없음
		String invPrtGrpStd = "PP00186";	//인쇄출력그룹기준
		
		model.addAttribute("invItmGrp1", commonDataService.getCommonCodeList(invItmGrp1, lngTyp));		
		model.addAttribute("invItmGrp2", commonDataService.getCommonCodeList(invItmGrp2, lngTyp));	
		model.addAttribute("invPrtGrpStd", commonDataService.getCommonCodeList(invPrtGrpStd, lngTyp));
		
		
		BillingItem tmpBillingItem = billingItemService.getInvoiceItm(billingItem);
			
		model.addAttribute("tmpBillingItem", tmpBillingItem);		
		
		return  URL + "/ajax/productBillingItemListUpdatePopUp";		
	}			
	
	@RequestMapping(value = "modInvoiceItem", method = RequestMethod.POST)
	public void modInvoiceItem (
				BillingItem billingItem  
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {	
	
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		int result = -1;		
		billingItem.setCurrentDay(currentDay);
		billingItem.setSysDate(DateUtil.sysdate());
		billingItem.setRegrId(usrId);
		billingItem.setChgrId(usrId);			
		
		int count = billingItemService.getInvoiceItemDupExpCnt(billingItem);
			
		//종료 일자를 유효시작일자 전 날로 설정
		Date tmpInactDt = null;
		try {
			tmpInactDt = transFormat.parse(billingItem.getActDt());
		} catch (ParseException e) {
			
			logger.error("error", e);
		}
		
		if (count == 0) {
			if (billingItem.getInActYn().equals(Consts.SVC_MGT_CODE.EXPIRE_NO)) //EXPIRE_NO : 1 => 변경 / 2 -> 종료
			{
			
				if (billingItem.getActDt().equals(billingItem.getBaseActDt())) {
					result = billingItemService.modInvoiceItm(billingItem);
				} else {
					billingItem.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
			
					
					String inactDt = billingItem.getInactDt();	
					
					billingItem.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
					result = billingItemService.modInvoiceItmInactDt(billingItem);
					
					billingItem.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
					billingItem.setInactDt(inactDt);
					result = billingItemService.addInvoiceItem(billingItem);					
					
				}
			} else {
				billingItem.setInactDt(transFormat.format(getNextDate(tmpInactDt)));
				billingItem.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
				result = billingItemService.modInvoiceItmInactDtion(billingItem);
			}
		} else {
			result = -1;
		}
		model.addAttribute("result", String.valueOf(result));
		
	}	
}
