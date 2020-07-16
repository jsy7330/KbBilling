package com.ntels.ccbs.distribute.controller.logistics.inventoryMoveChangeMng;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.distribute.domain.logistics.inventoryMoveChangeMng.ContactProductVO;
import com.ntels.ccbs.distribute.domain.logistics.inventoryMoveChangeMng.ProductStatusVO;
import com.ntels.ccbs.distribute.service.logistics.inventoryMoveChangeMng.ProductStatusService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/inventoryMoveChangeMng/productStatus")
public class ProductStatusController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ProductStatusService productStatusService;
	
	
	private String URL = "distributor/logistics/inventoryMoveChangeMng/productStatus";
	
	
	@RequestMapping(value = "productStatus", method = RequestMethod.POST)
	public String productStatus(Model model, ProductStatusVO productStatusVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("eqtStatCd", commonDataService.getCommonCodeListByRef1("DN00066", "C", lngTyp));		//단말기 상태코드(단말기)
		
		return URL + "/productStatus";
	}
	
	
	@RequestMapping(value = "eqtStatListAction", method = RequestMethod.POST)
	public void eqtStatListAction(
			ProductStatusVO productStatusVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productStatusVO.setLngTyp(lngTyp);
		productStatusVO.setSidx(sidx);
		productStatusVO.setSord(sord);
		
        List<ProductStatusVO> list = new ArrayList<ProductStatusVO>();
		int count = 0;
		count = productStatusService.eqtStatCount(productStatusVO);
		if (count > 0) list = productStatusService.eqtStatList(productStatusVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		
	}
	
	
	@RequestMapping(value = "productStatusInsertPopUp", method = RequestMethod.POST)
	public String productStatusInsertPopUp(
			ProductStatusVO productStatusVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("eqtStatCd", commonDataService.getCommonCodeListByRef1("DN00066", "C", lngTyp));		//단말기 상태코드(단말기)
		
		
		model.addAttribute("productStatusVO", productStatusVO);

		return URL + "/popup/productStatusInsertPopUp";
		
	}
	
	
	
	@RequestMapping(value = "eqtStatInfoListAction", method = RequestMethod.POST)
	public void eqtStatInfoListAction(
			ProductStatusVO productStatusVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productStatusVO.setLngTyp(lngTyp);
		productStatusVO.setSidx(sidx);
		productStatusVO.setSord(sord);
		
        List<ProductStatusVO> list  = productStatusService.eqtStatInfoList(productStatusVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)list.size() / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	
	@RequestMapping(value = "updateEqtStat", method = RequestMethod.POST)
	public void updateEqtStat(
			@RequestBody List<ProductStatusVO> productStatusVOs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = productStatusService.updateEqtStat(productStatusVOs, session);
		
		model.addAttribute("result", result);
		
	}
}
