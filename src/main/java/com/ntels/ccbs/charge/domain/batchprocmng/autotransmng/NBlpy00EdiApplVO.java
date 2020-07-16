package com.ntels.ccbs.charge.domain.batchprocmng.autotransmng;

import java.sql.Timestamp;

public class NBlpy00EdiApplVO {

	/**
	 * EDI 신청 일련번호
	 */
	private String ediApplSeqNo;

	/**
	 * 고객ID
	 */
	private String custId;
	
	/**
	 * 납부계정ID
	 */
	private String pymAcntId;
	
	/**
	 * 신청일자
	 */
	private String applDt;
	
	/**
	 * 신청구분 코드 - 공통코드:BL00029
	 */
	private String applClCd;
	
	/**
	 * 변경전 납부자번호
	 */
	private String bfrPayerNo;
	
	/**
	 * 변경후 납부자번호
	 */
	private String atrPayerNo;
	
	/**
	 * 은행코드
	 */
	private String bnkCd;
	
	/**
	 * 납부자 계좌번호
	 */
	private String payerAcntNo;
	
	/**
	 * 계좌 고객 이름
	 */
	private String acntCustNm;
	
	/**
	 * 고객 식별 번호
	 */
	private String custSsnNo;
	
	/**
	 * 납부 고객 핸드폰 번호
	 */
	private String cellPhnNo;
	
	/**
	 * 최초 개시일
	 */
	private String frstBgDt;
	
	/**
	 * 동의 자료 유무
	 */
	private String agreDataYn;
	
	/**
	 * 동의 자료 구분 - 공통코드:BL00032
	 */
	private String agreDataCl;
	
	/**
	 * 동의 자료 파일 경로
	 */
	private String agreDataFilePath;
	
	/**
	 * 동의 자료 파일명
	 */
	private String agreDataFileNm;
	
	/**
	 * 신청 처리 상태 - 공통코드:BL00065
	 */
	private String applProcStat;
	
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
	private Timestamp regDate;

	public String getEdiApplSeqNo() {
		return ediApplSeqNo;
	}

	public void setEdiApplSeqNo(String ediApplSeqNo) {
		this.ediApplSeqNo = ediApplSeqNo;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public String getApplDt() {
		return applDt;
	}

	public void setApplDt(String applDt) {
		this.applDt = applDt;
	}

	public String getApplClCd() {
		return applClCd;
	}

	public void setApplClCd(String applClCd) {
		this.applClCd = applClCd;
	}

	public String getBfrPayerNo() {
		return bfrPayerNo;
	}

	public void setBfrPayerNo(String bfrPayerNo) {
		this.bfrPayerNo = bfrPayerNo;
	}

	public String getAtrPayerNo() {
		return atrPayerNo;
	}

	public void setAtrPayerNo(String atrPayerNo) {
		this.atrPayerNo = atrPayerNo;
	}

	public String getBnkCd() {
		return bnkCd;
	}

	public void setBnkCd(String bnkCd) {
		this.bnkCd = bnkCd;
	}

	public String getPayerAcntNo() {
		return payerAcntNo;
	}

	public void setPayerAcntNo(String payerAcntNo) {
		this.payerAcntNo = payerAcntNo;
	}

	public String getAcntCustNm() {
		return acntCustNm;
	}

	public void setAcntCustNm(String acntCustNm) {
		this.acntCustNm = acntCustNm;
	}

	public String getCustSsnNo() {
		return custSsnNo;
	}

	public void setCustSsnNo(String custSsnNo) {
		this.custSsnNo = custSsnNo;
	}

	public String getCellPhnNo() {
		return cellPhnNo;
	}

	public void setCellPhnNo(String cellPhnNo) {
		this.cellPhnNo = cellPhnNo;
	}

	public String getFrstBgDt() {
		return frstBgDt;
	}

	public void setFrstBgDt(String frstBgDt) {
		this.frstBgDt = frstBgDt;
	}

	public String getAgreDataYn() {
		return agreDataYn;
	}

	public void setAgreDataYn(String agreDataYn) {
		this.agreDataYn = agreDataYn;
	}

	public String getAgreDataCl() {
		return agreDataCl;
	}

	public void setAgreDataCl(String agreDataCl) {
		this.agreDataCl = agreDataCl;
	}

	public String getAgreDataFilePath() {
		return agreDataFilePath;
	}

	public void setAgreDataFilePath(String agreDataFilePath) {
		this.agreDataFilePath = agreDataFilePath;
	}

	public String getAgreDataFileNm() {
		return agreDataFileNm;
	}

	public void setAgreDataFileNm(String agreDataFileNm) {
		this.agreDataFileNm = agreDataFileNm;
	}

	public String getApplProcStat() {
		return applProcStat;
	}

	public void setApplProcStat(String applProcStat) {
		this.applProcStat = applProcStat;
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

}
