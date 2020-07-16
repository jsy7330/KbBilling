package com.ntels.ccbs.customer.domain.contract.receipt;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class ReceiptCounselVO implements Serializable,CommonVO{

	private static final long serialVersionUID = -1029437278763613223L;
	private String rcptId; /* 접수번호 */
	private String soId; /* 사업ID */
	private String custId; /* 고객ID */
	private String ctrtId; /* 계약ID */
	private String rcptDt; /* 접수일자 */
	private String rcptDm; /* 접수시간 */
	private String procDt; /* 처리일자 */
	private String procDm; /* 처리시간 */
	private String cmplDt; /* 완료일자 */
	private String cmplDm; /* 완료시간 */
	private String mstRcptId; /* 마스터접수번호 */
	private String pressRcptYn; /* 독촉여부 */
	private String claimYn; /* 클레임여부 */
	private String rcptTp; /* 접수구분 */
	private String rcptTpNm; /* 접수구분 */
	private String reqTelNo; /* 요청전화번호 */
	private String reqMtelNo; /* 요청모바일번호 */
	private String reqEml; /* 요청이메일 */
	private String reqNm; /* 요청자명 */
	private String custRel; /* 고객관계 */
	private String custRelNm; /* 고객관계 */
	private String cnslMstCl; /* 상담대분류 */
	private String cnslMstClNm; /* 상담대분류명 */
	private String cnslMidCl; /* 상담중분류 */
	private String cnslMidClNm; /* 상담중분류명 */
	private String cnslSlvCl; /* 상담소분류 */
	private String cnslSlvClNm; /* 상담소분류명 */
	private String cnslResnCd; /* 접수사유 */
	private String cnslResnCdNm; /* 접수사유명 */
	private String reqDesc; /* 요청내용 */
	private String procDesc; /* 응대내용 */
	private String rcptOrgId; /* 접수조직 */
	private String rcptOrgIdNm; /* 접수조직 */
	private String rcptUsrId; /* 접수자 */
	private String rcptUsrIdNm; /* 접수자 */
	private String procOrgId; /* 처리조직 */
	private String procOrgIdNm; /* 처리조직 */
	private String procUsrId; /* 처리자 */
	private String procUsrIdNm; /* 처리자 */
	private String cmplOrgId; /* 완료조직 */
	private String cmplOrgIdNm; /* 완료조직 */
	private String cmplUsrId; /* 완료자 */
	private String cmplUsrIdNm; /* 완료자 */
	private String cnslStat; /* 상담상태 */
	private String cnslStatNm; /* 상담상태 */
	private String tranStat; /* 전달상태 */
	private String tranStatNm; /* 전달상태 */
	private String extId; /* 확장ID */
	private String regrId; /* 등록자 */
	private String regrIdNm; /* 등록자 */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자 */
	private String chgrIdNm; /* 수정자 */
	private Date chgDate; /* 수정일시 */
	
	private String searchStatDt; /*접수시간 조회 Start*/
	private String searchEndDt; /*접수시간 조회 End*/
	
	private String commonGrp;
	private String commonCd;
	private String commonCdNm;
	private String refCode;
	private String refCode2;
	private String refCode3;
	private String refCode4;
	private String defaultYn;
	private String rmrk;
	private String sortNo;
	private String useYn;	
	private String lng;	
	
	private String elapse; /*경과시간*/
	private String condCustNm; /*고객명*/
	private String userInfo; /*사용자정보*/
	private String orgInfo; /*조직정보*/
	private String rcptYn; /*접수기준*/


	private String orgId; /*조직ID*/
	
	private String condCustId; /*고객명*/

	private String elapseDate;
	
	public String getRcptId() {
		return rcptId;
	}
	public void setRcptId(String rcptId) {
		this.rcptId = rcptId;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getRcptDt() {
		return rcptDt;
	}
	public void setRcptDt(String rcptDt) {
		this.rcptDt = rcptDt;
	}
	public String getRcptDm() {
		return rcptDm;
	}
	public void setRcptDm(String rcptDm) {
		this.rcptDm = rcptDm;
	}
	public String getProcDt() {
		return procDt;
	}
	public void setProcDt(String procDt) {
		this.procDt = procDt;
	}
	public String getProcDm() {
		return procDm;
	}
	public void setProcDm(String procDm) {
		this.procDm = procDm;
	}
	public String getCmplDt() {
		return cmplDt;
	}
	public void setCmplDt(String cmplDt) {
		this.cmplDt = cmplDt;
	}
	public String getCmplDm() {
		return cmplDm;
	}
	public void setCmplDm(String cmplDm) {
		this.cmplDm = cmplDm;
	}
	public String getMstRcptId() {
		return mstRcptId;
	}
	public void setMstRcptId(String mstRcptId) {
		this.mstRcptId = mstRcptId;
	}
	public String getPressRcptYn() {
		return pressRcptYn;
	}
	public void setPressRcptYn(String pressRcptYn) {
		this.pressRcptYn = pressRcptYn;
	}
	public String getClaimYn() {
		return claimYn;
	}
	public void setClaimYn(String claimYn) {
		this.claimYn = claimYn;
	}
	public String getRcptTp() {
		return rcptTp;
	}
	public void setRcptTp(String rcptTp) {
		this.rcptTp = rcptTp;
	}
	public String getRcptTpNm() {
		return rcptTpNm;
	}
	public void setRcptTpNm(String rcptTpNm) {
		this.rcptTpNm = rcptTpNm;
	}
	public String getReqTelNo() {
		return reqTelNo;
	}
	public void setReqTelNo(String reqTelNo) {
		this.reqTelNo = reqTelNo;
	}
	public String getReqMtelNo() {
		return reqMtelNo;
	}
	public void setReqMtelNo(String reqMtelNo) {
		this.reqMtelNo = reqMtelNo;
	}
	public String getReqEml() {
		return reqEml;
	}
	public void setReqEml(String reqEml) {
		this.reqEml = reqEml;
	}
	public String getReqNm() {
		return reqNm;
	}
	public void setReqNm(String reqNm) {
		this.reqNm = reqNm;
	}
	public String getCustRel() {
		return custRel;
	}
	public void setCustRel(String custRel) {
		this.custRel = custRel;
	}
	public String getCustRelNm() {
		return custRelNm;
	}
	public void setCustRelNm(String custRelNm) {
		this.custRelNm = custRelNm;
	}
	public String getCnslMstCl() {
		return cnslMstCl;
	}
	public void setCnslMstCl(String cnslMstCl) {
		this.cnslMstCl = cnslMstCl;
	}
	public String getCnslMstClNm() {
		return cnslMstClNm;
	}
	public void setCnslMstClNm(String cnslMstClNm) {
		this.cnslMstClNm = cnslMstClNm;
	}
	public String getCnslMidCl() {
		return cnslMidCl;
	}
	public void setCnslMidCl(String cnslMidCl) {
		this.cnslMidCl = cnslMidCl;
	}
	public String getCnslMidClNm() {
		return cnslMidClNm;
	}
	public void setCnslMidClNm(String cnslMidClNm) {
		this.cnslMidClNm = cnslMidClNm;
	}
	public String getCnslSlvCl() {
		return cnslSlvCl;
	}
	public void setCnslSlvCl(String cnslSlvCl) {
		this.cnslSlvCl = cnslSlvCl;
	}
	public String getCnslSlvClNm() {
		return cnslSlvClNm;
	}
	public void setCnslSlvClNm(String cnslSlvClNm) {
		this.cnslSlvClNm = cnslSlvClNm;
	}
	public String getCnslResnCd() {
		return cnslResnCd;
	}
	public void setCnslResnCd(String cnslResnCd) {
		this.cnslResnCd = cnslResnCd;
	}
	public String getCnslResnCdNm() {
		return cnslResnCdNm;
	}
	public void setCnslResnCdNm(String cnslResnCdNm) {
		this.cnslResnCdNm = cnslResnCdNm;
	}
	public String getReqDesc() {
		return reqDesc;
	}
	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}
	public String getProcDesc() {
		return procDesc;
	}
	public void setProcDesc(String procDesc) {
		this.procDesc = procDesc;
	}
	public String getRcptOrgId() {
		return rcptOrgId;
	}
	public void setRcptOrgId(String rcptOrgId) {
		this.rcptOrgId = rcptOrgId;
	}
	public String getRcptOrgIdNm() {
		return rcptOrgIdNm;
	}
	public void setRcptOrgIdNm(String rcptOrgIdNm) {
		this.rcptOrgIdNm = rcptOrgIdNm;
	}
	public String getRcptUsrId() {
		return rcptUsrId;
	}
	public void setRcptUsrId(String rcptUsrId) {
		this.rcptUsrId = rcptUsrId;
	}
	public String getRcptUsrIdNm() {
		return rcptUsrIdNm;
	}
	public void setRcptUsrIdNm(String rcptUsrIdNm) {
		this.rcptUsrIdNm = rcptUsrIdNm;
	}
	public String getProcOrgId() {
		return procOrgId;
	}
	public void setProcOrgId(String procOrgId) {
		this.procOrgId = procOrgId;
	}
	public String getProcOrgIdNm() {
		return procOrgIdNm;
	}
	public void setProcOrgIdNm(String procOrgIdNm) {
		this.procOrgIdNm = procOrgIdNm;
	}
	public String getProcUsrId() {
		return procUsrId;
	}
	public void setProcUsrId(String procUsrId) {
		this.procUsrId = procUsrId;
	}
	public String getProcUsrIdNm() {
		return procUsrIdNm;
	}
	public void setProcUsrIdNm(String procUsrIdNm) {
		this.procUsrIdNm = procUsrIdNm;
	}
	public String getCmplOrgId() {
		return cmplOrgId;
	}
	public void setCmplOrgId(String cmplOrgId) {
		this.cmplOrgId = cmplOrgId;
	}
	public String getCmplOrgIdNm() {
		return cmplOrgIdNm;
	}
	public void setCmplOrgIdNm(String cmplOrgIdNm) {
		this.cmplOrgIdNm = cmplOrgIdNm;
	}
	public String getCmplUsrId() {
		return cmplUsrId;
	}
	public void setCmplUsrId(String cmplUsrId) {
		this.cmplUsrId = cmplUsrId;
	}
	public String getCmplUsrIdNm() {
		return cmplUsrIdNm;
	}
	public void setCmplUsrIdNm(String cmplUsrIdNm) {
		this.cmplUsrIdNm = cmplUsrIdNm;
	}
	public String getCnslStat() {
		return cnslStat;
	}
	public void setCnslStat(String cnslStat) {
		this.cnslStat = cnslStat;
	}
	public String getCnslStatNm() {
		return cnslStatNm;
	}
	public void setCnslStatNm(String cnslStatNm) {
		this.cnslStatNm = cnslStatNm;
	}
	public String getTranStat() {
		return tranStat;
	}
	public void setTranStat(String tranStat) {
		this.tranStat = tranStat;
	}
	public String getTranStatNm() {
		return tranStatNm;
	}
	public void setTranStatNm(String tranStatNm) {
		this.tranStatNm = tranStatNm;
	}
	public String getExtId() {
		return extId;
	}
	public void setExtId(String extId) {
		this.extId = extId;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getRegrIdNm() {
		return regrIdNm;
	}
	public void setRegrIdNm(String regrIdNm) {
		this.regrIdNm = regrIdNm;
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
	public String getChgrIdNm() {
		return chgrIdNm;
	}
	public void setChgrIdNm(String chgrIdNm) {
		this.chgrIdNm = chgrIdNm;
	}
	public Date getChgDate() {
		return chgDate;
	}
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	public String getSearchStatDt() {
		return searchStatDt;
	}
	public void setSearchStatDt(String searchStatDt) {
		this.searchStatDt = searchStatDt;
	}
	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}
	public String getCommonGrp() {
		return commonGrp;
	}
	public void setCommonGrp(String commonGrp) {
		this.commonGrp = commonGrp;
	}
	public String getCommonCd() {
		return commonCd;
	}
	public void setCommonCd(String commonCd) {
		this.commonCd = commonCd;
	}
	public String getCommonCdNm() {
		return commonCdNm;
	}
	public void setCommonCdNm(String commonCdNm) {
		this.commonCdNm = commonCdNm;
	}
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	public String getRefCode2() {
		return refCode2;
	}
	public void setRefCode2(String refCode2) {
		this.refCode2 = refCode2;
	}
	public String getRefCode3() {
		return refCode3;
	}
	public void setRefCode3(String refCode3) {
		this.refCode3 = refCode3;
	}
	public String getRefCode4() {
		return refCode4;
	}
	public void setRefCode4(String refCode4) {
		this.refCode4 = refCode4;
	}
	public String getDefaultYn() {
		return defaultYn;
	}
	public void setDefaultYn(String defaultYn) {
		this.defaultYn = defaultYn;
	}
	public String getRmrk() {
		return rmrk;
	}
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
		
	public String getElapse() {
		return elapse;
	}
	public void setElapse(String elapse) {
		this.elapse = elapse;
	}
	public String getCondCustNm() {
		return condCustNm;
	}
	public void setCondCustNm(String condCustNm) {
		this.condCustNm = condCustNm;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public String getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(String orgInfo) {
		this.orgInfo = orgInfo;
	}
	public String getRcptYn() {
		return rcptYn;
	}
	public void setRcptYn(String rcptYn) {
		this.rcptYn = rcptYn;
	}
	public String getCondCustId() {
		return condCustId;
	}
	public void setCondCustId(String condCustId) {
		this.condCustId = condCustId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getElapseDate() {
		return elapseDate;
	}
	public void setElapseDate(String elapseDate) {
		this.elapseDate = elapseDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
