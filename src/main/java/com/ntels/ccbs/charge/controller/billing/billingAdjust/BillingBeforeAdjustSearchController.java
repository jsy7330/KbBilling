package com.ntels.ccbs.charge.controller.billing.billingAdjust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustSearchVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingBeforeAdjustSearchVO;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingAfterAdjustService;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingBeforeAdjustSearchService;
import com.ntels.ccbs.common.consts.Consts.ExcelFormatType;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.view.ExcelCellVO;
import com.ntels.ccbs.common.view.ExcelColumnVO;
import com.ntels.ccbs.common.view.ExcelFileVO;
import com.ntels.ccbs.common.view.ExcelRowVO;
import com.ntels.ccbs.common.view.ExcelSheetVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.nisf.util.message.MessageUtil;


@Controller
@RequestMapping(value = "/charge/billing/billingAdjust/billingBeforeAdjustSearch")
public class BillingBeforeAdjustSearchController {
	
	private static String URL = "charge/billing/billingAdjust/billingBeforeAdjustSearch";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private BillingBeforeAdjustSearchService billingBeforeAdjustSearchService;
	
	@Autowired
	private BillingAfterAdjustService billingAfterAdjustService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "billingBeforeAdjustSearch", method = RequestMethod.POST)
	public String billingBeforeAdjustSearch(Model model, BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("dcsnProcStatList", commonDataService.getCommonCodeList("BL00055", lng));
		
		return URL + "/billingBeforeAdjustSearch";
	}

	@RequestMapping(value = "getBillChargeAdjBeforeReportList", method = RequestMethod.POST)
	public String getBillChargeAdjBeforeReportList(Model model, BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO, HttpServletRequest request,
			String sidx, 
			String sord, 
			int page, 
			int rows) {
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		Map<String, Object> getChargeAdjustReportInfo = billingBeforeAdjustSearchService.getBillChargeAdjustReportList(billingBeforeAdjustSearchVO, sidx, sord, page, rows, lng);
		
		model.addAttribute("billChargeAdjustReportList", getChargeAdjustReportInfo.get("billChargeAdjustReportList"));
		model.addAttribute("totalCount", getChargeAdjustReportInfo.get("totalCount"));
		model.addAttribute("totalPages", getChargeAdjustReportInfo.get("totalPages"));
		model.addAttribute("page", getChargeAdjustReportInfo.get("page"));
		
		return URL + "/billingBeforeAdjustSearch";
	}
	
	@RequestMapping(value="openBeforeAdjSearhReqPopup" , method = RequestMethod.POST)
	public String openBeforeAdjSearhReqPopup(BillingAdjustVO billingAdjustVO, Model model, HttpServletRequest request) {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		billingAdjustVO.setLngTyp(lngTyp);
		
		System.out.println(">> openCustChngHistPopup << ");
		System.out.println("## PYM_ACNT_ID : " +billingAdjustVO.getPymAcntId());
		System.out.println("## CTRT_ID : " +billingAdjustVO.getCtrtId());
		//System.out.println("## PYM_ACNT_NM: " +billingAdjustVO.getPymAcntNm());
		System.out.println("## ADJ_NO : " + billingAdjustVO.getAdjNo());
		System.out.println("## ADJ_PT : " + billingAdjustVO.getAdjPt());
		System.out.println("## BILL_SEQ_NO : " + billingAdjustVO.getBillSeqNo());
		System.out.println("## BILL_YYMM : " + billingAdjustVO.getBillYymm());
		System.out.println("## LNG_TYP : " + billingAdjustVO.getLngTyp());
		
		model.addAttribute("billingAdjust", billingAdjustVO);
		model.addAttribute("basicCustInfo", billingAfterAdjustService.getBasicCustInfo(billingAdjustVO.getSoId(), billingAdjustVO.getPymAcntId()));
		
		return  URL + "/ajax/billingBeforeAdjustSearchReq"; 
	}
	
	@RequestMapping(value="getBeforeAdjTgtList", method = RequestMethod.POST)
	public String getBeforeAdjTgtList(BillingAdjustVO billingAdjust, Model model, HttpServletRequest request,
			String sidx, 
			String sord, 
			int page, 
			int rows) {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage").toString();
		billingAdjust.setLngTyp(lngTyp);
		
		System.out.println(">> Popup List << ");
		System.out.println("## SO_ID : " + billingAdjust.getSoId());
		System.out.println("## ADJ_NO : " + billingAdjust.getAdjNo());
		System.out.println("## ADJ_PT : " + billingAdjust.getAdjPt());
		System.out.println("## LNG_TYP : " + billingAdjust.getLngTyp());
		System.out.println("## BILL_SEQ_NO : " + billingAdjust.getBillSeqNo());
		System.out.println("## BILL_YYMM : " + billingAdjust.getBillYymm());
		
		Map<String, Object> adjTgtList =billingBeforeAdjustSearchService.billingBeforeSearchPopupDtlList(billingAdjust, sidx, sord, page, rows, lngTyp);
		
		model.addAttribute("adjTgtList", adjTgtList.get("billChargeAdjustReportList"));
		model.addAttribute("totalCount", adjTgtList.get("totalCount"));
		model.addAttribute("totalPages", adjTgtList.get("totalPages"));
		model.addAttribute("page", adjTgtList.get("page"));
		return  URL + "/ajax/billingBeforeAdjustSearchReq"; 
	}
	
	@RequestMapping(value="getBillChargeAdjBeforeReportExcel",  method= RequestMethod.POST)
	public String getBillChargeAdjBeforeReportExcel(Model model, BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO, HttpServletRequest request) {
		/*
		 * 데이터 조회
		 */
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		List<Map<String, Object>> list  = billingBeforeAdjustSearchService.listExcel(billingBeforeAdjustSearchVO);

		/*
		 * Header 작성
		 */
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		columnList.add(new ExcelColumnVO(10, "신청일자", "APPL_DTTM_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "희망적용년월", "HOPE_APLY_YYMM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "납부계정명", "PYM_ACNT_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "서비스번호", "SVC_TEL_NO", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(25, "조정사유", "ADJ_RESN_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "청구금액", "BILL_AMT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "신청금액", "ADJ_APPL_AMT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "청구반영일자", "BILL_APLY_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "진행상태", "DCSN_PROC_STAT_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "신청자명", "RCPT_PSN_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "결재자명", "APPRR_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "청구년월", "BILL_YYMM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "청구일자", "BILL_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "납기마감일자", "PAY_DUE_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "신청번호", "ADJ_NO", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(25, "신청사유", "ADJ_APPL_CONTS", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "수정자", "CHGR_ID_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, "수정일시", "CHG_DTTM", ExcelFormatType.DATE));
		

		/*
		 * 데이터 세팅
		 */
		List<ExcelRowVO> rowList = new ArrayList<ExcelRowVO>();
		for(Map<String,Object> row : list){
			ExcelRowVO rowVo = new ExcelRowVO();
			//Row
			Map<String, ExcelCellVO> rowMap = new HashMap<String, ExcelCellVO>();
			for(ExcelColumnVO col : columnList){
				//Col 세팅
				ExcelCellVO cell = new ExcelCellVO();
				cell.setValue(row.get(col.getKey()));
				rowMap.put(col.getKey(), cell);
			}
			rowVo.setRowData(rowMap);
			rowList.add(rowVo);
		}
		
		/*
		 * Sheet 작성
		 */
		List<ExcelSheetVO> shList = new ArrayList<ExcelSheetVO>();
		ExcelSheetVO sh = new ExcelSheetVO();
		sh.setSheetName("Billing Bill Charge Adjust Before");
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);
		
		/*
		 * 파일작성
		 */
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName("Billing Adjust Before.xlsx");
		file.setSheetCount(1);
		file.setSheetList(shList);
		
		/*
		 * Model Set
		 */
		model.addAttribute("excelDataFile", file);
		return "excelXlsxView";
	}
	
	@RequestMapping(value="getPopBillChargeAdjBeforeReportExcel",  method= RequestMethod.POST)
	public String getPopBillChargeAdjBeforeReportExcel(BillingAdjustVO billingAdjust, Model model, HttpServletRequest request) {
		/*
		 * 데이터 조회
		 */
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		List<Map<String, Object>> list  = billingBeforeAdjustSearchService.popUpListExcel(billingAdjust);
		
		/*
		 * Header 작성
		 */
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		columnList.add(new ExcelColumnVO(10, "고객명", "CUST_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "계약ID", "CTRT_ID", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "서비스번호", "SVC_TEL_NO", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(25, "상품명", "PROD_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "서비스명", "SVC_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "요금항목", "CHRG_ITM_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "조정전청구금액", "ADJ_PRV_BILL_AMT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "조정금액", "ADJ_AMT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "청구금액", "ADJ_AFT_BILL_AMT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "신청금액", "ADJ_APPL_AMT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, "조정반영예상금액", "PRE_ADJ_APPL_AMT", ExcelFormatType.STRING));
		
		/*
		 * 데이터 세팅
		 */
		List<ExcelRowVO> rowList = new ArrayList<ExcelRowVO>();
		for(Map<String,Object> row : list){
			ExcelRowVO rowVo = new ExcelRowVO();
			//Row
			Map<String, ExcelCellVO> rowMap = new HashMap<String, ExcelCellVO>();
			for(ExcelColumnVO col : columnList){
				//Col 세팅
				ExcelCellVO cell = new ExcelCellVO();
				cell.setValue(row.get(col.getKey()));
				rowMap.put(col.getKey(), cell);
			}
			rowVo.setRowData(rowMap);
			rowList.add(rowVo);
		}
		
		/*
		 * Sheet 작성
		 */
		List<ExcelSheetVO> shList = new ArrayList<ExcelSheetVO>();
		ExcelSheetVO sh = new ExcelSheetVO();
		sh.setSheetName("Billing Bill Charge Adjust Before Detail");
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);
		
		/*
		 * 파일작성
		 */
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName("Billing Adjust Before Detail.xlsx");
		file.setSheetCount(1);
		file.setSheetList(shList);
		
		/*
		 * Model Set
		 */
		model.addAttribute("excelDataFile", file);
		return "excelXlsxView";
		
	}
}
