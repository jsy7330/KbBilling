package com.ntels.ccbs.product.controller.refInfo.commonInfo;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Factor;
import com.ntels.ccbs.product.service.refInfo.commonInfo.FactorService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/product/refInfo/commonInfo/factor")
public class factorController {

	private static String URL = "product/refInfo/commonInfo/factor";	
		
	@Autowired
	FactorService factorService;
	
	@Autowired
	private CommonDataService commonDataService;	
	
	@RequestMapping(value = "factorList", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		
		return URL + "/main";
	}	
	
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);

    /** 
     * 하루 전 날을 구한다. 
     */  
    public static Date getPreviousDate(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
          
        cal.add(Calendar.DATE, -1);  
          
        return cal.getTime();  
    }      
    
    public static String getMessage( String[] cnt, String[] msg )
    {
        String result = "";
        for ( int i = 0; i < cnt.length; i++ )
        {
            String strCnt = (String) cnt[i];
            if ( strCnt == null || strCnt.equals( "0" ) )
            {
            }
            else
            {
                result += msg[i];
            }
        }
        return result;
    }        
	
	@RequestMapping(value = "getTotalFactorList", method = RequestMethod.POST)
	public void getTotalFactorList(
			Factor factor
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		
        factor.setCurrentDay(currentDay);
        
        List<Factor> totalFactorList = null;
        int count = 0;
        count = factorService.totalFactorListCount(factor);
        
        String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
        factor.setLngTyp(lngTyp);
        
        if (count > 0) {
        	totalFactorList = factorService.totalFactorList(factor, page, perPageRow);
        	model.addAttribute("records", totalFactorList.size()); //현화면의 리스트갯수
        } else {
        	model.addAttribute("records", "0");
        }

        int size = totalFactorList.size();
        for (int i=0; i < size; i++) {
        	Factor tmpFactor = totalFactorList.get(i);
        	Date tmpDate = tmpFactor.getRegDate();      	
        			
        	String to = transFormat.format(tmpDate);
        	tmpFactor.setGridRegDate(to);
        	totalFactorList.set(i, tmpFactor);
        }
        
        
		model.addAttribute("rows", totalFactorList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);        
		
	}
    
	@RequestMapping(value = "factorListInsertPopUp", method = RequestMethod.POST)
	public String factorListInsertPopUp(Model model
										, HttpServletRequest request
										, Factor factor 
										) throws Exception {

		return  URL + "/ajax/factorListInsertPopUp";
	}	
	
	@RequestMapping(value = "fctrNameCheck", method = RequestMethod.POST)
	public void fctrNameCheck(
			Factor factor
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		 factor.setCurrentDay(currentDay);
		 
		 String result = factorService.fctrNameCheck(factor);
		
		 model.addAttribute("result", result);		 
	}
	
	@RequestMapping(value = "getCommonSelbox", method = RequestMethod.POST)
	public void getCommonSelbox(
			String commonCd
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		 model.addAttribute("commonData", commonDataService.getCommonCodeList(commonCd, lngTyp));		 
	}	
	
	@RequestMapping(value = "getRefColmnIdComboList", method = RequestMethod.POST)
	public void getRefColmnIdComboList(
			Factor factor
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		 model.addAttribute("refComboList", factorService.getRefColmnIdComboList(factor));		 
	}	
	
	@RequestMapping(value = "addTotalFactor", method = RequestMethod.POST)
	public void addTotalFactor (
			Factor factor
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
        factor.setCurrentDay(currentDay);
        factor.setSysDate(DateUtil.sysdate());		
        factor.setRegrId(usrId);
        factor.setChgrId(usrId);     
        factor.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
        
        String result = factorService.addTotalFactor(factor);
    	
		model.addAttribute("result", result);
        
	}

	@RequestMapping(value = "getCommonGrpPopupListPopUp", method = RequestMethod.POST)
	public String getCommonGrpPopupListPopUp(Model model
										, HttpServletRequest request
										, Factor factor 
										) throws Exception {

		return  URL + "/ajax/getCommonGrpPopupListPopUp";
	}	
	
	@RequestMapping(value = "getCommonGrpPopupList", method = RequestMethod.POST)	
	public void getCommonGrpPopupList(
			Factor factor 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();		
		factor.setLngTyp(lngTyp);		
		
		List<Factor> commonGrpList = factorService.getCommonGrpPopupList(factor);
		
		model.addAttribute("rows", commonGrpList);
	}
	
	
	@RequestMapping(value = "factorListUpdatePopUp", method = RequestMethod.POST)
	public String factorListUpdatePopUp(Model model
										, HttpServletRequest request
										, Factor factor 
										) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String fctrRefType = "PP00012";
		String tableId = "PP00013";
		String dataType = "PP00024";

		factor.setLngTyp(lngTyp);	

		Factor totalFactor = factorService.getTotalFactor(factor);
		if (totalFactor.getFctrRefTyp().equals("T")) {
			totalFactor.setTableName(totalFactor.getTableId());
			model.addAttribute("refComboList", factorService.getRefColmnIdComboList(totalFactor));
			model.addAttribute("tableId", commonDataService.getCommonCodeList(tableId, lngTyp));
		} else {
			model.addAttribute("refComboList", null);
			model.addAttribute("tableId", null);
		}
		
		
		model.addAttribute("fctrRefType", commonDataService.getCommonCodeList(fctrRefType, lngTyp));

		model.addAttribute("dataType", commonDataService.getCommonCodeList(dataType, lngTyp));
		model.addAttribute("totalFactor", totalFactor);

		
		return  URL + "/ajax/factorListUpdatePopUp";
	}		

	
	@RequestMapping(value = "modTotalFactor", method = RequestMethod.POST)
	public void modTotalFactor (
			Factor factor
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			

        factor.setCurrentDay(currentDay);
        factor.setSysDate(DateUtil.sysdate());		
        factor.setChgrId(usrId);  
        factor.setRegrId(usrId);
        factor.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
        
        String result = factorService.modTotalFactor(factor);
    	
		model.addAttribute("result", result);
        
	}	
	
	
	
}
