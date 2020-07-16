package com.ntels.ccbs.charge.mapper.batchprocmng.prepaytransmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommApplVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommBalVO;

@Component
public interface PrepayTransMapper {
	
	/**
	 * 대체 대상을 조회한다.
	 * @param stransApplNo, stransApplCl
	 * @return List<TransCommApplVO>
	 */
	List<TransCommApplVO> getTransApplList(@Param("stransApplNo") String stransApplNo, @Param("stransApplCl") String stransApplCl);
	
	/**
	 * 납부계정, 선수금 발생번호 조건에 따른 선수금 잔액 리스트를 조회한다.
	 * @param pymAcntId, prepayApplTgtNo
	 * @return List<TransCommBalVO>
	 */
	List<TransCommBalVO> getPrepayTransBalList(@Param("pymAcntId") String pymAcntId, @Param("prepayApplTgtNo") String prepayApplTgtNo);
	
	/**
	 * 미수납 처리된 청구내역 데이터를 조회한다.
	 * @param spymAcntId, sbillSeqNo, suseYymm, ssvcCmpsId, sprodCmpsId, schrgItmCd, sTransSubTp
	 * @return List<BillListVO>
	 */
	List<BillListVO> getUnRcptBillList(@Param("spymAcntId") String spymAcntId, @Param("sbillSeqNo") String sbillSeqNo, @Param("suseYymm") String suseYymm,
									@Param("ssvcCmpsId") String ssvcCmpsId, @Param("sprodCmpsId") String sprodCmpsId, @Param("schrgItmCd") String schrgItmCd, @Param("sTransSubTp") String sTransSubTp);
	
	/**
	 * 선수금 대체 내역에 등록한다.
	 * @param prepayTransHistVO
	 * @return
	 */
	int insertPrepayTransHist(@Param("prepayTransHistVO") PrepayTransHistVO prepayTransHistVO);
	
	/**
	 * 선수금 발생 내역의 선수반영금액, 잔액등을 변경한다.
	 * @param prepayOccVO
	 * @return
	 */
	int updatePrepayOccBalAmt(@Param("prepayOccVO") PrepayOccVO prepayOccVO);
	
	/**
	 * 선수금 대체로 인해 발생한 보증금 내역을 등록한다.
	 * @param assrOccVO
	 * @return
	 */
	int insertPrepayTransAssrOcc(@Param("assrOccVO") AssrOccVO assrOccVO);
	
	/**
	 * 수납 내역에 등록한다.
	 * @param receiptVO
	 * @return
	 */
	int insertTransReceipt(@Param("receiptVO") ReceiptVO receiptVO);
	
	/**
	 * 대체 신청 승인정보를 반영한다.
	 * @param transCommApplVO
	 * @return
	 */
	int updateTransAppl(@Param("transCommApplVO") TransCommApplVO transCommApplVO);

}
