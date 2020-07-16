package com.ntels.ccbs.charge.service.batchprocmng.receiptcnclmng;

public interface ReceiptCancelMainService {
	
	/**
	 * 대체신청, 환불신청 내역이 있는지 확인한다-수납취소 가능여부 CHECK
	 * @param pymSeqNo
	 * @return
	 */
	int getReceiptCancelAbleCheck(String pymSeqNo);
	
	/**
	 * 수납취소를 처리한다.
	 * @param pymSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	public int processReceiptCancelMain(String pymSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception;

}
