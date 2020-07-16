package com.ntels.ccbs.common.view;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.ntels.ccbs.common.consts.Consts.ExcelFormatType;

/**
 * <PRE>
 * 1. ClassName: ExcelColumnVO
 * 2. FileName : ExcelColumnVO.java
 * 3. Package  : com.ntels.ccbs.common.view
 * 4. Comment  : 엑셀 출력용 컬럼 타이블 VO
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 27. 오후 5:35:00
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 27.    : 신규개발
 * </PRE>
 */
public class ExcelColumnVO {
	/**
	 * Size
	 */
	private int columnSize;
	/**
	 * 타이틀
	 */
	private String title;
	
	/**
	 * Key
	 */
	private String key;
	
	/**
	 * Cell Style
	 */
	private XSSFCellStyle style;
	
	/**
	 * 출력 타입
	 */
	private ExcelFormatType type;
	
	
	public ExcelColumnVO(int columnSize, String title, String key, ExcelFormatType type) {
		super();
		this.columnSize = columnSize;
		this.title = title;
		this.key = key;
		this.type = type;
				
	}


	/**
	 * @return the columnSize
	 */
	public int getColumnSize() {
		return columnSize;
	}


	/**
	 * @param columnSize the columnSize to set
	 */
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}


	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}


	/**
	 * @return the style
	 */
	public XSSFCellStyle getStyle() {
		return style;
	}


	/**
	 * @param style the style to set
	 */
	public void setStyle(XSSFCellStyle style) {
		this.style = style;
	}


	/**
	 * @return the type
	 */
	public ExcelFormatType getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(ExcelFormatType type) {
		this.type = type;
	}


	

	
}
