package com.ntels.ccbs.system.domain.common.service;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * <PRE>
 * 1. ClassName: LeftTopMenu
 * 2. FileName : LeftTopMenu.java
 * 3. Package  : com.ntels.ccbs.system.domain.authorization
 * 4. Comment  : Left 상위 메뉴
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 4. 7. 오후 1:28:34
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 4. 7.    : 신규개발
 * </PRE>
 */
@XStreamAlias("leftTopMenu")
public class LeftTopMenu implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7925398158401727056L;

	/** 메뉴 NO */
	private String menuNo;
	
	/** 메뉴명 */
	private String menuNm;
	
	/** 메뉴 레벨 */
	private String stepNo;
	
	/** 표시순서 */
	private String displayOrder;
	
	/** 최상위 메뉴명 */
	private String topMenuNm;
	
	/** 하위 메뉴 리스트 **/
	private List<LeftSubMenu> leftSubMenu;

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

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getTopMenuNm() {
		return topMenuNm;
	}

	public void setTopMenuNm(String topMenuNm) {
		this.topMenuNm = topMenuNm;
	}

	public List<LeftSubMenu> getLeftSubMenu() {
		return leftSubMenu;
	}

	public void setLeftSubMenu(List<LeftSubMenu> leftSubMenu) {
		this.leftSubMenu = leftSubMenu;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LeftTopMenu [menuNo=");
		builder.append(menuNo);
		builder.append(", menuNm=");
		builder.append(menuNm);
		builder.append(", stepNo=");
		builder.append(stepNo);
		builder.append(", displayOrder=");
		builder.append(displayOrder);
		builder.append(", topMenuNm=");
		builder.append(topMenuNm);
		builder.append(", leftSubMenu=");
		builder.append(leftSubMenu);
		builder.append("]");
		return builder.toString();
	}





}
