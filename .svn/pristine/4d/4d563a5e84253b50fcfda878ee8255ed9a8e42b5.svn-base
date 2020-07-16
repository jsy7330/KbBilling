package com.ntels.ccbs.product.controller.usageProduct.usageProductMgt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.ManageRatingDef;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.ManageRatingDefService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Controller
@RequestMapping(value = "/product/usageProduct/usageProductMgt/newProductDtl")
public class NewProductDtlController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ManageRatingDefService manageRatingDefService;
	
	private String thisUrl = "product/usageProduct/usageProductMgt/newProductDtl";
	
	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
    
	/**
	 * manageRatingDefList
	 * 설명 : 과금정의관리 목록 조회 페이지 호출
	 */
	@RequestMapping(value = "newProductDtl", method = RequestMethod.POST)
	public String getChargeList(Model model, ManageRatingDef manageRatingDef, HttpServletRequest request) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		//String searchAttrNm = manageRatingDef.getSearchAttrNm();
		//manageRatingDef.setSearchAttrNm(searchAttrNm);
		
		model.addAttribute("listIfSys", commonDataService.getCommonCodeList("PP00111", lngTyp));//데이터유형 셀렉트박스에 들어갈 내용
		return thisUrl + "/newProductDtl";
	}
	
	
	/** 
	 * manageRateingDefListAction
	 * 설	명 : 과금정의 목록 조
	 * @param manageRatingDef
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "manageRatingDefListAction", method = RequestMethod.POST)
	public void manageRatingDefListAction(
			ManageRatingDef manageRatingDef
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		manageRatingDef.setLngTyp(lngTyp);
		manageRatingDef.setSidx(sidx);
		manageRatingDef.setSord(sord);
		
		//오늘날짜 가져오기
        String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
        //manageRatingDef.setCurrentDay(currentDay);
		
        List<ManageRatingDef> list = new ArrayList<ManageRatingDef>();
        
        int count = 0;
		count = manageRatingDefService.getChargeListCount(manageRatingDef);		
		
		if (count > 0) {
			list = manageRatingDefService.getChargeList(manageRatingDef, page, perPageRow);
		}
		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	/**
	 * manageRatingDefInsert
	 * 설명 : 과금정의등록 팝업 Layer
	 * @param manageRatingDef
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "newProductDtlPopUp", method = RequestMethod.POST)
	public String manageRatingDefInsert(
			ManageRatingDef manageRatingDef
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String dataTypCd = "PP00265"; 		
		 
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		//manageRatingDef.setCurrentDay(currentDay);
		System.out.println("currentDay==>" + currentDay);
		
		//속성유형 셀렉트 박스에 들어갈 속성 유형 
		model.addAttribute("listCommon", commonDataService.getCommonCodeList(dataTypCd, lngTyp));
		model.addAttribute("lngTyp", lngTyp);
		
		return  thisUrl + "/ajax/newProductDtlPopUp";
	}
	
	/**
	 * manageRatingDefUpdate
	 * 설명 : 과금정의등록 팝업 Layer
	 * @param manageRatingDef
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "manageRatingDefUpdatePopUp", method = RequestMethod.POST)
	public String manageRatingDefUpdate(
			ManageRatingDef manageRatingDef
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String dataTypCd = "PP00265"; 		
		 
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		//anageRatingDef.setCurrentDay(currentDay);
		System.out.println("currentDay==>" + currentDay);
	
		//속성유형 셀렉트 박스에 들어갈 속성 유형 
		model.addAttribute("listCommon", commonDataService.getCommonCodeList(dataTypCd, lngTyp));
		model.addAttribute("lngTyp",lngTyp);
		
		return  thisUrl + "/ajax/manageRatingDefUpdatePopUp";
	}
	
	/**
	 * delCharge
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "delCharge", method = RequestMethod.POST)
	public void delCharge(
			ManageRatingDef manageRatingDef,
			Model model, 
			HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		String resultUrl = thisUrl + "/manageRatingDefList";
		manageRatingDef.setUserId(session.getUserId());
		
		int result = manageRatingDefService.delCharge(manageRatingDef);

		model.addAttribute("result", result);
	}
	
	/**
	 * delCharge
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "addCharge", method = RequestMethod.POST)
	public void addCharge(
			ManageRatingDef manageRatingDef,
			Model model, 
			HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		String resultUrl = thisUrl + "/manageRatingDefList";
		manageRatingDef.setUserId(session.getUserId());
		
		int result = manageRatingDefService.addCharge(manageRatingDef);

		model.addAttribute("result", result);
	}
	
	/**
	 * delCharge
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "modCharge", method = RequestMethod.POST)
	public void modCharge(
			ManageRatingDef manageRatingDef,
			Model model, 
			HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		String resultUrl = thisUrl + "/manageRatingDefList";
		manageRatingDef.setUserId(session.getUserId());
		
		int result = manageRatingDefService.modCharge(manageRatingDef);

		model.addAttribute("result", result);
	}
	
	/*
	@RequestMapping(value = "manageRatingDefExcelAction", method = RequestMethod.POST)
	public String manageRatingDefExcelAction(
			 Model model 
			,NewSinglePaymentVO newSinglePaymentVO 
			,HttpServletRequest request
			,String billStrtYymm
			,String billEndYymm
			,String soId
			,String pymAcntId) {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		newSinglePaymentVO.setLngTyp(lngTyp);
		newSinglePaymentVO.setUsrId(session.getUserId());
		
		List<Map<String,Object>> list = newSinglePaymentService.listAnonyReceiptSubExcel(billStrtYymm, billEndYymm, soId, pymAcntId, lngTyp);
		
		if(list != null) {
			Map<String, Object> excelMap = new HashMap<String, Object>();
			for(int i = 0; i < list.size(); i++) {
				String billYymm = (String) list.get(i).get("BILL_YYMM");
				String billDate = (String) list.get(i).get("BILL_DATE");
				String paymentDate = (String) list.get(i).get("PAYMENT_DT");
				String rcptProcDate = (String) list.get(i).get("RECEIPT_PROCESSING_DT");
				
				String formatBillYymm = DateUtil.getLngDateFormat_yymm(billYymm, lngTyp);
				String formatBillDate = DateUtil.getLngDateFormat(billDate, lngTyp);
				String formatPaymentDate = DateUtil.getLngDateFormat(paymentDate, lngTyp);
				String formatRcptProcDate = DateUtil.getLngDateFormat(rcptProcDate, lngTyp);
				
				list.get(i).put("BILL_YYMM", formatBillYymm);
				list.get(i).put("BILL_DATE", formatBillDate);
				list.get(i).put("PAYMENT_DT", formatPaymentDate);
				list.get(i).put("RECEIPT_PROCESSING_DT", formatRcptProcDate);		
			}
		}
		
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M10.LAB00040"), "BILL_DATE", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M10.LAB00033"), "BILL_YYMM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M09.LAB00137"), "BILL_AMT_BFR_ADJ", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M09.LAB00134"), "ADJ_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M10.LAB00031"), "BILL_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M07.LAB00237"), "RECEIPT_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M05.LAB00048"), "UNPAID_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M02.LAB00004"), "PAYMENT_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M10.LAB00030"), "BILLING_CATEGORY", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(12, MessageUtil.getMessage("LAB.M08.LAB00043"), "FULL_PAY_YN", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(12, MessageUtil.getMessage("LAB.M07.LAB00212"), "SMALL_AMT_YN", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M07.LAB00243"), "RECEIPT_PROCESSING_DATE", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M07.LAB00236"), "RECEIPT_TYPE", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M07.LAB00194"), "OVER_PAYMENT_APLY", ExcelFormatType.NUMBER_WITH_COMMA));
		
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
		
		List<ExcelSheetVO> shList = new ArrayList<ExcelSheetVO>();
		ExcelSheetVO sh = new ExcelSheetVO();
		sh.setSheetName(MessageUtil.getMessage("LAB.M10.LAB00032"));
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);
		
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName(MessageUtil.getMessage("LAB.M10.LAB00032"));
		file.setSheetCount(1);
		file.setSheetList(shList);
		
		
		model.addAttribute("excelDataFile", file);
		
		return "excelXlsxView";
	}
*/

}