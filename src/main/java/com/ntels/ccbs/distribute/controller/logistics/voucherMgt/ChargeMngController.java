package com.ntels.ccbs.distribute.controller.logistics.voucherMgt;

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

import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.ChargeMngVO;
import com.ntels.ccbs.distribute.service.logistics.voucherMgt.ChargeMngService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/voucherMng/chargeMng")
public class ChargeMngController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ChargeMngService chargeMngService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String URL = "distributor/logistics/voucherMgt/chargeMgt";

	private String getLngType(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("sessionLanguage");
	}
	
	private SessionUser getSessionUser(HttpServletRequest request) {
		return (SessionUser) request.getSession().getAttribute("session_user");
	}
	
	@RequestMapping(value = "/chargeMng")
	public String chargeMng() {
		return URL + "/chargeMgt";
	}
	
	@RequestMapping(value = "/ctrtInfoListAction", method = RequestMethod.POST)
	public void ctrtInfoListAction(Model model,  ChargeMngVO chargeMngVO, HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        String lngTyp = getLngType(request);
        
        chargeMngVO.setPage(page);
        chargeMngVO.setPerPage(perPageRow);
        chargeMngVO.setLngTyp(lngTyp);
        
        List<ChargeMngVO> customerList = new ArrayList<>();
        int count = chargeMngService.getCtrtInfoListCount(chargeMngVO);
        
        if (count > 0) {
        	customerList = chargeMngService.getCtrtInfoList(chargeMngVO);
        }
        
		model.addAttribute("rows", customerList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", customerList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/customerSearchPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String customerSearchPopup(Model model, HttpServletRequest request) {
		
		String lngTyp = getLngType(request);
//		String callbackFunction = request.getParameter("callbackFunction");
		String popupType = request.getParameter("popupType");
		
		logger.info("popupType : " + popupType);

		model.addAttribute("custTpCdList", commonDataService.getCommonCodeList("CM00009", lngTyp));		// 고객유형
//		model.addAttribute("callbackFunction", callbackFunction);
		model.addAttribute("popupType", popupType);
		
		String popup = "";
		
		if ("m".equals(popupType) == true) {
			popup = "mpopup";
		} else if ("o".equals(popupType) == true) {
			popup = "opopup";
		} else {
			popup = "mpopup";
		}
		
		return "system/common/common/voucherMng/" + popup + "/customerSearchPopup";
	}
	
	@RequestMapping(value = "/customerSearchAction", method = RequestMethod.POST)
	public void customerSearchAction(ChargeMngVO chargeMngVO, Model model, HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        String lngTyp = getLngType(request);
        
        chargeMngVO.setPage(page);
        chargeMngVO.setPerPage(perPageRow);
        chargeMngVO.setLngTyp(lngTyp);
        
        List<ChargeMngVO> customerList = new ArrayList<>();
        int count = chargeMngService.getCustInfoListCount(chargeMngVO);
        
        if (count > 0) {
        	customerList = chargeMngService.getCustInfoList(chargeMngVO);
        }
        
		model.addAttribute("rows", customerList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", customerList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/chrgHistListAction", method = RequestMethod.POST)
	public void chrgHistListAction(Model model, ChargeMngVO chargeMngVO, HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        String lngTyp = getLngType(request);
        
        chargeMngVO.setPage(page);
        chargeMngVO.setPerPage(perPageRow);
        chargeMngVO.setLngTyp(lngTyp);
        
        List<ChargeMngVO> customerList = new ArrayList<>();
        int count = chargeMngService.getChrgHistListCount(chargeMngVO);
        
        if (count > 0) {
        	customerList = chargeMngService.getChrgHistList(chargeMngVO);
        }
        
		model.addAttribute("rows", customerList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", customerList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/chargePopup")
	public String chargePopup(Model model, ChargeMngVO chargeMngVO, HttpServletRequest request) {
		String lngTyp = getLngType(request);
		SessionUser session = getSessionUser(request);
		
		chargeMngVO.setUserId(session.getUserId());

		int rmnAmt = chargeMngService.getRmnAmt(chargeMngVO);
		ChargeMngVO preBasicProdYnInfo = chargeMngService.getPreBasicProdYnInfo(chargeMngVO);
		ChargeMngVO preBundleProdYnInfo = chargeMngService.getPreBundleProdYnInfo(chargeMngVO);
		ChargeMngVO latestBundleProdInfo = chargeMngService.getLatestBundleProdInfo(chargeMngVO);
		ChargeMngVO promoCurrentInfo = chargeMngService.getPromoCurrentInfo(chargeMngVO);
		int notResPvCount = chargeMngService.getNotResPVCount(chargeMngVO);
		
		model.addAttribute("rmnAmt", rmnAmt);
		model.addAttribute("preBasicProdYnInfo", preBasicProdYnInfo);
		model.addAttribute("preBundleProdYnInfo", preBundleProdYnInfo);
		model.addAttribute("latestBundleProdInfo", latestBundleProdInfo);
		model.addAttribute("promoCurrentInfo", promoCurrentInfo);
		model.addAttribute("notResPvCount", notResPvCount);
		model.addAttribute("ctrtInfo", chargeMngVO);
		model.addAttribute("loginUser", session);
		model.addAttribute("rchrgTpList", commonDataService.getCommonCodeList("DN00103", lngTyp));		// 충전유형
		
		return "system/common/common/voucherMng/mpopup/chargePopup";
	}
	
	@RequestMapping(value = "/voucherSearchPopup")
	public String voucherSearchPopup() {
		return URL + "/opopup/voucherSearchPopup";
	}
	
	@RequestMapping(value = "/chrgVeatListAction", method = RequestMethod.POST)
	public void chrgVeatListAction(Model model, ChargeMngVO chargeMngVO, HttpServletRequest request) {
		SessionUser session = getSessionUser(request);
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		
		chargeMngVO.setUserId(session.getUserId());
        chargeMngVO.setPage(page);
        chargeMngVO.setPerPage(perPageRow);
        
        List<ChargeMngVO> chrgVeqtList = new ArrayList<>();
        int count = chargeMngService.getChrgVeqtListCount(chargeMngVO);
        
        if (count > 0) {
        	chrgVeqtList = chargeMngService.getChrgVeqtList(chargeMngVO);
        }
        
		model.addAttribute("rows", chrgVeqtList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", chrgVeqtList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/chrgCtrtListPopup")
	public String chrgCtrtListPopup(Model model, ChargeMngVO chargeMngVO) {
		
		model.addAttribute("chargeMngVO", chargeMngVO);
		
		return URL + "/opopup/chrgCtrtListPopup";
	}
	
	@RequestMapping(value = "/chrgCtrtListAction", method = RequestMethod.POST)
	public void chrgCtrtListAction(Model model, ChargeMngVO chargeMngVO, HttpServletRequest request) {
		String lngTyp = getLngType(request);
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        chargeMngVO.setLngTyp(lngTyp);
        chargeMngVO.setPage(page);
        chargeMngVO.setPerPage(perPageRow);
        
        List<ChargeMngVO> chrgCtrtList = new ArrayList<>();
        int count = chargeMngService.getChrgCtrtListCount(chargeMngVO);
        
        if (count > 0) {
        	chrgCtrtList = chargeMngService.getChrgCtrtList(chargeMngVO);
        }
        
		model.addAttribute("rows", chrgCtrtList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", chrgCtrtList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/chrgProdPopup")
	public String chrgProdPopup(Model model, ChargeMngVO chargeMngVO, HttpServletRequest request) {
		
		String popupType = request.getParameter("popupType");
		
		model.addAttribute("chargeMngVO", chargeMngVO);
		model.addAttribute("popupType", popupType);
		
		logger.info("popupType : " + popupType);
		
		if ("o".equals(popupType) == true) {
			return URL + "/opopup/chrgProdPopup";
		}
		
		return URL + "/chrgProdPopup";
	}
	
	@RequestMapping(value = "/chrgProdListAction", method = RequestMethod.POST)
	public void chrgProdListAction(Model model, ChargeMngVO chargeMngVO, HttpServletRequest request) {
		String lngTyp = getLngType(request);
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        chargeMngVO.setLngTyp(lngTyp);
        chargeMngVO.setPage(page);
        chargeMngVO.setPerPage(perPageRow);
        
        List<ChargeMngVO> chrgCtrtList = new ArrayList<>();
        int count = chargeMngService.getChrgCtrtListCount(chargeMngVO);
        
        if (count > 0) {
        	chrgCtrtList = chargeMngService.getChrgCtrtList(chargeMngVO);
        }
        
		model.addAttribute("rows", chrgCtrtList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", chrgCtrtList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/addCharge", method = RequestMethod.POST)
	public void addCharge(Model model, ChargeMngVO chargeMngVO, HttpServletRequest request) {
		SessionUser session = getSessionUser(request);
		
		chargeMngVO.setUserId(session.getUserId());
		chargeMngVO.setRegrId(session.getUserId());
		
		try {
			chargeMngService.addCharge(chargeMngVO);
			model.addAttribute("result", 0);
		} catch (Exception e) {
			model.addAttribute("result", 1);
			model.addAttribute("errorMsg", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/cancelPopup")
	public String cancelPopup(Model model, ChargeMngVO chargeMngVO, HttpServletRequest request) {
		
		String lngTyp = getLngType(request);
		
		chargeMngVO.setLngTyp(lngTyp);
		
		int rmnAmt = chargeMngService.getRmnAmt(chargeMngVO);
		int notResPvCount = chargeMngService.getNotResPVCount(chargeMngVO);
		List<ChargeMngVO> ctrtChrgCntList = chargeMngService.getCtrtChrgCntList(chargeMngVO);
		
		model.addAttribute("chrgInfo", chargeMngVO);
		model.addAttribute("rmnAmt", rmnAmt);
		model.addAttribute("notResPvCount", notResPvCount);
		model.addAttribute("ctrtChrgCntList", ctrtChrgCntList);
		
		return "system/common/common/voucherMng/mpopup/cancelPopup";
	}
	
	@RequestMapping(value = "/getOcsCancelYn", method = RequestMethod.POST)
	public void getOcsCancelYn(Model model, ChargeMngVO chargeMngVO) {
		try {
			String ocsCancelYn = chargeMngService.getOcsCancelYn(chargeMngVO);
			model.addAttribute("result", 0);
			model.addAttribute("ocsCancelYn", ocsCancelYn);
		} catch (Exception e) {
			model.addAttribute("result", 1);
			model.addAttribute("errorMsg", e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/cancelCharge", method = RequestMethod.POST)
	public void cancelCharge(Model model, ChargeMngVO chargeMngVO, HttpServletRequest request) {

		SessionUser session = getSessionUser(request);
		chargeMngVO.setRegrId(session.getUserId());

		try {
			chargeMngService.cancelCharge(chargeMngVO);
			model.addAttribute("result", 0);
		} catch (Exception e) {
			model.addAttribute("result", 1);
			model.addAttribute("errorMsg", e.getMessage());
		}
		
	}

}
