package com.ntels.ccbs.charge.mapper.batchprocmng.depositcnclmng;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.depositcnclmng.DepositCancelVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;

@Component
public interface DepositCancelMapper {

	/**
	 * 수납이 처리된 입금내역인지 CHECK 한다.
	 * @param dpstSeqNo
	 * @return
	 */
	int getReceiptCheckCnt(@Param("dpstSeqNo") String dpstSeqNo);

	/**
	 * 수납이 처리된 입금내역인지 CHECK 한다.
	 * @param dpstSeqNo
	 * @return
	 */
	int getReceiptCheckCntSub(@Param("dpstSeqNo") String dpstSeqNo);

	/**
	 * 불명금, 보증금, 선수금 대체건이 있는 입금내역인지 CHECK 한다.
	 * @param pymAcntId
	 * @return
	 */
	Double getTransCheckAmt(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 입금 취소 대상을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	DepositVO getDepositCnclList(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * ERP 전송이 처리된 입금내역인지 CHECK 한다.
	 * @param trnsDt
	 * @return
	 */
	int getDepositErpSndCheck(@Param("trnsDt") String trnsDt);
	
	/**
	 * ERP 전송이 처리된 불명금 내역인지 CHECK 한다.
	 * @param ambgOccSeqNo
	 * @return
	 */
	int getAmbgErpSndCheck(@Param("ambgOccSeqNo") String ambgOccSeqNo);
	
	/**
	 * 불명금 발생내역 취소 정보를 UPDATE 한다.
	 * @param ambgOccVO
	 * @return
	 */
	int updateAmbgOccCncl(@Param("ambgOccVO") AmbgOccVO ambgOccVO);
	
	/**
	 * 보증금 발생내역 취소 정보를 UPDATE 한다.-수납취소에서 처리하는 것으로 변경
	 * @param assrOccVO
	 * @return
	 */
	/*int updateAssrOccCncl(@Param("assrOccVO") AssrOccVO assrOccVO);*/
	
	/**
	 * 선수금 발생내역 취소 정보를 UPDATE 한다.
	 * @param nBlpy00PrepayOccVO
	 * @return
	 */
	int updatePrepayOccCncl(@Param("prepayOccVO") PrepayOccVO prepayOccVO);
	
	/**
	 * 입금 취소내역을 불명금으로 발생 시킨다.
	 * @param ambgOccVO
	 * @return
	 */
	int insertAmbgOccForDpstCncl(@Param("ambgOccVO") AmbgOccVO ambgOccVO);
	
	/**
	 * 입금 취소내역에 등록한다.
	 * @param depositCancelVO
	 * @return
	 */
	int insertDepositCncl(@Param("depositCancelVO") DepositCancelVO depositCancelVO);

	/**
	 * 입금내역 취소 정보를 UPDATE 한다.
	 * @param nBlpy00DpstVO
	 * @return
	 */
	int updateDepositCncl(@Param("depositVO") DepositVO depositVO);
	
}
