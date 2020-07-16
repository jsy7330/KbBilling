package com.ntels.ccbs.charge.service.batchprocmng.cardpaymentmng;

public interface CardPaymentService {

	/**
	 * 카드 개별 입금을 처리한다.
	 * @param wtdrawReqNo, trnsDt, inptMenuId, workId
	 * @return
	 */
	public int processCardPayment(String wtdrawReqNo, String trnsDt, String inptMenuId, String workId, String pymAcntId) throws Exception;
	
}
