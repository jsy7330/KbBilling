package com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng;

import java.util.Date;

public class PrepayTransHistVO {

	private String soId;
	/**
	 * 선수 대체 일련번호
	 */
	private String prepayTransSeqNo;
	
	/**
	 * 선수 발생 일련번호
	 */
	private String prepayOccSeqNo;

	/**
	 * TRANS 처리 DTTM
	 */
	private String transProcDttm;

	/**
	 * 선수 REPL 타입 - 공통코드:BL00017
	 */
	private String prepayReplTp;

	/**
	 * TRANS 처리 금액
	 */
	private double transProcAmt;

	/**
	 * 처리 메모
	 */
	private String procMemo;

	/**
	 * 결재 요청자ID
	 */
	private String apprReqrId;

	/**
	 * 결재 요청 DTTM
	 */
	private String apprReqDttm;

	/**
	 * DCSN 처리 상태 - 공통코드:BL00018
	 */
	private String dcsnProcStat;

	/**
	 * 결재자 ID
	 */
	private String apprrId;

	/**
	 * 결재 DTTM
	 */
	private String apprDttm;

	/**
	 * 취소 여부
	 */
	private String cnclYn;

	/**
	 * 취소 DTTM
	 */
	private String cnclDttm;

	/**
	 * 잔액
	 */
	private double balAmt;
	
	/**
	 * ERP 전송여부
	 */
	private String erpSndYn;
	
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

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getPrepayTransSeqNo() {
		return prepayTransSeqNo;
	}

	public void setPrepayTransSeqNo(String prepayTransSeqNo) {
		this.prepayTransSeqNo = prepayTransSeqNo;
	}

	public String getPrepayOccSeqNo() {
		return prepayOccSeqNo;
	}

	public void setPrepayOccSeqNo(String prepayOccSeqNo) {
		this.prepayOccSeqNo = prepayOccSeqNo;
	}

	public String getTransProcDttm() {
		return transProcDttm;
	}

	public void setTransProcDttm(String transProcDttm) {
		this.transProcDttm = transProcDttm;
	}

	public String getPrepayReplTp() {
		return prepayReplTp;
	}

	public void setPrepayReplTp(String prepayReplTp) {
		this.prepayReplTp = prepayReplTp;
	}

	public double getTransProcAmt() {
		return transProcAmt;
	}

	public void setTransProcAmt(double transProcAmt) {
		this.transProcAmt = transProcAmt;
	}

	public String getProcMemo() {
		return procMemo;
	}

	public void setProcMemo(String procMemo) {
		this.procMemo = procMemo;
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

	public String getApprrId() {
		return apprrId;
	}

	public void setApprrId(String apprrId) {
		this.apprrId = apprrId;
	}

	public String getApprDttm() {
		return apprDttm;
	}

	public void setApprDttm(String apprDttm) {
		this.apprDttm = apprDttm;
	}

	public String getCnclYn() {
		return cnclYn;
	}

	public void setCnclYn(String cnclYn) {
		this.cnclYn = cnclYn;
	}

	public String getCnclDttm() {
		return cnclDttm;
	}

	public void setCnclDttm(String cnclDttm) {
		this.cnclDttm = cnclDttm;
	}

	public double getBalAmt() {
		return balAmt;
	}

	public void setBalAmt(double balAmt) {
		this.balAmt = balAmt;
	}

	public String getErpSndYn() {
		return erpSndYn;
	}

	public void setErpSndYn(String erpSndYn) {
		this.erpSndYn = erpSndYn;
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
