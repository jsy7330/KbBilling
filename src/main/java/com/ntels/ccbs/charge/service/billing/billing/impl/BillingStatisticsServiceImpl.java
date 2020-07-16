package com.ntels.ccbs.charge.service.billing.billing.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billing.BillingStatisticsVO;
import com.ntels.ccbs.charge.mapper.billing.billing.BillingStatisticsMapper;
import com.ntels.ccbs.charge.service.billing.billing.BillingStatisticsService;

@Service
public class BillingStatisticsServiceImpl implements BillingStatisticsService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private BillingStatisticsMapper billingStatisticsMapper;

	@Override
	public List<BillingStatisticsVO> billDtlList(BillingStatisticsVO billingStatistics) {
		return billingStatisticsMapper.billDtlList(billingStatistics);
	}

	@Override
	public List<BillingStatisticsVO> ctrtDtlList(BillingStatisticsVO billingStatistics) {

		return billingStatisticsMapper.ctrtDtlList(billingStatistics);
	}

	@Override
	public List<BillingStatisticsVO> chrgItmDtlList(BillingStatisticsVO billingStatistics) {
		return billingStatisticsMapper.chrgItmDtlList(billingStatistics);
	}

	@Override
	public int getBillInvoiceCount(BillingStatisticsVO billingStatisticsVO) {
		return billingStatisticsMapper.getBillInvoiceCount(billingStatisticsVO);
	}

	@Override
	public List<BillingStatisticsVO> getBillInvoiceList(BillingStatisticsVO billingStatisticsVO, int page,
			int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return billingStatisticsMapper.getBillInvoiceList(billingStatisticsVO, start, end);
	}

	@Override
	public List<BillingStatisticsVO> getBillDtlList(BillingStatisticsVO billingStatisticsVO) {
		return billingStatisticsMapper.getBillDtlList(billingStatisticsVO);
	}

}
