package com.ntels.ccbs.charge.service.batchprocmng.assrtransmng;

public interface AssrTransService {
	
	/**
	 * 보증금 대체 적용을 처리한다.
	 * @param transApplNo, replTp, procResn, pgmId, workId
	 * @return
	 */
	int processAssrTrans(String transApplNo, String replTp, String procResn, String pgmId, String workId) throws Exception;

}
