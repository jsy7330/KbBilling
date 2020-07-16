package com.ntels.ccbs.charge.domain.batchprocmng.billupdmng;

import java.sql.Timestamp;

public class BillMastVO {

	private String soId;
	private String billSeqNo;
	private String billYymm;
	private String pymCycl;
	private String billCycl;
	private String billDt;
	private String pymAcntId;
	private Long adjPreBillAmt;
	private Long totBillAmt;
	private Long totAdjAmt;
	private Long totRcptAmt;
	private Long totUpymAmt;
	private String pymMthd;
	private String lastRcptDt;
	private String fullPayYn;
	private Long wonBillAmt;
	private String billCrtYn;
	private String taxbilSeqNo;
	private String custGiroNo;
	private String elctrnGiroNo;
	private String giroSndYn;
	private String giroSndDt;
	private String giroSndErrCd;
	private String crncyCd;
	private String taxbilIssDt;
	private String selngProcYn;
	private String selngYymm;
	private String inptMenuId;
	private String regrId;
	private Timestamp regDate;
	private String chgrId;
	private Timestamp chgDate;
	private String rmrk1;
	private String rmrk2;
	private String rmrk3;
	private String rmrk4;
	private Double adjPrvBillAmt;

	public Double getAdjPrvBillAmt() {
		return adjPrvBillAmt;
	}

	public void setAdjPrvBillAmt(Double adjPrvBillAmt) {
		this.adjPrvBillAmt = adjPrvBillAmt;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getBillSeqNo() {
		return billSeqNo;
	}

	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}

	public String getBillYymm() {
		return billYymm;
	}

	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}

	public String getPymCycl() {
		return pymCycl;
	}

	public void setPymCycl(String pymCycl) {
		this.pymCycl = pymCycl;
	}

	public String getBillCycl() {
		return billCycl;
	}

	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}

	public String getBillDt() {
		return billDt;
	}

	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public Long getAdjPreBillAmt() {
		return adjPreBillAmt;
	}

	public void setAdjPreBillAmt(Long adjPreBillAmt) {
		this.adjPreBillAmt = adjPreBillAmt;
	}

	public Long getTotBillAmt() {
		return totBillAmt;
	}

	public void setTotBillAmt(Long totBillAmt) {
		this.totBillAmt = totBillAmt;
	}

	public Long getTotAdjAmt() {
		return totAdjAmt;
	}

	public void setTotAdjAmt(Long totAdjAmt) {
		this.totAdjAmt = totAdjAmt;
	}

	public Long getTotRcptAmt() {
		return totRcptAmt;
	}

	public void setTotRcptAmt(Long totRcptAmt) {
		this.totRcptAmt = totRcptAmt;
	}

	public Long getTotUpymAmt() {
		return totUpymAmt;
	}

	public void setTotUpymAmt(Long totUpymAmt) {
		this.totUpymAmt = totUpymAmt;
	}

	public String getPymMthd() {
		return pymMthd;
	}

	public void setPymMthd(String pymMthd) {
		this.pymMthd = pymMthd;
	}

	public String getLastRcptDt() {
		return lastRcptDt;
	}

	public void setLastRcptDt(String lastRcptDt) {
		this.lastRcptDt = lastRcptDt;
	}

	public String getFullPayYn() {
		return fullPayYn;
	}

	public void setFullPayYn(String fullPayYn) {
		this.fullPayYn = fullPayYn;
	}

	public Long getWonBillAmt() {
		return wonBillAmt;
	}

	public void setWonBillAmt(Long wonBillAmt) {
		this.wonBillAmt = wonBillAmt;
	}

	public String getBillCrtYn() {
		return billCrtYn;
	}

	public void setBillCrtYn(String billCrtYn) {
		this.billCrtYn = billCrtYn;
	}

	public String getTaxbilSeqNo() {
		return taxbilSeqNo;
	}

	public void setTaxbilSeqNo(String taxbilSeqNo) {
		this.taxbilSeqNo = taxbilSeqNo;
	}

	public String getCustGiroNo() {
		return custGiroNo;
	}

	public void setCustGiroNo(String custGiroNo) {
		this.custGiroNo = custGiroNo;
	}

	public String getElctrnGiroNo() {
		return elctrnGiroNo;
	}

	public void setElctrnGiroNo(String elctrnGiroNo) {
		this.elctrnGiroNo = elctrnGiroNo;
	}

	public String getGiroSndYn() {
		return giroSndYn;
	}

	public void setGiroSndYn(String giroSndYn) {
		this.giroSndYn = giroSndYn;
	}

	public String getGiroSndDt() {
		return giroSndDt;
	}

	public void setGiroSndDt(String giroSndDt) {
		this.giroSndDt = giroSndDt;
	}

	public String getGiroSndErrCd() {
		return giroSndErrCd;
	}

	public void setGiroSndErrCd(String giroSndErrCd) {
		this.giroSndErrCd = giroSndErrCd;
	}

	public String getCrncyCd() {
		return crncyCd;
	}

	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}

	public String getTaxbilIssDt() {
		return taxbilIssDt;
	}

	public void setTaxbilIssDt(String taxbilIssDt) {
		this.taxbilIssDt = taxbilIssDt;
	}

	public String getSelngProcYn() {
		return selngProcYn;
	}

	public void setSelngProcYn(String selngProcYn) {
		this.selngProcYn = selngProcYn;
	}

	public String getSelngYymm() {
		return selngYymm;
	}

	public void setSelngYymm(String selngYymm) {
		this.selngYymm = selngYymm;
	}

	public String getInptMenuId() {
		return inptMenuId;
	}

	public void setInptMenuId(String inptMenuId) {
		this.inptMenuId = inptMenuId;
	}

	public String getRegrId() {
		return regrId;
	}

	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getChgrId() {
		return chgrId;
	}

	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

	public Timestamp getChgDate() {
		return chgDate;
	}

	public void setChgDate(Timestamp chgDate) {
		this.chgDate = chgDate;
	}

	public String getRmrk1() {
		return rmrk1;
	}

	public void setRmrk1(String rmrk1) {
		this.rmrk1 = rmrk1;
	}

	public String getRmrk2() {
		return rmrk2;
	}

	public void setRmrk2(String rmrk2) {
		this.rmrk2 = rmrk2;
	}

	public String getRmrk3() {
		return rmrk3;
	}

	public void setRmrk3(String rmrk3) {
		this.rmrk3 = rmrk3;
	}

	public String getRmrk4() {
		return rmrk4;
	}

	public void setRmrk4(String rmrk4) {
		this.rmrk4 = rmrk4;
	}

	@Override
	public String toString() {
		return "NBlivBillMastVO [soId=" + soId + ", billSeqNo=" + billSeqNo + ", billYymm=" + billYymm + ", pymCycl=" + pymCycl + ", billCycl=" + billCycl + ", billDt=" + billDt
				+ ", pymAcntId=" + pymAcntId + ", adjPreBillAmt=" + adjPreBillAmt + ", totBillAmt=" + totBillAmt + ", totAdjAmt=" + totAdjAmt + ", totRcptAmt=" + totRcptAmt
				+ ", totUpymAmt=" + totUpymAmt + ", pymMthd=" + pymMthd + ", lastRcptDt=" + lastRcptDt + ", fullPayYn=" + fullPayYn + ", wonBillAmt=" + wonBillAmt + ", billCrtYn="
				+ billCrtYn + ", taxbilSeqNo=" + taxbilSeqNo + ", custGiroNo=" + custGiroNo + ", elctrnGiroNo=" + elctrnGiroNo + ", giroSndYn=" + giroSndYn + ", giroSndDt="
				+ giroSndDt + ", giroSndErrCd=" + giroSndErrCd + ", crncyCd=" + crncyCd + ", taxbilIssDt=" + taxbilIssDt + ", selngProcYn=" + selngProcYn + ", selngYymm="
				+ selngYymm + ", inptMenuId=" + inptMenuId + ", regrId=" + regrId + ", regDate=" + regDate + ", chgrId=" + chgrId + ", chgDate=" + chgDate + ", rmrk1=" + rmrk1
				+ ", rmrk2=" + rmrk2 + ", rmrk3=" + rmrk3 + ", rmrk4=" + rmrk4 + "]";
	}
}
