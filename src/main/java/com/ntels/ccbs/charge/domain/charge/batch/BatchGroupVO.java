package com.ntels.ccbs.charge.domain.charge.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class BatchGroupVO extends BatchCommonVO implements Serializable, CommonVO {

	/**
	 * 배치 프로그램이 묶여서 실행되는 단위의 그룹ID
	 */
	private String batGrpId;

	/**
	 * 그룹명
	 */
	private String batGrpNm;

	/**
	 * BLIV020 01: 빌링작업 11: 계산작업 21: 청구작업 31: 수납작업 41: 미납작업 51: 정산작업
	 */
	private String batGrpTp;

	private String batGrpTpNm;

	/**
	 * BLIV021 01: 정규계산 11: 월작업 21: 일작업 31: 수시작업(재계산) 32: 수시작업(HotBill) 33:
	 * 수시작업(시뮬레이션)
	 */
	private String batProcTp;

	private String batProcTpNm;

	private List<BatchWorkMapVO> batchWorkMapList;

	public String getBatGrpId() {
		return batGrpId;
	}

	public void setBatGrpId(String batGrpId) {
		this.batGrpId = batGrpId;
	}

	public String getBatGrpNm() {
		return batGrpNm == null ? "" : batGrpNm.trim();
	}

	public void setBatGrpNm(String batGrpNm) {
		this.batGrpNm = batGrpNm;
	}

	public String getBatGrpTp() {
		return batGrpTp == null ? "" : batGrpTp.trim();
	}

	public void setBatGrpTp(String batGrpTp) {
		this.batGrpTp = batGrpTp;
	}

	public String getBatGrpTpNm() {
		return batGrpTpNm == null ? "" : batGrpTpNm.trim();
	}

	public void setBatGrpTpNm(String batGrpTpNm) {
		this.batGrpTpNm = batGrpTpNm;
	}

	public String getBatProcTp() {
		return batProcTp == null ? "" : batProcTp.trim();
	}

	public void setBatProcTp(String batProcTp) {
		this.batProcTp = batProcTp;
	}

	public String getBatProcTpNm() {
		return batProcTpNm == null ? "" : batProcTpNm.trim();
	}

	public void setBatProcTpNm(String batProcTpNm) {
		this.batProcTpNm = batProcTpNm;
	}

	public List<BatchWorkMapVO> getBatchWorkMapList() {
		
		if (batchWorkMapList == null) {
			batchWorkMapList = new ArrayList<>();
		}
		
		return batchWorkMapList;
	}

	public void setBatchWorkMapList(List<BatchWorkMapVO> batchWorkMapList) {
		this.batchWorkMapList = batchWorkMapList;
	}

}
