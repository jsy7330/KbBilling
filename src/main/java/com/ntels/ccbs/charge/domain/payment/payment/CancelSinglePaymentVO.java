package com.ntels.ccbs.charge.domain.payment.payment;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("CancelSinglePaymentVO")
public class CancelSinglePaymentVO extends PagingValue {

	private String orgId;		//조직id
	private String orgNm;		//조직
	private String soId;		//사업자Id
	private String soNm;		//사업자명
	private String usrId;		//사용자id
	private String lngTyp;
	private String sidx;
	private String sord;
	private String chk;
	
	private String registantNm;     //등록자명
	private String registDate;      //등록일
	private String pymAcntId;	    //납부계정id
	private String pymAcntNm;	    //납부계정
	private String pymAcntMethod;	//납부방법
	private String pymAcntMethodNm;	//납부방법
	private String depositTyp;		//입금형태
	private String depositTypNm;    //입금형태명
	private String depositOption;	//입금구분
	private String depositOptionNm; //입금구분명
	private String financialInstitution;	//입금기관
	private String financialInstitutionNm;	//입금기관명
	private String financialInstitutionType;       //기관유형
    private String financialInstitutionTypeNm;       //기관유형명
    private String acntCardNo; 		//계좌 및 카드번호
    private String depositAmt;		//입금금액
    private String paymentDt;		//고객납부일자
    private String transferDt;		//이체일
    private String depositProcessingDt; //입금처리일자
    private String unknownPayment;	      //미확인입금대상
    private String overPaymentRecovered;  //선수금대상
    private String rcptProcessingDt;	  //수납처리일자
    private String payProcYn;       //수납처리여부
    private String regId;           //등록자ID
    private String depositSeqNo;    //입금일련번호
    
    private String rcptCnclCnt;
	
	/* 입금관련 */
	
	private String depositDt;		//입금일자
	private String depositDtFrom;   //입금일자 from
	private String depositDtTo;     //입금일자 to
	private String procDt;          //sysdate
	
	// 화면 권한 (여신 + 조직관련)
	private String deptCd;
	private String loanAvlAmt;
	private String loanGvFlg;
	
	private String rsnForCnsl; //취소사유
	
	
	
	
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getFinancialInstitutionType() {
		return financialInstitutionType;
	}
	public void setFinancialInstitutionType(String financialInstitutionType) {
		this.financialInstitutionType = financialInstitutionType;
	}
	public String getFinancialInstitutionTypeNm() {
		return financialInstitutionTypeNm;
	}
	public void setFinancialInstitutionTypeNm(String financialInstitutionTypeNm) {
		this.financialInstitutionTypeNm = financialInstitutionTypeNm;
	}
	public String getRcptCnclCnt() {
		return rcptCnclCnt;
	}
	public void setRcptCnclCnt(String rcptCnclCnt) {
		this.rcptCnclCnt = rcptCnclCnt;
	}
	public String getDepositProcessingDt() {
		return depositProcessingDt;
	}
	public void setDepositProcessingDt(String depositProcessingDt) {
		this.depositProcessingDt = depositProcessingDt;
	}
	public String getRcptProcessingDt() {
		return rcptProcessingDt;
	}
	public void setRcptProcessingDt(String rcptProcessingDt) {
		this.rcptProcessingDt = rcptProcessingDt;
	}
	public String getPymAcntMethod() {
		return pymAcntMethod;
	}
	public void setPymAcntMethod(String pymAcntMethod) {
		this.pymAcntMethod = pymAcntMethod;
	}
	public String getFinancialInstitutionNm() {
		return financialInstitutionNm;
	}
	public void setFinancialInstitutionNm(String financialInstitutionNm) {
		this.financialInstitutionNm = financialInstitutionNm;
	}
	public String getPayProcYn() {
		return payProcYn;
	}
	public void setPayProcYn(String payProcYn) {
		this.payProcYn = payProcYn;
	}
	public String getRsnForCnsl() {
		return rsnForCnsl;
	}
	public void setRsnForCnsl(String rsnForCnsl) {
		this.rsnForCnsl = rsnForCnsl;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getRegistantNm() {
		return registantNm;
	}
	public void setRegistantNm(String registantNm) {
		this.registantNm = registantNm;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getPymAcntNm() {
		return pymAcntNm;
	}
	public void setPymAcntNm(String pymAcntNm) {
		this.pymAcntNm = pymAcntNm;
	}
	public String getPymAcntMethodNm() {
		return pymAcntMethodNm;
	}
	public void setPymAcntMethodNm(String pymAcntMethodNm) {
		this.pymAcntMethodNm = pymAcntMethodNm;
	}
	public String getDepositTyp() {
		return depositTyp;
	}
	public void setDepositTyp(String depositTyp) {
		this.depositTyp = depositTyp;
	}
	public String getDepositTypNm() {
		return depositTypNm;
	}
	public void setDepositTypNm(String depositTypNm) {
		this.depositTypNm = depositTypNm;
	}
	public String getDepositOption() {
		return depositOption;
	}
	public void setDepositOption(String depositOption) {
		this.depositOption = depositOption;
	}
	public String getDepositOptionNm() {
		return depositOptionNm;
	}
	public void setDepositOptionNm(String depositOptionNm) {
		this.depositOptionNm = depositOptionNm;
	}
	public String getFinancialInstitution() {
		return financialInstitution;
	}
	public void setFinancialInstitution(String financialInstitution) {
		this.financialInstitution = financialInstitution;
	}
	public String getAcntCardNo() {
		return acntCardNo;
	}
	public void setAcntCardNo(String acntCardNo) {
		this.acntCardNo = acntCardNo;
	}
	public String getDepositAmt() {
		return depositAmt;
	}
	public void setDepositAmt(String depositAmt) {
		this.depositAmt = depositAmt;
	}
	public String getPaymentDt() {
		return paymentDt;
	}
	public void setPaymentDt(String paymentDt) {
		this.paymentDt = paymentDt;
	}
	public String getTransferDt() {
		return transferDt;
	}
	public void setTransferDt(String transferDt) {
		this.transferDt = transferDt;
	}
	public String getUnknownPayment() {
		return unknownPayment;
	}
	public void setUnknownPayment(String unknownPayment) {
		this.unknownPayment = unknownPayment;
	}
	public String getOverPaymentRecovered() {
		return overPaymentRecovered;
	}
	public void setOverPaymentRecovered(String overPaymentRecovered) {
		this.overPaymentRecovered = overPaymentRecovered;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getDepositSeqNo() {
		return depositSeqNo;
	}
	public void setDepositSeqNo(String depositSeqNo) {
		this.depositSeqNo = depositSeqNo;
	}
	public String getDepositDt() {
		return depositDt;
	}
	public void setDepositDt(String depositDt) {
		this.depositDt = depositDt;
	}
	public String getDepositDtFrom() {
		return depositDtFrom;
	}
	public void setDepositDtFrom(String depositDtFrom) {
		this.depositDtFrom = depositDtFrom;
	}
	public String getDepositDtTo() {
		return depositDtTo;
	}
	public void setDepositDtTo(String depositDtTo) {
		this.depositDtTo = depositDtTo;
	}
	public String getProcDt() {
		return procDt;
	}
	public void setProcDt(String procDt) {
		this.procDt = procDt;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getLoanAvlAmt() {
		return loanAvlAmt;
	}
	public void setLoanAvlAmt(String loanAvlAmt) {
		this.loanAvlAmt = loanAvlAmt;
	}
	public String getLoanGvFlg() {
		return loanGvFlg;
	}
	public void setLoanGvFlg(String loanGvFlg) {
		this.loanGvFlg = loanGvFlg;
	}
	
	
	
}
