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
import com.ntels.ccbs.distribute.domain.logistics.assignmentOrderMgt.DistributorReceiptInspVO;
import com.ntels.ccbs.distribute.service.logistics.assignmentOrderMgt.DistributorReceiptInspService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/assignmentOrderMgt/distributorReceiptInsp")
public class DistributorReceiptInspController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private DistributorReceiptInspService distributorReceiptInspService;
	
	private String URL = "distributor/logistics/assignmentOrderMgt/distributorReceiptInsp";
	
	
	@RequestMapping(value = "distributorReceiptInsp", method = RequestMethod.POST)
	public String distributorReceiptInsp(Model model, DistributorReceiptInspVO distributorReceiptInspVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		return URL + "/distributorReceiptInsp";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: inoutDtlList
	 * 2. ClassName : DistributorReceiptInspController
	 * 3. Comment   : 입고대기내역 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 1. 오전 9:49:31
	 * </PRE>
	 *   @return void
	 *   @param distributorReceiptInspVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "inoutDtlList", method = RequestMethod.POST)
	public void inoutDtlList(
			DistributorReceiptInspVO distributorReceiptInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		distributorReceiptInspVO.setLngTyp(lngTyp);
		distributorReceiptInspVO.setSidx(sidx);
		distributorReceiptInspVO.setSord(sord);
		
        List<DistributorReceiptInspVO> list = new ArrayList<DistributorReceiptInspVO>();
		int count = 0;
		count = distributorReceiptInspService.inoutDtlCount(distributorReceiptInspVO);	
		if (count > 0) list = distributorReceiptInspService.inoutDtlList(distributorReceiptInspVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: inoutDtlList2
	 * 2. ClassName : DistributorReceiptInspController
	 * 3. Comment   : 입고내역 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 2. 오전 9:45:50
	 * </PRE>
	 *   @return void
	 *   @param distributorReceiptInspVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "inoutDtlList2", method = RequestMethod.POST)
	public void inoutDtlList2(
			DistributorReceiptInspVO distributorReceiptInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		distributorReceiptInspVO.setLngTyp(lngTyp);
		distributorReceiptInspVO.setSidx(sidx);
		distributorReceiptInspVO.setSord(sord);
		
        List<DistributorReceiptInspVO> list = new ArrayList<DistributorReceiptInspVO>();
		list = distributorReceiptInspService.inoutDtlList2(distributorReceiptInspVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", list.size()); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: inoutDtlList3
	 * 2. ClassName : DistributorReceiptInspController
	 * 3. Comment   : 상세내역 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 2. 오전 9:46:04
	 * </PRE>
	 *   @return void
	 *   @param distributorReceiptInspVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "inoutDtlList3", method = RequestMethod.POST)
	public void inoutDtlList3(
			DistributorReceiptInspVO distributorReceiptInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		distributorReceiptInspVO.setLngTyp(lngTyp);
		distributorReceiptInspVO.setSidx(sidx);
		distributorReceiptInspVO.setSord(sord);
		
        List<DistributorReceiptInspVO> list = new ArrayList<DistributorReceiptInspVO>();
		list = distributorReceiptInspService.inoutDtlList3(distributorReceiptInspVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", list.size()); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertInsp
	 * 2. ClassName : DistributorReceiptInspController
	 * 3. Comment   : 검수저장
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 2. 오전 10:01:17
	 * </PRE>
	 *   @return void
	 *   @param distributorReceiptInspVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertInsp", method = RequestMethod.POST)
	public void insertInsp(
			@RequestBody DistributorReceiptInspVO distributorReceiptInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = distributorReceiptInspService.insertInsp(distributorReceiptInspVO, session);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertInConf
	 * 2. ClassName : DistributorReceiptInspController
	 * 3. Comment   : 입고승인
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 3. 오후 5:09:37
	 * </PRE>
	 *   @return void
	 *   @param distributorReceiptInspVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertInConf", method = RequestMethod.POST)
	public void insertInConf(
			@RequestBody DistributorReceiptInspVO distributorReceiptInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = distributorReceiptInspService.insertInConf(distributorReceiptInspVO, session);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertInRefuse
	 * 2. ClassName : DistributorReceiptInspController
	 * 3. Comment   : 입고거절
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 3. 오후 5:09:50
	 * </PRE>
	 *   @return void
	 *   @param distributorReceiptInspVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertInRefuse", method = RequestMethod.POST)
	public void insertInRefuse(
			@RequestBody DistributorReceiptInspVO distributorReceiptInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		distributorReceiptInspVO.setRegrId(session.getUserId());
		distributorReceiptInspVO.setChgrId(session.getUserId());
		distributorReceiptInspVO.setRegDate(DateUtil.sysdate());
		distributorReceiptInspVO.setChgDate(DateUtil.sysdate());
		distributorReceiptInspVO.setLngTyp(lngTyp);
		
		int result = distributorReceiptInspService.insertInRefuse(distributorReceiptInspVO, session);
		
		model.addAttribute("result", result);
		
	}
	
}
