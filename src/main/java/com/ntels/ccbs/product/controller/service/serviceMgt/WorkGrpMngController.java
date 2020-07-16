package com.ntels.ccbs.product.controller.service.serviceMgt;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.service.service.serviceMgt.WorkGrpMngService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/product/service/serviceMgt/workGrpMng")
public class WorkGrpMngController {
	
	private static String URL = "product/service/serviceMgt/workGrpMng";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private WorkGrpMngService workGrpMngService;
	
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value = "workGroupMng", method = RequestMethod.POST)
	public String workGroupMng(Model model,HttpServletRequest request) {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("useYnList", commonDataService.getCommonCodeList("PP00184", lngTyp));
		
		
		return URL + "/workGroupMng";
	}
	
	@RequestMapping(value = "getWorkGrpListAction", method = RequestMethod.POST)
	public String getWorkGrpList(Model model,HttpServletRequest request,
			String soId,
			String workGrpNm,
			String sidx,
			String sord,
			int page,
			int rows) {
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		
		Map<String,Object> workGrpInfo = workGrpMngService.getWorkGrpList(soId, sessionUser.getSoAuthList(), workGrpNm, sidx,sord, page, rows, today, lng);
		
		model.addAttribute("workGrpList", workGrpInfo.get("workGrpList"));
		model.addAttribute("totalCount", workGrpInfo.get("totalCount"));
		model.addAttribute("totalPages", workGrpInfo.get("totalPages"));
		model.addAttribute("page", workGrpInfo.get("page"));
		
		return URL + "/workGroupMng";
	}
	
	@RequestMapping(value = "getUserListAction", method = RequestMethod.POST)
	public String getUserList(Model model,HttpServletRequest request,
			String svcWrkGrpId) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		
		List<Map<String,Object>> userList = workGrpMngService.getUserList(svcWrkGrpId, today, lng);
		
		model.addAttribute("userList", userList);
		
		return URL + "/workGroupMng";
	}
	
	@RequestMapping(value = "getWorkGrpUserListAction", method = RequestMethod.POST)
	public String getWorkGrpUserList(Model model,HttpServletRequest request,
			String svcWrkGrpId) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		
		List<Map<String,Object>> workGrpUserList = workGrpMngService.getWorkGrpUserList(svcWrkGrpId, today, lng);
		
		model.addAttribute("workGrpUserList", workGrpUserList);
		
		return URL + "/workGroupMng";
	}
	
	@RequestMapping(value = "insertWorkGrpAction", method = RequestMethod.POST)
	public String insertWorkGrp(Model model,HttpServletRequest request,
			String soId,
			String workGrpNm,
			String useYn) {
		SessionUser sessionUser = CommonUtil.getSessionManager();
		workGrpMngService.insertWrokGrp(workGrpNm, soId, useYn, sessionUser.getUserId());
		return URL + "/workGroupMng";
	}
	
	@RequestMapping(value = "updateWorkGrpAction", method = RequestMethod.POST)
	public String updateWorkGrp(Model model,HttpServletRequest request,
			String workGrpId,
			String workGrpNm,
			String useYn) {
		SessionUser sessionUser = CommonUtil.getSessionManager();
		workGrpMngService.updateWorkGrp(workGrpId, workGrpNm, useYn, sessionUser.getUserId());
		return URL + "/workGroupMng";
	}
	
	@RequestMapping(value = "deleteWorkGrpAction", method = RequestMethod.POST)
	public String deleteWorkGrp(Model model,HttpServletRequest request,
			String workGrpId) {
		SessionUser sessionUser = CommonUtil.getSessionManager();
		workGrpMngService.deleteWorkGrp(workGrpId);
		return URL + "/workGroupMng";
	}
	
	@RequestMapping(value = "inserWorkGrpUserAction", method = RequestMethod.POST)
	public String inserWorkGrpUser(Model model,HttpServletRequest request,
			@RequestBody List<Map<String,Object>> addUserList) {
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		workGrpMngService.insertWorkUser(addUserList, sessionUser.getUserId());
		return URL + "/workGroupMng";
	}
	
	@RequestMapping(value = "updateWorkGrpUserAction", method = RequestMethod.POST)
	public String updateWorkGrpUser(Model model,HttpServletRequest request,
			@RequestBody List<Map<String,Object>> updateDataList) {
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		workGrpMngService.updateWorkUser(updateDataList, sessionUser.getUserId());
		return URL + "/workGroupMng";
	}
	
	@RequestMapping(value = "deleteWorkGrpUserAction", method = RequestMethod.POST)
	public String deleteWorkGrpUser(Model model,HttpServletRequest request,
			@RequestBody List<Map<String,Object>> deleteDataList) {
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		workGrpMngService.deleteWorkUser(deleteDataList, sessionUser.getUserId());
		return URL + "/workGroupMng";
	}
}




