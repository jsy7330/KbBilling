package com.ntels.ccbs.charge.service.payment.payment;

import java.util.List;

import com.ntels.ccbs.charge.domain.payment.payment.ReceiptDetailVO;

public interface ReceiptDetailService {
	public List<ReceiptDetailVO> receiptStatisticsList(ReceiptDetailVO receiptDetail);
	
	public List<ReceiptDetailVO> recList(ReceiptDetailVO receiptDetail);
	
	public List<ReceiptDetailVO> recDtlList(ReceiptDetailVO receiptDetail);
}
