package com.ntels.ccbs.charge.service.batchprocmng.refundmng;

public interface RefundService {
	
	/**
	 * 환불 승인을 처리한다.
	 * @param pymAcntId, rfndSeqNo, procResn, pgmId, workId
	 * @return
	 */
	int processRefundConfirm(String pymAcntId, String rfndSeqNo, String pgmId, String workId) throws Exception;

}
