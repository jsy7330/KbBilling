package com.ntels.ccbs.charge.domain.charge.batch;

import java.io.Serializable;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class BatchWorkMapVO extends BatchCommonVO implements Serializable, CommonVO {

	/**
	 * 배치 프로그램이 묶여서 실행되는 단위의 그룹ID
	 */
	private String batGrpId;

	/**
	 * 프로그램ID
	 */
	private String batPgmId;

	/**
	 * 그룹에 연결된 요소의 처리 순서
	 */
	private int wrkProcOrd;

	private String pgmNm;

	public String getBatGrpId() {
		return batGrpId == null ? "" : batGrpId.trim();
	}

	public String getBatPgmId() {
		return batPgmId == null ? "" : batPgmId.trim();
	}

	public int getWrkProcOrd() {
		return wrkProcOrd;
	}

	public void setBatGrpId(String batGrpId) {
		this.batGrpId = batGrpId;
	}

	public void setBatPgmId(String batPgmId) {
		this.batPgmId = batPgmId;
	}

	public void setWrkProcOrd(int wrkProcOrd) {
		this.wrkProcOrd = wrkProcOrd;
	}

	public String getPgmNm() {
		return pgmNm == null ? "" : pgmNm.trim();
	}

	public void setPgmNm(String pgmNm) {
		this.pgmNm = pgmNm;
	}

}
