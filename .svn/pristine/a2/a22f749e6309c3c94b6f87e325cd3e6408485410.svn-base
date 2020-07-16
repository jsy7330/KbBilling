package com.ntels.ccbs.product.controller.refInfo.ratingRefInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts.ExcelFormatType;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.common.view.ExcelCellVO;
import com.ntels.ccbs.common.view.ExcelColumnVO;
import com.ntels.ccbs.common.view.ExcelFileVO;
import com.ntels.ccbs.common.view.ExcelRowVO;
import com.ntels.ccbs.common.view.ExcelSheetVO;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.UsageTypeMng;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.UsageTypeMngService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/usageType")
public class UsageTypeMngController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private UsageTypeMngService usageTypeMngService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/usageType";
	
	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
    
	/**
	 * usageTypeMngList
	 * 설명 : 과금정의관리 목록 조회 페이지 호출
	 */
	@RequestMapping(value = "usageTypeMngList", method = RequestMethod.POST)
	public String getChargeList(
			Model model
			, UsageTypeMng usageTypeMng
			, HttpServletRequest request
			) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		return thisUrl + "/usageTypeMngList";
	}
	
	

	@RequestMapping(value = "usageTypeMngListAction", method = RequestMethod.POST)
	public void usageTypeMngListAction(
			UsageTypeMng usageTypeMng
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		usageTypeMng.setLngTyp(lngTyp);
		usageTypeMng.setSidx(sidx);
		usageTypeMng.setSord(sord);
		
		//오늘날짜 가져오기
        String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
        usageTypeMng.setCurrentDay(currentDay);
		
        List<UsageTypeMng> list = new ArrayList<UsageTypeMng>();
        
        int count = usageTypeMngService.getUsageTypeMngListCount(usageTypeMng);
        
		list = usageTypeMngService.getUsageTypeMngList(usageTypeMng, page, perPageRow);
		
		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수	
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}

	@RequestMapping(value = "usageTypeMngInsertPopUp", method = RequestMethod.POST)
	public String usageTypeMngInsertPopUp(
			UsageTypeMng usageTypeMng
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
				
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		usageTypeMng.setCurrentDay(currentDay);

		//속성유형 셀렉트 박스에 들어갈 속성 유형 
		model.addAttribute("usgOfferTypList", commonDataService.getCommonCodeList("PP00262", lngTyp));
		model.addAttribute("cdrIndicatorList", commonDataService.getCommonCodeList("PP00265", lngTyp));
		model.addAttribute("multipleDiscMethodList", commonDataService.getCommonCodeList("PP00249", lngTyp));
		model.addAttribute("periodApplMethodList", commonDataService.getCommonCodeList("PP00216", lngTyp));
		model.addAttribute("roundingList", commonDataService.getCommonCodeList("PP00232", lngTyp));
	
		model.addAttribute("lngTyp",lngTyp);
		
		return  thisUrl + "/ajax/usageTypeMngInsertPopUp";
	}

	@RequestMapping(value = "usageTypeMngInsertAction", method = RequestMethod.POST)
	public String addCharge(
			UsageTypeMng usageTypeMng,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		usageTypeMng.setInsertEffDt(usageTypeMng.getInsertEffDt().replaceAll("-", "") + "000000");
		usageTypeMng.setInsertExpDt(usageTypeMng.getInsertExpDt().replaceAll("-", "") + "235959");
		usageTypeMng.setSearchUsgTyp(usageTypeMng.getInsertUsgTyp());
		usageTypeMng.setSearchUsgTypNm("");
		usageTypeMng.setUserId(session.getUserId());
		usageTypeMng.setSysToDate(DateUtil.sysdate());
		usageTypeMng.setUsgTyp(usageTypeMng.getInsertUsgTyp());
		usageTypeMng.setEffDt(usageTypeMng.getInsertEffDt());

		if( usageTypeMngService.getUsageTypeMngRepCheckCount(usageTypeMng) == 0 ) {
			if( usageTypeMngService.addUsageTypeMng(usageTypeMng) > 0 ) {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			}
			else {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}
			return thisUrl + "/usageTypeMngList";
		}

		return thisUrl + "/usageTypeMngList";
	}
	
	@RequestMapping(value = "usageTypeMngUpdatePopUp", method = RequestMethod.POST)
	public String usageTypeMngUpdate(
			UsageTypeMng usageTypeMng
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		usageTypeMng.setCurrentDay(currentDay);
		
		model.addAttribute("usgOfferTypList", commonDataService.getCommonCodeList("PP00262", lngTyp));
		model.addAttribute("cdrIndicatorList", commonDataService.getCommonCodeList("PP00265", lngTyp));
		model.addAttribute("multipleDiscMethodList", commonDataService.getCommonCodeList("PP00249", lngTyp));
		model.addAttribute("periodApplMethodList", commonDataService.getCommonCodeList("PP00216", lngTyp));
		model.addAttribute("roundingList", commonDataService.getCommonCodeList("PP00232", lngTyp));
	
		model.addAttribute("lngTyp",lngTyp);
		
		return  thisUrl + "/ajax/usageTypeMngUpdatePopUp";
	}
	
	@RequestMapping(value = "usageTypeMngUpdateAction", method = RequestMethod.POST)
	public String modCharge(
			UsageTypeMng usageTypeMng,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		usageTypeMng.setUserId(session.getUserId());
		usageTypeMng.setSysToDate(DateUtil.sysdate());
		usageTypeMng.setModEffDt(usageTypeMng.getModEffDt().replaceAll("-", "") + "000000");
		usageTypeMng.setModExpDt(usageTypeMng.getModExpDt().replaceAll("-", "") + "235959");
		
		if( usageTypeMngService.modUsageTypeMng(usageTypeMng) > 0) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}
		
		return thisUrl + "/usageTypeMngList";
	}

	@RequestMapping(value = "usageTypeMngDeleteAction", method = RequestMethod.POST)
	public String delCharge(
			UsageTypeMng usageTypeMng,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String resultUrl = thisUrl + "/usageTypeMngList";
		
		usageTypeMng.setUserId(session.getUserId());
		
		if( usageTypeMngService.delUsageTypeMng(usageTypeMng) > 0) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		
		return resultUrl;
	}
	
	@RequestMapping(value = "usageTypeMngListExcelAction", method = RequestMethod.POST)
	public String usageTypeMngListExcel(
		  Model model
		, String usgTypNm
		, UsageTypeMng usageTypeMng 
		, HttpServletRequest request
		) {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		usageTypeMng.setUserId(session.getUserId());
		usageTypeMng.setLngTyp(lngTyp);
		
		List<Map<String,Object>> list = usageTypeMngService.usageTypeMngListExcel(usageTypeMng, usgTypNm);
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00052"), "USG_TYP", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00050"), "USG_TYP_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00078"), "USG_OFFER_TYP", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M14.LAB00058"), "CDR_INDICATOR_NM", ExcelFormatType.STRING));		
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M09.LAB00194"), "MULTIPLE_DISC_METHOD_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(22, MessageUtil.getMessage("LAB.M01.LAB00214"), "PERIOD_APPL_METHOD_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(22, MessageUtil.getMessage("LAB.M01.LAB00208"), "DEFAULT_NO_OF_UNITS", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(22, MessageUtil.getMessage("LAB.M01.LAB00209"), "DEFAULT_USG_ROUNDING_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(22, MessageUtil.getMessage("LAB.M01.LAB00207"), "DEFAULT_USG_ROUNDING_OFFSET", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(22, MessageUtil.getMessage("LAB.M01.LAB00206"), "DEFAULT_USG_FEE_ROUNDING_NM", ExcelFormatType.STRING));
		
		List<ExcelRowVO> rowList = new ArrayList<ExcelRowVO>();
		for(Map<String,Object> row : list) {
			ExcelRowVO rowVo = new ExcelRowVO();
			//Row
			Map<String, ExcelCellVO> rowMap = new HashMap<String, ExcelCellVO>();
			
			for( ExcelColumnVO col : columnList ) {
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
		sh.setSheetName("사용유형관리");
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);
	
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName("사용유형관리");
		file.setSheetCount(1);
		file.setSheetList(shList);

		model.addAttribute("excelDataFile", file);
		
		return "excelXlsxView";
	}
}