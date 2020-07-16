package com.ntels.ccbs.distribute.controller.logistics.voucherMgt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VoucherMasterVO;
import com.ntels.ccbs.distribute.service.logistics.voucherMgt.VoucherMasterMngService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/voucherMng/voucherMasterMng")
public class VoucherMasterMngController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private VoucherMasterMngService voucherMasterMngService;
	
	private String URL = "distributor/logistics/voucherMgt/voucherMasterMng";
	
	private String getLngType(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("sessionLanguage");
	}
	
	private SessionUser getSessionUser(HttpServletRequest request) {
		return (SessionUser) request.getSession().getAttribute("session_user");
	}
	
	@RequestMapping(value = "/voucherMasterMng")
	public String voucherMasterMng(Model model, HttpServletRequest request) {
		
		String lngTyp = getLngType(request);
		
		model.addAttribute("poStatList", commonDataService.getCommonCodeList("DN00099", lngTyp));		//바우처종류
		
		return URL + "/voucherMasterMng";
	}
	
	@RequestMapping(value = "/voucherMasterInfoAction", method = RequestMethod.POST)
	public void voucherMasterInfo(Model model, VissueVO vissueVO, HttpServletRequest request) {
		String lngTyp = getLngType(request);
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        vissueVO.setLngTyp(lngTyp);
        vissueVO.setPage(page);
        vissueVO.setPerPage(perPageRow);
        
        List<VoucherMasterVO> voucherList = new ArrayList<>();
        int count = voucherMasterMngService.getVeqtInfoCount(vissueVO);
        
        if (count > 0) {
        	voucherList = voucherMasterMngService.getVeqtInfoList(vissueVO);
        }

		model.addAttribute("rows", voucherList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", voucherList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
		
	}
	
	@RequestMapping(value = "/voucherSearchPopup")
	public String voucherSearchPopup(Model model, HttpServletRequest request) {
		String lngTyp = getLngType(request);
		
		String returnVissueSeqNo = request.getParameter("returnVissueSeqNo");
		String returnItemId = request.getParameter("returnItemId");
		String returnItemNm = request.getParameter("returnItemNm");
		String itemId = request.getParameter("itemId");
		String itemNm = request.getParameter("itemNm");
		
		model.addAttribute("voTpCdList", commonDataService.getCommonCodeList("DN00097", lngTyp));		//바우처종류
		model.addAttribute("returnVissueSeqNo", returnVissueSeqNo);
		model.addAttribute("returnItemId", returnItemId);
		model.addAttribute("returnItemNm", returnItemNm);
		model.addAttribute("itemId", itemId);
		model.addAttribute("itemNm", itemNm);
		
		return "system/common/common/voucherMng/mpopup/voucherSearchPopup";
	}
	
	@RequestMapping(value = "/voucherListAction", method = RequestMethod.POST)
	public void voucherListAction(Model model, VissueVO vissueVO, HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        vissueVO.setPage(page);
        vissueVO.setPerPage(perPageRow);
        
        if (StringUtils.isEmpty(vissueVO.getSoId()) == true) {
        	vissueVO.setSoId(getSessionUser(request).getSoId());
        }
        
        List<VissueVO> voucherList = new ArrayList<>();
        int count = voucherMasterMngService.vissueListCount(vissueVO);
        
        if (count > 0) {
        	voucherList = voucherMasterMngService.getVissueList(vissueVO);
        }

		model.addAttribute("rows", voucherList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", voucherList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/saleProcessVeqtSearchPopup")
	public String saleProcessVeqtSearchPopup(Model model, HttpServletRequest request) {
		
		String soId = request.getParameter("soId");
		String soNm = request.getParameter("soNm");
		String vissueSeqNo = request.getParameter("vissueSeqNo");
		String ownLocCd = request.getParameter("ownLocCd");
		String ownLocNm = request.getParameter("ownLocNm");
		
		model.addAttribute("soId", soId);
		model.addAttribute("soNm", soNm);
		model.addAttribute("vissueSeqNo", vissueSeqNo);
		model.addAttribute("ownLocCd", ownLocCd);
		model.addAttribute("ownLocNm", ownLocNm);
		// multiselect
		return "system/common/common/voucherMng/mpopup/saleProcessVeqtSearchPopup";
	}
	
	@RequestMapping(value = "/salesProcessPopVeqtListAction", method = RequestMethod.POST)
	public void salesProcessPopVeqtListAction(Model model, VoucherMasterVO voucherMaster, HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        voucherMaster.setPage(page);
        voucherMaster.setPerPage(perPageRow);
        
        if (StringUtils.isEmpty(voucherMaster.getSoId()) == true) {
        	voucherMaster.setSoId(getSessionUser(request).getSoId());
        }
        
        List<VoucherMasterVO> saleProcessVeqtList = new ArrayList<>();
        int count = voucherMasterMngService.getSaleProcessPopVeqtCount(voucherMaster);

        if (count > 0) {
        	saleProcessVeqtList = voucherMasterMngService.getSalesProcessPopVeqtList(voucherMaster);
        }
        
    	// 테스트
//    	saleProcessVeqtList = new ArrayList<>();
//    	
//    	VoucherMasterVO master = new VoucherMasterVO();
//    	
//    	master.setVoBarCd("1");
//    	master.setVoSeqNo("1");
//    	master.setVoStatCd("1");
//    	master.setVoIssueDt("20160822");
//    	master.setVoIssueAmt("1");
//    	saleProcessVeqtList.add(master);
//
//    	master = new VoucherMasterVO();
//    	master.setVoBarCd("2");
//    	master.setVoSeqNo("2");
//    	master.setVoStatCd("2");
//    	master.setVoIssueDt("20160822");
//    	master.setVoIssueAmt("2");
//    	saleProcessVeqtList.add(master);
//
//    	master = new VoucherMasterVO();
//    	master.setVoBarCd("3");
//    	master.setVoSeqNo("3");
//    	master.setVoStatCd("3");
//    	master.setVoIssueDt("20160822");
//    	master.setVoIssueAmt("3");
//    	saleProcessVeqtList.add(master);
//
//    	master = new VoucherMasterVO();
//    	master.setVoBarCd("4");
//    	master.setVoSeqNo("4");
//    	master.setVoStatCd("4");
//    	master.setVoIssueDt("20160822");
//    	master.setVoIssueAmt("4");
//    	saleProcessVeqtList.add(master);
//
//    	master = new VoucherMasterVO();
//    	master.setVoBarCd("5");
//    	master.setVoSeqNo("5");
//    	master.setVoStatCd("5");
//    	master.setVoIssueDt("20160822");
//    	master.setVoIssueAmt("5");
//    	saleProcessVeqtList.add(master);
//    	
//    	count = saleProcessVeqtList.size();
        // LAB.M07.LAB00012

		model.addAttribute("rows", saleProcessVeqtList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지 사이즈
		model.addAttribute("records", saleProcessVeqtList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "/saveSalesProcessVeqtInfo", method = RequestMethod.POST)
	public void saveSalesProcessVeqtInfo(@RequestBody List<VoucherMasterVO> voucherMasterList, Model model, HttpServletRequest request) {
		SessionUser session = getSessionUser(request);
		
		for (VoucherMasterVO voucherMaster : voucherMasterList) {
			voucherMaster.setCtChgId(session.getUserId());
			voucherMaster.setRegrId(session.getUserId());
		}
		
		try {
			voucherMasterMngService.saveSalesProcessVeqtInfo(voucherMasterList);
			model.addAttribute("result", 0);
		} catch (Exception e) {
			model.addAttribute("result", 1);
			model.addAttribute("errorMsg", e.getMessage());
		}
	}
	
}
