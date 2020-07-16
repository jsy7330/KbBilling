package com.ntels.ccbs.product.controller.service.serviceMgt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.product.domain.service.serviceMgt.WorkDefMngVO;
import com.ntels.ccbs.product.service.service.serviceMgt.WorkDefMngService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/product/service/serviceMgt/service")
public class WorkDefMngController {
	
	private static String URL = "product/service/serviceMgt/manageWorkDef";
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WorkDefMngService workDefMngService;
		
	@Autowired
	private CommonDataService commonDataService;
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: workDefMng
	 * 2. ClassName : WorkDefMngController
	 * 3. Comment   : 작업정의 관리 화면
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param WorkDefMngVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */	
	@RequestMapping(value = "workDefMng", method = RequestMethod.POST)
	public String workDefMng(Model model,HttpServletRequest request) {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("userSoId", sessionUser.getSoId());		
		model.addAttribute("wrkTyp", commonDataService.getCommonCodeList("CM00037", lngTyp));	//작업타입
		model.addAttribute("useYn", commonDataService.getCommonCodeList("PP00184", lngTyp));	//사용유무
				
		return URL + "/workDefMng";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: workDefMngListAction
	 * 2. ClassName : WorkDefMngController
	 * 3. Comment   : 작업정의관리 조회
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param WorkDefMngVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "workDefMngListAction", method = RequestMethod.POST)
	public void workDefMngListAction(
			WorkDefMngVO workDefMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		workDefMngVO.setLngTyp(lngTyp);
		workDefMngVO.setSidx(sidx);
		workDefMngVO.setSord(sord);
		
        List<WorkDefMngVO> list = new ArrayList<WorkDefMngVO>();
		int count = 0;
		count = workDefMngService.workDefMngListCount(workDefMngVO);		
		if (count > 0) list = workDefMngService.workDefMngListAction(workDefMngVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertWorkDefMngInfo
	 * 2. ClassName : WorkDefMngController
	 * 3. Comment   : 작업정의 등록
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param billingCycleInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "insertWorkDefMngInfo", method = RequestMethod.POST)
	public void insertWorkDefMngInfo(
			WorkDefMngVO workDefMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		workDefMngVO.setLngTyp(lngTyp);		
		workDefMngVO.setRegId(session.getUserId());
		workDefMngVO.setChgId(session.getUserId());
		int result = workDefMngService.insertWorkDefMngInfo(workDefMngVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateWorkDefMngInfo
	 * 2. ClassName : WorkDefMngController
	 * 3. Comment   : 작업정의 수정
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param billingCycleInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "updateWorkDefMngInfo", method = RequestMethod.POST)
	public void updateWorkDefMngInfo(
			WorkDefMngVO workDefMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		

		SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		workDefMngVO.setLngTyp(lngTyp);		
		workDefMngVO.setRegId(session.getUserId());
		workDefMngVO.setChgId(session.getUserId());
		int result = workDefMngService.updateWorkDefMngInfo(workDefMngVO);
										
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteWorkDefMngInfo
	 * 2. ClassName : WorkDefMngController
	 * 3. Comment   : 작업정의 삭제
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param billingCycleInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "deleteWorkDefMngInfo", method = RequestMethod.POST)
	public void deleteWorkDefMngInfo(
			WorkDefMngVO workDefMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		workDefMngVO.setLngTyp(lngTyp);		
		workDefMngVO.setRegId(session.getUserId());
		workDefMngVO.setChgId(session.getUserId());
		
		int result = workDefMngService.deleteWorkDefMngInfo(workDefMngVO);
		
		model.addAttribute("result", result);
	
	}
	
}
