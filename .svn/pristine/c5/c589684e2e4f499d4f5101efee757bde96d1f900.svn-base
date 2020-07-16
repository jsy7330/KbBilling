package com.ntels.ccbs.system.controller.log.logMng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.apache.bcel.util.SyntheticRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ntels.ccbs.system.domain.log.logMng.AccessLogHistVO;
import com.ntels.ccbs.system.domain.log.logMng.ProcessLogHistVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.log.logMng.ProcessLogHistService;
import com.ntels.nisf.util.message.MessageUtil;

/**
 * <PRE>
 * 1. ClassName: ProcessLogHistController
 * 2. FileName : ProcessLogHistController.java
 * 3. Package  : com.ntels.ccbs.system.controller.log.logMng
 * 4. Comment  : 사용이력조회 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 6:12:02
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/log/logMng/processLogHist")
public class ProcessLogHistController {
	/**
	 * 사용이력조회 메인 URL
	 */
	private static String URL = "system/log/logMng/processLogHist";
	
	/**
	 * 사용이력조회 서비스
	 */
	@Autowired ProcessLogHistService processLogHistService;
	/**
	 * 공통코드 조회 서비스
	 */
	@Autowired
	private CommonDataService commonDataService;
	/**
	 * <PRE>
	 * 1. MethodName: prcssLogHist
	 * 2. ClassName : ProcessLogHistController
	 * 3. Comment   : 사용이력조회 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 6:12:37
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param process 사용이력VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "prcssLogHist", method = RequestMethod.POST)
	public String prcssLogHist(Model model,ProcessLogHistVO process,HttpServletRequest request) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("workTypeList", commonDataService.getCommonCodeList("SY00004", lng));
		String weekAgo = DateUtil.getDateStringYYYYMMDDHH24MISS(-7);
		model.addAttribute("sdate", weekAgo);
		return URL + "/prcssLogHist";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: mainListAction
	 * 2. ClassName : ProcessLogHistController
	 * 3. Comment   :사용이력조회 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 6:12:41
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param process 사용이력VO
	 *   @param srchYn 검색여부
	 *   @param condUserId 사용자아이디 조건
	 *   @param sdate 시작날짜
	 *   @param edate 끝날짜
	 *   @param condWorkType 작업유형 조건
	 *   @param condSessionId 세션아이디 조건
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 */
	@RequestMapping(value = "mainListAction", method = RequestMethod.POST)
	public String mainListAction(Model model,ProcessLogHistVO process,HttpServletRequest request
			,String srchYn
			,String condUserId
			,String sdate
			,String edate
			,String condWorkType
			,String condSessionId
			,String sidx
			,String sord
			,int page
			,int rows) {
		
		if(StringUtils.isEmpty(srchYn)){
			return URL + "/ajax/prcssLogHist";
		}
		
		process.setCondUserId(condUserId);
		process.setCondWorkType(condWorkType);
		process.setSdate(sdate);
		process.setEdate(edate);
		process.setCondSessionId(condSessionId);
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> prcssInfo = processLogHistService.list(sidx,sord, page, rows, lng,process);
		model.addAttribute("prcssList", prcssInfo.get("prcssList"));
		model.addAttribute("totalCount", prcssInfo.get("totalCount"));
		model.addAttribute("totalPages", prcssInfo.get("totalPages"));
		model.addAttribute("page", prcssInfo.get("page"));
		
		return URL + "/prcssLogHist";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getProcessLogExcelAction
	 * 2. ClassName : ProcessLogHistController
	 * 3. Comment   :  사용이력조회 엑셀다운로드
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 28. 오후 2:19:59
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param process 사용이력VO
	 *   @param request {@link HttpServletRequest}
	 *   @param condUserId 사용자아이디 조건
	 *   @param sdate 시작날짜
	 *   @param edate 마지막 날짜
	 *   @param condSessionId 세션아이디 조건
	 *   @param condWorkType 사용유형 조건
	 */
	@RequestMapping(value = "getProcessLogExcelAction", method = RequestMethod.POST)
	public String getProcessLogExcelAction(Model model,ProcessLogHistVO process,HttpServletRequest request
			,String condUserId
			,String sdate
			,String edate
			,String condSessionId
			,String condWorkType
			) {
		/*
		 * 데이터 조회
		 */
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		List<Map<String,Object>> list =processLogHistService.listExcel(condUserId,sdate,edate,lng,condSessionId,condWorkType);
		
		/*
		 * Header 작성
		 */
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M09.LAB00028"), "WORK_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M07.LAB00258"), "SEQ", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M07.LAB00067"), "USER_ID", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M07.LAB00071"), "USER_NAME", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M09.LAB00022"), "WORK_TYPE", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(20, MessageUtil.getMessage("LAB.M09.LAB00027"), "WORK_DATE", ExcelFormatType.DATE));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M05.LAB00027"), "MENU_NO", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(20, MessageUtil.getMessage("LAB.M05.LAB00028"), "MENU_NAME", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M07.LAB00164"), "SERVER_NAME", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M07.LAB00165"), "SERVER_PORT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(50, MessageUtil.getMessage("LAB.M15.LAB00038"), "ACCEPT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(70, MessageUtil.getMessage("LAB.M15.LAB00040"), "USER_AGENT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(40, MessageUtil.getMessage("LAB.M15.LAB00039"), "CONTENT_TYPE", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M07.LAB00069"), "REMOTE_ADDR", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(30, MessageUtil.getMessage("LAB.M07.LAB00210"), "SESSION_ID", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(50, MessageUtil.getMessage("LAB.M14.LAB00060"), "REQUEST_PATH", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M14.LAB00061"), "REQUEST_METHOD", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(70, MessageUtil.getMessage("LAB.M02.LAB00034"), "PAYLOAD", ExcelFormatType.STRING));
		
		
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
		sh.setSheetName("Process Log History");
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);
		
		/*
		 * 파일작성
		 */
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName("Process Log History.xlsx");
		file.setSheetCount(1);
		file.setSheetList(shList);
		
		
		/*
		 * Model Set
		 */
		model.addAttribute("excelDataFile", file);
		return "excelXlsxView";
	}
	
}