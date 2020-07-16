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
import com.ntels.ccbs.charge.domain.nonpayment.nonpayment.DunningExemptionVO;
import com.ntels.ccbs.charge.service.nonpayment.nonpayment.DunningExemptionService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/charge/nonpayment/nonpayment/referenceInfo")
public class DunningExemptionController {
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private DunningExemptionService dunningExemptionService;
	
	private String URL = "charge/nonpayment/nonpayment/referenceInfo";

	/** per page. */
    //private @Value("${paging.per_page}") Integer perPage;
    
    /**
     * 
     * <PRE>
     * 1. MethodName: list
     * 2. ClassName : DunningExemptionController
     * 3. Comment   : 조치제외 조회페이지 호출
     * 4. 작성자    : jskim
     * 5. 작성일    : 2016.08.08.
     * </PRE>
     *   @return String
     *   @param model
     *   @param dunningExemptionVo
     *   @param request
     *   @return
     *   @throws Exception
     */
	@RequestMapping(value = "dunningExemptionMng", method = RequestMethod.POST)
	public String list(Model model, DunningExemptionVO dunningExemptionVo, HttpServletRequest request) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		return URL + "/dunningExemptionMng";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: dunningExemptionListAction
	 * 2. ClassName : DunningExemptionController
	 * 3. Comment   : 조치제외정보 목록 조회 리스트
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.08.
	 * </PRE>
	 *   @return void
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "dunningExemptionListAction", method = RequestMethod.POST)
	public void dunningExemptionListAction(
			DunningExemptionVO dunningInfo
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
		
        List<DunningExemptionVO> list = new ArrayList<DunningExemptionVO>();
		int count = 0;
		count = dunningExemptionService.dunningExemptionCount(dunningInfo);
		if (count > 0) list = dunningExemptionService.dunningExemptionList(dunningInfo, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: selectDunningExemptionInfo
	 * 2. ClassName : DunningExemptionController
	 * 3. Comment   : 조치제외 정보 상세정보 조회
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.08.
	 * </PRE>
	 *   @return void
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "selectDunningExemptionInfo", method = RequestMethod.POST)
	public void selectDunningExemptionInfo(
			DunningExemptionVO dunningInfo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		dunningInfo.setLngTyp(lngTyp);
		
		DunningExemptionVO returnData = dunningExemptionService.getDunningExemption(dunningInfo);
		
		model.addAttribute("dunningInfo", returnData);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: dunningExemptionInsertPopUp
	 * 2. ClassName : DunningExemptionController
	 * 3. Comment   : 조치제외 정보 등록 팝업
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.08.
	 * </PRE>
	 *   @return String
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "dunningExemptionInsertPopUp", method = RequestMethod.POST)
	public String dunningExemptionInsertPopUp(
			DunningExemptionVO dunningInfo
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "BL00109";
		String tpDtlCd = "BL00113";
		
		model.addAttribute("dunningTpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("dunningTpDtlCd", commonDataService.getCommonCodeList(tpDtlCd, lngTyp));
		
		return URL + "/ajax/dunningExemptionInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: dunningExemptionUpdatePopUp
	 * 2. ClassName : DunningExemptionController
	 * 3. Comment   : 조치제외 정보 수정 팝업
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.08.
	 * </PRE>
	 *   @return String
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "dunningExemptionUpdatePopUp", method = RequestMethod.POST)
	public String dunningExemptionUpdatePopUp(
			DunningExemptionVO dunningInfo
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "BL00109";
		String tpDtlCd = "BL00113";
		
		model.addAttribute("dunningTpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("dunningTpDtlCd", commonDataService.getCommonCodeList(tpDtlCd, lngTyp));
		
		return URL + "/ajax/dunningExemptionUpdatePopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertDunningExemptionInfo
	 * 2. ClassName : DunningExemptionController
	 * 3. Comment   : 조치제외 정보 등록
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.08.
	 * </PRE>
	 *   @return void
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertDunningExemptionInfo", method = RequestMethod.POST)
	public void insertDunningExemptionInfo(
			DunningExemptionVO dunningInfo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		dunningInfo.setRegId(session.getUserId());
		dunningInfo.setChgId(session.getUserId());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setLngTyp(lngTyp);
		
		int result = dunningExemptionService.insertDunningExemption(dunningInfo);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateDunningExemptionInfo
	 * 2. ClassName : DunningExemptionController
	 * 3. Comment   : 조치제외 정보 수정
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.08.
	 * </PRE>
	 *   @return void
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateDunningExemptionInfo", method = RequestMethod.POST)
	public void updateDunningExemptionInfo(
			DunningExemptionVO dunningInfo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		dunningInfo.setRegId(session.getUserId());
		dunningInfo.setChgId(session.getUserId());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setLngTyp(lngTyp);
		
		int result = dunningExemptionService.updateDunningExemption(dunningInfo);
		
		model.addAttribute("result", result);
	
	}
	
	/**
     * 
     * <PRE>
     * 1. MethodName: list2
     * 2. ClassName : DunningExemptionController
     * 3. Comment   : 조치제외내역 조회페이지 호출
     * 4. 작성자    : jskim
     * 5. 작성일    : 2016.08.12.
     * </PRE>
     *   @return String
     *   @param model
     *   @param dunningExemptionVo
     *   @param request
     *   @return
     *   @throws Exception
     */
	@RequestMapping(value = "dunningExemptionList", method = RequestMethod.POST)
	public String list2(Model model, DunningExemptionVO dunningExemptionVo, HttpServletRequest request) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		//데이터유형 셀렉트박스에 들어갈 내용
		String tpCd = "BL00109";
		String tpDtlCd = "BL00113";
		
		model.addAttribute("dunningTpCd", commonDataService.getCommonCodeList(tpCd, lngTyp));
		model.addAttribute("dunningTpDtlCd", commonDataService.getCommonCodeList(tpDtlCd, lngTyp));
		
		return URL + "/dunningExemptionList";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateDunningExemptionInfoList
	 * 2. ClassName : DunningExemptionController
	 * 3. Comment   : 조치제외 정보 수정
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.12.
	 * </PRE>
	 *   @return void
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateDunningExemptionListInfo", method = RequestMethod.POST)
	public void updateDunningExemptionInfoList(
			DunningExemptionVO dunningInfo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		dunningInfo.setRegId(session.getUserId());
		dunningInfo.setChgId(session.getUserId());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setLngTyp(lngTyp);
		
		int result = dunningExemptionService.updateDunningExemptionList(dunningInfo);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteDunningExemptionInfoList
	 * 2. ClassName : DunningExemptionController
	 * 3. Comment   : 조치제외 정보 삭제
	 * 4. 작성자    : jskim
	 * 5. 작성일    : 2016.08.12.
	 * </PRE>
	 *   @return void
	 *   @param dunningInfo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteDunningExemptionListInfo", method = RequestMethod.POST)
	public void deleteDunningExemptionInfoList(
			DunningExemptionVO dunningInfo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		dunningInfo.setRegId(session.getUserId());
		dunningInfo.setChgId(session.getUserId());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setSysToDate(DateUtil.sysdate());
		dunningInfo.setLngTyp(lngTyp);
		
		int result = dunningExemptionService.deleteDunningExemptionList(dunningInfo);
		
		model.addAttribute("result", result);
	
	}
}
