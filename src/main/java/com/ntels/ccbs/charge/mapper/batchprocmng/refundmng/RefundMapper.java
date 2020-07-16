package com.ntels.ccbs.charge.mapper.batchprocmng.refundmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.refundmng.RefundApplVO;
import com.ntels.ccbs.charge.domain.batchprocmng.refundmng.RefundOrgVO;
import com.ntels.ccbs.charge.domain.batchprocmng.refundmng.RefundVO;

@Component
public interface RefundMapper {

	/**
	 * 환불 신청 리스트를 조회한다.
	 * @param spymAcntId, srfndSeqNo
	 * @return List<RefundApplVO>
	 */
	List<RefundApplVO> getRefundApplList(@Param("spymAcntId") String spymAcntId, @Param("srfndSeqNo") String srfndSeqNo);
	
	/**
	 * 환불 대상에 대한 관리 조직 정보를 조회한다.
	 * @param rfndOccAmtCl, rfndOccTgtSeqNo
	 * @return RefundOrgVO
	 */
	RefundOrgVO getRefundOrgInfo(@Param("rfndOccAmtCl") String rfndOccAmtCl, @Param("rfndOccTgtSeqNo") String rfndOccTgtSeqNo);
	
	/**
	 * 환불 내역을 등록한다.
	 * @param refundVO
	 * @return
	 */
	int insertRefund(@Param("refundVO") RefundVO refundVO);
	
	/**
	 * 선수금 잔액에 환불 금액을 반영한다.
	 * @param refundApplVO
	 * @return
	 */
	int updatePrepayOccRfndAmt(@Param("refundApplVO") RefundApplVO refundApplVO);
	
	/**
	 * 붊명금 잔액에 환불 금액을 반영한다.
	 * @param refundApplVO
	 * @return
	 */
	int updateAmbgOccRfndAmt(@Param("refundApplVO") RefundApplVO refundApplVO);
	
	/**
	 * 보증금 잔액에 환불 금액을 반영한다.
	 * @param refundApplVO
	 * @return
	 */
	int updateAssrOccRfndAmt(@Param("refundApplVO") RefundApplVO refundApplVO);

	/**
	 * 환불 신청 내역에 환불 완료 정보를 반영한다.
	 * @param refundApplVO
	 * @return
	 */
	int updateRefundAppl(@Param("refundApplVO") RefundApplVO refundApplVO);

}
