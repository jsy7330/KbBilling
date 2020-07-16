package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.payment.payment.ReceiptCancelVO;
import com.ntels.ccbs.charge.mapper.payment.payment.ReceiptCancelMapper;
import com.ntels.ccbs.charge.service.batchprocmng.receiptcnclmng.PaymentCancelService;
import com.ntels.ccbs.charge.service.payment.payment.ReceiptCancelService;

@Service
public class ReceiptCancelServiceImpl implements ReceiptCancelService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private ReceiptCancelMapper receiptCancelMapper;
	
	@Autowired
	private PaymentCancelService paymentCancelService;

	@Override
	public int rcptListCnt(ReceiptCancelVO receiptCancel) {
		return receiptCancelMapper.rcptListCnt(receiptCancel);
	}

	@Override
	public List<ReceiptCancelVO> rcptList(ReceiptCancelVO receiptCancel, int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return receiptCancelMapper.rcptList(receiptCancel, start, end);
	}

	@Override
	public int receiptCancelResultListCount(ReceiptCancelVO receiptCancel) {
		return receiptCancelMapper.receiptCancelResultListCount(receiptCancel);
	}

	@Override
	public List<ReceiptCancelVO> receiptCancelResultList(ReceiptCancelVO receiptCancel, int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return receiptCancelMapper.receiptCancelResultList(receiptCancel, start, end);
	}

	/**
	 * 대체신청, 환불신청 내역이 있는지 확인한다-수납취소 가능여부 CHECK
	 * 
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public int getReceiptCancelAbleCheck(String pymSeqNo) {
		int returnFlag = 1;

		if (receiptCancelMapper.getTransApplCheckCnt(pymSeqNo) > 0) {
			returnFlag = -1;
		}

		return returnFlag;
	}

	/**
	 * 수납취소를 처리한다.
	 * 
	 * @param pymSeqNo,
	 *            cnclResnTxt, inptMenuId, workId
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
