package com.ntels.ccbs.system.domain.common.service;

import java.io.Serializable;
import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * <PRE>
 * 1. ClassName: LeftSubMenu
 * 2. FileName : LeftSubMenu.java
 * 3. Package  : com.ntels.ccbs.system.domain.authorization
 * 4. Comment  : Left 하위 메뉴
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 4. 7. 오후 1:28:01
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 4. 7.    : 신규개발
 * </PRE>
 */
@XStreamAlias("leftSubMenu")
public class LeftSubMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829731718211382425L;

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
	
	/** 읽기 권한 */
	private String authRYn;
	/** 쓰기 권한 */
	private String authCYn;
	/** 출력 권한 */
	private String authPYn;
	/** 수정 권한 */	
	private String authUYn;
	/** 삭제 권한 */
	private String authDYn;
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
	public String getAuthRYn() {
		return authRYn;
	}
	public void setAuthRYn(String authRYn) {
		this.authRYn = authRYn;
	}
	public String getAuthCYn() {
		return authCYn;
	}
	public void setAuthCYn(String authCYn) {
		this.authCYn = authCYn;
	}
	public String getAuthPYn() {
		return authPYn;
	}
	public void setAuthPYn(String authPYn) {
		this.authPYn = authPYn;
	}
	public String getAuthUYn() {
		return authUYn;
	}
	public void setAuthUYn(String authUYn) {
		this.authUYn = authUYn;
	}
	public String getAuthDYn() {
		return authDYn;
	}
	public void setAuthDYn(String authDYn) {
		this.authDYn = authDYn;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LeftSubMenu [menuNo=");
		builder.append(menuNo);
		builder.append(", menuNm=");
		builder.append(menuNm);
		builder.append(", stepNo=");
		builder.append(stepNo);
		builder.append(", viewPath=");
		builder.append(viewPath);
		builder.append(", displayOrder=");
		builder.append(displayOrder);
		builder.append(", authRYn=");
		builder.append(authRYn);
		builder.append(", authCYn=");
		builder.append(authCYn);
		builder.append(", authPYn=");
		builder.append(authPYn);
		builder.append(", authUYn=");
		builder.append(authUYn);
		builder.append(", authDYn=");
		builder.append(authDYn);
		builder.append("]");
		return builder.toString();
	}


	

}
