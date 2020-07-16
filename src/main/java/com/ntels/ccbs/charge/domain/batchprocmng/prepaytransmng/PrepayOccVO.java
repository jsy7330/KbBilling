package com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng;

import java.util.Date;

public class PrepayOccVO {

	/**
	 * 선수 발생 일련번호
	 */
	private String prepayOccSeqNo;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 선수 발생 DTTM
	 */
	private String prepayOccDttm;

	/**
	 * 선수 발생 타입 - 공통코드:BL00010
	 */
	private String prepayOccTp;
	
	/**
	 * 선수 발생 사유 - 공통코드:BL00011
	 */
	private String prepayOccResn;
	
	/**
	 * 선수금이 발생하게 된 원인에 대한 일련번호
	 */
	private String prepayOccTgtSeqNo;

	/**
	 * 고객출금일자
	 */
	private String dpstDt;

	/**
	 * 입금처리일
	 */
	private String dpstProcDt;

	/**
	 * 선수 처리 상태 - 공통코드:BL00012
	 */
	private String prepayProcStat;

	/**
	 * 선수 발생 금액
	 */
	private double prepayOccAmt;

	/**
	 * 선수 TRANS 금액
	 */
	private double prepayTransAmt;

	/**
	 * 선수 잔액
	 */
	private double prepayBal;
	
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
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 취소사유
	 */
	private String cnclResn;
	
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
	 * 선수 대체 일련번호
	 */
	private String prepayTransSeqNo;
	
	/**
	 * 입금번호
	 */
	private String dpstSeqNo;

	public String getPrepayOccSeqNo() {
		return prepayOccSeqNo;
	}

	public void setPrepayOccSeqNo(String prepayOccSeqNo) {
		this.prepayOccSeqNo = prepayOccSeqNo;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public String getPrepayOccDttm() {
		return prepayOccDttm;
	}

	public void setPrepayOccDttm(String prepayOccDttm) {
		this.prepayOccDttm = prepayOccDttm;
	}

	public String getPrepayOccTp() {
		return prepayOccTp;
	}

	public void setPrepayOccTp(String prepayOccTp) {
		this.prepayOccTp = prepayOccTp;
	}

	public String getPrepayOccResn() {
		return prepayOccResn;
	}

	public void setPrepayOccResn(String prepayOccResn) {
		this.prepayOccResn = prepayOccResn;
	}

	public String getPrepayOccTgtSeqNo() {
		return prepayOccTgtSeqNo;
	}

	public void setPrepayOccTgtSeqNo(String prepayOccTgtSeqNo) {
		this.prepayOccTgtSeqNo = prepayOccTgtSeqNo;
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

	public String getPrepayProcStat() {
		return prepayProcStat;
	}

	public void setPrepayProcStat(String prepayProcStat) {
		this.prepayProcStat = prepayProcStat;
	}

	public double getPrepayOccAmt() {
		return prepayOccAmt;
	}

	public void setPrepayOccAmt(double prepayOccAmt) {
		this.prepayOccAmt = prepayOccAmt;
	}

	public double getPrepayTransAmt() {
		return prepayTransAmt;
	}

	public void setPrepayTransAmt(double prepayTransAmt) {
		this.prepayTransAmt = prepayTransAmt;
	}

	public double getPrepayBal() {
		return prepayBal;
	}

	public void setPrepayBal(double prepayBal) {
		this.prepayBal = prepayBal;
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

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getCnclResn() {
		return cnclResn;
	}

	public void setCnclResn(String cnclResn) {
		this.cnclResn = cnclResn;
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

	public String getPrepayTransSeqNo() {
		return prepayTransSeqNo;
	}

	public void setPrepayTransSeqNo(String prepayTransSeqNo) {
		this.prepayTransSeqNo = prepayTransSeqNo;
	}

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}

}
