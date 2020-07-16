package com.ntels.ccbs.charge.service.batchprocmng.billupdmng;

public interface BillMastService {

	/**
	 * 청구마스터, 월계약미납현황 테이블 변경 처리한다.
	 * @param soId, billYymm, billSeqNo, pymAcntId, workId-작업자ID
	 * @return
	 */
	void billMastUpdate(String soId, String billYymm, String billSeqNo, String pymAcntId, String workId) throws Exception;

}
