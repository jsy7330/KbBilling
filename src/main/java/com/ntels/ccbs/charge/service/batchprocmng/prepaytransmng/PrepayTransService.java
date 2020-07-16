package com.ntels.ccbs.charge.service.batchprocmng.prepaytransmng;

public interface PrepayTransService {
	
	/**
	 * 선수 일반대체 적용을 처리한다.
	 * @param transApplNo, replTp, pgmId, workId
	 * @return
	 */
	int processPrepayTrans(String transApplNo, String replTp, String pgmId, String workId) throws Exception;
	
	/**
	 * 선수 보증금대체 적용을 처리한다.
	 * @param transApplNo, replTp, pgmId, workId
	 * @return
	 */
	int processPrepayAssrTrans(String transApplNo, String replTp, String pgmId, String workId) throws Exception;

}
