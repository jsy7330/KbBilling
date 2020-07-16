package com.ntels.ccbs.system.controller.configuration.approvalMng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.configuration.approvalMng.ApprovalMng;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.ccbs.system.service.configuration.approvalMng.ApprovalMngService;



@Controller
@RequestMapping(value = "/system/configuration/approvalMng/approvalMng")
public class ApprovalMngController {


	/**
	 * 결재 관리 메인 URL
	 */
	private static String URL = "system/configuration/approvalMng/approvalMng";
	
	/**
	 * Logger 
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApprovalMngService approvalMngService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private SequenceService sequenceSevice;	
	
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: approvalMngList
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결재선관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 17. 오후 2:20:12
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "approvalMngList", method = RequestMethod.POST)
	public String approvalMngList(Model model,HttpServletRequest request) {				
		return URL + "/approvalMngList";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: approvalMngListAction
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결재정보 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 17. 오후 2:20:28
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param approvalMng
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "approvalMngListAction", method = RequestMethod.POST)
	public String approvalMngListAction(Model model, ApprovalMng approvalMng, HttpServletRequest request) throws Exception {

		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
        int page = Integer.parseInt(request.getParameter("page"));        
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
        
		approvalMng.setLngTyp(lngTyp);
		approvalMng.setSidx(sidx);
		approvalMng.setSord(sord);
		
        List<ApprovalMng> list = new ArrayList<ApprovalMng>();
        int count = 0;
        count = approvalMngService.getApprovalMngCnt(approvalMng);
		System.out.println("count==>"+count);
		
		if (count > 0) list = approvalMngService.getApprovalMngList(approvalMng, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		
		return URL + "/approvalMngList";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: approvalListAction
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결재대상자정보 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 17. 오후 2:20:43
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param approvalMng
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "approvalListAction", method = RequestMethod.POST)
	public String approvalListAction(Model model, ApprovalMng approvalMng, HttpServletRequest request) throws Exception {

		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
        int page = Integer.parseInt(request.getParameter("page"));        
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
        
		approvalMng.setLngTyp(lngTyp);
		approvalMng.setSidx(sidx);
		approvalMng.setSord(sord);
		
        List<ApprovalMng> list = new ArrayList<ApprovalMng>();
        int count = 0;
        count = approvalMngService.getApprovalCnt(approvalMng);
		
		if (count > 0) list = approvalMngService.getApprovalList(approvalMng, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		
		return URL + "/approvalMngList";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: addApprovalPopup
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결재대상자 추가 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 17. 오후 2:21:09
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "addApprovalPopup", method = RequestMethod.POST)
	public String addApprovalPopup(Model model,HttpServletRequest request) {			
		return URL + "/ajax/addApprovalPopup";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: addApprovalPopupAction
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결재대상자 리스트 (결제상신 팝업- 결재대상자 리스트)
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 17. 오후 2:25:25
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param approvalMng
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "addApprovalPopupAction", method = RequestMethod.POST)
	public String addApprovalPopupAction(Model model, ApprovalMng approvalMng, HttpServletRequest request) throws Exception {

		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
        int page = Integer.parseInt(request.getParameter("page"));        
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		approvalMng.setLngTyp(lngTyp);
		approvalMng.setSidx(sidx);
		approvalMng.setSord(sord);
		
        List<ApprovalMng> list = new ArrayList<ApprovalMng>();
        int count = 0;
        count = approvalMngService.getAddApprovalCnt(approvalMng);
		
		if (count > 0) list = approvalMngService.getAddApprovalList(approvalMng, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		
		return URL + "/addApprovalPopup";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: saveAprvDtlList
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결제대상자 저장
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 18. 오후 4:30:34
	 * </PRE>
	 *   @return void
	 *   @param approvalMngs
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "saveAprvDtlList", method = RequestMethod.POST)
	public void saveAprvDtlList(
			@RequestBody List<ApprovalMng> approvalMngs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int result = approvalMngService.saveAprvDtlList(approvalMngs);

		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: approvalReportPopup
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결제상신 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 18. 오후 2:54:05
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "approvalReportPopup", method = RequestMethod.POST)
	public String approvalReportPopup(Model model, ApprovalMng approvalMng,
			HttpServletRequest request) {
		
		model.addAttribute("approvalMng", approvalMng);
		
		return URL + "/mpopup/approvalReportPopup";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: saveAprvReport
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결재상신 저장
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 18. 오후 5:00:32
	 * </PRE>
	 *   @return void
	 *   @param approvalMngs
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "saveAprvReport", method = RequestMethod.POST)
	public void saveAprvReport(
			@RequestBody ApprovalMng approvalMng
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int result = approvalMngService.saveAprvReport(approvalMng);

		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: approvalOkPopup
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결재승인 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 19. 오후 1:35:17
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param approvalMng
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "approvalOkPopup", method = RequestMethod.POST)
	public String approvalOkPopup(Model model, ApprovalMng approvalMng,
			HttpServletRequest request) {
		
		model.addAttribute("approvalMng", approvalMng);
		
		return URL + "/mpopup/approvalOkPopup";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: approvalOkListAction
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결제승인 결재대상자 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 19. 오후 4:12:31
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param approvalMng
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "approvalOkListAction", method = RequestMethod.POST)
	public void approvalOkListAction(Model model, ApprovalMng approvalMng, HttpServletRequest request) throws Exception {

		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
        int page = Integer.parseInt(request.getParameter("page"));        
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		approvalMng.setLngTyp(lngTyp);
		approvalMng.setSidx(sidx);
		approvalMng.setSord(sord);
		
        List<ApprovalMng> list = new ArrayList<ApprovalMng>();
        list = approvalMngService.getApprovalOkList(approvalMng, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)list.size() / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: saveApprovalAction
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결재 승인, 반려 처리
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 24. 오전 9:30:12
	 * </PRE>
	 *   @return void
	 *   @param approvalMng
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "saveApprovalAction", method = RequestMethod.POST)
	public void saveApprovalAction(
			@RequestBody ApprovalMng approvalMng
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		approvalMng.setLngTyp(lngTyp);
		
		int result = approvalMngService.saveApproval(approvalMng);

		model.addAttribute("result", result);
		
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: approvalHistPopup
	 * 2. ClassName : ApprovalMngController
	 * 3. Comment   : 결재이력조회 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 24. 오후 1:17:49
	 * </PRE>
	 *   @return String
	 *   @param approvalMng
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "approvalHistPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String approvalHistPopup(
			ApprovalMng approvalMng
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		model.addAttribute("approvalMng", approvalMng);
		
		if(approvalMng.getPopType().equals("m")){
			return URL + "/mpopup/approvalHistPopup";
		}else{
			return URL + "/opopup/approvalHistPopup";
		}
			
	}
	
	
	@RequestMapping(value = "approvalHistListAction", method = RequestMethod.POST)
	public void approvalHistListAction(Model model, ApprovalMng approvalMng, HttpServletRequest request) throws Exception {

		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
        int page = Integer.parseInt(request.getParameter("page"));        
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		approvalMng.setLngTyp(lngTyp);
		approvalMng.setSidx(sidx);
		approvalMng.setSord(sord);
		
        List<ApprovalMng> list = new ArrayList<ApprovalMng>();
        list = approvalMngService.getApprovalHistList(approvalMng, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)list.size() / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		
	}
	
	
	@RequestMapping(value = "approvalExample", method = RequestMethod.POST)
	public String approvalExample(Model model,HttpServletRequest request) {				
		return URL + "/approvalExample";
	}	
	
	@RequestMapping(value = "approvalPopup", method = RequestMethod.POST)
	public String approvalPopup(Model model,HttpServletRequest request) {			
		return URL + "/ajax/approvalPopup";
	}	
	@RequestMapping(value = "approvalPopup2", method = RequestMethod.POST)
	public String approvalPopup2(Model model,HttpServletRequest request) {			
		return URL + "/ajax/approvalPopup2";
	}	
	/*
	@RequestMapping(value = "approvalHistPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String approvalHistPopup(Model model,HttpServletRequest request) {			
		return URL + "/ajax/approvalHistPopup";
	}		
	*/
}
