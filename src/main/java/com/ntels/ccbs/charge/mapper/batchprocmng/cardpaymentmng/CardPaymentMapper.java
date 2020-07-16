package com.ntels.ccbs.charge.mapper.batchprocmng.cardpaymentmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.cardpaymentmng.CardWtdrawReqRsltVO;

@Component
public interface CardPaymentMapper {

	/**
	 * 카드 개별 입금/수납 대상 리스트를 조회한다.
	 * @param
	 * @return CardWtdrawReqRsltVO
	 */
	CardWtdrawReqRsltVO getCardWtdrawSingleDeposit(@Param("wtdrawReqNo") String wtdrawReqNo, @Param("trnsDt") String trnsDt);

	/**
	 * 카드 결제 요청 테이블에 입금/수납 처리 상태를 UPDATE 한다.
	 * @param nBlpy00CardWtdraw
	 * @return
	 */
	int updateCardWtdrawRcptRslt(@Param("cardWtdrawReqRsltVO") CardWtdrawReqRsltVO cardWtdrawReqRsltVO);
	
	/**
	 * 카드 개별 입금/수납 취소 리스트를 조회한다.
	 * @param
	 * @return List<NBlpy00CardWtdrawVO>
	 */
	CardWtdrawReqRsltVO getCardSingleDepositCncl(@Param("wtdrawReqNo") String wtdrawReqNo);
	

	/**
	 * 카드 개별 입금/수납 취소 리스트를 조회한다.
	 * @param
	 * @return List<NBlpy00CardWtdrawVO>
	 */
	List<CardWtdrawReqRsltVO> getCardSingleRcptList(@Param("dpstSeqNo") String dpstSeqNo);
	
}
