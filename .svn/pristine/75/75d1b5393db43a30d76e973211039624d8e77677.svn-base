package com.ntels.ccbs.system.controller.log.logMng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.ntels.ccbs.system.service.log.logMng.AccessLogHistService;
import com.ntels.nisf.util.message.MessageUtil;

/**
 * <PRE>
 * 1. ClassName: AccessLogHistController
 * 2. FileName : AccessLogHistController.java
 * 3. Package  : com.ntels.ccbs.system.controller.log.logMng
 * 4. Comment  : 접속로그조회 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 5:58:57
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/log/logMng/accessLogHist")
public class AccessLogHistController {
	/**
	 * 접속로그조회 메인URL
	 */
	private static String URL = "system/log/logMng/accessLogHist";
	
	/**
	 * 접속로그조회 서비스
	 */
	@Autowired AccessLogHistService accessLogHistService;
	
	/**
	 * <PRE>
	 * 1. MethodName: accssLogHist
	 * 2. ClassName : AccessLogHistController
	 * 3. Comment   : 접속로그조회 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:59:08
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param access 접속로그조회VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "accssLogHist", method = RequestMethod.POST)
	public String accssLogHist(Model model,AccessLogHistVO access,HttpServletRequest request) {
		String weekAgo = DateUtil.getDateStringYYYYMMDDHH24MISS(-7);
		model.addAttribute("sdate", weekAgo);
		return URL + "/accssLogHist";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: mainListAction
	 * 2. ClassName : AccessLogHistController
	 * 3. Comment   : 접속로그조회 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:59:11
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param access 접속로그조회VO
	 *   @param request {@link HttpServletRequest}
	 *   @param srchYn 검색여부
	 *   @param condUserId 사용자아이디 조건
	 *   @param sdate 시작날짜
	 *   @param edate 끝날짜
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 */
	@RequestMapping(value = "mainListAction", method = RequestMethod.POST)
	public String mainListAction(Model model,AccessLogHistVO access,HttpServletRequest request
			,String srchYn
			,String condUserId
			,String sdate
			,String edate
			,String sidx
			,String sord
			,int page
			,int rows) {
		
		if(StringUtils.isEmpty(srchYn)){
			return URL + "/ajax/accssLogHist";
		}
		
		access.setCondUserId(condUserId);
		access.setSdate(sdate);
		access.setEdate(edate);
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> accssInfo = accessLogHistService.list(sidx,sord, page, rows, lng,access);
		model.addAttribute("accssList", accssInfo.get("accssList"));
		model.addAttribute("totalCount", accssInfo.get("totalCount"));
		model.addAttribute("totalPages", accssInfo.get("totalPages"));
		model.addAttribute("page", accssInfo.get("page"));
		
		return URL + "/accssLogHist";
	}
	
	
	@RequestMapping(value = "getAccessLogExcelAction", method = RequestMethod.POST)
	public String getAccessLogExcelAction(Model model,AccessLogHistVO access,HttpServletRequest request
			,String condUserId
			,String sdate
			,String edate) {
		
		/*
		 * 데이터 조회
		 */
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		List<Map<String,Object>> list = accessLogHistService.listExcel(condUserId, sdate, edate, lng);
		
		/*
		 * Header 작성
		 */
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M07.LAB00067"), "USER_ID", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M07.LAB00075"), "USER_NAME", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M09.LAB00139"), "ORG_ID", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M09.LAB00154"), "ORG_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(20, MessageUtil.getMessage("LAB.M07.LAB00069"), "REMOTE_ADDR", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(30, MessageUtil.getMessage("LAB.M07.LAB00210"), "SESSION_ID", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(20, MessageUtil.getMessage("LAB.M04.LAB00010"), "FULL_LOGIN", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(20, MessageUtil.getMessage("LAB.M04.LAB00005"), "FULL_LOGOUT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M04.LAB00004"), "LOGOUT_STATUS", ExcelFormatType.STRING));

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
		sh.setSheetName("Access Log History");
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);
		
		/*
		 * 파일작성
		 */
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName("Access Log History");
		file.setSheetCount(1);
		file.setSheetList(shList);
		
		
		/*
		 * Model Set
		 */
		model.addAttribute("excelDataFile", file);
		
		return "excelXlsxView";
	}
}