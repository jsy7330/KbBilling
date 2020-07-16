package com.ntels.ccbs.charge.domain.batchprocmng.receiptcnclmng;

import java.util.Date;

public class PaymentCancelVO {

	/**
	 * 수납일련번호
	 */
	private String pymSeqNo;
	
	/**
	 * 입금일련번호
	 */
	private String dpstSeqNo;
	
	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) +
	 * 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;

	/**
	 * 청구년월(YYYYMM)
	 */
	private String billYymm;

	/**
	 * 청구주기
	 */
	private String billCycl;

	/**
	 * 청구일자(YYYYMMDD)
	 */
	private String billDt;
	
	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 취소일자
	 */
	private String cnclDt;
	
	/**
	 * 수납처리일자
	 */
	private String payProcDt;	

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
	 * 입금구분 - 공통코드:BL00002
	 */
	private String dpstCl;
	
	/**
	 * 결제타입 - 공통코드:BL00009
	 */
	private String payTp;

	/**
	 * 수납대상금액
	 */
	private double payObjAmt;

	/**
	 * 수납적용액
	 */
	private double payAplyAmt;

	/**
	 * 선수금적용액
	 */
	private double prepayAplyAmt;

	/**
	 * 수납금액
	 */
	private double rcptAmt;
	
	/**
	 * 선수 발생 일련번호
	 */
	private String prepayOccSeqNo;

	/**
	 * 선납계약ID
	 */
	private String prepdCtrtId;
	
	/**
	 * 취소 사원ID
	 */
	private String cnclEmpId;
	
	/**
	 * 취소 사유
	 */
	private String cnclResn;
	
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

	public String getPymSeqNo() {
		return pymSeqNo;
	}

	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
	}

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
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

	public String getCnclDt() {
		return cnclDt;
	}

	public void setCnclDt(String cnclDt) {
		this.cnclDt = cnclDt;
	}

	public String getPayProcDt() {
		return payProcDt;
	}

	public void setPayProcDt(String payProcDt) {
		this.payProcDt = payProcDt;
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

	public String getDpstCl() {
		return dpstCl;
	}

	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}

	public String getPayTp() {
		return payTp;
	}

	public void setPayTp(String payTp) {
		this.payTp = payTp;
	}

	public double getPayObjAmt() {
		return payObjAmt;
	}

	public void setPayObjAmt(double payObjAmt) {
		this.payObjAmt = payObjAmt;
	}

	public double getPayAplyAmt() {
		return payAplyAmt;
	}

	public void setPayAplyAmt(double payAplyAmt) {
		this.payAplyAmt = payAplyAmt;
	}

	public double getPrepayAplyAmt() {
		return prepayAplyAmt;
	}

	public void setPrepayAplyAmt(double prepayAplyAmt) {
		this.prepayAplyAmt = prepayAplyAmt;
	}

	public double getRcptAmt() {
		return rcptAmt;
	}

	public void setRcptAmt(double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}

	public String getPrepayOccSeqNo() {
		return prepayOccSeqNo;
	}

	public void setPrepayOccSeqNo(String prepayOccSeqNo) {
		this.prepayOccSeqNo = prepayOccSeqNo;
	}

	public String getPrepdCtrtId() {
		return prepdCtrtId;
	}

	public void setPrepdCtrtId(String prepdCtrtId) {
		this.prepdCtrtId = prepdCtrtId;
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
