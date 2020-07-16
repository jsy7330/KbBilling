package com.ntels.ccbs.product.controller.product.productDev;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping(value = "/product/product/productDev/viewApprovalStatus")
public class ProductViewApprovalStatusController {
	
	@Autowired
	private ProductDevMgtService productDevMgtService;	
	
	@Autowired
	private SequenceService sequenceSevice;		
	
	@Autowired
	private CommonDataService commonDataService;	
	
	private static String URL = "product/product/productDev/viewApprovalStatus";	
	
	//오늘날짜 가져오기
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	
    String currentDay1 =  DateUtil.getDateStringYYYYMMDDHH24MISS(0);
	List<Map<String,Object>> soAuthList;
    
	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		
		String confRslt = "PP00109";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();

		model.addAttribute("confRslt", commonDataService.getCommonCodeList(confRslt, lngTyp));
		
		return URL + "/main";
	}	
	
	@RequestMapping(value = "getConfSbjList", method = RequestMethod.POST)
	public void getConfSbjList(
			ProductDevMgt productDevMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
	
		List<ProductDevMgt> confSbjList = null;
		int count = 0;

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));			
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();	        
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setConfrCd(usrId);
		
		count = productDevMgtService.getConfSbjListCount(productDevMgt, soAuthList);		

		if (count > 0) { 
			confSbjList = productDevMgtService.getConfSbjList(productDevMgt, page, perPageRow, soAuthList);
			model.addAttribute("records", confSbjList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", confSbjList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	}	

	@RequestMapping(value = "confReturnPopup", method = RequestMethod.POST)
	public String confReturnPopup (Model model
			, HttpServletRequest request
			, ProductDevMgt productDevMgt  
			) throws Exception {
		
		String confRslt = "PP00109";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();

		model.addAttribute("confRslt", commonDataService.getCommonCodeList(confRslt, lngTyp));
		
		return  URL + "/ajax/confReturnPopup";
	}	
	
	@RequestMapping(value = "modConfReturn", method = RequestMethod.POST)
	public void modConfReturn (
				ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();		
		
		productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setCurrentDay(currentDay1);
		productDevMgt.setSysdate(DateUtil.sysdate());		
		
		String result = productDevMgtService.modConfReturn(productDevMgt);
				
		model.addAttribute("result", result);
	}		
	
}
