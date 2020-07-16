package com.ntels.ccbs.charge.mapper.payment.payment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.payment.payment.ReceiptCancelVO;

public interface ReceiptCancelMapper {

	/**
	 * Count.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attribute
	 * @return the list
	 * 
	 * 설명 : 수납count
	 */
	
	int rcptListCnt(@Param(value ="receiptCancel") ReceiptCancelVO receiptCancel);

	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attribute
	 * @return the list
	 * 
	 * 설명 : 수납리스트
	 */
	
	List<ReceiptCancelVO> rcptList(
			@Param(value ="receiptCancel") ReceiptCancelVO receiptCancel
		  , @Param(value="start")int start
		  , @Param(value="end") int end
	);
	
	int receiptCancelResultListCount(@Param(value ="receiptCancel") ReceiptCancelVO receiptCancel);
	
	List<ReceiptCancelVO> receiptCancelResultList(
			@Param(value ="receiptCancel") ReceiptCancelVO receiptCancel
		  , @Param(value="start")int start
		  , @Param(value="end") int end
	);

	/**
	 * 대체신청, 환불신청 내역이 있는지 확인한다-수납취소 가능여부 CHECK
	 * @param pymSeqNo
	 * @return
	 */
	int getTransApplCheckCnt(@Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 수납취소를 처리한다.
	 * @param pymSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	public int processReceiptCancelMain(String pymSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception;

}
