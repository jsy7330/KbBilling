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
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.ManageRatingDef;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.ManageRatingDefService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/manageRatingDef")
public class ManageRatingDefController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ManageRatingDefService manageRatingDefService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/manageRatingDef";
	
	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
    
	/**
	 * manageRatingDefList
	 * 설명 : 과금정의관리 목록 조회 페이지 호출
	 */
	@RequestMapping(value = "manageRatingDefList", method = RequestMethod.POST)
	public String getChargeList(
			Model model
			, ManageRatingDef manageRatingDef
			, HttpServletRequest request
			) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		
		return thisUrl + "/manageRatingDefList";
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
		String sidx = request.getParameter("sidx"); 
		String sord  = request.getParameter("sord");
		
		manageRatingDef.setLngTyp(lngTyp);
		manageRatingDef.setSidx(sidx);
		manageRatingDef.setSord(sord);
		
        List<ManageRatingDef> list = new ArrayList<ManageRatingDef>();
        
		list = manageRatingDefService.getChargeList(manageRatingDef, page, perPageRow);
		
		model.addAttribute("rows", list);				// 목록리스트
		model.addAttribute("records", list.size()); 	//현화면의 리스트갯수		
		model.addAttribute("page", page); 				//현재 페이지
		model.addAttribute("perpage", perPageRow);
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
	}
	
	/**
	 * manageRatingDefInsert
	 * 설명 : 과금정의등록 팝업 Layer
	 * @param manageRatingDef
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "manageRatingDefInsertPopUp", method = RequestMethod.POST)
	public String manageRatingDefInsertPopUp(
			ManageRatingDef manageRatingDef
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		 
		//속성유형 셀렉트 박스에 들어갈 속성 유형 
		model.addAttribute("lngTyp", lngTyp);
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		
		return  thisUrl + "/ajax/manageRatingDefInsertPopUp";
	}
	
	/**
	 * addCharge
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "manageRatingDefInsertAction", method = RequestMethod.POST)
	public String addCharge(
			ManageRatingDef manageRatingDef,
			Model model, 
			HttpServletRequest request) {
		
		String resultUrl = thisUrl + "/manageRatingDefList";
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		manageRatingDef.setEffDt(manageRatingDef.getEffDt().replaceAll("-", "") + "000000");
		manageRatingDef.setExpDt("99991231235959");
		manageRatingDef.setUserId(session.getUserId());
		manageRatingDef.setSysToDate(DateUtil.sysdate());
		
		if( manageRatingDefService.getChargeListCount(manageRatingDef) == 0 ) {
			if( manageRatingDefService.addCharge(manageRatingDef) > 0) {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			} else {
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}
			model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
			return resultUrl;
		}
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00049"));

		return resultUrl;
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

		model.addAttribute("listCommon", commonDataService.getCommonCodeList(dataTypCd, lngTyp));
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
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
	@RequestMapping(value = "manageRatingDefUpdateAction", method = RequestMethod.POST)
	public String modCharge(
			ManageRatingDef manageRatingDef,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String resultUrl = thisUrl + "/manageRatingDefList";
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		manageRatingDef.setUserId(session.getUserId());
		manageRatingDef.setSysToDate(DateUtil.sysdate());
		manageRatingDef.setEffDt(manageRatingDef.getEffDt().replace("-", "") + "000000");
		
		System.out.println("!!!!!!!!!!!!!!!!!!" + manageRatingDef.toString());
	
		if( manageRatingDefService.modCharge(manageRatingDef) > 0) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}

		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		
		return resultUrl;
	}
	
	/**
	 * delCharge
	 * 설명 : 
	 * @param 
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "manageRatingDefDeleteAction", method = RequestMethod.POST)
	public String delCharge(
			ManageRatingDef manageRatingDef,
			Model model, 
			HttpServletRequest request) {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String resultUrl = thisUrl + "/manageRatingDefList";
		
		manageRatingDef.setUserId(session.getUserId());

		if( manageRatingDefService.delCharge(manageRatingDef) > 0) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		
		return resultUrl;
	}
	
	@RequestMapping(value = "manageRatingDefListExcelAction", method = RequestMethod.POST)
	public String manageRatingDefListExcel(
		Model model 
		,ManageRatingDef manageRatingDef 
		,HttpServletRequest request
		,String chrgCd
		,String description
		,String usgTyp
		,String usgTypNm) {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		manageRatingDef.setUserId(session.getUserId());
		manageRatingDef.setLngTyp(lngTyp);
		
		List<Map<String,Object>> list = manageRatingDefService.manageRatingDefListExcel(manageRatingDef, lngTyp, chrgCd, description, usgTyp);
		
		/*
		 * Header 작성
		 */
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		
		columnList.add(new ExcelColumnVO(27, MessageUtil.getMessage("LAB.M01.LAB00141"), "CHRG_CD", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(27, MessageUtil.getMessage("LAB.M01.LAB00115"), "DESCRIPTION", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00029"), "USG_TYP", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(27, MessageUtil.getMessage("LAB.M07.LAB00050"), "USG_TYP_NM", ExcelFormatType.STRING));
	
		/*
		 * 데이터 세팅
		 */
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
		/*
		 * Sheet 작성
		 */
		List<ExcelSheetVO> shList = new ArrayList<ExcelSheetVO>();
		ExcelSheetVO sh = new ExcelSheetVO();
		sh.setSheetName(MessageUtil.getMessage("LAB.M01.LAB00134"));
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);
		
		/*
		 * 파일작성
		 */
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName(MessageUtil.getMessage("LAB.M01.LAB00134"));
		file.setSheetCount(1);
		file.setSheetList(shList);
		
		/*
		 * Model Set
		 */
		model.addAttribute("excelDataFile", file);
		model.addAttribute("usgTypList", commonDataService.getCommonCodeList("PP00303", lngTyp));
		
		return "excelXlsxView";
	}
}