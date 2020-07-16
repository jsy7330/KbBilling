package com.ntels.ccbs.charge.service.payment.payment;

import java.util.List;

import com.ntels.ccbs.charge.domain.payment.payment.ReceiptCancelVO;

public interface ReceiptCancelService {
	public int rcptListCnt(ReceiptCancelVO receiptCancel);
	
	public List<ReceiptCancelVO> rcptList(ReceiptCancelVO receiptCancel, int page, int perPage);
	
	public int receiptCancelResultListCount(ReceiptCancelVO receiptCancel);
	
	public List<ReceiptCancelVO> receiptCancelResultList(ReceiptCancelVO receiptCancel, int page, int perPage);
	
	/**
	 * 대체신청, 환불신청 내역이 있는지 확인한다-수납취소 가능여부 CHECK
	 * @param pymSeqNo
	 * @return
	 */
	public int getReceiptCancelAbleCheck(String pymSeqNo);
	
	/**
	 * 수납취소를 처리한다.
	 * @param pymSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	public int processReceiptCancelMain(String pymSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception;
}
