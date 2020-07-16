package com.ntels.ccbs.charge.domain.batchprocmng.refundmng;

import java.util.Date;

public class RefundVO {

	/**
	 * 환불 일련 번호
	 */
	private String rfndSeqNo;

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
	private String appntTelNo;
	
	/**
	 * 환불금액
	 */
	private double rfndAmt;
	
	/**
	 * 처리 PSN ID
	 */
	private String procPsnId;
	
	/**
	 * 처리 DTTM - YYYYMMDDHH24MISS
	 */
	private String procDttm;
	
	/**
	 * 고객 환불 일자
	 */
	private String custRfndDt;
	
	/**
	 * 관리 센터 조직 ID
	 */
	private String mngCnterOrgId;

	/**
	 * 관리 지사 조직 ID
	 */
	private String mngBrOrgId;
	
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
	
	/**
	 * 환불발생 금액구분
	 */
	private String rfndOccAmtCl;
	
	/**
	 * 환불발생 대상 일련번호
	 */
	private String rfndOccTgtSeqNo;
	

	public String getRfndSeqNo() {
		return rfndSeqNo;
	}

	public void setRfndSeqNo(String rfndSeqNo) {
		this.rfndSeqNo = rfndSeqNo;
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

	public String getAppntTelNo() {
		return appntTelNo;
	}

	public void setAppntTelNo(String appntTelNo) {
		this.appntTelNo = appntTelNo;
	}

	public double getRfndAmt() {
		return rfndAmt;
	}

	public void setRfndAmt(double rfndAmt) {
		this.rfndAmt = rfndAmt;
	}

	public String getProcPsnId() {
		return procPsnId;
	}

	public void setProcPsnId(String procPsnId) {
		this.procPsnId = procPsnId;
	}

	public String getProcDttm() {
		return procDttm;
	}

	public void setProcDttm(String procDttm) {
		this.procDttm = procDttm;
	}

	public String getCustRfndDt() {
		return custRfndDt;
	}

	public void setCustRfndDt(String custRfndDt) {
		this.custRfndDt = custRfndDt;
	}

	public String getMngCnterOrgId() {
		return mngCnterOrgId;
	}

	public void setMngCnterOrgId(String mngCnterOrgId) {
		this.mngCnterOrgId = mngCnterOrgId;
	}

	public String getMngBrOrgId() {
		return mngBrOrgId;
	}

	public void setMngBrOrgId(String mngBrOrgId) {
		this.mngBrOrgId = mngBrOrgId;
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

}
