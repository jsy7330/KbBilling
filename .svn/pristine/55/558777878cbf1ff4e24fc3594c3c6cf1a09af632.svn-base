package com.ntels.ccbs.customer.controller.contract.work;

import java.util.ArrayList;
import java.util.Date;
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

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.service.contract.work.WorkManagementService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/customer/contract/work/workManagement")
public class WorkManagementController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private WorkManagementService workManagementService;
	
	private String thisUrl = "customer/contract/work/workManagement";
	
	@RequestMapping(value = "workManagement", method = RequestMethod.POST)
	public String workManagement(Model model, HttpServletRequest request){
		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
		model.addAttribute("orderTpList", commonDataService.getCommonCodeList("CM00022", lng));
		model.addAttribute("workStatList", commonDataService.getCommonCodeList("CM00039", lng));
		model.addAttribute("startDate", DateUtil.getDateCalByDays(-30));
		model.addAttribute("endDate", DateUtil.getDateCalByDays(0));
		return thisUrl + "/workManagement";
	}
	
	@RequestMapping(value = "getWorkStatisticsAction", method = RequestMethod.POST)
	public String getWorkStatistics(Model model, HttpServletRequest request, String soId){
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		Map<String,Object> workStatistics = workManagementService.getWorkStatistics(soId, sessionUser.getSoAuthList());
		List<Map<String,Object>> workStatisticsList = new ArrayList<>();
		workStatisticsList.add(workStatistics);
		
		model.addAttribute("workStatisticsList", workStatisticsList);
		return thisUrl + "/workManagement";
	}
	
	@RequestMapping(value = "getWorkListAction", method = RequestMethod.POST)
	public String getWorkList(Model model, HttpServletRequest request 
			,String soId
			,String workStartDate
			,String workEndDate
			,String orderTp
			,String workStat
			,String custId
			,String workUserId
			,String sidx
			,String sord
			,int page
			,int rows
			){
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		Map<String,Object> workInfo = workManagementService.getWorkList(soId,
				sessionUser.getSoAuthList(),
				workStartDate,
				workEndDate,
				orderTp,
				workStat,
				custId,
				workUserId,
				sidx,
				sord,
				page,
				rows,
				today,
				lng);
		
		model.addAttribute("workList", workInfo.get("workList"));
		model.addAttribute("totalCount", workInfo.get("totalCount"));
		model.addAttribute("totalPages", workInfo.get("totalPages"));
		model.addAttribute("page", workInfo.get("page"));
		return thisUrl + "/workManagement";
	}
	
	
	@RequestMapping(value = "getWorkViewOpenCheckAction", method = RequestMethod.POST)
	public String getWorkViewOpenCheck(Model model, HttpServletRequest request 
			,String wrkId
			,String orderId
			,String hasViewAuthYn
			){
		SessionUser sessionUser = CommonUtil.getSessionManager();
		/**
		 * 조회 권한 체크
		 * -상담화면의 경우 조회 권한 부여
		 * -작업에 할당된 담당자 및 참조자는 조회 가능
		 */
		String workViewAuthR = null;
		if("Y".equals(hasViewAuthYn)){
			workViewAuthR = "Y"; //
		}else{
			workViewAuthR = workManagementService.getWorkAuthR(orderId, sessionUser.getUserId());
		}
		
		if("N".equals(workViewAuthR)){
			throw new ServiceException("message.customer.check.workAuth");
		}
		return thisUrl + "/workManagement";
	}
	
	@RequestMapping(value = "openWorkView", method = RequestMethod.POST)
	public String openWorkView(Model model, HttpServletRequest request 
			,String wrkId
			,String orderId
			,String rcptId
			,String hasViewAuthYn
			){
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		/**
		 * 진행 권한 체크
		 *  - 처리 담당자이면서 이전 오더가 완료이면서 해당 작업이 완료 전인 경우(접수, 진행, 완료 가능)
		 */
		String workViewAuthU = workManagementService.getWorkAuthU(wrkId, orderId, sessionUser.getUserId());
		
		/**
		 * 취소 권한 체크
		 * -최종 완료자의 경우 취소 가능(접수, 진행, 취소, 완료 가능)
		 * -최초 접수자인 경우이면서 해당 작업이 완료 전인 경우
		 */
		String workViewAuthC = workManagementService.getWorkAuthC(wrkId, orderId, sessionUser.getUserId());
	
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		
		Map<String,Object> workInfo = workManagementService.getWorkInfo(wrkId, orderId, lng, today);
		
		model.addAttribute("workViewAuthU", workViewAuthU);
		model.addAttribute("workViewAuthC", workViewAuthC);
		model.addAttribute("workInfo", workInfo);
		model.addAttribute("rcptId", rcptId);
		model.addAttribute("today", DateUtil.getDateStringYYYYMMDD(0));
		return thisUrl + "/ajax/workProcessPopup";
	}

	@RequestMapping(value = "getWorkHistListAction", method = RequestMethod.POST)
	public String getWorkHistList(Model model, HttpServletRequest request 
			,String orderId){
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		List<Map<String,Object>> workHistList = workManagementService.getWorkHistList(orderId,
				today,
				lng);
		
		model.addAttribute("workHistList", workHistList);
		return thisUrl + "/workProcessPopup";
	}
	
	@RequestMapping(value = "updateWorkReceiptAction", method = RequestMethod.POST)
	public String updateWorkReceipt(Model model, HttpServletRequest request
			,String wrkId
			,String orderId
			,String comment){
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		Date  todayDateType = DateUtil.sysdate();
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		workManagementService.updateWorkReceipt(wrkId, orderId, comment, sessionUser.getUserId(), lng, today, todayDateType );
		
		
		/**
		 * 진행 권한 체크
		 *  - 처리 담당자이면서 이전 오더가 완료이면서 해당 작업이 완료 전인 경우(접수, 진행, 완료 가능)
		 */
		String workViewAuthU = workManagementService.getWorkAuthU(wrkId, orderId, sessionUser.getUserId());
		
		/**
		 * 취소 권한 체크
		 * -최종 완료자의 경우 취소 가능(접수, 진행, 취소, 완료 가능)
		 * -최초 접수자인 경우이면서 해당 작업이 완료 전인 경우
		 */
		String workViewAuthC = workManagementService.getWorkAuthC(wrkId, orderId, sessionUser.getUserId());
		
		Map<String,Object> workInfo = workManagementService.getWorkInfo(wrkId, orderId, lng, today);
		
		model.addAttribute("workViewAuthU", workViewAuthU);
		model.addAttribute("workViewAuthC", workViewAuthC);
		model.addAttribute("workInfo", workInfo);
		return thisUrl + "/workProcessPopup";
	}
	
	@RequestMapping(value = "updateWorkProcessAction", method = RequestMethod.POST)
	public String updateWorkProcess(Model model, HttpServletRequest request
			,String wrkId
			,String orderId
			,String comment){
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		Date  todayDateType = DateUtil.sysdate();
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		workManagementService.updateWorkProcess(wrkId, orderId, comment, sessionUser.getUserId(), lng, today, todayDateType );
		
		
		/**
		 * 진행 권한 체크
		 *  - 처리 담당자이면서 이전 오더가 완료이면서 해당 작업이 완료 전인 경우(접수, 진행, 완료 가능)
		 */
		String workViewAuthU = workManagementService.getWorkAuthU(wrkId, orderId, sessionUser.getUserId());
		
		/**
		 * 취소 권한 체크
		 * -최종 완료자의 경우 취소 가능(접수, 진행, 취소, 완료 가능)
		 * -최초 접수자인 경우이면서 해당 작업이 완료 전인 경우
		 */
		String workViewAuthC = workManagementService.getWorkAuthC(wrkId, orderId, sessionUser.getUserId());
		
		Map<String,Object> workInfo = workManagementService.getWorkInfo(wrkId, orderId, lng, today);
		
		model.addAttribute("workViewAuthU", workViewAuthU);
		model.addAttribute("workViewAuthC", workViewAuthC);
		model.addAttribute("workInfo", workInfo);
		return thisUrl + "/workProcessPopup";
	}
	
	@RequestMapping(value = "updateWorkCancelAction", method = RequestMethod.POST)
	public String updateWorkCancel(Model model, HttpServletRequest request
			,String wrkId
			,String orderId
			,String rcptId
			,String comment){
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		Date  todayDateType = DateUtil.sysdate();
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		workManagementService.updateWorkCancel(wrkId, orderId, rcptId, comment, sessionUser.getUserId(),  sessionUser.getOrgId(), lng, today, todayDateType );
		
		/**
		 * 진행 권한 체크
		 *  - 처리 담당자이면서 이전 오더가 완료이면서 해당 작업이 완료 전인 경우(접수, 진행, 완료 가능)
		 */
		String workViewAuthU = workManagementService.getWorkAuthU(wrkId, orderId, sessionUser.getUserId());
		
		/**
		 * 취소 권한 체크
		 * -최종 완료자의 경우 취소 가능(접수, 진행, 취소, 완료 가능)
		 * -최초 접수자인 경우이면서 해당 작업이 완료 전인 경우
		 */
		String workViewAuthC = workManagementService.getWorkAuthC(wrkId, orderId, sessionUser.getUserId());
		
		Map<String,Object> workInfo = workManagementService.getWorkInfo(wrkId, orderId, lng, today);
		
		model.addAttribute("workViewAuthU", workViewAuthU);
		model.addAttribute("workViewAuthC", workViewAuthC);
		model.addAttribute("workInfo", workInfo);
		return thisUrl + "/workProcessPopup";
	}
	
	
	@RequestMapping(value = "updateWorkCmplAction", method = RequestMethod.POST)
	public String updateWorkCmpl(Model model, HttpServletRequest request
			,String wrkId
			,String orderId
			,String rcptId
			,String comment
			,String wrkReqDt){
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		Date  todayDateType = DateUtil.sysdate();
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		workManagementService.updateWorkCmpl(wrkId, orderId, rcptId, comment, sessionUser.getUserId(),  sessionUser.getOrgId(), lng, today, todayDateType, wrkReqDt );
		
		/**
		 * 진행 권한 체크
		 *  - 처리 담당자이면서 이전 오더가 완료이면서 해당 작업이 완료 전인 경우(접수, 진행, 완료 가능)
		 */
		String workViewAuthU = workManagementService.getWorkAuthU(wrkId, orderId, sessionUser.getUserId());
		
		/**
		 * 취소 권한 체크
		 * -최종 완료자의 경우 취소 가능(접수, 진행, 취소, 완료 가능)
		 * -최초 접수자인 경우이면서 해당 작업이 완료 전인 경우
		 */
		String workViewAuthC = workManagementService.getWorkAuthC(wrkId, orderId, sessionUser.getUserId());
		
		Map<String,Object> workInfo = workManagementService.getWorkInfo(wrkId, orderId, lng, today);
		
		model.addAttribute("workViewAuthU", workViewAuthU);
		model.addAttribute("workViewAuthC", workViewAuthC);
		model.addAttribute("workInfo", workInfo);
		return thisUrl + "/workProcessPopup";
	}
}

