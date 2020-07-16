package com.ntels.ccbs.charge.domain.batchprocmng.refundmng;

import java.util.Date;

public class RefundApplVO {

	/**
	 * 환불 일련 번호
	 */
	private String rfndSeqNo;

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;
	
	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 고객 ID
	 */
	private String custId;
	
	/**
	 * 계약 ID
	 */
	private String ctrdId;
	
	/**
	 * 접수 ID
	 */
	private String rcptId;
	
	/**
	 * 신청 DTTM - YYYYMMDDHH24MISS
	 */
	private String applDttm;
	
	/**
	 * 신청자명
	 */
	private String appntNm;

	/**
	 * PYR 관계 - 공통코드:BL00035
	 */
	private String pyrRel;
	
	/**
	 * 신청자 전화번호
	 */
	private String appntTelNo;
	
	/**
	 * 신청자 이메일
	 */
	private String appntEmail;

	/**
	 * 추가정보
	 */
	private String addInfo;

	/**
	 * 은행코드 - 공통코드:BL00008
	 */
	private String bnkCd;

	/**
	 * 계좌번호
	 */
	private String acntNo;

	/**
	 * 계좌주명
	 */
	private String acntOwnerNm;
	
	/**
	 * 계좌주 전화번호
	 */
	private String acntOwnerTelNo;
	
	/**
	 * 환불신청금액
	 */
	private double rfndApplAmt;
	
	/**
	 * 환불사유
	 */
	private String rfndResn;
	
	/**
	 * 환불발생 금액구분 - 공통코드:BL00037
	 */
	private String rfndOccAmtCl;
	
	/**
	 * 환불 발생 대상 일련번호
	 */
	private String rfndOccTgtSeqNo;
	
	/**
	 * 환불 발생 대상 REPL 일련번호
	 */
	private String rfndOccTgtReplSeqNo;
	
	/**
	 * 환불 예정일
	 */
	private String rfndPlnDt;
	
	/**
	 * 수납 PSN ID
	 */
	private String rcptPsnId;
	
	/**
	 * 수납 DTTM - YYYYMMDDHH24MISS
	 */
	private String rcptDttm;
	
	/**
	 * 결재 요청자 ID
	 */
	private String apprReqrId;
	
	/**
	 * 결재 요청 DTTM - YYYYMMDDHH24MISS
	 */
	private String apprReqDttm;
	
	/**
	 * DCSN 처리상태 - 공통코드:BL00018
	 */
	private String dcsnProcStat;
	
	/**
	 * 결재자 ID
	 */
	private String apprId;
	
	/**
	 * 결재 DTTM - YYYYMMDDHH24MISS
	 */
	private String apprDttm;
	
	/**
	 * 환불 완료 여부
	 */
	private String rfndCmplYn;
	
	/**
	 * 환불 완료일자
	 */
	private String rfndCmplDt;
	
	/**
	 * TRANS 적용일자
	 */
	private String transAplyDt;
	
	/**
	 * 처리 PSN ID
	 */
	private String procPsnId;
	
	/**
	 * 처리 사유
	 */
	private String procResn;
	
	/**
	 * TRANS 결재 ID
	 */
	private String transApprId;
	
	/**
	 * 입력메뉴ID
	 */
	private String inptMenuId;
	
	/**
	 * 등록자ID
	 */
	private String regrId;

	/**
	 * 등록일
	 */
	private Date regDate;

	public String getRfndSeqNo() {
		return rfndSeqNo;
	}

	public void setRfndSeqNo(String rfndSeqNo) {
		this.rfndSeqNo = rfndSeqNo;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCtrdId() {
		return ctrdId;
	}

	public void setCtrdId(String ctrdId) {
		this.ctrdId = ctrdId;
	}

	public String getRcptId() {
		return rcptId;
	}

	public void setRcptId(String rcptId) {
		this.rcptId = rcptId;
	}

	public String getApplDttm() {
		return applDttm;
	}

	public void setApplDttm(String applDttm) {
		this.applDttm = applDttm;
	}

	public String getAppntNm() {
		return appntNm;
	}

	public void setAppntNm(String appntNm) {
		this.appntNm = appntNm;
	}

	public String getPyrRel() {
		return pyrRel;
	}

	public void setPyrRel(String pyrRel) {
		this.pyrRel = pyrRel;
	}

	public String getAppntTelNo() {
		return appntTelNo;
	}

	public void setAppntTelNo(String appntTelNo) {
		this.appntTelNo = appntTelNo;
	}

	public String getAppntEmail() {
		return appntEmail;
	}

	public void setAppntEmail(String appntEmail) {
		this.appntEmail = appntEmail;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

	public String getBnkCd() {
		return bnkCd;
	}

	public void setBnkCd(String bnkCd) {
		this.bnkCd = bnkCd;
	}

	public String getAcntNo() {
		return acntNo;
	}

	public void setAcntNo(String acntNo) {
		this.acntNo = acntNo;
	}

	public String getAcntOwnerNm() {
		return acntOwnerNm;
	}

	public void setAcntOwnerNm(String acntOwnerNm) {
		this.acntOwnerNm = acntOwnerNm;
	}

	public String getAcntOwnerTelNo() {
		return acntOwnerTelNo;
	}

	public void setAcntOwnerTelNo(String acntOwnerTelNo) {
		this.acntOwnerTelNo = acntOwnerTelNo;
	}

	public double getRfndApplAmt() {
		return rfndApplAmt;
	}

	public void setRfndApplAmt(double rfndApplAmt) {
		this.rfndApplAmt = rfndApplAmt;
	}

	public String getRfndResn() {
		return rfndResn;
	}

	public void setRfndResn(String rfndResn) {
		this.rfndResn = rfndResn;
	}

	public String getRfndOccAmtCl() {
		return rfndOccAmtCl;
	}

	public void setRfndOccAmtCl(String rfndOccAmtCl) {
		this.rfndOccAmtCl = rfndOccAmtCl;
	}

	public String getRfndOccTgtSeqNo() {
		return rfndOccTgtSeqNo;
	}

	public void setRfndOccTgtSeqNo(String rfndOccTgtSeqNo) {
		this.rfndOccTgtSeqNo = rfndOccTgtSeqNo;
	}

	public String getRfndOccTgtReplSeqNo() {
		return rfndOccTgtReplSeqNo;
	}

	public void setRfndOccTgtReplSeqNo(String rfndOccTgtReplSeqNo) {
		this.rfndOccTgtReplSeqNo = rfndOccTgtReplSeqNo;
	}

	public String getRfndPlnDt() {
		return rfndPlnDt;
	}

	public void setRfndPlnDt(String rfndPlnDt) {
		this.rfndPlnDt = rfndPlnDt;
	}

	public String getRcptPsnId() {
		return rcptPsnId;
	}

	public void setRcptPsnId(String rcptPsnId) {
		this.rcptPsnId = rcptPsnId;
	}

	public String getRcptDttm() {
		return rcptDttm;
	}

	public void setRcptDttm(String rcptDttm) {
		this.rcptDttm = rcptDttm;
	}

	public String getApprReqrId() {
		return apprReqrId;
	}

	public void setApprReqrId(String apprReqrId) {
		this.apprReqrId = apprReqrId;
	}

	public String getApprReqDttm() {
		return apprReqDttm;
	}

	public void setApprReqDttm(String apprReqDttm) {
		this.apprReqDttm = apprReqDttm;
	}

	public String getDcsnProcStat() {
		return dcsnProcStat;
	}

	public void setDcsnProcStat(String dcsnProcStat) {
		this.dcsnProcStat = dcsnProcStat;
	}

	public String getApprId() {
		return apprId;
	}

	public void setApprId(String apprId) {
		this.apprId = apprId;
	}

	public String getApprDttm() {
		return apprDttm;
	}

	public void setApprDttm(String apprDttm) {
		this.apprDttm = apprDttm;
	}

	public String getRfndCmplYn() {
		return rfndCmplYn;
	}

	public void setRfndCmplYn(String rfndCmplYn) {
		this.rfndCmplYn = rfndCmplYn;
	}

	public String getRfndCmplDt() {
		return rfndCmplDt;
	}

	public void setRfndCmplDt(String rfndCmplDt) {
		this.rfndCmplDt = rfndCmplDt;
	}

	public String getTransAplyDt() {
		return transAplyDt;
	}

	public void setTransAplyDt(String transAplyDt) {
		this.transAplyDt = transAplyDt;
	}

	public String getProcPsnId() {
		return procPsnId;
	}

	public void setProcPsnId(String procPsnId) {
		this.procPsnId = procPsnId;
	}

	public String getProcResn() {
		return procResn;
	}

	public void setProcResn(String procResn) {
		this.procResn = procResn;
	}

	public String getTransApprId() {
		return transApprId;
	}

	public void setTransApprId(String transApprId) {
		this.transApprId = transApprId;
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

}
