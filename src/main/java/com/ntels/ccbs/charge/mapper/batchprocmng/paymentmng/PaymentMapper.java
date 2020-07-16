package com.ntels.ccbs.charge.mapper.batchprocmng.paymentmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.EachDepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;

@Component
public interface PaymentMapper {

	/**
	 * 건별입금내역에 등록된 데이타를 조회한다.
	 * @param eachDpstSeq
	 * @return NBlpy00EachDpstVO
	 */
	EachDepositVO getEachDeposit(@Param("seachDpstSeq") String seachDpstSeq);
	
	/**
	 * 입금 내역에 등록한다.
	 * @param depositVO
	 * @return
	 */
	int insertDeposit(@Param("depositVO") DepositVO depositVO);
	
	/**
	 * 납부계정 ID 가 존재하는지 CHECK 한다.
	 * @param pymAcntId
	 * @return
	 */
	int getPymAcntCnt(@Param("pymAcntId") String pymAcntId);
	
	/**
	 * 입금내역에 등록된 데이타를 조회한다.
	 * @param sdpstSeqNo
	 * @return DepositVO
	 */
	DepositVO getDeposit(@Param("sdpstSeqNo") String sdpstSeqNo);
	
	/**
	 * 불명금 발생 내역에 등록한다.
	 * @param ambgOccVO
	 * @return
	 */
	int insertAmbgOcc(@Param("ambgOccVO") AmbgOccVO ambgOccVO);
	
	/**
	 * 청구내역 데이터를 조회한다.
	 * @param spymAcntId, sbillSeqNo, sctrtId
	 * @return List<BillListVO>
	 */
	List<BillListVO> getBillDataList(@Param("spymAcntId") String spymAcntId, @Param("sbillSeqNo") String sbillSeqNo, @Param("sctrtId") String sctrtId);
	
	/**
	 * 보증금 발생 내역에 등록한다.
	 * @param assrOccVO
	 * @return
	 */
	int insertAssrOcc(@Param("assrOccVO") AssrOccVO assrOccVO);
	
	/**
	 * 수납 상세 내역에 등록한다.
	 * @param receiptDtlVO
	 * @return
	 */
	int insertReceiptDtl(@Param("receiptDtlVO") ReceiptDtlVO receiptDtlVO);
	
	/**
	 * 청구내역의 수납금액, 청구금액, 완납여부 컬럼을 변경한다.
	 * @param billListVO
	 * @return
	 */
	int updateBillData(@Param("billListVO") BillListVO billListVO);
	
	/**
	 * 수납 내역에 등록한다.
	 * @param receiptVO
	 * @return
	 */
	int insertReceipt(@Param("receiptVO") ReceiptVO receiptVO);

	/**
	 * 선수금 발생 내역에 등록한다.
	 * @param prepayOccVO
	 * @return
	 */
	int insertPrepayOcc(@Param("prepayOccVO") PrepayOccVO prepayOccVO);
	
	/**
	 * 수납내역의 선수금 발생번호를 UPDATE 한다.
	 * @param receiptVO
	 * @return
	 */
	int updateReceipt(@Param("receiptVO") ReceiptVO receiptVO);
	
	/**
	 * 입금내역의 수납처리 여부 컬럼을 변경한다.
	 * @param depositVO
	 * @return
	 */
	int updateDeposit(@Param("depositVO") DepositVO depositVO);
	
	/**
	 * 입금내역에 등록된 데이타에 대해서 건별입금내역의 입금처리일자를 수정한다.
	 * @param nBlpy00EachDpstVO
	 * @return
	 */
	int updateEachDeposit(@Param("eachDepositVO") EachDepositVO eachDepositVO);
	
}
