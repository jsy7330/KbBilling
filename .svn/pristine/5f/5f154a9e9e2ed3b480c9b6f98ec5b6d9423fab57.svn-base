package com.ntels.ccbs.common.view;

import java.util.Date;

/**
 * <PRE>
 * 1. ClassName: ExcelCellVO
 * 2. FileName : ExcelCellVO.java
 * 3. Package  : com.ntels.ccbs.common.view
 * 4. Comment  : 엑셀 출력용 Cell VO
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 27. 오후 5:33:55
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 27.    : 신규개발
 * </PRE>
 */
public class ExcelCellVO {
	
	
	/**
	 * 출력값(String or Date)
	 */
	private Object value;

	/**
	 * @return the value
	 */
	public Object getValue() {
		if(value instanceof String){
			return "null".equals(value) ? "" : value;
		}else if(value instanceof Date){
			return value;
		}else{
			return "null".equals(value) ? "" : value;
		}
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		if(value instanceof String){
			this.value = String.valueOf(value);
		}else if(value instanceof Date){
			this.value = value;
		}else{
			this.value = String.valueOf(value);
		}
	}
}
