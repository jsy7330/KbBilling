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
import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.DistributionUnitPriceVO;
import com.ntels.ccbs.distribute.service.logistics.referenceInfo.DistributionUnitPriceService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/referenceInfo/distributionUnitPrice")
public class DistributionUnitPriceController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private DistributionUnitPriceService distributionUnitPriceService;
	
	
	private String URL = "distributor/logistics/referenceInfo/distributionUnitPrice";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: distributionUnitPrice
	 * 2. ClassName : DistributionUnitPriceController
	 * 3. Comment   : 유통 단가 관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 2:57:50
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param distributionUnitPriceVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "distributionUnitPrice", method = RequestMethod.POST)
	public String distributionUnitPrice(Model model, DistributionUnitPriceVO distributionUnitPriceVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		
		return URL + "/distributionUnitPrice";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: distributionUnitPriceListAction
	 * 2. ClassName : DistributionUnitPriceController
	 * 3. Comment   : 제품 리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 2:58:00
	 * </PRE>
	 *   @return void
	 *   @param distributionUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "distributionUnitPriceListAction", method = RequestMethod.POST)
	public void distributionUnitPriceListAction(
			DistributionUnitPriceVO distributionUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		distributionUnitPriceVO.setLngTyp(lngTyp);
		distributionUnitPriceVO.setSidx(sidx);
		distributionUnitPriceVO.setSord(sord);
		
        List<DistributionUnitPriceVO> list = new ArrayList<DistributionUnitPriceVO>();
		int count = 0;
		count = distributionUnitPriceService.distributionUnitPriceCount(distributionUnitPriceVO);		
		if (count > 0) list = distributionUnitPriceService.distributionUnitPriceList(distributionUnitPriceVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: dstrbUtPrcDtlListAction
	 * 2. ClassName : DistributionUnitPriceController
	 * 3. Comment   : 유통단가 리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 3:10:03
	 * </PRE>
	 *   @return void
	 *   @param distributionUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "dstrbUtPrcDtlListAction", method = RequestMethod.POST)
	public void dstrbUtPrcDtlListAction(
			DistributionUnitPriceVO distributionUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		distributionUnitPriceVO.setLngTyp(lngTyp);
		distributionUnitPriceVO.setSidx(sidx);
		distributionUnitPriceVO.setSord(sord);
		
        List<DistributionUnitPriceVO> list = new ArrayList<DistributionUnitPriceVO>();
		int count = 0;
		count = distributionUnitPriceService.dstrbUtPrcDtlCount(distributionUnitPriceVO);		
		if (count > 0) list = distributionUnitPriceService.dstrbUtPrcDtlList(distributionUnitPriceVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: dstrbUtPrcDtlInsertPopUp
	 * 2. ClassName : DistributionUnitPriceController
	 * 3. Comment   : 유통단가 등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 3:13:02
	 * </PRE>
	 *   @return String
	 *   @param distributionUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "dstrbUtPrcDtlInsertPopUp", method = RequestMethod.POST)
	public String dstrbUtPrcDtlInsertPopUp(
			DistributionUnitPriceVO distributionUnitPriceVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		model.addAttribute("distributionUnitPriceVO", distributionUnitPriceVO);

		return URL + "/popup/dstrbUtPrcDtlInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertDstrbUtPrcDtl
	 * 2. ClassName : DistributionUnitPriceController
	 * 3. Comment   : 유통단가이력 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 3:17:45
	 * </PRE>
	 *   @return void
	 *   @param distributionUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertDstrbUtPrcDtl", method = RequestMethod.POST)
	public void insertDstrbUtPrcDtl(
			DistributionUnitPriceVO distributionUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		distributionUnitPriceVO.setRegrId(session.getUserId());
		distributionUnitPriceVO.setChgrId(session.getUserId());
		distributionUnitPriceVO.setRegDate(DateUtil.sysdate());
		distributionUnitPriceVO.setChgDate(DateUtil.sysdate());
		distributionUnitPriceVO.setLngTyp(lngTyp);
		
		int result = distributionUnitPriceService.insertDstrbUtPrcDtl(distributionUnitPriceVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: dstrbUtPrcDtlUpdatePopUp
	 * 2. ClassName : DistributionUnitPriceController
	 * 3. Comment   : 유통단가 수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 3:18:46
	 * </PRE>
	 *   @return String
	 *   @param distributionUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "dstrbUtPrcDtlUpdatePopUp", method = RequestMethod.POST)
	public String dstrbUtPrcDtlUpdatePopUp(
			DistributionUnitPriceVO distributionUnitPriceVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		model.addAttribute("distributionUnitPriceVO", distributionUnitPriceVO);

		return URL + "/popup/dstrbUtPrcDtlUpdatePopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateDstrbUtPrcDtl
	 * 2. ClassName : DistributionUnitPriceController
	 * 3. Comment   : 유통단가이력 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 3:19:57
	 * </PRE>
	 *   @return void
	 *   @param distributionUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateDstrbUtPrcDtl", method = RequestMethod.POST)
	public void updateDstrbUtPrcDtl(
			DistributionUnitPriceVO distributionUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		distributionUnitPriceVO.setRegrId(session.getUserId());
		distributionUnitPriceVO.setChgrId(session.getUserId());
		distributionUnitPriceVO.setRegDate(DateUtil.sysdate());
		distributionUnitPriceVO.setChgDate(DateUtil.sysdate());
		distributionUnitPriceVO.setLngTyp(lngTyp);
		
		int result = distributionUnitPriceService.updateDstrbUtPrcDtl(distributionUnitPriceVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteDstrbUtPrcDtl
	 * 2. ClassName : DistributionUnitPriceController
	 * 3. Comment   : 유통단가 이력 삭제
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 6. 1. 오후 3:20:47
	 * </PRE>
	 *   @return void
	 *   @param distributionUnitPriceVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteDstrbUtPrcDtl", method = RequestMethod.POST)
	public void deleteDstrbUtPrcDtl(
			DistributionUnitPriceVO distributionUnitPriceVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		
		int result = distributionUnitPriceService.deleteDstrbUtPrcDtl(distributionUnitPriceVO);
		
		model.addAttribute("result", result);
		
	}
	
}
