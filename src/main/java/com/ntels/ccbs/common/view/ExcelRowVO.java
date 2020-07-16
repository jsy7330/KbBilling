package com.ntels.ccbs.common.view;

import java.util.Map;

/**
 * <PRE>
 * 1. ClassName: ExcelRowVO
 * 2. FileName : ExcelRowVO.java
 * 3. Package  : com.ntels.ccbs.common.view
 * 4. Comment  : 엑셀 출력용 Row VO
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 27. 오후 5:37:05
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 27.    : 신규개발
 * </PRE>
 */
public class ExcelRowVO {

	/**
	 * 한 Row Map data
	 */
	private Map<String, ExcelCellVO> rowData;

	/**
	 * @return the rowData
	 */
	public Map<String, ExcelCellVO> getRowData() {
		return rowData;
	}

	/**
	 * @param rowData the rowData to set
	 */
	public void setRowData(Map<String, ExcelCellVO> rowData) {
		this.rowData = rowData;
	}
	
	public ExcelCellVO getCell(String key){
		return this.rowData.get(key);
	}
	
}
