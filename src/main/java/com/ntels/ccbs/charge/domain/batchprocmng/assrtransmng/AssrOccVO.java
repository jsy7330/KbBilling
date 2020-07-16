package com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng;

import java.util.Date;

public class AssrOccVO {

	/**
	 * 보증 발생 일련 번호
	 */
	private String assrOccSeqNo;

	/**
	 * 납부 일련번호
	 */
	private String pymSeqNo;

	/**
	 * 납부 계정 ID
	 */
	private String pymAcntId;
	
	/**
	 * 상품 구성 ID
	 */
	private String prodCmpsId;

	/**
	 * 서비스 구성 ID
	 */
	private String svcCmpsId;

	/**
	 * 요금 항목 코드
	 */
	private String chrgItmCd;

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;
	
	/**
	 * 고객 ID
	 */
	private String custId;

	/**
	 * 계약 ID
	 */
	private String ctrtId;

	/**
	 * 보증 발생 DTTM
	 */
	private String assrOccDttm;

	/**
	 * 입금구분에 따라 해당 Table에서의 참조 일련번호
	 */
	private String dpstTpSeqNo;

	/**
	 * 고객출금일자
	 */
	private String dpstDt;

	/**
	 * 입금구분 - 공통코드:BL00002
	 */
	private String dpstCl;

	/**
	 * 보증 처리 상태 - 공통코드:BL00013
	 */
	private String assrProcStat;

	/**
	 * 보증 발생 금액
	 */
	private double assrOccAmt;

	/**
	 * 보증  TRANS 금액
	 */
	private double assrTransAmt;

	/**
	 * 보증 잔액
	 */
	private double assrBal;

	/**
	 * TRANS 완료 여부
	 */
	private String transCmplYn;

	/**
	 * 취소 여부
	 */
	private String cnclYn;

	/**
	 * 취소 Dttm
	 */
	private String cnclDttm;
	
	/**
	 * TRANS일자
	 */
	private String transDt;

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
	 * 보증금 대체 일련번호
	 */
	private String assrTransSeqNo;
	
	/**
	 * 입금번호
	 */
	private String dpstSeqNo;

	public String getAssrOccSeqNo() {
		return assrOccSeqNo;
	}

	public void setAssrOccSeqNo(String assrOccSeqNo) {
		this.assrOccSeqNo = assrOccSeqNo;
	}

	public String getPymSeqNo() {
		return pymSeqNo;
	}

	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
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

	public String getChrgItmCd() {
		return chrgItmCd;
	}

	public void setChrgItmCd(String chrgItmCd) {
		this.chrgItmCd = chrgItmCd;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
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

	public String getAssrOccDttm() {
		return assrOccDttm;
	}

	public void setAssrOccDttm(String assrOccDttm) {
		this.assrOccDttm = assrOccDttm;
	}

	public String getDpstTpSeqNo() {
		return dpstTpSeqNo;
	}

	public void setDpstTpSeqNo(String dpstTpSeqNo) {
		this.dpstTpSeqNo = dpstTpSeqNo;
	}

	public String getDpstDt() {
		return dpstDt;
	}

	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt;
	}

	public String getDpstCl() {
		return dpstCl;
	}

	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}

	public String getAssrProcStat() {
		return assrProcStat;
	}

	public void setAssrProcStat(String assrProcStat) {
		this.assrProcStat = assrProcStat;
	}

	public double getAssrOccAmt() {
		return assrOccAmt;
	}

	public void setAssrOccAmt(double assrOccAmt) {
		this.assrOccAmt = assrOccAmt;
	}

	public double getAssrTransAmt() {
		return assrTransAmt;
	}

	public void setAssrTransAmt(double assrTransAmt) {
		this.assrTransAmt = assrTransAmt;
	}

	public double getAssrBal() {
		return assrBal;
	}

	public void setAssrBal(double assrBal) {
		this.assrBal = assrBal;
	}

	public String getTransCmplYn() {
		return transCmplYn;
	}

	public void setTransCmplYn(String transCmplYn) {
		this.transCmplYn = transCmplYn;
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

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
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

	public String getAssrTransSeqNo() {
		return assrTransSeqNo;
	}

	public void setAssrTransSeqNo(String assrTransSeqNo) {
		this.assrTransSeqNo = assrTransSeqNo;
	}

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}

}
