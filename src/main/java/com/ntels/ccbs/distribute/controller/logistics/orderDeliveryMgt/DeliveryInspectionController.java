package com.ntels.ccbs.distribute.controller.logistics.orderDeliveryMgt;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.DeliveryInspectionVO;
import com.ntels.ccbs.distribute.service.logistics.orderDeliveryMgt.DeliveryInspectionService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/distributor/logistics/orderDeliveryMgt/deliveryInspection")
public class DeliveryInspectionController {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private DeliveryInspectionService deliveryInspectionService;
	
	private String URL = "distributor/logistics/orderDeliveryMgt/deliveryInspection";
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deliveryInspection
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 제조사입고검수관리 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 1. 오후 1:25:09
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param deliveryInspectionVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "deliveryInspection", method = RequestMethod.POST)
	public String deliveryInspection(Model model, DeliveryInspectionVO deliveryInspectionVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("itemTpCd", commonDataService.getCommonCodeList("DN00063", lngTyp));		//제품유형코드
		
		return URL + "/deliveryInspection";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: inOutActncList
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 납품내역 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 1. 오후 1:25:52
	 * </PRE>
	 *   @return void
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "inOutActncList", method = RequestMethod.POST)
	public void inOutActncList(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		deliveryInspectionVO.setLngTyp(lngTyp);
		deliveryInspectionVO.setSidx(sidx);
		deliveryInspectionVO.setSord(sord);
		
        List<DeliveryInspectionVO> list = new ArrayList<DeliveryInspectionVO>();
		int count = 0;
		count = deliveryInspectionService.inOutActncCount(deliveryInspectionVO);	
		if (count > 0) list = deliveryInspectionService.inOutActncList(deliveryInspectionVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: inActncList
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고내역 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 4. 오전 9:43:48
	 * </PRE>
	 *   @return void
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "inActncList", method = RequestMethod.POST)
	public void inActncList(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		deliveryInspectionVO.setLngTyp(lngTyp);
		deliveryInspectionVO.setSidx(sidx);
		deliveryInspectionVO.setSord(sord);
		
        List<DeliveryInspectionVO> list = new ArrayList<DeliveryInspectionVO>();
		int count = 0;
		count = deliveryInspectionService.inActncCount(deliveryInspectionVO);	
		if (count > 0) list = deliveryInspectionService.inActncList(deliveryInspectionVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: inActncInsertPopUp
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고내역 등록 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 4. 오전 9:58:20
	 * </PRE>
	 *   @return String
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "inActncInsertPopUp", method = RequestMethod.POST)
	public String inActncInsertPopUp(
			DeliveryInspectionVO deliveryInspectionVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("eqtUseCd", commonDataService.getCommonCodeList("DN00065", lngTyp));		//단말기용도코드 EQT_USE_CD
		model.addAttribute("eqtRsrcClCd", commonDataService.getCommonCodeList("DN00068", lngTyp));		//단말기자원구분코드 EQT_RSRC_CL_CD
		model.addAttribute("deliveryInspectionVO", deliveryInspectionVO);

		return URL + "/popup/inActncInsertPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertInout
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고내역 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 5. 오전 10:27:39
	 * </PRE>
	 *   @return void
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "insertInout", method = RequestMethod.POST)
	public void insertInout(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		deliveryInspectionVO.setRegrId(session.getUserId());
		deliveryInspectionVO.setChgrId(session.getUserId());
		deliveryInspectionVO.setRegDate(DateUtil.sysdate());
		deliveryInspectionVO.setChgDate(DateUtil.sysdate());
		deliveryInspectionVO.setLngTyp(lngTyp);
		
		int result = deliveryInspectionService.insertInout(deliveryInspectionVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: inActncUpdatePopUp
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고내역 수정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 6. 오전 9:34:44
	 * </PRE>
	 *   @return String
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "inActncUpdatePopUp", method = RequestMethod.POST)
	public String inActncUpdatePopUp(
			DeliveryInspectionVO deliveryInspectionVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("eqtUseCd", commonDataService.getCommonCodeList("DN00065", lngTyp));		//단말기용도코드 EQT_USE_CD
		model.addAttribute("eqtRsrcClCd", commonDataService.getCommonCodeList("DN00068", lngTyp));		//단말기자원구분코드 EQT_RSRC_CL_CD
		model.addAttribute("deliveryInspectionVO", deliveryInspectionVO);

		return URL + "/popup/inActncUpdatePopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateInout
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고내역 수정 
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 6. 오전 9:35:03
	 * </PRE>
	 *   @return void
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "updateInout", method = RequestMethod.POST)
	public void updateInout(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		deliveryInspectionVO.setRegrId(session.getUserId());
		deliveryInspectionVO.setChgrId(session.getUserId());
		deliveryInspectionVO.setRegDate(DateUtil.sysdate());
		deliveryInspectionVO.setChgDate(DateUtil.sysdate());
		deliveryInspectionVO.setLngTyp(lngTyp);
		
		int result = deliveryInspectionService.updateInout(deliveryInspectionVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteInout
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고내역 삭제
	 * 4. 작성자    : jhkim 
	 * 5. 작성일    : 2016. 7. 6. 오전 9:35:13
	 * </PRE>
	 *   @return void
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "deleteInout", method = RequestMethod.POST)
	public void deleteInout(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		int result = deliveryInspectionService.deleteInout(deliveryInspectionVO);
		
		model.addAttribute("result", result);
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deliveryInspectionPopUp
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 제조사입고확정 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 6. 오전 9:43:58
	 * </PRE>
	 *   @return String
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "deliveryInspectionPopUp", method = RequestMethod.POST)
	public String deliveryInspectionPopUp(
			DeliveryInspectionVO deliveryInspectionVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		return URL + "/popup/deliveryInspectionPopUp";
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: selectInOutEqtListAction
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고 단말기내역  리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 6. 오후 4:37:52
	 * </PRE>
	 *   @return void
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "selectInOutEqtListAction", method = RequestMethod.POST)
	public void selectInOutEqtListAction(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		deliveryInspectionVO.setLngTyp(lngTyp);
		deliveryInspectionVO.setSidx(sidx);
		deliveryInspectionVO.setSord(sord);
		
		
        List<DeliveryInspectionVO> list = new ArrayList<DeliveryInspectionVO>();
		list = deliveryInspectionService.selectInOutEqtList(deliveryInspectionVO);
		
		int count = list.size();

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: selectInOutUsimListAction
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고USIM내역  리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 6. 오후 4:38:14
	 * </PRE>
	 *   @return void
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "selectInOutUsimListAction", method = RequestMethod.POST)
	public void selectInOutUsimListAction(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		deliveryInspectionVO.setLngTyp(lngTyp);
		deliveryInspectionVO.setSidx(sidx);
		deliveryInspectionVO.setSord(sord);
		
		
        List<DeliveryInspectionVO> list = new ArrayList<DeliveryInspectionVO>();
		list = deliveryInspectionService.selectInOutUsimList(deliveryInspectionVO);
		
		int count = list.size();

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: selectInOutVEqtListAction
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고바우쳐내역  리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 7. 6. 오후 4:38:32
	 * </PRE>
	 *   @return void
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "selectInOutVEqtListAction", method = RequestMethod.POST)
	public void selectInOutVEqtListAction(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		deliveryInspectionVO.setLngTyp(lngTyp);
		deliveryInspectionVO.setSidx(sidx);
		deliveryInspectionVO.setSord(sord);
		
		
        List<DeliveryInspectionVO> list = new ArrayList<DeliveryInspectionVO>();
		list = deliveryInspectionService.selectInOutVEqtList(deliveryInspectionVO);
		
		int count = list.size();

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	@RequestMapping(value = "excelUploadPopUp")
	public String excelUploadPopUp(
			DeliveryInspectionVO deliveryInspectionVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		return URL + "/opopup/excelUploadPopUp";
		
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "excelUploadColumnAction", method = RequestMethod.POST)
	public void excelUploadColumnAction(
	      Model model
	      , MultipartHttpServletRequest request
		  ) throws Exception {

		MultipartFile mf = request.getFile("fileName");
		File excelFile = new File( mf.getOriginalFilename());
		mf.transferTo(excelFile);
		
		FileInputStream inputDocument = null;
		Workbook workbook = null;
		
		try{
			inputDocument = new FileInputStream(excelFile);
			if (excelFile.getName().toLowerCase().endsWith("xlsx")) { // 엑셀 파일의 확장자(버전)에 따라서 생성해야 할 Workbook 객체가 다르다.
				workbook = new XSSFWorkbook(inputDocument);
			}else{
				workbook = new HSSFWorkbook(inputDocument);
			}
			
			Sheet workSheet = workbook.getSheetAt(0); // 첫번째 Sheet
			Row row = workSheet.getRow(0);
			
			if(row == null){
				inputDocument.close();
			}else{
				int cellLength = (int) row.getLastCellNum(); // 열의 총 개수
				List<String> returnList = new ArrayList<String>();
				
				for(int j=0; j < cellLength ; j++){
					Cell cell = row.getCell(j);
					
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						returnList.add("");
					}else{
						switch(cell.getCellType()){
							case Cell.CELL_TYPE_STRING : //문자열
								returnList.add(cell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC : // 숫자 혹은 날짜
								Double numericCellValue = cell.getNumericCellValue();
								if(Math.floor(numericCellValue) == numericCellValue){ // 소숫점 판단
									returnList.add(String.valueOf(numericCellValue.intValue()));
								}else{
									returnList.add(String.valueOf(numericCellValue));
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								returnList.add(String.valueOf(cell.getBooleanCellValue()));
								
								break;
						}
					}
				}
				
				model.addAttribute("returnList", returnList);
			}
			inputDocument.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException( "MSG.M15.MSG00001" );
		}
	}
	
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "excelUploadCAction", method = RequestMethod.POST)
	public void excelUploadCAction(
	      Model model
	      , MultipartHttpServletRequest request
		  ) throws Exception {

		
		List<DeliveryInspectionVO> returnList = new ArrayList<DeliveryInspectionVO>(); 
		/*
		String key = "";
		String value = "";
		Enumeration<String> enumer = request.getParameterNames();
		while (enumer.hasMoreElements()) {
	        key = enumer.nextElement();
	        value = request.getParameter(key);
	  
	        System.out.println(key + " : " + value);
	    }
		*/
		String column0 = request.getParameter("column0");
		String column1 = request.getParameter("column1");
		String column2 = request.getParameter("column2");
		String column3 = request.getParameter("column3");
		String column4 = request.getParameter("column4");
		String column5 = request.getParameter("column5");
		String column6 = request.getParameter("column6");
		
		int columnNum0 = -1;
		int columnNum1 = -1;
		int columnNum2 = -1;
		int columnNum3 = -1;
		int columnNum4 = -1;
		int columnNum5 = -1;
		int columnNum6 = -1;
		
		MultipartFile mf = request.getFile("fileName");
		File excelFile = new File( mf.getOriginalFilename());
		mf.transferTo(excelFile);
		
		FileInputStream inputDocument = null;
		Workbook workbook = null;
		
		try{
			inputDocument = new FileInputStream(excelFile);
			if (excelFile.getName().toLowerCase().endsWith("xlsx")) { // 엑셀 파일의 확장자(버전)에 따라서 생성해야 할 Workbook 객체가 다르다.
				workbook = new XSSFWorkbook(inputDocument);
			}else{
				workbook = new HSSFWorkbook(inputDocument);
			}
			
			Sheet workSheet = workbook.getSheetAt(0); // 첫번째 Sheet
			Row row = workSheet.getRow(0);
			
			if(row == null){
				inputDocument.close();
			}else{
				int cellLength = (int) row.getLastCellNum(); // 열의 총 개수
				String column = "";
				
				for(int j=0; j < cellLength ; j++){
					Cell cell = row.getCell(j);
					
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						column = "";
					}else{
						switch(cell.getCellType()){
							case Cell.CELL_TYPE_STRING : //문자열
								column = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC : // 숫자 혹은 날짜
								Double numericCellValue = cell.getNumericCellValue();
								if(Math.floor(numericCellValue) == numericCellValue){ // 소숫점 판단
									column = String.valueOf(numericCellValue.intValue());
								}else{
									column = String.valueOf(numericCellValue);
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								column = String.valueOf(cell.getBooleanCellValue());
								break;
						}
						//컬럼 이름 매핑 순서
						if(column.equals(column0)){
							columnNum0 = j;
						}
						if(column.equals(column1)){
							columnNum1 = j;
						}
						if(column.equals(column2)){
							columnNum2 = j;
						}
						if(column.equals(column3)){
							columnNum3 = j;
						}
						if(column.equals(column4)){
							columnNum4 = j;
						}
						if(column.equals(column5)){
							columnNum5 = j;
						}
						if(column.equals(column6)){
							columnNum6 = j;
						}
					}
				}
			}

			
			Sheet workSheet01 = workbook.getSheetAt(0); // 첫번째 Sheet
			int rowSize = workSheet01.getLastRowNum() + 1; // 행의 총 개수 (행은 0부터 시작함)
			
			for(int i = 1; i < rowSize; i++){ // i를 1부터 시작해야 두번째 행부터 데이터가 입력된다.
				Row row1 = workSheet.getRow(i);
				int cellLength = (int) row1.getLastCellNum(); // 열의 총 개수
				DeliveryInspectionVO deliveryInspectionVO = new DeliveryInspectionVO();
				
				for(int j=0; j < cellLength ; j++){
					Cell cell = row1.getCell(j);
					String cellString = "";
					
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						cellString = "";
					}else{
						switch(cell.getCellType()){
							case Cell.CELL_TYPE_STRING : //문자열
								cellString = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC : // 숫자 혹은 날짜
								Double numericCellValue = cell.getNumericCellValue();
								if(Math.floor(numericCellValue) == numericCellValue){ // 소숫점 판단
									cellString = String.valueOf(numericCellValue.intValue());
								}else{
									cellString = String.valueOf(numericCellValue);
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								cellString = String.valueOf(cell.getBooleanCellValue());
								break;
						}
					}
					
					//컬럼 이름 매핑 순서
					if(j == columnNum0){
						deliveryInspectionVO.setItemId(cellString);
					}
					if(j == columnNum1){
						deliveryInspectionVO.setEqtSeq(cellString);
					}
					if(j == columnNum2){
						deliveryInspectionVO.setImeiNo(cellString);
					}
					if(j == columnNum3){
						deliveryInspectionVO.setClorCd(cellString);
					}
					if(j == columnNum4){
						deliveryInspectionVO.setMacAddrVal(cellString);
					}
					if(j == columnNum5){
						deliveryInspectionVO.setEqtBarCd(cellString);
					}
					if(j == columnNum6){
						deliveryInspectionVO.setMncoDt(cellString);
					}
					
				} 
				//Row 추가
				returnList.add(deliveryInspectionVO);
			}
			
			model.addAttribute("returnList", returnList);
			
			inputDocument.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException( "MSG.M15.MSG00001" );
		}
		
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "excelUploadUAction", method = RequestMethod.POST)
	public void excelUploadUAction(
	      Model model
	      , MultipartHttpServletRequest request
		  ) throws Exception {

		
		List<DeliveryInspectionVO> returnList = new ArrayList<DeliveryInspectionVO>(); 
		
		String column0 = request.getParameter("column0");
		String column1 = request.getParameter("column1");
		String column2 = request.getParameter("column2");
		String column3 = request.getParameter("column3");
		String column4 = request.getParameter("column4");
		String column5 = request.getParameter("column5");
		String column6 = request.getParameter("column6");
		String column7 = request.getParameter("column7");
		String column8 = request.getParameter("column8");
		String column9 = request.getParameter("column9");
		String column10 = request.getParameter("column10");
		String column11 = request.getParameter("column11");
		String column12 = request.getParameter("column12");
		String column13 = request.getParameter("column13");
		
		int columnNum0 = -1;
		int columnNum1 = -1;
		int columnNum2 = -1;
		int columnNum3 = -1;
		int columnNum4 = -1;
		int columnNum5 = -1;
		int columnNum6 = -1;
		int columnNum7 = -1;
		int columnNum8 = -1;
		int columnNum9 = -1;
		int columnNum10 = -1;
		int columnNum11 = -1;
		int columnNum12 = -1;
		int columnNum13 = -1;
		
		MultipartFile mf = request.getFile("fileName");
		File excelFile = new File( mf.getOriginalFilename());
		mf.transferTo(excelFile);
		
		FileInputStream inputDocument = null;
		Workbook workbook = null;
		
		try{
			inputDocument = new FileInputStream(excelFile);
			if (excelFile.getName().toLowerCase().endsWith("xlsx")) { // 엑셀 파일의 확장자(버전)에 따라서 생성해야 할 Workbook 객체가 다르다.
				workbook = new XSSFWorkbook(inputDocument);
			}else{
				workbook = new HSSFWorkbook(inputDocument);
			}
			
			Sheet workSheet = workbook.getSheetAt(0); // 첫번째 Sheet
			Row row = workSheet.getRow(0);
			
			if(row == null){
				inputDocument.close();
			}else{
				int cellLength = (int) row.getLastCellNum(); // 열의 총 개수
				String column = "";
				
				for(int j=0; j < cellLength ; j++){
					Cell cell = row.getCell(j);
					
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						column = "";
					}else{
						switch(cell.getCellType()){
							case Cell.CELL_TYPE_STRING : //문자열
								column = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC : // 숫자 혹은 날짜
								Double numericCellValue = cell.getNumericCellValue();
								if(Math.floor(numericCellValue) == numericCellValue){ // 소숫점 판단
									column = String.valueOf(numericCellValue.intValue());
								}else{
									column = String.valueOf(numericCellValue);
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								column = String.valueOf(cell.getBooleanCellValue());
								break;
						}
						//컬럼 이름 매핑 순서
						if(column.equals(column0)){
							columnNum0 = j;
						}
						if(column.equals(column1)){
							columnNum1 = j;
						}
						if(column.equals(column2)){
							columnNum2 = j;
						}
						if(column.equals(column3)){
							columnNum3 = j;
						}
						if(column.equals(column4)){
							columnNum4 = j;
						}
						if(column.equals(column5)){
							columnNum5 = j;
						}
						if(column.equals(column6)){
							columnNum6 = j;
						}
						if(column.equals(column7)){
							columnNum7 = j;
						}
						if(column.equals(column8)){
							columnNum8 = j;
						}
						if(column.equals(column9)){
							columnNum9 = j;
						}
						if(column.equals(column10)){
							columnNum10 = j;
						}
						if(column.equals(column11)){
							columnNum11 = j;
						}
						if(column.equals(column12)){
							columnNum12 = j;
						}
						if(column.equals(column13)){
							columnNum13 = j;
						}
					}
				}
			}

			
			Sheet workSheet01 = workbook.getSheetAt(0); // 첫번째 Sheet
			int rowSize = workSheet01.getLastRowNum() + 1; // 행의 총 개수 (행은 0부터 시작함)
			
			for(int i = 1; i < rowSize; i++){ // i를 1부터 시작해야 두번째 행부터 데이터가 입력된다.
				Row row1 = workSheet.getRow(i);
				int cellLength = (int) row1.getLastCellNum(); // 열의 총 개수
				DeliveryInspectionVO deliveryInspectionVO = new DeliveryInspectionVO();
				
				for(int j=0; j < cellLength ; j++){
					Cell cell = row1.getCell(j);
					String cellString = "";
					
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						cellString = "";
					}else{
						switch(cell.getCellType()){
							case Cell.CELL_TYPE_STRING : //문자열
								cellString = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC : // 숫자 혹은 날짜
								Double numericCellValue = cell.getNumericCellValue();
								if(Math.floor(numericCellValue) == numericCellValue){ // 소숫점 판단
									cellString = String.valueOf(numericCellValue.intValue());
								}else{
									cellString = String.valueOf(numericCellValue);
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								cellString = String.valueOf(cell.getBooleanCellValue());
								break;
						}
					}
					
					//컬럼 이름 매핑 순서
					if(j == columnNum0){
						deliveryInspectionVO.setItemId(cellString);
					}
					if(j == columnNum1){
						deliveryInspectionVO.setImsiNo(cellString);
					}
					if(j == columnNum2){
						deliveryInspectionVO.setEqtBarCd(cellString);
					}
					if(j == columnNum3){
						deliveryInspectionVO.setPin1(cellString);
					}
					if(j == columnNum4){
						deliveryInspectionVO.setPuk1(cellString);
					}
					if(j == columnNum5){
						deliveryInspectionVO.setPin2(cellString);
					}
					if(j == columnNum6){
						deliveryInspectionVO.setPuk2(cellString);
					}
					if(j == columnNum7){
						deliveryInspectionVO.setAdm(cellString);
					}
					if(j == columnNum8){
						deliveryInspectionVO.setKi(cellString);
					}
					if(j == columnNum9){
						deliveryInspectionVO.setAcc(cellString);
					}
					if(j == columnNum10){
						deliveryInspectionVO.setAlgoid(cellString);
					}
					if(j == columnNum11){
						deliveryInspectionVO.setKdbid(cellString);
					}
					if(j == columnNum12){
						deliveryInspectionVO.setAcsub(cellString);
					}
					if(j == columnNum13){
						deliveryInspectionVO.setMncoDt(cellString);
					}
					
				} 
				//Row 추가
				returnList.add(deliveryInspectionVO);
			}
			
			model.addAttribute("returnList", returnList);
			
			inputDocument.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException( "MSG.M15.MSG00001" );
		}
		
	}
	
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "excelUploadVAction", method = RequestMethod.POST)
	public void excelUploadVAction(
	      Model model
	      , MultipartHttpServletRequest request
		  ) throws Exception {

		
		List<DeliveryInspectionVO> returnList = new ArrayList<DeliveryInspectionVO>(); 
		String column0 = request.getParameter("column0");
		String column1 = request.getParameter("column1");
		String column2 = request.getParameter("column2");
		String column3 = request.getParameter("column3");
		
		int columnNum0 = -1;
		int columnNum1 = -1;
		int columnNum2 = -1;
		int columnNum3 = -1;
		
		MultipartFile mf = request.getFile("fileName");
		File excelFile = new File( mf.getOriginalFilename());
		mf.transferTo(excelFile);
		
		FileInputStream inputDocument = null;
		Workbook workbook = null;
		
		try{
			inputDocument = new FileInputStream(excelFile);
			if (excelFile.getName().toLowerCase().endsWith("xlsx")) { // 엑셀 파일의 확장자(버전)에 따라서 생성해야 할 Workbook 객체가 다르다.
				workbook = new XSSFWorkbook(inputDocument);
			}else{
				workbook = new HSSFWorkbook(inputDocument);
			}
			
			Sheet workSheet = workbook.getSheetAt(0); // 첫번째 Sheet
			Row row = workSheet.getRow(0);
			
			if(row == null){
				inputDocument.close();
			}else{
				int cellLength = (int) row.getLastCellNum(); // 열의 총 개수
				String column = "";
				
				for(int j=0; j < cellLength ; j++){
					Cell cell = row.getCell(j);
					
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						column = "";
					}else{
						switch(cell.getCellType()){
							case Cell.CELL_TYPE_STRING : //문자열
								column = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC : // 숫자 혹은 날짜
								Double numericCellValue = cell.getNumericCellValue();
								if(Math.floor(numericCellValue) == numericCellValue){ // 소숫점 판단
									column = String.valueOf(numericCellValue.intValue());
								}else{
									column = String.valueOf(numericCellValue);
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								column = String.valueOf(cell.getBooleanCellValue());
								break;
						}
						//컬럼 이름 매핑 순서
						if(column.equals(column0)){
							columnNum0 = j;
						}
						if(column.equals(column1)){
							columnNum1 = j;
						}
						if(column.equals(column2)){
							columnNum2 = j;
						}
						if(column.equals(column3)){
							columnNum3 = j;
						}
					}
				}
			}

			
			Sheet workSheet01 = workbook.getSheetAt(0); // 첫번째 Sheet
			int rowSize = workSheet01.getLastRowNum() + 1; // 행의 총 개수 (행은 0부터 시작함)
			
			for(int i = 1; i < rowSize; i++){ // i를 1부터 시작해야 두번째 행부터 데이터가 입력된다.
				Row row1 = workSheet.getRow(i);
				int cellLength = (int) row1.getLastCellNum(); // 열의 총 개수
				DeliveryInspectionVO deliveryInspectionVO = new DeliveryInspectionVO();
				
				for(int j=0; j < cellLength ; j++){
					Cell cell = row1.getCell(j);
					String cellString = "";
					
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						cellString = "";
					}else{
						switch(cell.getCellType()){
							case Cell.CELL_TYPE_STRING : //문자열
								cellString = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC : // 숫자 혹은 날짜
								Double numericCellValue = cell.getNumericCellValue();
								if(Math.floor(numericCellValue) == numericCellValue){ // 소숫점 판단
									cellString = String.valueOf(numericCellValue.intValue());
								}else{
									cellString = String.valueOf(numericCellValue);
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								cellString = String.valueOf(cell.getBooleanCellValue());
								break;
						}
					}
					
					//컬럼 이름 매핑 순서
					if(j == columnNum0){
						deliveryInspectionVO.setItemId(cellString);
					}
					if(j == columnNum1){
						deliveryInspectionVO.setEqtSeq(cellString);
					}
					if(j == columnNum2){
						deliveryInspectionVO.setEqtBarCd(cellString);
					}
					if(j == columnNum3){
						deliveryInspectionVO.setMncoDt(cellString);
					}
					
				} 
				//Row 추가
				returnList.add(deliveryInspectionVO);
			}
			
			model.addAttribute("returnList", returnList);
			
			inputDocument.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException( "MSG.M15.MSG00001" );
		}
		
	}
	
	
	@RequestMapping(value = "insertInoutList", method = RequestMethod.POST)
	public void insertInoutList(
			@RequestBody List<DeliveryInspectionVO> deliveryInspectionVOs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = deliveryInspectionService.insertInoutList(deliveryInspectionVOs, session);
		
		model.addAttribute("result", result);
		
	}
	

	@RequestMapping(value = "insertInspection", method = RequestMethod.POST)
	public void insertInspection(
			@RequestBody List<DeliveryInspectionVO> deliveryInspectionVOs
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		int result = deliveryInspectionService.insertInspection(deliveryInspectionVOs, session);
		
		model.addAttribute("result", result);
		
	}
	
	
	@RequestMapping(value = "insertWearingAc", method = RequestMethod.POST)
	public void insertWearingAc(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		deliveryInspectionVO.setRegrId(session.getUserId());
		deliveryInspectionVO.setChgrId(session.getUserId());
		deliveryInspectionVO.setRegDate(DateUtil.sysdate());
		deliveryInspectionVO.setChgDate(DateUtil.sysdate());
		deliveryInspectionVO.setLngTyp(lngTyp);
		
		int result = deliveryInspectionService.insertWearingAc(deliveryInspectionVO, session);
		
		model.addAttribute("result", result);
		
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: inAcPopUp
	 * 2. ClassName : DeliveryInspectionController
	 * 3. Comment   : 입고승인 팝업
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 8. 11. 오후 2:21:39
	 * </PRE>
	 *   @return String
	 *   @param deliveryInspectionVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "inAcPopUp", method = RequestMethod.POST)
	public String inAcPopUp(
			DeliveryInspectionVO deliveryInspectionVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		List<DeliveryInspectionVO> salesDivision = deliveryInspectionService.selectSalesDivision(deliveryInspectionVO);
		
		model.addAttribute("salesDivision", salesDivision);

		return URL + "/popup/inAcPopUp";
		
	}
	
	@RequestMapping(value = "selectSalesTeam", method = RequestMethod.POST)
	public void salesTeam(
			DeliveryInspectionVO deliveryInspectionVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		List<DeliveryInspectionVO> salesTeam = deliveryInspectionService.selectSalesTeam(deliveryInspectionVO);
		
		model.addAttribute("salesTeam", salesTeam);
		
	}
	
}
