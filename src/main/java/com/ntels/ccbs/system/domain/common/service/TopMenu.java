package com.ntels.ccbs.system.domain.common.service;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * <PRE>
 * 1. ClassName: TopMenu
 * 2. FileName : TopMenu.java
 * 3. Package  : com.ntels.ccbs.system.domain.authorization
 * 4. Comment  : 최상위 메뉴
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 4. 7. 오후 1:28:46
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 4. 7.    : 신규개발
 * </PRE>
 */
@XStreamAlias("topMenu")
public class TopMenu implements Serializable {
	
	private static final long serialVersionUID = 5763294192476000409L;

	/** 메뉴 NO */
	private String menuNo;
	
	/** 메뉴명 */
	private String menuNm;
	
	/** 메뉴 레벨 */
	private String stepNo;
	
	/** 표시순서 */
	private String displayOrder;
	
	/** 하위 메뉴 리스트 **/
	private List<TopSubMenu> subMenu;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TopMenu [menuNo=");
		builder.append(menuNo);
		builder.append(", menuNm=");
		builder.append(menuNm);
		builder.append(", stepNo=");
		builder.append(stepNo);
		builder.append(", displayOrder=");
		builder.append(displayOrder);
		builder.append(", subMenu=");
		builder.append(subMenu);
		builder.append("]");
		return builder.toString();
	}

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

	public List<TopSubMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<TopSubMenu> subMenu) {
		this.subMenu = subMenu;
	}




}
