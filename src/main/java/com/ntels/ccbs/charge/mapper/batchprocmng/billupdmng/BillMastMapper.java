package com.ntels.ccbs.charge.mapper.batchprocmng.billupdmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.billupdmng.BillMastVO;
import com.ntels.ccbs.charge.domain.batchprocmng.billupdmng.MthCtrtUpymStusVO;

@Component
public interface BillMastMapper {

	/**
	 * 청구마스터 데이터를 조회한다.
	 * @param soId, billYymm, pymAcntId
	 * @return List<BillMastVO>
	 */
	List<BillMastVO> getForBillMastUpdt(@Param("ssoId") String ssoId, @Param("sbillYymm") String sbillYymm, @Param("sbillSeqNo") String sbillSeqNo,
			@Param("spymAcntId") String spymAcntId);

	/**
	 * 월계약미납현황 데이터를 조회한다.
	 * @param soId, billYymm, pymAcntId
	 * @return List<MthCtrtUpymStusVO>
	 */
	List<MthCtrtUpymStusVO> getForMthCtrtUpymStusUpdt(@Param("ssoId") String ssoId, @Param("sbillYymm") String sbillYymm, @Param("spymAcntId") String spymAcntId);

	/**
	 * 청구마스터의 총청구금액, 총조정금액, 총수납금액, 총UNPAY금액, 완납여부 컬럼을 변경한다.
	 * @param billMastVO
	 * @return
	 */
	int updateBillMast(@Param("billMastVO") BillMastVO billMastVO);

	/**
	 * 수납주기가 연선납일 경우 청구선납청구적용의 수납상태여부 컬럼을 변경한다.
	 * @param NBlivBillMastVO
	 * @return
	 */
	int updateprepdBillAply(@Param("billMastVO") BillMastVO billMastVO);
	
	/**
	 * 월계약미납현황의 총미납건수, 총미납금액, 월청구총액, 월수납총액 컬럼을 변경한다.
	 * @param MthCtrtUpymStusVO
	 * @return
	 */
	int updateMthCtrtUpymStus(@Param("mthCtrtUpymStusVO") MthCtrtUpymStusVO mthCtrtUpymStusVO);

	String getMaxYymm(@Param("ssoId") String ssoId);
	
	/**
	 * 월계약미납현황의 최종 청구년을을 조회한다
	 * @param ssoId,spymAcntId
	 * @return
	 */
	String getMaxUpymYymm(@Param("ssoId") String ssoId, @Param("spymAcntId") String spymAcntId);	
}
