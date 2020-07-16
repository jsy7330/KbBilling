package com.ntels.ccbs.charge.service.billing.billingAdjust.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.mapper.billing.billingAdjust.BillingBeforeAdjustMapper;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingBeforeAdjustService;

@Service
public class BillingBeforeAdjustServiceImpl implements BillingBeforeAdjustService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private BillingBeforeAdjustMapper billingBeforeAdjustMapper;
	
	@Override
	public List<BillingAdjustVO> getPymList(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.getPymList(billingAdjust);
	}
	
	@Override
	public List<BillingAdjustVO> getPymBillList(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.getPymBillList(billingAdjust);
	}
}
