package com.ntels.ccbs.product.controller.service.serviceMgt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.service.serviceMgt.ProductWorkMngVO;
import com.ntels.ccbs.product.service.service.serviceMgt.ProductWorkMngService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/product/service/serviceMgt/service")
public class ProductWorkMngController {
	
	private static String URL = "product/service/serviceMgt/manageWorkProduct";
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductWorkMngService productWorkMngService;
		
	@Autowired
	private CommonDataService commonDataService;
	
	@RequestMapping(value = "productWorkMng", method = RequestMethod.POST)
	public String productWorkMng(Model model,HttpServletRequest request) {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("userSoId", sessionUser.getSoId());	
		model.addAttribute("ctrtTyp", commonDataService.getCommonCodeList("CM00022", lngTyp));	//청약유형	
		model.addAttribute("prodGrp", commonDataService.getCommonCodeList("PP00141", lngTyp));	//상품군		
		model.addAttribute("workTyp", commonDataService.getCommonCodeList("CM00037", lngTyp));	//작업유형	

		
		return URL + "/productWorkMng";
	}
	@RequestMapping(value = "getProdListAction", method = RequestMethod.POST)
	public void getProdListAction(String soId, String prodGrp, Model model,
			HttpServletRequest request) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage")
				.toString();
		
		List<ProductWorkMngVO> prodList = productWorkMngService.getProdListAction(soId, prodGrp, lngTyp);

		model.addAttribute("prodList", prodList);

	}
	@RequestMapping(value = "prodWorkListAction", method = RequestMethod.POST)
	public void prodWorkListAction(
			ProductWorkMngVO productWorkMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productWorkMngVO.setLngTyp(lngTyp);
		productWorkMngVO.setSidx(sidx);
		productWorkMngVO.setSord(sord);
		
        List<ProductWorkMngVO> list = new ArrayList<ProductWorkMngVO>();
		int count = 0;
		count = productWorkMngService.prodWorkListCount(productWorkMngVO);		
		if (count > 0) list = productWorkMngService.prodWorkListAction(productWorkMngVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}	
	@RequestMapping(value = "getWrkDefList", method = RequestMethod.POST)
	public void getWrkDefList(String wrkTp, Model model,
			HttpServletRequest request) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage")
				.toString();
		
		List<ProductWorkMngVO> wrkDefList = productWorkMngService.getWrkDefList(wrkTp);

		model.addAttribute("wrkDefList", wrkDefList);

	}	

	
	
	@RequestMapping(value = "openWrkDefSearchAction", method = RequestMethod.POST)
	public void openWrkDefSearchAction(
			ProductWorkMngVO productWorkMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productWorkMngVO.setLngTyp(lngTyp);
		productWorkMngVO.setSidx(sidx);
		productWorkMngVO.setSord(sord);
		
        List<ProductWorkMngVO> list = new ArrayList<ProductWorkMngVO>();
		int count = 0;
		count = productWorkMngService.openWrkDefSearchActionCnt(productWorkMngVO);		
		if (count > 0) list = productWorkMngService.openWrkDefSearchAction(productWorkMngVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "openWrkDefSearchPopup", method = RequestMethod.POST)
	public String openWrkDefSearchPopup(
			ProductWorkMngVO productWorkMngVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
			
		return  URL + "/ajax/openWrkDefSearchPopup";
	}	
	@RequestMapping(value = "insertProdWrk", method = RequestMethod.POST)
	public void insertProdWrk(
			ProductWorkMngVO productWorkMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productWorkMngVO.setRegrId(session.getUserId());
		productWorkMngVO.setChgrId(session.getUserId());
		productWorkMngVO.setRegDate(DateUtil.sysdate());
		productWorkMngVO.setChgDate(DateUtil.sysdate());
		productWorkMngVO.setLngTyp(lngTyp);	

		String wrkSeqNo = productWorkMngService.getWrkSeqNo(productWorkMngVO);
		productWorkMngVO.setWrkSeqNo(wrkSeqNo);
		
		int result = productWorkMngService.insertProdWrk(productWorkMngVO);		
		model.addAttribute("result", result);
		
	}		
	@RequestMapping(value = "updateProdWrk", method = RequestMethod.POST)
	public void updateProdWrk(
			ProductWorkMngVO productWorkMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productWorkMngVO.setRegrId(session.getUserId());
		productWorkMngVO.setChgrId(session.getUserId());
		productWorkMngVO.setRegDate(DateUtil.sysdate());
		productWorkMngVO.setChgDate(DateUtil.sysdate());
		productWorkMngVO.setLngTyp(lngTyp);	

		//String wrkSeqNo = productWorkMngService.getWrkSeqNo(productWorkMngVO);
		//productWorkMngVO.setWrkSeqNo(wrkSeqNo);
		
		int result = productWorkMngService.updateProdWrk(productWorkMngVO);		
		model.addAttribute("result", result);
		
	}	
	@RequestMapping(value = "deleteProdWrk", method = RequestMethod.POST)
	public void deleteProdWrk(
			ProductWorkMngVO productWorkMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
	
		int result = productWorkMngService.deleteProdWrk(productWorkMngVO);		
		model.addAttribute("result", result);
		
	}		
	
}
