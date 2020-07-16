package com.ntels.ccbs.system.domain.common.service;

import java.io.Serializable;
import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * <PRE>
 * 1. ClassName: TopSubMenu
 * 2. FileName : TopSubMenu.java
 * 3. Package  : com.ntels.ccbs.system.domain.authorization
 * 4. Comment  : 상위 서브 메뉴
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 4. 7. 오후 1:28:57
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 4. 7.    : 신규개발
 * </PRE>
 */
@XStreamAlias("topSubMenu")
public class TopSubMenu implements Serializable {
	private static final long serialVersionUID = -6676050901542939674L;

	/** 메뉴 NO */
	private String menuNo;
	
	/** 메뉴명 */
	private String menuNm;
	
	/** 메뉴 레벨 */
	private String stepNo;
	
	/** 기본 표시 URL */
	private String viewPath;
	
	/** 표시순서 */
	private String displayOrder;
	
	/** 선택 메뉴 NO */
	private String selectMenuNo;
	
	/** 선택 메뉴명 */
	private String selectMenuNm;
	
	/** 최상위 메뉴 NO */
	private String topMenuNo;
	
	
	/** 최상위 메뉴명 */
	private String topMenuNm;


	public String getMenuNo() {
		return menuNo;
	}


	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}


	public String getMenuNm() {
		return menuNm;
	}


	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}


	public String getStepNo() {
		return stepNo;
	}


	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}


	public String getViewPath() {
		return viewPath;
	}


	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}


	public String getDisplayOrder() {
		return displayOrder;
	}


	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}


	public String getSelectMenuNo() {
		return selectMenuNo;
	}


	public void setSelectMenuNo(String selectMenuNo) {
		this.selectMenuNo = selectMenuNo;
	}


	public String getSelectMenuNm() {
		return selectMenuNm;
	}


	public void setSelectMenuNm(String selectMenuNm) {
		this.selectMenuNm = selectMenuNm;
	}


	public String getTopMenuNo() {
		return topMenuNo;
	}


	public void setTopMenuNo(String topMenuNo) {
		this.topMenuNo = topMenuNo;
	}


	public String getTopMenuNm() {
		return topMenuNm;
	}


	public void setTopMenuNm(String topMenuNm) {
		this.topMenuNm = topMenuNm;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TopSubMenu [menuNo=");
		builder.append(menuNo);
		builder.append(", menuNm=");
		builder.append(menuNm);
		builder.append(", stepNo=");
		builder.append(stepNo);
		builder.append(", viewPath=");
		builder.append(viewPath);
		builder.append(", displayOrder=");
		builder.append(displayOrder);
		builder.append(", selectMenuNo=");
		builder.append(selectMenuNo);
		builder.append(", selectMenuNm=");
		builder.append(selectMenuNm);
		builder.append(", topMenuNo=");
		builder.append(topMenuNo);
		builder.append(", topMenuNm=");
		builder.append(topMenuNm);
		builder.append("]");
		return builder.toString();
	}
	

	

}
