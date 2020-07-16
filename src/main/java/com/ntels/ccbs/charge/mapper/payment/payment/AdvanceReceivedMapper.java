package com.ntels.ccbs.charge.mapper.payment.payment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.billing.billing.BillingStatisticsVO;
import com.ntels.ccbs.charge.domain.payment.advanceReceived.AdvanceReceivedVO;

public interface AdvanceReceivedMapper {

	int getPrepayOccCount(@Param(value = "advanceReceivedVO") AdvanceReceivedVO advanceReceivedVO);

	List<AdvanceReceivedVO> getPrepayOccList(@Param(value = "advanceReceivedVO") AdvanceReceivedVO advanceReceivedVO, @Param(value = "start") int start,
			@Param(value = "end") int end);

	int getBillInvoiceCount(@Param(value = "billingStatisticsVO") BillingStatisticsVO billingStatisticsVO);

	List<BillingStatisticsVO> getBillInvoiceList(@Param(value = "billingStatisticsVO") BillingStatisticsVO billingStatisticsVO, @Param(value = "start") int start,
			@Param(value = "end") int end);

	int getRefundAppliedCnt(@Param(value = "prepayOccSeqNo") String prepayOccSeqNo);

	int insertAction(@Param(value = "billingStatisticsVO") BillingStatisticsVO billingStatisticsVO);
}
