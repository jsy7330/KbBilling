package com.ntels.ccbs.charge.domain.batchprocmng.autotransmng;

import java.sql.Timestamp;

public class NBlpy00EdiApplReqVO {
	
	/**
	 * EDI 신청 데이터 일련번호
	 */
	private String ediApplDataSeqNo;
	
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
	 * 고객유형 - 공통코드:CM00055
	 */
	private String custTp;
	
	/**
	 * 처리일자
	 */
	private String procDt;
	
	/**
	 * E03_일련번호
	 */
	private int e03SeqNo;
	
	/**
	 * E04_신청일자
	 */
	private String e04ApplDt;
	
	/**
	 * E05_신청 구분 코드 - 공통코드:BL00029
	 */
	private String e05ApplClCd;
	
	/**
	 * E06_변경전 납부자번호
	 */
	private String e06BfrPayerNo;
	
	/**
	 * E07_변경후 납부자번호
	 */
	private String e07AtrPayerNo;
	
	/**
	 * E10_계좌 개설 은행코드
	 */
	private String e10AcntOpenBnkCd;
	
	/**
	 * E11_암호화 계좌번호
	 */
	private String e11EncptAcntNo;
	
	/**
	 * E12_암호화 고객 식별 번호
	 */
	private String e12EncptCustSsnNo;
	
	/**
	 * E13_납부자 전화번호
	 */
	private String e13PayerTelNo;
	
	/**
	 * E14_신청서 접수점 코드
	 */
	private String e14ApfrmRcptpntCd;
	
	/**
	 * E15_신청 결과 코드 - 공통코드:BL00030
	 */
	private String e15ApplRsltCd;
	
	/**
	 * E16_최초 적용 일자
	 */
	private String e16FrstAplyDt;
	
	/**
	 * E17_접수처 구분 - 공통코드:BL00031
	 */
	private String e17RcptDskCl;
	
	/**
	 * E18_결제원 접수번호
	 */
	private String e18PayorgnlRcptNo;
	
	/**
	 * E19_계좌 고객 이름
	 */
	private String e19AcntCustNm;
	
	/**
	 * E20_동의 자료 구분 - 공통코드:BL00032
	 */
	private String e20AgreDataCl;
	
	/**
	 * EDI신청 처리 상태 - 공통코드:BL00033
	 */
	private String ediApplProcStat;
	
	/**
	 * 신청 처리 상태 - 공통코드:BL00065
	 */
	private String applProcStat;
	
	/**
	 * 요청 일괄 처리 번호
	 */
	private String reqBatProcNo;
	
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

	/**
	 * EDI 파일 내용
	 */
	private String ediFileConStr;
	
	/**
	 * EDI 지로번호
	 */
	private String ediGiroNo;
	
	/**
	 * 납부자번호
	 */
	private String payerNo;
	
	/**
	 * 금융회사크도
	 */
	private String bnkCd;
	
	/**
	 * 계좌번호
	 */
	private String acntNo;

	/**
	 * 고객인증번호
	 */
	private String custSsnNo;

	/**
	 * DATA RECORD 구분
	 */
	private String dataRecordYn;

	public String getEdiApplDataSeqNo() {
		return ediApplDataSeqNo;
	}

	public void setEdiApplDataSeqNo(String ediApplDataSeqNo) {
		this.ediApplDataSeqNo = ediApplDataSeqNo;
	}

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

	public String getCustTp() {
		return custTp;
	}

	public void setCustTp(String custTp) {
		this.custTp = custTp;
	}

	public String getProcDt() {
		return procDt;
	}

	public void setProcDt(String procDt) {
		this.procDt = procDt;
	}

	public int getE03SeqNo() {
		return e03SeqNo;
	}

	public void setE03SeqNo(int e03SeqNo) {
		this.e03SeqNo = e03SeqNo;
	}

	public String getE04ApplDt() {
		return e04ApplDt;
	}

	public void setE04ApplDt(String e04ApplDt) {
		this.e04ApplDt = e04ApplDt;
	}

	public String getE05ApplClCd() {
		return e05ApplClCd;
	}

	public void setE05ApplClCd(String e05ApplClCd) {
		this.e05ApplClCd = e05ApplClCd;
	}

	public String getE06BfrPayerNo() {
		return e06BfrPayerNo;
	}

	public void setE06BfrPayerNo(String e06BfrPayerNo) {
		this.e06BfrPayerNo = e06BfrPayerNo;
	}

	public String getE07AtrPayerNo() {
		return e07AtrPayerNo;
	}

	public void setE07AtrPayerNo(String e07AtrPayerNo) {
		this.e07AtrPayerNo = e07AtrPayerNo;
	}

	public String getE10AcntOpenBnkCd() {
		return e10AcntOpenBnkCd;
	}

	public void setE10AcntOpenBnkCd(String e10AcntOpenBnkCd) {
		this.e10AcntOpenBnkCd = e10AcntOpenBnkCd;
	}

	public String getE11EncptAcntNo() {
		return e11EncptAcntNo;
	}

	public void setE11EncptAcntNo(String e11EncptAcntNo) {
		this.e11EncptAcntNo = e11EncptAcntNo;
	}

	public String getE12EncptCustSsnNo() {
		return e12EncptCustSsnNo;
	}

	public void setE12EncptCustSsnNo(String e12EncptCustSsnNo) {
		this.e12EncptCustSsnNo = e12EncptCustSsnNo;
	}

	public String getE13PayerTelNo() {
		return e13PayerTelNo;
	}

	public void setE13PayerTelNo(String e13PayerTelNo) {
		this.e13PayerTelNo = e13PayerTelNo;
	}

	public String getE14ApfrmRcptpntCd() {
		return e14ApfrmRcptpntCd;
	}

	public void setE14ApfrmRcptpntCd(String e14ApfrmRcptpntCd) {
		this.e14ApfrmRcptpntCd = e14ApfrmRcptpntCd;
	}

	public String getE15ApplRsltCd() {
		return e15ApplRsltCd;
	}

	public void setE15ApplRsltCd(String e15ApplRsltCd) {
		this.e15ApplRsltCd = e15ApplRsltCd;
	}

	public String getE16FrstAplyDt() {
		return e16FrstAplyDt;
	}

	public void setE16FrstAplyDt(String e16FrstAplyDt) {
		this.e16FrstAplyDt = e16FrstAplyDt;
	}

	public String getE17RcptDskCl() {
		return e17RcptDskCl;
	}

	public void setE17RcptDskCl(String e17RcptDskCl) {
		this.e17RcptDskCl = e17RcptDskCl;
	}

	public String getE18PayorgnlRcptNo() {
		return e18PayorgnlRcptNo;
	}

	public void setE18PayorgnlRcptNo(String e18PayorgnlRcptNo) {
		this.e18PayorgnlRcptNo = e18PayorgnlRcptNo;
	}

	public String getE19AcntCustNm() {
		return e19AcntCustNm;
	}

	public void setE19AcntCustNm(String e19AcntCustNm) {
		this.e19AcntCustNm = e19AcntCustNm;
	}

	public String getE20AgreDataCl() {
		return e20AgreDataCl;
	}

	public void setE20AgreDataCl(String e20AgreDataCl) {
		this.e20AgreDataCl = e20AgreDataCl;
	}

	public String getEdiApplProcStat() {
		return ediApplProcStat;
	}

	public void setEdiApplProcStat(String ediApplProcStat) {
		this.ediApplProcStat = ediApplProcStat;
	}

	public String getApplProcStat() {
		return applProcStat;
	}

	public void setApplProcStat(String applProcStat) {
		this.applProcStat = applProcStat;
	}

	public String getReqBatProcNo() {
		return reqBatProcNo;
	}

	public void setReqBatProcNo(String reqBatProcNo) {
		this.reqBatProcNo = reqBatProcNo;
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

	public String getEdiFileConStr() {
		return ediFileConStr;
	}

	public void setEdiFileConStr(String ediFileConStr) {
		this.ediFileConStr = ediFileConStr;
	}

	public String getEdiGiroNo() {
		return ediGiroNo;
	}

	public void setEdiGiroNo(String ediGiroNo) {
		this.ediGiroNo = ediGiroNo;
	}

	public String getPayerNo() {
		return payerNo;
	}

	public void setPayerNo(String payerNo) {
		this.payerNo = payerNo;
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

	public String getCustSsnNo() {
		return custSsnNo;
	}

	public void setCustSsnNo(String custSsnNo) {
		this.custSsnNo = custSsnNo;
	}

	public String getDataRecordYn() {
		return dataRecordYn;
	}

	public void setDataRecordYn(String dataRecordYn) {
		this.dataRecordYn = dataRecordYn;
	}
	
}
