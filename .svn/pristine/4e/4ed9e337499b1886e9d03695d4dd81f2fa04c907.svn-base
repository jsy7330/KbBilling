package com.ntels.ccbs.distribute.controller.logistics.voucherMgt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.LogisticsCenterVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueDtlVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VoucherItemVO;
import com.ntels.ccbs.distribute.service.logistics.voucherMgt.VoucherGenerateService;
import com.ntels.ccbs.system.domain.common.common.ProductMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/voucherMng/voucherGenerate")
public class VoucherGenerateController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private VoucherGenerateService voucherGenerateService;
	
	private String URL = "distributor/logistics/voucherMgt/voucherGenerate";
	
	private SessionUser getSessionUser(HttpServletRequest request) {
		return (SessionUser) request.getSession().getAttribute("session_user");
	}
	
	@RequestMapping(value = "/voucherGenerateList")
	public String voucherGenerateList(Model model, HttpServletRequest request) {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		model.addAttribute("poPrgrStatCd", commonDataService.getCommonCodeList("DN00070", lngTyp));	//발주진행상태
		model.addAttribute("voTpCdList", commonDataService.getCommonCodeList("DN00097", lngTyp));		//바우처종류
		
		return URL + "/voucherGenerateList";
	}
	
	@RequestMapping(value = "getVissueListAction", method = RequestMethod.POST)
	public void vissueListAction(Model model, VissueVO vissueVO, HttpServletRequest request) {
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser session = getSessionUser(request);
		
		vissueVO.setLngTyp(lngTyp);
		
		if (StringUtils.isEmpty(vissueVO.getSoId()) == true) {
			vissueVO.setSoId(session.getSoId());	
		}
		
		vissueVO.setPage(page);
		vissueVO.setPerPage(perPageRow);
		
		List<VissueVO> vissueVOList = new ArrayList<VissueVO>();
		
		Integer count = voucherGenerateService.vissueListCount(vissueVO);
		count = count == null ? 0 : count;

		if (count > 0) {
			vissueVOList = voucherGenerateService.vissueList(vissueVO);
		}
		
		model.addAttribute("rows", vissueVOList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", vissueVOList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "getVissueDtlListAction", method = RequestMethod.POST)
	public void vissueDtlListAction(VissueDtlVO vissueDtlVO, Model model, HttpServletRequest request) {
		
		logger.info("vissueDtlVO : " + ToStringBuilder.reflectionToString(vissueDtlVO));
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		vissueDtlVO.setLngTyp(lngTyp);
		vissueDtlVO.setPage(page);
		vissueDtlVO.setPerPage(perPageRow);
		
		List<VissueDtlVO> vissueDtlVOList = new ArrayList<>();
		
		Integer count = voucherGenerateService.vissueDtlListCount(vissueDtlVO);
		count = count == null ? 0 : count;

		if (count > 0) {
			vissueDtlVOList = voucherGenerateService.vissueDtlList(vissueDtlVO);
		}
		
		model.addAttribute("rows", vissueDtlVOList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", vissueDtlVOList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/voucherItemSearchPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String voucherItemSearchPopup(Model model, HttpServletRequest request) {
		
		model.addAttribute("soId", request.getParameter("soId"));
		model.addAttribute("soNm", request.getParameter("soNm"));
		model.addAttribute("returnId", request.getParameter("returnId"));
		model.addAttribute("returnNm", request.getParameter("returnNm"));
		model.addAttribute("returnAmt", request.getParameter("returnAmt"));
		
		return "system/common/common/voucherMng/mpopup/voucherItemSearchPopup";
	}
	
	@RequestMapping(value = "/getVoucherItemListAction", method = RequestMethod.POST)
	public void voucherItemListAction(Model model, HttpServletRequest request) {
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");

		VoucherItemVO voucherItemVO = new VoucherItemVO();
		voucherItemVO.setSoId(session.getSoId());
		voucherItemVO.setPage(page);
		voucherItemVO.setPerPage(perPageRow);
		
		List<VoucherItemVO> voucherItemList = new ArrayList<>();
		
		Integer count = voucherGenerateService.voucherItemCount(voucherItemVO);
		count = count == null ? 0 : count;
		
		if (count > 0) {
			voucherItemList = voucherGenerateService.searchVoucherItem(voucherItemVO);
		}
		
		model.addAttribute("rows", voucherItemList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", voucherItemList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/logisticsCenterSearchPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String logisticsCenterSearchPopup(Model model, HttpServletRequest request) {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String returnId = request.getParameter("returnId");
		String returnNm = request.getParameter("returnNm");
		
		model.addAttribute("alTpList", commonDataService.getCommonCodeList("DN00101", lngTyp));		//지역구분
		model.addAttribute("alClList", commonDataService.getCommonCodeList("DN00100", lngTyp));		//지역분류
		model.addAttribute("tpDtlList", commonDataService.getCommonCodeList("DN00039", lngTyp));	//조직유형상세
		model.addAttribute("tpList", commonDataService.getCommonCodeList("DN00038", lngTyp));	//조직유형상세
		model.addAttribute("returnId", returnId);
		model.addAttribute("returnNm", returnNm);
		
		return "system/common/common/voucherMng/mpopup/logisticsCenterSearchPopup";
	}
	
	@RequestMapping(value = "logisticsCenterSearchAction", method = RequestMethod.POST)
	public void logisticsCenterSearchAction(LogisticsCenterVO logisticsCenterVO, Model model, HttpServletRequest request) {
		String lngTyp = (String) request.getSession().getAttribute("sessionLanguage");
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		
		logisticsCenterVO.setLngTyp(lngTyp);
		logisticsCenterVO.setSoId(session.getSoId());
		logisticsCenterVO.setPage(page);
		logisticsCenterVO.setPerPage(perPageRow);
		
		List<LogisticsCenterVO> logisticsCenterList = new ArrayList<>();
		
		Integer count = voucherGenerateService.logisticsCenterCount(logisticsCenterVO);
		count = count == null ? 0 : count;
		
		if (count > 0) {
			logisticsCenterList = voucherGenerateService.searchLogisticsCenter(logisticsCenterVO);
		}

		model.addAttribute("rows", logisticsCenterList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", logisticsCenterList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value="/saveVissueInfo", method = RequestMethod.POST)
	public void saveVissueInfo(Model model, VissueVO vissueVO) {
		try {
			voucherGenerateService.saveVissueInfo(vissueVO);
			model.addAttribute("result", 0);
		} catch (Exception e) {
			model.addAttribute("result", 1);
			model.addAttribute("errorMsg", e.getMessage());
		}

	}
	
	@RequestMapping(value = "/addIssueVoucherBulkInfo", method = RequestMethod.POST)
	public void addIssueVoucherBulkInfo(VissueVO vissueVO, Model model, HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		vissueVO.setRegrId(session.getUserId());
		
		try {
			voucherGenerateService.addIssueVoucherBulkInfo(vissueVO);
			model.addAttribute("result", 0);	
		} catch (Exception e) {
			model.addAttribute("result", 1);
			model.addAttribute("errorMsg", e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public void addOrder(VissueVO vissueVO, HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		vissueVO.setRegrId(session.getUserId());
		vissueVO.setChgrId(session.getUserId());
		voucherGenerateService.addOrder(vissueVO);
	}
	
	@RequestMapping(value = "/confirmCancelOrderPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String confirmCancelOrderPopup(VissueVO vissueVO, Model model, HttpServletRequest request) {

		String popupType = request.getParameter("popupType"); // 1 : confirm, 2: cancel
		
		model.addAttribute("vissueVO", vissueVO);
		model.addAttribute("popupType", popupType);
		
		return "system/common/common/voucherMng/mpopup/confirmCancelOrderPopup";
	}
	
	@RequestMapping(value = "/getVpoInfoList", method = RequestMethod.POST)
	public void vissuePoInfoList(VissueVO vissueVO, Model model, HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        vissueVO.setPage(page);
        vissueVO.setPerPage(perPageRow);
		
		List<VissueVO> vissuePoInfoList = new ArrayList<>();
		
		Integer count = voucherGenerateService.vissuePoInfoListCount(vissueVO);
		count = count == null ? 0 : count;
		
		if (count > 0) {
			vissuePoInfoList = voucherGenerateService.vissuePoInfoList(vissueVO);
		}
		
		model.addAttribute("rows", vissuePoInfoList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", vissuePoInfoList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public void confirmOrder(VissueVO vissueVO, HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		vissueVO.setRegrId(session.getUserId());
		vissueVO.setChgrId(session.getUserId());
		
		voucherGenerateService.saveConfirmOrder(vissueVO);
	}
	
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public void cancelOrder(VissueVO vissueVO, HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		vissueVO.setRegrId(session.getUserId());
		vissueVO.setChgrId(session.getUserId());
		
		voucherGenerateService.saveCancelOrder(vissueVO);
	}
	
	@RequestMapping(value = "/voucherTransferPopup", method = { RequestMethod.POST, RequestMethod.GET })
	public String voucherTransferPopup() {
		return "system/common/common/voucherMng/mpopup/voucherTransferPopup";
	}
	
	@RequestMapping(value = "/voucherTransfer", method = RequestMethod.POST)
	public void voucherTransfer(Model model, VissueVO vissueVO, HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		vissueVO.setRegrId(session.getUserId());
		vissueVO.setChgrId(session.getUserId());
		
		try {
			voucherGenerateService.saveVoucherTransfer(vissueVO);
			// 결과 성공
			model.addAttribute("result", 0);
		} catch (Exception e) {
			// 결과 실패
			model.addAttribute("result", 1);
			// 실패 메세지
			model.addAttribute("errorMsg", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/productSearchPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String productSearchPopup(
			ProductMngVO productMngVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("productMngVO", productMngVO);
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드

		return URL + "/ajax/productSearchPopup";
			
	}
	
	@RequestMapping(value = "/productListAction", method = RequestMethod.POST)
	public void productListAction(
			ProductMngVO productMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		productMngVO.setLngTyp(lngTyp);
		productMngVO.setSidx(sidx);
		productMngVO.setSord(sord);
		
        List<ProductMngVO> list = new ArrayList<ProductMngVO>();
		int count = 0;
		count = voucherGenerateService.productCount(productMngVO);	
		if (count > 0) list = voucherGenerateService.productList(productMngVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
}
