package com.ntels.ccbs.charge.domain.batchprocmng.autotransmng;

import java.sql.Timestamp;

public class NBlpyRcptFileBatVO {

	/**
	 * 일괄 처리 번호
	 */
	private String batProcNo;
	
	/**
	 * 처리 일자
	 */
	private String procDt;
	
	/**
	 * 수납 파일 유형 - 공통코드:BL00089
	 */
	private String rcptFileTp;
	
	/**
	 *  파일명 
	 */
	private String fileNm;

	/**
	 * 총 건수
	 */
	private int totCnt;
	
	/**
	 * 총 금액
	 */
	private double totAmt;

	/**
	 * 처리 건수
	 */
	private int procCnt;

	/**
	 * 처리 금액
	 */
	private double procAmt;
	
	/**
	 * 성공 건수
	 */
	private int scsCnt;

	/**
	 * 성공 금액
	 */
	private double scsAmt;
	
	/**
	 * 오류 건수
	 */
	private int errCnt;

	/**
	 * 오류 금액
	 */
	private double errAmt;
	
	/**
	 * 부분 WTDRAW 건수
	 */
	private int partWtdrawCnt;
	
	/**
	 * 부분 WTDRAW 금액
	 */
	private double partWtdrawAmt;

	/**
	 * 수수료 금액
	 */
	private double feeAmt;
	
	/**
	 * 신규 건수
	 */
	private int newCnt;
	
	/**
	 * 해지 건수
	 */
	private int termCnt;
	
	/**
	 * 임의 해지 건수
	 */
	private int anyTermCnt;
	
	/**
	 * 납부자 변경 건수
	 */
	private int payerChgCnt;
	
	/**
	 * 금융기관 신규 건수
	 */
	private int fnltNewCnt;
	
	/**
	 * 금융기관 해지 건수
	 */
	private int fnltTermCnt;
	
	/**
	 * 금융기관 임의 해지 건수
	 */
	private int fnltAnyTermCnt;
	
	/**
	 * 파일 생성 일련번호
	 */
	private String fileCrtSeqNo;
	
	/**
	 * 처리 상태 - 공통코드:BL00090
	 */
	private String procStat;
	
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

	public String getRcptFileTp() {
		return rcptFileTp;
	}

	public void setRcptFileTp(String rcptFileTp) {
		this.rcptFileTp = rcptFileTp;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	public double getTotAmt() {
		return totAmt;
	}

	public void setTotAmt(double totAmt) {
		this.totAmt = totAmt;
	}

	public int getProcCnt() {
		return procCnt;
	}

	public void setProcCnt(int procCnt) {
		this.procCnt = procCnt;
	}

	public double getProcAmt() {
		return procAmt;
	}

	public void setProcAmt(double procAmt) {
		this.procAmt = procAmt;
	}

	public int getScsCnt() {
		return scsCnt;
	}

	public void setScsCnt(int scsCnt) {
		this.scsCnt = scsCnt;
	}

	public double getScsAmt() {
		return scsAmt;
	}

	public void setScsAmt(double scsAmt) {
		this.scsAmt = scsAmt;
	}

	public int getErrCnt() {
		return errCnt;
	}

	public void setErrCnt(int errCnt) {
		this.errCnt = errCnt;
	}

	public double getErrAmt() {
		return errAmt;
	}

	public void setErrAmt(double errAmt) {
		this.errAmt = errAmt;
	}

	public int getPartWtdrawCnt() {
		return partWtdrawCnt;
	}

	public void setPartWtdrawCnt(int partWtdrawCnt) {
		this.partWtdrawCnt = partWtdrawCnt;
	}

	public double getPartWtdrawAmt() {
		return partWtdrawAmt;
	}

	public void setPartWtdrawAmt(double partWtdrawAmt) {
		this.partWtdrawAmt = partWtdrawAmt;
	}

	public double getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}

	public int getNewCnt() {
		return newCnt;
	}

	public void setNewCnt(int newCnt) {
		this.newCnt = newCnt;
	}

	public int getTermCnt() {
		return termCnt;
	}

	public void setTermCnt(int termCnt) {
		this.termCnt = termCnt;
	}

	public int getAnyTermCnt() {
		return anyTermCnt;
	}

	public void setAnyTermCnt(int anyTermCnt) {
		this.anyTermCnt = anyTermCnt;
	}

	public int getPayerChgCnt() {
		return payerChgCnt;
	}

	public void setPayerChgCnt(int payerChgCnt) {
		this.payerChgCnt = payerChgCnt;
	}

	public int getFnltNewCnt() {
		return fnltNewCnt;
	}

	public void setFnltNewCnt(int fnltNewCnt) {
		this.fnltNewCnt = fnltNewCnt;
	}

	public int getFnltTermCnt() {
		return fnltTermCnt;
	}

	public void setFnltTermCnt(int fnltTermCnt) {
		this.fnltTermCnt = fnltTermCnt;
	}

	public int getFnltAnyTermCnt() {
		return fnltAnyTermCnt;
	}

	public void setFnltAnyTermCnt(int fnltAnyTermCnt) {
		this.fnltAnyTermCnt = fnltAnyTermCnt;
	}

	public String getFileCrtSeqNo() {
		return fileCrtSeqNo;
	}

	public void setFileCrtSeqNo(String fileCrtSeqNo) {
		this.fileCrtSeqNo = fileCrtSeqNo;
	}

	public String getProcStat() {
		return procStat;
	}

	public void setProcStat(String procStat) {
		this.procStat = procStat;
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

}
