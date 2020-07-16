package com.ntels.ccbs.distribute.controller.logistics.orderDeliveryMgt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.OrderPlacementVO;
import com.ntels.ccbs.distribute.service.logistics.orderDeliveryMgt.OrderPlacementService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/orderDeliveryMgt/orderPlacement")
public class OrderPlacementController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private OrderPlacementService orderPlacementService;
	
	private String URL = "distributor/logistics/orderDeliveryMgt/orderPlacement";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: orderPlacement
	 * 2. ClassName : OrderPlacementController
	 * 3. Comment   : 제조사발주관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 7. 오후 3:01:36
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param orderPlacementVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "orderPlacement", method = RequestMethod.POST)
	public String orderPlacement(Model model, OrderPlacementVO orderPlacementVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("poPrgrStatCd", commonDataService.getCommonCodeList("DN00070", lngTyp));	//발주진행상태
		
		return URL + "/orderPlacement";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: orderPlacementListAction
	 * 2. ClassName : OrderPlacementController
	 * 3. Comment   : 제조사발주관리 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 7. 오후 3:01:55
	 * </PRE>
	 *   @return void
	 *   @param orderPlacementVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "orderPlacementListAction", method = RequestMethod.POST)
	public void orderPlacementListAction(
			OrderPlacementVO orderPlacementVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		orderPlacementVO.setLngTyp(lngTyp);
		orderPlacementVO.setSidx(sidx);
		orderPlacementVO.setSord(sord);
		
        List<OrderPlacementVO> list = new ArrayList<OrderPlacementVO>();
		int count = 0;
		count = orderPlacementService.orderPlacementCount(orderPlacementVO);		
		if (count > 0) list = orderPlacementService.orderPlacementList(orderPlacementVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: orderPlacementInsertPopUp
	 * 2. ClassName : OrderPlacementController
	 * 3. Comment   : 제조사발주관리 등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 9. 오전 10:30:33
	 * </PRE>
	 *   @return String
	 *   @param orderPlacementVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "orderPlacementInsertPopUp", method = RequestMethod.POST)
	public String orderPlacementInsertPopUp(
			OrderPlacementVO orderPlacementVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("colorCd", commonDataService.getCommonCodeList("DN00078", lngTyp));		//제품유형코드
		model.addAttribute("orderPlacementVO", orderPlacementVO);

		return URL + "/popup/orderPlacementInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertOrderPlacement
	 * 2. ClassName : OrderPlacementController
	 * 3. Comment   : 발주 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 9. 오후 1:22:48
	 * </PRE>
	 *   @return void
	 *   @param orderPlacementVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertOrderPlacement", method = RequestMethod.POST)
	public void insertOrderPlacement(
			OrderPlacementVO orderPlacementVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		orderPlacementVO.setRegrId(session.getUserId());
		orderPlacementVO.setChgrId(session.getUserId());
		orderPlacementVO.setRegDate(DateUtil.sysdate());
		orderPlacementVO.setChgDate(DateUtil.sysdate());
		orderPlacementVO.setLngTyp(lngTyp);
		
		int result = orderPlacementService.insertOrderPlacement(orderPlacementVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: orderPlacementUpdatePopUp
	 * 2. ClassName : OrderPlacementController
	 * 3. Comment   : 제조사발주관리 수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 14. 오전 10:24:33
	 * </PRE>
	 *   @return String
	 *   @param orderPlacementVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "orderPlacementUpdatePopUp", method = RequestMethod.POST)
	public String orderPlacementUpdatePopUp(
			OrderPlacementVO orderPlacementVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("colorCd", commonDataService.getCommonCodeList("DN00078", lngTyp));		//제품유형코드
		model.addAttribute("orderPlacementVO", orderPlacementVO);

		return URL + "/popup/orderPlacementUpdatePopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateOrderPlacement
	 * 2. ClassName : OrderPlacementController
	 * 3. Comment   : 발주 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 14. 오전 10:21:36
	 * </PRE>
	 *   @return void
	 *   @param orderPlacementVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateOrderPlacement", method = RequestMethod.POST)
	public void updateOrderPlacement(
			OrderPlacementVO orderPlacementVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		orderPlacementVO.setRegrId(session.getUserId());
		orderPlacementVO.setChgrId(session.getUserId());
		orderPlacementVO.setRegDate(DateUtil.sysdate());
		orderPlacementVO.setChgDate(DateUtil.sysdate());
		orderPlacementVO.setLngTyp(lngTyp);
		
		int result = orderPlacementService.updateOrderPlacement(orderPlacementVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updatePoPrgrStatCd
	 * 2. ClassName : OrderPlacementController
	 * 3. Comment   : 발주 등록/취소
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 14. 오후 3:58:12
	 * </PRE>
	 *   @return void
	 *   @param orderPlacementVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updatePoPrgrStatCd", method = RequestMethod.POST)
	public void updatePoPrgrStatCd(
			@RequestBody List<OrderPlacementVO> orderPlacementVOs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = orderPlacementService.updatePoPrgrStatCd(orderPlacementVOs, session);
		
		model.addAttribute("result", result);
		
	}
	
	
	@RequestMapping(value = "getTaxRate", method = RequestMethod.POST)
	public void getTaxRate(
	      Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		String result = orderPlacementService.getTaxRate("");
		
		model.addAttribute("result", result);
		
	}
	
}

