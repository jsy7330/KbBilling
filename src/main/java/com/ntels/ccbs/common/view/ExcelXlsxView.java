package com.ntels.ccbs.common.view;


import java.awt.Color;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.DateFormatConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.ntels.ccbs.common.consts.Consts.ExcelFormatType;
import com.ntels.ccbs.common.util.DateUtil;

/**
 * <PRE>
 * 1. ClassName: ExcelXlsxView
 * 2. FileName : ExcelXlsxView.java
 * 3. Package  : com.ntels.ccbs.common.view
 * 4. Comment  : xlsx 파일 출력
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 27. 오후 5:33:30
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 27.    : 신규개발
 * </PRE>
 */
public class ExcelXlsxView extends AbstractView {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	/** The content type for an Excel response */
	private static final String CONTENT_TYPE = "application/vnd.ms-excel";

	/** The extension to look for existing templates */
	private static final String EXTENSION = ".xlsx";


	private String url;

	public ExcelXlsxView() {
		setContentType(CONTENT_TYPE);
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("Start Excel Workbook");
		XSSFWorkbook workbook;
		if (this.url != null) {
			workbook = getTemplateSource(this.url, request);
		}
		else {
			workbook = new XSSFWorkbook();
		}

		buildExcelDocument(model, workbook, request, response);
		
		response.setContentType(getContentType());
				
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		logger.debug("End Excel Workbook");

	}

	protected XSSFWorkbook getTemplateSource(String url, HttpServletRequest request) throws Exception {
		LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
		Locale userLocale = RequestContextUtils.getLocale(request);
		Resource inputFile = helper.findLocalizedResource(url, EXTENSION, userLocale);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Loading Excel workbook from " + inputFile);
		}
		
		return new XSSFWorkbook(inputFile.getInputStream());
	}
	

	@SuppressWarnings("unchecked")
	public void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook,
			HttpServletRequest req, HttpServletResponse res) throws Exception 
	{		
		// 파일명
		ExcelFileVO excelDataFile = (ExcelFileVO)model.get("excelDataFile");
		String fileName = excelDataFile.getFileName() + "_" + DateUtil.getDateStringYYYYMMDDHH24MISS(0)  + EXTENSION;
		
		// 헤더설정
		String userAgent = req.getHeader("User-Agent");
		if (userAgent.indexOf("MSIE") > -1) {
			fileName = URLEncoder.encode(fileName, "utf-8");
		} else {
			fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		}		
		res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		
		
		/**
		 * Local Set
		 */
		String sessionCountry = (String)req.getSession().getAttribute("sessionCountry");
		String sessionLanguage = (String)req.getSession().getAttribute("sessionLanguage");
		
		Locale locale = new Locale(sessionLanguage,sessionCountry);
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, locale);
		SimpleDateFormat dateSdf = (SimpleDateFormat)dateFormatter;
		
		DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.LONG, locale);
		SimpleDateFormat timdSdf = (SimpleDateFormat)timeFormatter;
		String localPattern  = dateSdf.toLocalizedPattern() + " " + timdSdf.toLocalizedPattern();
        
		String excelFormatPattern = DateFormatConverter.convert( locale, localPattern);
		
		/**
		 * Sheet수 만큼 페이지 작성
		 */
		List<ExcelSheetVO> sheetList = excelDataFile.getSheetList();
		for(int i = 0; i < sheetList.size(); i++){
			ExcelSheetVO sheetData = sheetList.get(i);
			XSSFSheet sheet = createSheet(workbook,sheetData.getSheetName(), i);
			/**
			 * Sheet의 컬럼 타이틀 작성
			 */
			createColumnLabel(sheet, workbook, sheetData.getTitleList());
			/**
			 * Row Data 작성
			 */
			createPageRow(sheet, sheetData.getDataList(),sheetData.getTitleList(), workbook, excelFormatPattern);
		}
		
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: createSheet
	 * 2. ClassName : ExcelXlsxView
	 * 3. Comment   : Sheet 생성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 27. 오후 6:00:48
	 * </PRE>
	 *   @return XSSFSheet
	 *   @param workbook 
	 *   @param sheetName 
	 *   @param i
	 *   @return
	 */
	private XSSFSheet createSheet(XSSFWorkbook workbook, String sheetName, int i) 
	{
		XSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(i, sheetName);
		return sheet;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName: createColumnLabel
	 * 2. ClassName : ExcelXlsxView
	 * 3. Comment   : 컬럼 타이틀 생성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 27. 오후 6:01:00
	 * </PRE>
	 *   @return void
	 *   @param sheet
	 *   @param workbook
	 *   @param columnList
	 */
	private void createColumnLabel(XSSFSheet sheet, XSSFWorkbook workbook, List<ExcelColumnVO> columnList) 
	{
		// 속성
		XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        
		Font font = workbook.createFont();
        font.setFontName("ARIAL");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 8);
        font.setColor(IndexedColors.BLACK.getIndex());
        
        cellStyle.setFont(font);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setFillForegroundColor(new XSSFColor(Color.decode("#F9F9C1")));
        cellStyle.setFillPattern((short) CellStyle.SOLID_FOREGROUND);        
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setTopBorderColor(IndexedColors.AUTOMATIC.getIndex());        
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setLeftBorderColor(IndexedColors.AUTOMATIC.getIndex());        
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.AUTOMATIC.getIndex());        
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.AUTOMATIC.getIndex());
        
        
        // 라벨
		XSSFRow firstRow = sheet.createRow(0);
		
		short width = 265;
		 
		for (int i = 0; i < columnList.size(); i++) {
			ExcelColumnVO column = columnList.get(i);
			XSSFCell cell = firstRow.createCell(i);
			if (column.getColumnSize() > 0) {
				sheet.setColumnWidth(i, (column.getColumnSize() * width));
			}
			cell.setCellValue(column.getTitle());
			cell.setCellStyle(cellStyle);
		}		
	}

	
	/**
	 * <PRE>
	 * 1. MethodName: createPageRow
	 * 2. ClassName : ExcelXlsxView
	 * 3. Comment   : Row 생성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 27. 오후 6:01:15
	 * </PRE>
	 *   @return void
	 *   @param sheet
	 *   @param dataList
	 *   @param columnList
	 *   @param workbook
	 */
	private void createPageRow(XSSFSheet sheet, List<ExcelRowVO> dataList, List<ExcelColumnVO> columnList,  XSSFWorkbook workbook, String  excelFormatPattern)
	{		
		Font font = workbook.createFont();
        font.setFontName("ARIAL");		        	        
        font.setFontHeightInPoints((short) 8);		        	       
		
		XSSFDataFormat dataformat = workbook.createDataFormat();
		 
        if (dataList != null && dataList.size() > 0) {
	        for (int i = 0; i <= dataList.size() - 1; i++) {	
	        	XSSFRow row = sheet.createRow(i + 1);
	        	ExcelRowVO rowdata = dataList.get(i);
	        	
	        	for (int j = 0; j < columnList.size() ; j++) {
	        		ExcelColumnVO column = columnList.get(j);
	        		ExcelCellVO cellData = rowdata.getCell(column.getKey());
	        		XSSFCell cell = row.createCell(j);
	        		
	        		if(i == 0){ //첫번째 Row일때만 스타일 생성
	        			XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
		                cellStyle.setFillForegroundColor(new XSSFColor(Color.decode("#FFFFFF")));
		                cellStyle.setFillPattern((short) CellStyle.SOLID_FOREGROUND);        
		                cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		                cellStyle.setTopBorderColor(IndexedColors.AUTOMATIC.getIndex());        
		                cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		                cellStyle.setLeftBorderColor(IndexedColors.AUTOMATIC.getIndex());        
		                cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		                cellStyle.setRightBorderColor(IndexedColors.AUTOMATIC.getIndex());        
		                cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		                cellStyle.setBottomBorderColor(IndexedColors.AUTOMATIC.getIndex());
		                cellStyle.setFont(font);
		                
		        		if(column.getType() == ExcelFormatType.STRING){
		        			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		        			cell.setCellValue((String)cellData.getValue());
		        			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        		}else if(column.getType() == ExcelFormatType.NUMBER){
		        			cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		        			cellStyle.setDataFormat(dataformat.getFormat("0"));
		        			double d = Double.parseDouble((String)cellData.getValue() == "" ? "0" : (String)cellData.getValue());  
		        			cell.setCellValue(d);
		        			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		        		}else if(column.getType() == ExcelFormatType.NUMBER_WITH_COMMA){
		        			cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		        			cellStyle.setDataFormat(dataformat.getFormat("#,##0"));
		        			double d = Double.parseDouble((String)cellData.getValue() == "" ? "0" : (String)cellData.getValue());  
		        			cell.setCellValue(d);
		        			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		        		}else if(column.getType() == ExcelFormatType.NUMBER_WITH_COMMA_POINT){
		        			cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		        			cellStyle.setDataFormat(dataformat.getFormat("#,##0.00"));
		        			double d = Double.parseDouble((String)cellData.getValue() == "" ? "0" : (String)cellData.getValue());  
		        			cell.setCellValue(d);
		        			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		        		}else if(column.getType() == ExcelFormatType.DATE){
		        			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		        			cellStyle.setDataFormat(dataformat.getFormat(excelFormatPattern));
		        			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		        			cell.setCellValue((Date)cellData.getValue());
		        		}else {
		        			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		        			cell.setCellValue((String)cellData.getValue());
		        			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        		}
		        		column.setStyle(cellStyle);
	        		}else{
		        		if(column.getType() == ExcelFormatType.STRING){
		        			cell.setCellValue((String)cellData.getValue());
		        			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        		}else if(column.getType() == ExcelFormatType.NUMBER){
		        			double d = Double.parseDouble((String)cellData.getValue() == "" ? "0" : (String)cellData.getValue());  
		        			cell.setCellValue(d);
		        			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		        		}else if(column.getType() == ExcelFormatType.NUMBER_WITH_COMMA){
		        			double d = Double.parseDouble((String)cellData.getValue() == "" ? "0" : (String)cellData.getValue());  
		        			cell.setCellValue(d);
		        			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		        		}else if(column.getType() == ExcelFormatType.NUMBER_WITH_COMMA_POINT){
		        			double d = Double.parseDouble((String)cellData.getValue() == "" ? "0" : (String)cellData.getValue());  
		        			cell.setCellValue(d);
		        			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		        		}else if(column.getType() == ExcelFormatType.DATE){
		        			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		        			cell.setCellValue((Date)cellData.getValue());
		        		}else {
		        			cell.setCellValue((String)cellData.getValue());
		        			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        		}
	        		}
	        		cell.setCellStyle(column.getStyle());
				}          	
	        }
        } 
	}
}