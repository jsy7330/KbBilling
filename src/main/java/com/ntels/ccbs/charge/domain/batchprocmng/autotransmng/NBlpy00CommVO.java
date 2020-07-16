package com.ntels.ccbs.charge.domain.batchprocmng.autotransmng;

import java.sql.Timestamp;

public class NBlpy00CommVO {
	
	/**
	 * 청구월
	 */
	private String billYymm;

	/**
	 * 결제일
	 */
	private String payDd;
	
	/**
	 * 출금요청일
	 */
	private String wtdrawReqDt;
	
	/**
	 * 납부방법 코드
	 */
	private String pymMthdCd;
	
	/**
	 * 파일 생성 일련번호
	 */
	private String fileCrtSeqNo;
	
	/**
	 * 이체 일자
	 */
	private String trnsDt;
	
	/**
	 * 일괄 처리 번호
	 */
	private String batProcNo;
	
	/**
	 * 처리 일자
	 */
	private String procDt;
	
	/**
	 * 지로 입금일
	 */
	private String giroDpstDt;
	
	/**
	 * 거래 일자
	 */
	private String dealDt;
	
	/**
	 * 카드 배치 요청 번호
	 */
	private String cardBatReqNo;

	/**
	 * 출금 요청 번호
	 */
	private String wtdrawReqNo;
	
	/**
	 * 취소 사유
	 */
	private String cnclResnTxt;
	
	/**
	 * 현금영수증 발행대상 구분
	 */
	private String cashRcptIssCl;
	
	/**
	 * 현금영수증 발행대상 입금/입금취소일-FROM
	 */
	private String dpstCnclDtFr;
	
	/**
	 * 현금영수증 발행대상 입금/입금취소일-TO
	 */
	private String dpstCnclDtTo;
	
	/**
	 * 수납 ERP 데이터 생성 기준일
	 */
	private String stdrDt;
	
	/**
	 * 수납 ERP 데이터 발생일
	 */
	private String dataOccDt;
	
	/**
	 * 결제 유형
	 */
	private String payTp;
	
	/**
	 * ERP 전송일자
	 */
	private String erpSndDt;
	
	/**
	 * 입력 메뉴 ID
	 */
	private String inptMenuId;
	
	/**
	 * 작업자 ID
	 */
	private String workId;
	
	/**
	 * 변경일
	 */
	private Timestamp chgDate;

	public String getBillYymm() {
		return billYymm;
	}

	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}

	public String getPayDd() {
		return payDd;
	}

	public void setPayDd(String payDd) {
		this.payDd = payDd;
	}

	public String getWtdrawReqDt() {
		return wtdrawReqDt;
	}

	public void setWtdrawReqDt(String wtdrawReqDt) {
		this.wtdrawReqDt = wtdrawReqDt;
	}

	public String getPymMthdCd() {
		return pymMthdCd;
	}

	public void setPymMthdCd(String pymMthdCd) {
		this.pymMthdCd = pymMthdCd;
	}

	public String getFileCrtSeqNo() {
		return fileCrtSeqNo;
	}

	public void setFileCrtSeqNo(String fileCrtSeqNo) {
		this.fileCrtSeqNo = fileCrtSeqNo;
	}

	public String getTrnsDt() {
		return trnsDt;
	}

	public void setTrnsDt(String trnsDt) {
		this.trnsDt = trnsDt;
	}

	public String getBatProcNo() {
		return batProcNo;
	}

	public void setBatProcNo(String batProcNo) {
		this.batProcNo = batProcNo;
	}

	public String getProcDt() {
		return procDt;
	}

	public void setProcDt(String procDt) {
		this.procDt = procDt;
	}

	public String getGiroDpstDt() {
		return giroDpstDt;
	}

	public void setGiroDpstDt(String giroDpstDt) {
		this.giroDpstDt = giroDpstDt;
	}

	public String getDealDt() {
		return dealDt;
	}

	public void setDealDt(String dealDt) {
		this.dealDt = dealDt;
	}

	public String getCardBatReqNo() {
		return cardBatReqNo;
	}

	public void setCardBatReqNo(String cardBatReqNo) {
		this.cardBatReqNo = cardBatReqNo;
	}

	public String getWtdrawReqNo() {
		return wtdrawReqNo;
	}

	public void setWtdrawReqNo(String wtdrawReqNo) {
		this.wtdrawReqNo = wtdrawReqNo;
	}

	public String getCnclResnTxt() {
		return cnclResnTxt;
	}

	public void setCnclResnTxt(String cnclResnTxt) {
		this.cnclResnTxt = cnclResnTxt;
	}

	public String getCashRcptIssCl() {
		return cashRcptIssCl;
	}

	public void setCashRcptIssCl(String cashRcptIssCl) {
		this.cashRcptIssCl = cashRcptIssCl;
	}

	public String getDpstCnclDtFr() {
		return dpstCnclDtFr;
	}

	public void setDpstCnclDtFr(String dpstCnclDtFr) {
		this.dpstCnclDtFr = dpstCnclDtFr;
	}

	public String getDpstCnclDtTo() {
		return dpstCnclDtTo;
	}

	public void setDpstCnclDtTo(String dpstCnclDtTo) {
		this.dpstCnclDtTo = dpstCnclDtTo;
	}

	public String getStdrDt() {
		return stdrDt;
	}

	public void setStdrDt(String stdrDt) {
		this.stdrDt = stdrDt;
	}

	public String getDataOccDt() {
		return dataOccDt;
	}

	public void setDataOccDt(String dataOccDt) {
		this.dataOccDt = dataOccDt;
	}

	public String getPayTp() {
		return payTp;
	}

	public void setPayTp(String payTp) {
		this.payTp = payTp;
	}

	public String getErpSndDt() {
		return erpSndDt;
	}

	public void setErpSndDt(String erpSndDt) {
		this.erpSndDt = erpSndDt;
	}

	public String getInptMenuId() {
		return inptMenuId;
	}

	public void setInptMenuId(String inptMenuId) {
		this.inptMenuId = inptMenuId;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public Timestamp getChgDate() {
		return chgDate;
	}

	public void setChgDate(Timestamp chgDate) {
		this.chgDate = chgDate;
	}

}
