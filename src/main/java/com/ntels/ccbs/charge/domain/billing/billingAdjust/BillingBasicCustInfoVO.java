package com.ntels.ccbs.charge.domain.billing.billingAdjust;

public class BillingBasicCustInfoVO {
	private String chkJob;  //
	private String pymAcntId; // 납부계정id
	private String pymAcnt; // 납부계정명
	private String rsdtCrrno; // 주민번호
	private String maskingRsdtCrrno; // 마스킹주민번호
	private String custNm; // 고객명
	private String repNm; // 대표자명
	private String crrNm; // 업체명
	private String busiCndt;
	private String busiTp;
	private String addr; // 기본주소
	private String custTp; //고객타입
	private String custTpNm; // 고객타입명
	private String pymMthdNm; // 납부유형
	private String soId;
	public String getChkJob() {
		return chkJob;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public String getPymAcnt() {
		return pymAcnt;
	}
	public String getRsdtCrrno() {
		return rsdtCrrno;
	}
	public String getMaskingRsdtCrrno() {
		return maskingRsdtCrrno;
	}
	public String getCustNm() {
		return custNm;
	}
	public String getRepNm() {
		return repNm;
	}
	public String getCrrNm() {
		return crrNm;
	}
	public String getBusiCndt() {
		return busiCndt;
	}
	public String getBusiTp() {
		return busiTp;
	}
	public String getAddr() {
		return addr;
	}
	public String getCustTp() {
		return custTp;
	}
	public String getCustTpNm() {
		return custTpNm;
	}
	public String getPymMthdNm() {
		return pymMthdNm;
	}
	public String getSoId() {
		return soId;
	}
	public void setChkJob(String chkJob) {
		this.chkJob = chkJob;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public void setPymAcnt(String pymAcnt) {
		this.pymAcnt = pymAcnt;
	}
	public void setRsdtCrrno(String rsdtCrrno) {
		this.rsdtCrrno = rsdtCrrno;
	}
	public void setMaskingRsdtCrrno(String maskingRsdtCrrno) {
		this.maskingRsdtCrrno = maskingRsdtCrrno;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public void setRepNm(String repNm) {
		this.repNm = repNm;
	}
	public void setCrrNm(String crrNm) {
		this.crrNm = crrNm;
	}
	public void setBusiCndt(String busiCndt) {
		this.busiCndt = busiCndt;
	}
	public void setBusiTp(String busiTp) {
		this.busiTp = busiTp;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setCustTp(String custTp) {
		this.custTp = custTp;
	}
	public void setCustTpNm(String custTpNm) {
		this.custTpNm = custTpNm;
	}
	public void setPymMthdNm(String pymMthdNm) {
		this.pymMthdNm = pymMthdNm;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	@Override
	public String toString() {
		return "BillingBasicCustInfoVO [chkJob=" + chkJob + ", pymAcntId=" + pymAcntId + ", pymAcnt=" + pymAcnt
				+ ", rsdtCrrno=" + rsdtCrrno + ", maskingRsdtCrrno=" + maskingRsdtCrrno + ", custNm=" + custNm
				+ ", repNm=" + repNm + ", crrNm=" + crrNm + ", busiCndt=" + busiCndt + ", busiTp=" + busiTp + ", addr="
				+ addr + ", custTp=" + custTp + ", custTpNm=" + custTpNm + ", pymMthdNm=" + pymMthdNm + ", soId=" + soId
				+ "]";
	}
}
