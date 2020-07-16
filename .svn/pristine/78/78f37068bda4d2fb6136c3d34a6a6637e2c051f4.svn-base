package com.ntels.ccbs.system.controller.sample.sample;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.domain.menu.menuMng.MenuMngVO;


@Controller
@RequestMapping(value = "/system/sample/sample/sample")
public class ExcelUploadSampleController {
	private static String URL = "system/sample/sample/sample";
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "excelUploadSample", method = RequestMethod.POST)
	public String menuMng(	Model model,MenuMngVO menu ,HttpServletRequest request) {
		return URL + "/excelUploadSample";
	}

	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/excelUpload", method = RequestMethod.POST)
	public String fildUpload(MultipartHttpServletRequest mReq, Model model) {
	    try{
	    	MultipartFile mf = mReq.getFile("fileName");
	    	File uploadedExcelFile = new File( mf.getOriginalFilename());
	    	mf.transferTo(uploadedExcelFile);
	    	
	    	model.addAttribute("excelData", importExcelFile(uploadedExcelFile));
			
		} catch (Exception e) {
			throw new ServiceException( "MSG.M15.MSG00001" );
		}
	    
	    return URL + "/excelUploadSample";
	}
	
	private List<Map<String,String>> importExcelFile(File excelFile){
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
			int rowSize = workSheet.getLastRowNum() + 1; // 행의 총 개수 (행은 0부터 시작함)
			
			List<Map<String,String>> sheetRows = new ArrayList<Map<String,String>>();
			
			for(int i = 1; i < rowSize; i++){ // i를 1부터 시작해야 두번째 행부터 데이터가 입력된다.
				Row row = workSheet.getRow(i);
				
				int cellLength = (int) row.getLastCellNum(); // 열의 총 개수
				
				Map<String,String> cellMap = new HashMap<String,String>();
				for(int j=0; j < cellLength ; j++){
					Cell cell = row.getCell(j);
					
					String cellKey = "COL" + j;
					
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						cellMap.put(cellKey, "");
					}else{
						switch(cell.getCellType()){
							case Cell.CELL_TYPE_STRING : //문자열
								cellMap.put(cellKey, cell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC : // 숫자 혹은 날짜
								//날짜
								if(DateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
									String formattedStr = dateFormat.format(cell.getDateCellValue());
									cellMap.put(cellKey, formattedStr);
									break;
								// 숫자
								}else{
									Double numericCellValue = cell.getNumericCellValue();
									if(Math.floor(numericCellValue) == numericCellValue){ // 소숫점 판단
										cellMap.put(cellKey, String.valueOf(numericCellValue.intValue()));
									}else{
										cellMap.put(cellKey, String.valueOf(numericCellValue));
									}
									break;
								}
							case Cell.CELL_TYPE_BOOLEAN :
								cellMap.put(cellKey, String.valueOf(cell.getBooleanCellValue()));
								break;
						}
					}
				} 
				//Row 추가
				sheetRows.add(cellMap);
				log.debug("ROW " + i + " : " + cellMap.toString());
			}
			inputDocument.close();
			
			log.debug("Excel File read count : " + sheetRows.size());
			return sheetRows;
		} catch (Exception e) {
			throw new ServiceException( "MSG.M15.MSG00001" );
		}
	}
	
	@RequestMapping(value = "treeSample", method = RequestMethod.POST)
	public String treeSample(	Model model,MenuMngVO menu ,HttpServletRequest request) {
		return URL + "/treeSample";
	}
}