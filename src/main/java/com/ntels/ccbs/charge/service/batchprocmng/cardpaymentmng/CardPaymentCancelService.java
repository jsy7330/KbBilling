package com.ntels.ccbs.charge.service.batchprocmng.cardpaymentmng;

public interface CardPaymentCancelService {

	/**
	 * 카드 개별 결제 취소를 처리한다.
	 * @param wtdrawReqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	int processCardPaymentCancel(String wtdrawReqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception;
	
}
