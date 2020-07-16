package com.ntels.ccbs.charge.domain.batchprocmng.paymentmng;

import java.util.Date;

public class ReceiptDtlVO {

	/**
	 * 수납일련번호
	 */
	private String pymSeqNo;
	
	/**
	 * 상품구성ID
	 */
	private String prodCmpsId;
	
	/**
	 * 서비스구성ID
	 */
	private String svcCmpsId;
	
	/**
	 *  요금항목 코드
	 */
	private String chrgItmCd;

	/**
	 * 사용년월(YYYYMM)
	 */
	private String useYymm;
	
	/**
	 * 상위상품구성ID
	 */
	private String uppProdCmpsId;

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 고객ID
	 */
	private String custId;

	/**
	 * 계약ID
	 */
	private String ctrtId;

	/**
	 * 청구금액
	 */
	private double billAmt;

	/**
	 * 수납금액
	 */
	private double rcptAmt;

	/**
	 * 이전수납금액
	 */
	private double prePymAmt;

	private double preRcptAmt;
	/**
	 * 이전지점ID
	 */
	private String preSoId;
	
	/**
	 * 관리 본부 조직 ID
	 */
	private String mngCnterOrgId;
	
	/**
	 * 관리 지사 조직 ID
	 */
	private String mngBrOrgId;
	
	/**
	 * 관리자 ID
	 */
	private String mngrId;
	
	/**
	 * 선수 대체 일련번호
	 */
	private String prepayTransSeqNo;

	/**
	 * 불명 대체 일련번호
	 */
	private String ambgTransSeqNo;

	/**
	 * 보증 대체 일련번호
	 */
	private String assrTransSeqNo;
	
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
	 * 입금번호
	 */
	private String dpstSeqNo;
	
	/**
	 * 청구년월
	 */
	private String billYymm;
	
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
	 * 결제 타입 - 공통코드:BL00009
	 */
	private String payTp;
	
	/**
	 * 선수금 발생번호
	 */
	private String prepayOccSeqNo;
	
	/**
	 * 선수금 잔액
	 */
	private double prepayBal;

	public String getPymSeqNo() {
		return pymSeqNo;
	}

	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
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

	public String getUseYymm() {
		return useYymm;
	}

	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}

	public String getUppProdCmpsId() {
		return uppProdCmpsId;
	}

	public void setUppProdCmpsId(String uppProdCmpsId) {
		this.uppProdCmpsId = uppProdCmpsId;
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

	public double getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(double billAmt) {
		this.billAmt = billAmt;
	}

	public double getRcptAmt() {
		return rcptAmt;
	}

	public void setRcptAmt(double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}

	public double getPrePymAmt() {
		return prePymAmt;
	}

	public void setPrePymAmt(double prePymAmt) {
		this.prePymAmt = prePymAmt;
	}

	public String getPreSoId() {
		return preSoId;
	}

	public void setPreSoId(String preSoId) {
		this.preSoId = preSoId;
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

	public String getMngrId() {
		return mngrId;
	}

	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}

	public String getPrepayTransSeqNo() {
		return prepayTransSeqNo;
	}

	public void setPrepayTransSeqNo(String prepayTransSeqNo) {
		this.prepayTransSeqNo = prepayTransSeqNo;
	}

	public String getAmbgTransSeqNo() {
		return ambgTransSeqNo;
	}

	public void setAmbgTransSeqNo(String ambgTransSeqNo) {
		this.ambgTransSeqNo = ambgTransSeqNo;
	}

	public String getAssrTransSeqNo() {
		return assrTransSeqNo;
	}

	public void setAssrTransSeqNo(String assrTransSeqNo) {
		this.assrTransSeqNo = assrTransSeqNo;
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

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}

	public String getBillYymm() {
		return billYymm;
	}

	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
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

	public String getPayTp() {
		return payTp;
	}

	public void setPayTp(String payTp) {
		this.payTp = payTp;
	}

	public String getPrepayOccSeqNo() {
		return prepayOccSeqNo;
	}

	public void setPrepayOccSeqNo(String prepayOccSeqNo) {
		this.prepayOccSeqNo = prepayOccSeqNo;
	}

	public double getPrepayBal() {
		return prepayBal;
	}

	public void setPrepayBal(double prepayBal) {
		this.prepayBal = prepayBal;
	}

	public double getPreRcptAmt() {
		return preRcptAmt;
	}

	public void setPreRcptAmt(double preRcptAmt) {
		this.preRcptAmt = preRcptAmt;
	}
	
}
