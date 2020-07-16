package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class RcptTransInfoVO implements Serializable,CommonVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4449245057183690279L;
	private String rcptId; /* 접수번호 */
	private Date transDate; /* 전달일시 */
	private int transSeq; /* 전달순서 */
	private String orgSoId; /* 전달사업ID */
	private String orgSoIdNm; /* 전달사업ID */
	private String orgOrgId; /* 전달조직ID */
	private String orgOrgIdNm; /* 전달조직ID */
	private String orgUsrId; /* 전달ID */
	private String orgUsrIdNm; /* 전달ID */
	private String soId; /* 사업ID */
	private String soIdNm; /* 사업ID */
	private String orgId; /* 조직ID */
	private String orgIdNm; /* 조직ID */
	private String usrId; /* 사용자ID */
	private String usrIdNm; /* 사용자ID */
	private String transMemo; /* 메모 */
	private String transStat; /* 전달상태 */
	private String transStatNm; /* 전달상태 */
	private String recvDttm; /* 수신일시 */
	private String cmplDttm; /* 완료일시 */
	private String regrId; /* 등록자 */
	private String regrIdNm; /* 등록자 */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자 */
	private String chgrIdNm; /* 수정자 */
	private Date chgDate; /* 수정일시 */
	/**
	 * @return the rcptId
	 */
	public String getRcptId() {
		return rcptId;
	}
	/**
	 * @param rcptId the rcptId to set
	 */
	public void setRcptId(String rcptId) {
		this.rcptId = rcptId;
	}
	/**
	 * @return the transDate
	 */
	public Date getTransDate() {
		return transDate;
	}
	/**
	 * @param transDate the transDate to set
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	/**
	 * @return the transSeq
	 */
	public int getTransSeq() {
		return transSeq;
	}
	/**
	 * @param transSeq the transSeq to set
	 */
	public void setTransSeq(int transSeq) {
		this.transSeq = transSeq;
	}
	/**
	 * @return the orgSoId
	 */
	public String getOrgSoId() {
		return orgSoId;
	}
	/**
	 * @param orgSoId the orgSoId to set
	 */
	public void setOrgSoId(String orgSoId) {
		this.orgSoId = orgSoId;
	}
	/**
	 * @return the orgSoIdNm
	 */
	public String getOrgSoIdNm() {
		return orgSoIdNm;
	}
	/**
	 * @param orgSoIdNm the orgSoIdNm to set
	 */
	public void setOrgSoIdNm(String orgSoIdNm) {
		this.orgSoIdNm = orgSoIdNm;
	}
	/**
	 * @return the orgOrgId
	 */
	public String getOrgOrgId() {
		return orgOrgId;
	}
	/**
	 * @param orgOrgId the orgOrgId to set
	 */
	public void setOrgOrgId(String orgOrgId) {
		this.orgOrgId = orgOrgId;
	}
	/**
	 * @return the orgOrgIdNm
	 */
	public String getOrgOrgIdNm() {
		return orgOrgIdNm;
	}
	/**
	 * @param orgOrgIdNm the orgOrgIdNm to set
	 */
	public void setOrgOrgIdNm(String orgOrgIdNm) {
		this.orgOrgIdNm = orgOrgIdNm;
	}
	/**
	 * @return the orgUsrId
	 */
	public String getOrgUsrId() {
		return orgUsrId;
	}
	/**
	 * @param orgUsrId the orgUsrId to set
	 */
	public void setOrgUsrId(String orgUsrId) {
		this.orgUsrId = orgUsrId;
	}
	/**
	 * @return the orgUsrIdNm
	 */
	public String getOrgUsrIdNm() {
		return orgUsrIdNm;
	}
	/**
	 * @param orgUsrIdNm the orgUsrIdNm to set
	 */
	public void setOrgUsrIdNm(String orgUsrIdNm) {
		this.orgUsrIdNm = orgUsrIdNm;
	}
	/**
	 * @return the soId
	 */
	public String getSoId() {
		return soId;
	}
	/**
	 * @param soId the soId to set
	 */
	public void setSoId(String soId) {
		this.soId = soId;
	}
	/**
	 * @return the soIdNm
	 */
	public String getSoIdNm() {
		return soIdNm;
	}
	/**
	 * @param soIdNm the soIdNm to set
	 */
	public void setSoIdNm(String soIdNm) {
		this.soIdNm = soIdNm;
	}
	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return the orgIdNm
	 */
	public String getOrgIdNm() {
		return orgIdNm;
	}
	/**
	 * @param orgIdNm the orgIdNm to set
	 */
	public void setOrgIdNm(String orgIdNm) {
		this.orgIdNm = orgIdNm;
	}
	/**
	 * @return the usrId
	 */
	public String getUsrId() {
		return usrId;
	}
	/**
	 * @param usrId the usrId to set
	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	/**
	 * @return the usrIdNm
	 */
	public String getUsrIdNm() {
		return usrIdNm;
	}
	/**
	 * @param usrIdNm the usrIdNm to set
	 */
	public void setUsrIdNm(String usrIdNm) {
		this.usrIdNm = usrIdNm;
	}
	/**
	 * @return the transMemo
	 */
	public String getTransMemo() {
		return transMemo;
	}
	/**
	 * @param transMemo the transMemo to set
	 */
	public void setTransMemo(String transMemo) {
		this.transMemo = transMemo;
	}
	/**
	 * @return the transStat
	 */
	public String getTransStat() {
		return transStat;
	}
	/**
	 * @param transStat the transStat to set
	 */
	public void setTransStat(String transStat) {
		this.transStat = transStat;
	}
	/**
	 * @return the transStatNm
	 */
	public String getTransStatNm() {
		return transStatNm;
	}
	/**
	 * @param transStatNm the transStatNm to set
	 */
	public void setTransStatNm(String transStatNm) {
		this.transStatNm = transStatNm;
	}
	/**
	 * @return the recvDttm
	 */
	public String getRecvDttm() {
		return recvDttm;
	}
	/**
	 * @param recvDttm the recvDttm to set
	 */
	public void setRecvDttm(String recvDttm) {
		this.recvDttm = recvDttm;
	}
	/**
	 * @return the cmplDttm
	 */
	public String getCmplDttm() {
		return cmplDttm;
	}
	/**
	 * @param cmplDttm the cmplDttm to set
	 */
	public void setCmplDttm(String cmplDttm) {
		this.cmplDttm = cmplDttm;
	}
	/**
	 * @return the regrId
	 */
	public String getRegrId() {
		return regrId;
	}
	/**
	 * @param regrId the regrId to set
	 */
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	/**
	 * @return the regrIdNm
	 */
	public String getRegrIdNm() {
		return regrIdNm;
	}
	/**
	 * @param regrIdNm the regrIdNm to set
	 */
	public void setRegrIdNm(String regrIdNm) {
		this.regrIdNm = regrIdNm;
	}
	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the chgrId
	 */
	public String getChgrId() {
		return chgrId;
	}
	/**
	 * @param chgrId the chgrId to set
	 */
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	/**
	 * @return the chgrIdNm
	 */
	public String getChgrIdNm() {
		return chgrIdNm;
	}
	/**
	 * @param chgrIdNm the chgrIdNm to set
	 */
	public void setChgrIdNm(String chgrIdNm) {
		this.chgrIdNm = chgrIdNm;
	}
	/**
	 * @return the chgDate
	 */
	public Date getChgDate() {
		return chgDate;
	}
	/**
	 * @param chgDate the chgDate to set
	 */
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcptTransInfoVO [rcptId=");
		builder.append(rcptId);
		builder.append(", transDate=");
		builder.append(transDate);
		builder.append(", transSeq=");
		builder.append(transSeq);
		builder.append(", orgSoId=");
		builder.append(orgSoId);
		builder.append(", orgSoIdNm=");
		builder.append(orgSoIdNm);
		builder.append(", orgOrgId=");
		builder.append(orgOrgId);
		builder.append(", orgOrgIdNm=");
		builder.append(orgOrgIdNm);
		builder.append(", orgUsrId=");
		builder.append(orgUsrId);
		builder.append(", orgUsrIdNm=");
		builder.append(orgUsrIdNm);
		builder.append(", soId=");
		builder.append(soId);
		builder.append(", soIdNm=");
		builder.append(soIdNm);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", orgIdNm=");
		builder.append(orgIdNm);
		builder.append(", usrId=");
		builder.append(usrId);
		builder.append(", usrIdNm=");
		builder.append(usrIdNm);
		builder.append(", transMemo=");
		builder.append(transMemo);
		builder.append(", transStat=");
		builder.append(transStat);
		builder.append(", transStatNm=");
		builder.append(transStatNm);
		builder.append(", recvDttm=");
		builder.append(recvDttm);
		builder.append(", cmplDttm=");
		builder.append(cmplDttm);
		builder.append(", regrId=");
		builder.append(regrId);
		builder.append(", regrIdNm=");
		builder.append(regrIdNm);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", chgrId=");
		builder.append(chgrId);
		builder.append(", chgrIdNm=");
		builder.append(chgrIdNm);
		builder.append(", chgDate=");
		builder.append(chgDate);
		builder.append("]");
		return builder.toString();
	}
				
}
