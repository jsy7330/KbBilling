package com.ntels.ccbs.charge.service.batchprocmng.cardpaymentmng.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.cardpaymentmng.CardWtdrawReqRsltVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.cardpaymentmng.CardPaymentMapper;
import com.ntels.ccbs.charge.service.batchprocmng.cardpaymentmng.CardPaymentService;
import com.ntels.ccbs.charge.service.batchprocmng.paymentmng.PaymentService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.StringUtil;

@Service
public class CardPaymentServiceImpl implements CardPaymentService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CardPaymentMapper cardPaymentMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private PaymentService paymentService;
	
	/**
	 * 카드 개별 결제를 처리한다.
	 * @param eachDpstSeq, inptMenuId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processCardPayment(String wtdrawReqNo, String trnsDt, String inptMenuId, String workId, String pymAcntIdP) throws Exception {
		int resultFlag = 1;
		
		// 카드 개별 결제를 처리한다.
		logger.debug("====================================");
		logger.debug("  processCardPayment Start...  ");
		logger.debug("====================================");
				
		CardWtdrawReqRsltVO cardWtdrawInfo = new CardWtdrawReqRsltVO();
		
		cardWtdrawInfo = cardPaymentMapper.getCardWtdrawSingleDeposit(wtdrawReqNo, trnsDt);
		
		String pymAcntId = cardWtdrawInfo.getPymAcntId();
		//jes_20180307
		if(StringUtil.isEmpty(pymAcntId)) {
			pymAcntId = pymAcntIdP;		
		}		
		
		String dpstSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_DPST_NO, 10);
			
		// 수납금액 CHECK
		double payAmt = cardWtdrawInfo.getPayAmt();
		if(payAmt > 0) {
			// 입금내역 등록을 위한 값 할당
			DepositVO depositInfo = new DepositVO();
			depositInfo = setCardSingleDepositInfo(dpstSeqNo, cardWtdrawInfo, inptMenuId, workId);
			
			// 입금/수납 처리
			int rcptCnt = paymentService.processReceipt("A", pymAcntId, dpstSeqNo, depositInfo, inptMenuId, workId);
			if(rcptCnt == -1) {
				resultFlag = -1;
				throw new Exception("FAIL PROCESS RECEIPT");
			} else {
				// 수납 처리 결과 반영
				cardWtdrawInfo.setWtdrawReqProcStat("P");
				int updCnt = cardPaymentMapper.updateCardWtdrawRcptRslt(cardWtdrawInfo);
				if(updCnt <= 0) {
					resultFlag = -1;
					throw new Exception("FAIL UPDATE TBLPY_CARD_WTDRAW_REQ_RSLT");
				}
			}
		}
				
		logger.debug("====================================");
		logger.debug("  processCardPayment End...  ");
		logger.debug("====================================");
				
		return resultFlag;
	}
	
	/**
	 * 카드 건별 결제 입금 내역 값 할당
	 * @param dpstSeqNo, cardWtdrawReqRsltVO, inptMenuId, workId
	 * @return 
	 */
	private DepositVO setCardSingleDepositInfo(String dpstSeqNo, CardWtdrawReqRsltVO cardWtdrawReqRsltVO, String inptMenuId, String workId) {
		DepositVO depositInfo = new DepositVO();
		
		depositInfo.setDpstSeqNo(dpstSeqNo);
		depositInfo.setDpstProcCnterCd(cardWtdrawReqRsltVO.getDpstProcCnterCd());
		depositInfo.setDpstProcCnterBnkbno(cardWtdrawReqRsltVO.getDpstProcCnterBnkbno());
		depositInfo.setSoId("00");
		depositInfo.setPymAcntId(cardWtdrawReqRsltVO.getPymAcntId());
		depositInfo.setBillSeqNo(cardWtdrawReqRsltVO.getBillSeqNo());
		depositInfo.setDpstDt(cardWtdrawReqRsltVO.getCardConfDate().substring(0, 8));
		depositInfo.setTrnsDt(cardWtdrawReqRsltVO.getTrnsDt());
		depositInfo.setDpstProcDt(DateUtil.getDateStringYYYYMMDD(0));
		depositInfo.setDpstCl("04");
		depositInfo.setDpstTp("04");
		depositInfo.setCustId(cardWtdrawReqRsltVO.getCustId());
		depositInfo.setDpstTpSeqNo(cardWtdrawReqRsltVO.getWtdrawReqNo());
		depositInfo.setPayCorpTp("02");
		depositInfo.setPayCorpCd(cardWtdrawReqRsltVO.getCardCorpCd());
		depositInfo.setAcntCardNo(cardWtdrawReqRsltVO.getEncptCardNo());
		depositInfo.setDpstAmt(cardWtdrawReqRsltVO.getPayAmt());
		depositInfo.setFeeAmt(cardWtdrawReqRsltVO.getFeeAmt());
		depositInfo.setPayProcYn("N");
		depositInfo.setCnclYn("N");
		depositInfo.setPayCnclYn("N");
		depositInfo.setRcptEmpId(workId);
		depositInfo.setDpststdrErpSndYn("N");
		depositInfo.setTrnsstdrErpSndYn("N");
		depositInfo.setInptMenuId(inptMenuId);
		depositInfo.setRegrId(workId);
		depositInfo.setRegDate(DateUtil.sysdate());
		
		return depositInfo;
	}
		
}
