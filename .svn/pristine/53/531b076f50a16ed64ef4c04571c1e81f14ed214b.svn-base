package com.ntels.ccbs.charge.controller.nonpayment.nonpayment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.charge.domain.nonpayment.nonpayment.DunningInfoItemVO;
import com.ntels.ccbs.charge.service.nonpayment.nonpayment.DunningCriteriaService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/charge/nonpayment/nonpayment/referenceInfo")
public class DunningManagementController {
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private DunningCriteriaService dunningCriteriaService;
	
	private String URL = "charge/nonpayment/nonpayment/referenceInfo";

	/** per page. */
    //private @Value("${paging.per_page}") Integer perPage;
    
    /**
     * 
     * <PRE>
     * 1. MethodName: list
     * 2. ClassName : DunningManagementController
     * 3. Comment   : 대응기준 조회페이지 호출
     * 4. 작성자    : jskim
     * 5. 작성일    : 2016.07.29.
     * </PRE>
     *   @return String
     *   @param model
     *   @param dunningInfoItemVo
     *   @param request
     *   @return
     *   @throws Exception
     */
	@RequestMapping(value = "dunningCriteriaMng", method = RequestMethod.POST)
	public String list(Model model, DunningInfoItemVO dunningInfoItemVo, HttpServletRequest request) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "BL00109";
		String tpDtlCd = "BL00110";
		
		model.addAttribute("dunningTpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("dunningTpDtlCd", commonDataService.getCommonCodeList(tpDtlCd, lngTyp));
		
		return URL + "/dunningCriteriaMng";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: dunningCriteriaListAction
	 * 2. ClassName : DunningManagementController
	 * 3. Comment   : 기준정보 목록 조회 리스트
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.07.29.
	 * </PRE>
	 *   @return void
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "dunningCriteriaListAction", method = RequestMethod.POST)
	public void dunningCriteriaListAction(
			DunningInfoItemVO dunningInfo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		dunningInfo.setLngTyp(lngTyp);
		dunningInfo.setSidx(sidx);
		dunningInfo.setSord(sord);
		
        List<DunningInfoItemVO> list = new ArrayList<DunningInfoItemVO>();
		int count = 0;
		count = dunningCriteriaService.dunningCriteriaCount(dunningInfo);
		if (count > 0) list = dunningCriteriaService.dunningCriteriaList(dunningInfo, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: selectDunningCriteriaInfo
	 * 2. ClassName : DunningManagementController
	 * 3. Comment   : 대응기준 정보 상세정보 조회
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.02.
	 * </PRE>
	 *   @return void
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "selectDunningCriteriaInfo", method = RequestMethod.POST)
	public void selectDunningCriteriaInfo(
			DunningInfoItemVO dunningInfo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		dunningInfo.setLngTyp(lngTyp);
		
		DunningInfoItemVO returnData = dunningCriteriaService.getDunningCriteria(dunningInfo);
		
		model.addAttribute("dunningInfo", returnData);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: dunningCriteriaInsertPopUp
	 * 2. ClassName : DunningManagementController
	 * 3. Comment   : 대응기준 정보 등록 팝업
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.02.
	 * </PRE>
	 *   @return String
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "dunningCriteriaInsertPopUp", method = RequestMethod.POST)
	public String dunningCriteriaInsertPopUp(
			DunningInfoItemVO dunningInfo
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "BL00109";
		String tpDtlCd = "BL00110";
		
		model.addAttribute("dunningTpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("dunningTpDtlCd", commonDataService.getCommonCodeList(tpDtlCd, lngTyp));
		
		return URL + "/ajax/dunningCriteriaInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: dunningCriteriaUpdatePopUp
	 * 2. ClassName : DunningManagementController
	 * 3. Comment   : 대응기준 정보 수정 팝업
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.02.
	 * </PRE>
	 *   @return String
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "dunningCriteriaUpdatePopUp", method = RequestMethod.POST)
	public String dunningCriteriaUpdatePopUp(
			DunningInfoItemVO dunningInfo
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "BL00109";
		String tpDtlCd = "BL00110";
		
		model.addAttribute("dunningTpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("dunningTpDtlCd", commonDataService.getCommonCodeList(tpDtlCd, lngTyp));
		
		return URL + "/ajax/dunningCriteriaUpdatePopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertDunningCriteriaInfo
	 * 2. ClassName : DunningManagementController
	 * 3. Comment   : 대응기준 정보 등록
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.02.
	 * </PRE>
	 *   @return void
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertDunningCriteriaInfo", method = RequestMethod.POST)
	public void insertDunningCriteriaInfo(
			DunningInfoItemVO dunningInfo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		dunningInfo.setRegrId(session.getUserId());
		dunningInfo.setChgrId(session.getUserId());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setLngTyp(lngTyp);
		
		int result = dunningCriteriaService.insertDunningInfo(dunningInfo);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateDunningCriteriaInfo
	 * 2. ClassName : DunningManagementController
	 * 3. Comment   : 대응기준 정보 수정
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.02.
	 * </PRE>
	 *   @return void
	 *   @param distributionInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateDunningCriteriaInfo", method = RequestMethod.POST)
	public void updateDunningCriteriaInfo(
			DunningInfoItemVO dunningInfo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		dunningInfo.setRegrId(session.getUserId());
		dunningInfo.setChgrId(session.getUserId());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setLngTyp(lngTyp);
		
		int result = dunningCriteriaService.updateDunningInfo(dunningInfo);
		
		model.addAttribute("result", result);
	
	}
}
