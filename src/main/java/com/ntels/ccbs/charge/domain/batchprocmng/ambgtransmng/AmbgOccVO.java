package com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng;

import java.util.Date;

public class AmbgOccVO {

	/**
	 * 불명 발생 번호
	 */
	private String ambgOccSeqNo;
	
	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 불명 발생 Dttm
	 */
	private String ambgOccDttm;

	/**
	 * 불명 발생 타입 - 공통코드:BL00014
	 */
	private String ambgOccTp;

	/**
	 * 불명 발생 사유 - 공통코드:BL00015
	 */
	private String ambgOccResn;

	/**
	 * 입금구분에 따라 해당 Table에서의 참조 일련번호
	 */
	private String dpstTpSeqNo;

	/**
	 * 고객출금일자
	 */
	private String dpstDt;

	/**
	 * 입금처리일
	 */
	private String dpstProcDt;

	/**
	 * 입금구분 - 공통코드:BL00002
	 */
	private String dpstCl;

	/**
	 * 고객 입금 계좌 은행 코드
	 */
	private String dpstBnkAcntCd;

	/**
	 * 고객이 납부한 금융기관 코드 - 자동이체,가상계좌,지로:은행코드. 카드:카드사 코드
	 */
	private String payCorpCd;

	/**
	 * 고객이 납부한 계좌 및 카드번호 - 자동이체,가상계좌:계좌번호. 지로:지로번호. 카드:카드번호
	 */
	private String acntCardNo;

	/**
	 * 불명 처리 상태 - 공통코드:BL00016
	 */
	private String ambgProcStat;

	/**
	 * 불명 발생 금액
	 */
	private double ambgOccAmt;

	/**
	 * 불명 TRANS 금액
	 */
	private double ambgTransAmt;

	/**
	 * 불명 잔액
	 */
	private double ambgBal;

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
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * TRANS 일자
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
	 * 불명 대체 일련번호
	 */
	private String ambgTransSeqNo;

	public String getAmbgOccSeqNo() {
		return ambgOccSeqNo;
	}

	public void setAmbgOccSeqNo(String ambgOccSeqNo) {
		this.ambgOccSeqNo = ambgOccSeqNo;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getAmbgOccDttm() {
		return ambgOccDttm;
	}

	public void setAmbgOccDttm(String ambgOccDttm) {
		this.ambgOccDttm = ambgOccDttm;
	}

	public String getAmbgOccTp() {
		return ambgOccTp;
	}

	public void setAmbgOccTp(String ambgOccTp) {
		this.ambgOccTp = ambgOccTp;
	}

	public String getAmbgOccResn() {
		return ambgOccResn;
	}

	public void setAmbgOccResn(String ambgOccResn) {
		this.ambgOccResn = ambgOccResn;
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

	public String getDpstBnkAcntCd() {
		return dpstBnkAcntCd;
	}

	public void setDpstBnkAcntCd(String dpstBnkAcntCd) {
		this.dpstBnkAcntCd = dpstBnkAcntCd;
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

	public String getAmbgProcStat() {
		return ambgProcStat;
	}

	public void setAmbgProcStat(String ambgProcStat) {
		this.ambgProcStat = ambgProcStat;
	}

	public double getAmbgOccAmt() {
		return ambgOccAmt;
	}

	public void setAmbgOccAmt(double ambgOccAmt) {
		this.ambgOccAmt = ambgOccAmt;
	}

	public double getAmbgTransAmt() {
		return ambgTransAmt;
	}

	public void setAmbgTransAmt(double ambgTransAmt) {
		this.ambgTransAmt = ambgTransAmt;
	}

	public double getAmbgBal() {
		return ambgBal;
	}

	public void setAmbgBal(double ambgBal) {
		this.ambgBal = ambgBal;
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

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
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

	public String getAmbgTransSeqNo() {
		return ambgTransSeqNo;
	}

	public void setAmbgTransSeqNo(String ambgTransSeqNo) {
		this.ambgTransSeqNo = ambgTransSeqNo;
	}

}
