package com.ntels.ccbs.charge.domain.batchprocmng.autotransmng;

import java.sql.Timestamp;

public class NBlivBillFileCrtVO {

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;
	
	/**
	 * 파일 생성 번호
	 */
	private String fileCrtSeqNo;
	
	/**
	 * 청구 년월(YYYYMM)
	 */
	private String billYymm;
	
	/**
	 * 청구 주기
	 */
	private String billCycl;
	
	/**
	 * 청구 일자
	 */
	private String billDt;
	
	/**
	 * 파일 유형
	 */
	private String crtFileTp;
	
	/**
	 * 청구 구분
	 */
	private String billCl;
	
	/**
	 * 파일 생성 일자(YYYYMMDD)
	 */
	private String fileCrtDt;

	/**
	 * 파일 경로
	 */
	private String filePath;
	
	/**
	 * 파일명
	 */
	private String fileNm;
	
	/**
	 * 파일 SIZE
	 */
	private long fileSize;
	
	/**
	 * 입금 요청 일자(YYYYMMDD)
	 */
	private String dpstReqDt;
	
	/**
	 * 처리 건수
	 */
	private int procCnt;
	
	/**
	 * 최종 다운로드 일자(YYYYMMDD)
	 */
	private String lastDownloadDt;
	
	/**
	 * 최종 다운로드 작업자
	 */
	private String lastDownloadEmpId;
	
	/**
	 * 에러 DESCRIPTION
	 */
	private String errDesc;
	
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

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getFileCrtSeqNo() {
		return fileCrtSeqNo;
	}

	public void setFileCrtSeqNo(String fileCrtSeqNo) {
		this.fileCrtSeqNo = fileCrtSeqNo;
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

	public String getCrtFileTp() {
		return crtFileTp;
	}

	public void setCrtFileTp(String crtFileTp) {
		this.crtFileTp = crtFileTp;
	}

	public String getBillCl() {
		return billCl;
	}

	public void setBillCl(String billCl) {
		this.billCl = billCl;
	}

	public String getFileCrtDt() {
		return fileCrtDt;
	}

	public void setFileCrtDt(String fileCrtDt) {
		this.fileCrtDt = fileCrtDt;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getDpstReqDt() {
		return dpstReqDt;
	}

	public void setDpstReqDt(String dpstReqDt) {
		this.dpstReqDt = dpstReqDt;
	}

	public int getProcCnt() {
		return procCnt;
	}

	public void setProcCnt(int procCnt) {
		this.procCnt = procCnt;
	}

	public String getLastDownloadDt() {
		return lastDownloadDt;
	}

	public void setLastDownloadDt(String lastDownloadDt) {
		this.lastDownloadDt = lastDownloadDt;
	}

	public String getLastDownloadEmpId() {
		return lastDownloadEmpId;
	}

	public void setLastDownloadEmpId(String lastDownloadEmpId) {
		this.lastDownloadEmpId = lastDownloadEmpId;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
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
