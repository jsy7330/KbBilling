package com.ntels.ccbs.charge.service.batchprocmng.depositcnclmng;

public interface DepositCancelService {
	
	/**
	 * 입금 취소를 처리한다.
	 * @param dpstSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	int processDepositCancel(String dpstSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception;

}
