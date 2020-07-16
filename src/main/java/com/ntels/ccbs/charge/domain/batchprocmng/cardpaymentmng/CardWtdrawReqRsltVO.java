package com.ntels.ccbs.charge.domain.batchprocmng.cardpaymentmng;

import java.util.Date;

public class CardWtdrawReqRsltVO {

	/**
	 * 출금 요청 번호
	 */
	private String wtdrawReqNo;

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
	 * 계약 ID
	 */
	private String ctrtId;
	
	/**
	 * 고객 ID
	 */
	private String custId;
	
	/**
	 * MID
	 */
	private String mid;
		
	/**
	 * NICE 납부방법
	 */
	private String nicePymMthd;
	
	/**
	 * 결제 취소 구분 - 공통코드:BL00022
	 */
	private String payCnclCl;
	
	/**
	 * 카드 배치 요청 번호
	 */
	private String cardBatReqNo;
	
	/**
	 * UTR ID
	 */
	private String utrId;
	
	/**
	 * 요청 일련번호
	 */
	private int reqSeqNo;
	
	/**
	 * 요청일시 (YYYYMMDDHH24MISS)
	 */
	private String reqDate;
	
	/**
	 * 암호화 카드번호
	 */
	private String encptCardNo;
	
	/**
	 * 결제 카드 번호
	 */
	private String payCardNo;
	
	/**
	 * NICE BILLKEY
	 */
	private String niceBillKey;
	
	/**
	 * 카드 고객명
	 */
	private String cardCustNm;
	
	/**
	 * 카드 고객 이메일
	 */
	private String cardCustEmail;
	
	/**
	 * 결제 항목명
	 */
	private String payItmNm;
	
	/**
	 * 결제 경로 - 공통코드:BL00026
	 */
	private String payPath;
	
	/**
	 * 할부 개월수
	 */
	private String itllmtMthCnt;
	
	/**
	 * 카드 유효 기간
	 */
	private String cardEffcPrd;
	
	/**
	 * 결제 금액
	 */
	private double payAmt;
	
	/**
	 * 연결 번호
	 */
	private String cnncNo;
	
	/**
	 * 무이자 구분
	 */
	private String noIntrstCl;
	
	/**
	 * 부분취소 구분
	 */
	private String partCnclCl;
	
	/**
	 * 취소 사유
	 */
	private String cnclResn;
	
	/**
	 * NICE TID
	 */
	private String niceTid;
	
	/**
	 * 카드 종류 - 공통코드:BL00025
	 */
	private String cardKnd;
	
	/**
	 * 카드사 코드 - 공통코드:BL00024
	 */
	private String cardCorpCd;
	
	/**
	 * 카드사 코드2
	 */
	private String cardCorpCd2;
	
	/**
	 * 카드사명
	 */
	private String cardCorpNm;
	
	/**
	 * 취소 금액
	 */
	private double cnclAmt;
	
	/**
	 * 카드 승인일시 (YYYYMMDDHH24MISS)
	 */
	private String cardConfDate;
	
	/**
	 * 카드 승인 번호
	 */
	private String cardConfNo;	
	
	/**
	 * 나이스 연동 결과 코드 - 공통코드:BL00023
	 */
	private String niceIntrlckRsltCd;
	
	/**
	 * 나이스 연동 결과 메세지
	 */
	private String niceIntrlckRsltMsg;
	
	/**
	 * 상세 에러 코드
	 */
	private String dtlErrCd;
	
	/**
	 * 상세 에러 메세지
	 */
	private String dtlErrMsg;
	
	/**
	 * 고객출금일자
	 */
	private String dpstDt;
	
	/**
	 * 통장입금일자
	 */
	private String trnsDt;
	
	/**
	 * 입금처리일자
	 */
	private String dpstProcDt;
	
	/**
	 * 수수료
	 */
	private double feeAmt;
	
	/**
	 * 카드 SMS 핸드폰 번호
	 */
	private String cardSmsCellPhnNo;
	
	/**
	 * 카드 SMS 전송 여부
	 */
	private String cardSmsSndYn;
	
	/**
	 * 출금 요청 처리 상태 - 공통코드:BL00066
	 */
	private String wtdrawReqProcStat;
	
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
	 * 전체 요청 건수
	 */
	private int totReqCnt;
	
	/**
	 * 5000 건 단위로 나눈 나머지 값
	 */
	private int modVal;
	
	/**
	 * 카드 결제 AUTH KEY
	 */
	private String authKey;
	
	/**
	 * 입금 일련번호
	 */
	private String dpstSeqNo;
	
	/**
	 * 수납 일련번호
	 */
	private String pymSeqNo;
	
	/**
	 * 입금 처리 센터 코드-공통코드:BL00001
	 */
	private String dpstProcCnterCd;
	
	/**
	 * 입금 처리 센터 통장번호
	 */
	private String dpstProcCnterBnkbno;

	public String getWtdrawReqNo() {
		return wtdrawReqNo;
	}

	public void setWtdrawReqNo(String wtdrawReqNo) {
		this.wtdrawReqNo = wtdrawReqNo;
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

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getNicePymMthd() {
		return nicePymMthd;
	}

	public void setNicePymMthd(String nicePymMthd) {
		this.nicePymMthd = nicePymMthd;
	}

	public String getPayCnclCl() {
		return payCnclCl;
	}

	public void setPayCnclCl(String payCnclCl) {
		this.payCnclCl = payCnclCl;
	}

	public String getCardBatReqNo() {
		return cardBatReqNo;
	}

	public void setCardBatReqNo(String cardBatReqNo) {
		this.cardBatReqNo = cardBatReqNo;
	}

	public String getUtrId() {
		return utrId;
	}

	public void setUtrId(String utrId) {
		this.utrId = utrId;
	}

	public int getReqSeqNo() {
		return reqSeqNo;
	}

	public void setReqSeqNo(int reqSeqNo) {
		this.reqSeqNo = reqSeqNo;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getEncptCardNo() {
		return encptCardNo;
	}

	public void setEncptCardNo(String encptCardNo) {
		this.encptCardNo = encptCardNo;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public String getNiceBillKey() {
		return niceBillKey;
	}

	public void setNiceBillKey(String niceBillKey) {
		this.niceBillKey = niceBillKey;
	}

	public String getCardCustNm() {
		return cardCustNm;
	}

	public void setCardCustNm(String cardCustNm) {
		this.cardCustNm = cardCustNm;
	}

	public String getCardCustEmail() {
		return cardCustEmail;
	}

	public void setCardCustEmail(String cardCustEmail) {
		this.cardCustEmail = cardCustEmail;
	}

	public String getPayItmNm() {
		return payItmNm;
	}

	public void setPayItmNm(String payItmNm) {
		this.payItmNm = payItmNm;
	}

	public String getPayPath() {
		return payPath;
	}

	public void setPayPath(String payPath) {
		this.payPath = payPath;
	}

	public String getItllmtMthCnt() {
		return itllmtMthCnt;
	}

	public void setItllmtMthCnt(String itllmtMthCnt) {
		this.itllmtMthCnt = itllmtMthCnt;
	}

	public String getCardEffcPrd() {
		return cardEffcPrd;
	}

	public void setCardEffcPrd(String cardEffcPrd) {
		this.cardEffcPrd = cardEffcPrd;
	}

	public double getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(double payAmt) {
		this.payAmt = payAmt;
	}

	public String getCnncNo() {
		return cnncNo;
	}

	public void setCnncNo(String cnncNo) {
		this.cnncNo = cnncNo;
	}

	public String getNoIntrstCl() {
		return noIntrstCl;
	}

	public void setNoIntrstCl(String noIntrstCl) {
		this.noIntrstCl = noIntrstCl;
	}

	public String getPartCnclCl() {
		return partCnclCl;
	}

	public void setPartCnclCl(String partCnclCl) {
		this.partCnclCl = partCnclCl;
	}

	public String getCnclResn() {
		return cnclResn;
	}

	public void setCnclResn(String cnclResn) {
		this.cnclResn = cnclResn;
	}

	public String getNiceTid() {
		return niceTid;
	}

	public void setNiceTid(String niceTid) {
		this.niceTid = niceTid;
	}

	public String getCardKnd() {
		return cardKnd;
	}

	public void setCardKnd(String cardKnd) {
		this.cardKnd = cardKnd;
	}

	public String getCardCorpCd() {
		return cardCorpCd;
	}

	public void setCardCorpCd(String cardCorpCd) {
		this.cardCorpCd = cardCorpCd;
	}

	public String getCardCorpCd2() {
		return cardCorpCd2;
	}

	public void setCardCorpCd2(String cardCorpCd2) {
		this.cardCorpCd2 = cardCorpCd2;
	}

	public String getCardCorpNm() {
		return cardCorpNm;
	}

	public void setCardCorpNm(String cardCorpNm) {
		this.cardCorpNm = cardCorpNm;
	}

	public double getCnclAmt() {
		return cnclAmt;
	}

	public void setCnclAmt(double cnclAmt) {
		this.cnclAmt = cnclAmt;
	}

	public String getCardConfDate() {
		return cardConfDate;
	}

	public void setCardConfDate(String cardConfDate) {
		this.cardConfDate = cardConfDate;
	}

	public String getCardConfNo() {
		return cardConfNo;
	}

	public void setCardConfNo(String cardConfNo) {
		this.cardConfNo = cardConfNo;
	}

	public String getNiceIntrlckRsltCd() {
		return niceIntrlckRsltCd;
	}

	public void setNiceIntrlckRsltCd(String niceIntrlckRsltCd) {
		this.niceIntrlckRsltCd = niceIntrlckRsltCd;
	}

	public String getNiceIntrlckRsltMsg() {
		return niceIntrlckRsltMsg;
	}

	public void setNiceIntrlckRsltMsg(String niceIntrlckRsltMsg) {
		this.niceIntrlckRsltMsg = niceIntrlckRsltMsg;
	}

	public String getDtlErrCd() {
		return dtlErrCd;
	}

	public void setDtlErrCd(String dtlErrCd) {
		this.dtlErrCd = dtlErrCd;
	}

	public String getDtlErrMsg() {
		return dtlErrMsg;
	}

	public void setDtlErrMsg(String dtlErrMsg) {
		this.dtlErrMsg = dtlErrMsg;
	}

	public String getDpstDt() {
		return dpstDt;
	}

	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt;
	}

	public String getTrnsDt() {
		return trnsDt;
	}

	public void setTrnsDt(String trnsDt) {
		this.trnsDt = trnsDt;
	}

	public String getDpstProcDt() {
		return dpstProcDt;
	}

	public void setDpstProcDt(String dpstProcDt) {
		this.dpstProcDt = dpstProcDt;
	}

	public double getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getCardSmsCellPhnNo() {
		return cardSmsCellPhnNo;
	}

	public void setCardSmsCellPhnNo(String cardSmsCellPhnNo) {
		this.cardSmsCellPhnNo = cardSmsCellPhnNo;
	}

	public String getCardSmsSndYn() {
		return cardSmsSndYn;
	}

	public void setCardSmsSndYn(String cardSmsSndYn) {
		this.cardSmsSndYn = cardSmsSndYn;
	}

	public String getWtdrawReqProcStat() {
		return wtdrawReqProcStat;
	}

	public void setWtdrawReqProcStat(String wtdrawReqProcStat) {
		this.wtdrawReqProcStat = wtdrawReqProcStat;
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

	public int getTotReqCnt() {
		return totReqCnt;
	}

	public void setTotReqCnt(int totReqCnt) {
		this.totReqCnt = totReqCnt;
	}

	public int getModVal() {
		return modVal;
	}

	public void setModVal(int modVal) {
		this.modVal = modVal;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}

	public String getPymSeqNo() {
		return pymSeqNo;
	}

	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
	}

	public String getDpstProcCnterCd() {
		return dpstProcCnterCd;
	}

	public void setDpstProcCnterCd(String dpstProcCnterCd) {
		this.dpstProcCnterCd = dpstProcCnterCd;
	}

	public String getDpstProcCnterBnkbno() {
		return dpstProcCnterBnkbno;
	}

	public void setDpstProcCnterBnkbno(String dpstProcCnterBnkbno) {
		this.dpstProcCnterBnkbno = dpstProcCnterBnkbno;
	}
	
}
