package com.ntels.ccbs.product.controller.product.productDev;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/product/product/productRelMgt/productRel")
public class ProductRelController {

	@Autowired
	private ProductDevMgtService productDevMgtService;	
	
	@Autowired
	private SequenceService sequenceSevice;		
	
	@Autowired
	private CommonDataService commonDataService;	
	
	private static String URL = "product/product/productRelMgt/productRel";	
	
	//오늘날짜 가져오기
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	
    String currentDay1 =  DateUtil.getDateStringYYYYMMDDHH24MISS(0);
	List<Map<String,Object>> soAuthList;
    
	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
				
		return URL + "/main";
	}		
	
	@RequestMapping(value = "productRelationshipListAllPopup", method = RequestMethod.POST)
	public String confReturnPopup (Model model
			, HttpServletRequest request
			, ProductDevMgt productDevMgt  
			) throws Exception {
		
		return  URL + "/ajax/productRelationshipListAllPopup";
	}		
	
	@RequestMapping(value = "getProductRelationshipListAll", method = RequestMethod.POST)	
	public void getProductRelationshipListAll(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
			
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();			
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setCurrentDay(currentDay);
		String prodNm = productDevMgt.getProdNm();
		System.out.println("prodNm=========>"+prodNm);
		List<ProductDevMgt> resultList = productDevMgtService.getProductRelationshipListAll(productDevMgt, soAuthList);
		
		model.addAttribute("rows", resultList);
	}	
	
	@RequestMapping(value = "getProductdefultRelationsList_res", method = RequestMethod.POST)	
	public void getProductdefultRelationsList_res(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
			
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setCurrentDay(currentDay);
		
		List<ProductDevMgt> resultList = productDevMgtService.getProductdefultRelationsList_res(productDevMgt);
		
		model.addAttribute("rows", resultList);
	}		
	
	@RequestMapping(value = "getProductdefultRelationsList_req", method = RequestMethod.POST)	
	public void getProductdefultRelationsList_req(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
			
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setCurrentDay(currentDay);
		
		List<ProductDevMgt> resultList = productDevMgtService.getProductdefultRelationsList_req(productDevMgt);
		
		model.addAttribute("rows", resultList);
	}		
	
	
	@RequestMapping(value = "getProdect", method = RequestMethod.POST)		
	public void getProdect(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
				
		productDevMgt.setCurrentDay(currentDay);
		
		ProductDevMgt result = productDevMgtService.getProdect(productDevMgt);
		
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "productdefultRelationsListPopup", method = RequestMethod.POST)
	public String productdefultRelationsListPopup (Model model
			, HttpServletRequest request
			, ProductDevMgt productDevMgt  
			) throws Exception {
		
		
		String reltyp = "PP00067";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("reltyp", commonDataService.getCommonCodeList(reltyp, lngTyp));	
		model.addAttribute("productDevMgt", productDevMgt);	
		
		
		return  URL + "/ajax/productdefultRelationsListPopup";
	}		
	
	@RequestMapping(value = "getProductRelationshipListAll_in", method = RequestMethod.POST)	
	public void getProductRelationshipListAll_in(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
			
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setCurrentDay(currentDay);
		
		List<ProductDevMgt> resultList = null; 
		
		if (productDevMgt.getBasicProdFl() != null) {
			if (productDevMgt.getProdCase().equals("1")){
				if (productDevMgt.getBasicProdFl().equals("B")) {
					resultList = productDevMgtService.getProductRelationshipListAll_prod_case_1(productDevMgt);
				}
				if (productDevMgt.getBasicProdFl().equals("E") || productDevMgt.getBasicProdFl().equals("V")) {
					if (productDevMgt.getProdRelTyp().equals("P")) {
						productDevMgt.setBasicProdFl("B");
						resultList = productDevMgtService.getProductRelationshipListAll_prod_case_1(productDevMgt);
					}
					if (productDevMgt.getProdRelTyp().equals("B") || productDevMgt.getProdRelTyp().equals("X")) {
						productDevMgt.setBasicProdFl("V");
						productDevMgt.setBasicProdFl_1("E");
						resultList = productDevMgtService.getProductRelationshipListAll_prod_case_1(productDevMgt);
					}					
				}
				if (productDevMgt.getBasicProdFl().equals("P")) {
					productDevMgt.setBasicProdFl("B");
					resultList = productDevMgtService.getProductRelationshipListAll_prod_case_1(productDevMgt);
				}				
			}
			
			if (productDevMgt.getProdCase().equals("2")){
				if (productDevMgt.getBasicProdFl().equals("B")) {
					if (productDevMgt.getProdRelTyp().equals("M") || productDevMgt.getProdRelTyp().equals("C")) {
						productDevMgt.setBasicProdFl("B");
						resultList = productDevMgtService.getProductRelationshipListAll_prod_case_2(productDevMgt);
					}
					if (productDevMgt.getProdRelTyp().equals("P")) {
						productDevMgt.setBasicProdFl("V");
						productDevMgt.setBasicProdFl_1("E");
						resultList = productDevMgtService.getProductRelationshipListAll_prod_case_2(productDevMgt);
					}	
					if (productDevMgt.getProdRelTyp().equals("S")) {
						productDevMgt.setBasicProdFl("P");
						resultList = productDevMgtService.getProductRelationshipListAll_prod_case_2(productDevMgt);
					}	
				}
				if (productDevMgt.getBasicProdFl().equals("V") || productDevMgt.getBasicProdFl().equals("E")) {
					productDevMgt.setBasicProdFl("V");
					productDevMgt.setBasicProdFl_1("E");
					resultList = productDevMgtService.getProductRelationshipListAll_prod_case_2(productDevMgt);
				}
			}
		}
		
		model.addAttribute("rows", resultList);
	}		
	
	@RequestMapping(value = "setProductRelationship", method = RequestMethod.POST)
	public void setProductRelationship (
			@RequestBody List<ProductDevMgt> params,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
				
		String result = productDevMgtService.setProductRelationship(params, sessionUser);
		
		model.addAttribute("result", result);
	}		
	
	
	@RequestMapping(value = "productCancelPopup", method = RequestMethod.POST)
	public String productCancelPopup (Model model
			, HttpServletRequest request
			, ProductDevMgt productDevMgt  
			) throws Exception {
		
		return  URL + "/ajax/productCancelPopup";
	}		
	
	@RequestMapping(value = "setProductCancel", method = RequestMethod.POST)
	public void setProductCancel (
			@RequestBody List<ProductDevMgt> params,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
				
		String result = productDevMgtService.setProductCancel(params);
		
		model.addAttribute("result", result);
	}		
	
}
