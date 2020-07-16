package com.ntels.ccbs.charge.domain.batchprocmng.billupdmng;

import java.util.Date;

public class MthCtrtUpymStusVO {
	private String soId;
	private String billYymm;
	private String ctrtId;
	private String pymAcntId;
	private String frstUpymMth;
	private String lastUpymMth;
	private Long totUpymCnt;
	private Long totUpymAmt;
	private String lastBillYymm;
	private Long thsMthBillAmt;
	private Long thsMthRcptAmt;
	private String lastRcptDt;
	private String mngCnterBrOffcId;
	private String mngBrBrOffcId;
	private String inptMenuId;
	private String regrId;
	private Date regDate;
	private String chgrId;
	private Date chgDate;
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getFrstUpymMth() {
		return frstUpymMth;
	}
	public void setFrstUpymMth(String frstUpymMth) {
		this.frstUpymMth = frstUpymMth;
	}
	public String getLastUpymMth() {
		return lastUpymMth;
	}
	public void setLastUpymMth(String lastUpymMth) {
		this.lastUpymMth = lastUpymMth;
	}
	public Long getTotUpymCnt() {
		return totUpymCnt;
	}
	public void setTotUpymCnt(Long totUpymCnt) {
		this.totUpymCnt = totUpymCnt;
	}
	public Long getTotUpymAmt() {
		return totUpymAmt;
	}
	public void setTotUpymAmt(Long totUpymAmt) {
		this.totUpymAmt = totUpymAmt;
	}
	public String getLastBillYymm() {
		return lastBillYymm;
	}
	public void setLastBillYymm(String lastBillYymm) {
		this.lastBillYymm = lastBillYymm;
	}
	public Long getThsMthBillAmt() {
		return thsMthBillAmt;
	}
	public void setThsMthBillAmt(Long thsMthBillAmt) {
		this.thsMthBillAmt = thsMthBillAmt;
	}
	public Long getThsMthRcptAmt() {
		return thsMthRcptAmt;
	}
	public void setThsMthRcptAmt(Long thsMthRcptAmt) {
		this.thsMthRcptAmt = thsMthRcptAmt;
	}
	public String getLastRcptDt() {
		return lastRcptDt;
	}
	public void setLastRcptDt(String lastRcptDt) {
		this.lastRcptDt = lastRcptDt;
	}
	public String getMngCnterBrOffcId() {
		return mngCnterBrOffcId;
	}
	public void setMngCnterBrOffcId(String mngCnterBrOffcId) {
		this.mngCnterBrOffcId = mngCnterBrOffcId;
	}
	public String getMngBrBrOffcId() {
		return mngBrBrOffcId;
	}
	public void setMngBrBrOffcId(String mngBrBrOffcId) {
		this.mngBrBrOffcId = mngBrBrOffcId;
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
	@Override
	public String toString() {
		return "NBlivMthCtrtUpymStusVO [soId=" + soId + ", billYymm=" + billYymm + ", ctrtId=" + ctrtId + ", pymAcntId=" + pymAcntId + ", frstUpymMth=" + frstUpymMth
				+ ", lastUpymMth=" + lastUpymMth + ", totUpymCnt=" + totUpymCnt + ", totUpymAmt=" + totUpymAmt + ", lastBillYymm=" + lastBillYymm + ", thsMthBillAmt="
				+ thsMthBillAmt + ", thsMthRcptAmt=" + thsMthRcptAmt + ", lastRcptDt=" + lastRcptDt + ", mngCnterBrOffcId=" + mngCnterBrOffcId + ", mngBrBrOffcId=" + mngBrBrOffcId
				+ ", inptMenuId=" + inptMenuId + ", regrId=" + regrId + ", regDate=" + regDate + ", chgrId=" + chgrId + ", chgDate=" + chgDate + "]";
	}
}
