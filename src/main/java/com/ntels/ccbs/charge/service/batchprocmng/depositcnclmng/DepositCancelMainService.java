package com.ntels.ccbs.charge.service.batchprocmng.depositcnclmng;

public interface DepositCancelMainService {
	
	/**
	 * 입금취소 가능여부를 CHECK 한다.
	 * @param dpstSeqNo
	 * @return
	 */
	int getDepositCancelAbleCheck(String dpstSeqNo);
	
	/**
	 * 입급취소를 처리한다.
	 * @param dpstSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	public int processDepositCancelMain(String dpstSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception;

}
