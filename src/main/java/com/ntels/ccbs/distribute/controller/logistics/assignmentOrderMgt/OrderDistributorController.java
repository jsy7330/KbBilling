package com.ntels.ccbs.distribute.controller.logistics.assignmentOrderMgt;

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
import com.ntels.ccbs.distribute.domain.logistics.assignmentOrderMgt.OrderDistributorVO;
import com.ntels.ccbs.distribute.service.logistics.assignmentOrderMgt.OrderDistributorService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/assignmentOrderMgt/orderDistributor")
public class OrderDistributorController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private OrderDistributorService orderDistributorService;
	
	private String URL = "distributor/logistics/assignmentOrderMgt/orderDistributor";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: orderDistributor
	 * 2. ClassName : OrderDistributorController
	 * 3. Comment   : 유통점주문관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 15. 오후 2:33:14
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param orderDistributorVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "orderDistributor", method = RequestMethod.POST)
	public String orderDistributor(Model model, OrderDistributorVO orderDistributorVO, HttpServletRequest request) throws Exception {
		
		//String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		
		orderDistributorVO.setSoId(session.getSoId());
		orderDistributorVO.setOrgId(session.getOrgId());
		
		model.addAttribute("orderMngOrgList", orderDistributorService.orderMngOrgList(orderDistributorVO));	
		
		return URL + "/orderDistributor";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: asgnList
	 * 2. ClassName : OrderDistributorController
	 * 3. Comment   : 할당내역
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 18. 오후 1:36:55
	 * </PRE>
	 *   @return void
	 *   @param orderDistributorVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "asgnList", method = RequestMethod.POST)
	public void asgnList(
			OrderDistributorVO orderDistributorVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		orderDistributorVO.setLngTyp(lngTyp);
		orderDistributorVO.setSidx(sidx);
		orderDistributorVO.setSord(sord);
		
        List<OrderDistributorVO> list = new ArrayList<OrderDistributorVO>();
		int count = 0;
		count = orderDistributorService.asgnCount(orderDistributorVO);	
		if (count > 0) list = orderDistributorService.asgnList(orderDistributorVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: ordList
	 * 2. ClassName : OrderDistributorController
	 * 3. Comment   : 주문목록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 18. 오후 1:37:15
	 * </PRE>
	 *   @return void
	 *   @param orderDistributorVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "ordList", method = RequestMethod.POST)
	public void ordList(
			OrderDistributorVO orderDistributorVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		orderDistributorVO.setLngTyp(lngTyp);
		orderDistributorVO.setSidx(sidx);
		orderDistributorVO.setSord(sord);
		
        List<OrderDistributorVO> list = new ArrayList<OrderDistributorVO>();
		list = orderDistributorService.ordList(orderDistributorVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", list.size()); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: orderDistributorInsertPopup
	 * 2. ClassName : OrderDistributorController
	 * 3. Comment   : 주문등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 18. 오후 1:38:00
	 * </PRE>
	 *   @return String
	 *   @param orderDistributorVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "orderDistributorInsertPopup", method = RequestMethod.POST)
	public String orderDistributorInsertPopup(
			OrderDistributorVO orderDistributorVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		return URL + "/popup/orderDistributorInsertPopup";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: ordPreList
	 * 2. ClassName : OrderDistributorController
	 * 3. Comment   : 주문등록 팝업의 주문 목록 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 19. 오전 11:15:58
	 * </PRE>
	 *   @return void
	 *   @param orderDistributorVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "ordPreList", method = RequestMethod.POST)
	public void ordPreList(
			OrderDistributorVO orderDistributorVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		orderDistributorVO.setLngTyp(lngTyp);
		orderDistributorVO.setSidx(sidx);
		orderDistributorVO.setSord(sord);
		
        List<OrderDistributorVO> list = new ArrayList<OrderDistributorVO>();
		list = orderDistributorService.ordPreList(orderDistributorVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", list.size()); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	@RequestMapping(value = "insertOrd", method = RequestMethod.POST)
	public void insertOrd(
			@RequestBody List<OrderDistributorVO> orderDistributorVOs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = orderDistributorService.insertOrd(orderDistributorVOs, session);
		
		model.addAttribute("result", result);
		
	}
	
	@RequestMapping(value = "deleteOrd", method = RequestMethod.POST)
	public void deleteOrd(
			OrderDistributorVO orderDistributorVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		orderDistributorVO.setRegrId(session.getUserId());
		orderDistributorVO.setChgrId(session.getUserId());
		orderDistributorVO.setRegDate(DateUtil.sysdate());
		orderDistributorVO.setChgDate(DateUtil.sysdate());
		orderDistributorVO.setLngTyp(lngTyp);
		
		int result = orderDistributorService.deleteOrd(orderDistributorVO);
		
		model.addAttribute("result", result);
		
	}
	
	@RequestMapping(value = "updateOrd", method = RequestMethod.POST)
	public void updateOrd(
			OrderDistributorVO orderDistributorVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		orderDistributorVO.setRegrId(session.getUserId());
		orderDistributorVO.setChgrId(session.getUserId());
		orderDistributorVO.setRegDate(DateUtil.sysdate());
		orderDistributorVO.setChgDate(DateUtil.sysdate());
		orderDistributorVO.setLngTyp(lngTyp);
		
		String result = orderDistributorService.updateOrd(orderDistributorVO);
		
		model.addAttribute("result", result);
		
	}
	
	@RequestMapping(value = "orderDistributorHistPopup", method = RequestMethod.POST)
	public String orderDistributorHistPopup(
			OrderDistributorVO orderDistributorVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		return URL + "/popup/orderDistributorHistPopup";
		
	}
	
	
	@RequestMapping(value = "ordList2", method = RequestMethod.POST)
	public void ordList2(
			OrderDistributorVO orderDistributorVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		orderDistributorVO.setLngTyp(lngTyp);
		orderDistributorVO.setSidx(sidx);
		orderDistributorVO.setSord(sord);
		
        List<OrderDistributorVO> list = new ArrayList<OrderDistributorVO>();
		int count = 0;
		count = orderDistributorService.ordCount2(orderDistributorVO);	
		if (count > 0) list = orderDistributorService.ordList2(orderDistributorVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
}
