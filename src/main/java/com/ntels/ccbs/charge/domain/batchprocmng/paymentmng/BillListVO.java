package com.ntels.ccbs.charge.domain.batchprocmng.paymentmng;

import java.util.Date;

public class BillListVO {

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;
	
	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) +
	 * 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;
	
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
	 * 상위서비스구성ID
	 */
	private String uppSvcCmpsId;
	
	/**
	 * 상위상품구성ID
	 */
	private String uppProdCmpsId;

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
	 * 단체ID
	 */
	private String grpId;
	
	/**
	 * 계약ID
	 */
	private String ctrtId;
	
	/**
	 * 고객ID
	 */
	private String custId;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 청구금액
	 */
	private double billAmt;
	
	/**
	 * 수납금액
	 */
	private double rcptAmt;
	
	/**
	 * 최종 수납일자
	 */
	private String lastRcptDt;
	
	/**
	 * 전액 수납처리 여부
	 */
	private String fullPayYn;
	
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
	 * 입금적용순서1-마이너스 금액 먼저
	 */
	private int	aplyOrder1;

	/**
	 * 입금적용순서2-부가세 먼저
	 */
	private int	aplyOrder2;
	
	/**
	 * 변경자ID
	 */
	private String chgrId;
	
	/**
	 * 변경일
	 */
	private Date chgDate;
	
	/**
	 * 수납 번호
	 */
	private String pymSeqNo;
	
	/**
	 * 청구항목코드
	 */
	private String invItmCd;
	
	/**
	 * 수납내역 생성을 위한 순번
	 */
	private String rcptVal;
	
	/**
	 * 청구항목코드
	 */
	private String rateItmTypCd;

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getBillSeqNo() {
		return billSeqNo;
	}

	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
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

	public String getUppSvcCmpsId() {
		return uppSvcCmpsId;
	}

	public void setUppSvcCmpsId(String uppSvcCmpsId) {
		this.uppSvcCmpsId = uppSvcCmpsId;
	}

	public String getUppProdCmpsId() {
		return uppProdCmpsId;
	}

	public void setUppProdCmpsId(String uppProdCmpsId) {
		this.uppProdCmpsId = uppProdCmpsId;
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

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getCtrtId() {
		return ctrtId;
	}

	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
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

	public String getLastRcptDt() {
		return lastRcptDt;
	}

	public void setLastRcptDt(String lastRcptDt) {
		this.lastRcptDt = lastRcptDt;
	}

	public String getFullPayYn() {
		return fullPayYn;
	}

	public void setFullPayYn(String fullPayYn) {
		this.fullPayYn = fullPayYn;
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

	public int getAplyOrder1() {
		return aplyOrder1;
	}

	public void setAplyOrder1(int aplyOrder1) {
		this.aplyOrder1 = aplyOrder1;
	}

	public int getAplyOrder2() {
		return aplyOrder2;
	}

	public void setAplyOrder2(int aplyOrder2) {
		this.aplyOrder2 = aplyOrder2;
	}

	public String getChgrId() {
		return chgrId;
	}

	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

	public Date getChgDate() {
		return chgDate;
	}

	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}

	public String getPymSeqNo() {
		return pymSeqNo;
	}

	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
	}

	public String getInvItmCd() {
		return invItmCd;
	}

	public void setInvItmCd(String invItmCd) {
		this.invItmCd = invItmCd;
	}

	public String getRcptVal() {
		return rcptVal;
	}

	public void setRcptVal(String rcptVal) {
		this.rcptVal = rcptVal;
	}

	public String getRateItmTypCd() {
		return rateItmTypCd;
	}

	public void setRateItmTypCd(String rateItmTypCd) {
		this.rateItmTypCd = rateItmTypCd;
	}
	
}
