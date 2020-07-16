package com.ntels.ccbs.charge.mapper.batchprocmng.receiptcnclmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.receiptcnclmng.PaymentCancelDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.receiptcnclmng.PaymentCancelVO;

@Component
public interface PaymentCancelMapper {

	/**
	 * 대체신청, 환불신청 내역이 있는지 확인한다-수납취소 가능여부 CHECK
	 * @param pymSeqNo
	 * @return
	 */
	int getTransApplCheckCnt(@Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 수납 취소 대상을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	List<ReceiptDtlVO> getReceiptCnclList(@Param("spymSeqNo") String spymSeqNo);
	
	/**
	 * 선수금 대체내역 취소 정보를 UPDATE 한다.
	 * @param prepayTransHistVO
	 * @return
	 */
	int updatePrepayTransCncl(@Param("prepayTransHistVO") PrepayTransHistVO prepayTransHistVO);
	
	/**
	 * 선수금 적용 취소된 금액을 선수금 발생내역에 반영한다.
	 * @param prepayOccVO
	 * @return
	 */
	int updatePrepayOccCnclAmt(@Param("prepayOccVO") PrepayOccVO prepayOccVO);
	
	/**
	 * 불명금 대체내역 취소 정보를 UPDATE 한다.
	 * @param ambgTransHistVO
	 * @return
	 */
	int updateAmbgTransCncl(@Param("ambgTransHistVO") AmbgTransHistVO ambgTransHistVO);
	
	/**
	 * 불명금 적용 취소된 금액을 불명금 발생내역에 반영한다.
	 * @param ambgOccVO
	 * @return
	 */
	int updateAmbgOccCnclAmt(@Param("ambgOccVO") AmbgOccVO ambgOccVO);
	
	/**
	 * 보증금 대체내역 취소 정보를 UPDATE 한다.
	 * @param assrTransHistVO
	 * @return
	 */
	int updateAssrTransCncl(@Param("assrTransHistVO") AssrTransHistVO assrTransHistVO);
	
	/**
	 * 보증금 적용 취소된 금액을 보증금 발생내역에 반영한다.
	 * @param assrOccVO
	 * @return
	 */
	int updateAssrOccCnclAmt(@Param("assrOccVO") AssrOccVO assrOccVO);
	
	/**
	 * 수납 취소 금액을 청구내역에 반영한다.
	 * @param billListVO
	 * @return
	 */
	int updateBillCnclAmt(@Param("billListVO") BillListVO billListVO);
	
	/**
	 * 수납 상세 취소내역에 등록한다.
	 * @param paymentCancelDtlVO
	 * @return
	 */
	int insertReceiptCnclDtl(@Param("paymentCancelDtlVO") PaymentCancelDtlVO paymentCancelDtlVO);
	
	/**
	 * 수납  취소내역에 등록한다.
	 * @param paymentCancelVO
	 * @return
	 */
	int insertReceiptCncl(@Param("paymentCancelVO") PaymentCancelVO paymentCancelVO);
	
	/**
	 * 보증금 발생내역 취소 정보를 UPDATE 한다.-수납취소에서 처리하는 것으로 변경
	 * @param assrOccVO
	 * @return
	 */
	int updateAssrOccCncl(@Param("assrOccVO") AssrOccVO assrOccVO);
	
	/**
	 * 불명금으로 인해 발생한 선수금 내역의 잔액만큼 불명금 잔액을 증가시킨다.
	 * @param prepayOccVO
	 * @return
	 */
	int updateAmbgOccPrepayAmt(@Param("prepayOccVO") PrepayOccVO prepayOccVO);
	
	/**
	 * 수납 취소에 대한 선수금 내역을 등록한다.
	 * @param prepayOccVO
	 * @return
	 */
	int insertPrepayOcc(@Param("prepayOccVO") PrepayOccVO prepayOccVO);
	
	/**
	 * 수납내역에 취소정보를 반영한다.
	 * @param receiptVO
	 * @return
	 */
	int updateReceiptCncl(@Param("receiptVO") ReceiptVO receiptVO);
	
	/**
	 * 입금내역에 취소정보를 반영한다.
	 * @param depositVO
	 * @return
	 */
	int updateDepositCncl(@Param("depositVO") DepositVO depositVO);

}
