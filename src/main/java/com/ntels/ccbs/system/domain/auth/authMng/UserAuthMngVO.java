package com.ntels.ccbs.system.domain.auth.authMng;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: UserAuthMngVO
 * 2. FileName : UserAuthMngVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.auth.authMng
 * 4. Comment  : 사용자별 그룹관리VO
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 22. 오후 5:58:17
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 22.    : 신규개발
 * </PRE>
 */
@XStreamAlias("userAuth")
public class UserAuthMngVO extends PagingValue {
	/**
	 * 사용자ID
	 */
	private String userId;
	/**
	 * 사용자명
	 */
	private String userName;
	/**
	 * 조직ID
	 */
	private String orgId;
	/**
	 * 조직명 
	 */
	private String orgNm;
	/**
	 * 사용자그룹ID
	 */
	private String userGroupId;
	/**
	 * 사용자그룹명
	 */
	private String userGroupName;
	
	/**
	 * 사용자아이디 조건
	 */
	private String condUserId;
	/**
	 * 사용자명 조건
	 */
	private String condUserNm;
	/**
	 * 조직아이디 조건
	 */
	private String condOrgId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
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
	public String getCondUserId() {
		return condUserId;
	}
	public void setCondUserId(String condUserId) {
		this.condUserId = condUserId;
	}
	public String getCondUserNm() {
		return condUserNm;
	}
	public void setCondUserNm(String condUserNm) {
		this.condUserNm = condUserNm;
	}
	public String getCondOrgId() {
		return condOrgId;
	}
	public void setCondOrgId(String condOrgId) {
		this.condOrgId = condOrgId;
	}
	

	
}