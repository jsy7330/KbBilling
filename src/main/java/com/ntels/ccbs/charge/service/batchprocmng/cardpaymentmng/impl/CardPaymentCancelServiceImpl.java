package com.ntels.ccbs.charge.service.batchprocmng.cardpaymentmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.cardpaymentmng.CardWtdrawReqRsltVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.cardpaymentmng.CardPaymentMapper;
import com.ntels.ccbs.charge.service.batchprocmng.cardpaymentmng.CardPaymentCancelService;
import com.ntels.ccbs.charge.service.batchprocmng.depositcnclmng.DepositCancelService;
import com.ntels.ccbs.charge.service.batchprocmng.receiptcnclmng.PaymentCancelService;

@Service
public class CardPaymentCancelServiceImpl implements CardPaymentCancelService {

	@Autowired
	private CardPaymentMapper cardPaymentMapper;
	
	@Autowired
	private PaymentCancelService paymentCancelService;
	
	@Autowired
	private DepositCancelService depositCancelService;
	
	/**
	 * 카드 개별 입금/수납 취소 리스트를 조회한다.
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processCardPaymentCancel(String wtdrawReqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception {
		int resultFlag = 1;
		
		CardWtdrawReqRsltVO cardCancelDeposit = new CardWtdrawReqRsltVO();
		
		cardCancelDeposit = cardPaymentMapper.getCardSingleDepositCncl(wtdrawReqNo);
		
		if(cardCancelDeposit != null) {
			String dpstSeqNo = cardCancelDeposit.getDpstSeqNo();
			
			List<CardWtdrawReqRsltVO> rcptList = new ArrayList<CardWtdrawReqRsltVO>();
			rcptList = cardPaymentMapper.getCardSingleRcptList(dpstSeqNo);
			
			int loopCnt = rcptList.size();
			
			for (int i = 0; i < loopCnt; i++) {
				CardWtdrawReqRsltVO rcptInfo = new CardWtdrawReqRsltVO();
				
				rcptInfo = rcptList.get(i);
			
				String pymSeqNo = rcptInfo.getPymSeqNo();
			
				if(!"".equals(pymSeqNo)) {
					// 수납 취소 처리
					int rcptCnclCnt = paymentCancelService.processReceiptCancel(pymSeqNo, cnclResnTxt, inptMenuId, workId);
					if(rcptCnclCnt == -1) {
						resultFlag = -1;
						throw new Exception("FAIL PROCESS CARD RECEIPT CANCEL");
					}
				}
			}
			
			// 수납 취소 처리 결과 반영
			cardCancelDeposit.setWtdrawReqProcStat("P");
			int updCnt = cardPaymentMapper.updateCardWtdrawRcptRslt(cardCancelDeposit);
			if(updCnt <= 0) {
				resultFlag = -1;
				throw new Exception("FAIL UPDATE TBLPY_CARD_WTDRAW_REQ_RSLT");
			}
			
			// 입금 취소 처리
			if(!"".equals(dpstSeqNo)) {
				int dpstCnclCnt = depositCancelService.processDepositCancel(dpstSeqNo, cnclResnTxt, inptMenuId, workId);
				if(dpstCnclCnt == -1) {
					resultFlag = -1;
					throw new Exception("FAIL PROCESS CARD DEPOSIT CANCEL");
				}
			}
		}
		
		return resultFlag;
	}
		
}