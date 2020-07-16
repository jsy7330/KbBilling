package com.ntels.ccbs.system.controller.common.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.common.common.ProductMngVO;
import com.ntels.ccbs.system.service.common.common.ProductMngService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/system/common/common/productMng")
public class ProductMngController {

	private static String URL = "system/common/common/productMng";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ProductMngService productMngService;
	
	@RequestMapping(value = "/productSearchPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String productSearchPopup(
			ProductMngVO productMngVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("productMngVO", productMngVO);
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		
		if(productMngVO.getPopType().equals("m")){
			return URL + "/mpopup/productSearchPopup";
		}else{
			return URL + "/opopup/productSearchPopup";
		}
			
	}
	
	@RequestMapping(value = "productListAction", method = RequestMethod.POST)
	public void productListAction(
			ProductMngVO productMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productMngVO.setLngTyp(lngTyp);
		productMngVO.setSidx(sidx);
		productMngVO.setSord(sord);
		
        List<ProductMngVO> list = new ArrayList<ProductMngVO>();
		int count = 0;
		count = productMngService.productCount(productMngVO);	
		if (count > 0) list = productMngService.productList(productMngVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
}
