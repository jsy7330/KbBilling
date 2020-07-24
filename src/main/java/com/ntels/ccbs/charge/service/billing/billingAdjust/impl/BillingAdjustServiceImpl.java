package com.ntels.ccbs.charge.service.billing.billingAdjust.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.mapper.billing.billingAdjust.BillingAdjustMapper;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingAdjustService;

@Service
public class BillingAdjustServiceImpl implements BillingAdjustService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	
	@Autowired
	private BillingAdjustMapper billingAdjustMapper;
	
	@Override
	public List<BillingAdjustVO> getAdjTgtList(BillingAdjustVO billingAdjust) {
		
		List<BillingAdjustVO> adjTgtList = new ArrayList<BillingAdjustVO>();
		/*
		 * 청구전, 청구후 요금조정 대상 내역 분기
		 */
		System.out.println("//TEST//");
		System.out.println(billingAdjust.getBillSeqNo()+"//"+billingAdjust.getSoId());
		System.out.println("///////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////////////////////////////");
		
		if("1".equals(billingAdjust.getAdjPt())){
			adjTgtList = billingAdjustMapper.getBeforeAdjTgtList(billingAdjust);
		}
		if("2".equals(billingAdjust.getAdjPt())){
			adjTgtList = billingAdjustMapper.getAfterAdjTgtList(billingAdjust);
			
//			if(수정해야함){
//				throw new ServiceException("MSG.M09.MSG00021");
//			}
		}
		
		return adjTgtList;
	}
	
	@Override
	public List<BillingAdjustVO> getBillClsInfo(BillingAdjustVO billingAdjust) {
		return billingAdjustMapper.getBillClsInfo(billingAdjust);
	}
}
