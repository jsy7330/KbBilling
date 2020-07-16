package com.ntels.ccbs.charge.domain.batchprocmng.refundmng;

public class RefundOrgVO {

	/**
	 * 관리 센터 조직 ID
	 */
	private String mngCnterOrgId;

	/**
	 * 관리 지사 조직 ID
	 */
	private String mngBrOrgId;
	
	/**
	 * 보증금 수납번호
	 */
	private String assrPymSeqNo;

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

	public String getAssrPymSeqNo() {
		return assrPymSeqNo;
	}

	public void setAssrPymSeqNo(String assrPymSeqNo) {
		this.assrPymSeqNo = assrPymSeqNo;
	}

}
