package com.ntels.ccbs.distribute.controller.logistics.logisticsCenterReceIssue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.distribute.domain.logistics.logisticsCenterReceIssue.LogisticsCenterIssueInspVO;
import com.ntels.ccbs.distribute.service.logistics.logisticsCenterReceIssue.LogisticsCenterIssueInspService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/logisticsCenterReceIssue/logisticsCenterIssueInsp")
public class LogisticsCenterIssueInspController {
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private LogisticsCenterIssueInspService logisticsCenterIssueInspService;
	
	private String URL = "distributor/logistics/logisticsCenterReceIssue/logisticsCenterIssueInsp";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: logisticsCenterIssueInsp
	 * 2. ClassName : LogisticsCenterIssueInspController
	 * 3. Comment   : 물류센터 출고 관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 22. 오후 1:45:24
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param logisticsCenterIssueInspVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "logisticsCenterIssueInsp", method = RequestMethod.POST)
	public String logisticsCenterIssueInsp(Model model, LogisticsCenterIssueInspVO logisticsCenterIssueInspVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		
		return URL + "/logisticsCenterIssueInsp";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: lgstCntrOrdList
	 * 2. ClassName : LogisticsCenterIssueInspController
	 * 3. Comment   : 물류센터출고관리 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 22. 오후 2:01:16
	 * </PRE>
	 *   @return void
	 *   @param logisticsCenterIssueInspVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "lgstCntrOrdList", method = RequestMethod.POST)
	public void lgstCntrOrdList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		logisticsCenterIssueInspVO.setLngTyp(lngTyp);
		logisticsCenterIssueInspVO.setSidx(sidx);
		logisticsCenterIssueInspVO.setSord(sord);
		
        List<LogisticsCenterIssueInspVO> list = new ArrayList<LogisticsCenterIssueInspVO>();
		int count = 0;
		count = logisticsCenterIssueInspService.lgstCntrOrdCount(logisticsCenterIssueInspVO);		
		if (count > 0) list = logisticsCenterIssueInspService.lgstCntrOrdList(logisticsCenterIssueInspVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: lgstCntrOutList
	 * 2. ClassName : LogisticsCenterIssueInspController
	 * 3. Comment   : 출고내역 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 22. 오후 4:03:28
	 * </PRE>
	 *   @return void
	 *   @param logisticsCenterIssueInspVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "lgstCntrOutList", method = RequestMethod.POST)
	public void lgstCntrOutList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		logisticsCenterIssueInspVO.setLngTyp(lngTyp);
		logisticsCenterIssueInspVO.setSidx(sidx);
		logisticsCenterIssueInspVO.setSord(sord);
		
        List<LogisticsCenterIssueInspVO> list = new ArrayList<LogisticsCenterIssueInspVO>();
		list = logisticsCenterIssueInspService.lgstCntrOutList(logisticsCenterIssueInspVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)list.size() / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: logisticsCenterIssueInspInsertPopUp
	 * 2. ClassName : LogisticsCenterIssueInspController
	 * 3. Comment   : 출고내역 등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 25. 오후 2:28:32
	 * </PRE>
	 *   @return String
	 *   @param logisticsCenterIssueInspVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "logisticsCenterIssueInspInsertPopUp", method = RequestMethod.POST)
	public String logisticsCenterIssueInspInsertPopUp(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		return URL + "/popup/logisticsCenterIssueInspInsertPopUp";
		
	}
	
	@RequestMapping(value = "lgstCntrOutEqtList", method = RequestMethod.POST)
	public void lgstCntrOutEqtList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		logisticsCenterIssueInspVO.setLngTyp(lngTyp);
		logisticsCenterIssueInspVO.setSidx(sidx);
		logisticsCenterIssueInspVO.setSord(sord);
		
        List<LogisticsCenterIssueInspVO> list = new ArrayList<LogisticsCenterIssueInspVO>();
		list = logisticsCenterIssueInspService.lgstCntrOutEqtList(logisticsCenterIssueInspVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)list.size() / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	@RequestMapping(value = "lgstCntrOutUsimList", method = RequestMethod.POST)
	public void lgstCntrOutUsimList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		logisticsCenterIssueInspVO.setLngTyp(lngTyp);
		logisticsCenterIssueInspVO.setSidx(sidx);
		logisticsCenterIssueInspVO.setSord(sord);
		
        List<LogisticsCenterIssueInspVO> list = new ArrayList<LogisticsCenterIssueInspVO>();
		list = logisticsCenterIssueInspService.lgstCntrOutUsimList(logisticsCenterIssueInspVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)list.size() / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	@RequestMapping(value = "lgstCntrOutVeqtList", method = RequestMethod.POST)
	public void lgstCntrOutVeqtList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		logisticsCenterIssueInspVO.setLngTyp(lngTyp);
		logisticsCenterIssueInspVO.setSidx(sidx);
		logisticsCenterIssueInspVO.setSord(sord);
		
        List<LogisticsCenterIssueInspVO> list = new ArrayList<LogisticsCenterIssueInspVO>();
		list = logisticsCenterIssueInspService.lgstCntrOutVeqtList(logisticsCenterIssueInspVO);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)list.size() / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	
	@RequestMapping(value = "insertInoutList", method = RequestMethod.POST)
	public void insertInoutList(
			@RequestBody List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = logisticsCenterIssueInspService.insertInoutList(logisticsCenterIssueInspVOs, session);
		
		model.addAttribute("result", result);
		
	}
	
	@RequestMapping(value = "deleteOut", method = RequestMethod.POST)
	public void deleteOut(
			@RequestBody List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = logisticsCenterIssueInspService.deleteOut(logisticsCenterIssueInspVOs, session);
		
		model.addAttribute("result", result);
		
	}
	
	
	@RequestMapping(value = "insertOutConf", method = RequestMethod.POST)
	public void insertOutConf(
			@RequestBody List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = logisticsCenterIssueInspService.insertOutConf(logisticsCenterIssueInspVOs, session);
		
		model.addAttribute("result", result);
		
	}
	
	
	
	
}
