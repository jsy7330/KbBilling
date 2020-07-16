package com.ntels.ccbs.charge.mapper.payment.payment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.payment.payment.ReceiptDetailVO;

public interface ReceiptDetailMapper {

	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attribute
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	
	List<ReceiptDetailVO> receiptStatisticsList(
			@Param(value ="receiptDetail") ReceiptDetailVO receiptDetail
	);
	
	List<ReceiptDetailVO> recList(
			@Param(value ="receiptDetail") ReceiptDetailVO receiptDetail
	);
	
	List<ReceiptDetailVO> recDtlList(
			@Param(value ="receiptDetail") ReceiptDetailVO receiptDetail
	);
}
