package com.ntels.ccbs.charge.service.batchprocmng.receiptcnclmng;

public interface PaymentCancelService {
	
	/**
	 * 수납 취소를 처리한다.
	 * @param pymSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	int processReceiptCancel(String pymSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception;

}
