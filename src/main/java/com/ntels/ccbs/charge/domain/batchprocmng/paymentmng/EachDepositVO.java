package com.ntels.ccbs.charge.domain.batchprocmng.paymentmng;

import java.util.Date;

public class EachDepositVO {

	/**
	 * 개별입금번호
	 */
	private String eachDpstSeq;
	
	/**
	 * 입금번호
	 */
	private String dpstSeqNo;
	
	/**
	 * 입금 처리 센터 코드
	 */
	private String dpstProcCnterCd;
	
	/**
	 * 입금 처리 센터 통장번호
	 */
	private String dpstProcCnterBnkbno;
	
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
	 * 입금구분 - 공통코드:BL00002
	 */
	private String dpstCl;
	
	/**
	 * 현금입금구분 - 공통코드:BL00003
	 */
	private String cashDpstCl;
	
	/**
	 * 고객출금일자
	 */
	private String dpstDt;
	
	/**
	 * 통장입금일자
	 */
//	private String trnsDt;
	
	/**
	 * 통장입금일자
	 */
	private String transDt;
	
	/**
	 * 입금처리일자
	 */
	private String dpstProcDt;
	
	/**
	 * 입금금액
	 */
	private double dpstAmt;
	
	/**
	 * 입금수수료타입 - 공통코드:BL00004
	 */
	private String dpstFeeTp;
	
	/**
	 * 수수료
	 */
	private double feeAmt;
	
	/**
	 * 고객ID
	 */
	private String custId;
	
	/**
	 * 입금은행 계좌코드
	 */
	private String dpstBnkAcntCd;
	
	/**
	 * 수금사원ID
	 */
	private String rcptEmpId;
	
	/**
	 * 내용
	 */
	private String smry;
	
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

	public String getEachDpstSeq() {
		return eachDpstSeq;
	}

	public void setEachDpstSeq(String eachDpstSeq) {
		this.eachDpstSeq = eachDpstSeq;
	}

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}

	public String getDpstProcCnterCd() {
		return dpstProcCnterCd;
	}

	public void setDpstProcCnterCd(String dpstProcCnterCd) {
		this.dpstProcCnterCd = dpstProcCnterCd;
	}

	public String getDpstProcCnterBnkbno() {
		return dpstProcCnterBnkbno;
	}

	public void setDpstProcCnterBnkbno(String dpstProcCnterBnkbno) {
		this.dpstProcCnterBnkbno = dpstProcCnterBnkbno;
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

	public String getDpstCl() {
		return dpstCl;
	}

	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}

	public String getCashDpstCl() {
		return cashDpstCl;
	}

	public void setCashDpstCl(String cashDpstCl) {
		this.cashDpstCl = cashDpstCl;
	}

	public String getDpstDt() {
		return dpstDt;
	}

	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt;
	}

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

	public String getDpstProcDt() {
		return dpstProcDt;
	}

	public void setDpstProcDt(String dpstProcDt) {
		this.dpstProcDt = dpstProcDt;
	}

	public double getDpstAmt() {
		return dpstAmt;
	}

	public void setDpstAmt(double dpstAmt) {
		this.dpstAmt = dpstAmt;
	}

	public String getDpstFeeTp() {
		return dpstFeeTp;
	}

	public void setDpstFeeTp(String dpstFeeTp) {
		this.dpstFeeTp = dpstFeeTp;
	}

	public double getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getDpstBnkAcntCd() {
		return dpstBnkAcntCd;
	}

	public void setDpstBnkAcntCd(String dpstBnkAcntCd) {
		this.dpstBnkAcntCd = dpstBnkAcntCd;
	}

	public String getRcptEmpId() {
		return rcptEmpId;
	}

	public void setRcptEmpId(String rcptEmpId) {
		this.rcptEmpId = rcptEmpId;
	}

	public String getSmry() {
		return smry;
	}

	public void setSmry(String smry) {
		this.smry = smry;
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
