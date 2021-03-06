package com.ntels.ccbs.charge.mapper.payment.payment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.ntels.ccbs.charge.domain.payment.payment.NewSinglePaymentVO;

@Component
public interface NewSinglePaymentMapper {

	List<NewSinglePaymentVO> getAnonyReceiptSubList(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	int getAnonyReceiptSubListCount(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	List<NewSinglePaymentVO> getMyReceiptList(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	int getMyReceiptListCount(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	List<NewSinglePaymentVO> getBillInfo(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	int getBillInfoCount(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	List<NewSinglePaymentVO> getPermitOrg(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	int getPermitOrgCount(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	List<NewSinglePaymentVO> getLoanAvlAmt(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	int getLoanAvlAmtCount(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	List<Map<String, Object>> listAnonyReceiptSubExcel(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

	List<Map<String, Object>> listMyReceiptExcel(@Param(value = "soId") String soId, @Param(value = "usrId") String usrId,
			@Param(value = "lngTyp") String lngTyp, @Param(value = "procDt") String procDt);

	int insertEachDpst(@Param(value = "newSinglePaymentVO") NewSinglePaymentVO newSinglePaymentVO);

}
