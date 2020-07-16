package com.ntels.ccbs.common.view;

import java.util.List;

/**
 * <PRE>
 * 1. ClassName: ExcelSheetVO
 * 2. FileName : ExcelSheetVO.java
 * 3. Package  : com.ntels.ccbs.common.view
 * 4. Comment  : 엑셀 출력용 Sheet VO
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 27. 오후 5:37:34
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 27.    : 신규개발
 * </PRE>
 */
public class ExcelSheetVO {
	/**
	 * Sheet Name
	 */
	private String sheetName;
	
	/**
	 * Header Title Name List
	 */
	private List<ExcelColumnVO> titleList;
	
	/**
	 * Data List
	 */
	private List<ExcelRowVO> dataList;

	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * @return the titleList
	 */
	public List<ExcelColumnVO> getTitleList() {
		return titleList;
	}

	/**
	 * @param titleList the titleList to set
	 */
	public void setTitleList(List<ExcelColumnVO> titleList) {
		this.titleList = titleList;
	}

	/**
	 * @return the dataList
	 */
	public List<ExcelRowVO> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<ExcelRowVO> dataList) {
		this.dataList = dataList;
	}


	

	
	

}
