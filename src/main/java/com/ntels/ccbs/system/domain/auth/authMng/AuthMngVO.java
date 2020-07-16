package com.ntels.ccbs.system.domain.auth.authMng;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: AuthMngVO
 * 2. FileName : AuthMngVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.auth.authMng
 * 4. Comment  :그룹별권한관리VO
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 27. 오전 9:32:08
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 27.    : 신규개발
 * </PRE>
 */
@XStreamAlias("auth")
public class AuthMngVO {
	/**
	 * 사용자그룹ID
	 */
	private String userGroupId;
	/**
	 * 사용자그룹명
	 */
	private String userGroupName;
	/**
	 * 메인화면
	 */
	private String mainView;
	/**
	 * 설명
	 */
	private String description;
	/**
	 *  사용자그룹ID 조건
	 */
	private String condGroupId;
	/**
	 * 사용자그룹명조건
	 */
	private String condGroupNm;
	/**
	 * 메뉴번호
	 */
	private String menuNo;
	/**
	 * 전체선택
	 */
	private String checkAll;
	/**
	 * 읽기권한
	 */
	private String authRYn;
	/**
	 * 생성권한
	 */
	private String authCYn;
	/**
	 * 출력권한
	 */
	private String authPYn;
	/**
	 * 수정권한
	 */
	private String authUYn;
	/**
	 * 삭제권한
	 */
	private String authDYn;
	/**
	 * 순서번호
	 */
	private int stepNo;
	/**
	 * 출력순서
	 */
	private String displayOrder;
	/**
	 * 메뉴병
	 */
	private String menuName;
	/**
	 * 상위메뉴번호
	 */
	private String upMenuNo;
	
	public String getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	public String getMainView() {
		return mainView;
	}
	public void setMainView(String mainView) {
		this.mainView = mainView;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCondGroupId() {
		return condGroupId;
	}
	public void setCondGroupId(String condGroupId) {
		this.condGroupId = condGroupId;
	}
	public String getCondGroupNm() {
		return condGroupNm;
	}
	public void setCondGroupNm(String condGroupNm) {
		this.condGroupNm = condGroupNm;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getCheckAll() {
		return checkAll;
	}
	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
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
	public int getStepNo() {
		return stepNo;
	}
	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUpMenuNo() {
		return upMenuNo;
	}
	public void setUpMenuNo(String upMenuNo) {
		this.upMenuNo = upMenuNo;
	}
	
}