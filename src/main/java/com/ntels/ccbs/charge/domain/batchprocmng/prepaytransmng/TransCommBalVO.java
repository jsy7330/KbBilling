package com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng;

public class TransCommBalVO {

	/**
	 * 선수 발생 번호/불명 발생번호/보증 발생 번호
	 */
	private String transTgtOccSeqNo;

	/**
	 * 선수금잔액
	 */
	private double transBal;
	
	/**
	 * 보증금 수납번호
	 */
	private String assrPymSeqNo;
	
	/**
	 * 입금번호
	 */
	private String dpstSeqNo;

	public String getTransTgtOccSeqNo() {
		return transTgtOccSeqNo;
	}

	public void setTransTgtOccSeqNo(String transTgtOccSeqNo) {
		this.transTgtOccSeqNo = transTgtOccSeqNo;
	}

	public double getTransBal() {
		return transBal;
	}

	public void setTransBal(double transBal) {
		this.transBal = transBal;
	}

	public String getAssrPymSeqNo() {
		return assrPymSeqNo;
	}

	public void setAssrPymSeqNo(String assrPymSeqNo) {
		this.assrPymSeqNo = assrPymSeqNo;
	}

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}
	
}
