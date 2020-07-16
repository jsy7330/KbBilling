package com.ntels.ccbs.system.domain.auth.authMng;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: SoAuthMngVO
 * 2. FileName : SoAuthMngVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.auth.authMng
 * 4. Comment  : 사용자별 사업권한관리VO
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:01:22
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@XStreamAlias("soAuth")
public class SoAuthMngVO extends PagingValue {
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
	 * 사업ID
	 */
	private String soId;
	/**
	 * 사업명
	 */
	private String soNm;
	
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
	
	/**
	 * 등록자
	 */
	private String regrId;
	/**
	 * 현재날짜
	 */
	private Date sysToDate;
	
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
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public Date getSysToDate() {
		return sysToDate;
	}
	public void setSysToDate(Date sysToDate) {
		this.sysToDate = sysToDate;
	}
	
	
}