package com.ntels.ccbs.distribute.controller.logistics.referenceInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.PurchaseUnitPriceVO;
import com.ntels.ccbs.distribute.service.logistics.referenceInfo.PurchaseUnitPriceService;
import com.ntels.ccbs.system.controller.login.LoginController;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/referenceInfo/purchaseUnitPrice")
public class PurchaseUnitPriceController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private PurchaseUnitPriceService purchaseUnitPriceService;
	
	
	private String URL = "distributor/logistics/referenceInfo/purchaseUnitPrice";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: purchaseUnitPrice
	 * 2. ClassName : PurchaseUnitPriceController
	 * 3. Comment   : 매입단가관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 31. 오후 1:08:41
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param purchaseUnitPriceVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "purchaseUnitPrice", method = RequestMethod.POST)
	public String purchaseUnitPrice(Model model, PurchaseUnitPriceVO purchaseUnitPriceVO, HttpServletRequest request) throws Exception {
		
		String userGrp = (String)request.getSession().getAttribute("orgId");
		logger.info("==>> user group : " + userGrp);
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		
		return URL + "/purchaseUnitPrice";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: purchaseUnitPriceListAction
	 * 2. ClassName : PurchaseUnitPriceController
	 * 3. Comment   : 제품 리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 31. 오후 1:08:56
	 * </PRE>
	 *   @return void
	 *   @param purchaseUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "purchaseUnitPriceListAction", method = RequestMethod.POST)
	public void purchaseUnitPriceListAction(
			PurchaseUnitPriceVO purchaseUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		purchaseUnitPriceVO.setLngTyp(lngTyp);
		purchaseUnitPriceVO.setSidx(sidx);
		purchaseUnitPriceVO.setSord(sord);
		
        List<PurchaseUnitPriceVO> list = new ArrayList<PurchaseUnitPriceVO>();
		int count = 0;
		count = purchaseUnitPriceService.purchaseUnitPriceCount(purchaseUnitPriceVO);		
		if (count > 0) list = purchaseUnitPriceService.purchaseUnitPriceList(purchaseUnitPriceVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: mncoUtPrcDtlListAction
	 * 2. ClassName : PurchaseUnitPriceController
	 * 3. Comment   : 매입단가이력 리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 31. 오후 1:09:59
	 * </PRE>
	 *   @return void
	 *   @param purchaseUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "mncoUtPrcDtlListAction", method = RequestMethod.POST)
	public void mncoUtPrcDtlListAction(
			PurchaseUnitPriceVO purchaseUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		purchaseUnitPriceVO.setLngTyp(lngTyp);
		purchaseUnitPriceVO.setSidx(sidx);
		purchaseUnitPriceVO.setSord(sord);
		
        List<PurchaseUnitPriceVO> list = new ArrayList<PurchaseUnitPriceVO>();
		int count = 0;
		count = purchaseUnitPriceService.mncoUtPrcDtlCount(purchaseUnitPriceVO);		
		if (count > 0) list = purchaseUnitPriceService.mncoUtPrcDtlList(purchaseUnitPriceVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: proPertyMngPopUp
	 * 2. ClassName : PurchaseUnitPriceController
	 * 3. Comment   : 매입단가이력 등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 31. 오후 4:22:24
	 * </PRE>
	 *   @return String
	 *   @param purchaseUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "mncoUtPrcDtlInsertPopUp", method = RequestMethod.POST)
	public String mncoUtPrcDtlInsertPopUp(
			PurchaseUnitPriceVO purchaseUnitPriceVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		model.addAttribute("purchaseUnitPriceVO", purchaseUnitPriceVO);

		return URL + "/popup/mncoUtPrcDtlInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertProductInfo
	 * 2. ClassName : PurchaseUnitPriceController
	 * 3. Comment   : 매입단가이력 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 31. 오후 4:22:45
	 * </PRE>
	 *   @return void
	 *   @param purchaseUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertMncoUtPrcDtl", method = RequestMethod.POST)
	public void insertProductInfo(
			PurchaseUnitPriceVO purchaseUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		purchaseUnitPriceVO.setRegrId(session.getUserId());
		purchaseUnitPriceVO.setChgrId(session.getUserId());
		purchaseUnitPriceVO.setRegDate(DateUtil.sysdate());
		purchaseUnitPriceVO.setChgDate(DateUtil.sysdate());
		purchaseUnitPriceVO.setLngTyp(lngTyp);
		
		int result = purchaseUnitPriceService.insertMncoUtPrcDtl(purchaseUnitPriceVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: mncoUtPrcDtlUpdatePopUp
	 * 2. ClassName : PurchaseUnitPriceController
	 * 3. Comment   : 매입단가 수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 1:46:55
	 * </PRE>
	 *   @return String
	 *   @param purchaseUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "mncoUtPrcDtlUpdatePopUp", method = RequestMethod.POST)
	public String mncoUtPrcDtlUpdatePopUp(
			PurchaseUnitPriceVO purchaseUnitPriceVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		model.addAttribute("purchaseUnitPriceVO", purchaseUnitPriceVO);

		return URL + "/popup/mncoUtPrcDtlUpdatePopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateMncoUtPrcDtl
	 * 2. ClassName : PurchaseUnitPriceController
	 * 3. Comment   : 매입단가 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 1:47:06
	 * </PRE>
	 *   @return void
	 *   @param purchaseUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateMncoUtPrcDtl", method = RequestMethod.POST)
	public void updateMncoUtPrcDtl(
			PurchaseUnitPriceVO purchaseUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		purchaseUnitPriceVO.setRegrId(session.getUserId());
		purchaseUnitPriceVO.setChgrId(session.getUserId());
		purchaseUnitPriceVO.setRegDate(DateUtil.sysdate());
		purchaseUnitPriceVO.setChgDate(DateUtil.sysdate());
		purchaseUnitPriceVO.setLngTyp(lngTyp);
		
		int result = purchaseUnitPriceService.updateMncoUtPrcDtl(purchaseUnitPriceVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteMncoUtPrcDtl
	 * 2. ClassName : PurchaseUnitPriceController
	 * 3. Comment   : 매입단가 삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 1:47:32
	 * </PRE>
	 *   @return void
	 *   @param purchaseUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteMncoUtPrcDtl", method = RequestMethod.POST)
	public void deleteMncoUtPrcDtl(
			PurchaseUnitPriceVO purchaseUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		
		int result = purchaseUnitPriceService.deleteMncoUtPrcDtl(purchaseUnitPriceVO);
		
		model.addAttribute("result", result);
		
	}
	
	
	@RequestMapping(value = "priceChangeHisthPopUp", method = RequestMethod.POST)
	public String priceChangeHisthPopUp(
			PurchaseUnitPriceVO purchaseUnitPriceVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("purchaseUnitPriceVO", purchaseUnitPriceVO);

		return URL + "/popup/priceChangeHisthPopUp";
		
	}
}
