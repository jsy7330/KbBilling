package com.ntels.ccbs.charge.mapper.batchprocmng.assrtransmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommBalVO;

@Component
public interface AssrTransMapper {

	/**
	 * 납부계정, 보증금 발생번호 조건에 따른 보증금 잔액 리스트를 조회한다.
	 * @param pymAcntId, assrApplTgtNo
	 * @return List<TransCommBalVO>
	 */
	List<TransCommBalVO> getAssrTransBalList(@Param("pymAcntId") String pymAcntId, @Param("assrApplTgtNo") String assrApplTgtNo);

	List<TransCommBalVO> getAssrTransBalListRetry(@Param("pymAcntId") String pymAcntId, @Param("assrApplTgtNo") String assrApplTgtNo);

	/**
	 * 보증 대체 이력을 생성한다.
	 * @param assrTransHistVO
	 * @return
	 */
	int insertAssrTransHist(@Param("assrTransHistVO") AssrTransHistVO assrTransHistVO);
	
	/**
	 * 보증 발생내역에 대체금액, 잔액을 반영한다.
	 * @param assrOccVO
	 * @return
	 */
	int updateAssrOccBalAmt(@Param("assrOccVO") AssrOccVO assrOccVO);
	
	/**
	 * 보증금 수납취소 대상을 조회한다.
	 * @param pymSeqNo
	 * @return BillListVO
	 */
	BillListVO getCnclAssrRcptInfo(@Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 보증 대체 금액만큼 보증금 차감 수납내역을 생성한다.
	 * @param receiptVO
	 * @return
	 */
	int insertAssrMinusReceipt(@Param("receiptVO") ReceiptVO receiptVO);	

}
