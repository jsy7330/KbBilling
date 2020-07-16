package com.ntels.ccbs.customer.controller.statistics.statisticsMgt;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.product.domain.refInfo.commonInfo.AttrTypMap;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.log.logMng.AccessLogHistVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.consts.Consts.ExcelFormatType;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.common.view.ExcelCellVO;
import com.ntels.ccbs.common.view.ExcelColumnVO;
import com.ntels.ccbs.common.view.ExcelFileVO;
import com.ntels.ccbs.common.view.ExcelRowVO;
import com.ntels.ccbs.common.view.ExcelSheetVO;
import com.ntels.ccbs.customer.domain.statistics.statisticsMgt.StatisticsMgtVO;
import com.ntels.ccbs.customer.service.statistics.statisticsMgt.StatisticsMgtService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/customer/statistics/statisticsMgt")
public class StatisticsMgtController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StatisticsMgtService statisticsMgtService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "customer/statistics/statistics/statisticsMgt";
	
	@RequestMapping(value = "statisticsMgtList", method = RequestMethod.POST)
	public String statisticsMgtList(Model model, StatisticsMgtVO statisticsMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("sqlTpList", commonDataService.getCommonCodeList("CM00056", lng)); //sql 타입
		model.addAttribute("getStatisticeList", statisticsMgtService.getStatisticeList()); //SQL명		

		return thisUrl + "/statisticsMgtList";
	}
	
	@RequestMapping(value = "statisticsMgtListAction", method = RequestMethod.POST)
	public String statisticsMgtListAction(Model model, StatisticsMgtVO statisticsMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String varDefn = statisticsMgtVO.getVarDefn();
		
		if(varDefn == null || varDefn.equals("")){
			model.addAttribute("rows", statisticsMgtService.getInitCodeList(lng));
			model.addAttribute("selList", "");			
		}else if(varDefn != null || !varDefn.equals("")){
			varDefn = varDefn.replaceAll(",", "','");	
			varDefn = "'"+varDefn+"'";
			model.addAttribute("rows", statisticsMgtService.getUseableList(varDefn,lng));	
			model.addAttribute("selList", statisticsMgtService.getSelectedList(varDefn,lng));			
		}

		return thisUrl + "/statisticsMgtList";
	}
	
	@RequestMapping(value = "statisticsMgtListSearch", method = RequestMethod.POST)
	public void statisticsMgtListAction(
			 StatisticsMgtVO statisticsMgtVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		StatisticsMgtVO result = statisticsMgtService.getStatisticeDtl(statisticsMgtVO.getSearchStsCd());
		model.addAttribute("getStatisticeDtl", result); 
	}
	
	@RequestMapping(value = "statisticsMgtUpdateAction", method = RequestMethod.POST)
	public String statisticsMgtUpdateAction(
			@RequestBody StatisticsMgtVO statisticsMgtVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
        String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
        SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
		
        statisticsMgtVO.setVarDefn(statisticsMgtVO.getVarDefn());
		statisticsMgtVO.setStmtCd(statisticsMgtVO.getStmtCd());
		statisticsMgtVO.setStmtTp(statisticsMgtVO.getStmtTp());
		statisticsMgtVO.setSoId(statisticsMgtVO.getSoId());
		statisticsMgtVO.setStmtNm(statisticsMgtVO.getStmtNm());
		statisticsMgtVO.setActDt(statisticsMgtVO.getActDt());
		statisticsMgtVO.setInactDt(statisticsMgtVO.getInactDt());
		statisticsMgtVO.setTmout(statisticsMgtVO.getTmout());
		statisticsMgtVO.setRmrkDesc(statisticsMgtVO.getRmrkDesc());
		statisticsMgtVO.setStmt(statisticsMgtVO.getStmt());
		statisticsMgtVO.setChgrId(session.getUserId());
		statisticsMgtVO.setVldYn("");
		
		if(statisticsMgtService.updateStatistics(statisticsMgtVO)>0) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		}else{
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}
		
		model.addAttribute("sqlTpList", commonDataService.getCommonCodeList("CM00056", lngTyp)); //sql 타입
		model.addAttribute("getStatisticeList", statisticsMgtService.getStatisticeList()); //SQL명	
		
		return thisUrl + "/statisticsMgtList";
	}
	
	@RequestMapping(value = "statisticsMgtInsertAction", method = RequestMethod.POST)
	public String statisticsMgtInsertAction(
			@RequestBody StatisticsMgtVO statisticsMgtVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
        String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
        SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
/*
		System.out.println("getVarDefn==>"+statisticsMgtVO.getVarDefn());
		System.out.println("getStmtTp==>"+statisticsMgtVO.getStmtTp());
		System.out.println("getSoId==>"+statisticsMgtVO.getSoId());
		System.out.println("getStmtNm==>"+statisticsMgtVO.getStmtNm());
		System.out.println("getActDt==>"+statisticsMgtVO.getActDt());
		System.out.println("getInactDt==>"+statisticsMgtVO.getInactDt());
		System.out.println("getTmout==>"+statisticsMgtVO.getTmout());
		System.out.println("getRmrkDesc==>"+statisticsMgtVO.getRmrkDesc());
		System.out.println("getStmt==>"+statisticsMgtVO.getStmt());
*/
        statisticsMgtVO.setVarDefn(statisticsMgtVO.getVarDefn());
		statisticsMgtVO.setStmtTp(statisticsMgtVO.getStmtTp());
		statisticsMgtVO.setSoId(statisticsMgtVO.getSoId());
		statisticsMgtVO.setStmtNm(statisticsMgtVO.getStmtNm());
		statisticsMgtVO.setActDt(statisticsMgtVO.getActDt());
		statisticsMgtVO.setInactDt(statisticsMgtVO.getInactDt());
		statisticsMgtVO.setTmout(statisticsMgtVO.getTmout());
		statisticsMgtVO.setRmrkDesc(statisticsMgtVO.getRmrkDesc());
		statisticsMgtVO.setStmt(statisticsMgtVO.getStmt());
		//statisticsMgtVO.setStmt(URLDecoder.decode(statisticsMgtVO.getStmt(), "UTF-8"));
		statisticsMgtVO.setChgrId(session.getUserId());
		statisticsMgtVO.setVldYn("");
		statisticsMgtService.insertStatistics(statisticsMgtVO);
		
		/*
		if(statisticsMgtService.insertStatistics(statisticsMgtVO)>0) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		}else{
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}
		*/
		model.addAttribute("sqlTpList", commonDataService.getCommonCodeList("CM00056", lngTyp)); //sql 타입
		model.addAttribute("getStatisticeList", statisticsMgtService.getStatisticeList()); //SQL명	
		
		return thisUrl + "/statisticsMgtList";
	}	
	
	@RequestMapping(value = "testSql", method = RequestMethod.POST)
	public String testSql(Model model, StatisticsMgtVO statisticsMgtVO, HttpServletRequest request) throws Exception {
		
	
		String tmout = statisticsMgtVO.getTmout();
		model.addAttribute("colNm",statisticsMgtService.test(URLDecoder.decode(statisticsMgtVO.getStmt(), "UTF-8"),Integer.parseInt(tmout)));
		return thisUrl + "/statisticsMgtList";
	}	
	
	@RequestMapping(value = "statisticsSoSqlList", method = RequestMethod.POST)
	public String statisticsSoSqlList(Model model, StatisticsMgtVO statisticsMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		
		statisticsMgtVO.setStmtTp("1");
		statisticsMgtVO.setSoId(sessionUser.getSoId());
		
		model.addAttribute("getStatisticeList", statisticsMgtService.getStatistice(statisticsMgtVO)); //SQL명		
		model.addAttribute("ctrtStatList", commonDataService.getCommonCodeList("CM00023", lng)); //계약상태
		return thisUrl + "/statisticsSoSqlList";
	}	
	@RequestMapping(value = "statisticsAdminSqlList", method = RequestMethod.POST)
	public String statisticsAdminSqlList(Model model, StatisticsMgtVO statisticsMgtVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		
		//statisticsMgtVO.setStmtTp("1");
		//statisticsMgtVO.setSoId(sessionUser.getSoId());
		
		model.addAttribute("getStatisticeList", statisticsMgtService.getStatistice(statisticsMgtVO)); //SQL명		
		model.addAttribute("ctrtStatList", commonDataService.getCommonCodeList("CM00023", lng)); //계약상태
		return thisUrl + "/statisticsAdminSqlList";
	}		
	@RequestMapping(value = "statisticsStmtList", method = RequestMethod.POST)
	public void statisticsStmtList(
			 StatisticsMgtVO statisticsMgtVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("statisticeList",  statisticsMgtService.getStatisticeSoList(statisticsMgtVO.getSearchSoId())); 
		model.addAttribute("ctrtStatList", commonDataService.getCommonCodeList("CM00023", lng)); //계약상태
	}
	
	@RequestMapping(value = "sqlList", method = RequestMethod.POST)
	public String sqlList(Model model, StatisticsMgtVO statisticsMgtVO, HttpServletRequest request) throws Exception {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
	
		
		System.out.println("getSoId==>"+statisticsMgtVO.getSoId());
		System.out.println("getAcntId==>"+statisticsMgtVO.getAcntId());
		System.out.println("getStrtDd==>"+statisticsMgtVO.getStrtDd());
		System.out.println("getEndDd==>"+statisticsMgtVO.getEndDd());
		System.out.println("getSvcTelNo==>"+statisticsMgtVO.getSvcTelNo());
		System.out.println("getBillBgnMth==>"+statisticsMgtVO.getBillBgnMth());
		System.out.println("getBillEndMth==>"+statisticsMgtVO.getBillEndMth());
		System.out.println("getCtrtStat==>"+statisticsMgtVO.getCtrtStat());
		System.out.println("getCustId==>"+statisticsMgtVO.getCustId());
		System.out.println("getOpenBgnDd==>"+statisticsMgtVO.getOpenBgnDd());
		System.out.println("getOpenEndDd==>"+statisticsMgtVO.getOpenEndDd());
		System.out.println("getSearchDd==>"+statisticsMgtVO.getSearchDd());
		System.out.println("----------------------------------------------------------------");

		Map<String, Object> paramMap = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(statisticsMgtVO.getSoId())){				
				paramMap.put("SO_ID",statisticsMgtVO.getSoId());
				System.out.println("getSoId==>"+statisticsMgtVO.getSoId());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getAcntId())){
				paramMap.put("ACNT_ID",statisticsMgtVO.getAcntId());
				System.out.println("getAcntId==>"+statisticsMgtVO.getAcntId());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getStrtDd())){
				paramMap.put("STRT_DD",statisticsMgtVO.getStrtDd());
				System.out.println("getStrtDd==>"+statisticsMgtVO.getStrtDd());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getEndDd())){
				paramMap.put("END_DD",statisticsMgtVO.getEndDd());
				System.out.println("getEndDd==>"+statisticsMgtVO.getEndDd());
			
			}	
			if(StringUtils.isNotEmpty(statisticsMgtVO.getSvcTelNo())){
				paramMap.put("SVC_TEL_NO",statisticsMgtVO.getSvcTelNo());
				System.out.println("getSvcTelNo==>"+statisticsMgtVO.getSvcTelNo());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getBillBgnMth())){
				paramMap.put("BILL_BGN_MTH",statisticsMgtVO.getBillBgnMth());
				System.out.println("getBillBgnMth==>"+statisticsMgtVO.getBillBgnMth());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getBillEndMth())){
				paramMap.put("BILL_END_MTH",statisticsMgtVO.getBillEndMth());
				System.out.println("getBillEndMth==>"+statisticsMgtVO.getBillEndMth());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getCtrtStat())){
				paramMap.put("CTRT_STAT",statisticsMgtVO.getCtrtStat());
				System.out.println("getCtrtStat==>"+statisticsMgtVO.getCtrtStat());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getCustId())){
				paramMap.put("CUST_ID",statisticsMgtVO.getCustId());
				System.out.println("getCustId==>"+statisticsMgtVO.getCustId());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getOpenBgnDd())){
				paramMap.put("OPEN_BGN_DD",statisticsMgtVO.getOpenBgnDd());
				System.out.println("getOpenBgnDd==>"+statisticsMgtVO.getOpenBgnDd());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getOpenEndDd())){
				paramMap.put("OPEN_END_DD",statisticsMgtVO.getOpenEndDd());
				System.out.println("getOpenEndDd==>"+statisticsMgtVO.getOpenEndDd());
			
			}
			if(StringUtils.isNotEmpty(statisticsMgtVO.getSearchDd())){
				paramMap.put("SEARCH_DD",statisticsMgtVO.getSearchDd());
				System.out.println("getSearchDd==>"+statisticsMgtVO.getSearchDd());
			}		
			paramMap.put("_transaction_type_","N");
			paramMap.put("LNG_TYP",lng);
			paramMap.put("REGR_ID",session.getUserId());
			paramMap.put("CHGR_ID",session.getUserId());			
			
		//Map paramMap = (Map) statisticsMgtVO;
			
		model.addAttribute("sqlResult",statisticsMgtService.execute(URLDecoder.decode(statisticsMgtVO.getStmt(), "UTF-8"),Integer.parseInt(statisticsMgtVO.getTmout()), paramMap));

		return thisUrl + "/statisticsMgtList";
	}	
	
	@RequestMapping(value = "getExcelAction", method = RequestMethod.POST)
	public String getExcelAction(Model model, StatisticsMgtVO statisticsMgtVO, HttpServletRequest request) throws Exception {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(statisticsMgtVO.getSoId())){				
			paramMap.put("SO_ID",statisticsMgtVO.getSoId());
			System.out.println("getSoId==>"+statisticsMgtVO.getSoId());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getAcntId())){
			paramMap.put("ACNT_ID",statisticsMgtVO.getAcntId());
			System.out.println("getAcntId==>"+statisticsMgtVO.getAcntId());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getStrtDd())){
			paramMap.put("STRT_DD",statisticsMgtVO.getStrtDd());
			System.out.println("getStrtDd==>"+statisticsMgtVO.getStrtDd());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getEndDd())){
			paramMap.put("END_DD",statisticsMgtVO.getEndDd());
			System.out.println("getEndDd==>"+statisticsMgtVO.getEndDd());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getSvcTelNo())){
			paramMap.put("SVC_TEL_NO",statisticsMgtVO.getSvcTelNo());
			System.out.println("getSvcTelNo==>"+statisticsMgtVO.getSvcTelNo());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getBillBgnMth())){
			paramMap.put("BILL_BGN_MTH",statisticsMgtVO.getBillBgnMth());
			System.out.println("getBillBgnMth==>"+statisticsMgtVO.getBillBgnMth());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getBillEndMth())){
			paramMap.put("BILL_END_MTH",statisticsMgtVO.getBillEndMth());
			System.out.println("getBillEndMth==>"+statisticsMgtVO.getBillEndMth());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getCtrtStat())){
			paramMap.put("CTRT_STAT",statisticsMgtVO.getCtrtStat());
			System.out.println("getCtrtStat==>"+statisticsMgtVO.getCtrtStat());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getCustId())){
			paramMap.put("CUST_ID",statisticsMgtVO.getCustId());
			System.out.println("getCustId==>"+statisticsMgtVO.getCustId());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getOpenBgnDd())){
			paramMap.put("OPEN_BGN_DD",statisticsMgtVO.getOpenBgnDd());
			System.out.println("getOpenBgnDd==>"+statisticsMgtVO.getOpenBgnDd());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getOpenEndDd())){
			paramMap.put("OPEN_END_DD",statisticsMgtVO.getOpenEndDd());
			System.out.println("getOpenEndDd==>"+statisticsMgtVO.getOpenEndDd());
		
		}else if(StringUtils.isNotEmpty(statisticsMgtVO.getSearchDd())){
			paramMap.put("SEARCH_DD",statisticsMgtVO.getSearchDd());
			System.out.println("getSearchDd==>"+statisticsMgtVO.getSearchDd());
		}		
		paramMap.put("_transaction_type_","N");
		paramMap.put("LNG_TYP",lng);
		paramMap.put("REGR_ID",session.getUserId());
		paramMap.put("CHGR_ID",session.getUserId());
		
		StatisticsMgtVO result = statisticsMgtService.getStatisticeDtl(statisticsMgtVO.getSearchStsCd());
		
		/*
		 * Header 작성
		 */
		List<Map<String, Object>> list = statisticsMgtService.test(result.getStmt(),Integer.parseInt(statisticsMgtVO.getTmout()));
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		for(int i =0; i< list.size(); i++){
			System.out.println("head===>"+(String)list.get(i).get("name"));
			columnList.add(new ExcelColumnVO(10, (String)list.get(i).get("name"), (String)list.get(i).get("name"), ExcelFormatType.STRING));
		}

		/*
		 * 데이터 세팅
		 */
		
		List<Map<String, Object>> list2 = statisticsMgtService.execute(result.getStmt(),Integer.parseInt(statisticsMgtVO.getTmout()), paramMap);
		List<ExcelRowVO> rowList = new ArrayList<ExcelRowVO>();
		for(Map<String,Object> row : list2){
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
		sh.setSheetName("SQL Result");
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);
		
		/*
		 * 파일작성
		 */
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName("SQL Result");
		file.setSheetCount(1);
		file.setSheetList(shList);
		
		/*
		 * Model Set
		 */
		model.addAttribute("excelDataFile", file);
		
		return "excelXlsxView";
	}	
	@RequestMapping(value = "statisticsExamplePopup", method = RequestMethod.POST)
	public String statisticsExamplePopup(
			StatisticsMgtVO statisticsMgtVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		return  thisUrl + "/ajax/statisticsExamplePopup";
	}
	@RequestMapping(value = "statisticsMgtDeleteAction", method = RequestMethod.POST)
	public String statisticsMgtDeleteAction(
			@RequestBody StatisticsMgtVO statisticsMgtVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
        String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
        SessionUser session = (SessionUser)request.getSession().getAttribute("session_user");

		statisticsMgtVO.setStmtCd(statisticsMgtVO.getStmtCd());
		
		statisticsMgtService.deleteStatistics(statisticsMgtVO);

		
		model.addAttribute("sqlTpList", commonDataService.getCommonCodeList("CM00056", lngTyp)); //sql 타입
		model.addAttribute("getStatisticeList", statisticsMgtService.getStatisticeList()); //SQL명	
		
		return thisUrl + "/statisticsMgtList";
	}	
}

