package com.ntels.ccbs.distribute.controller.distributor.distributorMgt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.EmployeeVO;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.EmployeeService;
import com.ntels.ccbs.system.domain.common.common.OrganizationMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.common.OrganizationMngService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/distributor/distributorMgt/employee")
public class EmployeeController {

	@Autowired
	private OrganizationMngService organizationMngService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String URL = "distributor/distributor/distributorMgt/employee";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : EmployeeController
	 * 3. Comment   : 직원관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 19. 오후 1:36:41
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param employeeVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "employee", method = RequestMethod.POST)
	public String employee(Model model, EmployeeVO employeeVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("crrTp", commonDataService.getCommonCodeList("SY00003", lngTyp));
		
		return URL + "/employee";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: employeeListAction
	 * 2. ClassName : EmployeeController
	 * 3. Comment   : 직원 목록 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 19. 오후 2:18:27
	 * </PRE>
	 *   @return void
	 *   @param employeeVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "employeeListAction", method = RequestMethod.POST)
	public void employeeListAction(
			EmployeeVO employeeVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		employeeVO.setLngTyp(lngTyp);
		employeeVO.setSidx(sidx);
		employeeVO.setSord(sord);
		
		List<String> orgIdList = new ArrayList<String>();
		List<OrganizationMngVO> paramList = new ArrayList<OrganizationMngVO>();
		OrganizationMngVO param = new OrganizationMngVO();
		
		//orgId 공백처리
		if(employeeVO.getOrgId() != null && !employeeVO.getOrgId().trim().equals("")){
			param.setOrgId(employeeVO.getOrgId());
			paramList.add(param);
			
			orgIdList = organizationMngService.recursionOrganizationList(paramList);
		}
		employeeVO.setOrgIdList(orgIdList);
		
		
        List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		int count = 0;
		count = employeeService.employeeCount(employeeVO);		
		if (count > 0) list = employeeService.employeeList(employeeVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: employeeInsertPopUp
	 * 2. ClassName : EmployeeController
	 * 3. Comment   : 직원등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 20. 오전 9:56:24
	 * </PRE>
	 *   @return String
	 *   @param employeeVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "employeeInsertPopUp", method = RequestMethod.POST)
	public String employeeInsertPopUp(
			EmployeeVO employeeVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("crrTp", commonDataService.getCommonCodeList("SY00003", lngTyp));
		model.addAttribute("userGroupList", employeeService.userGroupList(employeeVO));
		
		model.addAttribute("employeeVO", employeeVO);

		return URL + "/popup/employeeInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: checkUserId
	 * 2. ClassName : EmployeeController
	 * 3. Comment   : ID중복 체크
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 20. 오전 9:57:36
	 * </PRE>
	 *   @return void
	 *   @param employeeVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "checkUserId", method = RequestMethod.POST)
	public void checkUserId(
			EmployeeVO employeeVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int idCount = employeeService.checkUserId(employeeVO);
		
		model.addAttribute("idCount", idCount);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertEmployee
	 * 2. ClassName : EmployeeController
	 * 3. Comment   : 직원등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 20. 오후 3:15:07
	 * </PRE>
	 *   @return void
	 *   @param employeeVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertEmployee", method = RequestMethod.POST)
	public void insertEmployee(
			EmployeeVO employeeVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		employeeVO.setRegrId(session.getUserId());
		employeeVO.setChgrId(session.getUserId());
		employeeVO.setRegDate(DateUtil.sysdate());
		employeeVO.setChgDate(DateUtil.sysdate());
		employeeVO.setLngTyp(lngTyp);
		
		int result = employeeService.insertEmployee(employeeVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: employeeUpdatePopUp
	 * 2. ClassName : EmployeeController
	 * 3. Comment   : 직원 수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 20. 오후 4:47:01
	 * </PRE>
	 *   @return String
	 *   @param employeeVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "employeeUpdatePopUp", method = RequestMethod.POST)
	public String employeeUpdatePopUp(
			EmployeeVO employeeVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("crrTp", commonDataService.getCommonCodeList("SY00003", lngTyp));
		model.addAttribute("userGroupList", employeeService.userGroupList(employeeVO));
		
		model.addAttribute("employeeVO", employeeVO);

		return URL + "/popup/employeeUpdatePopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateEmployee
	 * 2. ClassName : EmployeeController
	 * 3. Comment   : 직원 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 24. 오후 3:46:29
	 * </PRE>
	 *   @return void
	 *   @param employeeVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateEmployee", method = RequestMethod.POST)
	public void updateEmployee(
			EmployeeVO employeeVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		employeeVO.setRegrId(session.getUserId());
		employeeVO.setChgrId(session.getUserId());
		employeeVO.setRegDate(DateUtil.sysdate());
		employeeVO.setChgDate(DateUtil.sysdate());
		employeeVO.setLngTyp(lngTyp);
		
		int result = employeeService.updateEmployee(employeeVO);
		
		model.addAttribute("result", result);
	
	}
}
