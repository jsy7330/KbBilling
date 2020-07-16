package com.ntels.ccbs.system.domain.common.service;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 사용자 그룹 권한.
 * 
 * @author smyun@ntels.com
 */
@XStreamAlias("userGroupAuth")
public class UserGroupAuth extends Menu implements Serializable {
	
	/** Serializable serialVersionUID. */
	private static final long serialVersionUID = 8021174022201439098L;
	
	/** 사용자 그룹 ID. */
	private String userGroupId;
	
	/** 권한 구분 Read. */
	private String authRYn;
	
	/** 권한 구분 Create. */
	private String authCYn;
	
	/** 권한 구분 Print. */
	private String authPYn;
	
	/** 권한 구분 Update. */
	private String authUYn;
	
	/** 권한 구분 Delete. */
	private String authDYn;
	
	/** 선택 메뉴 번호. */
	private String selectedMenuNo;
	
	/** 선택 메뉴 명. */
	private String selectedMenuName;

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
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

	public String getSelectedMenuNo() {
		return selectedMenuNo;
	}

	public void setSelectedMenuNo(String selectedMenuNo) {
		this.selectedMenuNo = selectedMenuNo;
	}

	public String getSelectedMenuName() {
		return selectedMenuName;
	}

	public void setSelectedMenuName(String selectedMenuName) {
		this.selectedMenuName = selectedMenuName;
	}

	@Override
    public String toString() {
	    return "UserGroupAuth [userGroupId=" + userGroupId + ", authRYn="
	            + authRYn + ", authCYn=" + authCYn + ", authPYn=" + authPYn
	            + ", authUYn=" + authUYn + ", authDYn=" + authDYn
	            + ", selectedMenuNo=" + selectedMenuNo + ", selectedMenuName="
	            + selectedMenuName + "]";
    }
	
	


}
