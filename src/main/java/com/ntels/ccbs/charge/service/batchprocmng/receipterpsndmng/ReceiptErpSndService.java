package com.ntels.ccbs.charge.service.batchprocmng.receipterpsndmng;

public interface ReceiptErpSndService {
	
	/**
	 * 수납 관련 ERP 데이터를 전송한다.
	 * @param dataOccDt, pgmId, workId
	 * @return
	 */
	int processReceiptErpSndData(String dataOccDt, String pgmId, String workId) throws Exception;

}
