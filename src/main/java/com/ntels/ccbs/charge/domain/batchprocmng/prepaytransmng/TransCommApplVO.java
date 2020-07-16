package com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng;

import java.util.Date;

public class TransCommApplVO {

	/**
	 * 대체 신청 번호
	 */
	private String transApplNo;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;
	
	/**
	 * 대체 신청 구분 - 공통코드:BL00037(01:선수금, 02:불명금, 03:보증금)
	 */
	private String transApplCl;
	
	/**
	 * 대체 신청 대상번호(선수금발생번호, 불명금발생번호, 보증금발생번호)
	 */
	private String transApplTgtNo;
	
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
	 * 사용년월(YYYYMM)
	 */
	private String useYymm;
	
	/**
	 * 서비스구성ID
	 */
	private String svcCmpsId;
	
	/**
	 * 상품구성ID
	 */
	private String prodCmpsId;
	
	/**
	 *  요금항목 코드
	 */
	private String chrgItmCd;
	
	/**
	 * 상위상품구성ID
	 */
	private String uppProdCmpsId;
	
	/**
	 *  신청일자
	 */
	private String applDt;

	/**
	 *  신청자
	 */
	private String applntId;
	
	/**
	 *  대체 신청 가능 금액
	 */
	private double transApplAvlamt;
	
	/**
	 *  대체 신청 금액
	 */
	private double transApplAmt;
	
	/**
	 *  신청자명
	 */
	private String applntNm;
	
	/**
	 *  신청자 핸드폰번호
	 */
	private String applntCellPhnNo;
	
	/**
	 *  신청사유
	 */
	private String applResn;
	
	/**
	 *  처리일자
	 */
	private String procDt;
	
	/**
	 *  처리자
	 */
	private String procId;
	
	/**
	 * 처리 사유
	 */
	private String procResn;
	
	/**
	 * DCSN 처리 상태 - 공통코드:BL00018
	 */
	private String dcsnProcStat;
	
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

	public String getTransApplNo() {
		return transApplNo;
	}

	public void setTransApplNo(String transApplNo) {
		this.transApplNo = transApplNo;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public String getTransApplCl() {
		return transApplCl;
	}

	public void setTransApplCl(String transApplCl) {
		this.transApplCl = transApplCl;
	}

	public String getTransApplTgtNo() {
		return transApplTgtNo;
	}

	public void setTransApplTgtNo(String transApplTgtNo) {
		this.transApplTgtNo = transApplTgtNo;
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

	public String getUseYymm() {
		return useYymm;
	}

	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}

	public String getSvcCmpsId() {
		return svcCmpsId;
	}

	public void setSvcCmpsId(String svcCmpsId) {
		this.svcCmpsId = svcCmpsId;
	}

	public String getProdCmpsId() {
		return prodCmpsId;
	}

	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}

	public String getChrgItmCd() {
		return chrgItmCd;
	}

	public void setChrgItmCd(String chrgItmCd) {
		this.chrgItmCd = chrgItmCd;
	}

	public String getUppProdCmpsId() {
		return uppProdCmpsId;
	}

	public void setUppProdCmpsId(String uppProdCmpsId) {
		this.uppProdCmpsId = uppProdCmpsId;
	}

	public String getApplDt() {
		return applDt;
	}

	public void setApplDt(String applDt) {
		this.applDt = applDt;
	}

	public String getApplntId() {
		return applntId;
	}

	public void setApplntId(String applntId) {
		this.applntId = applntId;
	}

	public double getTransApplAvlamt() {
		return transApplAvlamt;
	}

	public void setTransApplAvlamt(double transApplAvlamt) {
		this.transApplAvlamt = transApplAvlamt;
	}

	public double getTransApplAmt() {
		return transApplAmt;
	}

	public void setTransApplAmt(double transApplAmt) {
		this.transApplAmt = transApplAmt;
	}

	public String getApplntNm() {
		return applntNm;
	}

	public void setApplntNm(String applntNm) {
		this.applntNm = applntNm;
	}

	public String getApplntCellPhnNo() {
		return applntCellPhnNo;
	}

	public void setApplntCellPhnNo(String applntCellPhnNo) {
		this.applntCellPhnNo = applntCellPhnNo;
	}

	public String getApplResn() {
		return applResn;
	}

	public void setApplResn(String applResn) {
		this.applResn = applResn;
	}

	public String getProcDt() {
		return procDt;
	}

	public void setProcDt(String procDt) {
		this.procDt = procDt;
	}

	public String getProcId() {
		return procId;
	}

	public void setProcId(String procId) {
		this.procId = procId;
	}

	public String getProcResn() {
		return procResn;
	}

	public void setProcResn(String procResn) {
		this.procResn = procResn;
	}

	public String getDcsnProcStat() {
		return dcsnProcStat;
	}

	public void setDcsnProcStat(String dcsnProcStat) {
		this.dcsnProcStat = dcsnProcStat;
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
