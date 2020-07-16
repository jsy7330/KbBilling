package com.ntels.ccbs.system.domain.user.userMng;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: UserMngVO
 * 2. FileName : UserMngVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.user.userMng
 * 4. Comment  : 사용자관리 VO
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 22. 오후 4:22:16
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 22.    : 신규개발
 * </PRE>
 */
@XStreamAlias("user")
public class UserMngVO extends PagingValue {
	/**
	 * 사용자ID
	 */
	private String userId;
	/**
	 * 비밀번호
	 */
	private String password;
	/**
	 * 사용자이름
	 */
	private String userName;
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
	 * 조직구분
	 */
	private String crrTp;
	/**
	 * 사원번호
	 */
	private String empNo;
	/**
	 * 전화번호
	 */
	private String telNo;
	/**
	 * 휴대전화
	 */
	private String mtelNo;
	/**
	 * 이메일
	 */
	private String eMail;
	/**
	 * 허용IP대역
	 */
	private String ipBandwidth;
	/**
	 * 로그인실패수
	 */
	private int loginFailCount;
	/**
	 * 비밀번호 만기일
	 */
	private String passwordDueDate;
	/**
	 * 비밀번호 교환주기
	 */
	private String passwordChangePeriod;
	/**
	 * 최종로그인일짜
	 */
	private String lastLoginDate;
	/**
	 * 최종로그인일시
	 */
	private String lastLoginTime;
	/**
	 * 계정잠금여부
	 */
	private String accountLock;
	/**
	 * 이전비밀번호1
	 */
	private String password1;
	/**
	 * 이전비밀번호2
	 */
	private String password2;
	/**
	 * 사용유무
	 */
	private String useYn;
	/**
	 * 등록자
	 */
	private String regrId;
	/**
	 * 등록일시
	 */
	private Date regDate;
	/**
	 * 수정자
	 */
	private String chgrId;
	/**
	 * 수정일시
	 */
	private Date chgDate;
	/**
	 * 사업명조건
	 */
	private String condSoId;	//사업명
	/**
	 * 조직ID 조건
	 */
	private String condOrgId;
	/**
	 * 조직명 조건
	 */
	private String condOrgNm;	//조직명
	/**
	 * 사용자명 조건
	 */
	private String condUserNm;	//사용자명
	/**
	 * 사용유무 조건
	 */
	private String condYn;		//사용유무
	/**
	 * 잠김여부 조건
	 */
	private String condLockYn;	//잠김여부
	/**
	 * 검색여부
	 */
	private String srchYn;
	/**
	 * 현재날짜
	 */
	private Date sysToDate;
	
	/**
	 * 기본그룹명
	 */
	private String userGroupName;
	/**
	 * 사업명
	 */
	private String soNm;
	/**
	 * 사업ID
	 */
	private String soId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
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
	public String getIpBandwidth() {
		return ipBandwidth;
	}
	public void setIpBandwidth(String ipBandwidth) {
		this.ipBandwidth = ipBandwidth;
	}
	public int getLoginFailCount() {
		return loginFailCount;
	}
	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	public String getPasswordDueDate() {
		return passwordDueDate;
	}
	public void setPasswordDueDate(String passwordDueDate) {
		this.passwordDueDate = passwordDueDate;
	}
	public String getPasswordChangePeriod() {
		return passwordChangePeriod;
	}
	public void setPasswordChangePeriod(String passwordChangePeriod) {
		this.passwordChangePeriod = passwordChangePeriod;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getAccountLock() {
		return accountLock;
	}
	public void setAccountLock(String accountLock) {
		this.accountLock = accountLock;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getChgrId() {
		return chgrId;
	}
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	public Date getChgDate() {
		return chgDate;
	}
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	public String getCondSoId() {
		return condSoId;
	}
	public void setCondSoId(String condSoId) {
		this.condSoId = condSoId;
	}
	public String getCondOrgId() {
		return condOrgId;
	}
	public void setCondOrgId(String condOrgId) {
		this.condOrgId = condOrgId;
	}
	public String getCondOrgNm() {
		return condOrgNm;
	}
	public void setCondOrgNm(String condOrgNm) {
		this.condOrgNm = condOrgNm;
	}
	public String getCondUserNm() {
		return condUserNm;
	}
	public void setCondUserNm(String condUserNm) {
		this.condUserNm = condUserNm;
	}
	public String getCondYn() {
		return condYn;
	}
	public void setCondYn(String condYn) {
		this.condYn = condYn;
	}
	public String getCondLockYn() {
		return condLockYn;
	}
	public void setCondLockYn(String condLockYn) {
		this.condLockYn = condLockYn;
	}
	public String getSrchYn() {
		return srchYn;
	}
	public void setSrchYn(String srchYn) {
		this.srchYn = srchYn;
	}
	public Date getSysToDate() {
		return sysToDate;
	}
	public void setSysToDate(Date sysToDate) {
		this.sysToDate = sysToDate;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	
	
	
}