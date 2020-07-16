package com.ntels.ccbs.charge.mapper.payment.payment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.payment.payment.CancelSinglePaymentVO;

@Component
public interface CancelSinglePaymentMapper {

	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attribute
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	
	List<CancelSinglePaymentVO> getCaseByCancelList(
			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
	);
	
	int getCaseByCancelListCount(
			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
	);
	
	List<CancelSinglePaymentVO> getPermitOrg(
			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
	);
	
	int getPermitOrgCount(
			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
	);
	
	List<CancelSinglePaymentVO> getLoanAvlAmt(
			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
	);
	
	int getLoanAvlAmtCount(
			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
	);
	
	List<CancelSinglePaymentVO> getRcptCnclCnt(	
			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
	);
	
	List<Map<String,Object>> getCaseByCancelListExcel(
			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO,
			@Param(value="lngTyp") String lngTyp
	);
}
