package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.payment.payment.ReceiptDetailVO;
import com.ntels.ccbs.charge.mapper.payment.payment.ReceiptDetailMapper;
import com.ntels.ccbs.charge.service.payment.payment.ReceiptDetailService;

@Service
public class ReceiptDetailServiceImpl implements ReceiptDetailService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private ReceiptDetailMapper receipDetailMapper;
	
	@Override
	public List<ReceiptDetailVO> receiptStatisticsList(ReceiptDetailVO receiptDetail) {
		return receipDetailMapper.receiptStatisticsList(receiptDetail);
	}
	
	@Override
	public List<ReceiptDetailVO> recList(ReceiptDetailVO receiptDetail) {
		
		//System.out.println("SoId :" + receiptDetail.getSoId());
		//System.out.println("UsrId :" + receiptDetail.getUsrId());
		//System.out.println("PymAcntId :" + receiptDetail.getPymAcntId());
		//System.out.println("PymDtTp :" + receiptDetail.getPymDtTp());
		//System.out.println("StrtDt :" + receiptDetail.getStrtDt());
		//System.out.println("EndDt :" + receiptDetail.getEndDt());
		//System.out.println("DpstCl :" + receiptDetail.getDpstCl());
		//System.out.println("CnclYn :" + receiptDetail.getCnclYn());
		
		return receipDetailMapper.recList(receiptDetail);
	}
	
	@Override
	public List<ReceiptDetailVO> recDtlList(ReceiptDetailVO receiptDetail) {
		return receipDetailMapper.recDtlList(receiptDetail);
	}
}
