package com.ntels.ccbs.charge.mapper.billing.billing;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.billing.billing.BillingStatisticsVO;

public interface BillingStatisticsMapper {

	/**
	 * List.
	 *
	 * @param page
	 *            the page
	 * @param perPage
	 *            the per page
	 * @param attribute
	 * @return the list
	 * 
	 *         설명 : 속성 목록
	 */

	List<BillingStatisticsVO> billDtlList(@Param(value = "billingStatistics") BillingStatisticsVO billingStatistics);

	List<BillingStatisticsVO> ctrtDtlList(@Param(value = "billingStatistics") BillingStatisticsVO billingStatistics);

	List<BillingStatisticsVO> chrgItmDtlList(@Param(value = "billingStatistics") BillingStatisticsVO billingStatistics);

	int getBillInvoiceCount(@Param(value = "billingStatisticsVO") BillingStatisticsVO billingStatisticsVO);

	List<BillingStatisticsVO> getBillInvoiceList(
			@Param(value = "billingStatisticsVO") BillingStatisticsVO billingStatisticsVO,
			@Param(value = "start") int start, @Param(value = "end") int end);

	List<BillingStatisticsVO> getBillDtlList(
			@Param(value = "billingStatisticsVO") BillingStatisticsVO billingStatisticsVO);

}
