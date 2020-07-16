package com.ntels.ccbs.charge.service.batchprocmng.receiptcnclmng.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.mapper.batchprocmng.receiptcnclmng.PaymentCancelMapper;
import com.ntels.ccbs.charge.service.batchprocmng.receiptcnclmng.PaymentCancelService;
import com.ntels.ccbs.charge.service.batchprocmng.receiptcnclmng.ReceiptCancelMainService;

@Service
public class ReceiptCancelMainServiceImpl implements ReceiptCancelMainService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private PaymentCancelMapper paymentCancelMapper;
	
	@Autowired
	private PaymentCancelService paymentCancelService;
	
	/**
	 * 대체신청, 환불신청 내역이 있는지 확인한다-수납취소 가능여부 CHECK
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public int getReceiptCancelAbleCheck(String pymSeqNo) {
		int returnFlag = 1;
		
		if(paymentCancelMapper.getTransApplCheckCnt(pymSeqNo) > 0) {
			returnFlag = -1;
		}
		
		return returnFlag;
	}
	
	/**
	 * 수납취소를 처리한다.
	 * @param pymSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processReceiptCancelMain(String pymSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception {
		int returnFlag = 1;
		
		logger.debug("========================================");
		logger.debug(" processReceiptCancelMain Start...  ");
		logger.debug("========================================");
		
		returnFlag = paymentCancelService.processReceiptCancel(pymSeqNo, cnclResnTxt, inptMenuId, workId);
		
		logger.debug("========================================");
		logger.debug(" processReceiptCancelMain End...  ");
		logger.debug("========================================");
		
		return returnFlag;
	}
	
}
