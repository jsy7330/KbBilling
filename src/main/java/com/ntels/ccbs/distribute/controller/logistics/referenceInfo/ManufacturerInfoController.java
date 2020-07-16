package com.ntels.ccbs.distribute.controller.logistics.referenceInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.ManufacturerInfoVO;
import com.ntels.ccbs.distribute.service.logistics.referenceInfo.ManufacturerInfoService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/referenceInfo/manufacturerInfo")
public class ManufacturerInfoController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ManufacturerInfoService manufacturerInfoService;
	
	private String URL = "distributor/logistics/referenceInfo/manufacturerInfo";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: manufacturerInfo
	 * 2. ClassName : ManufacturerInfoController
	 * 3. Comment   : 제조사 정보 관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 23. 오후 4:21:42
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param manufacturerInfoVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "manufacturerInfo", method = RequestMethod.POST)
	public String manufacturerInfo(Model model, ManufacturerInfoVO manufacturerInfoVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("bnkCd", commonDataService.getCommonCodeList("CM00006", lngTyp));	//은행
		model.addAttribute("pymMthdCd", commonDataService.getCommonCodeList("DN00062", lngTyp));	//대금지급방법
		
		return URL + "/manufacturerInfo";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: manufacturerInfoListAction
	 * 2. ClassName : ManufacturerInfoController
	 * 3. Comment   : 제조사정보리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 23. 오후 4:22:05
	 * </PRE>
	 *   @return void
	 *   @param manufacturerInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "manufacturerInfoListAction", method = RequestMethod.POST)
	public void manufacturerInfoListAction(
			ManufacturerInfoVO manufacturerInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		manufacturerInfoVO.setLngTyp(lngTyp);
		manufacturerInfoVO.setSidx(sidx);
		manufacturerInfoVO.setSord(sord);
		
        List<ManufacturerInfoVO> list = new ArrayList<ManufacturerInfoVO>();
		int count = 0;
		count = manufacturerInfoService.manufacturerInfoCount(manufacturerInfoVO);		
		if (count > 0) list = manufacturerInfoService.manufacturerInfoList(manufacturerInfoVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getExistManufacturerInfo
	 * 2. ClassName : ManufacturerInfoController
	 * 3. Comment   : 제조사 아이디 중복 체크
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 24. 오전 9:30:44
	 * </PRE>
	 *   @return void
	 *   @param manufacturerInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "getExistManufacturerInfo", method = RequestMethod.POST)
	public void getExistManufacturerInfo(
			ManufacturerInfoVO manufacturerInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		manufacturerInfoVO.setLngTyp(lngTyp);

		int count = manufacturerInfoService.getExistManufacturerInfo(manufacturerInfoVO);
		
		model.addAttribute("count", count);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertManufacturerInfo
	 * 2. ClassName : ManufacturerInfoController
	 * 3. Comment   : 제조사 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 24. 오전 9:31:44
	 * </PRE>
	 *   @return void
	 *   @param manufacturerInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertManufacturerInfo", method = RequestMethod.POST)
	public void insertManufacturerInfo(
			ManufacturerInfoVO manufacturerInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		manufacturerInfoVO.setRegrId(session.getUserId());
		manufacturerInfoVO.setChgrId(session.getUserId());
		manufacturerInfoVO.setRegDate(DateUtil.sysdate());
		manufacturerInfoVO.setChgDate(DateUtil.sysdate());
		manufacturerInfoVO.setLngTyp(lngTyp);
		
		int result = manufacturerInfoService.insertManufacturerInfo(manufacturerInfoVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateManufacturerInfo
	 * 2. ClassName : ManufacturerInfoController
	 * 3. Comment   : 제조사 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 24. 오전 11:13:37
	 * </PRE>
	 *   @return void
	 *   @param manufacturerInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateManufacturerInfo", method = RequestMethod.POST)
	public void updateManufacturerInfo(
			ManufacturerInfoVO manufacturerInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		manufacturerInfoVO.setRegrId(session.getUserId());
		manufacturerInfoVO.setChgrId(session.getUserId());
		manufacturerInfoVO.setRegDate(DateUtil.sysdate());
		manufacturerInfoVO.setChgDate(DateUtil.sysdate());
		manufacturerInfoVO.setLngTyp(lngTyp);
		
		int result = manufacturerInfoService.updateManufacturerInfo(manufacturerInfoVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteManufacturerInfo
	 * 2. ClassName : ManufacturerInfoController
	 * 3. Comment   : 제조사 삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 24. 오후 1:22:56
	 * </PRE>
	 *   @return void
	 *   @param manufacturerInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteManufacturerInfo", method = RequestMethod.POST)
	public void deleteManufacturerInfo(
			ManufacturerInfoVO manufacturerInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		int result = manufacturerInfoService.deleteManufacturerInfo(manufacturerInfoVO);
		
		model.addAttribute("result", result);
	
	}
}
