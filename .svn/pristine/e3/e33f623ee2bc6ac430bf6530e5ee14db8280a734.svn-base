package com.ntels.ccbs.product.controller.product.productDev;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import oracle.jdbc.Const;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;
import com.ntels.ccbs.product.service.product.productDev.ProductDevMgtService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Controller
@RequestMapping(value = "/product/product/productDev/viewDevStatus")
public class ProductViewDevStatusController {

	@Autowired
	private ProductDevMgtService productDevMgtService;	
	
	@Autowired
	private SequenceService sequenceSevice;		
	
	@Autowired
	private CommonDataService commonDataService;	
	
	private static String URL = "product/product/productDev/viewDevStatus";	
	
	//오늘날짜 가져오기
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	
    String currentDay1 =  DateUtil.getDateStringYYYYMMDDHH24MISS(0);
    
	List<Map<String,Object>> soAuthList;
    
	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		
		String dvlpTyp = "PP00106";
		String dvlpStatus = "PP00107";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("dvlpTyp", commonDataService.getCommonCodeList(dvlpTyp, lngTyp));
		model.addAttribute("dvlpStatus", commonDataService.getCommonCodeList(dvlpStatus, lngTyp));
		
		
		return URL + "/main";
	}	
	
	@RequestMapping(value = "getProdDvlpList", method = RequestMethod.POST)
	public void getProdDvlpList(
			ProductDevMgt productDevMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
	
		List<ProductDevMgt> prodDvlpList = null;
		int count = 0;

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));			
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();	        
		
		productDevMgt.setLngTyp(lngTyp);		
		
		
		count = productDevMgtService.getProdDvlpListCount(productDevMgt, soAuthList);		

		if (count > 0) { 
			prodDvlpList = productDevMgtService.getProdDvlpList(productDevMgt, page, perPageRow, soAuthList);
			model.addAttribute("records", prodDvlpList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", prodDvlpList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	}

	@RequestMapping(value = "getProdConfReqDtlList", method = RequestMethod.POST)
	public void getProdConfReqDtlList(
			ProductDevMgt productDevMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int result = -1;
				
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setConfStatus(Consts.PROD_MGT_CODE.CONF_REQ_ING);
        productDevMgt.setSysdate(DateUtil.sysdate());
        productDevMgt.setChgrId(usrId);
        productDevMgt.setCurrentDay(currentDay1);
        
        List<ProductDevMgt> confDtlList = productDevMgtService.transProdConfDtlList(productDevMgt);
        
		if (confDtlList.size() > 0){
			model.addAttribute("rows", confDtlList);
		}
	}
	
	@RequestMapping(value = "modProdConfReqCancelProc", method = RequestMethod.POST)
	public void modProdConfReqCancelProc (
				ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();		
		
		productDevMgt.setChgrId(usrId);
		productDevMgt.setModFl("CONF_REQ_CANCEL");
		productDevMgt.setDvlpStatus(Consts.PROD_MGT_CODE.DEV_ING);
		productDevMgt.setConfStatus(Consts.PROD_MGT_CODE.CONF_CANCEL);
		productDevMgt.setCurrentDay(currentDay1);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.modProdConfReqCancelProc(productDevMgt);
				
		model.addAttribute("result", result);
	}	
		
	@RequestMapping(value = "getProdConfReqDtlListPopup", method = RequestMethod.POST)
	public String getProdConfReqDtlListPopup (Model model
			, HttpServletRequest request
			, ProductDevMgt productDevMgt  
			) throws Exception {
	
		String radioGubun = "PP00106";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("radioGubun", commonDataService.getCommonCodeList(radioGubun, lngTyp));
		model.addAttribute("productDevMgt", productDevMgt);
		productDevMgt.setConfStatus(Consts.PROD_MGT_CODE.CONF_REQ_ING);
		List<ProductDevMgt> confReqCdList = productDevMgtService.getProdConfHistCnt(productDevMgt);
		model.addAttribute("confReqCdList", confReqCdList);
		
		
		return  URL + "/ajax/prodConfReqDtlListPopup";
	}
	
	@RequestMapping(value = "getConfUsrIdListPopUp", method = RequestMethod.POST)
	public String getConfUsrIdListPopUp (Model model
			, HttpServletRequest request
			, ProductDevMgt productDevMgt  
			) throws Exception {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getSoId();			
		
		model.addAttribute("usrId", usrId);
		return  URL + "/ajax/confUsrIdListPopUp";
	}	
	
	
	@RequestMapping(value = "getConfDeptComboList", method = RequestMethod.POST)
	public void confUsrIdList(
			ProductDevMgt productDevMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		model.addAttribute("confDeptComboList", productDevMgtService.getConfDeptComboList(productDevMgt));
	}
	
	@RequestMapping(value = "getConfUsrIdList", method = RequestMethod.POST)
	public void getConfUsrIdList(
			ProductDevMgt productDevMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
				
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();		
		productDevMgt.setAuthChk("Y");//권한 체크 때문에 추가 COMMON_GRP 값이 PP00302인 COMMON_CD(화면ID에 권한 있는 사용자만 가져옴
		
        List<ProductDevMgt> confUsrIdList = productDevMgtService.getConfUsrIdList(productDevMgt, soAuthList);
        
		if (confUsrIdList.size() > 0){
			Iterator it = confUsrIdList.iterator();
			while(it.hasNext()) {
				ProductDevMgt tmpProductDevMgt = (ProductDevMgt) it.next();
				String confReqCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMPD_PROD_CONF_DTL, "CR", 8);
				tmpProductDevMgt.setConfReqCd(confReqCd);
			}
			
			
			model.addAttribute("rows", confUsrIdList);
		}
	}	
	
	@RequestMapping(value = "addProdConfDtl", method = RequestMethod.POST)
	public void addProdConfDtl (
			@RequestBody List<ProductDevMgt> params,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
				
		String result = productDevMgtService.addProdConfDtl(params, sessionUser);
		
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "modProdConfReqProc", method = RequestMethod.POST)
	public void modProdConfReqProc (
			@RequestBody List<ProductDevMgt> params,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
				
		String result = productDevMgtService.modProdConfReqProc(params);
		
		model.addAttribute("result", result);
	}			

	@RequestMapping(value = "delProdConfDtl", method = RequestMethod.POST)
	public void delProdConfDtl (
			@RequestBody List<ProductDevMgt> params,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
				
		String result = productDevMgtService.delProdConfDtl(params, sessionUser);
		
		model.addAttribute("result", result);
	}
	
	
}
