package com.ntels.ccbs.charge.domain.batchprocmng.depositcnclmng;

import java.util.Date;

public class DepositCancelVO {

	/**
	 * 입금 일련 번호
	 */
	private String dpstSeqNo;
	
	/**
	 * SO_ID or RO_ID
	 */
	private String soId;
	
	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) +
	 * 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;
	
	/**
	 * 계약ID
	 */
	private String ctrtId;

	/**
	 * 취소일자
	 */
	private String cnclDt;
	
	/**
	 * 고객출금일자
	 */
	private String dpstDt;
	
	/**
	 * 통장입금일자
	 */
	private String trnsDt;
	
	/**
	 * 입금처리일자
	 */
	private String dpstProcDt;

	/**
	 * 고객ID
	 */
	private String custId;

	/**
	 * 입금구분 - 공통코드:BL00002
	 */
	private String dpstCl;

	/**
	 * 입금타입 - 공통코드:BL00006
	 */
	private String dpstTp;	

	/**
	 * 입금구분에 따라 해당 Table에서의 참조 일련번호
	 */
	private String dpstTpSeqNo;

	/**
	 * 결제 법인 타입 - 공통코드:BL00007
	 */
	private String payCorpTp;

	/**
	 * 고객이 납부한 금융기관 코드 - 자동이체(EDI), 가상계좌, 지로인 경우는 은행코드, 카드인 경우 카드사 코드
	 */
	private String payCorpCd;

	/**
	 * 고객이 납부한 계좌 및 카드번호 - 자동이체(EDI) : 계쫘번호, 지로 : 지로번호, 가상계좌 : 가상계좌번호, 카드 : 카드번호
	 */
	private String acntCardNo;

	/**
	 * 입금금액
	 */
	private double dpstAmt;

	/**
	 * 입금은행 계좌코드
	 */
	private String dpstBnkAcntCd;
	
	/**
	 * 수수료
	 */
	private double feeAmt;

	/**
	 * 수납처리여부
	 */
	private String payProcYn;

	/**
	 * 수납처리일자
	 */
	private String payProcDt;

	/**
	 * 선수대상여부
	 */
	private String prepayTgtYn;
	
	/**
	 * 불명대상여부
	 */
	private String ambgTgtYn;

	/**
	 * 취소사원ID
	 */
	private String cnclEmpId;

	/**
	 * 취소사유
	 */
	private String cnclResn;
	
	/**
	 * 현금영수증 발행여부
	 */
	private String cashRcptIssYn;
	
	/**
	 * 현금영수증 발행번호
	 */
	private String cashRcptIssNo;
	
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

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
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

	public String getBillSeqNo() {
		return billSeqNo;
	}

	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}

	public String getCtrtId() {
		return ctrtId;
	}

	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}

	public String getCnclDt() {
		return cnclDt;
	}

	public void setCnclDt(String cnclDt) {
		this.cnclDt = cnclDt;
	}

	public String getDpstDt() {
		return dpstDt;
	}

	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt;
	}

	public String getTrnsDt() {
		return trnsDt;
	}

	public void setTrnsDt(String trnsDt) {
		this.trnsDt = trnsDt;
	}

	public String getDpstProcDt() {
		return dpstProcDt;
	}

	public void setDpstProcDt(String dpstProcDt) {
		this.dpstProcDt = dpstProcDt;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getDpstCl() {
		return dpstCl;
	}

	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}

	public String getDpstTp() {
		return dpstTp;
	}

	public void setDpstTp(String dpstTp) {
		this.dpstTp = dpstTp;
	}

	public String getDpstTpSeqNo() {
		return dpstTpSeqNo;
	}

	public void setDpstTpSeqNo(String dpstTpSeqNo) {
		this.dpstTpSeqNo = dpstTpSeqNo;
	}

	public String getPayCorpTp() {
		return payCorpTp;
	}

	public void setPayCorpTp(String payCorpTp) {
		this.payCorpTp = payCorpTp;
	}

	public String getPayCorpCd() {
		return payCorpCd;
	}

	public void setPayCorpCd(String payCorpCd) {
		this.payCorpCd = payCorpCd;
	}

	public String getAcntCardNo() {
		return acntCardNo;
	}

	public void setAcntCardNo(String acntCardNo) {
		this.acntCardNo = acntCardNo;
	}

	public double getDpstAmt() {
		return dpstAmt;
	}

	public void setDpstAmt(double dpstAmt) {
		this.dpstAmt = dpstAmt;
	}

	public String getDpstBnkAcntCd() {
		return dpstBnkAcntCd;
	}

	public void setDpstBnkAcntCd(String dpstBnkAcntCd) {
		this.dpstBnkAcntCd = dpstBnkAcntCd;
	}

	public double getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getPayProcYn() {
		return payProcYn;
	}

	public void setPayProcYn(String payProcYn) {
		this.payProcYn = payProcYn;
	}

	public String getPayProcDt() {
		return payProcDt;
	}

	public void setPayProcDt(String payProcDt) {
		this.payProcDt = payProcDt;
	}

	public String getPrepayTgtYn() {
		return prepayTgtYn;
	}

	public void setPrepayTgtYn(String prepayTgtYn) {
		this.prepayTgtYn = prepayTgtYn;
	}

	public String getAmbgTgtYn() {
		return ambgTgtYn;
	}

	public void setAmbgTgtYn(String ambgTgtYn) {
		this.ambgTgtYn = ambgTgtYn;
	}

	public String getCnclEmpId() {
		return cnclEmpId;
	}

	public void setCnclEmpId(String cnclEmpId) {
		this.cnclEmpId = cnclEmpId;
	}

	public String getCnclResn() {
		return cnclResn;
	}

	public void setCnclResn(String cnclResn) {
		this.cnclResn = cnclResn;
	}

	public String getCashRcptIssYn() {
		return cashRcptIssYn;
	}

	public void setCashRcptIssYn(String cashRcptIssYn) {
		this.cashRcptIssYn = cashRcptIssYn;
	}

	public String getCashRcptIssNo() {
		return cashRcptIssNo;
	}

	public void setCashRcptIssNo(String cashRcptIssNo) {
		this.cashRcptIssNo = cashRcptIssNo;
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
