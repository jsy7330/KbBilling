package com.ntels.ccbs.system.domain.common.service;

import java.io.Serializable;

/**
 * 선택 메뉴.
 * 
 * @author smyun@ntels.com
 */
public class SelectedMenu implements Serializable{
	/** Serializable serialVersionUID. */
	private static final long serialVersionUID = 2269785928007434720L;
	
	/** 메뉴 번호. */
	private String menuNo;
	
	/** 선택 메뉴 번호. */
	private String selectMenuNo;
	
	/** 선택 메뉴 명 */
	private String selectMenuNm;
	
	/** 최상위 메뉴. */
	private String topMenuNo;
	
	/** 최상위 메뉴 명 */
	private String topMenuNm;

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
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
		builder.append("SelectedMenu [menuNo=");
		builder.append(menuNo);
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
