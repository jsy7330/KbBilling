package com.ntels.ccbs.charge.mapper.batchprocmng.ambgtransmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommBalVO;

@Component
public interface AmbgTransMapper {

	/**
	 * 납부계정, 불명금 발생번호 조건에 따른 불명금 잔액 리스트를 조회한다.
	 * @param pymAcntId, ambgApplTgtNo
	 * @return List<TransCommBalVO>
	 */
	List<TransCommBalVO> getAmbgTransBalList(@Param("pymAcntId") String pymAcntId, @Param("ambgApplTgtNo") String ambgApplTgtNo);
	
	/**
	 * 불명 대체 이력을 생성한다.
	 * @param ambgTransHistVO
	 * @return
	 */
	int insertAmbgTransHist(@Param("ambgTransHistVO") AmbgTransHistVO ambgTransHistVO);
	
	/**
	 * 불명금 처리후에 잔액이 남은 경우 선수금으로 발생시킨다.
	 * @param prepayOccVO
	 * @return
	 */
	int insertPrepayOccFromAmbg(@Param("prepayOccVO") PrepayOccVO prepayOccVO);
	
	/**
	 * 불명 발생내역에 대체금액, 잔액을 반영한다.
	 * @param ambgOccVO
	 * @return
	 */
	int updateAmbgOccBalAmt(@Param("ambgOccVO") AmbgOccVO ambgOccVO);
	
	/**
	 * 불명 대체 처리후에 납부계정ID를 입금내역에 UPDATE 한다.
	 * @param depositVO
	 * @return
	 */
	int updateDepositAcntId(@Param("depositVO") DepositVO depositVO);

}
