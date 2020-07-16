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
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.itemTypeMgt.ChargeType;
import com.ntels.ccbs.product.service.refInfo.itemTypeMgt.ChargeTypeService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Controller
@RequestMapping(value = "/product/refInfo/itemTypeMgt/chargeType")
public class ChargeTypController {

	private static String URL = "product/refInfo/itemTypeMgt/chargeType";

	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ChargeTypeService chargeTypeService;
	
	@Autowired
	private SequenceService sequenceSevice;	
	
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
	
	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String chrgCtgry = "PP00031";		//요금유형
		
		model.addAttribute("chrgCtgry", commonDataService.getCommonCodeList(chrgCtgry, lngTyp));
		
		return URL + "/main";
	}	
	
	@RequestMapping(value = "productChargeTypeListAction", method = RequestMethod.POST)	
	public void productDevMgtProductListAction(
			ChargeType chargeType
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();        
        
		List<ChargeType> productDevMgtProductList = null;
		//page 2 rows 20		
		chargeType.setLngTyp(lngTyp);
		chargeType.setCurrentDay(currentDay);
		
		int count = 0;
		
		count = chargeTypeService.getRateTypeListCount(chargeType);		
		
		if (count > 0) {
			productDevMgtProductList = chargeTypeService.getRateTypeList(chargeType, page, perPageRow);
		
			model.addAttribute("records", productDevMgtProductList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", productDevMgtProductList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);			
		
	}
	
	@RequestMapping(value = "productChargeTypeListInsertPopUp", method = RequestMethod.POST)
	public String productDevMgtProductListInsertPopUp(Model model
										, HttpServletRequest request
										, ChargeType chargeType 
										) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String chrgCtgry = "PP00031";		//요금유형
		
		model.addAttribute("chrgCtgry", commonDataService.getCommonCodeList(chrgCtgry, lngTyp));		
		
		return  URL + "/ajax/productChargeTypeListInsertPopUp";		
	}	
	
	@RequestMapping(value = "getRateTypeMaxPriNo", method = RequestMethod.POST)
	public void getRateTypeMaxPriNo (
				ChargeType chargeType 
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		ChargeType productChargeTypeListChargeType = chargeTypeService.getRateTypeMaxPriNo(chargeType);
		
		model.addAttribute("productChargeTypeListChargeType", productChargeTypeListChargeType);	
		
	}
	
	@RequestMapping(value = "addRateType", method = RequestMethod.POST)
	public void addRateType (
				ChargeType chargeType 
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {

		int result = -1;
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();	
		String dispPriNo = chargeType.getDispPriNo();
		
		chargeType.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		chargeType.setCurrentDay(currentDay);
		chargeType.setSysDate(DateUtil.sysdate());
		chargeType.setRegrId(usrId);
		chargeType.setChgrId(usrId);		

		if(dispPriNo.length() < 3){	
			if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_N)) {
				chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_31 + CommonUtil.leftPadding(dispPriNo, 3, ""));
			} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_R)) {
				chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_32 + CommonUtil.leftPadding(dispPriNo, 3, ""));
			} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_U)) {
				chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_33 + CommonUtil.leftPadding(dispPriNo, 3, ""));
			} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_A)) {
				chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_41 + CommonUtil.leftPadding(dispPriNo, 3, ""));
			} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_M)) {
				chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_42 + CommonUtil.leftPadding(dispPriNo, 3, ""));
			} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_D)) {
				chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_43 + CommonUtil.leftPadding(dispPriNo, 3, ""));
			} else {
				chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_91 + CommonUtil.leftPadding(dispPriNo, 3, ""));
			}
		}
		int count = chargeTypeService.getRateTypeDupCnt(chargeType);
		
		if (count == 0) {
			String rateItmTypCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMBI_RATE_ITM_TYP, "CT", 5);
			chargeType.setRateItmTypCd(rateItmTypCd);
			
			result = chargeTypeService.addRateType(chargeType);
			model.addAttribute("result", String.valueOf(result));
		} else {
			model.addAttribute("result", "-1");
		}
	}
	
	@RequestMapping(value = "productChargeTypeListUpdatePopUp", method = RequestMethod.POST)
	public String productDevMgtProductListUpdatePopUp(Model model
										, HttpServletRequest request
										, ChargeType chargeType 
										) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String chrgCtgry = "PP00031";		//요금유형
		ChargeType productChargeTypeListUpdateChargeType = chargeTypeService.getRateType(chargeType);
		
		
		
		model.addAttribute("chrgCtgry", commonDataService.getCommonCodeList(chrgCtgry, lngTyp));		
		model.addAttribute("productChargeTypeListUpdateChargeType", productChargeTypeListUpdateChargeType);		
		
		return  URL + "/ajax/productChargeTypeListUpdatePopUp";		
	}		
	
	@RequestMapping(value = "modRateType", method = RequestMethod.POST)
	public void modRateType (
				ChargeType chargeType 
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String dispPriNo = chargeType.getDispPriNo();
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		int result = -1;
		
		
		chargeType.setCurrentDay(currentDay);
		chargeType.setChgrId(usrId);
		chargeType.setRegrId(usrId);
		chargeType.setSysDate(DateUtil.sysdate());
		
		if (dispPriNo.isEmpty()) {
			if(dispPriNo.length() < 3){	
				if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_N)) {
					chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_31 + CommonUtil.leftPadding(dispPriNo, 3, ""));
				} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_R)) {
					chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_32 + CommonUtil.leftPadding(dispPriNo, 3, ""));
				} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_U)) {
					chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_33 + CommonUtil.leftPadding(dispPriNo, 3, ""));
				} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_A)) {
					chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_41 + CommonUtil.leftPadding(dispPriNo, 3, ""));
				} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_M)) {
					chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_42 + CommonUtil.leftPadding(dispPriNo, 3, ""));
				} else if (chargeType.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_D)) {
					chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_43 + CommonUtil.leftPadding(dispPriNo, 3, ""));
				} else {
					chargeType.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_91 + CommonUtil.leftPadding(dispPriNo, 3, ""));
				}
			}
		} else {
			chargeType.setDispPriNo(dispPriNo);
		}
	
		//종료 일자를 유효시작일자 전 날로 설정
		Date tmpInactDt = null;
		try {
			tmpInactDt = transFormat.parse(chargeType.getActDt());
		} catch (ParseException e) {
			
			logger.error("error", e);
		}		
		
		int count = chargeTypeService.getRateTypeDupExpCnt(chargeType);
		
		if (count == 0) {
			if (chargeType.getInActYn().equals(Consts.SVC_MGT_CODE.EXPIRE_NO)) //EXPIRE_NO : 1 => 변경 / 2 -> 종료
			{				
				if (chargeType.getActDt().equals(chargeType.getBaseActDt())) {
					result = chargeTypeService.modRateType(chargeType);
				} else {
					chargeType.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);

					
					//String inactDt = chargeType.getInactDt();					
					chargeType.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
					result = chargeTypeService.modRateTypeActDt(chargeType);
					
					chargeType.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
					chargeType.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
					result = chargeTypeService.addRateType(chargeType);
					
				}
			} else {
				chargeType.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
				chargeType.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
				result = chargeTypeService.modRateTypeActDtion(chargeType);
			}
		} else {
			result = -1;
		}
		
		model.addAttribute("result", String.valueOf(result));
		
	}
	
}
