package com.ntels.ccbs.charge.service.billing.billingAdjust.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustApplyVO;
import com.ntels.ccbs.charge.mapper.billing.billingAdjust.BillingAfterAdjustApplyMapper;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingAfterAdjustApplyService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class BillingAfterAdjustApplyServiceImpl implements BillingAfterAdjustApplyService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SequenceService sequenceService;

	/** AttributeMapper Autowired. */
	@Autowired
	private BillingAfterAdjustApplyMapper billingAfterAdjustApplyMapper;
	
	@Override
	public List<BillingAfterAdjustApplyVO> getAfterAdjTgtList(BillingAfterAdjustApplyVO billingAfterAdjustApply) {
		return billingAfterAdjustApplyMapper.getAfterAdjTgtList(billingAfterAdjustApply);
	}
}
