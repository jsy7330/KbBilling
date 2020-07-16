package com.ntels.ccbs.system.domain.common.common;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: UserSearchVO
 * 2. FileName : UserSearchVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.common.common
 * 4. Comment  : 사용자검색VO
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:53:59
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@XStreamAlias("userSearch")
public class UserSearchVO extends PagingValue {
	/**
	 * 사용자ID
	 */
	private String userId;
	/**
	 * 사용자명
	 */
	private String userName;
	/**
	 * 사업ID
	 */
	private String soId;
	/**
	 * 사업명
	 */
	private String soNm;
	/**
	 * 기본그룹ID
	 */
	private String userGroupId;
	/**
	 * 조직ID
	 */
	private String orgId;
	/**
	 * 조직명
	 */
	private String orgNm;
	/**
	 * 사용자유형
	 */
	private String crrTp;
	/**
	 * 사용자유형명
	 */
	private String crrTpNm;
	/**
	 * 휴대폰번호
	 */
	private String mtelNo;
	/**
	 * 이메일
	 */
	private String eMail;
	/**
	 * 사업ID 조건
	 */
	private String condSoId;	
	/**
	 * 사용자명 조건
	 */
	private String condUserNm;	//사용자명
	/**
	 * 팝업종류
	 */
	private String popType;
	/**
	 * 부모창에서 받을 사용자명
	 */
	private String returnId1;			
	/**
	 * 부모창에서 받을 사용자ID
	 */
	private String returnId2;
	
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
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
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
	public String getCrrTp() {
		return crrTp;
	}
	public void setCrrTp(String crrTp) {
		this.crrTp = crrTp;
	}
	public String getCrrTpNm() {
		return crrTpNm;
	}
	public void setCrrTpNm(String crrTpNm) {
		this.crrTpNm = crrTpNm;
	}
	public String getMtelNo() {
		return mtelNo;
	}
	public void setMtelNo(String mtelNo) {
		this.mtelNo = mtelNo;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getCondSoId() {
		return condSoId;
	}
	public void setCondSoId(String condSoId) {
		this.condSoId = condSoId;
	}
	public String getCondUserNm() {
		return condUserNm;
	}
	public void setCondUserNm(String condUserNm) {
		this.condUserNm = condUserNm;
	}
	public String getPopType() {
		return popType;
	}
	public void setPopType(String popType) {
		this.popType = popType;
	}
	public String getReturnId1() {
		return returnId1;
	}
	public void setReturnId1(String returnId1) {
		this.returnId1 = returnId1;
	}
	public String getReturnId2() {
		return returnId2;
	}
	public void setReturnId2(String returnId2) {
		this.returnId2 = returnId2;
	}
}