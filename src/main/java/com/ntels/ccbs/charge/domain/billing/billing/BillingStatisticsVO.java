package com.ntels.ccbs.charge.domain.billing.billing;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("BillingStatisticsVO")
public class BillingStatisticsVO extends PagingValue {

	private String orgId;
	private String orgNm;
	private String lngTyp;
	private String sidx;
	private String sord;
	private String soId;
	private String soNm;
	
	private String chkJob;
	private String pymAcntId;
	private String billYymm;
	private String billDt;
	private String adjPrvBillAmt;
	private String adjAmt;
	private String billAmt;
	private String rcptAmt;
	private String unpayAmt;
	private String fullPayYn;
	private String smlAmtYn;
	private String adjApplConts;
	private String paymthd;
	private String billSeqNo;
	private String billTpNm;
	private String billExptYn;
	private String chgrNm;
	private String chgDt;
	private String smry;
	private String custId;
	private String ctrtId;
	private String prodCd;
	private String prodGrp;
	private String prodNm;
	private String custNm;
	private String svcTelNo;
	private String useYymm;
	private String svcCd;
	private String chrgItmCd;
	private String usgCnt;
	private String usgAmt;
	private String payDueDt;
	

	private String svcNm;
	private String chrgItmNm;
	private String useDt;
	
	private String billCycl;
	private String prodCmpsId;
	private String svcCmpsId;
	
	private String searchStartMonth;
	private String searchEndMonth;

	/* TBLPY_TRANS_APPL (신청 테이블) */
	private String transApplNo; // 대체 신청 번호
	private String transApplCl; // 대체 신청 구분
	private String transApplTgtNo; // 대체 신청 대상 번호
	private String applDt; // 신청 일자
	private String appntId; // 신청자 ID
	private double transApplAvlamt; // 대체 신청 가능액
	private double transApplAmt; // 대체 신청 금액
	private String appntNm; // 신청자 명
	private String appntCellPhnNo; // 신청자 휴대폰 번호
	private String applResn; // 신청 사유
	private String procDt; // 처리 일자
	private String procId; // 처리 ID
	private String dcsnProcStat; // DCSN 처리 상태
	private String attchFileSeq; // 첨부 파일 순번
	private String inptMenuId; // 입력 메뉴ID
	private String regrId; // 등록자ID
	private Date regDate; // 등록일시
	private String chgrId; // 수정자ID
	private Date chgDate; // 수정일시
	
	private String transTp;
	
	
	public String getTransTp() {
		return transTp;
	}
	public void setTransTp(String transTp) {
		this.transTp = transTp;
	}
	public double getTransApplAvlamt() {
		return transApplAvlamt;
	}
	public void setTransApplAvlamt(double transApplAvlamt) {
		this.transApplAvlamt = transApplAvlamt;
	}
	public double getTransApplAmt() {
		return transApplAmt;
	}
	public void setTransApplAmt(double transApplAmt) {
		this.transApplAmt = transApplAmt;
	}
	public String getAppntNm() {
		return appntNm;
	}
	public void setAppntNm(String appntNm) {
		this.appntNm = appntNm;
	}
	public String getAppntCellPhnNo() {
		return appntCellPhnNo;
	}
	public void setAppntCellPhnNo(String appntCellPhnNo) {
		this.appntCellPhnNo = appntCellPhnNo;
	}
	public String getApplResn() {
		return applResn;
	}
	public void setApplResn(String applResn) {
		this.applResn = applResn;
	}
	public String getProcDt() {
		return procDt;
	}
	public void setProcDt(String procDt) {
		this.procDt = procDt;
	}
	public String getProcId() {
		return procId;
	}
	public void setProcId(String procId) {
		this.procId = procId;
	}
	public String getAttchFileSeq() {
		return attchFileSeq;
	}
	public void setAttchFileSeq(String attchFileSeq) {
		this.attchFileSeq = attchFileSeq;
	}
	public String getInptMenuId() {
		return inptMenuId;
	}
	public void setInptMenuId(String inptMenuId) {
		this.inptMenuId = inptMenuId;
	}
	public String getApplDt() {
		return applDt;
	}
	public void setApplDt(String applDt) {
		this.applDt = applDt;
	}
	public String getTransApplTgtNo() {
		return transApplTgtNo;
	}
	public void setTransApplTgtNo(String transApplTgtNo) {
		this.transApplTgtNo = transApplTgtNo;
	}
	public String getTransApplCl() {
		return transApplCl;
	}
	public void setTransApplCl(String transApplCl) {
		this.transApplCl = transApplCl;
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
	public String getAppntId() {
		return appntId;
	}
	public void setAppntId(String appntId) {
		this.appntId = appntId;
	}
	public String getTransApplNo() {
		return transApplNo;
	}
	public void setTransApplNo(String transApplNo) {
		this.transApplNo = transApplNo;
	}
	public String getDcsnProcStat() {
		return dcsnProcStat;
	}
	public void setDcsnProcStat(String dcsnProcStat) {
		this.dcsnProcStat = dcsnProcStat;
	}
	public String getSearchStartMonth() {
		return searchStartMonth;
	}
	public void setSearchStartMonth(String searchStartMonth) {
		this.searchStartMonth = searchStartMonth;
	}
	public String getSearchEndMonth() {
		return searchEndMonth;
	}
	public void setSearchEndMonth(String searchEndMonth) {
		this.searchEndMonth = searchEndMonth;
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
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
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
	public String getChkJob() {
		return chkJob;
	}
	public void setChkJob(String chkJob) {
		this.chkJob = chkJob;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getBillDt() {
		return billDt;
	}
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}
	public String getAdjPrvBillAmt() {
		return adjPrvBillAmt;
	}
	public void setAdjPrvBillAmt(String adjPrvBillAmt) {
		this.adjPrvBillAmt = adjPrvBillAmt;
	}
	public String getAdjAmt() {
		return adjAmt;
	}
	public void setAdjAmt(String adjAmt) {
		this.adjAmt = adjAmt;
	}
	public String getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}
	public String getRcptAmt() {
		return rcptAmt;
	}
	public void setRcptAmt(String rcptAmt) {
		this.rcptAmt = rcptAmt;
	}
	public String getUnpayAmt() {
		return unpayAmt;
	}
	public void setUnpayAmt(String unpayAmt) {
		this.unpayAmt = unpayAmt;
	}
	public String getFullPayYn() {
		return fullPayYn;
	}
	public void setFullPayYn(String fullPayYn) {
		this.fullPayYn = fullPayYn;
	}
	public String getSmlAmtYn() {
		return smlAmtYn;
	}
	public void setSmlAmtYn(String smlAmtYn) {
		this.smlAmtYn = smlAmtYn;
	}
	public String getAdjApplConts() {
		return adjApplConts;
	}
	public void setAdjApplConts(String adjApplConts) {
		this.adjApplConts = adjApplConts;
	}
	public String getPaymthd() {
		return paymthd;
	}
	public void setPaymthd(String paymthd) {
		this.paymthd = paymthd;
	}
	public String getBillSeqNo() {
		return billSeqNo;
	}
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}
	public String getBillTpNm() {
		return billTpNm;
	}
	public void setBillTpNm(String billTpNm) {
		this.billTpNm = billTpNm;
	}
	public String getBillExptYn() {
		return billExptYn;
	}
	public void setBillExptYn(String billExptYn) {
		this.billExptYn = billExptYn;
	}
	public String getChgrNm() {
		return chgrNm;
	}
	public void setChgrNm(String chgrNm) {
		this.chgrNm = chgrNm;
	}
	public String getChgDt() {
		return chgDt;
	}
	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}
	public String getSmry() {
		return smry;
	}
	public void setSmry(String smry) {
		this.smry = smry;
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
	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public String getProdGrp() {
		return prodGrp;
	}
	public void setProdGrp(String prodGrp) {
		this.prodGrp = prodGrp;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getSvcTelNo() {
		return svcTelNo;
	}
	public void setSvcTelNo(String svcTelNo) {
		this.svcTelNo = svcTelNo;
	}
	public String getUseYymm() {
		return useYymm;
	}
	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}
	public String getSvcCd() {
		return svcCd;
	}
	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}
	public String getChrgItmCd() {
		return chrgItmCd;
	}
	public void setChrgItmCd(String chrgItmCd) {
		this.chrgItmCd = chrgItmCd;
	}
	public String getUsgCnt() {
		return usgCnt;
	}
	public void setUsgCnt(String usgCnt) {
		this.usgCnt = usgCnt;
	}
	public String getUsgAmt() {
		return usgAmt;
	}
	public void setUsgAmt(String usgAmt) {
		this.usgAmt = usgAmt;
	}
	public String getPayDueDt() {
		return payDueDt;
	}
	public void setPayDueDt(String payDueDt) {
		this.payDueDt = payDueDt;
	}
	public String getSvcNm() {
		return svcNm;
	}
	public void setSvcNm(String svcNm) {
		this.svcNm = svcNm;
	}
	public String getChrgItmNm() {
		return chrgItmNm;
	}
	public void setChrgItmNm(String chrgItmNm) {
		this.chrgItmNm = chrgItmNm;
	}
	public String getUseDt() {
		return useDt;
	}
	public void setUseDt(String useDt) {
		this.useDt = useDt;
	}
	public String getBillCycl() {
		return billCycl;
	}
	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}
	public String getProdCmpsId() {
		return prodCmpsId;
	}
	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}
	public String getSvcCmpsId() {
		return svcCmpsId;
	}
	public void setSvcCmpsId(String svcCmpsId) {
		this.svcCmpsId = svcCmpsId;
	}	
	
}
