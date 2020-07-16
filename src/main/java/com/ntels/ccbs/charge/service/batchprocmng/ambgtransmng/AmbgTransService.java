package com.ntels.ccbs.charge.service.batchprocmng.ambgtransmng;

public interface AmbgTransService {
	
	/**
	 * 불명금 대체 적용을 처리한다.
	 * @param transApplNo, replTp, pgmId, workId
	 * @return
	 */
	int processAmbgTrans(String transApplNo, String replTp, String pgmId, String workId) throws Exception;

}
