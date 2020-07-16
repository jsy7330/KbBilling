package com.ntels.ccbs.distribute.controller.logistics.referenceInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.ProductInfoVO;
import com.ntels.ccbs.distribute.service.logistics.referenceInfo.ProductInfoService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/referenceInfo/productInfo")
public class ProductInfoController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ProductInfoService productInfoService;
	
	private String URL = "distributor/logistics/referenceInfo/productInfo";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: productInfo
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 25. 오후 4:18:18
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param productInfoVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "productInfo", method = RequestMethod.POST)
	public String productInfo(Model model, ProductInfoVO productInfoVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("itemKndCd", commonDataService.getCommonCodeList("DN00064", lngTyp));	//제품유형코드
		model.addAttribute("ctrtTpCd", commonDataService.getCommonCodeList("DN00060", lngTyp));		//계약유형
		
		return URL + "/productInfo";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: productInfoListAction
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 25. 오후 4:19:09
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "productInfoListAction", method = RequestMethod.POST)
	public void productInfoListAction(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productInfoVO.setLngTyp(lngTyp);
		productInfoVO.setSidx(sidx);
		productInfoVO.setSord(sord);
		
        List<ProductInfoVO> list = new ArrayList<ProductInfoVO>();
		int count = 0;
		count = productInfoService.productInfoCount(productInfoVO);		
		if (count > 0) list = productInfoService.productInfoList(productInfoVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: checkItemId
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 아이디 중복 체크
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 26. 오전 9:09:08
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "checkItemId", method = RequestMethod.POST)
	public void checkItemId(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int idCount = productInfoService.checkItemId(productInfoVO);
		
		model.addAttribute("idCount", idCount);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertProductInfo
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 26. 오후 1:46:23
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertProductInfo", method = RequestMethod.POST)
	public void insertProductInfo(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.insertProductInfo(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateProductInfo
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 26. 오후 1:47:47
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateProductInfo", method = RequestMethod.POST)
	public void updateProductInfo(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.updateProductInfo(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteProductInfo
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 26. 오후 1:47:59
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteProductInfo", method = RequestMethod.POST)
	public void deleteProductInfo(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int result = productInfoService.deleteProductInfo(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
		
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: proPertyMngPopUp
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성관리 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오전 10:28:45
	 * </PRE>
	 *   @return String
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "proPertyMngPopUp", method = RequestMethod.POST)
	public String proPertyMngPopUp(
			ProductInfoVO productInfoVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("productInfoVO", productInfoVO);

		return URL + "/popup/proPertyMngPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: itemAttrListAction
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품속성 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오전 10:30:30
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "itemAttrListAction", method = RequestMethod.POST)
	public void itemAttrListAction(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productInfoVO.setLngTyp(lngTyp);
		productInfoVO.setSidx(sidx);
		productInfoVO.setSord(sord);
		
        List<ProductInfoVO> list = new ArrayList<ProductInfoVO>();
		int count = 0;
		count = productInfoService.itemAttrCount(productInfoVO);
		if (count > 0) list = productInfoService.itemAttrList(productInfoVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: checkAttrCd
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품속성 중복 체크
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오후 1:44:33
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "checkAttrCd", method = RequestMethod.POST)
	public void checkAttrCd(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int idCount = productInfoService.checkAttrCd(productInfoVO);
		
		model.addAttribute("idCount", idCount);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertItemAttr
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성키 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오후 2:40:16
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertItemAttr", method = RequestMethod.POST)
	public void insertItemAttr(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.insertItemAttr(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateItemAttr
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성키 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오후 2:41:09
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateItemAttr", method = RequestMethod.POST)
	public void updateItemAttr(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.updateItemAttr(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteItemAttr
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성키 삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오후 2:41:31
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteItemAttr", method = RequestMethod.POST)
	public void deleteItemAttr(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int result = productInfoService.deleteItemAttr(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: itemAttrValListAction
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품 속성 값 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오후 1:43:32
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "itemAttrValListAction", method = RequestMethod.POST)
	public void itemAttrValListAction(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productInfoVO.setLngTyp(lngTyp);
		productInfoVO.setSidx(sidx);
		productInfoVO.setSord(sord);
		
        List<ProductInfoVO> list = new ArrayList<ProductInfoVO>();
		int count = 0;
		count = productInfoService.itemAttrValCount(productInfoVO);
		if (count > 0) list = productInfoService.itemAttrValList(productInfoVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: checkAttrValCd
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품속성값 중복 체크
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오후 1:51:26
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "checkAttrValCd", method = RequestMethod.POST)
	public void checkAttrValCd(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int idCount = productInfoService.checkAttrValCd(productInfoVO);
		
		model.addAttribute("idCount", idCount);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertItemAttrVal
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성값 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오후 2:45:48
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertItemAttrVal", method = RequestMethod.POST)
	public void insertItemAttrVal(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.insertItemAttrVal(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateItemAttrVal
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성값 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오후 2:46:29
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateItemAttrVal", method = RequestMethod.POST)
	public void updateItemAttrVal(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.updateItemAttrVal(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteItemAttrVal
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성값 삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 27. 오후 2:46:52
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteItemAttrVal", method = RequestMethod.POST)
	public void deleteItemAttrVal(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int result = productInfoService.deleteItemAttrVal(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: itemAttrMappListAction
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제푹 속성 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오전 10:37:01
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "itemAttrMappListAction", method = RequestMethod.POST)
	public void itemAttrMappListAction(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productInfoVO.setLngTyp(lngTyp);
		productInfoVO.setSidx(sidx);
		productInfoVO.setSord(sord);
		
        List<ProductInfoVO> list = new ArrayList<ProductInfoVO>();
		int count = 0;
		count = productInfoService.itemAttrMappCount(productInfoVO);
		if (count > 0) list = productInfoService.itemAttrMappList(productInfoVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: itemAttrSelectListAction
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성키 리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오후 1:21:48
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "itemAttrSelectListAction", method = RequestMethod.POST)
	public void itemAttrSelectListAction(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productInfoVO.setLngTyp(lngTyp);
		
		model.addAttribute("itemAttrList", productInfoService.itemAttrSelectList(productInfoVO));

	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: itemAttrValSelectListAction
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성값 리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오후 1:22:03
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "itemAttrValSelectListAction", method = RequestMethod.POST)
	public void itemAttrValSelectListAction(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productInfoVO.setLngTyp(lngTyp);
		
		
		model.addAttribute("itemAttrValList", productInfoService.itemAttrValSelectList(productInfoVO));

	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertItemAttrMapp
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성키 매핑 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오후 1:23:04
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertItemAttrMapp", method = RequestMethod.POST)
	public void insertItemAttrMapp(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.insertItemAttrMapp(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateItemAttrMapp
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성키 매핑 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오후 1:23:35
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateItemAttrMapp", method = RequestMethod.POST)
	public void updateItemAttrMapp(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.updateItemAttrMapp(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteItemAttrMapp
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 속성키 매핑 삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오후 1:24:04
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteItemAttrMapp", method = RequestMethod.POST)
	public void deleteItemAttrMapp(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int result = productInfoService.deleteItemAttrMapp(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: itemDtlListAction
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품 상세 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오후 3:34:02
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "itemDtlListAction", method = RequestMethod.POST)
	public void itemDtlListAction(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productInfoVO.setLngTyp(lngTyp);
		productInfoVO.setSidx(sidx);
		productInfoVO.setSord(sord);
		
        List<ProductInfoVO> list = new ArrayList<ProductInfoVO>();
		int count = 0;
		count = productInfoService.itemDtlCount(productInfoVO);
		if (count > 0) list = productInfoService.itemDtlList(productInfoVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertItemDtl
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품상세 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오후 3:35:24
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertItemDtl", method = RequestMethod.POST)
	public void insertItemDtl(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.insertItemDtl(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateItemDtl
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품 상세 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오후 3:35:36
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateItemDtl", method = RequestMethod.POST)
	public void updateItemDtl(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productInfoVO.setRegrId(session.getUserId());
		productInfoVO.setChgrId(session.getUserId());
		productInfoVO.setRegDate(DateUtil.sysdate());
		productInfoVO.setChgDate(DateUtil.sysdate());
		productInfoVO.setLngTyp(lngTyp);
		
		int result = productInfoService.updateItemDtl(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteItemDtl
	 * 2. ClassName : ProductInfoController
	 * 3. Comment   : 제품 상세 삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 30. 오후 3:35:47
	 * </PRE>
	 *   @return void
	 *   @param productInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteItemDtl", method = RequestMethod.POST)
	public void deleteItemDtl(
			ProductInfoVO productInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int result = productInfoService.deleteItemDtl(productInfoVO);
		
		model.addAttribute("result", result);
		
	}
	
	
	@RequestMapping(value = "getCommonCodeListAction", method = RequestMethod.POST)
	public void getCommonCodeListAction(String code, Model model,
			HttpServletRequest request) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage")
				.toString();

		model.addAttribute("itemKndCd", commonDataService.getCommonCodeList(code, lngTyp));	//제품유형코드

	}
}
