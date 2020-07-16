package com.ntels.ccbs.system.domain.common.service;

import java.util.HashMap;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 메뉴 관리.
 * 
 * @author smyun@ntels.com
 */
@XStreamAlias("menu")
public class Menu {
	
	/** 메뉴번호. */
	private Integer menuNo;
	
	/** 메뉴명. */
	@NotEmpty
	private String menuName;
	
	private String menuNameC;

	private List<HashMap<String, String>> listMenuName;
	
	/** 상위메뉴번호. */
	private String upMenuNo;
	
	/** 접근 url.  */
	private String  viewPath;
	
	/** 이미지경로. */
	private String  imgPath;
	
	/** 도움말 파일 명. */
	private String  helpFilename;
	
	/** depth. */
	private Integer stepNo;
	
	/** 출력 순서. */
	private Integer displayOrder;
	
	/** 메뉴 그룹 여부.  */
	@NotEmpty
	private String isfolder;
	
	/** 설명. */
	private String description;
	
	/** 국가코드. */
	private String countryCode;
	
	/** 언어코드. */
	private String languageCode;

	public Integer getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(Integer menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuNameC() {
		return menuNameC;
	}

	public void setMenuNameC(String menuNameC) {
		this.menuNameC = menuNameC;
	}

	public List<HashMap<String, String>> getListMenuName() {
		return listMenuName;
	}

	public void setListMenuName(List<HashMap<String, String>> listMenuName) {
		this.listMenuName = listMenuName;
	}

	public String getUpMenuNo() {
		return upMenuNo;
	}

	public void setUpMenuNo(String upMenuNo) {
		this.upMenuNo = upMenuNo;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getHelpFilename() {
		return helpFilename;
	}

	public void setHelpFilename(String helpFilename) {
		this.helpFilename = helpFilename;
	}

	public Integer getStepNo() {
		return stepNo;
	}

	public void setStepNo(Integer stepNo) {
		this.stepNo = stepNo;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getIsfolder() {
		return isfolder;
	}

	public void setIsfolder(String isfolder) {
		this.isfolder = isfolder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@Override
    public String toString() {
	    return "Menu [menuNo=" + menuNo + ", menuName=" + menuName
	            + ", menuNameC=" + menuNameC + ", listMenuName=" + listMenuName
	            + ", upMenuNo=" + upMenuNo + ", viewPath=" + viewPath
	            + ", imgPath=" + imgPath + ", helpFilename=" + helpFilename
	            + ", stepNo=" + stepNo + ", displayOrder=" + displayOrder
	            + ", isfolder=" + isfolder + ", description=" + description
	            + ", countryCode=" + countryCode + ", languageCode="
	            + languageCode + "]";
    }
	
	


}
