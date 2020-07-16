package com.ntels.ccbs.charge.domain.batchprocmng.autotransmng;

public class NBlpy00EdiAgreDataVO {
	
	/**
	 * EDI 신청 일련번호
	 */
	private String ediApplSeqNo;

	/**
	 * 동의자료유무
	 */
	private String agreDataYn;
	
	/**
	 * 동의자료구분
	 */
	private String agreDataCl;
	
	/**
	 * 동의자료 파일경로
	 */
	private String agreDataFilePath;
	
	/**
	 * 동의자료 파일명
	 */
	private String agreDataFileNm;
	
	/**
	 * 동의자료 파일내용
	 */
	private byte[] agreDataFileCt;
	
	/**
	 * 지로번호
	 */
	private String giroNo;
	
	/**
	 * 총건수
	 */
	private int totAgreDataCnt;

	private String agreFileUuid;
	
	public String getEdiApplSeqNo() {
		return ediApplSeqNo;
	}

	public void setEdiApplSeqNo(String ediApplSeqNo) {
		this.ediApplSeqNo = ediApplSeqNo;
	}

	public String getAgreDataYn() {
		return agreDataYn;
	}

	public void setAgreDataYn(String agreDataYn) {
		this.agreDataYn = agreDataYn;
	}

	public String getAgreDataCl() {
		return agreDataCl;
	}

	public void setAgreDataCl(String agreDataCl) {
		this.agreDataCl = agreDataCl;
	}

	public String getAgreDataFilePath() {
		return agreDataFilePath;
	}

	public void setAgreDataFilePath(String agreDataFilePath) {
		this.agreDataFilePath = agreDataFilePath;
	}

	public String getAgreDataFileNm() {
		return agreDataFileNm;
	}

	public void setAgreDataFileNm(String agreDataFileNm) {
		this.agreDataFileNm = agreDataFileNm;
	}

	public byte[] getAgreDataFileCt() {
		return agreDataFileCt;
	}

	public void setAgreDataFileCt(byte[] agreDataFileCt) {
		this.agreDataFileCt = agreDataFileCt;
	}

	public String getGiroNo() {
		return giroNo;
	}

	public void setGiroNo(String giroNo) {
		this.giroNo = giroNo;
	}

	public int getTotAgreDataCnt() {
		return totAgreDataCnt;
	}

	public void setTotAgreDataCnt(int totAgreDataCnt) {
		this.totAgreDataCnt = totAgreDataCnt;
	}

	public String getAgreFileUuid() {
		return agreFileUuid;
	}

	public void setAgreFileUuid(String agreFileUuid) {
		this.agreFileUuid = agreFileUuid;
	}

}
