package com.ntels.ccbs.charge.service.billing.billing.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billing.BillingInfoItemVO;
import com.ntels.ccbs.charge.mapper.billing.billing.BillingInfoItemMapper;
import com.ntels.ccbs.charge.service.billing.billing.BillingInfoItemService;

@Service
public class BillingInfoItemServiceImpl implements BillingInfoItemService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private BillingInfoItemMapper billingInfoItemMapper;

	@Override
	public List<BillingInfoItemVO> billingInfoItemListAction(BillingInfoItemVO billingInfoItemVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return billingInfoItemMapper.billingInfoItemListAction(billingInfoItemVO, start, end);
	}
	
	@Override
	public int billingInfoItemListCount(BillingInfoItemVO billingInfoItemVO) {
		return billingInfoItemMapper.billingInfoItemListCount(billingInfoItemVO);
	}
}
