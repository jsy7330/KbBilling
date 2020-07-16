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
import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.DeliveryConfirmationVO;
import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.OrderPlacementVO;
import com.ntels.ccbs.distribute.service.logistics.orderDeliveryMgt.OrderPlacementService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/orderDeliveryMgt/deliveryConfirmation")
public class DeliveryConfirmationController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private OrderPlacementService orderPlacementService;
	
	private String URL = "distributor/logistics/orderDeliveryMgt/deliveryConfirmation";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deliveryConfirmation
	 * 2. ClassName : DeliveryConfirmationController
	 * 3. Comment   : 제조사납품확인 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 16. 오전 9:44:06
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param deliveryConfirmationVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "deliveryConfirmation", method = RequestMethod.POST)
	public String deliveryConfirmation(Model model, DeliveryConfirmationVO deliveryConfirmationVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("poPrgrStatCd", commonDataService.getCommonCodeList("DN00070", lngTyp));	//발주진행상태
		
		return URL + "/deliveryConfirmation";
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
	 * 1. MethodName: insertActnc
	 * 2. ClassName : DeliveryConfirmationController
	 * 3. Comment   : 제조사 납품 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 16. 오후 4:22:18
	 * </PRE>
	 *   @return void
	 *   @param orderPlacementVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertActnc", method = RequestMethod.POST)
	public void insertActnc(
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
		
		int result = orderPlacementService.insertActnc(orderPlacementVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertActncAction
	 * 2. ClassName : DeliveryConfirmationController
	 * 3. Comment   : 납품내역 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 16. 오후 4:23:03
	 * </PRE>
	 *   @return void
	 *   @param orderPlacementVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertActncAction", method = RequestMethod.POST)
	public void insertActncAction(
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
		count = orderPlacementService.actncCount(orderPlacementVO);		
		if (count > 0) list = orderPlacementService.actncList(orderPlacementVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deliveryConfirmationInsertPopUp
	 * 2. ClassName : DeliveryConfirmationController
	 * 3. Comment   : 제조사납품등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 16. 오후 4:28:23
	 * </PRE>
	 *   @return String
	 *   @param orderPlacementVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "deliveryConfirmationInsertPopUp", method = RequestMethod.POST)
	public String deliveryConfirmationInsertPopUp(
			OrderPlacementVO orderPlacementVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("orderPlacementVO", orderPlacementVO);

		return URL + "/popup/deliveryConfirmationInsertPopUp";
		
	}
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertActncIdlDtl
	 * 2. ClassName : DeliveryConfirmationController
	 * 3. Comment   : 납품확인
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 30. 오전 10:54:52
	 * </PRE>
	 *   @return void
	 *   @param orderPlacementVOs
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertActncIdlDtl", method = RequestMethod.POST)
	public void insertActncIdlDtl(
			@RequestBody List<OrderPlacementVO> orderPlacementVOs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = orderPlacementService.insertActncIdlDtl(orderPlacementVOs, session);
		
		model.addAttribute("result", result);
		
	}
}
